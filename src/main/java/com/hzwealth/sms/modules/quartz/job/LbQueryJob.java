package com.hzwealth.sms.modules.quartz.job;

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
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrow.service.TBorrowService;
import com.hzwealth.sms.modules.buss.service.MessageModulesService;
import com.hzwealth.sms.modules.common.BalancePay;
import com.hzwealth.sms.modules.common.BussinessPayHx;
import com.hzwealth.sms.modules.common.DataHttpClient;
import com.hzwealth.sms.modules.common.HttpUtils;
import com.hzwealth.sms.modules.common.SendSms;
import com.hzwealth.sms.modules.fk.service.FkuanService;
import com.hzwealth.sms.modules.innerreffere.service.TinnerReffereeService;
import com.hzwealth.sms.modules.invest.entity.Invest;
import com.hzwealth.sms.modules.invest.service.InvestService;

/**
 * 流标查询接口定时器
 * @author luwei
 *
 */
public class LbQueryJob extends QuartzJobBean {
	private final Logger logger = LoggerFactory.getLogger(LbQueryJob.class);

	@Autowired
	private FkuanService fKuanService;
	@Autowired
	private TBorrowService tBorrowService;
    @Autowired
	private InvestService investService;
    @Autowired
	private MessageModulesService messageModulesService;
    @Autowired
	private TinnerReffereeService tinnerReffereeService;
    
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		logger.debug("开始定时调用流标查询接口query，time：{}",new Date());
		try {
			List<HashMap<String,Object>> objectList = 	fKuanService.selectFkQuery("R2039");
			 for (int i = 0; objectList!=null && i < objectList.size(); i++) {
				 HashMap<String,Object>  hashMapObject = objectList.get(i);
				 String code = (String)hashMapObject.get("code");
				 String userId = (String)hashMapObject.get("userId");
				 String requestNo = (String)hashMapObject.get("requestNo");
				 String borrowCode = (String)hashMapObject.get("borrowCode");
				 String queryFk = BussinessPayHx.LbQuery(requestNo);
				 logger.debug("流标查询请求=======1=========>"+BussinessPayHx.reqObject("R2049", "v0001", queryFk, "PC"));
				 String resInterface = HttpUtils.sendPostMessage(BussinessPayHx.reqUrlBorrowCreate, BussinessPayHx.reqObject("R2049", "v0001", queryFk, "PC"));
				 logger.debug("流标查询返回=========2=======>"+resInterface);
				 TBorrow tborrowObject = tBorrowService.getBorrowByCode(borrowCode);
				 String borrowId = tborrowObject.getBorrowId();
				 JSONObject jsonObject = (JSONObject)JSONObject.parse(resInterface);
				 String status = "L";
				 String errorCode= (String)jsonObject.get("errorCode");
				 if(errorCode!=null && !"0".equals(errorCode)){//失败
						fKuanService.deleteFkQuery(userId, requestNo, code, borrowCode);
				 }
				 if(errorCode!=null && "0".equals(errorCode)){//成功
					 JSONObject dataObject= (JSONObject)jsonObject.get("data");
					 status = (String)dataObject.get("status");
				 }
				 if("S".equals(status)){//(成功的情况)如果成功,更新数据库为1,如果处理中不做处理，如果处理失败删除数据库
					   List<Invest> investObject = investService.findInvestById(borrowId);
						//投标流水号bizid修改flag=3
					   StringBuffer sb = new StringBuffer();
					   if(investObject!=null&&investObject.size()>0){
							for (int j = 0; j < investObject.size(); j++) {
								String bizId = investObject.get(j).getBizid();
								investService.updateInvestFlag(bizId);
								String mobile = tinnerReffereeService.get(investObject.get(j).getInvestor()).getMobile();
								Map<String,Object> mapObjct = new HashMap<String,Object>();
								mapObjct.put("receiver", "2");		
								mapObjct.put("message_type", "6");	
								Map<String,Object> messageObject = messageModulesService.getMessage(mapObjct);
								if(messageObject!=null){
									String  message_content = (String)messageObject.get("message_content");
									String  message_status = (String)messageObject.get("message_status");
									if("1".equals(message_status)){
										String  name = tinnerReffereeService.get(investObject.get(j).getInvestor()).getRealname();
										String  sex = tinnerReffereeService.get(investObject.get(j).getInvestor()).getSex();
										if("0".equals(sex)) sex = "女士";
										if("1".equals(sex)) sex = "先生";
										message_content = message_content.replace("[title]", tborrowObject.getBorrowtitle()).replace("[name]", name+sex);
										SendSms.sendSmsMesage(message_content, mobile);
									}
								}
								//流标的时候取出优惠券ID及手机号
								String couponId = investObject.get(j).getCoupon_log_id();
								if(couponId!=null && !"".equals(couponId)){
									Map<String,Object> mapObject = new HashMap<String,Object>();
									mapObject.put("id", couponId);
									mapObject.put("mobile", mobile);
									tinnerReffereeService.queryCouponLb(mapObject);
								}
							}
							String j_mobile = tinnerReffereeService.get(tborrowObject.getUserid()).getMobile();
							Map<String,Object> j_mapObjct = new HashMap<String,Object>();
							j_mapObjct.put("receiver", "3");		
							j_mapObjct.put("message_type", "12");	
							Map<String,Object> j_messageObject = messageModulesService.getMessage(j_mapObjct);
							if(j_messageObject!=null){
								String  j_message_content = (String)j_messageObject.get("message_content");
								String  j_message_status = (String)j_messageObject.get("message_status");
								if("1".equals(j_message_status)){
									String  j_name = tinnerReffereeService.get(tborrowObject.getUserid()).getRealname();
									String  j_sex = tinnerReffereeService.get(tborrowObject.getUserid()).getSex();
									if("0".equals(j_sex)) j_sex = "女士";
									if("1".equals(j_sex)) j_sex = "先生";
									j_message_content = j_message_content.replace("[borrowId]", tborrowObject.getLoannumber()).replace("[name]", j_name+j_sex);
									SendSms.sendSmsMesage(j_message_content, j_mobile);
								}
							}
						}
						fKuanService.updateFkQuery(userId, requestNo, code, borrowCode);
						tBorrowService.changeBorrowstatus("16", borrowId);
						//BalancePay.fkCancelXS(borrowCode);
						 HashMap<String,Object> paramMap = new HashMap<String,Object>();
						 paramMap.put("type", "999");
						 paramMap.put("remark", "流标成功，自动打回线下");
						 paramMap.put("applyId", borrowCode);
						 paramMap.put("borrowStatus", "999");
						 paramMap.put("operatorName", "admin");
						 paramMap.put("foreign_password", BalancePay.foreignPassword);
						 String paramJson = JSONArray.toJSONString(paramMap);
						 logger.info("流标成功调用线下接口数据请求===============>{}", paramJson);
						 String resString = DataHttpClient.postData(BalancePay.borrowListFkUrl, paramJson, "POST");
						 logger.info("流标成功调用线下接口数据返回===============>{}", resString); 
				 }
				 if("F".equals(status)){//失败的情况
					 fKuanService.deleteFkQuery(userId, requestNo, code, borrowCode);
					 logger.info("流标查询失败记录:"+borrowCode); 
				 }
			}
		} catch (Exception e) {
			logger.error("流标查询接口失败", e);
		}

		logger.debug("结束定时调用流标查询接口query，time：{}",new Date());
	}

}
