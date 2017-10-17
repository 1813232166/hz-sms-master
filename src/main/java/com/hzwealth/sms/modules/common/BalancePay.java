package com.hzwealth.sms.modules.common;

import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.hzwealth.sms.common.utils.PropertiesLoader;

/**
 * 后台放款调用接口
 * @author luwei
 *
 */

public class BalancePay {
	
      	private static PropertiesLoader loader = new PropertiesLoader("hzwd.properties");
	
		private static String reqUrlBalance ;
		
		private static String reqUrlBalanceStatus;
		
		private static String interfurlPlan;
		
		private static String xsSucess;
		
		private static String xsCancel;
		
		public static String borrowListFkUrl;
		
		public static String  foreignPassword;
		
		static{
			reqUrlBalance = loader.getProperty("interfurl_balance");
			reqUrlBalanceStatus = loader.getProperty("interfurl_balance_status");
			interfurlPlan = loader.getProperty("interfurl_plan");
			xsSucess = loader.getProperty("interfurl_xs_sucess");
			xsCancel = loader.getProperty("interfurl_xs_cancel");
			borrowListFkUrl = loader.getProperty("borrowlist_FkUrl");
			foreignPassword = loader.getProperty("offline_foreign_password");
		}
		
		/**
		 * 放款请求结算
		 * @param reqUrl
		 * @param prouectNo
		 * @return
		 */
		public static String  getBalance(String prouectNo) throws Exception{
			HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
			objectHashMapObject.put("projectNo",prouectNo);
			String resJson = JSONArray.toJSONString(objectHashMapObject);
			String resString = DataHttpClient.postData(reqUrlBalance, resJson, "POST");
			System.out.println(resString);
			return resString;
		}
		
		/**
		 * 放款请求结算状态
		 * @param reqUrl
		 * @param requestNo
		 * @return
		 */
		public static String  getBalanceByStatus(String requestNo) throws Exception{
			HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
			objectHashMapObject.put("requestNo",requestNo);
			String resJson = JSONArray.toJSONString(objectHashMapObject);
			String resString = DataHttpClient.postData(reqUrlBalanceStatus, resJson, "POST");
			System.out.println(resString);
			return resString;
		}
		
		
		/**
		 * 请求报文封装
		 * @param code
		 * @param version
		 * @param data
		 * @param userDevice
		 * @return
		 */
		public static String reqPlanObject(String applyId,String totalBillNums,long lendingTime) throws Exception{
			HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
			objectHashMapObject.put("applyId",applyId);
			objectHashMapObject.put("totalBillNums", totalBillNums);
			objectHashMapObject.put("lendingTime", lendingTime);
//			objectHashMapObject.put("monthlyPaymentOrigin", monthlyPaymentOrigin);
			String resJson = JSONArray.toJSONString(objectHashMapObject);
			System.out.println("请求还回/款计划数据报文===============>"+resJson);
			String resString = DataHttpClient.postData(interfurlPlan, resJson, "POST");
			return resString;
		}
		
		
		
		/**
		 * 放款成功调用信审
		 * @param borrowCode
		 * @return
		 * @throws Exception
		 */
		public static String fkSucessXS(String borrowCode) throws Exception{
			HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
			objectHashMapObject.put("borrowCode",borrowCode);
			String resJson = JSONArray.toJSONString(objectHashMapObject);
			System.out.println("放款成功请求信审===============>"+resJson);
			String resString = DataHttpClient.postData(xsSucess, resJson, "POST");
			return resString;
		}
		
	    /**
	     * 撤消调用信审
	     * @param borrowCode
	     * @return
	     * @throws Exception
	     */
		public static String fkCancelXS(String borrowCode) throws Exception{
			HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
			objectHashMapObject.put("borrowCode",borrowCode);
			String resJson = JSONArray.toJSONString(objectHashMapObject);
			System.out.println("撤消成功请求信审===============>"+resJson);
			String resString = DataHttpClient.postData(xsCancel, resJson, "POST");
			return resString;
		}
		
		
//		public static void main(String[] args) {
//			try {
//				String resObject = getBalance("xinshen001");
//				Map<String,Object>  mapObject = JSONObject.parseObject(resObject,Map.class);
//				Map<String,Object>  dataOrMessageObject = (Map<String,Object>)mapObject.get("dataOrMessage");
//				System.out.println(dataOrMessageObject.get("requestNo")); 
//				String requestNo = (String)dataOrMessageObject.get("requestNo");
//				
//				String resObjectByStatus = getBalanceByStatus(requestNo);
//				Map<String,Object>  mapObjectByStatus = JSONObject.parseObject(resObjectByStatus,Map.class);
//				boolean status = (boolean)mapObjectByStatus.get("success");
//				System.out.println(status);
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
}
