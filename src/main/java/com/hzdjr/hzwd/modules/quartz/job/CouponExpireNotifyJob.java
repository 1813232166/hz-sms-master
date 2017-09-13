package com.hzdjr.hzwd.modules.quartz.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzdjr.hzwd.modules.buss.service.MessageModulesService;
import com.hzdjr.hzwd.modules.salesupport.service.CouponService;
import com.hzdjr.hzwd.modules.salesupport.util.SmsThread;

public class CouponExpireNotifyJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(CouponExpireNotifyJob.class);
    @Autowired
    CouponService couponService;
    @Autowired
    private MessageModulesService messageModulesService;
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("两天后优惠券失效，短信提醒开始……，time：{}",new Date());
            Map<String,Object> parMap=new HashMap<String, Object>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(new Date());
            Date today =null;
            try {
                today = sdf.parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            parMap.put("nowTime", today);
            List<String> mobileList = couponService.findAssignTimePast(parMap);
                Map<String,Object> mesMap=new HashMap<String, Object>();
                mesMap.put("receiver", "2");      
                mesMap.put("message_type", "20"); 
                Map<String, Object> messageObject = messageModulesService.getMessage(mesMap);
                if(mobileList.size()>0 && messageObject!=null){
                    String  messageContent = (String)messageObject.get("message_content");
                    String  message_status = (String)messageObject.get("message_status");
                    if("1".equals(message_status)){
                        new SmsThread(messageContent, mobileList, "03").start();
                    }
            }
        logger.info("两天后优惠券失效，短信提醒结束……，time：{}",new Date());
    }

}
