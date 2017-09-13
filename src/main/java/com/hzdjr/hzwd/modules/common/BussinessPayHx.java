package com.hzdjr.hzwd.modules.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.hzdjr.hzwd.common.utils.PropertiesLoader;

/**
 * 请求支付业务类
 * @author luwei
 *
 */
public class BussinessPayHx {
	
	private static PropertiesLoader loader = new PropertiesLoader("hzwd.properties");
	
	public static String reqUrlBorrowCreate ;
	public static String reqUrlBorrowCancel ;
	
	
	static{
		reqUrlBorrowCreate = loader.getProperty("interfurl_borrow_create_hx");
		reqUrlBorrowCancel = loader.getProperty("interfurl_borrow_cancel_front");
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
     * R2031 创建标的
     * @param requestNo
     * @param LOANNO
     * @param projectNo
     * @param projectAmount
     * @param projectName
     * @param projectDescription
     * @param projectType
     * @param annnualInterestRate
     * @param INVESTBEGINDATE
     * @param INVESTENDDATE
     * @param INVESTRANGE
     * @param INVESTOBJSTATE
     * @param BWLIST
     * @return
     */
	public static String createBorrow(String requestNo,String LOANNO,
			String projectNo,String projectAmount,String projectName,
            String annnualInterestRate ,
			String INVESTBEGINDATE,String INVESTENDDATE,String INVESTRANGE,String INVESTOBJSTATE,List<HashMap<String,Object>> BWLIST){
			HashMap<String,Object> hashMapObject = new HashMap<String,Object>();
			hashMapObject.put("requestNo", requestNo);
			hashMapObject.put("LOANNO", LOANNO);
			hashMapObject.put("projectNo", projectNo);
			hashMapObject.put("projectAmount", projectAmount);
			hashMapObject.put("projectName", projectName);
			hashMapObject.put("annnualInterestRate", annnualInterestRate);
			hashMapObject.put("INVESTBEGINDATE", INVESTBEGINDATE);
			hashMapObject.put("INVESTENDDATE", INVESTENDDATE);
			hashMapObject.put("INVESTRANGE", INVESTRANGE);
			hashMapObject.put("INVESTOBJSTATE", INVESTOBJSTATE);
			hashMapObject.put("BWTOTALNUM", "1");
			hashMapObject.put("BWLIST", BWLIST);
			return JSONArray.toJSONString(hashMapObject);
	}
	
	

    /**
     * 
     * R2050 结果查询
     * @param requestNo
     * @param LOANNO
     * @return
     */
	public static String fkQuery(String requestNo,String LOANNO){
			HashMap<String,Object> hashMapObject = new HashMap<String,Object>();
			hashMapObject.put("requestNo", requestNo);
			hashMapObject.put("LOANNO", LOANNO);
			return JSONArray.toJSONString(hashMapObject);
	}
	
	/**
     * 
     * R2049 流标查询
     * @param requestNo
     * @return
     */
	public static String LbQuery(String requestNo){
			HashMap<String,Object> hashMapObject = new HashMap<String,Object>();
			hashMapObject.put("requestNo", requestNo);
			return JSONArray.toJSONString(hashMapObject);
	}
	
	/**
     * R2031 创建标的
     */
	public static List<HashMap<String,Object>> createBorrowByLend(String BWACNAME,String BWACNO,
			String BWAMT){
		    List<HashMap<String,Object>> objectByBorrowList = new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> hashMapObject = new HashMap<String,Object>();
			hashMapObject.put("BWACNAME", BWACNAME);
			hashMapObject.put("BWACNO", BWACNO);
			hashMapObject.put("BWAMT", BWAMT);
			objectByBorrowList.add(hashMapObject);
			return objectByBorrowList;
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
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String createBorrowStr = createBorrow("1", "1", "1", "1", "2", "11111", "STANDARDPOWDER", "0.01", "ONE_TIME_SERVICING");
//		HashMap<String,Object> reqMap = reqObject("R2000", "v0001", createBorrowStr, "PC");
//		System.out.println(reqMap);
//		System.out.println(HttpUtils.sendPostMessage(reqUrlBorrowCreate, reqMap));
		System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf((int)((Math.random()*9+1)*100000)));
		
		try {
			Date d = new Date();  
			  SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
			  String date = format.format(d);  
			  System.out.println("现在的日期是：" + date);  
			  
			  Calendar ca = Calendar.getInstance();  
			  ca.add(Calendar.DATE, 30);// 30为增加的天数，可以改变的  
			  d = ca.getTime();  
			  String backTime = format.format(d);  
			  System.out.println("增加天数以后的日期：" + backTime);  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
