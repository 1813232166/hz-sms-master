package com.hzdjr.hzwd.modules.moneystatistics.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeStatistics {

	private String id;
	private Date rechargeTime;
	private BigDecimal totalAmount;
	private Integer count;
	private Integer userCount;
	private Integer newUserCount;
	private BigDecimal pc;
	private BigDecimal app;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRechargeTime() {
		return rechargeTime;
	}
	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public Integer getNewUserCount() {
		return newUserCount;
	}
	public void setNewUserCount(Integer newUserCount) {
		this.newUserCount = newUserCount;
	}
	public BigDecimal getPc() {
		return pc;
	}
	public void setPc(BigDecimal pc) {
		this.pc = pc;
	}
	public BigDecimal getApp() {
		return app;
	}
	public void setApp(BigDecimal app) {
		this.app = app;
	}
	
	
}
