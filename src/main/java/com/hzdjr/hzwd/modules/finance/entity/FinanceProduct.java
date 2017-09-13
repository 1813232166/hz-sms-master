package com.hzdjr.hzwd.modules.finance.entity;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 出借产品Entity
 * @author FYP
 * @version 2017-06-28
 */
public class FinanceProduct extends DataEntity<FinanceProduct> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 计划名称
	private String nper;		// 出借期限
	private String lendingMethod;		// 出借方式(PTTZ:普通投资，BXFT:本息复投，BJFT:本金复投)
	private String rate;		// 年均出借回报率约
	private String rewardRate;
	private String isNoviceLabel;
	private String lendingAgreement;		// 出借协议
	private String lendingAgreementCode;		// 协议Code
	
	
	public FinanceProduct() {
		super();
	}

	public FinanceProduct(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNper() {
		return nper;
	}

	public void setNper(String nper) {
		this.nper = nper;
	}
	
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

	public String getRewardRate() {
		return rewardRate;
	}

	public void setRewardRate(String rewardRate) {
		this.rewardRate = rewardRate;
	}

	public String getIsNoviceLabel() {
		return isNoviceLabel;
	}

	public void setIsNoviceLabel(String isNoviceLabel) {
		this.isNoviceLabel = isNoviceLabel;
	}
	
}