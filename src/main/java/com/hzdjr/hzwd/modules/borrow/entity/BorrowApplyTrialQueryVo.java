package com.hzdjr.hzwd.modules.borrow.entity;

import java.math.BigDecimal;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 
 * @Title: BorrowApplyTiralQueryVo.java
 * @Package com.hzdjr.hzwd.modules.borrow.entity
 * @Description: 线下进件标的审核查询
 * @author zhf
 * @date Apr 25, 2017
 * @version 1.0
 */
public class BorrowApplyTrialQueryVo  extends DataEntity<BorrowApplyTrialQueryVo> {

	private static final long serialVersionUID = 7303964890111966154L;
	private Integer pageStart;					// 起始条数
	private Integer pageSize;					// 页面大小
	private String borrowCode;					// 申请编号
	private String realName;					// 姓名
	private String productType;					// 产品类型
	private String borrowStatus;				// 状态
	private BigDecimal borrowAmountLow;			// 借款金额 最小
	private BigDecimal borrowAmountHigh;		// 借款金额 最大
	private BigDecimal periodsLow;				// 借款期限 最小
	private BigDecimal periodsHigh;				// 借款期限 最大
	private String createTimeBegin;				// 申请时间 开始
	private String createTimeEnd;				// 申请时间 结束
	private Map<String, String> borrowStatusMap;// 所需状态集合
	private String isExcel;						// 导出excel需要小于20000
	private String loanNumber;					// 借款编号
	
	public Integer getPageStart() {
		return pageStart;
	}
	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getBorrowCode() {
		return borrowCode;
	}
	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getBorrowStatus() {
		return borrowStatus;
	}
	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}
	public BigDecimal getBorrowAmountLow() {
		return borrowAmountLow;
	}
	public void setBorrowAmountLow(BigDecimal borrowAmountLow) {
		this.borrowAmountLow = borrowAmountLow;
	}
	public BigDecimal getBorrowAmountHigh() {
		return borrowAmountHigh;
	}
	public void setBorrowAmountHigh(BigDecimal borrowAmountHigh) {
		this.borrowAmountHigh = borrowAmountHigh;
	}
	public BigDecimal getPeriodsLow() {
		return periodsLow;
	}
	public void setPeriodsLow(BigDecimal periodsLow) {
		this.periodsLow = periodsLow;
	}
	public BigDecimal getPeriodsHigh() {
		return periodsHigh;
	}
	public void setPeriodsHigh(BigDecimal periodsHigh) {
		this.periodsHigh = periodsHigh;
	}
	public String getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Map<String, String> getBorrowStatusMap() {
		return borrowStatusMap;
	}
	public void setBorrowStatusMap(Map<String, String> borrowStatusMap) {
		this.borrowStatusMap = borrowStatusMap;
	}
	public String getIsExcel() {
		return isExcel;
	}
	public void setIsExcel(String isExcel) {
		this.isExcel = isExcel;
	}
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	
}
