/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.financialadmis.entity;


import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

/**
 * 平台账户Entity
 * @author xq
 * @version 2016-10-18
 */
public class CompFdAccountLog extends DataEntity<CompFdAccountLog> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 商户垫资账户SYS_GENERATE_001,
	 * 风险保证金SYS_GENERATE_002,
	 * 收费账户SYS_GENERATE_003,
	 * 奖励金账户SYS_GENERATE_004
	 */
	private String accountId;
	private String userId;
	
	private String bizid;		// 业务编号(开头BWD:后台系统录入取现金额；BOR:后台系统录入充值金额)
	private String biztype;		// 交易类型[1:网银充值,5:提现,6:出借,7:还款,8:放款,9:代偿【垫付】,10:还代偿款,12:流标,13:收益明细提交,14:回款]
	private Date acdate;		// 交易时间
	private String tradeNo;    //平台交易流水号
	private BigDecimal amount; //交易金额
	private BigDecimal commission; //平台佣金(服务费)
	private String debit;		// 收入
	private String credit;		// 支出
	private BigDecimal guarantAmt; //风险保证金
	private String bal;		// 账户余额（有些涉及余额的不是实时确定，需考虑哪些业务类型需要更新，该字段需慎重考虑）
	private Date createtime;		// 创建时间
	private String status;		// 状态(1-新增，2-成功，3-失败, 4-理财投资成功, 5-理财预约投资(扣款), 6-理财预约投资失败(回款)),7-(理财投资回款)
	private String remark;		// 备注
	private String termno;		// 其他终端返回流水号
	private String operuserid;		// 后台用户号
	private String beginTime; //交易开始时间
	private String overTime;
	
	private Integer endLimit; //导出数据
	public CompFdAccountLog() {
		super();
	}

	public CompFdAccountLog(String id){
		super(id);
	}

	@Length(min=1, max=36, message="业务编号(开头BWD:后台系统录入取现金额；BOR:后台系统录入充值金额)长度必须介于 1 和 36 之间")
	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}
	

	@Length(min=1, max=2, message="业务类型(01-充值;02-提现;11-手工投资理财;12-手工投资散标;13-自动投资理财;14-自动投资散标;21-还本金;22-还利息;30-投资奖励;31-推荐用户奖励;32-活动返现;33-活动提成;61-提现手续费;62-利息管理费;63-实名认证管理费)长度必须介于 1 和 2 之间")
	public String getBiztype() {
		if(biztype.equals("01")){
			
			return "充值";
		}else if(biztype.equals("02")){
			return "提现";
		}
		return "";
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}
	
	@ExcelField(title="交易时间",type=1,align=2,sort=2)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcdate() {
		return acdate;
	}

	public void setAcdate(Date acdate) {
		this.acdate = acdate;
	}
	
	
	public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    @ExcelField(title="收入",type=1,align=2,sort=4)
	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}
	
	@ExcelField(title="支出",type=1,align=2,sort=5)
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	
	public BigDecimal getGuarantAmt() {
        return guarantAmt;
    }

    public void setGuarantAmt(BigDecimal guarantAmt) {
        this.guarantAmt = guarantAmt;
    }

    public String getBal() {
		return bal;
	}

	public void setBal(String bal) {
		this.bal = bal;
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
	
	@ExcelField(title="备注",type=1,align=2,sort=6)
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

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    @ExcelField(title="账户类型",type=1,align=2,sort=1)
    @Length(min=1, max=3, message="账户类型长度必须介于 0 和 3 之间")
    public String getUserId() {
        if(userId!=null && !("").equals(userId)){
            if(userId.equals("SYS_GENERATE_001") || (userId.equals("SYS_GENERATE_002"))){
                return "平台垫付账户";
            }
            if(userId.equals("SYS_GENERATE_003") || (userId.equals("SYS_GENERATE_004"))){
                return "服务费账户";
            }
        }
        return "";
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @ExcelField(title="平台交易流水号",type=1,align=2,sort=3)
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getEndLimit() {
        return endLimit;
    }

    public void setEndLimit(Integer endLimit) {
        this.endLimit = endLimit;
    }

    @Override
    public String toString() {
        return "CompFdAccountLog [accountId=" + accountId + ", userId=" + userId + ", bizid=" + bizid + ", acctype="
                + ", biztype=" + biztype + ", acdate=" + acdate + ", tradeNo=" + tradeNo + ", debit=" + debit
                + ", credit=" + credit + ", bal=" + bal + ", createtime=" + createtime + ", status=" + status
                + ", remark=" + remark + ", termno=" + termno + ", operuserid=" + operuserid + ", beginTime="
                + beginTime + ", overTime=" + overTime + "]";
    }
	
	
	
}