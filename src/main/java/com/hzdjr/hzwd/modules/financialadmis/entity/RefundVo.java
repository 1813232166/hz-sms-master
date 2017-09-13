package com.hzdjr.hzwd.modules.financialadmis.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;
/**
 * 还款VO
 * @author xq
 *
 */
public class RefundVo extends DataEntity<RefundVo>{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户手机号、还款时间、期数、还款类型、还款金额（元）、借款编号、平台交易流水号
	 */

	private String userMobile;
	private Date repayTime;
	private String repaymentPeriod; // 还款期数
	private String totalPeriod; // 总期数
	private String repayType; // 还款类型
	private BigDecimal repayAmot;
	private String loanNumber;
	private String tradeNo;

	private String beginTime;
	private String overTime;

	private String borrowTitile;   //项目名称
	private String mobile;      //出借人手机号

	public RefundVo() {
		super();
	}
	public RefundVo(String id) {
		super(id);
	}

	@ExcelField(title="用户手机号",type=1,align=2,sort=0)
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	@ExcelField(title="还款金额（元）",type=1,align=2,sort=30)
	public  String getRepayAmot() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
		if(repayAmot!=null){
			return df.format(repayAmot);
		}else{
			return df.format(new BigDecimal(0.00));
		}
	}
	public void setRepayAmot(BigDecimal repayAmot) {
		this.repayAmot = repayAmot;
	}

	@ExcelField(title="还款时间",type=1,align=2,sort=10)
	public String getRepayTime() {
		if(repayTime!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(repayTime);
			return str;
		}else{
			return "";
		}
	}
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

	@ExcelField(title="平台交易流水号",type=1,align=2,sort=70)
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
	 * @return the showPeriod
	 */
	@ExcelField(title="期数",type=1,align=1,sort=50)
	public String getShowPeriod() {
		return repaymentPeriod + "/" + totalPeriod;
	}
	/**
	 * @return the showRepayType
	 */
	@ExcelField(title="还款类型",type=1,align=1,sort=60)
	public String getShowRepayType() {
		String showtype = "逾期还款";
		if ("7".equals(repayType)) { // 正常还款
			showtype = "正常还款";
		}
		return showtype;
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
	@ExcelField(title="借款编号",type=1,align=2,sort=40)
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
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

	public String getBorrowTitile() {
		return borrowTitile;
	}
	public void setBorrowTitile(String borrowTitile) {
		this.borrowTitile = borrowTitile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "RefundVo [userMobile=" + userMobile + ", repayTime=" + repayTime
				+ ", repayAmot=" + repayAmot + ", loanNumber=" + loanNumber + ", tradeNo=" + tradeNo
				+ ", beginTime=" + beginTime + ", overTime=" + overTime
				+ ", borrowTitile=" + borrowTitile + ", mobile=" + mobile + "]";
	}




}
