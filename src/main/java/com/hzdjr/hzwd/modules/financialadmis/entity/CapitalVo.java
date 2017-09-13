package com.hzdjr.hzwd.modules.financialadmis.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;
/**
 * 放款、还款，回款VO
 *
 */
public class CapitalVo extends DataEntity<CapitalVo>{
	private static final long serialVersionUID = 1L;
	/**
	 * groups={7:还款(还代偿款),8:放款,14:回款}
	 * 用户手机号、放款&还款&回款时间、放款&还款&回款金额（元）、借款编号、(期数)、(还款类型)、平台交易流水号
	 */

	private String userMobile;
	private Date transactionTime;
	private BigDecimal transactionAmot;
	private String loanNumber;

	private String repaymentPeriod; // 还款期数
	private String totalPeriod; // 总期数
	private String repayType; // 还款类型

	private String tradeNo;

	private String beginTime;
	private String overTime;

	public CapitalVo() {
		super();
	}
	public CapitalVo(String id) {
		super(id);
	}

	@ExcelField(title="用户手机号",type=1,align=1,sort=0,groups={7,8,14})
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getTransactionTime() {
		if(transactionTime!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transactionTime);
			return str;
		}else{
			return "";
		}
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	@ExcelField(title="放款时间",type=1,align=1,sort=10,groups={8})
	public String getLoanTime() {
		return getTransactionTime();
	}
	@ExcelField(title="还款时间",type=1,align=1,sort=10,groups={7})
	public String getRepayTime() {
		return getTransactionTime();
	}
	@ExcelField(title="回款时间",type=1,align=1,sort=10,groups={14})
	public String getPaymentTime() {
		return getTransactionTime();
	}
	public  String getTransactionAmot() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
		if(transactionAmot!=null){
			return df.format(transactionAmot);
		}else{
			return df.format(new BigDecimal(0.00));
		}
	}
	public void setTransactionAmot(BigDecimal transactionAmot) {
		this.transactionAmot = transactionAmot;
	}
	@ExcelField(title="放款金额（元）",type=1,align=3,sort=30,groups={8})
	public String getLoanAmot() {
		return getTransactionAmot();
	}
	@ExcelField(title="还款金额（元）",type=1,align=3,sort=30,groups={7})
	public String getRepayAmot() {
		return getTransactionAmot();
	}
	@ExcelField(title="回款金额（元）",type=1,align=3,sort=30,groups={14})
	public String getPaymentAmot() {
		return getTransactionAmot();
	}
	@ExcelField(title="借款编号",type=1,align=1,sort=40,groups={7,8,14})
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	/**
	 * @return the showPeriod
	 */
	@ExcelField(title="期数",type=1,align=1,sort=50,groups={7,14})
	public String getShowPeriod() {
		String period = "";
		if (StringUtils.isNotBlank(repaymentPeriod) || StringUtils.isNotBlank(totalPeriod)) {
			period=StringUtils.trimToEmpty(repaymentPeriod) + "/" + StringUtils.trimToEmpty(totalPeriod);
		}
		return period;
	}
	/**
	 * @return the showRepayType
	 */
	@ExcelField(title="还款类型",type=1,align=1,sort=60,groups={7})
	public String getShowRepayType() {
		String showtype = "逾期还款";
		if ("7".equals(repayType)) { // 正常还款
			showtype = "正常还款";
		}
		return showtype;
	}

	@ExcelField(title="平台交易流水号",type=1,align=1,sort=70,groups={7,8,14})
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * @return the repaymentPeriod
	 */
	public String getRepaymentPeriod() {
		return repaymentPeriod;
	}
	/**
	 * @param repaymentPeriod the repaymentPeriod to set
	 */
	public void setRepaymentPeriod(String repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}
	/**
	 * @return the totalPeriod
	 */
	public String getTotalPeriod() {
		return totalPeriod;
	}
	/**
	 * @param totalPeriod the totalPeriod to set
	 */
	public void setTotalPeriod(String totalPeriod) {
		this.totalPeriod = totalPeriod;
	}

	/**
	 * @return the repayType
	 */
	public String getRepayType() {
		return repayType;
	}
	/**
	 * @param repayType the repayType to set
	 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getOverTime() {
		return overTime;
	}
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	@Override
	public String toString() {
		return "CapitalVo [userMobile=" + userMobile + ", transactionTime=" + transactionTime + ", transactionAmot="
				+ transactionAmot + ", loanNumber=" + loanNumber + ", repaymentPeriod=" + repaymentPeriod
				+ ", totalPeriod=" + totalPeriod + ", repayType=" + repayType + ", tradeNo=" + tradeNo + ", beginTime="
				+ beginTime + ", overTime=" + overTime  + "]";
	}

}
