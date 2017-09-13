package com.hzdjr.hzwd.modules.fk.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

public class Fkuan {

	private String biaoid;    //标的编号
	private String biaoname;        //标的名称
	private String borrowerNumber;   //借款编号
	private String borrowerName;      //借款人名字
	private String borrowerPhone;    //借款人手机号
	private BigDecimal  cash;   //借款金额   
	private BigDecimal reate;    //利率
	private Integer dedaline; //出借期限
	private String type;   //还款方式
	private Date lendingTime;   // 放款时间
	private Date fullBorrowDate;
	
	private BigDecimal sumCount;  //借款总金额
	private String borrowerId;
	private String failelend;
	private String borrowCode;
	private String status;
	
	private String operatorUser;  //操作人
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBorrowCode() {
		return borrowCode;
	}
	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="满标时间",type=1,align=2,sort=100,groups={1})
	public String getFullBorrowDate() {
		if(fullBorrowDate!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fullBorrowDate);
			return str;
		}else{
			return "";
		}
	}
	public void setFullBorrowDate(Date fullBorrowDate) {
		this.fullBorrowDate = fullBorrowDate;
	}
	public String getFailelend() {
		return failelend;
	}
	public void setFailelend(String failelend) {
		this.failelend = failelend;
	}
	@ExcelField(title="标的编号",type=1,align=2,sort=10,groups={1,2,4})
	public String getBiaoid() {
		return biaoid;
	}
	public void setBiaoid(String biaoid) {
		this.biaoid = biaoid;
	}
	@ExcelField(title="标的名称",type=1,align=2,sort=20,groups={1,2,4})
	public String getBiaoname() {
		return biaoname;
	}
	public void setBiaoname(String biaoname) {
		this.biaoname = biaoname;
	}
	@ExcelField(title="借款编号",type=1,align=2,sort=30,groups={1,2,4})
	public String getBorrowerNumber() {
		return borrowerNumber;
	}
	public void setBorrowerNumber(String borrowerNumber) {
		this.borrowerNumber = borrowerNumber;
	}
	@ExcelField(title="借款人",type=1,align=2,sort=40,groups={1,2,4})
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	@ExcelField(title="借款人手机号",type=1,align=2,sort=50,groups={1,2,4})
	public String getBorrowerPhone() {
		return borrowerPhone;
	}
	public void setBorrowerPhone(String borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}
	@ExcelField(title="借款金额(元)",type=1,align=2,sort=60,groups={1,2,4})
	public String getCash() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
	    return df.format(cash);
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	@ExcelField(title="出借年利化率",type=1,align=2,sort=70,groups={1,2,4})
	public BigDecimal getReate() {
		return reate;
	}
	public void setReate(BigDecimal reate) {
		this.reate = reate;
	}
	@ExcelField(title="出借期限",type=1,align=2,sort=80,groups={1,2,4})
	public Integer getDedaline() {
		return dedaline;
	}
	public void setDedaline(Integer dedaline) {
		this.dedaline = dedaline;
	}
	@ExcelField(title="还款方式",type=1,align=2,sort=90,groups={1,2,4})
	public String getType() {
		if(type!=null && !("").equals(type)){
			if(type.equals("debx")){
				return "等额本息";
			}else if(type.equals("xxhb")){
				return "先息后本";
			}else if(type.equals("ychbx")){
				return "一次性还本付息";
			}
		}
		return "";
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="放款时间",type=1,align=2,sort=100,groups={2,4})
	public String getLendingTime() {
		if(lendingTime!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lendingTime);
			return str;
		}else{
			return "";
		}
	}
	public void setLendingTime(Date lendingTime) {
		this.lendingTime = lendingTime;
	}
	@Override
	public String toString() {
		return "Fkuan [biaoid=" + biaoid + ", biaoname=" + biaoname + ", borrowerNumber=" + borrowerNumber
				+ ", borrowerName=" + borrowerName + ", borrowerPhone=" + borrowerPhone + ", cash=" + cash + ", reate="
				+ reate + ", dedaline=" + dedaline + ", borrowCode=" + borrowCode + ", type=" + type + ", status=" + status + ",lendingTime=" + lendingTime + "]";
	}
	public BigDecimal getSumCount() {
		return sumCount;
	}
	public void setSumCount(BigDecimal sumCount) {
		this.sumCount = sumCount;
	}
	public String getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	public String getOperatorUser() {
		return operatorUser;
	}
	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}
}
