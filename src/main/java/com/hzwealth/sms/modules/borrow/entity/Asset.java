package com.hzwealth.sms.modules.borrow.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Asset implements Serializable {
	
	private static final long serialVersionUID = 8291907464369291420L;
	private String id;			//资产id
	private String status;		//资产状态  1:待匹配;2:部分匹配;3:已匹配;
	private String assetCategory;//资产类别：1通用资产（出借计划） 2散标集
	private String assetType;	//资产类型  1:新借款;2:存量债券(债权转让标的)
	private String productId;	//产品类型(borrow表type字段)  3公积金贷款4精英贷
	private String raiseTerm;	//匹配期限(天)
	private BigDecimal amount;	//金额
	private BigDecimal residueAmount;//剩余金额
	private String borrowType;	//借款类型  1:企业;2:个人
	private BigDecimal rate;	//利率 （利率统一保存规则例：12.6%  则保存：12.6）
	private String nper;		//期数
	private String urgent;		//是否加急  1:加急;0:不加急
	private String stickTop;	//是否置顶  1:置顶;0:不置顶
	private Date entryTime;		//进入债匹时间
	private Date matchedTime;	//匹配完成时间
	private String freeze;		//是否冻结  1:冻结;0:不冻结
	private Date freezeTime;	//冻结时间
	private Date thawTime;		//解冻时间
	private String sourceAssetId;//债券来源  新借款:0;存量债券:父债券id
	private String originalAssetId;//债权原始来源  新借款:0;存量债权:最初债权id
	private String matchedId;	//债权转让的投资号,对应invest表中的id
	private Integer customWeight;//自定义权重系数
	private Integer weight;		//权重值
	private String borrowId;	//资产对应标的id
	private String userId;		//借款人 ID
	private String assetCode;	//资产编号
	private Integer matchingPenNumber;//撮合笔数
	private String assetSource;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRaiseTerm() {
		return raiseTerm;
	}
	public void setRaiseTerm(String raiseTerm) {
		this.raiseTerm = raiseTerm;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getNper() {
		return nper;
	}
	public void setNper(String nper) {
		this.nper = nper;
	}
	public String getUrgent() {
		return urgent;
	}
	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public Date getMatchedTime() {
		return matchedTime;
	}
	public void setMatchedTime(Date matchedTime) {
		this.matchedTime = matchedTime;
	}
	public String getFreeze() {
		return freeze;
	}
	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}
	public Date getFreezeTime() {
		return freezeTime;
	}
	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
	}
	public Date getThawTime() {
		return thawTime;
	}
	public void setThawTime(Date thawTime) {
		this.thawTime = thawTime;
	}
	public String getSourceAssetId() {
		return sourceAssetId;
	}
	public void setSourceAssetId(String sourceAssetId) {
		this.sourceAssetId = sourceAssetId;
	}
	public String getOriginalAssetId() {
		return originalAssetId;
	}
	public void setOriginalAssetId(String originalAssetId) {
		this.originalAssetId = originalAssetId;
	}
	public String getMatchedId() {
		return matchedId;
	}
	public void setMatchedId(String matchedId) {
		this.matchedId = matchedId;
	}
	public BigDecimal getResidueAmount() {
		return residueAmount;
	}
	public void setResidueAmount(BigDecimal residueAmount) {
		this.residueAmount = residueAmount;
	}
	public String getStickTop() {
		return stickTop;
	}
	public void setStickTop(String stickTop) {
		this.stickTop = stickTop;
	}
	public Integer getCustomWeight() {
		return customWeight;
	}
	public void setCustomWeight(Integer customWeight) {
		this.customWeight = customWeight;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public Integer getMatchingPenNumber() {
		return matchingPenNumber;
	}
	public void setMatchingPenNumber(Integer matchingPenNumber) {
		this.matchingPenNumber = matchingPenNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAssetCategory() {
		return assetCategory;
	}
	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}
	public String getAssetSource() {
		return assetSource;
	}
	public void setAssetSource(String assetSource) {
		this.assetSource = assetSource;
	}
	
}
