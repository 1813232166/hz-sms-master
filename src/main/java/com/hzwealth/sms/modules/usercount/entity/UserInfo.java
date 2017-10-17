package com.hzwealth.sms.modules.usercount.entity;

import java.util.Date;

import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

public class UserInfo {
	private Integer num;
	private Date times;
	private Integer currentreg;
	private Integer newuser;
	private Integer investuser;
	private Integer borrowuser;
	private Integer loginuser;
	private Integer pcreguser;
	private Integer appreguser;
	@ExcelField(title="序号",type=1,align=2,sort=0,fieldType=Integer.class)
	public String getNum() {
		return num+"";
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	@ExcelField(title="时间",type=1,align=2,sort=10)
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	@ExcelField(title="当日注册（个）",type=1,align=2,sort=20,fieldType=Integer.class)
	public String getCurrentreg() {
		if(currentreg==null){
			currentreg=0;
		}
		return currentreg+"";
	}
	public void setCurrentreg(Integer currentreg) {
		this.currentreg = currentreg;
	}
	@ExcelField(title="新增充值用户（个）",type=1,align=2,sort=30,fieldType=Integer.class)
	public String getNewuser() {
		if(newuser==null){
			newuser=0;
		}
		return newuser+"";
	}
	public void setNewuser(Integer newuser) {
		this.newuser = newuser;
	}
	@ExcelField(title="投资用户（个）",type=1,align=2,sort=40,fieldType=Integer.class)
	public String getInvestuser() {
		if(investuser==null){
			investuser=0;
		}
		return investuser+"";
	}
	public void setInvestuser(Integer investuser) {
		this.investuser = investuser;
	}
	@ExcelField(title="借款意向用户（个）",type=1,align=2,sort=50,fieldType=Integer.class)
	public String getBorrowuser() {
		if(borrowuser==null){
			borrowuser=0;
		}
		return borrowuser+"";
	}
	public void setBorrowuser(Integer borrowuser) {
		this.borrowuser = borrowuser;
	}
	@ExcelField(title="登录用户（个）",type=1,align=2,sort=60,fieldType=Integer.class)
	public String getLoginuser() {
		if(loginuser==null){
			loginuser=0;
		}
		return loginuser+"";
	}
	public void setLoginuser(Integer loginuser) {
		this.loginuser = loginuser;
	}
	@ExcelField(title="PC端注册量（个）",type=1,align=2,sort=70,fieldType=Integer.class)
	public String getPcreguser() {
		if(pcreguser==null){
			pcreguser=0;
		}
		return pcreguser+"";
	}
	public void setPcreguser(Integer pcreguser) {
		this.pcreguser = pcreguser;
	}
	@ExcelField(title="APP注册量（个）",type=1,align=2,sort=80)
	public String getAppreguser() {
		if(appreguser==null){
			appreguser=0;
		}
		return appreguser+"";
	}
	public void setAppreguser(Integer appreguser) {
		this.appreguser = appreguser;
	}

	
}
