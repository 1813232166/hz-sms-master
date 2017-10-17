package com.hzwealth.sms.modules.financialadmis.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;
/**
 * 投标VO
 * @author xq
 *
 */
public class TenderVo extends DataEntity<TenderVo>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户手机号、投标时间、完成时间、投标金额（元）、借款编号、平台交易流水号
	 */
	private String userMobile;
	private Date tenderTime;
	private Date tenderEndTime;
	private BigDecimal tenderAmot;
	private String loanNumber;
	private String tradeNo;
	
	private Date beginTime;
	private Date overTime;
	private Date finBeginTime;
	private Date finEndTime;
	
	private String borrowerTitle;  //项目名称
	private String mobile;   //借款人手机号
	
	
	
	public TenderVo() {
		super();
	}
	public TenderVo(String id) {
		super(id);
	}
	
	@ExcelField(title="用户手机号",type=1,align=2,sort=0)
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	@ExcelField(title="出借时间",type=1,align=2,sort=10)
	public String getTenderTime() {
		if(tenderTime!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tenderTime);
			return str;
		}else{
			return "";
		}
	}
	public void setTenderTime(Date tenderTime) {
		this.tenderTime = tenderTime;
	}
	@ExcelField(title="完成时间",type=1,align=2,sort=20)
	public String getTenderEndTime() {
		if(tenderEndTime!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tenderEndTime);
			return str;
		}else{
			return "";
		}
	}
	public void setTenderEndTime(Date tenderEndTime) {
		this.tenderEndTime = tenderEndTime;
	}
	@ExcelField(title="出借金额（元）",type=1,align=2,sort=40)
	public String getTenderAmot() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
	    return df.format(tenderAmot);
	}
	public void setTenderAmot(BigDecimal tenderAmot) {
		this.tenderAmot = tenderAmot;
	}
	@ExcelField(title="借款编号",type=1,align=2,sort=50)
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	@ExcelField(title="平台交易流水号",type=1,align=2,sort=60)
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getOverTime() {
		return overTime;
	}
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}
	
	public Date getFinBeginTime() {
		return finBeginTime;
	}
	public void setFinBeginTime(Date finBeginTime) {
		this.finBeginTime = finBeginTime;
	}
	public Date getFinEndTime() {
		return finEndTime;
	}
	public void setFinEndTime(Date finEndTime) {
		this.finEndTime = finEndTime;
	}
	public String getBorrowerTitle() {
		return borrowerTitle;
	}
	public void setBorrowerTitle(String borrowerTitle) {
		this.borrowerTitle = borrowerTitle;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
