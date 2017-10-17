package com.hzwealth.sms.modules.common;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hzwealth.sms.common.utils.PropertiesLoader;
import com.hzwealth.sms.modules.quartz.job.FkQueryJob;


/**
 * 短信接口
 * @author luwei
 *
 */
public class SendSms {
	private final static Logger logger = LoggerFactory.getLogger(SendSms.class);
	private static PropertiesLoader loader = new PropertiesLoader("sms.properties");
	
	public static String addr ;
	
	static{
		addr = loader.getProperty("interfurl_sms");
	}
	
	//{fieldJson:短信内容,addr:短信接收手机号码  可以用英文;号分隔多个手机号码,sendType:01  校验码；02  金额变动提醒；03  其他}
	public static  void  sendSmsMesage(String  content,String mobile) throws Exception{
		HashMap<String,Object> hashMapObject = new HashMap<String,Object>();
		hashMapObject.put("fieldJson", content);
		hashMapObject.put("addr", mobile);
		hashMapObject.put("sendType", "01");
		logger.debug("发送短信：：：：：：：：：：："+mobile+"===========>"+content);
		HttpUtils.sendPostMessage(addr, hashMapObject);
	}
	//{fieldJson:短信内容,addr:短信接收手机号码  可以用英文;号分隔多个手机号码,sendType:01  校验码；02  金额变动提醒；03  其他}
	public static  void  sendSmsMesageBatch(String  content,String mobile,String sendType){
        HashMap<String,Object> hashMapObject = new HashMap<String,Object>();
        hashMapObject.put("fieldJson", content);
        hashMapObject.put("addr", mobile);
        hashMapObject.put("sendType", sendType);//sendType:01  校验码；02  金额变动提醒；03  其他
        String jsonString = JSONObject.toJSONString(hashMapObject);
        logger.debug("发送短信：：：：：：：：：：："+mobile+"===========>"+content+"----------"+jsonString);
        String  res= HttpUtils.urlPostMethod(addr, jsonString);//{"message":"短信发送异常！","success":false}
        logger.info("短信发送结果："+res);
    }

}
