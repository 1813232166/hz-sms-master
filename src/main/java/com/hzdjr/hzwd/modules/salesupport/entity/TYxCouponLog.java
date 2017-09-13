package com.hzdjr.hzwd.modules.salesupport.entity;

import java.util.Date;
import java.util.List;


/**
 * 营销日志
 * @author xq
 *
 */
public class TYxCouponLog {

       private String id;
       private String mobile;
       private String couponId;
       private String couponType;
       private String couponGroupId;
       private String couponGroupName;
       private String status;//0未使用1已使用2已过期
       private String activityId;
       private String activityName;
       private String useTime;
       private Date createDate;
       private String createBy;
       private Date updateDate;
       private String updateBy;
       private String remarks;
       private String delFlag;
       private Date endTime;//失效时间或者结束时间
       private String couponIds;
       private List<TYxCoupon> couponList;//组或活动关联优惠券
       
    public TYxCouponLog() {
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getCouponId() {
        return couponId;
    }


    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }


    public String getCouponType() {
        return couponType;
    }


    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }


    public String getCouponGroupId() {
        return couponGroupId;
    }


    public void setCouponGroupId(String couponGroupId) {
        this.couponGroupId = couponGroupId;
    }

    public String getCouponGroupName() {
        return couponGroupName;
    }


    public void setCouponGroupName(String couponGroupName) {
        this.couponGroupName = couponGroupName;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getActivityId() {
        return activityId;
    }


    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }


    public String getActivityName() {
        return activityName;
    }


    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }


    public String getUseTime() {
        return useTime;
    }


    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }


    public String getCreateBy() {
        return createBy;
    }


    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    
    public Date getCreateDate() {
        return createDate;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Date getUpdateDate() {
        return updateDate;
    }


    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public String getUpdateBy() {
        return updateBy;
    }


    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    public String getRemarks() {
        return remarks;
    }


    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public String getDelFlag() {
        return delFlag;
    }


    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    

    public Date getEndTime() {
        return endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public String getCouponIds() {
        return couponIds;
    }


    public void setCouponIds(String couponIds) {
        this.couponIds = couponIds;
    }


    public List<TYxCoupon> getCouponList() {
        return couponList;
    }


    public void setCouponList(List<TYxCoupon> couponList) {
        this.couponList = couponList;
    }


    @Override
    public String toString() {
        return "TYxCouponLog [id=" + id + ", mobile=" + mobile + ", couponId=" + couponId + ", couponType="
                + couponType + ", couponGroupId=" + couponGroupId + ", coupon_groupName=" + couponGroupName
                + ", status=" + status + ", activityId=" + activityId + ", activityName=" + activityName + ", useTime="
                + useTime + ", createDate=" + createDate + ", createBy=" + createBy + ", updateDate=" + updateDate
                + ", updateBy=" + updateBy + ", remarks=" + remarks + ", delFlag=" + delFlag + "]";
    }

    
}
