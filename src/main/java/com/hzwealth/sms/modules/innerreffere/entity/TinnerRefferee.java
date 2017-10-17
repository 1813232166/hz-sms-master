/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.innerreffere.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;

/**
 * 用户推荐Entity
 * @author ln
 * @version 2016-10-17
 */
public class TinnerRefferee extends DataEntity<TinnerRefferee> {
	
	private static final long serialVersionUID = 1L;
	private String depid;		// 部门ID
	private String username;		// 用户名称
	private String realname;		// 真实姓名
	private String sex;		// 性别（1男2女）
	private Date birthday;		// 出生日期
	private Date regdate;		// 注册时间
	private String mobile;		// 手机号
	private String email;		// 邮箱
	private String logincount;		// 登录次数
	private String refferee;		// 推荐人(也是userid,也可以是线下seller工号)
	private String lastip;		// 最后登录IP
	private Date lastdate;		// 最后登录时间
	private String headimg;		// 头像
	private String lockstatus;		// 锁定状态(1锁定，0未锁定)
	private Date locktime;		// 锁定时间
	private String cashstatus;		// 提现状态
	private String loginerrorcount;		// 错误登录次数
	private String password;		// 密码 MD5加密
	private String isfuyou;		// 是否开通富友(0未开通，1开通)
	private String isloginlimit;		// 是否限制登录 0不限制1限制
	private String usertype;		// 用户类型1借款2投资
	private String regsource;		// 注册来源（web01：  web线上;web02：  web线下;iosApp01： ios线上;iosApp02： ios线下;androidApp01： android线上;androidApp02 ：android线;weixin01 ：     微信线上;weixin02  ：    微信线下）
	private String reffertype;		// 推荐类型  0为互联网用户1内部员工
	private String invitecode;		// 推荐人
	private String customerid;		// crm中的客户编号
	private String investcount;		// 用户投资次数
	private String staffid;		// 员工编号(理财师注册使用)
	private String departmentcode;		// 营业部编码
	private String departmentinfo;		// 员工营业部名称
	private String smsmobile;		// smsmobile
	private Date beginRegdate;		// 开始 注册时间
	private Date endRegdate;		// 结束 注册时间
	private String tRealname;
	private String tStaffId;
	private String tdepartmentinfo;
	private String tid;
	private String tmobile;
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public TinnerRefferee() {
		super();
	}

	public TinnerRefferee(String id){
		super(id);
	}

	@Length(min=0, max=32, message="部门ID长度必须介于 0 和 32 之间")
	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}
	
	@Length(min=0, max=32, message="用户名称长度必须介于 0 和 32 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=32, message="真实姓名长度必须介于 0 和 32 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=10, message="性别（1男2女）长度必须介于 0 和 10 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Length(min=0, max=20, message="手机号长度必须介于 0 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=50, message="邮箱长度必须介于 0 和 50 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=10, message="登录次数长度必须介于 0 和 10 之间")
	public String getLogincount() {
		return logincount;
	}

	public void setLogincount(String logincount) {
		this.logincount = logincount;
	}
	
	@Length(min=0, max=50, message="推荐人(也是userid,也可以是线下seller工号)长度必须介于 0 和 50 之间")
	public String getRefferee() {
		return refferee;
	}

	public void setRefferee(String refferee) {
		this.refferee = refferee;
	}
	
	@Length(min=0, max=50, message="最后登录IP长度必须介于 0 和 50 之间")
	public String getLastip() {
		return lastip;
	}

	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	
	@Length(min=0, max=100, message="头像长度必须介于 0 和 100 之间")
	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	@Length(min=0, max=2, message="锁定状态(1锁定，0未锁定)长度必须介于 0 和 2 之间")
	public String getLockstatus() {
		return lockstatus;
	}

	public void setLockstatus(String lockstatus) {
		this.lockstatus = lockstatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLocktime() {
		return locktime;
	}

	public void setLocktime(Date locktime) {
		this.locktime = locktime;
	}
	
	@Length(min=0, max=2, message="提现状态长度必须介于 0 和 2 之间")
	public String getCashstatus() {
		return cashstatus;
	}

	public void setCashstatus(String cashstatus) {
		this.cashstatus = cashstatus;
	}
	
	@Length(min=0, max=10, message="错误登录次数长度必须介于 0 和 10 之间")
	public String getLoginerrorcount() {
		return loginerrorcount;
	}

	public void setLoginerrorcount(String loginerrorcount) {
		this.loginerrorcount = loginerrorcount;
	}
	
	@Length(min=0, max=255, message="密码 MD5加密长度必须介于 0 和 255 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=2, message="是否开通富友(0未开通，1开通)长度必须介于 0 和 2 之间")
	public String getIsfuyou() {
		return isfuyou;
	}

	public void setIsfuyou(String isfuyou) {
		this.isfuyou = isfuyou;
	}
	
	@Length(min=0, max=11, message="是否限制登录 0不限制1限制长度必须介于 0 和 11 之间")
	public String getIsloginlimit() {
		return isloginlimit;
	}

	public void setIsloginlimit(String isloginlimit) {
		this.isloginlimit = isloginlimit;
	}
	
	@Length(min=0, max=11, message="用户类型1借款2投资长度必须介于 0 和 11 之间")
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	@Length(min=0, max=50, message="注册来源（web01：  web线上;web02：  web线下;iosApp01： ios线上;iosApp02： ios线下;androidApp01： android线上;androidApp02 ：android线;weixin01 ：     微信线上;weixin02  ：    微信线下）长度必须介于 0 和 50 之间")
	public String getRegsource() {
		return regsource;
	}

	public void setRegsource(String regsource) {
		this.regsource = regsource;
	}
	
	@Length(min=0, max=2, message="推荐类型  0为互联网用户1内部员工长度必须介于 0 和 2 之间")
	public String getReffertype() {
		return reffertype;
	}

	public void setReffertype(String reffertype) {
		this.reffertype = reffertype;
	}
	
	@Length(min=0, max=16, message="推荐人长度必须介于 0 和 16 之间")
	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	
	@Length(min=0, max=50, message="crm中的客户编号长度必须介于 0 和 50 之间")
	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	
	@Length(min=0, max=11, message="用户投资次数长度必须介于 0 和 11 之间")
	public String getInvestcount() {
		return investcount;
	}

	public void setInvestcount(String investcount) {
		this.investcount = investcount;
	}
	
	@Length(min=0, max=50, message="员工编号(理财师注册使用)长度必须介于 0 和 50 之间")
	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	
	@Length(min=0, max=10, message="营业部编码长度必须介于 0 和 10 之间")
	public String getDepartmentcode() {
		return departmentcode;
	}

	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}
	
	@Length(min=0, max=50, message="员工营业部名称长度必须介于 0 和 50 之间")
	public String getDepartmentinfo() {
		return departmentinfo;
	}

	public void setDepartmentinfo(String departmentinfo) {
		this.departmentinfo = departmentinfo;
	}
	
	@Length(min=0, max=11, message="smsmobile长度必须介于 0 和 11 之间")
	public String getSmsmobile() {
		return smsmobile;
	}

	public void setSmsmobile(String smsmobile) {
		this.smsmobile = smsmobile;
	}
	
	public Date getBeginRegdate() {
		return beginRegdate;
	}

	public void setBeginRegdate(Date beginRegdate) {
		this.beginRegdate = beginRegdate;
	}
	
	public Date getEndRegdate() {
		return endRegdate;
	}

	public void setEndRegdate(Date endRegdate) {
		this.endRegdate = endRegdate;
	}

	public String gettRealname() {
		return tRealname;
	}

	public void settRealname(String tRealname) {
		this.tRealname = tRealname;
	}

	public String gettStaffId() {
		return tStaffId;
	}

	public void settStaffId(String tStaffId) {
		this.tStaffId = tStaffId;
	}

	public String getTdepartmentinfo() {
		return tdepartmentinfo;
	}

	public void setTdepartmentinfo(String tdepartmentinfo) {
		this.tdepartmentinfo = tdepartmentinfo;
	}

	public String getTmobile() {
		return tmobile;
	}

	public void setTmobile(String tmobile) {
		this.tmobile = tmobile;
	}
}