package com.hzdjr.hzwd.modules.repaymentmanage.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *Title:BorrowBillPlan
 *Description: t_borrowBillPan
 *@author:黄亚浩
 *@date:2016年10月24日 下午4:11:37
 */
public class BorrowBillPlan {

	private Integer id;
	private Integer applyId;  //apply_id  借款编号
	private Integer period;    //期数
	private BigDecimal monthlyPaymentOrigin;  // 应还月还款额	 monthly_payment_origin
	private BigDecimal monthlyPaymentActual;   // 实还月还款额  	 monthly_payment_actual
	private BigDecimal manageFee;   //月管理费		manage_fee 
	private BigDecimal  failsChargeOrigin;   //应还违约金	fails_charge_origin
	private BigDecimal failsChargeActual;   //实还违约金	fails_charge_actual
	private BigDecimal lateChargeOrigin;   //应还罚息 	late_charge_origin
	private BigDecimal lateChargeActual;   //实还罚息	late_charge_actual
	private String repayStatus;    //1:未还款，2：已还款，6：风险金垫付，7：风险金补偿,8：预约撤销，9：提前还款完成，10：提前还款预约中', repay_status
	private Date repayTime;    //还款时间  repay_time
	private BigDecimal planRepayAmount;   //计划还款额	plan_repay_amount
	private BigDecimal monthCapital;   //月本金	month_capital
	private BigDecimal monthInterest;   //月利息	month_interest
	private String contractId;      //合同编号	contract_id
	private BigDecimal noAllotAmount;   //未分配金额
	private BigDecimal remainsPrincipal;   //剩余本金	remains_principal
	private BigDecimal prepaymentAmount;   //一次性还款金额	prepayment_amount
	private BigDecimal prepaymentFailsCharge;  //提前还款违约金	prepayment_FailsCharge
	private String rollOutId;    //旧数据转出的投资编号	roll_out_id
	private String isOverdue;    //0：未曾逾期，1：曾逾期',	is_overdue
	private Date latePaymentTime;   //逾期还款时间	late_payment_time
	private BigDecimal parkingFee;   //停车费	parking_fee
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public BigDecimal getMonthlyPaymentOrigin() {
		return monthlyPaymentOrigin;
	}
	public void setMonthlyPaymentOrigin(BigDecimal monthlyPaymentOrigin) {
		this.monthlyPaymentOrigin = monthlyPaymentOrigin;
	}
	public BigDecimal getMonthlyPaymentActual() {
		return monthlyPaymentActual;
	}
	public void setMonthlyPaymentActual(BigDecimal monthlyPaymentActual) {
		this.monthlyPaymentActual = monthlyPaymentActual;
	}
	public BigDecimal getManageFee() {
		return manageFee;
	}
	public void setManageFee(BigDecimal manageFee) {
		this.manageFee = manageFee;
	}
	public BigDecimal getFailsChargeOrigin() {
		return failsChargeOrigin;
	}
	public void setFailsChargeOrigin(BigDecimal failsChargeOrigin) {
		this.failsChargeOrigin = failsChargeOrigin;
	}
	public BigDecimal getFailsChargeActual() {
		return failsChargeActual;
	}
	public void setFailsChargeActual(BigDecimal failsChargeActual) {
		this.failsChargeActual = failsChargeActual;
	}
	public BigDecimal getLateChargeOrigin() {
		return lateChargeOrigin;
	}
	public void setLateChargeOrigin(BigDecimal lateChargeOrigin) {
		this.lateChargeOrigin = lateChargeOrigin;
	}
	public BigDecimal getLateChargeActual() {
		return lateChargeActual;
	}
	public void setLateChargeActual(BigDecimal lateChargeActual) {
		this.lateChargeActual = lateChargeActual;
	}
	public String getRepayStatus() {
		return repayStatus;
	}
	public void setRepayStatus(String repayStatus) {
		this.repayStatus = repayStatus;
	}
	public Date getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}
	public BigDecimal getPlanRepayAmount() {
		return planRepayAmount;
	}
	public void setPlanRepayAmount(BigDecimal planRepayAmount) {
		this.planRepayAmount = planRepayAmount;
	}
	public BigDecimal getMonthCapital() {
		return monthCapital;
	}
	public void setMonthCapital(BigDecimal monthCapital) {
		this.monthCapital = monthCapital;
	}
	public BigDecimal getMonthInterest() {
		return monthInterest;
	}
	public void setMonthInterest(BigDecimal monthInterest) {
		this.monthInterest = monthInterest;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public BigDecimal getNoAllotAmount() {
		return noAllotAmount;
	}
	public void setNoAllotAmount(BigDecimal noAllotAmount) {
		this.noAllotAmount = noAllotAmount;
	}
	public BigDecimal getRemainsPrincipal() {
		return remainsPrincipal;
	}
	public void setRemainsPrincipal(BigDecimal remainsPrincipal) {
		this.remainsPrincipal = remainsPrincipal;
	}
	public BigDecimal getPrepaymentAmount() {
		return prepaymentAmount;
	}
	public void setPrepaymentAmount(BigDecimal prepaymentAmount) {
		this.prepaymentAmount = prepaymentAmount;
	}
	public BigDecimal getPrepaymentFailsCharge() {
		return prepaymentFailsCharge;
	}
	public void setPrepaymentFailsCharge(BigDecimal prepaymentFailsCharge) {
		this.prepaymentFailsCharge = prepaymentFailsCharge;
	}
	public String getRollOutId() {
		return rollOutId;
	}
	public void setRollOutId(String rollOutId) {
		this.rollOutId = rollOutId;
	}
	public String getIsOverdue() {
		return isOverdue;
	}
	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}
	public Date getLatePaymentTime() {
		return latePaymentTime;
	}
	public void setLatePaymentTime(Date latePaymentTime) {
		this.latePaymentTime = latePaymentTime;
	}
	public BigDecimal getParkingFee() {
		return parkingFee;
	}
	public void setParkingFee(BigDecimal parkingFee) {
		this.parkingFee = parkingFee;
	}
	
	
	
	
}
