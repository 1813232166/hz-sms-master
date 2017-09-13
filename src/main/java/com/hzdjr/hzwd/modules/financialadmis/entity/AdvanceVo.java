package com.hzdjr.hzwd.modules.financialadmis.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

public class AdvanceVo extends  DataEntity<AdvanceVo>{

	private static final long serialVersionUID = -4270076169394718014L;
	/**
	 * 垫付时间，垫付金额（元），借款编号，期数，平台交易流水号
	 */
	private Date advanceTime; //垫付交易时间
	private BigDecimal advanceAmount;//垫付金额（元）
	private String borrowNo;  //借款编号
	private String advancePeriod; // 垫付期数
	private String totalPeriod; // 总期数
	private String tradeNo;  //平台交易流水号

	private String beginTime;
	private String overTime;


	/**
	 * @return the advanceTime
	 */
	@ExcelField(title="垫付时间",type=1,align=1,sort=0)
	public String getAdvanceTime() {
		if(advanceTime!=null){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(advanceTime);
		}
		return "";
	}
	/**
	 * @param advanceTime the advanceTime to set
	 */
	public void setAdvanceTime(Date advanceTime) {
		this.advanceTime = advanceTime;
	}
	/**
	 * @return the advanceAmount
	 */
	@ExcelField(title="垫付金额（元）",type=1,align=3,sort=10)
	public String getAdvanceAmount() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
		if(advanceAmount!=null){
			return df.format(advanceAmount);
		}
		return df.format(new BigDecimal(0.00));
	}
	/**
	 * @param advanceAmount the advanceAmount to set
	 */
	public void setAdvanceAmount(BigDecimal advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	/**
	 * @return the borrowNo
	 */
	@ExcelField(title="借款编号",type=1,align=1,sort=20)
	public String getBorrowNo() {
		return borrowNo;
	}
	/**
	 * @param borrowNo the borrowNo to set
	 */
	public void setBorrowNo(String borrowNo) {
		this.borrowNo = borrowNo;
	}
	/**
	 * @return the eturnPeriod
	 */
	public String getAdvancePeriod() {
		return advancePeriod;
	}
	/**
	 * @param eturnPeriod the eturnPeriod to set
	 */
	public void setAdvancePeriod(String advancePeriod) {
		this.advancePeriod = advancePeriod;
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
	@ExcelField(title="期数",type=1,align=1,sort=30)
	public String getShowPeriod() {
		String period = "";
		if (StringUtils.isNotBlank(advancePeriod) || StringUtils.isNotBlank(totalPeriod)) {
			period=StringUtils.trimToEmpty(advancePeriod) + "/" + StringUtils.trimToEmpty(totalPeriod);
		}
		return period;
	}
	/**
	 * @return the tradeNo
	 */
	@ExcelField(title="平台交易流水号",type=1,align=1,sort=30)
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
	public AdvanceVo(){
    	super();
	}
    public AdvanceVo(String id){
    	super(id);
	}

}
