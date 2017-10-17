package com.hzwealth.sms.modules.salesupport.util;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzwealth.sms.modules.common.SendSms;
/**
 *  发送短信线程
 *  禁止相同的内容多个手机号连续一条一条提交;
 *  禁止使用多线程，异步提交;
 *
 */
public class SmsThread extends Thread{
    Logger logger = LoggerFactory.getLogger(SmsThread.class);
    List<String> mobils;
    String messageContent;
    String sendType;//sendType:01  校验码；02  金额变动提醒；03  其他
    public SmsThread(String messageContent,List<String> mobils,String sendType){
        this.messageContent=messageContent;
        this.mobils=mobils;
        this.sendType=sendType;
    }
    @Override
    public void run() {
        Date begin= new Date();
        int sendSum=3000;//每次最多发送量
        logger.info("发送短信开始……time：{}",begin);
        String mobString="";
        for (int i = 0; i < mobils.size(); i++) {
            mobString += ";"+mobils.get(i);
            if ((i!=0 && (i+1)%sendSum==0) || ((i+1)==mobils.size())) {
                SendSms.sendSmsMesageBatch(messageContent.trim(),mobString.substring(1),sendType);
                mobString="";
            }
        }
        Long spendTime =(System.currentTimeMillis()-begin.getTime())/1000;
        logger.info("发送短信结束……time：{}"+new Date(),"……共发送："+mobils.size()+"条；用时："+spendTime+" 秒");
    }
}
