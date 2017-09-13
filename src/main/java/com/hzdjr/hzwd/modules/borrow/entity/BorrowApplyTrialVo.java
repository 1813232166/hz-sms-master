package com.hzdjr.hzwd.modules.borrow.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

/**
 * 
 * @Title: BorrowApplyTrial.java
 * @Package com.hzdjr.hzwd.modules.borrow.entity
 * @Description: 线下进件标的审核
 * @author zhf
 * @date Apr 24, 2017
 * @version 1.0
 */
public class BorrowApplyTrialVo extends DataEntity<BorrowApplyTrialVo> {

	private static final long serialVersionUID = -6489106252867616679L;
	
	private String borrowId;		//标的id
	private String loanNumber;		//借款编号
	private String borrowCode;		//申请编号
	private String realName;		//姓名
	private String mobile;			//手机号
	private String productName;		//产品类型
	private BigDecimal borrowAmount;//借款金额（元）
	private String periods;			//借款期限（期）
	private String yearRate;		//借款年化利率（%）
	private Date createTime;		//线下推送时间
	private String borrowStatus;	//状态
	private String firstPassSuggest;	//初审意见
	private String firstNopassSuggest;	//初审打回原因
	private String reviewPassSuggest;	//终审意见
	private String reviewNopassSuggest;	//终审打回原因
	private String borrowingSources;//来源
	
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	@ExcelField(title="申请编号",type=1,align=2,sort=20)
	public String getBorrowCode() {
		return borrowCode;
	}
	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}
	@ExcelField(title="姓名",type=1,align=2,sort=30)
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	@ExcelField(title="手机号",type=1,align=2,sort=40)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@ExcelField(title="产品类型",type=1,align=2,sort=50)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@ExcelField(title="借款金额（元）",type=1,align=2,sort=60)
	public BigDecimal getBorrowAmount() {
		return borrowAmount;
	}
	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}
	@ExcelField(title="借款期限（期）",type=1,align=2,sort=70)
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	@ExcelField(title="借款年化利率（%）",type=1,align=2,sort=80)
	public String getYearRate() {
		return yearRate;
	}
	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}
	@ExcelField(title="线下推送时间",type=1,align=2,sort=90)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@ExcelField(title="状态",type=1,align=2,sort=100)
	public String getBorrowStatus() {
		return borrowStatus;
	}
	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}
	@ExcelField(title="初审意见",type=1,align=2,sort=110)
	public String getFirstPassSuggest() {
		return firstPassSuggest;
	}
	public void setFirstPassSuggest(String firstPassSuggest) {
		this.firstPassSuggest = firstPassSuggest;
	}
	@ExcelField(title="打回线下原因",type=1,align=2,sort=110)
	public String getFirstNopassSuggest() {
		return firstNopassSuggest;
	}
	public void setFirstNopassSuggest(String firstNopassSuggest) {
		this.firstNopassSuggest = firstNopassSuggest;
	}
	@ExcelField(title="终审意见",type=1,align=2,sort=110)
	public String getReviewPassSuggest() {
		return reviewPassSuggest;
	}
	public void setReviewPassSuggest(String reviewPassSuggest) {
		this.reviewPassSuggest = reviewPassSuggest;
	}
	@ExcelField(title="终审打回原因",type=1,align=2,sort=110)
	public String getReviewNopassSuggest() {
		return reviewNopassSuggest;
	}
	public void setReviewNopassSuggest(String reviewNopassSuggest) {
		this.reviewNopassSuggest = reviewNopassSuggest;
	}
	@ExcelField(title="来源",type=1,align=2,sort=140)
	public String getBorrowingSources() {
		return borrowingSources;
	}
	public void setBorrowingSources(String borrowingSources) {
		this.borrowingSources = borrowingSources;
	}
	@ExcelField(title="借款编号",type=1,align=2,sort=10)
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	
}




