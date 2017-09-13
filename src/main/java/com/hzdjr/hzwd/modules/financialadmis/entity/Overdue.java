package com.hzdjr.hzwd.modules.financialadmis.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
/**
 * 标的逾期
 * @author xq
 *
 */
public class Overdue extends DataEntity<Overdue>{
    private static final long serialVersionUID = 1L;
    
    private String  borrowId;   //标的唯一Id
    private Integer period;    //期数
    private String borrowaliasno; // 标的中文别名编号
    private String borrowalias; // 标的中文别名
    private String borrowcode; // 标的编号
    private String applyId; // 借款编号
    private String userName;// 借款人
    private String userid; 
    private String mobile; //借款人手机号
    private String  idcardno;    //借款人身份证号
    private String borrowamount; // 借款总额
    private String monthcapital; // 月本金
    private String monthinterest; // 月利息
    
    private BigDecimal monthlyPaymentOrigin;  // 应还月还款额  monthly_payment_origin
    private BigDecimal monthlyPaymentActual;   // 实还月还款额     monthly_payment_actual
    private BigDecimal manageFee;   //月管理费      manage_fee 
    private BigDecimal  failsChargeOrigin;   //应还违约金    fails_charge_origin
    private BigDecimal failsChargeActual;   //实还违约金 fails_charge_actual
    private BigDecimal lateChargeOrigin;   //应还罚息   late_charge_origin
    private BigDecimal lateChargeActual;   //实还罚息   late_charge_actual
    private String repayStatus;    //1:未还款，2：已还款，6：风险金垫付，7：风险金补偿,8：预约撤销，9：提前还款完成，10：提前还款预约中', repay_status
    private Date repayTime;    //本期还款时间  repay_time
    private BigDecimal planRepayAmount;   //计划还款额   plan_repay_amount
    
    private String contractId;      //合同编号  contract_id
    private BigDecimal noAllotAmount;   //未分配金额
    private BigDecimal remainsPrincipal;   //剩余本金   remains_principal
    private BigDecimal prepaymentAmount;   //一次性还款金额    prepayment_amount
    private BigDecimal prepaymentFailsCharge;  //提前还款违约金    prepayment_FailsCharge
    private String rollOutId;    //旧数据转出的投资编号   roll_out_id
    private String isOverdue;    //0：未曾逾期，1：曾逾期',   is_overdue
    private Date latePaymentTime;   //逾期还款时间    late_payment_time
    private BigDecimal parkingFee;   //停车费  parking_fee
    private String isAdvances;  //是否垫付
    private String isOffset;    //是否冲抵
    private String preTransactionNo; //预处理请求流水号
    private BigDecimal amount;//    总额：月本金+月利息+罚息+违约金
    private BigDecimal advancesSum;//    垫付金额
    private Date advancesTime;//    垫付时间
    private BigDecimal offsetSum; //    冲抵金额
    private Date offsetTime;//    冲抵时间
    private Integer overdueDay;//    逾期天数   
    private String startAdvancesTime;
    private String endAdvancesTime;
    private String startOffsetTime;
    private String endOffsetTime;


    
    public String getBorrowId() {
        return borrowId;
    }



    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }



    public Integer getPeriod() {
        return period;
    }



    public void setPeriod(Integer period) {
        this.period = period;
    }



    public String getBorrowaliasno() {
        return borrowaliasno;
    }



    public void setBorrowaliasno(String borrowaliasno) {
        this.borrowaliasno = borrowaliasno;
    }



    public String getBorrowalias() {
        return borrowalias;
    }



    public void setBorrowalias(String borrowalias) {
        this.borrowalias = borrowalias;
    }



    public String getBorrowcode() {
        return borrowcode;
    }



    public void setBorrowcode(String borrowcode) {
        this.borrowcode = borrowcode;
    }



    public String getApplyId() {
        return applyId;
    }



    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }



    public String getUserName() {
        return userName;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getUserid() {
        return userid;
    }



    public void setUserid(String userid) {
        this.userid = userid;
    }



    public String getMobile() {
        return mobile;
    }



    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getIdcardno() {
        return idcardno;
    }



    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }



    public String getBorrowamount() {
        return borrowamount;
    }



    public void setBorrowamount(String borrowamount) {
        this.borrowamount = borrowamount;
    }



    public String getMonthcapital() {
        return monthcapital;
    }



    public void setMonthcapital(String monthcapital) {
        this.monthcapital = monthcapital;
    }



    public String getMonthinterest() {
        return monthinterest;
    }



    public void setMonthinterest(String monthinterest) {
        this.monthinterest = monthinterest;
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



    public String getPreTransactionNo() {
        return preTransactionNo;
    }



    public void setPreTransactionNo(String preTransactionNo) {
        this.preTransactionNo = preTransactionNo;
    }



    public BigDecimal getAmount() {
        return amount;
    }



    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    public BigDecimal getAdvancesSum() {
        return advancesSum;
    }



    public void setAdvancesSum(BigDecimal advancesSum) {
        this.advancesSum = advancesSum;
    }



    public Date getAdvancesTime() {
        return advancesTime;
    }



    public void setAdvancesTime(Date advancesTime) {
        this.advancesTime = advancesTime;
    }



    public BigDecimal getOffsetSum() {
        return offsetSum;
    }



    public void setOffsetSum(BigDecimal offsetSum) {
        this.offsetSum = offsetSum;
    }



    public Date getOffsetTime() {
        return offsetTime;
    }



    public void setOffsetTime(Date offsetTime) {
        this.offsetTime = offsetTime;
    }



    public Integer getOverdueDay() {
        return overdueDay;
    }



    public void setOverdueDay(Integer overdueDay) {
        this.overdueDay = overdueDay;
    }



    public String getStartAdvancesTime() {
        return startAdvancesTime;
    }



    public void setStartAdvancesTime(String startAdvancesTime) {
        this.startAdvancesTime = startAdvancesTime;
    }



    public String getEndAdvancesTime() {
        return endAdvancesTime;
    }



    public void setEndAdvancesTime(String endAdvancesTime) {
        this.endAdvancesTime = endAdvancesTime;
    }



    public String getStartOffsetTime() {
        return startOffsetTime;
    }



    public void setStartOffsetTime(String startOffsetTime) {
        this.startOffsetTime = startOffsetTime;
    }



    public String getEndOffsetTime() {
        return endOffsetTime;
    }



    public void setEndOffsetTime(String endOffsetTime) {
        this.endOffsetTime = endOffsetTime;
    }



    @Override
    public String toString() {
        return "Overdue [id=" + id + ", period=" + period + ", borrowaliasno=" + borrowaliasno + ", borrowalias="
                + borrowalias + ", borrowcode=" + borrowcode + ", applyId=" + applyId + ", userName=" + userName
                + ", userid=" + userid + ", mobile=" + mobile + ", borrowamount=" + borrowamount + ", monthcapital="
                + monthcapital + ", monthinterest=" + monthinterest + ", monthlyPaymentOrigin=" + monthlyPaymentOrigin
                + ", monthlyPaymentActual=" + monthlyPaymentActual + ", manageFee=" + manageFee
                + ", failsChargeOrigin=" + failsChargeOrigin + ", failsChargeActual=" + failsChargeActual
                + ", lateChargeOrigin=" + lateChargeOrigin + ", lateChargeActual=" + lateChargeActual
                + ", repayStatus=" + repayStatus + ", repayTime=" + repayTime + ", planRepayAmount=" + planRepayAmount
                + ", contractId=" + contractId + ", noAllotAmount=" + noAllotAmount + ", remainsPrincipal="
                + remainsPrincipal + ", prepaymentAmount=" + prepaymentAmount + ", prepaymentFailsCharge="
                + prepaymentFailsCharge + ", rollOutId=" + rollOutId + ", isOverdue=" + isOverdue
                + ", latePaymentTime=" + latePaymentTime + ", parkingFee=" + parkingFee + ", isAdvances=" + isAdvances
                + ", isOffset=" + isOffset + ", preTransactionNo=" + preTransactionNo + ", amount=" + amount
                + ", advancesSum=" + advancesSum + ", advancesTime=" + advancesTime + ", offsetSum=" + offsetSum
                + ", offsetTime=" + offsetTime + ", overdueDay=" + overdueDay + "]";
    }
    
    
   
}
