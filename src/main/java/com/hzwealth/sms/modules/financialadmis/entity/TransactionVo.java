package com.hzwealth.sms.modules.financialadmis.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

public class TransactionVo extends  DataEntity<TransactionVo>{

	private static final long serialVersionUID = -4270076169394718014L;
	/**
	 * 平台交易流水号，账户，交易类型，交易时间，状态
	 */
	private String tradeNo;  //平台交易流水号
	private String accCode;  //交易账户
	private String tradeType;  //交易类型
	private Date tradeTime; //交易时间
	private String tradeStatus;  //交易状态

	private String beginTime;
	private String overTime;

    /**
	 * @return the tradeNo
	 */
	@ExcelField(title="平台交易流水号",type=1,align=1,sort=1)
	public String getTradeNo() {
		return tradeNo;
	}
	/**
	 * @param tradeNo the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * @return the accCode
	 */
	public String getAccCode() {
		return accCode;
	}
	/**
	 * @param accCode the accCode to set
	 */
	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}
	/**
	 * @return the tradeType
	 */
	public String getTradeType() {
		return tradeType;
	}
	/**
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * @return the showAccCode
	 */
	@ExcelField(title="账户",type=1,align=1,sort=2)
	public String getShowAccCode() {
		String showAcc = ShowSysAccount.getShowNameByCode(accCode);
		if (StringUtils.isBlank(showAcc)) {
			return accCode;
		}
		return showAcc;
	}
	/**
	 * @return the showTradeType
	 */
	@ExcelField(title="交易类型",type=1,align=1,sort=3)
	public String getShowTradeType() {
		return ShowTradeType.getNameByCode(tradeType);
	}
	/**
	 * @return the showTradeStatus
	 */
	@ExcelField(title="状态",type=1,align=1,sort=5)
	public String getShowTradeStatus() {
		return ShowTransactionStatus.getShowNameByCode(tradeStatus);
	}
	/**
	 * @return the tradeTime
	 */
	@ExcelField(title="交易时间",type=1,align=1,sort=4)
	public String getTradeTime() {
		if(tradeTime!=null){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tradeTime);
		}
		return "";
	}
	/**
	 * @param tradeTime the tradeTime to set
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	/**
	 * @return the tradeStatus
	 */
	public String getTradeStatus() {
		return tradeStatus;
	}
	/**
	 * @param tradeStatus the tradeStatus to set
	 */
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	/**
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}
	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * @return the overTime
	 */
	public String getOverTime() {
		return overTime;
	}
	/**
	 * @param overTime the overTime to set
	 */
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	public TransactionVo(){
    	super();
	}
    public TransactionVo(String id){
    	super(id);
	}

}
