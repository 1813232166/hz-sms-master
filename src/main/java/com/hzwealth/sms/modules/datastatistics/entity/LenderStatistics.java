package com.hzwealth.sms.modules.datastatistics.entity;

import java.math.BigDecimal;

public class LenderStatistics {
	
	private Long totalInvest;
	private BigDecimal sumInvestAmount;
	private BigDecimal sumAbleBalanceAmount;
	private Long totalPayoff;
	private BigDecimal sumActualCapitalAmount;
	
	private BigDecimal sumActualInteAmount;
	private Long totalRepaying;
	private BigDecimal sumOughtCapitalAmount;
	private BigDecimal sumOughtInteAmount;
	private Long totalOverdue;
	
	private BigDecimal sumOverdueCapitalAmount;

	public Long getTotalInvest() {
		return totalInvest;
	}

	public void setTotalInvest(Long totalInvest) {
		this.totalInvest = totalInvest;
	}

	public BigDecimal getSumInvestAmount() {
		return sumInvestAmount;
	}

	public void setSumInvestAmount(BigDecimal sumInvestAmount) {
		this.sumInvestAmount = sumInvestAmount;
	}

	public BigDecimal getSumAbleBalanceAmount() {
		return sumAbleBalanceAmount;
	}

	public void setSumAbleBalanceAmount(BigDecimal sumAbleBalanceAmount) {
		this.sumAbleBalanceAmount = sumAbleBalanceAmount;
	}

	public Long getTotalPayoff() {
		return totalPayoff;
	}

	public void setTotalPayoff(Long totalPayoff) {
		this.totalPayoff = totalPayoff;
	}

	public BigDecimal getSumActualCapitalAmount() {
		return sumActualCapitalAmount;
	}

	public void setSumActualCapitalAmount(BigDecimal sumActualCapitalAmount) {
		this.sumActualCapitalAmount = sumActualCapitalAmount;
	}

	public BigDecimal getSumActualInteAmount() {
		return sumActualInteAmount;
	}

	public void setSumActualInteAmount(BigDecimal sumActualInteAmount) {
		this.sumActualInteAmount = sumActualInteAmount;
	}

	public Long getTotalRepaying() {
		return totalRepaying;
	}

	public void setTotalRepaying(Long totalRepaying) {
		this.totalRepaying = totalRepaying;
	}

	public BigDecimal getSumOughtCapitalAmount() {
		return sumOughtCapitalAmount;
	}

	public void setSumOughtCapitalAmount(BigDecimal sumOughtCapitalAmount) {
		this.sumOughtCapitalAmount = sumOughtCapitalAmount;
	}

	public BigDecimal getSumOughtInteAmount() {
		return sumOughtInteAmount;
	}

	public void setSumOughtInteAmount(BigDecimal sumOughtInteAmount) {
		this.sumOughtInteAmount = sumOughtInteAmount;
	}

	public Long getTotalOverdue() {
		return totalOverdue;
	}

	public void setTotalOverdue(Long totalOverdue) {
		this.totalOverdue = totalOverdue;
	}

	public BigDecimal getSumOverdueCapitalAmount() {
		return sumOverdueCapitalAmount;
	}

	public void setSumOverdueCapitalAmount(BigDecimal sumOverdueCapitalAmount) {
		this.sumOverdueCapitalAmount = sumOverdueCapitalAmount;
	}
	
	
	
	
}
