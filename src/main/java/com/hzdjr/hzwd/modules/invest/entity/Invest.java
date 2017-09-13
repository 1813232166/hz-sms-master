/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.invest.entity;

import org.hibernate.validator.constraints.Length;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 出借记录Entity
 * @author yansy
 * @version 2016-10-24
 */
public class Invest extends DataEntity<Invest> {
	
	private static final long serialVersionUID = 1L;
	private String investamount;		// 投资金额
	private String yearrate;		// 年利率
	private String monthrate;		// 月利率
	private String investor;		// 投资人Id
	private String borrowid;		// 借款ID
	private Date investtime;		// 投资时间
	private Date arritradetime;		// 到账时间
	private String oriinvestor;		// 原始投资人
	private String realamount;		// 实际投资金额
	private String haspi;		// 已收本息
	private String deadline;		// 期数
	private String hasdeadline;		// 已还款期数
	private String recivedprincipal;		// 应收本金
	private String recievedinterest;		// 应收利息
	private String hasprincipal;		// 已收本金
	private String hasinterest;		// 已收利息
	private String recivedfi;		// 应收罚金
	private String hasfi;		// 已收罚金
	private String managefee;		// 管理费
	private String reward;		// 奖励
	private String flag;		// 标识(0-预约;1-成功;2-失败;)
	private String offlineflag;		// 线下状态(0-未通知;1-通知成功;2-转线下)
	private String interestflag;		// 计息状态(0-未计息;1-已计息)
	private String channel;		// 投资渠道
	private String isautobid;		// 自动投标( 默认 1 手动 2 自动)
	private String isdebt;		// 是否转让(1,没有转让，2转让)
	private String investtype;		// 投资类型0 理财1 散标
	private String investcode;		// 投资编号
	private String bizid;		// 交易流水号
	private Date interestaccrualdate;		// 计息日期
	private Date expiredate;		// 到期日期
	private String refferee;		// 推荐人
	private String serverip;		// 服务器IP地址
	private String departmentinfo;		// 营业部名称
	private Date beginInvesttime;		// 开始 投资时间
	private Date endInvesttime;		// 结束 投资时间
	private String borrowCode;		//标的编号
	private String borrowAliasNo;		//标的编号
	private String borrowAlias;		//标的名称
	private String realName;		//出借人
	private String jrealName;		//借款人
	private String payMethod;		//还款方式
	private String anualRate;		//出借年化率
	private String loanNumber;		//借款编号
	private String isAutoBid;		//出借类型
	private String borrowStatus;	//借款状态
	private String coupon_log_id;	//优惠券ID
	
	public String getCoupon_log_id() {
		return coupon_log_id;
	}

	public void setCoupon_log_id(String coupon_log_id) {
		this.coupon_log_id = coupon_log_id;
	}

	public Invest() {
		super();
	}

	public Invest(String id){
		super(id);
	}

	public String getBorrowCode() {
		return borrowCode;
	}

	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}

	public String getBorrowAlias() {
		return borrowAlias;
	}

	public void setBorrowAlias(String borrowAlias) {
		this.borrowAlias = borrowAlias;
	}

	public String getBorrowAliasNo() {
		return borrowAliasNo;
	}

	public void setBorrowAliasNo(String borrowAliasNo) {
		this.borrowAliasNo = borrowAliasNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getInvestamount() {
		return investamount;
	}

	public void setInvestamount(String investamount) {
		this.investamount = investamount;
	}
	
	public String getYearrate() {
		return yearrate;
	}

	public void setYearrate(String yearrate) {
		this.yearrate = yearrate;
	}
	
	public String getMonthrate() {
		return monthrate;
	}

	public void setMonthrate(String monthrate) {
		this.monthrate = monthrate;
	}
	
	@Length(min=0, max=32, message="投资人Id长度必须介于 0 和 32 之间")
	public String getInvestor() {
		return investor;
	}

	public void setInvestor(String investor) {
		this.investor = investor;
	}
	
	@Length(min=0, max=32, message="借款ID长度必须介于 0 和 32 之间")
	public String getBorrowid() {
		return borrowid;
	}

	public void setBorrowid(String borrowid) {
		this.borrowid = borrowid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInvesttime() throws ParseException {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(investtime!=null){
			String investS = sim.format(investtime);
			investtime = sim.parse(investS);
		}
		return investtime;
	}

	public void setInvesttime(Date investtime) {
		this.investtime = investtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArritradetime() {
		return arritradetime;
	}

	public void setArritradetime(Date arritradetime) {
		this.arritradetime = arritradetime;
	}
	
	@Length(min=0, max=32, message="原始投资人长度必须介于 0 和 32 之间")
	public String getOriinvestor() {
		return oriinvestor;
	}

	public void setOriinvestor(String oriinvestor) {
		this.oriinvestor = oriinvestor;
	}
	
	public String getRealamount() {
		return realamount;
	}

	public void setRealamount(String realamount) {
		this.realamount = realamount;
	}
	
	public String getHaspi() {
		return haspi;
	}

	public void setHaspi(String haspi) {
		this.haspi = haspi;
	}
	
	@Length(min=0, max=11, message="期数长度必须介于 0 和 11 之间")
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	@Length(min=0, max=11, message="已还款期数长度必须介于 0 和 11 之间")
	public String getHasdeadline() {
		return hasdeadline;
	}

	public void setHasdeadline(String hasdeadline) {
		this.hasdeadline = hasdeadline;
	}
	
	public String getRecivedprincipal() {
		return recivedprincipal;
	}

	public void setRecivedprincipal(String recivedprincipal) {
		this.recivedprincipal = recivedprincipal;
	}
	
	public String getRecievedinterest() {
		return recievedinterest;
	}

	public void setRecievedinterest(String recievedinterest) {
		this.recievedinterest = recievedinterest;
	}
	
	public String getHasprincipal() {
		return hasprincipal;
	}

	public void setHasprincipal(String hasprincipal) {
		this.hasprincipal = hasprincipal;
	}
	
	public String getHasinterest() {
		return hasinterest;
	}

	public void setHasinterest(String hasinterest) {
		this.hasinterest = hasinterest;
	}
	
	public String getRecivedfi() {
		return recivedfi;
	}

	public void setRecivedfi(String recivedfi) {
		this.recivedfi = recivedfi;
	}
	
	public String getHasfi() {
		return hasfi;
	}

	public void setHasfi(String hasfi) {
		this.hasfi = hasfi;
	}
	
	public String getManagefee() {
		return managefee;
	}

	public void setManagefee(String managefee) {
		this.managefee = managefee;
	}
	
	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}
	
	@Length(min=0, max=2, message="标识(0-预约;1-成功;2-失败;)长度必须介于 0 和 2 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Length(min=0, max=2, message="线下状态(0-未通知;1-通知成功;2-转线下)长度必须介于 0 和 2 之间")
	public String getOfflineflag() {
		return offlineflag;
	}

	public void setOfflineflag(String offlineflag) {
		this.offlineflag = offlineflag;
	}
	
	@Length(min=0, max=2, message="计息状态(0-未计息;1-已计息)长度必须介于 0 和 2 之间")
	public String getInterestflag() {
		return interestflag;
	}

	public void setInterestflag(String interestflag) {
		this.interestflag = interestflag;
	}
	
	@Length(min=0, max=10, message="投资渠道长度必须介于 0 和 10 之间")
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	@Length(min=0, max=11, message="自动投标( 默认 1 手动 2 自动)长度必须介于 0 和 11 之间")
	public String getIsautobid() {
		return isautobid;
	}

	public void setIsautobid(String isautobid) {
		this.isautobid = isautobid;
	}
	
	@Length(min=0, max=11, message="是否转让(1,没有转让，2转让)长度必须介于 0 和 11 之间")
	public String getIsdebt() {
		return isdebt;
	}

	public void setIsdebt(String isdebt) {
		this.isdebt = isdebt;
	}
	
	@Length(min=0, max=2, message="投资类型0 理财1 散标长度必须介于 0 和 2 之间")
	public String getInvesttype() {
		return investtype;
	}

	public void setInvesttype(String investtype) {
		this.investtype = investtype;
	}
	
	@Length(min=0, max=30, message="投资编号长度必须介于 0 和 30 之间")
	public String getInvestcode() {
		return investcode;
	}

	public void setInvestcode(String investcode) {
		this.investcode = investcode;
	}
	
	@Length(min=0, max=30, message="交易流水号长度必须介于 0 和 30 之间")
	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInterestaccrualdate() {
		return interestaccrualdate;
	}

	public void setInterestaccrualdate(Date interestaccrualdate) {
		this.interestaccrualdate = interestaccrualdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(Date expiredate) {
		this.expiredate = expiredate;
	}
	
	@Length(min=0, max=50, message="推荐人长度必须介于 0 和 50 之间")
	public String getRefferee() {
		return refferee;
	}

	public void setRefferee(String refferee) {
		this.refferee = refferee;
	}
	
	@Length(min=0, max=50, message="服务器IP地址长度必须介于 0 和 50 之间")
	public String getServerip() {
		return serverip;
	}

	public void setServerip(String serverip) {
		this.serverip = serverip;
	}
	
	@Length(min=0, max=50, message="营业部名称长度必须介于 0 和 50 之间")
	public String getDepartmentinfo() {
		return departmentinfo;
	}

	public void setDepartmentinfo(String departmentinfo) {
		this.departmentinfo = departmentinfo;
	}
	
	public Date getBeginInvesttime() {
		return beginInvesttime;
	}

	public void setBeginInvesttime(Date beginInvesttime) {
		this.beginInvesttime = beginInvesttime;
	}
	
	public Date getEndInvesttime() {
		return endInvesttime;
	}

	public void setEndInvesttime(Date endInvesttime) {
		this.endInvesttime = endInvesttime;
	}

	public String getJrealName() {
		return jrealName;
	}

	public void setJrealName(String jrealName) {
		this.jrealName = jrealName;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getAnualRate() {
		return anualRate;
	}

	public void setAnualRate(String anualRate) {
		this.anualRate = anualRate;
	}
	
	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	public String getIsAutoBid() {
		return isAutoBid;
	}

	public void setIsAutoBid(String isAutoBid) {
		this.isAutoBid = isAutoBid;
	}
		
	
}