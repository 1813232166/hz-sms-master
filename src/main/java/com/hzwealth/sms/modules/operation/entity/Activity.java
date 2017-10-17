/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;

/**
 * 活动列表Entity
 * @author xuchenglin
 * @version 2016-10-21
 */
public class Activity extends DataEntity<Activity> {
	
	private static final long serialVersionUID = 1L;
	private String activityname;		// 活动名称
	private String rewardtype;		// 奖励类型
	private String rewardcount;		// 奖励额度
	private String couponid;		// 加息券ID
	private String activitydescribe;		// 活动说明
	private String lender;		// 发放人
	private Date lendtime;		// 发放时间
	private String status;		// 状态
	
	public Activity() {
		super();
	}

	public Activity(String id){
		super(id);
	}

	@Length(min=1, max=50, message="活动名称长度必须介于 1 和 50 之间")
	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	
	@Length(min=0, max=32, message="奖励类型长度必须介于 0 和 32 之间")
	public String getRewardtype() {
		return rewardtype;
	}

	public void setRewardtype(String rewardtype) {
		this.rewardtype = rewardtype;
	}
	
	@Length(min=0, max=10, message="奖励额度长度必须介于 0 和 10 之间")
	public String getRewardcount() {
		return rewardcount;
	}

	public void setRewardcount(String rewardcount) {
		this.rewardcount = rewardcount;
	}
	
	@Length(min=0, max=32, message="加息券ID长度必须介于 0 和 32 之间")
	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}
	
	public String getActivitydescribe() {
		return activitydescribe;
	}

	public void setActivitydescribe(String activitydescribe) {
		this.activitydescribe = activitydescribe;
	}
	
	@Length(min=0, max=32, message="发放人长度必须介于 0 和 32 之间")
	public String getLender() {
		return lender;
	}

	public void setLender(String lender) {
		this.lender = lender;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLendtime() {
		return lendtime;
	}

	public void setLendtime(Date lendtime) {
		this.lendtime = lendtime;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}