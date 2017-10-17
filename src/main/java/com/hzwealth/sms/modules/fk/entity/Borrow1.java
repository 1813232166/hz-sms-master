package com.hzwealth.sms.modules.fk.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Borrow1 {
	
	private String borrowId;
	private String deadline; 
	private Date lendingTime;
	private BigDecimal monthPrepaymentAmount;
	private String  loanNumber;
	
	
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public Date getLendingTime() {
		return lendingTime;
	}
	public void setLendingTime(Date lendingTime) {
		this.lendingTime = lendingTime;
	}
	public BigDecimal getMonthPrepaymentAmount() {
		return monthPrepaymentAmount;
	}
	public void setMonthPrepaymentAmount(BigDecimal monthPrepaymentAmount) {
		this.monthPrepaymentAmount = monthPrepaymentAmount;
	}
	
	

}
