/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

/**
 * 普享表申请列表Entity
 * @author hdj
 * @version 2016-10-13
 */
public class Borrow extends DataEntity<Borrow> {
	
	private static final long serialVersionUID = 1L;
	private String status;		// 满标状态
	private String borrowId;		// borrow_id
	private String type;		// 1信用标2抵押标
	private String borrowtitle;		// 借款标题
	private String borrowway;		// 借款方式(debx等额本息，ycx一次性还款，dqhb到期还本)
	private String borrowcode;		// 标的编号
	private String borrowamount;		// 借款总额
	private String deadline;		// 借款期限
	private String anualrate;		// 年利率
	private String mintendersum;		// 最小投资金额
	private String payMethod;		// 还款方式(debx等额本息 xxhb先息后本ychbx一次性还本付息)
	private String maxtendersum;		// 最大投资金额
	private String raiseterm;		// 筹标期限(募集期)
	private String borrowtype;		// 借款形式(xy信用借款fd房贷借款cd车贷借款)
	private String borrowstatus;		// 状态(0未匹配1预发布;2初审成功;3初审失败;4招标中;5待复审;6复审失败;7还款中;8已还清;9已流标;10逾期;11已经满标)
	private String raisetermunit;		// 筹标期限单位（0小时 1天）
	private String detail;		// 借款详情
	private Date openborrowdate;		// 开标时间
	private String userid;		// 借款人
	private String repaysource;		// 还款来源
	private String borrowaliasno;		// 标的中文别名编号
	private String borrowalias;		// 标的中文别名
	private String ip;		// ip
	private String loannumber;		// 借款编号
	private String oriuserid;		// 原始借款人
	private String name;		// 姓名
	private String oriuseridcard;		// 原借款人身份证
	private Date auditdate;		// 审核时间
	private String hasinvestamount;		// 已投总额
	private String hasinvestnum;		// 已投数量
	private Date createTime;		// 创建时间
	private String servicefree;		// 服务费率
	private Date fullBorrowDate;		// 散标满标时间
	private Date contractsigningtime;		// 合同签订时间
	private String contractcode;		// 合同编号
	private String productcode;		// 产品编号
	private String repaymentdate;		// 还款日
	private String monthinterest;		// 月利息
	private String monthcapital;		// 月本金
	private String monthprepaymentamount;		// 月还款额
	private String giveamount;		// 到手金额
	private String monthmanagementfee;		// 月管理费率
	private String servicecharge;		// 服务费
	private String businesschannel;		// 商户渠道
	private String mobile;		// 手机号
	private String bcbizdeptid;		// 营业部编码
	private String bztype;		// 保障方式(dbgs担保金保障fxj风险金保障no不保障)
	private Date loantime;		// 批贷时间
	private String borrowfid;		// 散标包ID
	private String criticalid;		// 紧急状态2不紧急1紧急
	private String warnings;		// 风险提示
	private String paidinamount;		// 实收金额
	private String usageofloan;		// 借款用途
	private String creditrating;		// 信用评级
	private String idcardno;		// 身份证号码
	private String sex;		// 性别(0女,1男)
	private String age;		// 年龄
	private String city;		// 所在城市
	private String annualincome;		// 年收入
	private String assetsliabilities;		// 资产负债情况
	private String isEdit;		// 编辑状态(0未编辑1已编辑)
	private String isMatch;		// 是否匹配(0是未匹配1是匹配)
	private String loanName;		// 借款名称
	private Date lendingtime;		// 放款时间
	private String borrowCustomerCode;		// 借款人客户编号
	private String borrowAccCode;		// 借款人金账户号
	private String openBankCode;		// 开户行别代码
	private String cardno;		// 银行账号
	private String creditlevel;		// 信用评级
	private String isFinish;		// 是否完成(0未完成,1完成)
	private String openBank;		// 开户行
	private String fillMark;		// 是否到期补标(0不补 1补标)
	private String riskWarning;		// 风险提示
	private String remainamount;		// 借款人应还款金额
	private Date beginOpenborrowdate;		// 开始 开标时间
	private Date endOpenborrowdate;		// 结束 开标时间
	private String borrowUserName;		//借款人姓名
	private String projectBrief;  // 项目介绍
	private String borrowListId;	// 普享标集合id
	public Borrow() {
		super();
	}

	public Borrow(String id){
		super(id);
	}
	@ExcelField(title="借款人",type=1,align=2,sort=30)
	public String getBorrowUserName() {
		return borrowUserName;
	}

	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}

	@Length(min=1, max=1, message="满标状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=32, message="borrow_id长度必须介于 1 和 32 之间")
	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	
	@Length(min=1, max=10, message="1信用标2抵押标长度必须介于 1 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="借款标题长度必须介于 0 和 255 之间")
	public String getBorrowtitle() {
		return borrowtitle;
	}

	public void setBorrowtitle(String borrowtitle) {
		this.borrowtitle = borrowtitle;
	}
	
	@Length(min=0, max=50, message="借款方式(debx等额本息，ycx一次性还款，dqhb到期还本)长度必须介于 0 和 50 之间")
	public String getBorrowway() {
		return borrowway;
	}

	public void setBorrowway(String borrowway) {
		this.borrowway = borrowway;
	}
	
	@Length(min=1, max=30, message="标的编号长度必须介于 1 和 30 之间")
	@ExcelField(title="标的编号",type=1,align=2,sort=0)
	public String getBorrowcode() {
		return borrowcode;
	}

	public void setBorrowcode(String borrowcode) {
		this.borrowcode = borrowcode;
	}
	
	@ExcelField(title="借款金额",type=1,align=2,sort=40)
	public String getBorrowamount() {
		return borrowamount;
	}

	public void setBorrowamount(String borrowamount) {
		this.borrowamount = borrowamount;
	}
	
	@Length(min=0, max=10, message="借款期限长度必须介于 0 和 10 之间")
	@ExcelField(title="出借期限",type=1,align=2,sort=60)
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	@ExcelField(title="出借年利率",type=1,align=2,sort=50)
	public String getAnualrate() {
		return anualrate;
	}

	public void setAnualrate(String anualrate) {
		this.anualrate = anualrate;
	}
	
	public String getMintendersum() {
		return mintendersum;
	}

	public void setMintendersum(String mintendersum) {
		this.mintendersum = mintendersum;
	}
	
	@Length(min=0, max=11, message="还款方式(debx等额本息 xxhb先息后本ychbx一次性还本付息)长度必须介于 0 和 11 之间")
	@ExcelField(title="还款方式",type=1,align=2,sort=70)
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	public String getMaxtendersum() {
		return maxtendersum;
	}

	public void setMaxtendersum(String maxtendersum) {
		this.maxtendersum = maxtendersum;
	}
	
	@Length(min=0, max=2, message="筹标期限(募集期)长度必须介于 0 和 2 之间")
	public String getRaiseterm() {
		return raiseterm;
	}

	public void setRaiseterm(String raiseterm) {
		this.raiseterm = raiseterm;
	}
	
	@Length(min=0, max=20, message="借款形式(xy信用借款fd房贷借款cd车贷借款)长度必须介于 0 和 20 之间")
	public String getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(String borrowtype) {
		this.borrowtype = borrowtype;
	}
	
	@Length(min=0, max=10, message="状态(0未匹配1预发布;2初审成功;3初审失败;4招标中;5待复审;6复审失败;7还款中;8已还清;9已流标;10逾期;11已经满标)长度必须介于 0 和 10 之间")
	@ExcelField(title="状态",type=1,align=2,sort=110)
	public String getBorrowstatus() {
		return borrowstatus;
	}

	public void setBorrowstatus(String borrowstatus) {
		this.borrowstatus = borrowstatus;
	}
	
	@Length(min=0, max=2, message="筹标期限单位（0小时 1天）长度必须介于 0 和 2 之间")
	public String getRaisetermunit() {
		return raisetermunit;
	}

	public void setRaisetermunit(String raisetermunit) {
		this.raisetermunit = raisetermunit;
	}
	
	@Length(min=0, max=500, message="借款详情长度必须介于 0 和 500 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOpenborrowdate() {
		return openborrowdate;
	}

	public void setOpenborrowdate(Date openborrowdate) {
		this.openborrowdate = openborrowdate;
	}
	
	@Length(min=0, max=32, message="借款人长度必须介于 0 和 32 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=500, message="还款来源长度必须介于 0 和 500 之间")
	public String getRepaysource() {
		return repaysource;
	}

	public void setRepaysource(String repaysource) {
		this.repaysource = repaysource;
	}
	
	@Length(min=0, max=50, message="标的中文别名编号长度必须介于 0 和 50 之间")
	public String getBorrowaliasno() {
		return borrowaliasno;
	}

	public void setBorrowaliasno(String borrowaliasno) {
		this.borrowaliasno = borrowaliasno;
	}
	
	@Length(min=0, max=20, message="标的中文别名长度必须介于 0 和 20 之间")
	@ExcelField(title="标的名称",type=1,align=2,sort=10)
	public String getBorrowalias() {
		return borrowalias;
	}

	public void setBorrowalias(String borrowalias) {
		this.borrowalias = borrowalias;
	}
	
	@Length(min=0, max=255, message="ip长度必须介于 0 和 255 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=32, message="借款编号长度必须介于 0 和 32 之间")
	@ExcelField(title="借款编号",type=1,align=2,sort=20)
	public String getLoannumber() {
		return loannumber;
	}

	public void setLoannumber(String loannumber) {
		this.loannumber = loannumber;
	}
	
	@Length(min=0, max=32, message="原始借款人长度必须介于 0 和 32 之间")
	public String getOriuserid() {
		return oriuserid;
	}

	public void setOriuserid(String oriuserid) {
		this.oriuserid = oriuserid;
	}
	
	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=18, message="原借款人身份证长度必须介于 0 和 18 之间")
	public String getOriuseridcard() {
		return oriuseridcard;
	}

	public void setOriuseridcard(String oriuseridcard) {
		this.oriuseridcard = oriuseridcard;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditdate() {
		return auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}
	
	public String getHasinvestamount() {
		return hasinvestamount;
	}

	public void setHasinvestamount(String hasinvestamount) {
		this.hasinvestamount = hasinvestamount;
	}
	
	@Length(min=0, max=11, message="已投数量长度必须介于 0 和 11 之间")
	public String getHasinvestnum() {
		return hasinvestnum;
	}

	public void setHasinvestnum(String hasinvestnum) {
		this.hasinvestnum = hasinvestnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间",type=1,align=2,sort=90)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getServicefree() {
		return servicefree;
	}

	public void setServicefree(String servicefree) {
		this.servicefree = servicefree;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFullBorrowDate() {
		return fullBorrowDate;
	}

	public void setFullBorrowDate(Date fullBorrowDate) {
		this.fullBorrowDate = fullBorrowDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getContractsigningtime() {
		return contractsigningtime;
	}

	public void setContractsigningtime(Date contractsigningtime) {
		this.contractsigningtime = contractsigningtime;
	}
	
	@Length(min=0, max=32, message="合同编号长度必须介于 0 和 32 之间")
	public String getContractcode() {
		return contractcode;
	}

	public void setContractcode(String contractcode) {
		this.contractcode = contractcode;
	}
	
	@Length(min=0, max=32, message="产品编号长度必须介于 0 和 32 之间")
	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	
	@Length(min=0, max=5, message="还款日长度必须介于 0 和 5 之间")
	public String getRepaymentdate() {
		return repaymentdate;
	}

	public void setRepaymentdate(String repaymentdate) {
		this.repaymentdate = repaymentdate;
	}
	
	public String getMonthinterest() {
		return monthinterest;
	}

	public void setMonthinterest(String monthinterest) {
		this.monthinterest = monthinterest;
	}
	
	public String getMonthcapital() {
		return monthcapital;
	}

	public void setMonthcapital(String monthcapital) {
		this.monthcapital = monthcapital;
	}
	
	public String getMonthprepaymentamount() {
		return monthprepaymentamount;
	}

	public void setMonthprepaymentamount(String monthprepaymentamount) {
		this.monthprepaymentamount = monthprepaymentamount;
	}
	
	public String getGiveamount() {
		return giveamount;
	}

	public void setGiveamount(String giveamount) {
		this.giveamount = giveamount;
	}
	
	@Length(min=0, max=10, message="月管理费率长度必须介于 0 和 10 之间")
	public String getMonthmanagementfee() {
		return monthmanagementfee;
	}

	public void setMonthmanagementfee(String monthmanagementfee) {
		this.monthmanagementfee = monthmanagementfee;
	}
	
	public String getServicecharge() {
		return servicecharge;
	}

	public void setServicecharge(String servicecharge) {
		this.servicecharge = servicecharge;
	}
	
	@Length(min=0, max=32, message="商户渠道长度必须介于 0 和 32 之间")
	public String getBusinesschannel() {
		return businesschannel;
	}

	public void setBusinesschannel(String businesschannel) {
		this.businesschannel = businesschannel;
	}
	
	@Length(min=0, max=32, message="手机号长度必须介于 0 和 32 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=32, message="营业部编码长度必须介于 0 和 32 之间")
	public String getBcbizdeptid() {
		return bcbizdeptid;
	}

	public void setBcbizdeptid(String bcbizdeptid) {
		this.bcbizdeptid = bcbizdeptid;
	}
	
	@Length(min=0, max=50, message="保障方式(dbgs担保金保障fxj风险金保障no不保障)长度必须介于 0 和 50 之间")
	public String getBztype() {
		return bztype;
	}

	public void setBztype(String bztype) {
		this.bztype = bztype;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoantime() {
		return loantime;
	}

	public void setLoantime(Date loantime) {
		this.loantime = loantime;
	}
	
	@Length(min=0, max=32, message="散标包ID长度必须介于 0 和 32 之间")
	public String getBorrowfid() {
		return borrowfid;
	}

	public void setBorrowfid(String borrowfid) {
		this.borrowfid = borrowfid;
	}
	
	@Length(min=0, max=2, message="紧急状态2不紧急1紧急长度必须介于 0 和 2 之间")
	@ExcelField(title="紧急状态",type=1,align=2,sort=70)
	public String getCriticalid() {
		return criticalid;
	}

	public void setCriticalid(String criticalid) {
		this.criticalid = criticalid;
	}
	
	@Length(min=0, max=200, message="风险提示长度必须介于 0 和 200 之间")
	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}
	
	public String getPaidinamount() {
		return paidinamount;
	}

	public void setPaidinamount(String paidinamount) {
		this.paidinamount = paidinamount;
	}
	
	@Length(min=0, max=255, message="借款用途长度必须介于 0 和 255 之间")
	public String getUsageofloan() {
		return usageofloan;
	}

	public void setUsageofloan(String usageofloan) {
		this.usageofloan = usageofloan;
	}
	
	@Length(min=0, max=50, message="信用评级长度必须介于 0 和 50 之间")
	public String getCreditrating() {
		return creditrating;
	}

	public void setCreditrating(String creditrating) {
		this.creditrating = creditrating;
	}
	
	@Length(min=0, max=18, message="身份证号码长度必须介于 0 和 18 之间")
	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	
	@Length(min=0, max=10, message="性别(0女,1男)长度必须介于 0 和 10 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=50, message="年龄长度必须介于 0 和 50 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=255, message="所在城市长度必须介于 0 和 255 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAnnualincome() {
		return annualincome;
	}

	public void setAnnualincome(String annualincome) {
		this.annualincome = annualincome;
	}
	
	@Length(min=0, max=10, message="资产负债情况长度必须介于 0 和 10 之间")
	public String getAssetsliabilities() {
		return assetsliabilities;
	}

	public void setAssetsliabilities(String assetsliabilities) {
		this.assetsliabilities = assetsliabilities;
	}
	
	@Length(min=0, max=4, message="编辑状态(0未编辑1已编辑)长度必须介于 0 和 4 之间")
	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
	
	@Length(min=0, max=4, message="是否匹配(0是未匹配1是匹配)长度必须介于 0 和 4 之间")
	public String getIsMatch() {
		return isMatch;
	}

	public void setIsMatch(String isMatch) {
		this.isMatch = isMatch;
	}
	
	@Length(min=0, max=32, message="借款名称长度必须介于 0 和 32 之间")
	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLendingtime() {
		return lendingtime;
	}

	public void setLendingtime(Date lendingtime) {
		this.lendingtime = lendingtime;
	}
	
	@Length(min=0, max=32, message="借款人客户编号长度必须介于 0 和 32 之间")
	public String getBorrowCustomerCode() {
		return borrowCustomerCode;
	}

	public void setBorrowCustomerCode(String borrowCustomerCode) {
		this.borrowCustomerCode = borrowCustomerCode;
	}
	
	@Length(min=0, max=32, message="借款人金账户号长度必须介于 0 和 32 之间")
	public String getBorrowAccCode() {
		return borrowAccCode;
	}

	public void setBorrowAccCode(String borrowAccCode) {
		this.borrowAccCode = borrowAccCode;
	}
	
	@Length(min=0, max=12, message="开户行别代码长度必须介于 0 和 12 之间")
	public String getOpenBankCode() {
		return openBankCode;
	}

	public void setOpenBankCode(String openBankCode) {
		this.openBankCode = openBankCode;
	}
	
	@Length(min=0, max=32, message="银行账号长度必须介于 0 和 32 之间")
	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
	@Length(min=0, max=12, message="信用评级长度必须介于 0 和 12 之间")
	public String getCreditlevel() {
		return creditlevel;
	}

	public void setCreditlevel(String creditlevel) {
		this.creditlevel = creditlevel;
	}
	
	@Length(min=0, max=4, message="是否完成(0未完成,1完成)长度必须介于 0 和 4 之间")
	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}
	
	@Length(min=0, max=50, message="开户行长度必须介于 0 和 50 之间")
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	@Length(min=0, max=1, message="是否到期补标(0不补 1补标)长度必须介于 0 和 1 之间")
	public String getFillMark() {
		return fillMark;
	}

	public void setFillMark(String fillMark) {
		this.fillMark = fillMark;
	}
	
	@Length(min=0, max=100, message="风险提示长度必须介于 0 和 100 之间")
	public String getRiskWarning() {
		return riskWarning;
	}

	public void setRiskWarning(String riskWarning) {
		this.riskWarning = riskWarning;
	}
	
	public String getRemainamount() {
		return remainamount;
	}

	public void setRemainamount(String remainamount) {
		this.remainamount = remainamount;
	}
	
	public Date getBeginOpenborrowdate() {
		return beginOpenborrowdate;
	}

	public void setBeginOpenborrowdate(Date beginOpenborrowdate) {
		this.beginOpenborrowdate = beginOpenborrowdate;
	}
	
	public Date getEndOpenborrowdate() {
		return endOpenborrowdate;
	}

	public void setEndOpenborrowdate(Date endOpenborrowdate) {
		this.endOpenborrowdate = endOpenborrowdate;
	}

	public String getProjectBrief() {
		return projectBrief;
	}

	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}

	public String getBorrowListId() {
		return borrowListId;
	}

	public void setBorrowListId(String borrowListId) {
		this.borrowListId = borrowListId;
	}
	
}