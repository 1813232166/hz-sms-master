/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 预约记录管理Entity
 * @author HDG
 * @version 2017-07-05
 */
public class TCapitalBespeak extends DataEntity<TCapitalBespeak> {
	
	private static final long serialVersionUID = 1L;
	private String capitalCategory;		// 资金类别  1：出借计划 2 散标集
	private String status;		// 资金状态  1:待匹配;2:匹配中;3:已匹配;4:已退出;5:部分出借失败;6:出借失败;7:债权转让申请中
	private String amount;		// 原始出借总金额
	private String investAmount;		// 已投资金额
	private String surplusAmount;		// 剩余金额
	private String rate;		// 利率 （利率统一保存规则例：12.6%  则保存：12.6）
	private String nper;		// 期数
	private String customer;		// 客户级别  1:老用户;0:新用户
	private Date investTime;		// 出借时间
	private String capitalSource;		// 资金来源：0PC1APP2M站
	private String capitalCode;		// 资金编号
	private User user;		// 出借人ID
	private String userId;		// 出借人ID
	private String financeId;		// 出借产品Id（出借计划or散标集）
	private String financeBespeakId;		// 预约计划ID
	
	
	private String name;		// 出借计划名称
	private String mobile;		// 手机号
	private String realName;		// 真实姓名
	
	private Date beginBespeakTime;//预约时间 开始
	private Date endBespeakTime;//预约时间  结束
	public TCapitalBespeak() {
		super();
	}

	public TCapitalBespeak(String id){
		super(id);
	}

	@Length(min=0, max=32, message="资金类别  1：出借计划 2 散标集长度必须介于 0 和 32 之间")
	public String getCapitalCategory() {
		return capitalCategory;
	}

	public void setCapitalCategory(String capitalCategory) {
		this.capitalCategory = capitalCategory;
	}
	
	@Length(min=1, max=2, message="资金状态  1:待匹配;2:匹配中;3:已匹配;4:已退出;5:部分出借失败;6:出借失败;7:债权转让申请中长度必须介于 1 和 2 之间")
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
	
	public String getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}
	
	public String getSurplusAmount() {
		return surplusAmount;
	}

	public void setSurplusAmount(String surplusAmount) {
		this.surplusAmount = surplusAmount;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	
	@Length(min=0, max=10, message="资金来源：0PC1APP2M站长度必须介于 0 和 10 之间")
	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}
	
	@Length(min=0, max=32, message="资金编号长度必须介于 0 和 32 之间")
	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}
	
	@NotNull(message="出借人ID不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=32, message="出借产品Id（出借计划or散标集）长度必须介于 0 和 32 之间")
	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	
	@Length(min=0, max=32, message="预约计划ID长度必须介于 0 和 32 之间")
	public String getFinanceBespeakId() {
		return financeBespeakId;
	}

	public void setFinanceBespeakId(String financeBespeakId) {
		this.financeBespeakId = financeBespeakId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getBeginBespeakTime() {
		return beginBespeakTime;
	}

	public void setBeginBespeakTime(Date beginBespeakTime) {
		this.beginBespeakTime = beginBespeakTime;
	}

	public Date getEndBespeakTime() {
		return endBespeakTime;
	}

	public void setEndBespeakTime(Date endBespeakTime) {
		this.endBespeakTime = endBespeakTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}