package com.hzwealth.sms.modules.usermanage.entity;

import java.util.Date;

/**
 *Title:TUserALL 
 *Description:  前台用户表
 *@author:黄亚浩
 *@date:2016年10月17日 下午6:47:13
 */
public class TUserALL {

	private String id;  //
	private String depId;   //部门ID
	private String userName;   //用户名称
	private String sex;   //性别（1男2女）
	private Date birthday;  // 出生日期
	private Date regDate;   // 注册时间
	private String mobile;  //手机号
	private String email;   //邮箱
	private Integer loginCount; //登录次数
	private String refferee;   //推荐人(也是userid,也可以是线下seller工号)
	private String lastIp;   //最后登录IP
	private Date lastDate;   //最后登录时间
	private String headImg;   //'/upload/deaultHeadImg.jpg' COMMENT '头像',
	private Integer lockStatus;    //锁定状态(1锁定，0未锁定)
	private Date lockTime;   //锁定时间
	private Integer cashStatus;   //提现状态
	private Integer loginErrorCount;  //错误登录次数
	private String password;   //'密码 MD5加密',
	private Integer isFuYou;   //是否开通富友(0未开通，1开通)',
	private Integer isLoginLimit;  //是否限制登录 0不限制1限制'
	private Integer userType;   //用户类型1借款2投资',
	private String regSource;  //注册来源（web01：  web线上;web02：  web线下;iosApp01： ios线上;iosApp02： ios线下;androidApp01： android线上;androidApp02 ：android线;weixin01 ：     微信线上;weixin02  ：    微信线下）',
	  //`refferType` varchar(2) DEFAULT NULL COMMENT '推荐类型  0为互联网用户1内部员工',
	private String refferType;  //推荐类型  0为互联网用户1内部员工'
	private String invitecode;   //推荐人
	private String customerId;   //crm中的客户编号
	private Integer investCount;   //用户投资次数
	private String staffId;     //员工编号(理财师注册使用)
	private String departmentCode;  //营业部编码
	private String departmentInfo;   //员工营业部名称'
	private String smsmobile;
	private String  realName;   //用户真实姓名
	//departMent entity
	private String value;   //内容（省、市、部门）'
	//userDetail ebtity
	private String qq;   //QQ
	private String addressDetail;   //详细地址
	private String contractUser;     //紧急联系人
	private String contractPhone;    //紧急联系人电话
	private String company;   // 公司名称',
	private String companyNature;  //公司性质
	private String workLevel;    //职工级别
	private String workYear;  //工作年限
	private String monthIncome;   //月收入
	private String marriageState;   //婚姻状况
	private String marryName;     //配偶名称
	private String marryMothIncome;   //配偶月收入\
	private String educationLevel;   //最高学历
	private String school;    //学院名称
	private String master;    //专业
	private Date masterStartDate;   //入学开始时间
	private Date masterEndDate;   //入学结束时间
	private String isBuyCar;    //是否购车
	private String isPurchase;    //是否购房(0否，1是)',
	private Integer idcardStatus;   //身份认证状态(0未认证1已认证)'
	private Date idcardAuthDate;  //身份认证时间',
	private Integer emailStatus;   //邮箱认证状态(0未认证1已认证)',
	private Date emailAuthDate;    //邮箱认证时间'
	private String idcard;    //省份证号
	private String risk;   
	
	private String address;   //现居住地
	private String time;   //入学开始到结束
	
	
	
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public String getId() {
		return id;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getIdcardStatus() {
		return idcardStatus;
	}
	public void setIdcardStatus(Integer idcardStatus) {
		this.idcardStatus = idcardStatus;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Date getIdcardAuthDate() {
		return idcardAuthDate;
	}
	public void setIdcardAuthDate(Date idcardAuthDate) {
		this.idcardAuthDate = idcardAuthDate;
	}
	public Integer getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(Integer emailStatus) {
		this.emailStatus = emailStatus;
	}
	public Date getEmailAuthDate() {
		return emailAuthDate;
	}
	public void setEmailAuthDate(Date emailAuthDate) {
		this.emailAuthDate = emailAuthDate;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public String getRefferee() {
		return refferee;
	}
	public void setRefferee(String refferee) {
		this.refferee = refferee;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Integer getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}
	public Date getLockTime() {
		return lockTime;
	}
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	public Integer getCashStatus() {
		return cashStatus;
	}
	public void setCashStatus(Integer cashStatus) {
		this.cashStatus = cashStatus;
	}
	public Integer getLoginErrorCount() {
		return loginErrorCount;
	}
	public void setLoginErrorCount(Integer loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIsFuYou() {
		return isFuYou;
	}
	public void setIsFuYou(Integer isFuYou) {
		this.isFuYou = isFuYou;
	}
	public Integer getIsLoginLimit() {
		return isLoginLimit;
	}
	public void setIsLoginLimit(Integer isLoginLimit) {
		this.isLoginLimit = isLoginLimit;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getRegSource() {
		return regSource;
	}
	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}
	public String getRefferType() {
		return refferType;
	}
	public void setRefferType(String refferType) {
		this.refferType = refferType;
	}
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Integer getInvestCount() {
		return investCount;
	}
	public void setInvestCount(Integer investCount) {
		this.investCount = investCount;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentInfo() {
		return departmentInfo;
	}
	public void setDepartmentInfo(String departmentInfo) {
		this.departmentInfo = departmentInfo;
	}
	public String getSmsmobile() {
		return smsmobile;
	}
	public void setSmsmobile(String smsmobile) {
		this.smsmobile = smsmobile;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getContractUser() {
		return contractUser;
	}
	public void setContractUser(String contractUser) {
		this.contractUser = contractUser;
	}
	public String getContractPhone() {
		return contractPhone;
	}
	public void setContractPhone(String contractPhone) {
		this.contractPhone = contractPhone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyNature() {
		return companyNature;
	}
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}
	public String getWorkLevel() {
		return workLevel;
	}
	public void setWorkLevel(String workLevel) {
		this.workLevel = workLevel;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getMonthIncome() {
		return monthIncome;
	}
	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}
	public String getMarriageState() {
		return marriageState;
	}
	public void setMarriageState(String marriageState) {
		this.marriageState = marriageState;
	}
	public String getMarryName() {
		return marryName;
	}
	public void setMarryName(String marryName) {
		this.marryName = marryName;
	}
	public String getMarryMothIncome() {
		return marryMothIncome;
	}
	public void setMarryMothIncome(String marryMothIncome) {
		this.marryMothIncome = marryMothIncome;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public Date getMasterStartDate() {
		return masterStartDate;
	}
	public void setMasterStartDate(Date masterStartDate) {
		this.masterStartDate = masterStartDate;
	}
	public Date getMasterEndDate() {
		return masterEndDate;
	}
	public void setMasterEndDate(Date masterEndDate) {
		this.masterEndDate = masterEndDate;
	}
	
	public String getIsBuyCar() {
		return isBuyCar;
	}
	public void setIsBuyCar(String isBuyCar) {
		this.isBuyCar = isBuyCar;
	}
	public String getIsPurchase() {
		return isPurchase;
	}
	public void setIsPurchase(String isPurchase) {
		this.isPurchase = isPurchase;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
	
	
	
	
}
