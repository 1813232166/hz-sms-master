package com.hzdjr.hzwd.modules.quartz.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.hzdjr.hzwd.modules.salesupport.service.ActivityInfoService;
import com.hzdjr.hzwd.modules.salesupport.service.CouponService;
import com.hzdjr.hzwd.modules.salesupport.util.JDBCUtil;

public class CouponActivityJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(CouponActivityJob.class);
    @Autowired
    CouponService couponService;
    @Autowired
    ActivityInfoService activityInfoService;
    
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("定时更新优惠券活动状态开始……，time：{}",new Date());
            couponService.updstatus();
            Map<String,Object> parMap=new HashMap<String, Object>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            String format = sdf.format(new Date());
            Date today =null;
            try {
                today = sdf.parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            parMap.put("nowTime", today);
            //更新活动状态
            List<Map<String, Object>> activityList = activityInfoService.findAlList(parMap);
            List<String> idList = new ArrayList<String>();
            for (Map<String, Object> map : activityList) {
                String id = (String) map.get("id");
                    idList.add(id);
            }
            if (idList.size()>0) {
                activityInfoService.updateActivityStatus(idList);
            }
            //更新log表 status=2
            this.updateYXLog(parMap);
        logger.info("定时更新优惠券活动状态结束……，time：{}",new Date());
    }

    public void updateYXLog(Map<String,Object> parMap){
        String sql="UPDATE t_yx_coupon_log SET `status`= '2' WHERE id= ?";
        JDBCUtil jdbcUtil= new JDBCUtil(sql);
        int pageSize=2000;
        parMap.put("pageSize", pageSize);
        long pageCount  =  couponService.findCountPastData(parMap).get("total")/pageSize;
        List<String> logIdList  = null;
        try {
            for (int i = 0; i < pageCount+1; i++) {
                parMap.put("pageIndex", i*pageSize);
                List<Map<String, Object>> pastData = couponService.findAllPastData(parMap);
                logIdList  = new ArrayList<String>();
                for (Map<String, Object> map : pastData) {
                    String id = (String) map.get("id");
                        logIdList.add(id);
                }
                this.executeSQL(jdbcUtil,sql,logIdList);
            }
        } catch (SQLException e) {
            logger.info("定时更新t_yx_coupon_log过期异常"+e.getMessage());
            e.printStackTrace();
        }finally{
            JDBCUtil.releaseDB(jdbcUtil.conn, jdbcUtil.pst, null);
        }
    }
    //更新log表
    public void executeSQL(JDBCUtil jdbcUtil,String sql,List<String> logIdList) throws SQLException{
            Connection conn = jdbcUtil.conn;
            PreparedStatement pst = jdbcUtil.pst;
            conn.setAutoCommit(false);
            int len = logIdList.size();
            for (int i=0; i<len; i++) {
                pst.setString(1, logIdList.get(i));
                pst.addBatch(); 
                if((i!=0 && i%200==0) || i==len-1){
                    pst.executeBatch();  
                    conn.commit();  
                    pst.clearBatch(); //提交后，Batch清空。
                }
            }
       
    }
    
    public Date getYesterday(){
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  
        calendar.add(Calendar.DAY_OF_MONTH, -4);  
        Date yesterday = calendar.getTime(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(yesterday));
        return yesterday;
    }
}
