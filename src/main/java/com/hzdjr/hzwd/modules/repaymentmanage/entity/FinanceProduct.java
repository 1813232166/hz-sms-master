package com.hzdjr.hzwd.modules.repaymentmanage.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *Title:FinanceProduct
 *Description: t_finance_product
 *@author:黄亚浩
 *@date:2016年10月20日 下午4:17:58
 */
public class FinanceProduct {

	private String id;
	private String name;    //理财名称
	private String financeId;   //理财品类的id'
	private String productName;    //品类名称',
	private Integer productNo;   //理财顺序号'
	private BigDecimal amount;    //资金总额',
	private BigDecimal uninvestedAmount;    //未投资金额',
	private BigDecimal matchingAmount;   //撮合中金额',
	private BigDecimal matchedAmount;   //'撮合成功金额',
	private BigDecimal minInvestAmount;   //最小投资金额'
	private BigDecimal maxInvestAmount;   //最大投资金额',
	private Date endTime;   //结束时间'
	private Date starTime;   //开始时间
	private Integer repayStyle;  //还款方式(2-到期还本;3-一次性还款)',
	private Integer status;   //'状态(1.收益中；2.已结清;3待审核；4招标中;5审核未通过）',
	private BigDecimal rate;   //利率',
	private BigDecimal rewardRate;  //奖励利率',
	private Integer closeTime;   //封闭期'
	private String unit;   //封闭期单位1 天2月3年',
	private String operator;  // '录入人'
	private Date opTime;  //录入时间',
	private String auditor;  //审核人'
	private Date auditTime;   //审核时间',
	private String auditContent;  //审核内容'
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getUninvestedAmount() {
		return uninvestedAmount;
	}
	public void setUninvestedAmount(BigDecimal uninvestedAmount) {
		this.uninvestedAmount = uninvestedAmount;
	}
	public BigDecimal getMatchingAmount() {
		return matchingAmount;
	}
	public void setMatchingAmount(BigDecimal matchingAmount) {
		this.matchingAmount = matchingAmount;
	}
	public BigDecimal getMatchedAmount() {
		return matchedAmount;
	}
	public void setMatchedAmount(BigDecimal matchedAmount) {
		this.matchedAmount = matchedAmount;
	}
	public BigDecimal getMinInvestAmount() {
		return minInvestAmount;
	}
	public void setMinInvestAmount(BigDecimal minInvestAmount) {
		this.minInvestAmount = minInvestAmount;
	}
	public BigDecimal getMaxInvestAmount() {
		return maxInvestAmount;
	}
	public void setMaxInvestAmount(BigDecimal maxInvestAmount) {
		this.maxInvestAmount = maxInvestAmount;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getStarTime() {
		return starTime;
	}
	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}
	public Integer getRepayStyle() {
		return repayStyle;
	}
	public void setRepayStyle(Integer repayStyle) {
		this.repayStyle = repayStyle;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getRewardRate() {
		return rewardRate;
	}
	public void setRewardRate(BigDecimal rewardRate) {
		this.rewardRate = rewardRate;
	}
	public Integer getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Integer closeTime) {
		this.closeTime = closeTime;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditContent() {
		return auditContent;
	}
	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}
	@Override
	public String toString() {
		return "FinanceProduct [id=" + id + ", name=" + name + ", financeId=" + financeId + ", productName="
				+ productName + ", productNo=" + productNo + ", amount=" + amount + ", uninvestedAmount="
				+ uninvestedAmount + ", matchingAmount=" + matchingAmount + ", matchedAmount=" + matchedAmount
				+ ", minInvestAmount=" + minInvestAmount + ", maxInvestAmount=" + maxInvestAmount + ", endTime="
				+ endTime + ", starTime=" + starTime + ", repayStyle=" + repayStyle + ", status=" + status + ", rate="
				+ rate + ", rewardRate=" + rewardRate + ", closeTime=" + closeTime + ", unit=" + unit + ", operator="
				+ operator + ", opTime=" + opTime + ", auditor=" + auditor + ", auditTime=" + auditTime
				+ ", auditContent=" + auditContent + "]";
	}
	
	
	
	
	
	
	
}
