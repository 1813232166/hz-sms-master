package com.hzdjr.hzwd.modules.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.borrow.service.ContractSealService;
import com.hzdjr.hzwd.modules.borrow.service.TBorrowService;
import com.hzdjr.hzwd.modules.buss.service.MessageModulesService;
import com.hzdjr.hzwd.modules.common.BalancePay;
import com.hzdjr.hzwd.modules.common.BussinessPayHx;
import com.hzdjr.hzwd.modules.common.DataHttpClient;
import com.hzdjr.hzwd.modules.common.HttpUtils;
import com.hzdjr.hzwd.modules.common.SendSms;
import com.hzdjr.hzwd.modules.fk.service.FkuanService;
import com.hzdjr.hzwd.modules.innerreffere.service.TinnerReffereeService;
import com.hzdjr.hzwd.modules.rebate.service.InvestRecordService;

/**
 * 放款查询接口定时器
 * @author luwei
 *
 */
public class FkQueryJob extends QuartzJobBean {
	private final Logger logger = LoggerFactory.getLogger(FkQueryJob.class);

	@Autowired
	private FkuanService fKuanService;
	@Autowired
	private TBorrowService tBorrowService;
	@Autowired
	private ContractSealService contractSealService;
    @Autowired
	private TinnerReffereeService tinnerReffereeService;
    @Autowired
	private MessageModulesService messageModulesService;
    @Autowired
    private InvestRecordService investRecordService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		logger.debug("开始定时调用放款查询接口，time：{}",new Date());
		try {
		 String operator = "admin";
		 List<HashMap<String,Object>> objectList = 	fKuanService.selectFkQuery("R2030");
		 for (int i = 0; objectList!=null && i < objectList.size(); i++) {
			 HashMap<String,Object>  hashMapObject = objectList.get(i);
			 String code = (String)hashMapObject.get("code");
			 String userId = (String)hashMapObject.get("userId");
			 String requestNo = (String)hashMapObject.get("requestNo");
			 String borrowCode = (String)hashMapObject.get("borrowCode");
			 TBorrow tb = tBorrowService.getBorrowByCode(borrowCode);
			 String borrowId = tBorrowService.getBorrowByCode(borrowCode).getBorrowId();
			 String queryFk = BussinessPayHx.fkQuery(requestNo, borrowCode);
			 logger.debug("放款定时器请求报文========1========>"+queryFk);
			 String resInterface = HttpUtils.sendPostMessage(BussinessPayHx.reqUrlBorrowCreate, BussinessPayHx.reqObject("R2050", "v0001", queryFk, "PC"));
			 logger.debug("放款定时器请求返回报文========2========>"+resInterface);
			 JSONObject jsonObject = (JSONObject)JSONObject.parse(resInterface);
			 if("serviceException".equals((String)jsonObject.get("status"))){//系统异常的情况
					//fKuanService.deleteFkQuery(userId, requestNo, "R2030", borrowCode);
			 }else{//系统正常返回报文，进行报文解析判断
				 String status = "L";
				 String errorCode= (String)jsonObject.get("errorCode");
				 if(errorCode!=null && !"0".equals(errorCode)){//失败
						fKuanService.deleteFkQuery(userId, requestNo, code, borrowCode);
						fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowId, "调用放款接口异常",operator);
				 }
				 if(errorCode!=null && "0".equals(errorCode)){//成功
					 JSONObject dataObject= (JSONObject)jsonObject.get("data");
					 status = (String)dataObject.get("status");
				 }
				 if("S".equals(status)){//(成功的情况)如果成功,更新数据库为1,如果处理中不做处理，如果处理失败删除数据库
					 //TODO 具体调用方法
					 HashMap<String,Object>  objectHashMapObject = new HashMap<>();
					 String if_the_notice_of_success =Global.getConfig("if_the_notice_of_success");	
					 String paramJson = JSONArray.toJSONString(objectHashMapObject);
					 logger.info("资产权重重置调用参数===============>{}", paramJson); 
					 String resString = DataHttpClient.postData(if_the_notice_of_success, paramJson, "POST");
					 logger.info("资产权重重置调用返回=======>{}", resString);  
					 Map<String, String> resMap = JSONObject.parseObject(resString, new TypeReference<Map<String, String>>(){});
					 if("true".equals(resMap.get("isSuccess"))){
						 loanSuccess(userId, requestNo, errorCode, borrowCode, borrowId, operator);
						 //发送短信
						 sendSMS(tb);
						 Map<String,Object> investData = fKuanService.getInvestInfo(borrowCode);
						 investRecordService.saveInvestRecord(investData); 
					 }
				 }
				 if("F".equals(status)){//失败的情况
					 fKuanService.deleteFkQuery(userId, requestNo, code, borrowCode);
					 fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowId, "调用放款接口异常",operator);
					 logger.debug("放款失败流水查询:"+borrowCode);
				 }
				 logger.debug("放款查询结果状态======>"+status);
			 }
		  }
		} catch (Exception e) {
			logger.error("放款查询接口失败", e);
		}
		logger.debug("结束定时调用放款查询接口，time：{}",new Date());
	}
	public void loanSuccess(String userId,String requestNo,String code,String borrowCode,String borrowId,String operator) throws Exception{
		//(成功的情况)如果成功,更新数据库为1,如果处理中不做处理，如果处理失败删除数据库
	    fKuanService.updateFkQuery(userId, requestNo, code, borrowCode);
		//(4)结算状态
        String balanceStatus = BalancePay.getBalanceByStatus(requestNo);
	    logger.debug("请求结算第二步接口返回=====>"+balanceStatus);
        fKuanService.updateBorrowTime(borrowId,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//修改时间
		//(8)把标的状态改成修改状态
	      boolean updateBorrowDb = contractSealService.updateBorrowState(borrowId);//7
	      fKuanService.updateFinishInvestTime(borrowId,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
         //调用信审的标的请求接口
	     // BalancePay.fkSucessXS(borrowCode);//调用接口
	     //以下部分为散标集的调用逻辑，等同调用信审请求标的请求接口
	      HashMap<String,Object> paramMap = new HashMap<String,Object>();
		  paramMap.put("type", "11");
		  paramMap.put("remark", "放款成功");
		  paramMap.put("applyId", borrowCode);
		  paramMap.put("borrowStatus", "11");
		  paramMap.put("operatorName", "admin");
		  paramMap.put("foreign_password", BalancePay.foreignPassword);
		  String paramJson = JSONArray.toJSONString(paramMap);
		  logger.info("放款成功调用线下接口数据请求===============>{}", paramJson);
		  String resString = DataHttpClient.postData(BalancePay.borrowListFkUrl, paramJson, "POST");
		  logger.info("放款成功调用线下接口数据返回===============>{}", resString); 
	   //contractSealService.recordContract(borrowId, "PX_JKXY");
	   if(!updateBorrowDb){
	   logger.debug("更新数据库放款最后一步失败");
	   fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowId, "更新数据库放款失败",operator);
			//return "0";
	   }
 
	}
	public void sendSMS(TBorrow tb) throws Exception{
		   //发送短信
	    String mobile = tinnerReffereeService.get(tb.getUserid()).getMobile();
	    String  name = tinnerReffereeService.get(tb.getUserid()).getRealname();
		String  sex = tinnerReffereeService.get(tb.getUserid()).getSex();
		if("0".equals(sex)) sex = "女士";
		if("1".equals(sex)) sex = "先生";
		Map<String,Object> mapObjct = new HashMap<String,Object>();
		mapObjct.put("receiver", "3");		
		mapObjct.put("message_type", "11");	
		Map<String,Object> messageObject = messageModulesService.getMessage(mapObjct);
		if(messageObject!=null){
			String  message_content = (String)messageObject.get("message_content");
			String  message_status = (String)messageObject.get("message_status");
			if("1".equals(message_status)){
				message_content = message_content.replace("[title]", tb.getBorrowcode()).replace("[name]", name+sex);
				SendSms.sendSmsMesage(message_content, mobile);
			}
		}

	}
}
