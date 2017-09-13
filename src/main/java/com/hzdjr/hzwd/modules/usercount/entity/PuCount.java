package com.hzdjr.hzwd.modules.usercount.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

public class PuCount {
	private Integer num;
	private Date times;
	private String biaonum;
	private String biaoname;
	private String borrownum;
	private BigDecimal borrowamount;
	private String borrowtype;
	private Integer deadline;
	private BigDecimal anualrate;
	
	@ExcelField(title="序号",type=1,align=2,sort=0)
	public String getNum() {
		return num+"";
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@ExcelField(title="发标时间",type=1,align=2,sort=10)
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	@ExcelField(title="标的编号",type=1,align=2,sort=20)
	public String getBiaonum() {
		return biaonum;
	}
	public void setBiaonum(String biaonum) {
		this.biaonum = biaonum;
	}
	@ExcelField(title="标的名称",type=1,align=2,sort=30)
	public String getBiaoname() {
		return biaoname;
	}
	public void setBiaoname(String biaoname) {
		this.biaoname = biaoname;
	}
	@ExcelField(title="借款编号",type=1,align=2,sort=40)
	public String getBorrownum() {
		return borrownum;
	}
	public void setBorrownum(String borrownum) {
		this.borrownum = borrownum;
	}
	@ExcelField(title="借款金额（元）",type=1,align=2,sort=50)
	public BigDecimal getBorrowamount() {
		return borrowamount;
	}
	public void setBorrowamount(BigDecimal borrowamount) {
		this.borrowamount = borrowamount;
	}
	@ExcelField(title="借款类型",type=1,align=2,sort=60)
	public String getBorrowtype() {
		return borrowtype;
	}
	public void setBorrowtype(String borrowtype) {
		this.borrowtype = borrowtype;
	}
	@ExcelField(title="借款期限（月）",type=1,align=2,sort=70)
	public String getDeadline() {
		return deadline+"";
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
	@ExcelField(title="年利率",type=1,align=2,sort=80)
	public String getAnualrate() {
		return anualrate+"%";
	}
	public void setAnualrate(BigDecimal anualrate) {
		this.anualrate = anualrate;
	}
	
	

}
