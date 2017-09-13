package com.hzdjr.hzwd.modules.repaymentmanage.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

/**
 * 普享标实体类
 */
public class PuBiao extends DataEntity<PuBiao>{
    private static final long serialVersionUID = 1L;
    
    private String borrowId;
	private String	borrowCode;//	t1.borrowCode as '标的编号',
	private String borrowaliasno; //标的编号-中文
	private String borrowAlias;//	t1.borrowAlias as '标的名称',
	private String loanNumber;//	t1.loanNumber as '借款编号',
	private String name;		//	t1.name as '借款人',
	private String mobile;		//	t1.mobile as '借款人手机号',
	private BigDecimal borrowAmount;		//	t1.BORROWAMOUNT as '借款金额',
	private BigDecimal anualrate;		//	t1.ANUALRATE as '出借年化利率',
	private String deadline;		//	t1.DEADLINE as '出借期限',
	private String payMethod;		//	t1.pay_method as '还款方式',
	private BigDecimal remainAmount;		//	t1.remainAmount as '还款金额', 
	private String	repaymentNum;		//	 '已还款期数',
	private String	repaymentNumSum;		//	 '总还款期数',
	private String	repaymentDate;		//	t1.productCode as '还款时间',还款日
	private String	borrowStatus;		//	t1.BORROWSTATUS as '还款状态'
	private String	bzType;		//	保障方式
	private String	borrowType;		//	借款形式
	private Date loanTime;		//	批贷时间
	private String criticalId;		//	紧集状态
	private Date lendingTime;		//放款时间
	private Date  openborrowDate;//	发布时间
	private BigDecimal minTenderSum;//最低出借金额
	private String raiseTerm;//募集期
	private String fillMark;//是否到期补标
	private Date fullBorrowDate;//满标时间
	private String bili;//'已还款期数'/'总还款期数'
	private String riskWarning;		// 风险提示
	private String usageofloan;
	private BigDecimal sumCount;
	
	
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getRepaymentNumSum() {
		return repaymentNumSum;
	}
	public void setRepaymentNumSum(String repaymentNumSum) {
		this.repaymentNumSum = repaymentNumSum;
	}
	public String getBzType() {
		return bzType;
	}
	
	public String getBorrowCode() {
		return borrowCode;
	}
	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}
	public void setBzType(String bzType) {
		this.bzType = bzType;
	}
	public String getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	public Date getLoanTime() {
		return loanTime;
	}
	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}
	public String getCriticalId() {
		return criticalId;
	}
	public void setCriticalId(String criticalId) {
		this.criticalId = criticalId;
	}
	public Date getLendingTime() {
		return lendingTime;
	}
	public void setLendingTime(Date lendingTime) {
		this.lendingTime = lendingTime;
	}
	public BigDecimal getMinTenderSum() {
		return minTenderSum;
	}
	public void setMinTenderSum(BigDecimal minTenderSum) {
		this.minTenderSum = minTenderSum;
	}
	public String getRaiseTerm() {
		return raiseTerm;
	}
	public void setRaiseTerm(String raiseTerm) {
		this.raiseTerm = raiseTerm;
	}
	public String getFillMark() {
		return fillMark;
	}
	public void setFillMark(String fillMark) {
		this.fillMark = fillMark;
	}
	public Date getFullBorrowDate() {
		return fullBorrowDate;
	}
	public void setFullBorrowDate(Date fullBorrowDate) {
		this.fullBorrowDate = fullBorrowDate;
	}
	
	
	@ExcelField(title="标的名称",type=1,align=2,sort=11)
	public String getBorrowAlias() {
		return borrowAlias;
	}
	public void setBorrowAlias(String borrowAlias) {
		this.borrowAlias = borrowAlias;
	}
	
	@ExcelField(title="借款编号",type=1,align=2,sort=12)
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	@ExcelField(title="借款人",type=1,align=2,sort=13)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title="借款人手机号",type=1,align=2,sort=14)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@ExcelField(title="借款金额(元)",type=1,align=2,sort=15)
	public String getBorrowAmount() {
		return borrowAmount+"";
	}
	public void setBorrowAmount(BigDecimal borrowAmount) {
		this.borrowAmount = borrowAmount;
	}
	@ExcelField(title="出借年化利率",type=1,align=2,sort=16)
	public String getAnualrate() {
		return anualrate+"%";
	}
	public void setAnualrate(BigDecimal anualrate) {
		this.anualrate = anualrate;
	}
	
	@ExcelField(title="出借期限",type=1,align=2,sort=17)
	public String getDeadline() {
		return deadline+"个月";
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	@ExcelField(title="还款方式",type=1,align=2,sort=18)
	public String getPayMethod() {
		if("debx".equals(payMethod)){
			return "等额本息";
		}
		else if("xxhb".equals(payMethod)){
			return "先息后本";
		}
		else if("ychbx".equals(payMethod)){
			return "一次性还本付息";
		}
		else{
			return "";
		}
		
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	
	@ExcelField(title="还款金额(元)",type=1,align=2,sort=19)
	public String getRemainAmount() {
		if(remainAmount==null){
			remainAmount=new BigDecimal(0.00);
		}
		return remainAmount+"";
	}
	public void setRemainAmount(BigDecimal remainAmount) {
		this.remainAmount = remainAmount;
	}
	public String getRepaymentNum() {
		return repaymentNum;
	}
	public void setRepaymentNum(String repaymentNum) {
		this.repaymentNum = repaymentNum;
	}
	
	
	
	
	@ExcelField(title="还款期数(已还款/总还款期数)",type=1,align=2,sort=20)
	public String getBili() {
		
		return bili;
	}
	public void setBili(String bili) {
		this.bili = bili;
	}
	
	
	@ExcelField(title="还款时间",type=1,align=2,sort=21)
	public String getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	/**
	 * 状态(0前台待审核、3审核失败、4招标中、7还款中、8已还清、9已流标、10逾期、11已满标、12已撤销、13信审过程中、
	 * 14信审失败、15信审成功、16新建、17拒绝签约、18已作废、19后台待审核、20 后台新建包装前已编辑)
	 * @return
	 */
	@ExcelField(title="状态",type=1,align=2,sort=22)
	public String getBorrowStatus() {
		if("11".equals(borrowStatus)){
			return "还款中";
		}
		else if("12".equals(borrowStatus)){
			return "已结清";
		}
		else if("111".equals(borrowStatus)){
			return "已逾期";
		}else if("112".equals(borrowStatus)){
            return "已垫付";
		}
		else if("113".equals(borrowStatus)){
            return "已冲抵";
        }
		else{
			return "";
		}
		
	}
	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}
	public BigDecimal getSumCount() {
		return sumCount;
	}
	public void setSumCount(BigDecimal sumCount) {
		this.sumCount = sumCount;
	}
	@ExcelField(title="标的编号",type=1,align=2,sort=10)
	public String getBorrowaliasno() {
		return borrowaliasno;
	}
	public void setBorrowaliasno(String borrowaliasno) {
		this.borrowaliasno = borrowaliasno;
	}
	
	
	public Date getOpenborrowDate() {
		return openborrowDate;
	}
	public void setOpenborrowDate(Date openborrowDate) {
		this.openborrowDate = openborrowDate;
	}
	public String getRiskWarning() {
		return riskWarning;
	}
	public void setRiskWarning(String riskWarning) {
		this.riskWarning = riskWarning;
	}
	public String getUsageofloan() {
		return usageofloan;
	}
	public void setUsageofloan(String usageofloan) {
		this.usageofloan = usageofloan;
	}
	@Override
	public String toString() {
		return "PuBiao [borrowAlias=" + borrowAlias + ", loanNumber=" + loanNumber + ", name=" + name + ", mobile="
				+ mobile + ", borrowAmount=" + borrowAmount + ", anualrate=" + anualrate + ", deadline=" + deadline
				+ ", payMethod=" + payMethod + ", remainAmount=" + remainAmount + ", loanTime=" + loanTime + ", bili="
				+ bili + ", sumCount=" + sumCount + " ,borrowaliasno="+borrowaliasno+"]";
	}

	
	

}
