package com.hzwealth.sms.modules.rebate.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.rebate.service.InvestRebateService;
import com.hzwealth.sms.modules.rebate.service.InvestRecordService;
import com.hzwealth.sms.modules.rebate.service.RebateRecordService;
import com.hzwealth.sms.modules.rebate.util.AESUtil;
/**
 * 接口
 * @author wzb
 *
 */
@Controller
@RequestMapping("/rebate")
public class InvestRecordController extends BaseController{
	@Autowired
	private InvestRecordService investRecordService;
	@Autowired
	private InvestRebateService investRebateService;
	@Autowired
	private RebateRecordService rebateRecordService;
	/**
	 * 出借记录
	 * 1.0调用此方法进行记录投资记录,单条记录
	 * @return
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping("/recordInvest")
	@ResponseBody
	public String investRecord(HttpServletRequest request, HttpServletResponse response){
		String requestData = request.getParameter("data");
		logger.info("投资记录（解密前）："+requestData);
		try {
			requestData = AESUtil.deCrypt(requestData, AESUtil.dKey);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("解密失败");
			return "FAIL";
		}
		logger.info("解密后："+requestData);
		Map<String, Object> requestMap = JSON.parseObject(requestData);
		investRecordService.saveInvestRecord(requestMap);
		logger.info("投资记录录入完成。");
		return "SUCCESS";
	}
	/**
	 * 测试专用
	 */
	@RequestMapping("/rebateComputerJob")
	@ResponseBody
	public void rebateComputerJob(){
		logger.info("返佣计算开始==================");
		investRebateService.rebateComputerJob();
		logger.info("==================返佣计算结束");
	}
	/**
	 * 测试专用
	 */
	@RequestMapping("/test")
	public String testRebateJsp(){
		return "modules/rebate/test";
	}
	/**
	 * 每月统计返佣
	 */
	@RequestMapping("/monthRebateJob")
	@ResponseBody
	public void monthRebateJob(){
		rebateRecordService.computerMonthRebateJob();
	}
}
