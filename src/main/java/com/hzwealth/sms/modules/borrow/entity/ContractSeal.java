/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.modules.sys.entity.User;

/**
 * 签章列表Entity
 * @author l
 * @version 2016-11-24
 */
public class ContractSeal extends DataEntity<ContractSeal> {
	
	private static final long serialVersionUID = 1L;
	private String contractType;		// PTZCXY:平台注册协议，CFCASQS:cfca申请书，HZKHSQS:汇中客户申请书，FYZHXY:富友账户协议，CJZXXY:出借咨询协议
	private User userId;		// 用户id
	private String referenceId;		// 引用id：合同类型是CJZXXY:合同id，其他类型为用户id
	private String fileName;		// 合同名称
	private String keywords;		// 个人签名用：身份证号
	private String idNum;		// 个人签名用：身份证号
	private String imgText;		// 个人签名用：用户名
	private String companyType;		// 公司章：hzph，hzlt（多个用，号隔开）
	private String sealType;		// 必选（0-签名（数字证书） 1-盖章（公司章） 2-签名且盖章）
	private String status;		// 0：新建，1生成pdf失败or上传文件服务器失败，2:线下签章中，3：签章成功，4：签章失败,9:作废
	private String sealInfo;		// 签章状态信息
	private String pdfPath;		// 新建pdf路径
	private String sealedPdfPath;		// 签完章pdf路劲
	private Date createTime;		// create_time
	private Date updateTime;		// update_time
	private String sealedCount;		// 签章次数
	private Date beginCreateTime;		// 开始 create_time
	private Date endCreateTime;		// 结束 create_time
	private String borrowName;//借款人
	private String borrowAliasNo; //标的编号
	private String borrowAlias;//标的名称
	private String investName;//出借人
	private String contractSeal;//合同编号
	private String cuserName;//出借人名字
	private String userType;//出借人名字
	private String loanNumber;//借款编号
	private String userName;//借款人
	private String investAmount;//合同金额
	private String DEADLINE;//借款期限
	private String ANUALRATE;//年利率
	private Date interestAccrualDate;//签订时间
	
	public ContractSeal() {
		super();
	}

	public ContractSeal(String id){
		super(id);
	}

	@Length(min=0, max=60, message="PTZCXY:平台注册协议，CFCASQS:cfca申请书，HZKHSQS:汇中客户申请书，FYZHXY:富友账户协议，CJZXXY:出借咨询协议长度必须介于 0 和 60 之间")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=60, message="引用id：合同类型是CJZXXY:合同id，其他类型为用户id长度必须介于 0 和 60 之间")
	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
	@Length(min=0, max=60, message="合同名称长度必须介于 0 和 60 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Length(min=0, max=60, message="个人签名用：身份证号长度必须介于 0 和 60 之间")
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Length(min=0, max=60, message="个人签名用：身份证号长度必须介于 0 和 60 之间")
	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
	@Length(min=0, max=60, message="个人签名用：用户名长度必须介于 0 和 60 之间")
	public String getImgText() {
		return imgText;
	}

	public void setImgText(String imgText) {
		this.imgText = imgText;
	}
	
	@Length(min=0, max=60, message="公司章：hzph，hzlt（多个用，号隔开）长度必须介于 0 和 60 之间")
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	@Length(min=0, max=2, message="必选（0-签名（数字证书） 1-盖章（公司章） 2-签名且盖章）长度必须介于 0 和 2 之间")
	public String getSealType() {
		return sealType;
	}

	public void setSealType(String sealType) {
		this.sealType = sealType;
	}
	
	@Length(min=0, max=2, message="0：新建，1生成pdf失败or上传文件服务器失败，2:线下签章中，3：签章成功，4：签章失败,9:作废长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="签章状态信息长度必须介于 0 和 255 之间")
	public String getSealInfo() {
		return sealInfo;
	}

	public void setSealInfo(String sealInfo) {
		this.sealInfo = sealInfo;
	}
	
	@Length(min=0, max=255, message="新建pdf路径长度必须介于 0 和 255 之间")
	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
	@Length(min=0, max=255, message="签完章pdf路劲长度必须介于 0 和 255 之间")
	public String getSealedPdfPath() {
		return sealedPdfPath;
	}

	public void setSealedPdfPath(String sealedPdfPath) {
		this.sealedPdfPath = sealedPdfPath;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=11, message="签章次数长度必须介于 0 和 11 之间")
	public String getSealedCount() {
		return sealedCount;
	}

	public void setSealedCount(String sealedCount) {
		this.sealedCount = sealedCount;
	}
	
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getBorrowAliasNo() {
		return borrowAliasNo;
	}

	public void setBorrowAliasNo(String borrowAliasNo) {
		this.borrowAliasNo = borrowAliasNo;
	}

	public String getBorrowAlias() {
		return borrowAlias;
	}

	public void setBorrowAlias(String borrowAlias) {
		this.borrowAlias = borrowAlias;
	}

	public String getInvestName() {
		return investName;
	}

	public void setInvestName(String investName) {
		this.investName = investName;
	}

	public String getContractSeal() {
		return contractSeal;
	}

	public void setContractSeal(String contractSeal) {
		this.contractSeal = contractSeal;
	}

	public String getCuserName() {
		return cuserName;
	}

	public void setCuserName(String cuserName) {
		this.cuserName = cuserName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}

	public String getDEADLINE() {
		return DEADLINE;
	}

	public void setDEADLINE(String dEADLINE) {
		DEADLINE = dEADLINE;
	}

	public String getANUALRATE() {
		return ANUALRATE;
	}

	public void setANUALRATE(String aNUALRATE) {
		ANUALRATE = aNUALRATE;
	}

	public Date getInterestAccrualDate() {
		return interestAccrualDate;
	}

	public void setInterestAccrualDate(Date interestAccrualDate) {
		this.interestAccrualDate = interestAccrualDate;
	}
}