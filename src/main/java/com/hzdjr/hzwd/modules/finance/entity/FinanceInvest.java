package com.hzdjr.hzwd.modules.finance.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinanceInvest implements Serializable {
	
	private static final long serialVersionUID = -1453636397112114496L;
	
	private String investId;		// 投资id
	private String loanNumber;		// 借款编号
	private String investCode;		// 交易单号
	private Date investTime;		// 出借时间
	private Date matchedTime;		// 匹配成功时间
	private BigDecimal investAmount;// 出借金额（元）
	private String investStatus;	// 复投状态 -1投资失败 0投资成功 1申请债转 2债转成功 3债转失败
	private Date interestDate;		// 起息日
	
	public String getInvestId() {
		return investId;
	}
	public void setInvestId(String investId) {
		this.investId = investId;
	}
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getInvestCode() {
		return investCode;
	}
	public void setInvestCode(String investCode) {
		this.investCode = investCode;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public Date getMatchedTime() {
		return matchedTime;
	}
	public void setMatchedTime(Date matchedTime) {
		this.matchedTime = matchedTime;
	}
	public BigDecimal getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	public String getInvestStatus() {
		return investStatus;
	}
	public void setInvestStatus(String investStatus) {
		this.investStatus = investStatus;
	}
	public Date getInterestDate() {
		return interestDate;
	}
	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}
	
	
}
