package com.hzwealth.sms.modules.repaymentmanage.entity;
import java.math.BigDecimal;
import java.util.Date;

import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;
/**
 * 出借计划实体类   
 */
public class LendPlan {
    
	//t_finance
	
	private String id;
	private String parent;
	private String productName;   //品类名称'
	private String closeTime;    //封闭期限
	private String unit;    //'单位',
	private BigDecimal rate;    //年利率',
	private String operator;   //录入人
	private Date opTime;   //录入时间
	private String reinvestId;    //线下产品ID
	private String productUrl;   //产品介绍链接
	private String introduce;   //产品介绍
	private Integer recommend;   //首页产品推荐(1-推荐，其他-未被推荐)'
	private Integer repay_style;   //还款方式(2-到期还本;3-一次性还款)',
	
	//t_finance_product
	
	private String name;    //理财名称
	//private Integer repayStyle;  //还款方式(2-到期还本;3-一次性还款)',
	private String status;   //'状态(1.收益中；2.已结清;3待审核；4招标中;5审核未通过）',
	private BigDecimal amount;   //资金总额',
	private BigDecimal minInvestAmount;  //最小投资金额
	private BigDecimal maxInvestAmount;   //最大投资金额
	private Date startTime;   //开始时间
	private Date endTime;   //结束时间
	
	
	//t_invest
	private String deadline;  //期数     Integer  因导出数据需求改为String
	private String investor;   //投资人id
	private String primaryId;    //主键
	
	//自定义变量
	private BigDecimal count;   //还款金额
	private BigDecimal sumCount;  //还款总金额
	private Integer  repayCount;  //已经还款次数
	private Date lastRepayDate;     //最后一次的还款时间
	private String repayNumber;    //还款期数
	private Integer repayStyle;   //repay_style 别名
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title="状态",type=1,align=2,sort=10)
	public String getStatus() {
		if(status.equals("1")){
			
			return "收益中";
		}else if(status.equals("2")){
			return "已结清";
		}else if(status.equals("3")){
			
			return "待审核";
		}else if(status.equals("4")){
			
			return "招标中";
		}else if(status.equals("5")){
			
			return "审核未通过";
		}else{
			
			return "";
		}
			
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ExcelField(title="标的标号",type=1,align=2,sort=1)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	@ExcelField(title="标的名称",type=1,align=2,sort=2)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@ExcelField(title="出借年化利率",type=1,align=2,sort=4)
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOpTime() {
		return opTime;
	}
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}
	public String getReinvestId() {
		return reinvestId;
	}
	public void setReinvestId(String reinvestId) {
		this.reinvestId = reinvestId;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	
	@ExcelField(title="出借期限",type=1,align=2,sort=5)
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	@ExcelField(title="还款金额",type=1,align=2,sort=7)
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
	
	public Integer getRepayCount() {
		return repayCount;
	}
	public void setRepayCount(Integer repayCount) {
		this.repayCount = repayCount;
	}
	@ExcelField(title="还款时间",type=1,align=2,sort=9)
	public Date getLastRepayDate() {
		return lastRepayDate;
	}
	public void setLastRepayDate(Date lastRepayDate) {
		this.lastRepayDate = lastRepayDate;
	}
	@ExcelField(title="借款金额",type=1,align=2,sort=3)
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}
	public BigDecimal getSumCount() {
		return sumCount;
	}
	public void setSumCount(BigDecimal sumCount) {
		this.sumCount = sumCount;
	}
	@ExcelField(title="还款期数",type=1,align=2,sort=8)
	public String getRepayNumber() {
		repayNumber=repayCount+"/"+deadline;
		if(repayCount!=null){
			return repayNumber;
		}
		return "";
	}
	public void setRepayNumber(String repayNumber) {
		this.repayNumber = repayNumber;
	}
	@ExcelField(title="还款方式",type=1,align=2,sort=6)
	public String getRepayStyle() {
		if(2==repayStyle){
			return "一次还清";
		}else if(3==repayStyle){
			return "等额还清";
		}
		return null;
	}
	public void setRepayStyle(Integer repayStyle) {
		this.repayStyle = repayStyle;
	}
	public Integer getRepay_style() {
		return repay_style;
	}
	public void setRepay_style(Integer repay_style) {
		this.repay_style = repay_style;
	}
	public BigDecimal getMinInvestAmount() {
		return minInvestAmount;
	}
	public void setMinInvestAmount(BigDecimal minInvestAmount) {
		this.minInvestAmount = minInvestAmount;
	}
	public BigDecimal getMaxInvestAmount() {
		return maxInvestAmount;
	}
	public void setMaxInvestAmount(BigDecimal maxInvestAmount) {
		this.maxInvestAmount = maxInvestAmount;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	
	
	
	
 
}
