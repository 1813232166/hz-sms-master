package com.hzdjr.hzwd.modules.borrow.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BorrowApplyDeatil implements Serializable {
	
	private static final long serialVersionUID = -9027122724712975946L;
	private BigDecimal MINTENDERSUM;//申请金额 最小(前台进件使用)
	private BigDecimal MAXTENDERSUM;//申请金额 最大(前台进件使用)
	private String DEADLINE;//期数(前台进件使用)
	private BigDecimal ANUALRATE;//年化利率(前台进件使用)
	
	private String borrowId;//标的ID
	private String type;//借款类型 1信用标;2抵押标;3公积金贷款
	private String usageOfLoanType;//借款用途 大类
	private String usageOfLoan;//借款用途 小类
	private BigDecimal borrowAmountLow;//申请金额 最小(后台进件使用)
	private BigDecimal borrowAmountHigh;//申请金额 最大(后台进件使用)
	private String periods;//期数(后台进件使用)
	private BigDecimal yearRate;//年化利率(后台进件使用)
	private BigDecimal monthPrepaymentAmount;//最高月还款
	private String criticalId;//是否加急 1是;2否
	
	private String name;//姓名
	private String sex;//性别 0女;1男
	private String heducation;//最高学历 10硕士及以上;20本科;30专科;60高中;70初中及以下
	private String birthday;//出生日期
	private String idCardNo;//身份证号码
	private String validityofDocumnets;//证件有效期
	private String maritalStauts;//婚姻状况 10已婚;20未婚;40离异;30丧偶
	private String hasChildren;//有无子女 1有;2没有
	private String realeStateSituation;//房产状况 1有房无贷款;2有房有贷款;3其他
	private String coResident;//共同居住者 1父母;2配偶及子女;3朋友;4独居;5其他
	private BigDecimal annualIncome;//个人年收入
	private BigDecimal highCredit;//信用卡最高额度
	private String registryProvince;//户籍省
	private String registryCity;//户籍市
	private String registryQu;//户籍区
	private String registryAddress;//户籍详细地址
	private String registryCode;//户籍邮政编码
	private String familyProvince;//现居住地址省
	private String familyCity;//现居住地址市
	private String familyQu;//现居住地址区
	private String familyAddress;//现居住地详细地址
	private String familyCode;//现居住地邮编
	private String familyQuhao;//现居住地电话区号
	private String familyTel;//现居住地电话号码
	
	private String companyName;//工作单位全称
	private String companyStyle;//单位性质 1机关及事业单位;2国营;3民营;4三资企业;5其他
	private String companyInTime;//进入单位时间
	private String companyDepartment;//所在部门/处室
	private String companyAssume;//担任职务 1创始人/负责人;2高层管理人员;3中层管理人员;4一般管理人员;5普通员工;6临时员工;7其他
	private String companyCode;//单位邮政编码
	private String companyPorvince;//单位地址省
	private String companyCity;//单位地址市
	private String companyQu;//单位地址区
	private String companyAddress;//单位详细地址
	private String companyQuhao;//单位电话区号
	private String companyTel;//单位电话号码
	private String companySuffix;//单位电话分机号
	
	private String queryNet;//查询网址
	private String accountType;//帐户类型 1身份证;2联名卡;3用户名
	private String accountNo;//账号
	private String accountPassword;//密码
	
	private List<BorrowContacts> list;//联系人集合
	private List<BorrowPicsVo> borrowPicsVoList;//相册集合，相册中有图片集合
	
	public List<BorrowContacts> getList() {
		return list;
	}
	public void setList(List<BorrowContacts> list) {
		this.list = list;
	}
	public String getUsageOfLoanType() {
		return usageOfLoanType;
	}
	public void setUsageOfLoanType(String usageOfLoanType) {
		this.usageOfLoanType = usageOfLoanType;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsageOfLoan() {
		return usageOfLoan;
	}
	public void setUsageOfLoan(String usageOfLoan) {
		this.usageOfLoan = usageOfLoan;
	}
	public BigDecimal getMINTENDERSUM() {
		return MINTENDERSUM;
	}
	public void setMINTENDERSUM(BigDecimal mINTENDERSUM) {
		MINTENDERSUM = mINTENDERSUM;
	}
	public BigDecimal getMAXTENDERSUM() {
		return MAXTENDERSUM;
	}
	public void setMAXTENDERSUM(BigDecimal mAXTENDERSUM) {
		MAXTENDERSUM = mAXTENDERSUM;
	}
	public String getDEADLINE() {
		return DEADLINE;
	}
	public void setDEADLINE(String dEADLINE) {
		DEADLINE = dEADLINE;
	}
	public BigDecimal getANUALRATE() {
		return ANUALRATE;
	}
	public void setANUALRATE(BigDecimal aNUALRATE) {
		ANUALRATE = aNUALRATE;
	}
	public BigDecimal getMonthPrepaymentAmount() {
		return monthPrepaymentAmount;
	}
	public void setMonthPrepaymentAmount(BigDecimal monthPrepaymentAmount) {
		this.monthPrepaymentAmount = monthPrepaymentAmount;
	}
	public String getCriticalId() {
		return criticalId;
	}
	public void setCriticalId(String criticalId) {
		this.criticalId = criticalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHeducation() {
		return heducation;
	}
	public void setHeducation(String heducation) {
		this.heducation = heducation;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getValidityofDocumnets() {
		return validityofDocumnets;
	}
	public void setValidityofDocumnets(String validityofDocumnets) {
		this.validityofDocumnets = validityofDocumnets;
	}
	public String getMaritalStauts() {
		return maritalStauts;
	}
	public void setMaritalStauts(String maritalStauts) {
		this.maritalStauts = maritalStauts;
	}
	public String getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}
	public String getRealeStateSituation() {
		return realeStateSituation;
	}
	public void setRealeStateSituation(String realeStateSituation) {
		this.realeStateSituation = realeStateSituation;
	}
	public String getCoResident() {
		return coResident;
	}
	public void setCoResident(String coResident) {
		this.coResident = coResident;
	}
	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}
	public BigDecimal getHighCredit() {
		return highCredit;
	}
	public void setHighCredit(BigDecimal highCredit) {
		this.highCredit = highCredit;
	}
	public String getRegistryProvince() {
		return registryProvince;
	}
	public void setRegistryProvince(String registryProvince) {
		this.registryProvince = registryProvince;
	}
	public String getRegistryCity() {
		return registryCity;
	}
	public void setRegistryCity(String registryCity) {
		this.registryCity = registryCity;
	}
	public String getRegistryQu() {
		return registryQu;
	}
	public void setRegistryQu(String registryQu) {
		this.registryQu = registryQu;
	}
	public String getRegistryAddress() {
		return registryAddress;
	}
	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}
	public String getRegistryCode() {
		return registryCode;
	}
	public void setRegistryCode(String registryCode) {
		this.registryCode = registryCode;
	}
	public String getFamilyProvince() {
		return familyProvince;
	}
	public void setFamilyProvince(String familyProvince) {
		this.familyProvince = familyProvince;
	}
	public String getFamilyCity() {
		return familyCity;
	}
	public void setFamilyCity(String familyCity) {
		this.familyCity = familyCity;
	}
	public String getFamilyQu() {
		return familyQu;
	}
	public void setFamilyQu(String familyQu) {
		this.familyQu = familyQu;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}
	public String getFamilyQuhao() {
		return familyQuhao;
	}
	public void setFamilyQuhao(String familyQuhao) {
		this.familyQuhao = familyQuhao;
	}
	public String getFamilyTel() {
		return familyTel;
	}
	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyStyle() {
		return companyStyle;
	}
	public void setCompanyStyle(String companyStyle) {
		this.companyStyle = companyStyle;
	}
	public String getCompanyInTime() {
		return companyInTime;
	}
	public void setCompanyInTime(String companyInTime) {
		this.companyInTime = companyInTime;
	}
	public String getCompanyDepartment() {
		return companyDepartment;
	}
	public void setCompanyDepartment(String companyDepartment) {
		this.companyDepartment = companyDepartment;
	}
	public String getCompanyAssume() {
		return companyAssume;
	}
	public void setCompanyAssume(String companyAssume) {
		this.companyAssume = companyAssume;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyPorvince() {
		return companyPorvince;
	}
	public void setCompanyPorvince(String companyPorvince) {
		this.companyPorvince = companyPorvince;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getCompanyQu() {
		return companyQu;
	}
	public void setCompanyQu(String companyQu) {
		this.companyQu = companyQu;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyQuhao() {
		return companyQuhao;
	}
	public void setCompanyQuhao(String companyQuhao) {
		this.companyQuhao = companyQuhao;
	}
	public String getCompanyTel() {
		return companyTel;
	}
	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	public String getCompanySuffix() {
		return companySuffix;
	}
	public void setCompanySuffix(String companySuffix) {
		this.companySuffix = companySuffix;
	}
	public String getQueryNet() {
		return queryNet;
	}
	public void setQueryNet(String queryNet) {
		this.queryNet = queryNet;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public BigDecimal getBorrowAmountLow() {
		return borrowAmountLow;
	}
	public void setBorrowAmountLow(BigDecimal borrowAmountLow) {
		this.borrowAmountLow = borrowAmountLow;
	}
	public BigDecimal getBorrowAmountHigh() {
		return borrowAmountHigh;
	}
	public void setBorrowAmountHigh(BigDecimal borrowAmountHigh) {
		this.borrowAmountHigh = borrowAmountHigh;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public BigDecimal getYearRate() {
		return yearRate;
	}
	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}
	public List<BorrowPicsVo> getBorrowPicsVoList() {
		return borrowPicsVoList;
	}
	public void setBorrowPicsVoList(List<BorrowPicsVo> borrowPicsVoList) {
		this.borrowPicsVoList = borrowPicsVoList;
	}
	
}
