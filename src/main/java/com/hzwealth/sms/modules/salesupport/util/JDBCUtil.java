package com.hzwealth.sms.modules.salesupport.util;

import java.sql.SQLException;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.hzwealth.sms.common.utils.PropertiesLoader;
import com.hzwealth.sms.common.utils.UUIDUtil;
import com.hzwealth.sms.modules.salesupport.entity.TYxCoupon;
import com.hzwealth.sms.modules.salesupport.entity.TYxCouponLog;

public class JDBCUtil {

    private static final PropertiesLoader property = new PropertiesLoader("hzwd.properties");
    public static final String type = property.getProperty("jdbc.type");  
    public static final String driver = property.getProperty("jdbc.driver");  
    public static final String url = property.getProperty("jdbc.url");  
    public static final String user = property.getProperty("jdbc.username");  
    public static final String password = property.getProperty("jdbc.password");  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public JDBCUtil(String sql) {  
        try {  
            Class.forName(driver);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            System.out.println("获取JDBC连接异常:"+e.getMessage());
            e.printStackTrace();  
        }  
    }  
   
    public boolean  batchInsertCouponLog(Connection conn,PreparedStatement pst,TYxCouponLog convertCouponLog,List<String> list) throws SQLException{
    //不需要插入字段   coupon_type,activity_id,activity_name,use_time,updateDate,updateBy,remarks,delFlag
  //String sql="INSERT INTO t_yx_coupon_log (id,mobile,coupon_group_id,coupon_group_name,status,createDate,createBy,coupon_id,coupon_type,end_time) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?);"
            conn.setAutoCommit(false);
            List<TYxCoupon> couponList = convertCouponLog.getCouponList();
            if (couponList.size()>0) {
                for (TYxCoupon tYxCoupon : couponList) {
                    tYxCoupon.setShixiao(this.calculateTime(tYxCoupon));
                }
            }
            int len = list.size();
            for(int i=0; i<len; i++) {
                if (couponList.size()>0) {
                    int siz = couponList.size();
                    for (int j = 0; j < siz; j++) {
                        pst.setString(1,UUIDUtil.genUUIDString());
                        pst.setString(2, list.get(i));
                        pst.setString(3, convertCouponLog.getCouponGroupId());
                        pst.setString(4, convertCouponLog.getCouponGroupName());
                        pst.setString(5, convertCouponLog.getStatus());
                        pst.setTimestamp(6,new Timestamp(System.currentTimeMillis()));//发送时间
                        pst.setString(7, convertCouponLog.getCreateBy());
                        pst.setString(8, couponList.get(j).getId());
                        pst.setString(9,couponList.get(j).getCouponTypeId());
                        pst.setString(10,couponList.get(j).getShixiao());
                        pst.addBatch(); 
                        //提交
                        long total=(i+1)*siz;
                        if((total!=0 && total%10000==0) || total==len*siz){//可以设置不同的大小；如50，100，200，500，1000等等  
                            pst.executeBatch();  
                            conn.commit();  
                            pst.clearBatch();        //提交后，Batch清空。
                        }
                    }
                }
            }
        return true;
    }
    /**
     * 计算失效时间
     * @param coupon
     * @return
     */
    public String calculateTime(TYxCoupon coupon){
        String nwTime=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        if (null!=coupon.getEndTime()) {
            nwTime=sdf.format(coupon.getEndTime());
        }else{
            Calendar c = Calendar.getInstance();
            c.setTime(new java.util.Date());
            c.add(Calendar.DATE,Integer.parseInt(coupon.getEffectiveDays()));
            nwTime = sdf.format(c.getTime());
        }
        return nwTime;
    }

    /**
     * 释放连接
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void releaseDB(Connection connection,PreparedStatement statement, ResultSet resultSet) {  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (statement != null) {  
            try {  
                statement.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        if (connection != null) {  
            try {  
                connection.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
}  