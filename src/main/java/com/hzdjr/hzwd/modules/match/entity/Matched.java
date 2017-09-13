package com.hzdjr.hzwd.modules.match.entity;

import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 撮合队列Entity
 * @author FYP
 * @version 2017-06-15
 */
public class Matched extends DataEntity<Matched> {
	
	private static final long serialVersionUID = 1L;
	
	private String capitalCode;		// 资金编号
	private String capitalProduct;		// 资金产品
	private String capitalCategory;	// 资金类别 1：出借计划 2 散标集
	private String capitalRealName;	// 出借人姓名
	private String capitalIdCard;	// 出借人身份证
	private String capitalAmount;	//出借金额
	private String capitalMatchAmount;	//出借匹配金额
	private String capitalRate;		// 出借利率
	private String capitalNper;		// 出借期数
	private String projectedEarnings;	// 预计收益
	private String capitalMatchStatus;	// 资金匹配状态 1:部分撮合，2撮合完成，3解除撮合
	
	private String capitalType;		// 资金类型 1:原始资金 ;999:复投出借;
	private Date investTime;		// 出借时间
	private String assetCode;		// 资产编号
	private String assetCategory;		// 资产类别：1通用资产（出借计划） 2散标集
	private String productId;		// 产品类型(borrow表type字段) 1信用标2抵押标3公积金贷款4精英贷
	private String assetRealName;		// 借款人姓名
	private String assetIdCard;		//借款人身份证号
	private String assetAmount;		//借款金额
	private String assetMatchAmount;		//借款匹配金额
	private String assetNper;		// 出借期数
	private String assetRate;		// 出借利率
	private String assetType;		// 资产类型  1:新借款;2:存量债券(债权转让标的)
	
	private String capitalAmountLow;	//出借金额
	private String capitalAmountHigh;	//出借金额
	private Date beginInvestTime;		// 开始 出借时间
	private Date endInvestTime;		// 结束 出借时间
	
	private String assetAmountLow;	//借款金额
	private String assetAmountHigh;	//借款金额
	private Date beginCreateTime;		// 开始匹配时间
	private Date endCreateTime;		// 结束 匹配时间
	
	
	private String assetId;		// 资产id
	private String capitalId;		// 资金id
	private String matchAmount;		// 匹配金额
	private Date createTime;		// 匹配时间
	private String surplusBorrowAmount;// 本次匹配完毕后标的剩余可投金额
	private String investId;		// 投资id
	private String haspi;		// 已收本息
	private String surplusCapitalAmount;		// 本次匹配完毕后投资剩余金额
	private String assetMatchStatus;		// 资产匹配状态 1:部分撮合，2:撮合完成，3:解除撮合
	
	private String capitalTypeShow;		// 展示字段携带查询条件（资金类型 1:原始资金 ;999:复投出借）;
	private String assetTypeShow;		// 展示字段携带查询条件（资产类型  1:新借款;2:存量债券(债权转让标的)）
	public String getCapitalCode() {
		return capitalCode;
	}
	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}
	public String getCapitalProduct() {
		return capitalProduct;
	}
	public void setCapitalProduct(String capitalProduct) {
		this.capitalProduct = capitalProduct;
	}
	public String getCapitalCategory() {
		return capitalCategory;
	}
	public void setCapitalCategory(String capitalCategory) {
		this.capitalCategory = capitalCategory;
	}
	public String getCapitalRealName() {
		return capitalRealName;
	}
	public void setCapitalRealName(String capitalRealName) {
		this.capitalRealName = capitalRealName;
	}
	public String getCapitalIdCard() {
		return capitalIdCard;
	}
	public void setCapitalIdCard(String capitalIdCard) {
		this.capitalIdCard = capitalIdCard;
	}
	public String getCapitalAmount() {
		return capitalAmount;
	}
	public void setCapitalAmount(String capitalAmount) {
		this.capitalAmount = capitalAmount;
	}
	public String getCapitalMatchAmount() {
		return capitalMatchAmount;
	}
	public void setCapitalMatchAmount(String capitalMatchAmount) {
		this.capitalMatchAmount = capitalMatchAmount;
	}
	public String getCapitalRate() {
		return capitalRate;
	}
	public void setCapitalRate(String capitalRate) {
		this.capitalRate = capitalRate;
	}
	public String getCapitalNper() {
		return capitalNper;
	}
	public void setCapitalNper(String capitalNper) {
		this.capitalNper = capitalNper;
	}
	public String getProjectedEarnings() {
		return projectedEarnings;
	}
	public void setProjectedEarnings(String projectedEarnings) {
		this.projectedEarnings = projectedEarnings;
	}
	public String getCapitalMatchStatus() {
		return capitalMatchStatus;
	}
	public void setCapitalMatchStatus(String capitalMatchStatus) {
		this.capitalMatchStatus = capitalMatchStatus;
	}
	public String getCapitalType() {
		return capitalType;
	}
	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getAssetCategory() {
		return assetCategory;
	}
	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAssetRealName() {
		return assetRealName;
	}
	public void setAssetRealName(String assetRealName) {
		this.assetRealName = assetRealName;
	}
	public String getAssetIdCard() {
		return assetIdCard;
	}
	public void setAssetIdCard(String assetIdCard) {
		this.assetIdCard = assetIdCard;
	}
	public String getAssetAmount() {
		return assetAmount;
	}
	public void setAssetAmount(String assetAmount) {
		this.assetAmount = assetAmount;
	}
	public String getAssetMatchAmount() {
		return assetMatchAmount;
	}
	public void setAssetMatchAmount(String assetMatchAmount) {
		this.assetMatchAmount = assetMatchAmount;
	}
	public String getAssetNper() {
		return assetNper;
	}
	public void setAssetNper(String assetNper) {
		this.assetNper = assetNper;
	}
	public String getAssetRate() {
		return assetRate;
	}
	public void setAssetRate(String assetRate) {
		this.assetRate = assetRate;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getCapitalId() {
		return capitalId;
	}
	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}
	public String getMatchAmount() {
		return matchAmount;
	}
	public void setMatchAmount(String matchAmount) {
		this.matchAmount = matchAmount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSurplusBorrowAmount() {
		return surplusBorrowAmount;
	}
	public void setSurplusBorrowAmount(String surplusBorrowAmount) {
		this.surplusBorrowAmount = surplusBorrowAmount;
	}
	public String getInvestId() {
		return investId;
	}
	public void setInvestId(String investId) {
		this.investId = investId;
	}
	public String getHaspi() {
		return haspi;
	}
	public void setHaspi(String haspi) {
		this.haspi = haspi;
	}
	public String getSurplusCapitalAmount() {
		return surplusCapitalAmount;
	}
	public void setSurplusCapitalAmount(String surplusCapitalAmount) {
		this.surplusCapitalAmount = surplusCapitalAmount;
	}
	public String getAssetMatchStatus() {
		return assetMatchStatus;
	}
	public void setAssetMatchStatus(String assetMatchStatus) {
		this.assetMatchStatus = assetMatchStatus;
	}
	public String getCapitalAmountLow() {
		return capitalAmountLow;
	}
	public void setCapitalAmountLow(String capitalAmountLow) {
		this.capitalAmountLow = capitalAmountLow;
	}
	public String getCapitalAmountHigh() {
		return capitalAmountHigh;
	}
	public void setCapitalAmountHigh(String capitalAmountHigh) {
		this.capitalAmountHigh = capitalAmountHigh;
	}
	public Date getBeginInvestTime() {
		return beginInvestTime;
	}
	public void setBeginInvestTime(Date beginInvestTime) {
		this.beginInvestTime = beginInvestTime;
	}
	public Date getEndInvestTime() {
		return endInvestTime;
	}
	public void setEndInvestTime(Date endInvestTime) {
		this.endInvestTime = endInvestTime;
	}
	public String getAssetAmountLow() {
		return assetAmountLow;
	}
	public void setAssetAmountLow(String assetAmountLow) {
		this.assetAmountLow = assetAmountLow;
	}
	public String getAssetAmountHigh() {
		return assetAmountHigh;
	}
	public void setAssetAmountHigh(String assetAmountHigh) {
		this.assetAmountHigh = assetAmountHigh;
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
	public String getCapitalTypeShow() {
		return capitalTypeShow;
	}
	public void setCapitalTypeShow(String capitalTypeShow) {
		this.capitalTypeShow = capitalTypeShow;
	}
	public String getAssetTypeShow() {
		return assetTypeShow;
	}
	public void setAssetTypeShow(String assetTypeShow) {
		this.assetTypeShow = assetTypeShow;
	}
	
	
}