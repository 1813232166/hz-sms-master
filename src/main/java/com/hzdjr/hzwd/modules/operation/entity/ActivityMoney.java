/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

/**
 * 活动现金发放Entity
 * @author hdj
 * @version 2016-10-26
 */
public class ActivityMoney extends DataEntity<ActivityMoney> {
	
	private static final long serialVersionUID = 1L;
	private String activityid;		// 活动ID
	private String amount;		// 现金数
	private String status;		// 发放状态（1待发2已发3失败）
	private Date senddate;		// 发放时间
	private Date beginSenddate;		// 开始 发放时间
	private Date endSenddate;		// 结束 发放时间
	private String realName;	// 用户名
	private String mobile;		// 用户手机号
	private String activityName;	// 活动名称
	
	public ActivityMoney() {
		super();
	}

	public ActivityMoney(String id){
		super(id);
	}
	
	@ExcelField(title="姓名",align=2,sort=0,groups=1)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	@ExcelField(title="手机号",align=2,sort=10,groups=1)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@ExcelField(title="活动名称",align=2,sort=30,groups=1)
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Length(min=1, max=32, message="活动ID长度必须介于 1 和 32 之间")
	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	@ExcelField(title="金额（元）",align=2,sort=20,groups=1)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=1, message="发放状态（1待发2已发3失败）长度必须介于 0 和 1 之间")
	@ExcelField(title="状态 ",align=2,sort=40,groups=1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="发放时间",align=2,sort=50,groups=1)
	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	
	public Date getBeginSenddate() {
		return beginSenddate;
	}

	public void setBeginSenddate(Date beginSenddate) {
		this.beginSenddate = beginSenddate;
	}
	
	public Date getEndSenddate() {
		return endSenddate;
	}

	public void setEndSenddate(Date endSenddate) {
		this.endSenddate = endSenddate;
	}
		
}