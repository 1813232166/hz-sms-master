package com.hzwealth.sms.modules.rebate.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.hzwealth.sms.common.persistence.DataEntity;
/**
 * 返佣记录
 * @author wzb
 *
 */
public class RebateRecord extends DataEntity<RebateRecord>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 返佣人数
	 */
	private Integer rebateUserAccount;
	/**
	 * 返佣月份(2017.6)
	 */
	private Integer rebateMonth;
	/**
	 * 返佣年(2017.6)
	 */
	private Integer rebateYear;
	/**
	 * 应返金额
	 */
	private BigDecimal rebateAmount;
	/**
	 * 已返金额
	 */
	private BigDecimal completeRebateAmount;
	/**
	 * 返佣笔数
	 */
	private Integer rebateAccount;
	/**
	 * 返佣时间
	 */
	private Date rebateDate;
	/**
	 * 返佣标识（0：未返佣,1：返佣中，1：返佣完成），如果多条返佣单，则可能出现返佣中，需要多次返佣的情况。
	 */
	private String status;
	
	/**
	 * 返佣记录
	 */
	private List<InvestRebate> rebateList;
	
	public Integer getRebateMonth() {
		return rebateMonth;
	}
	public void setRebateMonth(Integer rebateMonth) {
		this.rebateMonth = rebateMonth;
	}
	public Integer getRebateYear() {
		return rebateYear;
	}
	public void setRebateYear(Integer rebateYear) {
		this.rebateYear = rebateYear;
	}
	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}
	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}
	public BigDecimal getCompleteRebateAmount() {
		return completeRebateAmount;
	}
	public void setCompleteRebateAmount(BigDecimal completeRebateAmount) {
		this.completeRebateAmount = completeRebateAmount;
	}
	public Integer getRebateAccount() {
		return rebateAccount;
	}
	public void setRebateAccount(Integer rebateAccount) {
		this.rebateAccount = rebateAccount;
	}
	public Date getRebateDate() {
		return rebateDate;
	}
	public void setRebateDate(Date rebateDate) {
		this.rebateDate = rebateDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRebateUserAccount() {
		return rebateUserAccount;
	}
	public void setRebateUserAccount(Integer rebateUserAccount) {
		this.rebateUserAccount = rebateUserAccount;
	}
	public List<InvestRebate> getRebateList() {
		return rebateList;
	}
	public void setRebateList(List<InvestRebate> rebateList) {
		this.rebateList = rebateList;
	}
	@Override
	public String toString() {
		return "RebateRecord [rebateUserAccount=" + rebateUserAccount + ", rebateMonth=" + rebateMonth + ", rebateYear="
				+ rebateYear + ", rebateAmount=" + rebateAmount + ", completeRebateAmount=" + completeRebateAmount
				+ ", rebateAccount=" + rebateAccount + ", rebateDate=" + rebateDate + ", status=" + status
				+ ", rebateList=" + rebateList + "]";
	}
	
}
