package com.hzwealth.sms.modules.repaymentmanage.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *Title:Invest
 *Description:  t_invest
 *@author:黄亚浩
 *@date:2016年10月24日 下午3:49:52
 */
public class Invest {

	private Integer id;    //主键', 
	private BigDecimal investAmount;   //投资金额
	private BigDecimal yearRate;   //年利率
	private BigDecimal monthRate;   //月利率
	private String investor;   //投资人Id
	private String borrowId;  //借款ID
	private Date investTime;    //投资时间
	private Date arriTradeTime;   //到账时间
	private String oriInvestor;   //原始投资人
	private BigDecimal realAmount;   //实际投资金额
	private BigDecimal hasPI;   //已收本息
	private Integer deadline;   //期数',
	private Integer hasDeadline;   //已还款期数
	private BigDecimal recivedPrincipal;   //应收本金
	private BigDecimal recievedInterest;   //应收利息
	private BigDecimal hasPrincipal;   //已收本金
	private BigDecimal hasInterest;   //已收利息
	private BigDecimal recivedFI;  //应收罚金
	private BigDecimal hasFI;   //已收罚金
	private BigDecimal manageFee;  //管理费
	private BigDecimal reward;  //奖励
	private String flag;  //标识(0-预约;1-成功;2-失败;)'
	private String offlineflag;  //线下状态(0-生成投资接口未调用;1-生成投资接口调用成功)',
	private String interestflag;  //'计息状态(0-未计息;1-已计息)',
	private String channel;  //'投资渠道',
	private Integer isAutoBid;  //自动投标( 默认 1 手动 2 自动)',
	private Integer isDebt;   // 是否转让(1,没有转让，2转让)',
	private String investType;  //'投资类型0 理财1 散标',
	private String investCode;  //投资编号',
	private String bizId;  //交易流水号',
	private Date interestAccrualDate;  //计息日期',
	private Date expireDate;   //到期日期'
	private String refferee;  //推荐人',
	private String serverIP;  //服务器IP地址',
	private String departmentInfo;   //员工营业部名称',
	private String supplementFlag;  //是否补标（1：是，0：否）',
	private String borrowName;  //0.普享标 1悦享标
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	public BigDecimal getYearRate() {
		return yearRate;
	}
	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}
	public BigDecimal getMonthRate() {
		return monthRate;
	}
	public void setMonthRate(BigDecimal monthRate) {
		this.monthRate = monthRate;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public Date getArriTradeTime() {
		return arriTradeTime;
	}
	public void setArriTradeTime(Date arriTradeTime) {
		this.arriTradeTime = arriTradeTime;
	}
	public String getOriInvestor() {
		return oriInvestor;
	}
	public void setOriInvestor(String oriInvestor) {
		this.oriInvestor = oriInvestor;
	}
	public BigDecimal getRealAmount() {
		return realAmount;
	}
	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}
	public BigDecimal getHasPI() {
		return hasPI;
	}
	public void setHasPI(BigDecimal hasPI) {
		this.hasPI = hasPI;
	}
	public Integer getDeadline() {
		return deadline;
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
	public Integer getHasDeadline() {
		return hasDeadline;
	}
	public void setHasDeadline(Integer hasDeadline) {
		this.hasDeadline = hasDeadline;
	}
	public BigDecimal getRecivedPrincipal() {
		return recivedPrincipal;
	}
	public void setRecivedPrincipal(BigDecimal recivedPrincipal) {
		this.recivedPrincipal = recivedPrincipal;
	}
	public BigDecimal getRecievedInterest() {
		return recievedInterest;
	}
	public void setRecievedInterest(BigDecimal recievedInterest) {
		this.recievedInterest = recievedInterest;
	}
	public BigDecimal getHasPrincipal() {
		return hasPrincipal;
	}
	public void setHasPrincipal(BigDecimal hasPrincipal) {
		this.hasPrincipal = hasPrincipal;
	}
	public BigDecimal getHasInterest() {
		return hasInterest;
	}
	public void setHasInterest(BigDecimal hasInterest) {
		this.hasInterest = hasInterest;
	}
	public BigDecimal getRecivedFI() {
		return recivedFI;
	}
	public void setRecivedFI(BigDecimal recivedFI) {
		this.recivedFI = recivedFI;
	}
	public BigDecimal getHasFI() {
		return hasFI;
	}
	public void setHasFI(BigDecimal hasFI) {
		this.hasFI = hasFI;
	}
	public BigDecimal getManageFee() {
		return manageFee;
	}
	public void setManageFee(BigDecimal manageFee) {
		this.manageFee = manageFee;
	}
	public BigDecimal getReward() {
		return reward;
	}
	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOfflineflag() {
		return offlineflag;
	}
	public void setOfflineflag(String offlineflag) {
		this.offlineflag = offlineflag;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getIsAutoBid() {
		return isAutoBid;
	}
	public void setIsAutoBid(Integer isAutoBid) {
		this.isAutoBid = isAutoBid;
	}
	public Integer getIsDebt() {
		return isDebt;
	}
	public void setIsDebt(Integer isDebt) {
		this.isDebt = isDebt;
	}
	public String getInvestType() {
		return investType;
	}
	public void setInvestType(String investType) {
		this.investType = investType;
	}
	public String getInvestCode() {
		return investCode;
	}
	public void setInvestCode(String investCode) {
		this.investCode = investCode;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public Date getInterestAccrualDate() {
		return interestAccrualDate;
	}
	public void setInterestAccrualDate(Date interestAccrualDate) {
		this.interestAccrualDate = interestAccrualDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getRefferee() {
		return refferee;
	}
	public void setRefferee(String refferee) {
		this.refferee = refferee;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getDepartmentInfo() {
		return departmentInfo;
	}
	public void setDepartmentInfo(String departmentInfo) {
		this.departmentInfo = departmentInfo;
	}
	public String getSupplementFlag() {
		return supplementFlag;
	}
	public void setSupplementFlag(String supplementFlag) {
		this.supplementFlag = supplementFlag;
	}
	public String getBorrowName() {
		return borrowName;
	}
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
	public String getInterestflag() {
		return interestflag;
	}
	public void setInterestflag(String interestflag) {
		this.interestflag = interestflag;
	}
	
	
	
	
	
	
	
	
}
