/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.match.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.modules.sys.entity.User;

/**
 * 资产队列Entity
 * @author HDG
 * @version 2017-06-23
 */
public class Asset extends DataEntity<Asset> {
	
	private static final long serialVersionUID = 1L;
	private String status;		// 资产状态  1:待匹配;2:部分匹配;3:已匹配;
	private String assetCategory;		// 资产类别：1通用资产（出借计划） 2散标集
	private String assetType;		// 资产类型  1:新借款;2:存量债券(债权转让标的)
	private String productId;		// 产品类型(borrow表type字段)  3公积金贷款4精英贷
	private String raiseTerm;		// 匹配期限(天)
	private String amount;		// 金额
	private String residueAmount;		// 剩余金额
	private String borrowType;		// 借款类型  1:企业;2:个人
	private String rate;		// 利率 （利率统一保存规则例：12.6%  则保存：12.6）
	private String nper;		// 期数
	private String urgent;		// 是否加急  1:加急;0:不加急
	private String stickTop;		// 是否置顶  1:置顶;0:不置顶
	private Date entryTime;		// 进入债匹时间
	private Date matchedTime;		// 匹配完成时间
	private String freeze;		// 是否冻结  1:冻结;0:不冻结
	private Date freezeTime;		// 冻结时间
	private Date thawTime;		// 解冻时间
	private String sourceAssetId;		// 债券来源  新借款:0;存量债券:父债券id
	private String originalAssetId;		// 债权原始来源  新借款:0;存量债权:最初债权id   
	private String matchedId;		// 债权转让的投资号,对应invest表中的id
	private String customWeight;		// 自定义权重系数
	private String weight;		// 权重值
	private String borrowId;		// 资产对应标的id
	private User user;		// 借款人 ID
	private String assetCode;		// 资产编号
	private String matchingPenNumber;		// 撮合笔数
	private String assetSource;		// 0:PC 1:APP 2:M站
	
	//t_user_detail表
	private String realName;//出借人姓名
	private String idCard;//身份证号
	
	//查询条件
	private Date beginTimes;
	private Date endTimes;
	private Integer beginWeight;
	private Integer endWeight;
	private String borrowcode;		// 标的编号
	public Asset() {
		super();
	}

	public Asset(String id){
		super(id);
	}

	@Length(min=1, max=2, message="资产状态  1:待匹配;2:部分匹配;3:已匹配;长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="资产类别：1通用资产（出借计划） 2散标集长度必须介于 0 和 2 之间")
	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}
	
	@Length(min=1, max=2, message="资产类型  1:新借款;2:存量债券(债权转让标的)长度必须介于 1 和 2 之间")
	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	
	@Length(min=0, max=32, message="产品类型(borrow表type字段)  3公积金贷款4精英贷长度必须介于 0 和 32 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=1, max=2, message="匹配期限(天)长度必须介于 1 和 2 之间")
	public String getRaiseTerm() {
		return raiseTerm;
	}

	public void setRaiseTerm(String raiseTerm) {
		this.raiseTerm = raiseTerm;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getResidueAmount() {
		return residueAmount;
	}

	public void setResidueAmount(String residueAmount) {
		this.residueAmount = residueAmount;
	}
	
	@Length(min=1, max=2, message="借款类型  1:企业;2:个人长度必须介于 1 和 2 之间")
	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@Length(min=1, max=10, message="期数长度必须介于 1 和 10 之间")
	public String getNper() {
		return nper;
	}

	public void setNper(String nper) {
		this.nper = nper;
	}
	
	@Length(min=0, max=2, message="是否加急  1:加急;0:不加急长度必须介于 0 和 2 之间")
	public String getUrgent() {
		return urgent;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}
	
	@Length(min=0, max=2, message="是否置顶  1:置顶;0:不置顶长度必须介于 0 和 2 之间")
	public String getStickTop() {
		return stickTop;
	}

	public void setStickTop(String stickTop) {
		this.stickTop = stickTop;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="进入债匹时间不能为空")
	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMatchedTime() {
		return matchedTime;
	}

	public void setMatchedTime(Date matchedTime) {
		this.matchedTime = matchedTime;
	}
	
	@Length(min=1, max=2, message="是否冻结  1:冻结;0:不冻结长度必须介于 1 和 2 之间")
	public String getFreeze() {
		return freeze;
	}

	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getThawTime() {
		return thawTime;
	}

	public void setThawTime(Date thawTime) {
		this.thawTime = thawTime;
	}
	
	@Length(min=1, max=32, message="债券来源  新借款:0;存量债券:父债券id长度必须介于 1 和 32 之间")
	public String getSourceAssetId() {
		return sourceAssetId;
	}

	public void setSourceAssetId(String sourceAssetId) {
		this.sourceAssetId = sourceAssetId;
	}
	
	@Length(min=0, max=32, message="债权原始来源  新借款:0;存量债权:最初债权id长度必须介于 0 和 32 之间")
	public String getOriginalAssetId() {
		return originalAssetId;
	}

	public void setOriginalAssetId(String originalAssetId) {
		this.originalAssetId = originalAssetId;
	}
	
	@Length(min=0, max=32, message="债权转让的投资号,对应invest表中的id长度必须介于 0 和 32 之间")
	public String getMatchedId() {
		return matchedId;
	}

	public void setMatchedId(String matchedId) {
		this.matchedId = matchedId;
	}
	
	@Length(min=0, max=10, message="自定义权重系数长度必须介于 0 和 10 之间")
	public String getCustomWeight() {
		return customWeight;
	}

	public void setCustomWeight(String customWeight) {
		this.customWeight = customWeight;
	}
	
	@Length(min=0, max=10, message="权重值长度必须介于 0 和 10 之间")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=1, max=32, message="资产对应标的id长度必须介于 1 和 32 之间")
	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	
	@NotNull(message="借款人 ID不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=32, message="资产编号长度必须介于 1 和 32 之间")
	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	
	@Length(min=0, max=11, message="撮合笔数长度必须介于 0 和 11 之间")
	public String getMatchingPenNumber() {
		return matchingPenNumber;
	}

	public void setMatchingPenNumber(String matchingPenNumber) {
		this.matchingPenNumber = matchingPenNumber;
	}
	
	@Length(min=0, max=10, message="0:PC 1:APP 2:M站长度必须介于 0 和 10 之间")
	public String getAssetSource() {
		return assetSource;
	}

	public void setAssetSource(String assetSource) {
		this.assetSource = assetSource;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getBeginTimes() {
		return beginTimes;
	}

	public void setBeginTimes(Date beginTimes) {
		this.beginTimes = beginTimes;
	}

	public Date getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(Date endTimes) {
		this.endTimes = endTimes;
	}

	public Integer getBeginWeight() {
		return beginWeight;
	}

	public void setBeginWeight(Integer beginWeight) {
		this.beginWeight = beginWeight;
	}

	public Integer getEndWeight() {
		return endWeight;
	}

	public void setEndWeight(Integer endWeight) {
		this.endWeight = endWeight;
	}

	public String getBorrowcode() {
		return borrowcode;
	}

	public void setBorrowcode(String borrowcode) {
		this.borrowcode = borrowcode;
	}
	
}