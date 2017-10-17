package com.hzwealth.sms.modules.salesupport.entity;

import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

/**
 * 优惠券统计
 * @author xq
 *
 */
public class ExcelCouponStatistics extends DataEntity<CouponStatistics>{
    private static final long serialVersionUID = 1L;

    private String userMobile; //手机号
    private Integer sentCouponSum; //发送优惠券数量
    private String sentdTime; //发送时间
    private Integer usedCouponSum;//已使用优惠券数量
    private Integer unusedCouponSum;//未使用优惠券数量
    private Integer expiredCouponSum;//已过期优惠券数量
    private Integer endLimit;
    private String couponGroupId;
    private String activityId;
    @ExcelField(title = "手机号",type=1,align=2,sort=10)
    public String getUserMobile() {
        return userMobile;
    }
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    @ExcelField(title = "发送优惠券数量",type=1,align=2,sort=20)
    public Integer getSentCouponSum() {
        return sentCouponSum;
    }
    public void setSentCouponSum(Integer sentCouponSum) {
        this.sentCouponSum = sentCouponSum;
    }
    
    @ExcelField(title = "发送时间",type=1,align=2,sort=30)
	public String getSentdTime() {
		return sentdTime;
	}
	public void setSentdTime(String sentdTime) {
		this.sentdTime = sentdTime;
	}

    @ExcelField(title = "已使用优惠券数量",type=1,align=2,sort=40)
    public Integer getUsedCouponSum() {
        return usedCouponSum;
    }
    
    public void setUsedCouponSum(Integer usedCouponSum) {
        this.usedCouponSum = usedCouponSum;
    }
    @ExcelField(title = "未使用优惠券数量",type=1,align=2,sort=50)
    public Integer getUnusedCouponSum() {
        return unusedCouponSum;
    }
    public void setUnusedCouponSum(Integer unusedCouponSum) {
        this.unusedCouponSum = unusedCouponSum;
    }
    @ExcelField(title = "已过期优惠券数量",type=1,align=2,sort=60)
    public Integer getExpiredCouponSum() {
        return expiredCouponSum;
    }
    public void setExpiredCouponSum(Integer expiredCouponSum) {
        this.expiredCouponSum = expiredCouponSum;
    }
    
    
    public Integer getEndLimit() {
        return endLimit;
    }
    public void setEndLimit(Integer endLimit) {
        this.endLimit = endLimit;
    }
    
    public String getCouponGroupId() {
        return couponGroupId;
    }
    public void setCouponGroupId(String couponGroupId) {
        this.couponGroupId = couponGroupId;
    }
    public String getActivityId() {
        return activityId;
    }
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
    @Override
    public String toString() {
        return "CouponStatistics [userMobile=" + userMobile + ", sentCouponSum=" + sentCouponSum + ",sentdTime="+sentdTime+", usedCouponSum="
                + usedCouponSum + ", unusedCouponSum=" + unusedCouponSum + ", expiredCouponSum=" + expiredCouponSum
                + "]";
    }

    
}
