package com.hzwealth.sms.modules.finance.entity;

import java.util.Date;

import com.hzwealth.sms.common.persistence.DataEntity;

public class BorrowManagementVo extends DataEntity<BorrowManagementVo>{

	private static final long serialVersionUID = 1L;
	private String loanNumber; //借款编号
	private String borrowcode;//散标编号
	private String deadline;//出借期限
	private String payMethod;//还款方式
	private String borrowamount;//借款金额
	private String anualrate;//借款利率
	private String residueAmount;//剩余金额
	private Date loantime;//批贷时间
	private Date openborrowdate;//发布时间
	private String borrowtype;//借款类型
	private String borrowstatus;//状态
	private String scale;		// 募集比例
	private String hasinvestamount;		// 已投总额
	
	//查询
	private Date beginOpenborrowdate;		// 开始 发布时间
	private Date endOpenborrowdate;		// 结束 开标时间
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getBorrowcode() {
		return borrowcode;
	}
	public void setBorrowcode(String borrowcode) {
		this.borrowcode = borrowcode;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getBorrowamount() {
		return borrowamount;
	}
	public void setBorrowamount(String borrowamount) {
		this.borrowamount = borrowamount;
	}
	public String getAnualrate() {
		return anualrate;
	}
	public void setAnualrate(String anualrate) {
		this.anualrate = anualrate;
	}
	public String getResidueAmount() {
		return residueAmount;
	}
	public void setResidueAmount(String residueAmount) {
		this.residueAmount = residueAmount;
	}
	public Date getLoantime() {
		return loantime;
	}
	public void setLoantime(Date loantime) {
		this.loantime = loantime;
	}
	public Date getOpenborrowdate() {
		return openborrowdate;
	}
	public void setOpenborrowdate(Date openborrowdate) {
		this.openborrowdate = openborrowdate;
	}
	
	public String getBorrowstatus() {
		return borrowstatus;
	}
	public void setBorrowstatus(String borrowstatus) {
		this.borrowstatus = borrowstatus;
	}
	public String getBorrowtype() {
		return borrowtype;
	}
	public void setBorrowtype(String borrowtype) {
		this.borrowtype = borrowtype;
	}
	public Date getBeginOpenborrowdate() {
		return beginOpenborrowdate;
	}
	public void setBeginOpenborrowdate(Date beginOpenborrowdate) {
		this.beginOpenborrowdate = beginOpenborrowdate;
	}
	public Date getEndOpenborrowdate() {
		return endOpenborrowdate;
	}
	public void setEndOpenborrowdate(Date endOpenborrowdate) {
		this.endOpenborrowdate = endOpenborrowdate;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getHasinvestamount() {
		return hasinvestamount;
	}
	public void setHasinvestamount(String hasinvestamount) {
		this.hasinvestamount = hasinvestamount;
	}
	
}
