package com.hzwealth.sms.modules.finance.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;

/**
 * 预约管理Entity
 * @author FYP
 * @version 2017-07-05
 */
public class FinanceBespeakManage extends DataEntity<FinanceBespeakManage> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// product_id
	private String status;		// 出借计划状态（1:未启用,2:待审核,3:审核失败,4:启用中,5:禁用中
	private String name;		// 出借计划名称
	private String financeCode;		// 计划编号
	private String collectAmount;		// 每日最高预约金额
	private String collectedAmounts;		// 已募集金额(总额)
	private String alreadyCollectedAmount;		// 已募集金额(可用)
	private String nper;		// 出借期限
	private String lendingMethod;		// 出借方式(PTTZ:普通投资，BXFT:本息复投，BJFT:本金复投)
	private String rate;		// 年均出借回报率约
	private String rewardRate;
	private String interestDateType;		// 起息日(1:匹配成功第二日)
	private String minTenderSum;		// 最低预约金额
	private String incrementalAmount;		// 递增金额
	private String maxTenderSum;		// 最高预约金额
	private Date startTimeOfCollection;		// 募集开始时间
	private Date endTimeOfCollection;		// 募集结束时间
	private String poundage;		// 期限过半提前赎回手续费%
	private String exitMode;		// 退出方式
	private String guaranteeMode;		// 保障方式
	private String detail;		// 介绍
	private Date createTime;		// 创建时间
	private String lendingAgreementCode;		// 协议code
	private String lendingAgreement;		// 协议名称
	private String poundageMore;		// 期限未过半提前赎回手续费%
	private Date enableTime;		// 启用时间
	
	private Date beginCreateTime;		// 创建时间QI
	private Date endCreateTime;		// 创建时间ZHI
	
	public FinanceBespeakManage() {
		super();
	}

	public FinanceBespeakManage(String id){
		super(id);
	}

	@Length(min=1, max=32, message="product_id长度必须介于 1 和 32 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=3, message="出借计划状态（1:未启用,2:待审核,3:审核失败,4:启用中,5:禁用中长度必须介于 0 和 3 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFinanceCode() {
		return financeCode;
	}

	public void setFinanceCode(String financeCode) {
		this.financeCode = financeCode;
	}
	
	public String getCollectAmount() {
		return collectAmount;
	}

	public void setCollectAmount(String collectAmount) {
		this.collectAmount = collectAmount;
	}
	
	public String getCollectedAmounts() {
		return collectedAmounts;
	}

	public void setCollectedAmounts(String collectedAmounts) {
		this.collectedAmounts = collectedAmounts;
	}
	
	public String getAlreadyCollectedAmount() {
		return alreadyCollectedAmount;
	}

	public void setAlreadyCollectedAmount(String alreadyCollectedAmount) {
		this.alreadyCollectedAmount = alreadyCollectedAmount;
	}
	
	@Length(min=0, max=5, message="出借期限长度必须介于 0 和 5 之间")
	public String getNper() {
		return nper;
	}

	public void setNper(String nper) {
		this.nper = nper;
	}
	
	@Length(min=0, max=50, message="出借方式(PTTZ:普通投资，BXFT:本息复投，BJFT:本金复投)长度必须介于 0 和 50 之间")
	public String getLendingMethod() {
		return lendingMethod;
	}

	public void setLendingMethod(String lendingMethod) {
		this.lendingMethod = lendingMethod;
	}
	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@Length(min=0, max=2, message="起息日(1:匹配成功第二日)长度必须介于 0 和 2 之间")
	public String getInterestDateType() {
		return interestDateType;
	}

	public void setInterestDateType(String interestDateType) {
		this.interestDateType = interestDateType;
	}
	
	public String getMinTenderSum() {
		return minTenderSum;
	}

	public void setMinTenderSum(String minTenderSum) {
		this.minTenderSum = minTenderSum;
	}
	
	public String getIncrementalAmount() {
		return incrementalAmount;
	}

	public void setIncrementalAmount(String incrementalAmount) {
		this.incrementalAmount = incrementalAmount;
	}
	
	public String getMaxTenderSum() {
		return maxTenderSum;
	}

	public void setMaxTenderSum(String maxTenderSum) {
		this.maxTenderSum = maxTenderSum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTimeOfCollection() {
		return startTimeOfCollection;
	}

	public void setStartTimeOfCollection(Date startTimeOfCollection) {
		this.startTimeOfCollection = startTimeOfCollection;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTimeOfCollection() {
		return endTimeOfCollection;
	}

	public void setEndTimeOfCollection(Date endTimeOfCollection) {
		this.endTimeOfCollection = endTimeOfCollection;
	}
	
	public String getPoundage() {
		return poundage;
	}

	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}
	
	@Length(min=0, max=32, message="退出方式长度必须介于 0 和 32 之间")
	public String getExitMode() {
		return exitMode;
	}

	public void setExitMode(String exitMode) {
		this.exitMode = exitMode;
	}
	
	@Length(min=0, max=32, message="保障方式长度必须介于 0 和 32 之间")
	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}
	
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=100, message="协议code长度必须介于 0 和 100 之间")
	public String getLendingAgreementCode() {
		return lendingAgreementCode;
	}

	public void setLendingAgreementCode(String lendingAgreementCode) {
		this.lendingAgreementCode = lendingAgreementCode;
	}
	
	@Length(min=0, max=100, message="协议名称长度必须介于 0 和 100 之间")
	public String getLendingAgreement() {
		return lendingAgreement;
	}

	public void setLendingAgreement(String lendingAgreement) {
		this.lendingAgreement = lendingAgreement;
	}
	
	public String getPoundageMore() {
		return poundageMore;
	}

	public void setPoundageMore(String poundageMore) {
		this.poundageMore = poundageMore;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnableTime() {
		return enableTime;
	}

	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}

	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getRewardRate() {
		return rewardRate;
	}

	public void setRewardRate(String rewardRate) {
		this.rewardRate = rewardRate;
	}
	
	
}