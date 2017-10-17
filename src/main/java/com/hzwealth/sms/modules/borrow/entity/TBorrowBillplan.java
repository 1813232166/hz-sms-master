/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 还款计划Entity
 * @author 秦桂晶
 * @version 2016-10-11
 */
public class TBorrowBillplan extends DataEntity<TBorrowBillplan> {
	
	private static final long serialVersionUID = 1L;
	private String applyId;		// 借款编号
	private String period;		// 期数
	private String monthlyPaymentOrigin;		// 应还月还款额
	private String monthlyPaymentActual;		// 实还月还款额
	private String manageFee;		// 月管理费
	private String failsChargeOrigin;		// 应还违约金
	private String failsChargeActual;		// 实还违约金
	private String lateChargeOrigin;		// 应还罚息
	private String lateChargeActual;		// 实还罚息
	private String repayStatus;		// 1:未还款，2：已还款，6：风险金垫付，7：风险金补偿,8：预约撤销，9：提前还款完成，10：提前还款预约中
	private Date repayTime;		// 还款时间
	private String planRepayAmount;		// 计划还款额
	private String monthCapital;		// 月本金
	private String monthInterest;		// 月利息
	private String contractId;		// 合同编号
	private String noallotamount;		// 未分配金额
	private String remainsPrincipal;		// 剩余本金
	private String prepaymentAmount;		// 一次性还款金额
	private String prepaymentFailscharge;		// 提前还款违约金
	private String rollOutId;		// 旧数据转出的投资编号
	private String isOverdue;		// 0：未曾逾期，1：曾逾期
	private String isAdvances;      // 0:未曾垫付，1:垫付处理中（等待垫付），2:垫付处理中（等待明细提交），3:垫付处理中（等待明细查询），4:已经垫付
	private String isOffset;      // 0:未曾冲抵，1:冲抵处理中，2:已经冲抵
	private Date latePaymentTime;		// 逾期还款时间
	private String parkingFee;		// 停车费
	
	public TBorrowBillplan() {
		super();
	}

	public TBorrowBillplan(String id){
		super(id);
	}

	@Length(min=1, max=30, message="借款编号长度必须介于 1 和 30 之间")
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@Length(min=1, max=4, message="期数长度必须介于 1 和 4 之间")
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String getMonthlyPaymentOrigin() {
		return monthlyPaymentOrigin;
	}

	public void setMonthlyPaymentOrigin(String monthlyPaymentOrigin) {
		this.monthlyPaymentOrigin = monthlyPaymentOrigin;
	}
	
	public String getMonthlyPaymentActual() {
		return monthlyPaymentActual;
	}

	public void setMonthlyPaymentActual(String monthlyPaymentActual) {
		this.monthlyPaymentActual = monthlyPaymentActual;
	}
	
	public String getManageFee() {
		return manageFee;
	}

	public void setManageFee(String manageFee) {
		this.manageFee = manageFee;
	}
	
	public String getFailsChargeOrigin() {
		return failsChargeOrigin;
	}

	public void setFailsChargeOrigin(String failsChargeOrigin) {
		this.failsChargeOrigin = failsChargeOrigin;
	}
	
	public String getFailsChargeActual() {
		return failsChargeActual;
	}

	public void setFailsChargeActual(String failsChargeActual) {
		this.failsChargeActual = failsChargeActual;
	}
	
	public String getLateChargeOrigin() {
		return lateChargeOrigin;
	}

	public void setLateChargeOrigin(String lateChargeOrigin) {
		this.lateChargeOrigin = lateChargeOrigin;
	}
	
	public String getLateChargeActual() {
		return lateChargeActual;
	}

	public void setLateChargeActual(String lateChargeActual) {
		this.lateChargeActual = lateChargeActual;
	}
	
	@Length(min=1, max=5, message="1:未还款，2：已还款，6：风险金垫付，7：风险金补偿,8：预约撤销，9：提前还款完成，10：提前还款预约中长度必须介于 1 和 5 之间")
	public String getRepayStatus() {
		return repayStatus;
	}

	public void setRepayStatus(String repayStatus) {
		this.repayStatus = repayStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="还款时间不能为空")
	public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}
	
	public String getPlanRepayAmount() {
		return planRepayAmount;
	}

	public void setPlanRepayAmount(String planRepayAmount) {
		this.planRepayAmount = planRepayAmount;
	}
	
	public String getMonthCapital() {
		return monthCapital;
	}

	public void setMonthCapital(String monthCapital) {
		this.monthCapital = monthCapital;
	}
	
	public String getMonthInterest() {
		return monthInterest;
	}

	public void setMonthInterest(String monthInterest) {
		this.monthInterest = monthInterest;
	}
	
	@Length(min=1, max=36, message="合同编号长度必须介于 1 和 36 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	public String getNoallotamount() {
		return noallotamount;
	}

	public void setNoallotamount(String noallotamount) {
		this.noallotamount = noallotamount;
	}
	
	public String getRemainsPrincipal() {
		return remainsPrincipal;
	}

	public void setRemainsPrincipal(String remainsPrincipal) {
		this.remainsPrincipal = remainsPrincipal;
	}
	
	public String getPrepaymentAmount() {
		return prepaymentAmount;
	}

	public void setPrepaymentAmount(String prepaymentAmount) {
		this.prepaymentAmount = prepaymentAmount;
	}
	
	public String getPrepaymentFailscharge() {
		return prepaymentFailscharge;
	}

	public void setPrepaymentFailscharge(String prepaymentFailscharge) {
		this.prepaymentFailscharge = prepaymentFailscharge;
	}
	
	@Length(min=1, max=17, message="旧数据转出的投资编号长度必须介于 1 和 17 之间")
	public String getRollOutId() {
		return rollOutId;
	}

	public void setRollOutId(String rollOutId) {
		this.rollOutId = rollOutId;
	}
	
	@Length(min=1, max=10, message="0：未曾逾期，1：曾逾期长度必须介于 1 和 10 之间")
	public String getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}
	
	public String getIsAdvances() {
        return isAdvances;
    }

    public void setIsAdvances(String isAdvances) {
        this.isAdvances = isAdvances;
    }

    public String getIsOffset() {
        return isOffset;
    }

    public void setIsOffset(String isOffset) {
        this.isOffset = isOffset;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLatePaymentTime() {
		return latePaymentTime;
	}

	public void setLatePaymentTime(Date latePaymentTime) {
		this.latePaymentTime = latePaymentTime;
	}
	
	public String getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(String parkingFee) {
		this.parkingFee = parkingFee;
	}
	
}