package com.hzdjr.hzwd.modules.rebate.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
/**
 * 投资返佣计算
 * @author wzb
 *
 */
public class InvestRebate extends DataEntity<InvestRebate>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 邀请人ID
	 */
	private String userId;
	/**
	 * 邀请人手机号
	 */
	private String userMobile;
	/**
	 * 邀请人类型：邀请人，理财师
	 */
	private String inviteUserType;
	/**
	 * 邀请人等级（记录等级（普通，金牌）快照）邀请人类型类型在user表中1:普通，2：银牌，3：金牌
	 */
	private String inviteGrade;
	/**
	 * 被邀请人类型（一级邀请、二级邀请）
	 */
	private Integer inviteType;
	/**
	 * 返佣类型（一次性返佣）
	 */
	private String rebateType;
	/**
	 * 返佣利率（0.005）
	 */
	private BigDecimal rebateRate;
	/**
	 * 结算方式（线下返佣）
	 */
	private String settlementType;
	/**
	 * 应返金额（1000）
	 */
	private BigDecimal rebateAmount;
	/**
	 * 已返金额（返佣时单条记录不允许多次返佣，必须一次性返佣
	 */
	private BigDecimal completeRebateAmount;
	/**
	 * 返佣时间
	 */
	private Date rebateTime;
	/**
	 * 返佣记录表
	 */
	private RebateRecord rebateRecord;
	
	/**
	 * 返佣订单表
	 */
	private InvestRecord investRecord;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getInviteGrade() {
		return inviteGrade;
	}
	public void setInviteGrade(String inviteGrade) {
		this.inviteGrade = inviteGrade;
	}
	public Integer getInviteType() {
		return inviteType;
	}
	public void setInviteType(Integer inviteType) {
		this.inviteType = inviteType;
	}
	public String getRebateType() {
		return rebateType;
	}
	public void setRebateType(String rebateType) {
		this.rebateType = rebateType;
	}
	public BigDecimal getRebateRate() {
		return rebateRate;
	}
	public void setRebateRate(BigDecimal rebateRate) {
		this.rebateRate = rebateRate;
	}
	public String getSettlementType() {
		return settlementType;
	}
	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
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
	public Date getRebateTime() {
		return rebateTime;
	}
	public void setRebateTime(Date rebateTime) {
		this.rebateTime = rebateTime;
	}
	public String getInviteUserType() {
		return inviteUserType;
	}
	public void setInviteUserType(String inviteUserType) {
		this.inviteUserType = inviteUserType;
	}
	public InvestRecord getInvestRecord() {
		return investRecord;
	}
	public void setInvestRecord(InvestRecord investRecord) {
		this.investRecord = investRecord;
	}
	public RebateRecord getRebateRecord() {
		return rebateRecord;
	}
	public void setRebateRecord(RebateRecord rebateRecord) {
		this.rebateRecord = rebateRecord;
	}
	@Override
	public String toString() {
		return "InvestRebate [userId=" + userId + ", userMobile=" + userMobile + ", inviteUserType=" + inviteUserType
				+ ", inviteGrade=" + inviteGrade + ", inviteType=" + inviteType + ", rebateType=" + rebateType
				+ ", rebateRate=" + rebateRate + ", settlementType=" + settlementType + ", rebateAmount=" + rebateAmount
				+ ", completeRebateAmount=" + completeRebateAmount + ", rebateTime=" + rebateTime + ", rebateRecord="
				+ rebateRecord + ", investRecord=" + investRecord + "]";
	}

}
