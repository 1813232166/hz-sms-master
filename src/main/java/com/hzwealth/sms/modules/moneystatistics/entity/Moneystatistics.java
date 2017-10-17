package com.hzwealth.sms.modules.moneystatistics.entity;

import java.math.BigDecimal;

public class Moneystatistics {
	
	
	private String id;//序号
	private String phone;//用户手机号
	private String userType;//用户类型
	private BigDecimal totalAmount;//总额
	private BigDecimal balance;//可用余额
	private BigDecimal frozenCapital;//冻结资金
	private BigDecimal collectingAmount;//代收金额
	private BigDecimal payTotal;//充值总数
	private BigDecimal withdrawTotal;//提现总数
	private BigDecimal totalInvestment;//投资总额
	private BigDecimal incomeReceived;//已收收益
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getFrozenCapital() {
		return frozenCapital;
	}
	public void setFrozenCapital(BigDecimal frozenCapital) {
		this.frozenCapital = frozenCapital;
	}
	public BigDecimal getCollectingAmount() {
		return collectingAmount;
	}
	public void setCollectingAmount(BigDecimal collectingAmount) {
		this.collectingAmount = collectingAmount;
	}
	public BigDecimal getPayTotal() {
		return payTotal;
	}
	public void setPayTotal(BigDecimal payTotal) {
		this.payTotal = payTotal;
	}
	public BigDecimal getWithdrawTotal() {
		return withdrawTotal;
	}
	public void setWithdrawTotal(BigDecimal withdrawTotal) {
		this.withdrawTotal = withdrawTotal;
	}
	public BigDecimal getTotalInvestment() {
		return totalInvestment;
	}
	public void setTotalInvestment(BigDecimal totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	public BigDecimal getIncomeReceived() {
		return incomeReceived;
	}
	public void setIncomeReceived(BigDecimal incomeReceived) {
		this.incomeReceived = incomeReceived;
	}
	
	
	
}
