/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.financialadmis.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;


/**
 * 财务管理Entity
 * @author xq
 * @version 2016-10-17
 */
public class TFdAccountLogVo extends DataEntity<TFdAccountLogVo> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 前台用户号
	private String mobile;		// 用户手机号
	private Date acdate;		// 发生日期
	private String biztype;		// 业务类型(2-充值;5-提现/放款提现;7-还款/本息;11-手工投资理财;12-手工投资散标;13-自动投资理财;14-自动投资散标;21-还本金;22-还利息;30-投资奖励;31-推荐用户奖励;32-活动返现;33-活动提成;61-提现手续费;62-利息管理费;63-实名认证管理费)
	private BigDecimal debit;		// 借方金额(收入)
	private BigDecimal credit;		// 贷方金额（支出）
	private BigDecimal bal;		// 账户余额（有些涉及余额的不是实时确定，需考虑哪些业务类型需要更新，该字段需慎重考虑）
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
	public TFdAccountLogVo() {
		super();
	}

	public TFdAccountLogVo(String id){
		super(id);
	}

	@ExcelField(title="用户手机号",type=1,align=2,sort=10)
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
	@ExcelField(title="类型",type=1,align=2,sort=30)
	@Length(min=1, max=2, message="业务类型(1:网银充值,5:提现,6:出借,7:还款,8:放款,9:代偿【垫付】,10:还代偿款,12:流标,13:收益明细提交,14:回款)长度必须介于 1 和 2 之间")
	public String getBiztype() {
		if(biztype!=null && !("").equals(biztype)){
			if("1".equals(biztype)||"2".equals(biztype)||"3".equals(biztype)||"4".equals(biztype)){
				return "充值";
			}else if("6".equals(biztype)){
				return "投标";
			}else if("5".equals(biztype) ){
				return "提现";
			}else if("7".equals(biztype)){
				return "还款";
			}else if("8".equals(biztype)){
				return "放款";
			}else if("10".equals(biztype)){
				return "还代偿款";
			}else if("14".equals(biztype)){
                return "本息";
            }
		}
		return "";
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}
	@ExcelField(title="收入(元)",type=1,align=2,sort=40)
	public String getDebit() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
	    return df.format(debit);
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	@ExcelField(title="支出(元)",type=1,align=2,sort=50)
	public String getCredit() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
	    return df.format(credit);
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	//@ExcelField(title="结余(元)",type=1,align=2,sort=60)
	public String getBal() {
		DecimalFormat df=new DecimalFormat("#,##0.00");
	    return df.format(bal);
	}

	public void setBal(BigDecimal bal) {
		this.bal = bal;
	}
	
	@Length(min=1, max=36, message="业务编号(开头BWD:后台系统录入取现金额；BOR:后台系统录入充值金额)长度必须介于 1 和 36 之间")
	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}
	@ExcelField(title="交易时间",type=1,align=2,sort=20)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public String getCreatetime() {
		if(createtime!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createtime);
			return str;
		}else{
			return "";
		}
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
	
	@ExcelField(title="平台交易流水号",type=1,align=2,sort=60)
	public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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

	@Override
	public String toString() {
		return "TFdAccountLog [userid=" + userid + ", mobile=" + mobile + ", acdate=" + acdate + ", biztype=" + biztype
				+ ", debit=" + debit + ", credit=" + credit + ", bal=" + bal + ", bizid=" + bizid + ", createtime="
				+ createtime + ", status=" + status + ", remark=" + remark + ", termno=" + termno + ", operuserid="
				+ operuserid + ", beginTime=" + beginTime + ", overTime=" + overTime + ", createDate=" + createDate
				+ "]";
	}

	
	
}