package com.hzwealth.sms.modules.rebate.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.DataEntity;

/**
 * 用户表的延伸
 * @author wzb
 *
 */
public class ExtendUser extends DataEntity<ExtendUser>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户手机号
	 */
	private String userMobile;
	
	/**
	 * 二级邀请出借金额
	 */
	private BigDecimal sumBInviteInvestAmount;
	/**
	 * 应返金额
	 */
	private BigDecimal sumRebateAmount;
	/**
	 * 已返金额
	 */
	private BigDecimal sumCompleteRebateAmount;
	/**
	 * 个人出借金额
	 */
	private BigDecimal sumInvestAmount;
	/**
	 * 一级邀请出借金额
	 */
	private BigDecimal sumAInviteInvestAmount;
	
	//用户表中的字段
	
	private String userName;

	private String refferType;//1：推荐人，2：理财师

	//user表中统计字段
	private String refferLevel;//等级
	private Integer refferUserCount;//被邀请注册人数
	private Integer refferIdcardCount;//被邀请实名人数
	private Integer refferInvestCount;//被邀请出借人数
	
	private String refferee;//推荐码
	private String staffId;//员工编号(理财师注册使用)
	
	private ExtendUser refferUser; //邀请人信息
	/**
	 * 被邀请人投资后，返给邀请人的记录
	 */
	private List<InvestRebate> rebateList;
	//同步标志
	private String syncFlag;
	
	//配置
	private Map<String,BigDecimal> rakebackConfigMap;
	/**
	 * 导出标志
	 */
	private String exportFlag;
	
	public String getSyncFlag() {
		return syncFlag;
	}
	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRefferType() {
		return refferType;
	}
	public void setRefferType(String refferType) {
		this.refferType = refferType;
	}
	public String getRefferLevel() {
		return refferLevel;
	}
	public void setRefferLevel(String refferLevel) {
		this.refferLevel = refferLevel;
	}
	public Integer getRefferUserCount() {
		return refferUserCount;
	}
	public void setRefferUserCount(Integer refferUserCount) {
		this.refferUserCount = refferUserCount;
	}
	public Integer getRefferIdcardCount() {
		return refferIdcardCount;
	}
	public void setRefferIdcardCount(Integer refferIdcardCount) {
		this.refferIdcardCount = refferIdcardCount;
	}
	public Integer getRefferInvestCount() {
		return refferInvestCount;
	}
	public void setRefferInvestCount(Integer refferInvestCount) {
		this.refferInvestCount = refferInvestCount;
	}
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
	public BigDecimal getSumBInviteInvestAmount() {
		return sumBInviteInvestAmount;
	}
	public void setSumBInviteInvestAmount(BigDecimal sumBInviteInvestAmount) {
		this.sumBInviteInvestAmount = sumBInviteInvestAmount;
	}
	public BigDecimal getSumRebateAmount() {
		return sumRebateAmount;
	}
	public void setSumRebateAmount(BigDecimal sumRebateAmount) {
		this.sumRebateAmount = sumRebateAmount;
	}
	public BigDecimal getSumCompleteRebateAmount() {
		return sumCompleteRebateAmount;
	}
	public void setSumCompleteRebateAmount(BigDecimal sumCompleteRebateAmount) {
		this.sumCompleteRebateAmount = sumCompleteRebateAmount;
	}
	public BigDecimal getSumInvestAmount() {
		return sumInvestAmount;
	}
	public void setSumInvestAmount(BigDecimal sumInvestAmount) {
		this.sumInvestAmount = sumInvestAmount;
	}
	public BigDecimal getSumAInviteInvestAmount() {
		return sumAInviteInvestAmount;
	}
	public void setSumAInviteInvestAmount(BigDecimal sumAInviteInvestAmount) {
		this.sumAInviteInvestAmount = sumAInviteInvestAmount;
	}
	public List<InvestRebate> getRebateList() {
		return rebateList;
	}
	public void setRebateList(List<InvestRebate> rebateList) {
		this.rebateList = rebateList;
	}
	public ExtendUser getRefferUser() {
		return refferUser;
	}
	public void setRefferUser(ExtendUser refferUser) {
		this.refferUser = refferUser;
	}
	public String getRefferee() {
		return refferee;
	}
	public void setRefferee(String refferee) {
		this.refferee = refferee;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public Map<String, BigDecimal> getRakebackConfigMap() {
		return rakebackConfigMap;
	}
	public void setRakebackConfigMap(Map<String, BigDecimal> rakebackConfigMap) {
		this.rakebackConfigMap = rakebackConfigMap;
	}
	public String getBlurUserName(){
		String blurName = "";
		if(userName != null){
			if(userName.length() > 3){
				blurName = userName.substring(0, 2);
				blurName = blurName + "**";
			}else if(userName.length() > 2){
				blurName = userName.substring(0,1);
				blurName = blurName + "**";
			}else if(userName.length() > 1){
				blurName = userName.substring(0,1);
				blurName = blurName + "*";
			}else{
				blurName = userName;
			}
		}
		return blurName;
	}
	public String getBlurUserMobile(){
		String blurMobile = "";
		if(userMobile != null && userMobile.length() >= 11){
			blurMobile = userMobile.substring(0, 3);
			blurMobile = blurMobile + "*******" + userMobile.substring(9);
		}else if(userMobile != null && userMobile.length() >= 3){
			blurMobile = userMobile.substring(0, 3);
			for(int i = 3; i < userMobile.length();i++){
				blurMobile = blurMobile + "*";
			}
		}else{
			blurMobile = userMobile;
		}
		return blurMobile;
	}
	public String getExportFlag() {
		return exportFlag;
	}
	public void setExportFlag(String exportFlag) {
		this.exportFlag = exportFlag;
	}
	
}
