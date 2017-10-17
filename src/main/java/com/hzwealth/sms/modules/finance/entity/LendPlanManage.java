/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.entity;

import java.util.Date;

import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

/**
 * 出借计划管理详情Entity
 * @author FYP
 * @version 2017-07-03
 */
public class LendPlanManage extends DataEntity<LendPlanManage> {
	
	private static final long serialVersionUID = 1L;
	
	private String mobile;				// 手机号
	private Date investTime;		// 出借时间
	private String amount;		// 原始出借总金额
	private String capitalCode;		// 资金编号
	private String capitalSource;		// 资金来源：0PC1APP2M站
	private String projectedEarnings;		// 预计收益
	private String couponType;			// 优惠券类型
	private String couponAmount;	// 优惠券面额
	private String status;		// 资金状态  
	private String financeId;		// 出借产品Id
	private String userId;		// 出借人ID
	private String rowNO;				// excel显示序号
	
	public LendPlanManage() {
		super();
	}

	public LendPlanManage(String id){
		super(id);
	}
	
	@ExcelField(title="手机号",type=1,align=2,sort=20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@ExcelField(title="出借时间",type=1,align=2,sort=30)
	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	
	@ExcelField(title="出借金额",type=1,align=2,sort=40)
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@ExcelField(title="交易单号",type=1,align=2,sort=50)
	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}
	@ExcelField(title="出借端口",type=1,align=2,sort=60)
	public String getCapitalSource() {
		return capitalSource;
	}

	public void setCapitalSource(String capitalSource) {
		this.capitalSource = capitalSource;
	}
	
	@ExcelField(title="预计收益（元）",type=1,align=2,sort=70)
	public String getProjectedEarnings() {
		return projectedEarnings;
	}

	public void setProjectedEarnings(String projectedEarnings) {
		this.projectedEarnings = projectedEarnings;
	}
	@ExcelField(title="优惠券类型",type=1,align=2,sort=80)
	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	@ExcelField(title="优惠券面额",type=1,align=2,sort=90)
	public String getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}
	@ExcelField(title="状态",type=1,align=2,sort=100)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ExcelField(title="序号",type=1,align=2,sort=10)
	public String getRowNO() {
		return rowNO;
	}

	public void setRowNO(String rowNO) {
		this.rowNO = rowNO;
	}

	
}