package com.hzdjr.hzwd.modules.finance.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 出借计划管理Entity
 * @author FYP
 * @version 2017-06-28
 */
public class Finance extends DataEntity<Finance> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// product_id
	private String status;		// 出借计划状态（1:未发布,2:待审核,3:审核失败,4:待发布,5:募集中,6:正常满标,7:已过募集期满标,8:手动满标）
	private String name;		// 出借计划名称
	private String financeCode;		// 计划编号
	private String collectAmount;		// 募集总额
	private String alreadyCollectedAmount;		// 已募集金额
	private String nper;		// 出借期限
	private String lendingMethod;		// 出借方式
	private String rate;		// 年均出借回报率约
	private String rewardRate;		// 年均出借回报率约
	private String isNoviceLabel;
	private String interestDateType;		// 起息日(1:匹配成功第二日)
	private String minTenderSum;		// 最低出借金额
	private String incrementalAmount;		// 递增金额
	private String maxTenderSum;		// 本期产品最高出借金额
	private Date startTimeOfCollection;		// 募集开始时间
	private Date endTimeOfCollection;		// 募集结束时间
	private String poundage;		// 期限过半提前赎回手续费
	private String poundageMore;		// 期限未过半提前赎回手续费
	private String exitMode;		// 退出方式
	private String guaranteeMode;		// 保障方式
	private String detail;		// 介绍
	private Date createTime;		// 创建时间、
	
	
	private Date beginCreateTime;		// 创建时间QI
	private Date endCreateTime;		// 创建时间ZHI
	
	
	private String scale;		// 募集比例
	
	private String lendingAgreement;		// 协议名称
	private String lendingAgreementCode;		//协议code
	
	private Date auditCreatTime;//审核时间
	private String cause;//备注：审核未通过原因
	
	public Finance() {
		super();
	}

	public Finance(String id){
		super(id);
	}

	@Length(min=1, max=32, message="product_id长度必须介于 1 和 32 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=0, max=3, message="出借计划状态（1:未发布,2:待审核,3:审核失败,4:待发布,5:募集中,6:正常满标,7:已过募集期满标,8:手动满标）长度必须介于 0 和 3 之间")
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
	
	@Length(min=0, max=32, message="计划编号长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=50, message="出借方式长度必须介于 0 和 50 之间")
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

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getInterestDateType() {
		return interestDateType;
	}

	public void setInterestDateType(String interestDateType) {
		this.interestDateType = interestDateType;
	}

	public String getLendingAgreement() {
		return lendingAgreement;
	}

	public void setLendingAgreement(String lendingAgreement) {
		this.lendingAgreement = lendingAgreement;
	}

	public String getLendingAgreementCode() {
		return lendingAgreementCode;
	}

	public void setLendingAgreementCode(String lendingAgreementCode) {
		this.lendingAgreementCode = lendingAgreementCode;
	}

	public Date getAuditCreatTime() {
		return auditCreatTime;
	}

	public void setAuditCreatTime(Date auditCreatTime) {
		this.auditCreatTime = auditCreatTime;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getPoundageMore() {
		return poundageMore;
	}

	public String getRewardRate() {
		return rewardRate;
	}

	public void setRewardRate(String rewardRate) {
		this.rewardRate = rewardRate;
	}

	public void setPoundageMore(String poundageMore) {
		this.poundageMore = poundageMore;
	}

	public String getIsNoviceLabel() {
		return isNoviceLabel;
	}

	public void setIsNoviceLabel(String isNoviceLabel) {
		this.isNoviceLabel = isNoviceLabel;
	}
	
	
}