/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

/**
 * 出借计划交易Entity
 * @author ZHF
 * @version 2017-06-28
 */
public class FinanceTransaction extends DataEntity<FinanceTransaction> {
	
	private static final long serialVersionUID = 1L;
	private String capitalType;		// 资金类型  1:原始资金 ;999:复投出借;
	private String capitalCategory;		// 资金类别  1：出借计划 2 散标集
	private String status;		// 资金状态  1:待匹配;2:匹配中;3:已匹配;4:已退出;5:部分出借失败;6:出借失败;
	private String amount;		// 原始出借总金额
	private String surplusAmount;		// 剩余金额
	private String matchedAmount;		// 匹配金额
	private String rate;		// 利率 （利率统一保存规则例：12.6%  则保存：12.6）
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
	private String capitalProduct;		// 资金产品
	private String matchingPenNumber;		// 撮合笔数
	private String userId;		// 出借人ID
	private Date backEndDate;		// 到期应退出日期
	private Date interestDate;		// 计息日
	private String financeId;		// 出借产品Id（出借计划or散标集）
	
	
	private String capitalId;			// 资金id
	private String financeName;			// 计划名称
	private String financeProductId;	// 计划产品id
	private String financeCode;			// 计划编号
	private String mobile;				// 手机号
	private String userName;			// 真实姓名
	private String capitalCode;			// 交易单号
	private Date capitalInvestTime;		// 出借时间
	private BigDecimal capitailAmount;	// 出借金额
	private String lendingMethod;		// 出借方式
	private String capitalSource;		// 出借端口
	private String earlyExit;			// 提前退出
	private String capitalStatus;		// 交易单状态
	private Date capitalInvestTimeBegin;// 出借时间开始
	private Date capitalInvestTimeEnd;	// 出借时间结束
	private BigDecimal projectedEarnings;// 预计收益
	private Date endDate;				// 到期应退出日期
	private Date quitTime;				// 申请退出时间
	private Date realQuitTime;			// 真实退出时间
	private BigDecimal paidIncome;		// 已收收益
	private BigDecimal returnedFunds;	// 退回金额
	
	private String rowNO;				// excel显示序号
	private String couponType;			// 优惠券类型
	private BigDecimal couponAmount;	// 优惠券面额
	
	public FinanceTransaction() {
		super();
	}

	public FinanceTransaction(String id){
		super(id);
	}

	@Length(min=1, max=32, message="资金类型  1:原始资金 ;999:复投出借;长度必须介于 1 和 32 之间")
	public String getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}
	
	@Length(min=0, max=32, message="资金类别  1：出借计划 2 散标集长度必须介于 0 和 32 之间")
	public String getCapitalCategory() {
		return capitalCategory;
	}

	public void setCapitalCategory(String capitalCategory) {
		this.capitalCategory = capitalCategory;
	}
	
	@Length(min=1, max=2, message="资金状态  1:待匹配;2:匹配中;3:已匹配;4:已退出;5:部分出借失败;6:出借失败;长度必须介于 1 和 2 之间")
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
	
	@ExcelField(title="出借端口",type=1,align=2,sort=120)
	@Length(min=0, max=10, message="资金来源：0PC1APP2M站长度必须介于 0 和 10 之间")
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
	
	@Length(min=0, max=11, message="撮合笔数长度必须介于 0 和 11 之间")
	public String getMatchingPenNumber() {
		return matchingPenNumber;
	}

	public void setMatchingPenNumber(String matchingPenNumber) {
		this.matchingPenNumber = matchingPenNumber;
	}
	
	@ExcelField(title="交易单号",type=1,align=2,sort=60)
	@Length(min=0, max=32, message="资金编号长度必须介于 0 和 32 之间")
	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}
	
	@Length(min=1, max=32, message="出借人ID长度必须介于 1 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBackEndDate() {
		return backEndDate;
	}

	public void setBackEndDate(Date backEndDate) {
		this.backEndDate = backEndDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}
	
	@Length(min=0, max=32, message="出借产品Id（出借计划or散标集）长度必须介于 0 和 32 之间")
	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}

	@ExcelField(title="计划名称",type=1,align=2,sort=20)
	public String getFinanceName() {
		return financeName;
	}

	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}

	@ExcelField(title="计划编号",type=1,align=2,sort=30)
	public String getFinanceCode() {
		return financeCode;
	}

	public void setFinanceCode(String financeCode) {
		this.financeCode = financeCode;
	}

	@ExcelField(title="手机号",type=1,align=2,sort=40)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ExcelField(title="真实姓名",type=1,align=2,sort=50)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ExcelField(title="出借时间",type=1,align=2,sort=70)
	public Date getCapitalInvestTime() {
		return capitalInvestTime;
	}

	public void setCapitalInvestTime(Date capitalInvestTime) {
		this.capitalInvestTime = capitalInvestTime;
	}

	@ExcelField(title="出借金额（元）",type=1,align=2,sort=80)
	public BigDecimal getCapitailAmount() {
		return capitailAmount;
	}

	public void setCapitailAmount(BigDecimal capitailAmount) {
		this.capitailAmount = capitailAmount;
	}

	@ExcelField(title="出借方式",type=1,align=2,sort=110)
	public String getLendingMethod() {
		return lendingMethod;
	}

	public void setLendingMethod(String lendingMethod) {
		this.lendingMethod = lendingMethod;
	}

	@ExcelField(title="是否提前退出",type=1,align=2,sort=130)
	public String getEarlyExit() {
		return earlyExit;
	}

	public void setEarlyExit(String earlyExit) {
		this.earlyExit = earlyExit;
	}
	
	@ExcelField(title="状态",type=1,align=2,sort=140)
	public String getCapitalStatus() {
		return capitalStatus;
	}

	public void setCapitalStatus(String capitalStatus) {
		this.capitalStatus = capitalStatus;
	}

	public Date getCapitalInvestTimeBegin() {
		return capitalInvestTimeBegin;
	}

	public void setCapitalInvestTimeBegin(Date capitalInvestTimeBegin) {
		this.capitalInvestTimeBegin = capitalInvestTimeBegin;
	}

	public Date getCapitalInvestTimeEnd() {
		return capitalInvestTimeEnd;
	}

	public void setCapitalInvestTimeEnd(Date capitalInvestTimeEnd) {
		this.capitalInvestTimeEnd = capitalInvestTimeEnd;
	}

	public String getFinanceProductId() {
		return financeProductId;
	}

	public void setFinanceProductId(String financeProductId) {
		this.financeProductId = financeProductId;
	}

	@ExcelField(title="优惠券类型",type=1,align=2,sort=90)
	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	@ExcelField(title="优惠券面额",type=1,align=2,sort=100)
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	@ExcelField(title="序号",type=1,align=2,sort=10)
	public String getRowNO() {
		return rowNO;
	}

	public void setRowNO(String rowNO) {
		this.rowNO = rowNO;
	}

	public String getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}

	public BigDecimal getProjectedEarnings() {
		return projectedEarnings;
	}

	public void setProjectedEarnings(BigDecimal projectedEarnings) {
		this.projectedEarnings = projectedEarnings;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRealQuitTime() {
		return realQuitTime;
	}

	public void setRealQuitTime(Date realQuitTime) {
		this.realQuitTime = realQuitTime;
	}

	public BigDecimal getPaidIncome() {
		return paidIncome;
	}

	public void setPaidIncome(BigDecimal paidIncome) {
		this.paidIncome = paidIncome;
	}

	public BigDecimal getReturnedFunds() {
		return returnedFunds;
	}

	public void setReturnedFunds(BigDecimal returnedFunds) {
		this.returnedFunds = returnedFunds;
	}
	
}