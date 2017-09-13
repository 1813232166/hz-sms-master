package com.hzdjr.hzwd.modules.borrow.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.modules.borrow.entity.Asset;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.borrow.util.BorrowAliasUtils;
import com.hzdjr.hzwd.modules.borrow.util.FinalJudgmentBorrowException;
import com.hzdjr.hzwd.modules.borrow.web.BorrowApplyController;
import com.hzdjr.hzwd.modules.buss.service.MessageModulesService;
import com.hzdjr.hzwd.modules.common.BalancePay;
import com.hzdjr.hzwd.modules.common.BussinessPayHx;
import com.hzdjr.hzwd.modules.common.DataHttpClient;
import com.hzdjr.hzwd.modules.common.HttpUtils;
import com.hzdjr.hzwd.modules.common.ReqUrlPreUUID;
import com.hzdjr.hzwd.modules.common.SendSms;
import com.hzdjr.hzwd.modules.innerreffere.service.TinnerReffereeService;
import com.hzdjr.hzwd.modules.match.dao.AssetDao;
import com.hzdjr.hzwd.modules.refferee.entity.TUser;
import com.hzdjr.hzwd.modules.refferee.service.TUserService;

/**
 * 处理终审成功后相关逻辑操作
 * @author XJIN
 *
 */
@Service
@Transactional
public class FinalJudgmentService {
	protected Logger logger = LoggerFactory.getLogger(BorrowApplyController.class);
	
	@Autowired
	private BorrowApplyService borrowApplyService;
	@Autowired
	private TUserService tUserService;	
	@Autowired
	private TBorrowService tBorrowService;
    @Autowired
	private TinnerReffereeService tinnerReffereeService;
    @Autowired
	private MessageModulesService messageModulesService;
    @Autowired
    private TAssetService tAssetService;


	public void finalJudgmentSuccess(String borrowId,String msgText,String assetId)throws FinalJudgmentBorrowException{
		Date d = new Date();
		String openborrowdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMdd");
		String openBorrowDateIntf =  sdf.format(d);

		updateBorrow(borrowId);

		TBorrow tb = tBorrowService.get(borrowId);	
	
		toHuaxin(tb, openBorrowDateIntf, sdf);
		
		/**
		 * 3成功之后更改borrow状态为9招标中,更新开标时间为当前时间,更新审核记录
		 */
		tBorrowService.changeBorrowstatus("9",borrowId);
		tBorrowService.updateBorrowOpenBorrowDate(openborrowdate,borrowId);
		borrowApplyService.saveOrUpdateSuggest(borrowId, msgText, "2", "pass");
		
		createAsset(tb,assetId);
		
		toBelowTheLine(tb);
		
		toMessage(tb);
	}

	
	@SuppressWarnings("unused")
	private void updateBorrow(String borrowId)throws FinalJudgmentBorrowException{
		try{
			TBorrow tBorrow= new TBorrow();
			String borrowAlias = BorrowAliasUtils.getBorrowNo();
			String borrowAliasNo = "PX"+borrowAlias.substring(3, borrowAlias.length());
			tBorrow.setBorrowId(borrowId);
			tBorrow.setBorrowalias(borrowAlias);// 标的中文别名
			tBorrow.setBorrowaliasno(borrowAliasNo);// 标的中文别名编号
			tBorrow.setRaiseterm("21");// 筹标期限(募集期)
			tBorrowService.releaseBorrow(tBorrow);
		} catch (Exception e) {
			logger.error("更新标的信息出现异常",e);
			throw new FinalJudgmentBorrowException(e.getMessage(),"终审通过,更新标的信息出现异常");
		}
	}
	
	private void toHuaxin(TBorrow tb,String openBorrowDateIntf,SimpleDateFormat  sdf)throws FinalJudgmentBorrowException{
		try {
					
			String hkType = tb.getPayMethod();//借款方式(debx等额本息，ycx一次性还款，dqhb到期还本)
			if("debx".equals(hkType)) hkType = "FIXED_PAYMENT_MORTGAGE";
			if("ycx".equals(hkType)) hkType = "ONE_TIME_SERVICING";
			if("dqhb".equals(hkType)) hkType = "FIRSEINTREST_LASTPRICIPAL";
			
			String userId = tb.getUserid();
			TUser tUserObject = tUserService.get(userId);//tBorrowService.getBankCardByUserId(userId);
			List<HashMap<String,Object>> bwListObject = BussinessPayHx.createBorrowByLend(tUserObject.getRealname(),tUserObject.getACNO(), tb.getBorrowamount());
			
			String INVESTBEGINDATE = openBorrowDateIntf;//sdf.format(tb.getOpenborrowdate());
			Integer INVESTRANGE = Integer.parseInt(tb.getRaiseterm());
			Calendar ca = Calendar.getInstance();  
			ca.add(Calendar.DATE, INVESTRANGE);// 30为增加的天数，可以改变的  
			String INVESTENDDATE = sdf.format(ca.getTime());
			
			ReqUrlPreUUID reqUrl = new ReqUrlPreUUID();
			String createBorrow = BussinessPayHx.createBorrow(reqUrl.GetRequestNo("2031"),tb.getBorrowcode(),tb.getBorrowcode(),tb.getBorrowamount(),tb.getBorrowtitle(),tb.getAnualrate(),INVESTBEGINDATE,INVESTENDDATE,INVESTRANGE.toString(),"0",bwListObject);
			
			logger.debug("审核通过请求创建标的接口请求数据===1====>"+createBorrow);
			String resInterface = HttpUtils.sendPostMessage(BussinessPayHx.reqUrlBorrowCreate, BussinessPayHx.reqObject("R2031", "v0001", createBorrow, "PC"));
			logger.debug("审核通过请求创建标的接口返回===2====>"+resInterface);
			JSONObject jsonObject = (JSONObject)JSONObject.parse(resInterface);
			JSONObject dataObject= (JSONObject)jsonObject.get("data");
			String errorCode = (String)dataObject.get("errorCode");
			String errorMsg = (String)dataObject.get("errorMsg");
			
			if(!("0".equals(errorCode)&&"success".equals(errorMsg))){
				logger.error("通过支付调用华新银行注册新标的失败");
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("终审通过,创建标的失败",e);
			throw new FinalJudgmentBorrowException(e.getMessage(),"终审通过,创建标的失败");
		}
	}
	 /**
	 * 调用线下接口创建标的，更新状态
	 */
	private void toBelowTheLine(TBorrow tb){
		try {
			 HashMap<String,Object> paramMap = new HashMap<String,Object>();
			 paramMap.put("type", "9");
			 paramMap.put("remark", "创建标的");
			 paramMap.put("applyId", tb.getBorrowcode());
			 paramMap.put("borrowStatus", "9");
			 paramMap.put("operatorName", "admin");
			 paramMap.put("foreign_password", BalancePay.foreignPassword);
			 String paramJson = JSONArray.toJSONString(paramMap);
			 logger.info("创建标的调用线下接口数据请求===============>{}", paramJson);
			 String resString = DataHttpClient.postData(BalancePay.borrowListFkUrl, paramJson, "POST");
			 logger.info("创建标的调用线下接口数据返回===============>{}", resString); 
		} catch (Exception e) {
			logger.error("终审成功后调用线下接口失败，不影响正常流程",e);
		}
	}
	  /**
	  * 发送短信
	  */
	private void toMessage(TBorrow tb){
		try {
			String mobile = tinnerReffereeService.get(tb.getUserid()).getMobile();
			Map<String,Object> mapObjct = new HashMap<String,Object>();
			mapObjct.put("receiver", "3");		
			mapObjct.put("message_type", "10");	
			Map<String,Object> messageObject = messageModulesService.getMessage(mapObjct);
			if(messageObject!=null){
				String  message_content = (String)messageObject.get("message_content");
				String  message_status = (String)messageObject.get("message_status");
				if("1".equals(message_status)){
					String  name = tinnerReffereeService.get(tb.getUserid()).getRealname();
					String  sex = tinnerReffereeService.get(tb.getUserid()).getSex();
					if("0".equals(sex)) sex = "女士";
					if("1".equals(sex)) sex = "先生";
					message_content = message_content.replace("[title]", tb.getBorrowcode()).replace("[name]", name+sex);
					SendSms.sendSmsMesage(message_content, mobile);
				}
			}
		} catch (Exception e) {
			logger.error("终审成功后短信发送失败，不影响正常流程",e);
		}

	}
	private void createAsset(TBorrow tb,String assetId)throws FinalJudgmentBorrowException{
		try {			
			Asset asset = copyBorrowToAsset(tb,assetId);
			tAssetService.insertTransferAsset(asset);
		} catch (Exception e) {
			logger.error("根据标的添加资产失败",e);
			throw new FinalJudgmentBorrowException(e.getMessage(),"终审通过,添加资产出现异常");
		}
		
	}

	
	private Asset copyBorrowToAsset(TBorrow tb,String assetId){
		Asset asset = new Asset();
		asset.setId(assetId);
		asset.setStatus("1");//待匹配
		asset.setAssetCategory("1");//通用资产
		asset.setAssetType("1");//新借款
		asset.setProductId(tb.getType());
		asset.setRaiseTerm(tb.getRaiseterm());
		asset.setAmount(new BigDecimal(tb.getBorrowamount()));
		asset.setResidueAmount(new BigDecimal(tb.getBorrowamount()));
		asset.setBorrowType("2");//个人借款
		asset.setRate(new BigDecimal(tb.getAnualrate()));
		asset.setNper(tb.getDeadline());
		asset.setUrgent(tb.getCriticalId());
		asset.setStickTop("0");
		asset.setEntryTime(new Date());
		asset.setMatchedTime(null);
		asset.setFreeze("0");
		asset.setFreezeTime(null);
		asset.setThawTime(null);
		asset.setSourceAssetId("0");
		asset.setOriginalAssetId("0");
		asset.setMatchedId(null);
		asset.setCustomWeight(1);
		asset.setWeight(Integer.valueOf(1));
		asset.setBorrowId(tb.getBorrowId());
		asset.setUserId(tb.getUserid());
		asset.setAssetCode(tb.getLoannumber());
		asset.setMatchingPenNumber(0);
		asset.setAssetSource("0");//TODO 暂时默认为来源为PC
		return asset;
	}
}
