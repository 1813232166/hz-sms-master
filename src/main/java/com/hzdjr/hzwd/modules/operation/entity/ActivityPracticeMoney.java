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
 * 体验金Entity
 * @author hdj
 * @version 2016-10-27
 */
public class ActivityPracticeMoney extends DataEntity<ActivityPracticeMoney> {
	
	private static final long serialVersionUID = 1L;
	private String activityid;		// 活动ID
	private String giftamount;		// 体验金额
	private String isused;		// 使用状态（1未用过2用过）
	private String usedamount;		// 使用金额
	private String profitamount;		// 用户收益
	private String status;		// 发放状态（1已发2待发3失败）
	private String sourcechannel;		// 渠道（1web、2安卓、3苹果、4微信、5其它）
	private Date senddate;		// 发放时间
	private Date useddate;		// 使用时间
	private Date profitdate;		// 收益时间
	private String profitid;		// 收益发放ID
	private String profitstatus;		// 钱发放状态（1已发2待发3失败）
	private Date beginSenddate;		// 开始 发放时间
	private Date endSenddate;		// 结束 发放时间
	private Date beginUseddate;		// 开始 使用时间
	private Date endUseddate;		// 结束 使用时间
	private Date beginProfitdate;		// 开始 收益时间
	private Date endProfitdate;		// 结束 收益时间
	private String realName;		//用户名
	private String mobile;		//用户手机号
	private String activityName;	//活动名称
	private Date createDate;	//创建时间
	
	public ActivityPracticeMoney() {
		super();
	}

	public ActivityPracticeMoney(String id){
		super(id);
	}

	public Date getCreateDate() {
		return createDate;
	}
	 @JsonFormat(pattern="yyyy-MM-dd")
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ExcelField(title="姓名",align=2,sort=0,groups={1,2})
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	@ExcelField(title="手机号",align=2,sort=10,groups={1,2})
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
	
	public Date getBeginUseddate() {
		return beginUseddate;
	}

	public void setBeginUseddate(Date beginUseddate) {
		this.beginUseddate = beginUseddate;
	}
	
	public Date getEndUseddate() {
		return endUseddate;
	}

	public void setEndUseddate(Date endUseddate) {
		this.endUseddate = endUseddate;
	}
		
	public Date getBeginProfitdate() {
		return beginProfitdate;
	}

	public void setBeginProfitdate(Date beginProfitdate) {
		this.beginProfitdate = beginProfitdate;
	}
	
	public Date getEndProfitdate() {
		return endProfitdate;
	}

	public void setEndProfitdate(Date endProfitdate) {
		this.endProfitdate = endProfitdate;
	}
		
	@Length(min=1, max=32, message="活动ID长度必须介于 1 和 32 之间")
	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	
	@ExcelField(title="发放金额（元）",align=2,sort=20,groups=1)
	public String getGiftamount() {
		return giftamount;
	}

	public void setGiftamount(String giftamount) {
		this.giftamount = giftamount;
	}
	
	@Length(min=0, max=1, message="使用状态（1未用过2用过）长度必须介于 0 和 1 之间")
	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}
	@ExcelField(title="使用金额（元）",align=2,sort=20,groups=2)
	public String getUsedamount() {
		return usedamount;
	}

	public void setUsedamount(String usedamount) {
		this.usedamount = usedamount;
	}
	@ExcelField(title="用户收益",align=2,sort=50,groups=2)
	public String getProfitamount() {
		return profitamount;
	}

	public void setProfitamount(String profitamount) {
		this.profitamount = profitamount;
	}
	
	@Length(min=0, max=1, message="发放状态（1已发2待发3失败）长度必须介于 0 和 1 之间")
	@ExcelField(title="状态",align=2,sort=50,groups=1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="渠道（1web、2安卓、3苹果、4微信、5其它）长度必须介于 0 和 1 之间")
	@ExcelField(title="渠道",align=2,sort=40,groups={1,2})
	public String getSourcechannel() {
		return sourcechannel;
	}

	public void setSourcechannel(String sourcechannel) {
		this.sourcechannel = sourcechannel;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="发放时间",align=2,sort=60,groups=1)
	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="使用时间",align=2,sort=30,groups=2)	
	public Date getUseddate() {
		return useddate;
	}

	public void setUseddate(Date useddate) {
		this.useddate = useddate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="收益时间",align=2,sort=60,groups=2)
	public Date getProfitdate() {
		return profitdate;
	}

	public void setProfitdate(Date profitdate) {
		this.profitdate = profitdate;
	}
	
	@Length(min=0, max=32, message="收益发放ID长度必须介于 0 和 32 之间")
	public String getProfitid() {
		return profitid;
	}

	public void setProfitid(String profitid) {
		this.profitid = profitid;
	}
	
	@Length(min=0, max=1, message="钱发放状态（1已发2待发3失败）长度必须介于 0 和 1 之间")
	@ExcelField(title="收益发放",align=2,sort=70,groups=2)
	public String getProfitstatus() {
		return profitstatus;
	}

	public void setProfitstatus(String profitstatus) {
		this.profitstatus = profitstatus;
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