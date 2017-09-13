package com.hzdjr.hzwd.modules.usercount.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

public class Withdraw {
	private Integer num;
	private Date times;
	private BigDecimal withdrawamount;
	private BigDecimal alreadyload;
	private BigDecimal pendingload;
	private Integer withdrawnum;
	private BigDecimal pcwithdraw;
	private BigDecimal appwithdraw;
	
	@ExcelField(title="序号",type=1,align=2,sort=0,fieldType=Integer.class)
	public String getNum() {
		return num+"";
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@ExcelField(title="申请提现时间",type=1,align=2,sort=10)
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	@ExcelField(title="当日提现总金额（元）",type=1,align=2,sort=20)
	public BigDecimal getWithdrawamount() {
		return withdrawamount;
	}
	public void setWithdrawamount(BigDecimal withdrawamount) {
		this.withdrawamount = withdrawamount;
	}
	
	@ExcelField(title="已放款（元）",type=1,align=2,sort=30)
	public BigDecimal getAlreadyload() {
		return alreadyload;
	}
	public void setAlreadyload(BigDecimal alreadyload) {
		this.alreadyload = alreadyload;
	}
	@ExcelField(title="待放款（元）",type=1,align=2,sort=40)
	public BigDecimal getPendingload() {
		return pendingload;
	}
	public void setPendingload(BigDecimal pendingload) {
		this.pendingload = pendingload;
	}
	@ExcelField(title="提现用户数（个）",type=1,align=2,sort=50)
	public String getWithdrawnum() {
		return withdrawnum+"";
	}
	public void setWithdrawnum(Integer withdrawnum) {
		this.withdrawnum = withdrawnum;
	}
	@ExcelField(title="PC端提现（元）",type=1,align=2,sort=60)
	public BigDecimal getPcwithdraw() {
		return pcwithdraw;
	}
	public void setPcwithdraw(BigDecimal pcwithdraw) {
		this.pcwithdraw = pcwithdraw;
	}
	@ExcelField(title="APP端提现（元）",type=1,align=2,sort=70)
	public BigDecimal getAppwithdraw() {
		return appwithdraw;
	}
	public void setAppwithdraw(BigDecimal appwithdraw) {
		this.appwithdraw = appwithdraw;
	}
	
}
