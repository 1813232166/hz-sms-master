/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 全部交易单Entity
 * @author XJin
 * @version 2017-07-03
 */
public class FinanceTransactionS extends DataEntity<FinanceTransactionS> {
	
	private static final long serialVersionUID = 1L;
	
	private String mobile;
	private String name;
	private String transactionNumber;
	private Date investTime;
	private BigDecimal investAmount;
	private String productType;
	private String status;
	private String borrowCode;
	private Date investTimeBegin;
	private Date investTimeEnd;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public BigDecimal getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBorrowCode() {
		return borrowCode;
	}
	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}
	public Date getInvestTimeBegin() {
		return investTimeBegin;
	}
	public void setInvestTimeBegin(Date investTimeBegin) {
		this.investTimeBegin = investTimeBegin;
	}
	public Date getInvestTimeEnd() {
		return investTimeEnd;
	}
	public void setInvestTimeEnd(Date investTimeEnd) {
		this.investTimeEnd = investTimeEnd;
	}
	
	
}