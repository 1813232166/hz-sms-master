package com.hzwealth.sms.modules.borrow.entity;


import java.math.BigDecimal;

import com.hzwealth.sms.common.persistence.DataEntity;

public class RepayPlanVo extends DataEntity<RepayPlanVo>{
	private static final long serialVersionUID = 1L;

	private String sDate; //应收款日期
	private BigDecimal stcapi;//应收本金
	private BigDecimal sinte;//应收利息
	
	private String rDate; 
	private BigDecimal rtcapi;//实收本金
	private BigDecimal  rinte;  //实收利息
	private String realName;
	private BigDecimal amounts;
	
	private String borrowId; // 标的主键
	private String billNum; //借款期数
	
	public RepayPlanVo() {
	}
	public RepayPlanVo(String id) {
		super(id);
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public BigDecimal getStcapi() {
		return stcapi;
	}
	public void setStcapi(BigDecimal stcapi) {
		this.stcapi = stcapi;
	}
	public BigDecimal getSinte() {
		return sinte;
	}
	public void setSinte(BigDecimal sinte) {
		this.sinte = sinte;
	}
	public BigDecimal getRtcapi() {
		return rtcapi;
	}
	public void setRtcapi(BigDecimal rtcapi) {
		this.rtcapi = rtcapi;
	}
	public BigDecimal getRinte() {
		return rinte;
	}
	public void setRinte(BigDecimal rinte) {
		this.rinte = rinte;
	}
	public BigDecimal getAmounts() {
		return amounts;
	}
	public void setAmounts(BigDecimal amounts) {
		this.amounts = amounts;
	}
	
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	
	
	
	
}
