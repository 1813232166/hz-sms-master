package com.hzwealth.sms.modules.borrow.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

public class BorrowApplyNew { 
	private String borrowId;
	private String loanNumber;
	private String borrowCode;
	private String name;
	private String borrowamount;
	private BigDecimal anualrate;
	private String deadline;
	private String payMethod;
	private String borrowtype;
	private Date lendingtime;
	private String isMatch;
	private String isEdit;
	private String borrowStatus;
	private String mobile;
	private String userId;
	private String borrowAliasNo;   //标的中文别名编号
	
	
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	@ExcelField(title="借款编号",type=1,align=2,sort=10)
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	@ExcelField(title="申请编号",type=1,align=2,sort=20)
	public String getBorrowCode() {
		return borrowCode;
	}
	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}
	@ExcelField(title="借款人",type=1,align=2,sort=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title="借款金额（元）",type=1,align=2,sort=50)
	public String getBorrowamount() {
		return borrowamount;
	}
	public void setBorrowamount(String borrowamount) {
		this.borrowamount = borrowamount;
	}
	@ExcelField(title="借款利率",type=1,align=2,sort=60)
	public String getAnualrate() {
		return anualrate+"%";
	}
	public void setAnualrate(BigDecimal anualrate) {
		this.anualrate = anualrate;
	}
	@ExcelField(title="借款期限（月）",type=1,align=2,sort=70)
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	@ExcelField(title="还款方式",type=1,align=2,sort=80)
	public String getPayMethod() {
		if("debx".equals(payMethod)){
			return "等额本息";
		}else if("xxhb".equals(payMethod)){
			return "先息后本";
		}else if("ychbx".equals(payMethod)){
			return "一次性还本付息";
		}else if("".equals(payMethod)){
			return "";
		}
		return "等额本息";
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	@ExcelField(title="借款形式",type=1,align=2,sort=90)
	public String getBorrowtype() {
		if("xy".equals(borrowtype)){
			return "信用借款";
		}else if("fd".equals(borrowtype)){
			return "房贷借款";
		}else if("cd".equals(borrowtype)){
			return "车贷借款";
		}
		return "";
	}
	public void setBorrowtype(String borrowtype) {
		this.borrowtype = borrowtype;
	}
	@ExcelField(title="批贷时间",type=1,align=2,sort=100)
	public String getLendingtime() {
		if(lendingtime!=null){
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lendingtime);
			return date;
		}
		else{
			return "";
		}
	}
	public void setLendingtime(Date lendingtime) {
		this.lendingtime = lendingtime;
	}
	@ExcelField(title="匹配状态",type=1,align=2,sort=110)
	public String getIsMatch() {
		if("0".equals(borrowStatus) ||"20".equals(borrowStatus)){
			return "未匹配";
		}else if("3".equals(borrowStatus) ||"4".equals(borrowStatus)||"7".equals(borrowStatus) ||"8".equals(borrowStatus)||"11".equals(borrowStatus)||"19".equals(borrowStatus)){
			return "已匹配";
		}else if((loanNumber!=null && borrowAliasNo == null && "12".equals(borrowStatus)) || ("12".equals(borrowStatus) && borrowAliasNo != null)){
			return "已撤销";
		}else if("9".equals(borrowStatus)){
			return "已流标";
		}
		return "";
	}
	public void setIsMatch(String isMatch) {
		this.isMatch = isMatch;
	}
	@ExcelField(title="编辑状态",type=1,align=2,sort=120)
	public String getIsEdit() {
		if("0".equals(borrowStatus)){
			return "未编辑";
		}else if("3".equals(borrowStatus) ||"4".equals(borrowStatus)||"7".equals(borrowStatus) ||"8".equals(borrowStatus)|| "11".equals(borrowStatus) || "10".equals(borrowStatus) ||"19".equals(borrowStatus)|| "20".equals(borrowStatus)){
			return "已编辑";
		}else if((loanNumber!=null && borrowAliasNo == null && "12".equals(borrowStatus)) || ("12".equals(borrowStatus) && borrowAliasNo != null)){
			return "--";
		}else if("9".equals(borrowStatus)){
			return "--";
		}
		return "";
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
	public String getBorrowStatus() {
		return borrowStatus;
	}
	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}
	@ExcelField(title="手机号",type=1,align=2,sort=40)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBorrowAliasNo() {
		return borrowAliasNo;
	}
	public void setBorrowAliasNo(String borrowAliasNo) {
		this.borrowAliasNo = borrowAliasNo;
	}

	
	
}
