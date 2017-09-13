package com.hzdjr.hzwd.modules.usercount.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

public class LendPlan {
	private Integer num;
	private Date times;
	private String productnum;
	private String producttype;
	private String productname;
	private BigDecimal amountsum;
	private Integer deadline;
	private BigDecimal anualrate;
	
	@ExcelField(title="序号",type=1,align=2,sort=0,fieldType=Integer.class)
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
	@ExcelField(title="理财标号",type=1,align=2,sort=20)
	public String getProductnum() {
		return productnum;
	}
	public void setProductnum(String productnum) {
		this.productnum = productnum;
	}
	@ExcelField(title="理财产品类型",type=1,align=2,sort=30)
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	@ExcelField(title="理财产品名称",type=1,align=2,sort=40)
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	@ExcelField(title="发布总额(元)",type=1,align=2,sort=50)
	public BigDecimal getAmountsum() {
		return amountsum;
	}
	public void setAmountsum(BigDecimal amountsum) {
		this.amountsum = amountsum;
	}
	@ExcelField(title="封闭期限(月)",type=1,align=2,sort=60)
	public String getDeadline() {
		return deadline+"";
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
	@ExcelField(title="年利率",type=1,align=2,sort=70)
	public String getAnualrate() {
		return anualrate+"%";
	}
	public void setAnualrate(BigDecimal anualrate) {
		this.anualrate = anualrate;
	}
	
	

}
