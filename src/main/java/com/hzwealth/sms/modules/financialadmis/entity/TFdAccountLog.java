/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.financialadmis.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;


/**
 * 财务管理Entity
 * @author xq
 * @version 2016-10-17
 */
public class TFdAccountLog extends DataEntity<TFdAccountLog> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 前台用户号
	private String mobile;		// 用户手机号
	private Date acdate;		// 发生日期
	private String biztype;		// 业务类型(2-充值;5-提现/放款提现;7-还款/本息;11-手工投资理财;12-手工投资散标;13-自动投资理财;14-自动投资散标;21-还本金;22-还利息;30-投资奖励;31-推荐用户奖励;32-活动返现;33-活动提成;61-提现手续费;62-利息管理费;63-实名认证管理费)
	private String debit;		// 借方金额(收入)
	private String credit;		// 贷方金额（支出）
	private String bal;		// 账户余额（有些涉及余额的不是实时确定，需考虑哪些业务类型需要更新，该字段需慎重考虑）
	private String bizid;		// 业务编号(开头BWD:后台系统录入取现金额；BOR:后台系统录入充值金额)
	private Date createtime;		// 创建时间
	private String status;		// 状态(1-新增，2-成功，3-失败, 4-理财投资成功, 5-理财预约投资(扣款), 6-理财预约投资失败(回款)),7-(理财投资回款)
	private String remark;		// 备注
	private String termno;		// 其他终端返回流水号
	private String operuserid;		// 后台用户号
	
	private String tradeNo;    //平台交易流水号
	private Date beginTime;
	private Date overTime;
	
	
	
	//表结构变动后的 个人账户  中的交易时间
	private Date createDate;
	public TFdAccountLog() {
		super();
	}

	public TFdAccountLog(String id){
		super(id);
	}

	
	@Length(min=11, max=11, message="用户手机号长度必须 11 ")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(min=1, max=32, message="前台用户号长度必须介于 1 和 32 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcdate() {
		return acdate;
	}

	public void setAcdate(Date acdate) {
		this.acdate = acdate;
	}
	
	@Length(min=1, max=2, message="业务类型(01-充值;02-提现;11-手工投资理财;12-手工投资散标;13-自动投资理财;14-自动投资散标;21-还本金;22-还利息;30-投资奖励;31-推荐用户奖励;32-活动返现;33-活动提成;61-提现手续费;62-利息管理费;63-实名认证管理费)长度必须介于 1 和 2 之间")
	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}
	
	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}
	
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	public String getBal() {
		return bal;
	}

	public void setBal(String bal) {
		this.bal = bal;
	}
	
	@Length(min=1, max=36, message="业务编号(开头BWD:后台系统录入取现金额；BOR:后台系统录入充值金额)长度必须介于 1 和 36 之间")
	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=6, message="状态(1-新增，2-成功，3-失败, 4-理财投资成功, 5-理财预约投资(扣款), 6-理财预约投资失败(回款)),7-(理财投资回款)长度必须介于 0 和 6 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=36, message="其他终端返回流水号长度必须介于 0 和 36 之间")
	public String getTermno() {
		return termno;
	}

	public void setTermno(String termno) {
		this.termno = termno;
	}
	
	@Length(min=0, max=32, message="后台用户号长度必须介于 0 和 32 之间")
	public String getOperuserid() {
		return operuserid;
	}

	public void setOperuserid(String operuserid) {
		this.operuserid = operuserid;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @Override
	public String toString() {
		return "TFdAccountLog [userid=" + userid + ", mobile=" + mobile + ", acdate=" + acdate + ", biztype=" + biztype
				+ ", debit=" + debit + ", credit=" + credit + ", bal=" + bal + ", bizid=" + bizid + ", createtime="
				+ createtime + ", status=" + status + ", remark=" + remark + ", termno=" + termno + ", operuserid="
				+ operuserid + ", beginTime=" + beginTime + ", overTime=" + overTime + ", createDate=" + createDate
				+ "]";
	}

	
	
}