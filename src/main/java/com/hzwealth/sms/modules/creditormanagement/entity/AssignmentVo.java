package com.hzwealth.sms.modules.creditormanagement.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hzwealth.sms.common.persistence.DataEntity;
/**
 * 转让列表Entity
 * @author HDG
 * @version 2017-07-27
 */
public class AssignmentVo extends DataEntity<AssignmentVo>{
	private static final long serialVersionUID = 1L;
	private String tinvestId;//t_invest表ID
	private String assetCode;//债权编号
	private String borrowtitle;//债权名称
	private String investRealname;//受让人
	private String investamount;//受让金额
	private String borrowRealname;//出让人
	private String capitalRate;//出借年化利率
	private String deadline;//借款期限
	private String surplusDeadlline;//剩余期限
	private String payMethod;//还款方式
	private Date investtime;//受让时间
	private Date lendingtime;//完成时间
	private String borrowstatus;//状态
	private String transfertype;//转让类型
	private String loannumber;		// 借款编号
	private BigDecimal amount;	//债权价值（元）
	private String cityCode;//所在城市
	private String idcard;//身份证号
	private BigDecimal yearIncome;//年收入
	private String age;//年龄
	private String sex;//性别
	private String usageOfLoan;//借款用途
	private String mintendersum;		// 最低出借金额
	private String investId;		// t_invest表id
	private String borrowId;		// borrow_id
	//查询条件
	private Date investtimeBeginTimes;
	private Date investtimeEndTimes;
	private Date lendingtimeBeginTimes;
	private Date lendingtimeEndTimes;
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getBorrowtitle() {
		return borrowtitle;
	}
	public void setBorrowtitle(String borrowtitle) {
		this.borrowtitle = borrowtitle;
	}
	public String getInvestRealname() {
		return investRealname;
	}
	public void setInvestRealname(String investRealname) {
		this.investRealname = investRealname;
	}
	public String getInvestamount() {
		return investamount;
	}
	public void setInvestamount(String investamount) {
		this.investamount = investamount;
	}
	public String getBorrowRealname() {
		return borrowRealname;
	}
	public void setBorrowRealname(String borrowRealname) {
		this.borrowRealname = borrowRealname;
	}
	public String getCapitalRate() {
		if(StringUtils.isEmpty(capitalRate) ){
			return "0";
		}
		return capitalRate;
	}
	public void setCapitalRate(String capitalRate) {
		this.capitalRate = capitalRate;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getSurplusDeadlline() {
		if(surplusDeadlline==null || surplusDeadlline.equals("")){
			return "0";
		}
		return surplusDeadlline;
	}
	public void setSurplusDeadlline(String surplusDeadlline) {
		this.surplusDeadlline = surplusDeadlline;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public Date getInvesttime() {
		return investtime;
	}
	public void setInvesttime(Date investtime) {
		this.investtime = investtime;
	}
	public Date getLendingtime() {
		return lendingtime;
	}
	public void setLendingtime(Date lendingtime) {
		this.lendingtime = lendingtime;
	}
	public String getBorrowstatus() {
		return borrowstatus;
	}
	public void setBorrowstatus(String borrowstatus) {
		this.borrowstatus = borrowstatus;
	}
	public Date getInvesttimeBeginTimes() {
		return investtimeBeginTimes;
	}
	public void setInvesttimeBeginTimes(Date investtimeBeginTimes) {
		this.investtimeBeginTimes = investtimeBeginTimes;
	}
	public Date getInvesttimeEndTimes() {
		return investtimeEndTimes;
	}
	public void setInvesttimeEndTimes(Date investtimeEndTimes) {
		this.investtimeEndTimes = investtimeEndTimes;
	}
	public Date getLendingtimeBeginTimes() {
		return lendingtimeBeginTimes;
	}
	public void setLendingtimeBeginTimes(Date lendingtimeBeginTimes) {
		this.lendingtimeBeginTimes = lendingtimeBeginTimes;
	}
	public Date getLendingtimeEndTimes() {
		return lendingtimeEndTimes;
	}
	public void setLendingtimeEndTimes(Date lendingtimeEndTimes) {
		this.lendingtimeEndTimes = lendingtimeEndTimes;
	}
	public String getTransfertype() {
		return transfertype;
	}
	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}
	public String getLoannumber() {
		return loannumber;
	}
	public void setLoannumber(String loannumber) {
		this.loannumber = loannumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public BigDecimal getYearIncome() {
		return yearIncome;
	}
	public void setYearIncome(BigDecimal yearIncome) {
		this.yearIncome = yearIncome;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUsageOfLoan() {
		return usageOfLoan;
	}
	public void setUsageOfLoan(String usageOfLoan) {
		this.usageOfLoan = usageOfLoan;
	}
	public String getMintendersum() {
		return mintendersum;
	}
	public void setMintendersum(String mintendersum) {
		this.mintendersum = mintendersum;
	}
	public String getInvestId() {
		return investId;
	}
	public void setInvestId(String investId) {
		this.investId = investId;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getTinvestId() {
		return tinvestId;
	}
	public void setTinvestId(String tinvestId) {
		this.tinvestId = tinvestId;
	}
	
}
