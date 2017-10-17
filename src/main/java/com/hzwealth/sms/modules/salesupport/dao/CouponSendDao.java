package com.hzwealth.sms.modules.salesupport.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.salesupport.entity.CouponStatistics;
import com.hzwealth.sms.modules.salesupport.entity.ExcelCouponStatistics;
import com.hzwealth.sms.modules.salesupport.entity.TYxCoupon;
import com.hzwealth.sms.modules.salesupport.entity.TYxCouponGroup;

@MyBatisDao
public interface CouponSendDao extends CrudDao<TYxCouponGroup>{

    /**
     * 优惠券发送列表
     * @param TYxCoupon
     * @return
     */
    List<TYxCouponGroup> findCouponSendList(TYxCouponGroup couponGroup);
    /**
     * 发送审核列表
     * @param TYxCoupon
     * @return
     */
    List<TYxCouponGroup> findCouponSendAuditList(TYxCouponGroup couponGroup);
    /**
     * 优惠券发送提交审核
     * @param coupon
     * @return
     */
    int saveSubmitAuditCouponSend(TYxCouponGroup couponGroup);
 
    
    /**
     * 优惠券发送审核
     * @param coupon
     * @return
     */
    int updateAuditCouponSend(TYxCouponGroup couponGroup);
    /**
     * 优惠券统计
     * @return
     */
    List<CouponStatistics> couponStatistics(CouponStatistics couponStatistics);
    
    

    /**
     * 查询所有的优惠券关联用户的统计
     * 活动详情页面使用
     * @author zhouzhankui
     * @param excelCouponStatistics
     * @return
     */
    List<ExcelCouponStatistics> getAllCouponStatistics(ExcelCouponStatistics excelCouponStatistics);
    
    
    
    /**
     * 通过组ID获取优惠券
     * @param couponGroupId
     * @return
     */
    List<TYxCoupon> getRelCoupon(String couponGroupId);
    
    
    /**
     * 保存优惠券组信息
     * @param couponGroup
     * @return
     */
    int saveTYxCouponGroupInfo(TYxCouponGroup couponGroup);
    
    /**
     * 删除优惠券组信息
     * @param couponGroup
     * @return
     */
    int deleteTYxCouponGroupInfo(@Param("couponGroupId") String couponGroupId);
    /**
     * 查询用户手机号
     * @return
     */
    List<String> findUserMobile(Map<String, Integer> parMap);
    //用户总数
    HashMap<String, Long>findUserCount();
    List<Map<String, Object>> getUsedCoupon(CouponStatistics couponStatistics);

}
