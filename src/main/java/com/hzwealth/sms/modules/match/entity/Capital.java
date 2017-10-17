package com.hzwealth.sms.modules.match.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;

/**
 * 资金列表Entity
 * @author FYP
 * @version 2017-06-12
 */
public class Capital extends DataEntity<Capital> {
	
	private static final long serialVersionUID = 1L;
	private String capitalType;		// 资金类型 1:原始资金 ;999:复投出借;
	private String capitalCategory;	// 资金类别 1：出借计划 2 散标集
	private String status;		// 资金状态  1:待匹配;2:匹配中;3:已匹配;
	private String amount;		// 原始出借总金额
	private String surplusAmount;		// 剩余金额
	private String matchedAmount;		// 匹配金额
	private String rate;		// 利率
	private String nper;		// 期数
	private String customer;		// 客户级别  1:老用户;0:新用户
	private String urgent;		// 是否加急  1:加急;0:不加急
	private String stickTop;		// 是否置顶  1:置顶;0:不置顶
	private Date investTime;		// 出借时间
	private Date matchedTime;		// 匹配成功时间
	private Date freezeTime;		// 冻结资金时间
	private Date thawTime;		// 解冻资金时间
	private String sourceCapitalId;		// 资金来源  新资金:0;回款资金:父资金id
	private String customWeight;		// 自定义权重系数
	private String weight;		// 权重值
	private String capitalSource;		// 资金来源：pc/app
	private String capitalProduct;		// 资金产品
	private String projectedEarnings;		// 预计收益
	private String matchingPenNumber;		// 撮合笔数
	private String capitalCode;		// 资金编号
	private String userId;		// 出借人ID
	
	private String realName;		// 真实姓名
	private String idCard;		// 身份证号
	
	private String weightLow;//最低权重值
	private String weightHigh;//最高权重值
	
	private Date beginInvestTime;		// 开始 加入队列时间
	private Date endInvestTime;		// 结束 加入队列时间
	
	
	public Capital() {
		super();
	}

	public Capital(String id){
		super(id);
	}

	@Length(min=1, max=2, message="资金类型 1:原始资金 ;999:复投出借;")
	public String getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}
	
	@Length(min=1, max=2, message="资金状态  1:待匹配;2:匹配中;3:已匹配;长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getSurplusAmount() {
		return surplusAmount;
	}

	public void setSurplusAmount(String surplusAmount) {
		this.surplusAmount = surplusAmount;
	}
	
	public String getMatchedAmount() {
		return matchedAmount;
	}

	public void setMatchedAmount(String matchedAmount) {
		this.matchedAmount = matchedAmount;
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
	
	@Length(min=0, max=2, message="客户级别  1:老用户;0:新用户长度必须介于 0 和 2 之间")
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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
	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMatchedTime() {
		return matchedTime;
	}

	public void setMatchedTime(Date matchedTime) {
		this.matchedTime = matchedTime;
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
	
	@Length(min=0, max=32, message="资金来源  新资金:0;回款资金:父资金id长度必须介于 0 和 32 之间")
	public String getSourceCapitalId() {
		return sourceCapitalId;
	}

	public void setSourceCapitalId(String sourceCapitalId) {
		this.sourceCapitalId = sourceCapitalId;
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
	
	@Length(min=0, max=10, message="资金来源：pc/app长度必须介于 0 和 10 之间")
	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}
	
	@Length(min=0, max=10, message="资金产品长度必须介于 0 和 10 之间")
	public String getCapitalProduct() {
		return capitalProduct;
	}

	public void setCapitalProduct(String capitalProduct) {
		this.capitalProduct = capitalProduct;
	}
	
	public String getProjectedEarnings() {
		return projectedEarnings;
	}

	public void setProjectedEarnings(String projectedEarnings) {
		this.projectedEarnings = projectedEarnings;
	}
	
	@Length(min=0, max=11, message="撮合笔数长度必须介于 0 和 11 之间")
	public String getMatchingPenNumber() {
		return matchingPenNumber;
	}

	public void setMatchingPenNumber(String matchingPenNumber) {
		this.matchingPenNumber = matchingPenNumber;
	}
	
	@Length(min=0, max=32, message="资金编号长度必须介于 0 和 32 之间")
	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getWeightLow() {
		return weightLow;
	}

	public void setWeightLow(String weightLow) {
		this.weightLow = weightLow;
	}

	public String getWeightHigh() {
		return weightHigh;
	}

	public void setWeightHigh(String weightHigh) {
		this.weightHigh = weightHigh;
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

	public String getCapitalCategory() {
		return capitalCategory;
	}

	public void setCapitalCategory(String capitalCategory) {
		this.capitalCategory = capitalCategory;
	}
	
	
	
}