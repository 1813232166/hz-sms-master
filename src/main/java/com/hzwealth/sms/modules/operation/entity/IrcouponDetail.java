/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

/**
 * 加息券使用列表Entity
 * @author hdj
 * @version 2016-11-02
 */
public class IrcouponDetail extends DataEntity<IrcouponDetail> {
	
	private static final long serialVersionUID = 1L;
	private String realname;		// 用户真实姓名
	private String mobile;		// 用户手机号
	private String ircouponid;		// 加息券ID
	private String activityid;		// 活动ID
	private String investname;		// 出借项目名称
	private String investtype;		// 出借类型（1普享标 2 出借计划 3其他）
	private String investmoney;		// 出借金额
	private String sendstatus;		// 发放状态（1已发 2待发 3失败）
	private String sourcechannel;		// 发放渠道（1web、2安卓、3苹果、4微信、5其它）
	private Date senddate;		// 发放时间
	private String uesdstatus;		// 加息券使用状态(0已结束1收益中3未使用)
	private String consumchannel;		// 消费渠道（1web、2安卓、3苹果、4微信、5其它）
	private Date useddate;		// 使用时间
	private String profit;		// 收益
	private Date beginUseddate;		// 开始 使用时间
	private Date endUseddate;		// 结束 使用时间
	private Date beginSenddate;		// 开始 发放时间
	private Date endSenddate;		// 结束 发放时间
	private String myStatus;	//区分加息券查询状态
	private String ircouponName; // 加息券名称
	private BigDecimal scale;  // 加息比例
	private String profitTime;  //加息天数 
	private String activityName;	//活动名称
	
	
	
	public IrcouponDetail() {
		super();
	}

	public IrcouponDetail(String id){
		super(id);
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
	@ExcelField(title="活动名称",align=2,sort=30,groups=2)
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	@ExcelField(title="加息券名称",align=2,sort=20,groups={1,2})
	public String getIrcouponName() {
		return ircouponName;
	}

	public void setIrcouponName(String ircouponName) {
		this.ircouponName = ircouponName;
	}
	@ExcelField(title="加息比例",align=2,sort=60,groups={1,2})
	public BigDecimal getScale() {
		return scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = scale;
	}
	@ExcelField(title="加息时间",align=2,sort=70,groups={1,2})
	public String getProfitTime() {
		return profitTime;
	}

	public void setProfitTime(String profitTime) {
		this.profitTime = profitTime;
	}

	public String getMyStatus() {
		return myStatus;
	}

	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}
	
	@Length(min=0, max=32, message="用户真实姓名长度必须介于 0 和 32 之间")
	@ExcelField(title="姓名",align=2,sort=0,groups={1,2})
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=11, message="用户手机号长度必须介于 0 和 11 之间")
	@ExcelField(title="手机号",align=2,sort=10,groups={1,2})
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=64, message="加息券ID长度必须介于 0 和 64 之间")
	public String getIrcouponid() {
		return ircouponid;
	}

	public void setIrcouponid(String ircouponid) {
		this.ircouponid = ircouponid;
	}
	
	@Length(min=0, max=64, message="活动ID长度必须介于 0 和 64 之间")
	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	
	@Length(min=0, max=255, message="出借项目名称长度必须介于 0 和 255 之间")
	@ExcelField(title="出借项目",align=2,sort=30,groups=1)
	public String getInvestname() {
		return investname;
	}

	public void setInvestname(String investname) {
		this.investname = investname;
	}
	
	@Length(min=0, max=1, message="出借类型（1普享标 2 出借计划 3其他）长度必须介于 0 和 1 之间")
	@ExcelField(title="项目类型",align=2,sort=40,groups=1)
	public String getInvesttype() {
		return investtype;
	}

	public void setInvesttype(String investtype) {
		this.investtype = investtype;
	}
	@ExcelField(title="出借金额",align=2,sort=50,groups=1)
	public String getInvestmoney() {
		return investmoney;
	}

	public void setInvestmoney(String investmoney) {
		this.investmoney = investmoney;
	}
	
	@Length(min=0, max=1, message="发放状态（1已发 2待发 3失败）长度必须介于 0 和 1 之间")
	@ExcelField(title="发放状态",align=2,sort=90,groups=2)
	public String getSendstatus() {
		return sendstatus;
	}

	public void setSendstatus(String sendstatus) {
		this.sendstatus = sendstatus;
	}
	
	@Length(min=0, max=1, message="发放渠道（1web、2安卓、3苹果、4微信、5其它）长度必须介于 0 和 1 之间")
	@ExcelField(title="发放渠道",align=2,sort=80,groups=2)
	public String getSourcechannel() {
		return sourcechannel;
	}

	public void setSourcechannel(String sourcechannel) {
		this.sourcechannel = sourcechannel;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="发放时间",align=2,sort=100,groups=2)
	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	
	@Length(min=0, max=1, message="加息券使用状态(0已结束1收益中3未使用)长度必须介于 0 和 1 之间")
	@ExcelField(title="使用状态",align=2,sort=110,groups=1)
	public String getUesdstatus() {
		return uesdstatus;
	}

	public void setUesdstatus(String uesdstatus) {
		this.uesdstatus = uesdstatus;
	}
	
	@Length(min=0, max=1, message="消费渠道（1web、2安卓、3苹果、4微信、5其它）长度必须介于 0 和 1 之间")
	@ExcelField(title="消费渠道",align=2,sort=90,groups=1)
	public String getConsumchannel() {
		return consumchannel;
	}

	public void setConsumchannel(String consumchannel) {
		this.consumchannel = consumchannel;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="使用时间",align=2,sort=100,groups=1)
	public Date getUseddate() {
		return useddate;
	}

	public void setUseddate(Date useddate) {
		this.useddate = useddate;
	}
	@ExcelField(title="收益",align=2,sort=80,groups=1)
	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
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
		
}