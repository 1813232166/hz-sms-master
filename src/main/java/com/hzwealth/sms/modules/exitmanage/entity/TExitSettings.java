/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.exitmanage.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;

/**
 * 退出管理Entity
 * @author HDG
 * @version 2017-07-21
 */
public class TExitSettings extends DataEntity<TExitSettings> {
	
	private static final long serialVersionUID = 1L;
	private String expireAmount;		// 明天到期金额（元）
	private String notExitedAmount;		// 到期未退出金额（元）
	private String canQuitAmount;		// 今日可退出金额（元）
	private String quitAmount;		// 今日已退出金额（元）
	private Date createTime;		// 创建时间
	private Date releaseTime;		// 发布时间
	private String status;		// 状态(1:未发布，2:待审核，3:审核未通过,3:进行中)
	private String detail;		// 备注
	
	//查询条件
	private Date beginTimes;
	private Date endTimes;
	public TExitSettings() {
		super();
	}

	public TExitSettings(String id){
		super(id);
	}

	public String getExpireAmount() {
		return expireAmount;
	}

	public void setExpireAmount(String expireAmount) {
		this.expireAmount = expireAmount;
	}
	
	public String getNotExitedAmount() {
		return notExitedAmount;
	}

	public void setNotExitedAmount(String notExitedAmount) {
		this.notExitedAmount = notExitedAmount;
	}
	
	public String getCanQuitAmount() {
		return canQuitAmount;
	}

	public void setCanQuitAmount(String canQuitAmount) {
		this.canQuitAmount = canQuitAmount;
	}
	
	public String getQuitAmount() {
		return quitAmount;
	}

	public void setQuitAmount(String quitAmount) {
		this.quitAmount = quitAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@Length(min=0, max=2, message="状态(1:未发布，2:待审核，3:审核未通过,3:进行中)长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getBeginTimes() {
		return beginTimes;
	}

	public void setBeginTimes(Date beginTimes) {
		this.beginTimes = beginTimes;
	}

	public Date getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(Date endTimes) {
		this.endTimes = endTimes;
	}
	
}