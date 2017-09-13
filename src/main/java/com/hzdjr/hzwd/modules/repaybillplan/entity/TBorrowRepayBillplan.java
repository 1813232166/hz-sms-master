/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.repaybillplan.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 回款管理列表Entity
 * @author yansy
 * @version 2016-10-12
 */
public class TBorrowRepayBillplan extends DataEntity<TBorrowRepayBillplan> {
	
	private static final long serialVersionUID = 1L;
	private String investid;		// 投资编号
	private String billnum;		// 投资期次序号
	private String capiyear;		// 利息年份
	private String capimonth;		// 利息月份
	private Date sdate;		// 应收款日期
	private String stcapi;		// 应收本金
	private String sinte;		// 应收利息
	private String bal;		// 贷款余额
	private Date rdate;		// 实收款日期
	private String rtcapi;		// 实收本金
	private String rinte;		// 实收利息
	private String overflag;		// 是否到期未收到
	private String payoffflag;		// 该期次是否已收清
	private BigDecimal sAmount;		// 应收总金额
	
	public TBorrowRepayBillplan() {
		super();
	}

	public TBorrowRepayBillplan(String id){
		super(id);
	}

	@Length(min=1, max=32, message="投资编号长度必须介于 1 和 32 之间")
	public String getInvestid() {
		return investid;
	}

	public void setInvestid(String investid) {
		this.investid = investid;
	}
	
	public String getBillnum() {
		return billnum;
	}

	public void setBillnum(String billnum) {
		this.billnum = billnum;
	}
	
	@Length(min=0, max=4, message="利息年份长度必须介于 0 和 4 之间")
	public String getCapiyear() {
		return capiyear;
	}

	public void setCapiyear(String capiyear) {
		this.capiyear = capiyear;
	}
	
	@Length(min=0, max=11, message="利息月份长度必须介于 0 和 11 之间")
	public String getCapimonth() {
		return capimonth;
	}

	public void setCapimonth(String capimonth) {
		this.capimonth = capimonth;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	
	public String getStcapi() {
		return stcapi;
	}

	public void setStcapi(String stcapi) {
		this.stcapi = stcapi;
	}
	
	public String getSinte() {
		return sinte;
	}

	public void setSinte(String sinte) {
		this.sinte = sinte;
	}
	
	public String getBal() {
		return bal;
	}

	public void setBal(String bal) {
		this.bal = bal;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	public String getRtcapi() {
		return rtcapi;
	}

	public void setRtcapi(String rtcapi) {
		this.rtcapi = rtcapi;
	}
	
	public String getRinte() {
		return rinte;
	}

	public void setRinte(String rinte) {
		this.rinte = rinte;
	}
	
	@Length(min=0, max=1, message="是否到期未收到长度必须介于 0 和 1 之间")
	public String getOverflag() {
		return overflag;
	}

	public void setOverflag(String overflag) {
		this.overflag = overflag;
	}
	
	@Length(min=0, max=2, message="该期次是否已收清长度必须介于 0 和 2 之间")
	public String getPayoffflag() {
		return payoffflag;
	}

	public void setPayoffflag(String payoffflag) {
		this.payoffflag = payoffflag;
	}

	public BigDecimal getsAmount() {
		return sAmount;
	}

	public void setsAmount(BigDecimal sAmount) {
		this.sAmount = sAmount;
	}
	
}