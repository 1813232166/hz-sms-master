package com.hzdjr.hzwd.modules.repaymentmanage.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.BaseEntity;

/**
 * 逾期业务对象
 * @author xuchenglin
 * @since 2017-04
 */
public class OverdueDTO extends BaseEntity<OverdueDTO> {

  private static final long serialVersionUID = 1L;

  private String borrowId; // 借款ID
  private String repaymentId; // 还款ID
  private int period; //期数
  private String borrowCode;// 标的编号
  private String loanNumber;// 借款编号
  private String name; // 借款人
  private String mobile; // 借款人手机号
  private BigDecimal borrowAmount; // 借款金额
  private BigDecimal monthCapital;// 本金
  private BigDecimal monthInterest;// 利息
  private BigDecimal lateChargeOrigin; //应还罚息
  //private BigDecimal lateChargeActual;// 罚息
  private BigDecimal failsChargeOrigin;// 应还违约金
 // private BigDecimal failsChargeActual;// 违约金
  private BigDecimal advancesAmount; // 垫付金额
  private Date advancesTime; // 垫付时间
  private BigDecimal offsetAmount; // 冲抵金额
  private Date offsetTime; // 冲抵时间
  private Date repaymentDate; // 还款时间
  private int overdueDay; // 逾期天数
  private String advanceStatus; //垫付状态
  
  public String getBorrowId() {
    return borrowId;
  }

  public void setBorrowId(String borrowId) {
    this.borrowId = borrowId;
  }

  public String getRepaymentId() {
    return repaymentId;
  }

  public void setRepaymentId(String repaymentId) {
    this.repaymentId = repaymentId;
  }

  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public String getBorrowCode() {
    return borrowCode;
  }

  public void setBorrowCode(String borrowCode) {
    this.borrowCode = borrowCode;
  }

  public String getLoanNumber() {
    return loanNumber;
  }

  public void setLoanNumber(String loanNumber) {
    this.loanNumber = loanNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public BigDecimal getBorrowAmount() {
    return borrowAmount;
  }

  public void setBorrowAmount(BigDecimal borrowAmount) {
    this.borrowAmount = borrowAmount;
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

  public BigDecimal getLateChargeOrigin() {
    return lateChargeOrigin;
  }

  public void setLateChargeOrigin(BigDecimal lateChargeOrigin) {
    this.lateChargeOrigin = lateChargeOrigin;
  }

  public BigDecimal getFailsChargeOrigin() {
    return failsChargeOrigin;
  }

  public void setFailsChargeOrigin(BigDecimal failsChargeOrigin) {
    this.failsChargeOrigin = failsChargeOrigin;
  }

  public Date getAdvancesTime() {
    return advancesTime;
  }

  public void setAdvancesTime(Date advancesTime) {
    this.advancesTime = advancesTime;
  }

  public Date getOffsetTime() {
    return offsetTime;
  }

  public void setOffsetTime(Date offsetTime) {
    this.offsetTime = offsetTime;
  }

  public Date getRepaymentDate() {
    return repaymentDate;
  }

  public void setRepaymentDate(Date repaymentDate) {
    this.repaymentDate = repaymentDate;
  }

  public int getOverdueDay() {
    return overdueDay;
  }

  public void setOverdueDay(int overdueDay) {
    this.overdueDay = overdueDay;
  }

  public BigDecimal getAdvancesAmount() {
    return advancesAmount;
  }

  public void setAdvancesAmount(BigDecimal advancesAmount) {
    this.advancesAmount = advancesAmount;
  }

  public BigDecimal getOffsetAmount() {
    return offsetAmount;
  }

  public void setOffsetAmount(BigDecimal offsetAmount) {
    this.offsetAmount = offsetAmount;
  }

  public String getAdvanceStatus() {
    return advanceStatus;
  }

  public void setAdvanceStatus(String advanceStatus) {
    this.advanceStatus = advanceStatus;
  }

  @Override
  public void preInsert() {
    //暂不使用
  }

  @Override
  public void preUpdate() {
    //暂不使用
  }

}
