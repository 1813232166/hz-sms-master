package com.hzwealth.sms.modules.repaymentmanage.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *Title:InvestBillPlan
 *Description:  t_invest_billPalan
 *@author:黄亚浩
 *@date:2016年10月24日 下午4:05:11
 */
public class InvestBillPlan {

	private String investId;   //投资编号
	private BigDecimal billNum;   //投资期次序号
	private Integer capiYear;  //利息年份
	private Integer capiMonth;    //利息月份
	private Date sDate;   //应收款日期
	private BigDecimal stcapi;   //应收本金
	private BigDecimal sinte;  //应收利息
	private BigDecimal bal;  //贷款余额
	private Date rDate;    //实收款日期
	private BigDecimal rtcapi;  //实收本金
	private BigDecimal rinte;  //实收利息
	private char overflag;  //是否到期未收到
	private char payoffFlag;  //该期次是否已收清
	
	
	//自定义变量
	private BigDecimal count;  //还款金额
	private BigDecimal sumCount;   //还款总金额
	public String getInvestId() {
		return investId;
	}
	public void setInvestId(String investId) {
		this.investId = investId;
	}
	public BigDecimal getBillNum() {
		return billNum;
	}
	public void setBillNum(BigDecimal billNum) {
		this.billNum = billNum;
	}
	public Integer getCapiYear() {
		return capiYear;
	}
	public void setCapiYear(Integer capiYear) {
		this.capiYear = capiYear;
	}
	public Integer getCapiMonth() {
		return capiMonth;
	}
	public void setCapiMonth(Integer capiMonth) {
		this.capiMonth = capiMonth;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
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
	public BigDecimal getBal() {
		return bal;
	}
	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
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
	public char getOverflag() {
		return overflag;
	}
	public void setOverflag(char overflag) {
		this.overflag = overflag;
	}
	public char getPayoffFlag() {
		return payoffFlag;
	}
	public void setPayoffFlag(char payoffFlag) {
		this.payoffFlag = payoffFlag;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	public BigDecimal getSumCount() {
		return sumCount;
	}
	public void setSumCount(BigDecimal sumCount) {
		this.sumCount = sumCount;
	}
	
	
	
	
	
}
