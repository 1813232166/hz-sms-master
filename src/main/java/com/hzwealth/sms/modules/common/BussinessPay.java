package com.hzwealth.sms.modules.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.hzwealth.sms.common.utils.PropertiesLoader;

/**
 * 请求支付业务类
 * @author luwei
 *
 */
public class BussinessPay {
	
	private static PropertiesLoader loader = new PropertiesLoader("sms.properties");
	
	public static String reqUrlBorrowCreate ;
	
	
	static{
		reqUrlBorrowCreate = loader.getProperty("interfurl_borrow_create");
	}
	
	
	/**
	 * 请求报文封装
	 * @param code
	 * @param version
	 * @param data
	 * @param userDevice
	 * @return
	 */
	public static HashMap<String,Object> reqObject(String code,String version,String data,String userDevice){
		HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
		objectHashMapObject.put("code",code);
		objectHashMapObject.put("version", version);
		objectHashMapObject.put("data", data);
		objectHashMapObject.put("userDevice", userDevice);
		return objectHashMapObject;
	}
	
	
	/**
	 * 流标调用
	 * @param hashMapObject
	 * @return
	 */
	public static String  getLb(HashMap<String,Object> hashMapObject) throws Exception{
		return HttpUtils.sendPostMessage(reqUrlBorrowCreate, hashMapObject);
	}

	
    /**
     * R2000 创建标的
     * @param requestNo    请求流水号
     * @param platformUserNo  借款方平台用户编号
     * @param projectNo    标的号
     * @param projectAmount  标的金额
     * @param projectName   标的名称
     * @param projectDescription   标的描述
     * @param projectType   【标的产品类型】
     * @param annnualInterestRate  年化利率
     * @param repaymentWay  【还款方式】
     * @param extend   标的扩展信息
     * @return
     */
	public static String createBorrow(String requestNo,String platformUserNo,
			String projectNo,String projectAmount,String projectName,
            String projectDescription,String projectType,String annnualInterestRate ,
			String repaymentWay){
			HashMap<String,Object> hashMapObject = new HashMap<String,Object>();
			hashMapObject.put("requestNo", requestNo);
			hashMapObject.put("platformUserNo", platformUserNo);
			hashMapObject.put("projectNo", projectNo);
			hashMapObject.put("projectAmount", projectAmount);
			hashMapObject.put("projectName", projectName);
			hashMapObject.put("projectDescription", projectDescription);
			hashMapObject.put("projectType", projectType);
			hashMapObject.put("annnualInterestRate", annnualInterestRate);
			hashMapObject.put("repaymentWay", repaymentWay);
			return JSONArray.toJSONString(hashMapObject);
	}
	
	
	/**
     * R2001 修改标的
     * @param requestNo    请求流水号
     * @param projectNo    标的号
     
     * @return
     */
	public static String updateBorrow(String requestNo,String status,
			String projectNo){
			HashMap<String,Object> hashMapObject = new HashMap<String,Object>();
			hashMapObject.put("requestNo", requestNo);
			hashMapObject.put("status", status);
			hashMapObject.put("projectNo", projectNo);
			return JSONArray.toJSONString(hashMapObject);
	}
	
	
	public static void main(String[] args) {
//		String createBorrowStr = createBorrow("1", "1", "1", "1", "2", "11111", "STANDARDPOWDER", "0.01", "ONE_TIME_SERVICING");
//		HashMap<String,Object> reqMap = reqObject("R2000", "v0001", createBorrowStr, "PC");
//		System.out.println(reqMap);
//		System.out.println(HttpUtils.sendPostMessage(reqUrlBorrowCreate, reqMap));
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf((int)((Math.random()*9+1)*100000)));
		
	}

}
