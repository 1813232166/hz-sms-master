package com.hzdjr.hzwd.modules.financialadmis.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

public class WithdrawVo extends DataEntity<WithdrawVo>{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户手机号、提现时间、完成时间、提现金额（元）、手续费（元）、手续费承担方类型、平台交易流水号
	 */
	private String orderId;    //`orderId` varchar(32) NOT NULL COMMENT '业务编号',
	private String orderType;    //`orderType` char(2) DEFAULT NULL COMMENT '业务类型（01-充值、02-提现）',
	private String userId;    //`userId` varchar(32) NOT NULL COMMENT '用户名',
	private String userRealName;    //`userRealName` varchar(36) DEFAULT NULL COMMENT '用户真实姓名',
	private String userMobile; 		//用户手机号
	private Date createTime;    //`createTime` datetime DEFAULT NULL COMMENT '提现时间',
	private Date endTime;
	private BigDecimal amount;    //`amount` decimal(12,2) DEFAULT '0.00' COMMENT '金额',
	private String status;    //`status` char(1) DEFAULT NULL COMMENT '状态（0-成功、1-新增、2-失败）',
	private String cardNo;    //`cardNo` varchar(32) DEFAULT NULL COMMENT '银行卡号',
	private String bankCode;    //`bankCode` varchar(3) DEFAULT NULL COMMENT '银行代码，3位数字，比如102为工行',
	private String accountBankName;    //`accountBankName` varchar(128) DEFAULT NULL COMMENT '开户行名称',
	private String accountBankId;    //`accountBankId` varchar(12) DEFAULT NULL COMMENT '开户行行号',
	private String accountId;    //`accountId` varchar(32) DEFAULT NULL COMMENT '用户支付号',
	private String companyId;    //`companyId` varchar(32) DEFAULT NULL COMMENT '商户号',
	private BigDecimal fee;    //`fee` decimal(12,2) DEFAULT '0.00' COMMENT '手续费',
	private String feesBearer;  //手续费承担方
	private String tradeNo;  //平台交易流水号
	private BigDecimal ramount;    //`ramount` decimal(12,2) DEFAULT '0.00' COMMENT '实际到账金额',
	private String apprState;    //`apprState` varchar(2) DEFAULTNULL COMMENT '审批状态（提现时需要注意此状态：00-新增、01-初审通过、02-初审退回、[03-复审通过、04-复审拒绝(这两种状态没有，此处标记)]、05-打款成功、06-打款失败）',
	private Date beginTime;
	private Date overTime;
	private Date finBeginTime;
	private Date finEndTime;
	public WithdrawVo() {
		super();
	}
	public WithdrawVo(String id) {
		super(id);
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	@ExcelField(title="用户手机号",type=1,align=2,sort=0)
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@ExcelField(title="提现金额",type=1,align=2,sort=30)
	public String getAmount() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
	    return df.format(amount);
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccountBankName() {
		return accountBankName;
	}
	public void setAccountBankName(String accountBankName) {
		this.accountBankName = accountBankName;
	}
	public String getAccountBankId() {
		return accountBankId;
	}
	public void setAccountBankId(String accountBankId) {
		this.accountBankId = accountBankId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	@ExcelField(title="手续费（元）",type=1,align=2,sort=40)
	public BigDecimal getFee() {
	    if (null==fee) {
            return new BigDecimal(0.00);
        }
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	@ExcelField(title="手续费承担方类型",type=1,align=2,sort=50)
	public String getFeesBearer() {
	    if (null ==feesBearer || "".equals(feesBearer)) {
	        return "交易方承担";
        }
	    return feesBearer;
	}
	public void setFeesBearer(String feesBearer) {
		this.feesBearer = feesBearer;
	}
	@ExcelField(title="平台流水号",type=1,align=2,sort=60)
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public BigDecimal getRamount() {
		return ramount;
	}
	public void setRamount(BigDecimal ramount) {
		this.ramount = ramount;
	}
	public String getApprState() {
		return apprState;
	}
	public void setApprState(String apprState) {
		this.apprState = apprState;
	}
	
	@ExcelField(title="提现时间",type=1,align=2,sort=10)
	public String getBeginTime() {
		if(beginTime!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beginTime);
			return str;
		}else{
			return "";
		}
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	//@ExcelField(title="完成时间",type=1,align=2,sort=20)
	public String getOverTime() {
		if(overTime!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(overTime);
			return str;
		}else{
			return "";
		}
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
	

}
