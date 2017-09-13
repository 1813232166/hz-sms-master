package com.hzdjr.hzwd.modules.rebate.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 投资记录
 * @author wzb
 *
 */
public class InvestRecord extends DataEntity<InvestRecord>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 投资人ID
	 */
	private String userId;
	/**
	 * 投资人手机号
	 */
	private String userMobile;
	/**
	 * 出借时间
	 */
	private Date investTime;
	
	//查询字段，开始，结束出借时间
	private Date startInvestTime;
	private Date endInvestTime;
	
	/**
	 * 出借金额
	 */
	private BigDecimal investAmount;
	/**
	 * 出借期限
	 */
	private Integer deadline;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 标号（编号）
	 */
	private String projectNo;
	/**
	 * 订单类型（1：新手标，2：出借计划，3：散标）
	 */
	private String orderType;
	/**
	 * 0:新增，1：已经生成返佣单
	 */
	private String status;
	/**
	 * 来源（1：1.0，2：2.0）
	 */
	private String resource;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 用户姓名，本应该写user类，为了简单，故添加一个姓名即可
	 */
	private String userName;
	
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public BigDecimal getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}
	public Integer getDeadline() {
		return deadline;
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getStartInvestTime() {
		return startInvestTime;
	}
	public void setStartInvestTime(Date startInvestTime) {
		this.startInvestTime = startInvestTime;
	}
	public Date getEndInvestTime() {
		return endInvestTime;
	}
	public void setEndInvestTime(Date endInvestTime) {
		this.endInvestTime = endInvestTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "InvestRecord [userId=" + userId + ", userMobile=" + userMobile + ", investTime=" + investTime
				+ ", startInvestTime=" + startInvestTime + ", endInvestTime=" + endInvestTime + ", investAmount="
				+ investAmount + ", deadline=" + deadline + ", projectName=" + projectName + ", projectNo=" + projectNo
				+ ", orderType=" + orderType + ", status=" + status + ", resource=" + resource + ", orderId=" + orderId
				+ ", userName=" + userName + "]";
	}

}
