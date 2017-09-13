/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

/**
 * 加息券管理Entity
 * @author hdj
 * @version 2016-11-02
 */
public class ActivityIrcoupon extends DataEntity<ActivityIrcoupon> {
	
	private static final long serialVersionUID = 1L;
	private String id;		//加息券ID
	private String name;		// 加息券名称
	private String scale;		// 加息比例
	private String profittime;		// 加息天数(永久为 -1)
	private String sendnumber;		// 发放数量
	private String usednumber;		// 使用数量
	private String expirenumber;		// 过期数量
	private String profitamount;		// 奖励金额
	private Date startdate;		// 开始时间（永久没有开始时间）
	private Date enddate;		// 结束时间（永久没有结束时间）
	private String useconditionstatus;		// 使用条件(0无限制1有限制)
	private String useconditionamount;		// 出借金额大于等于金额
	private String usetype;		// 类型（1普享标2出借计划3全部）
	private String status;		// 状态(1待发布 2进行中 3已结束 4已停止 )
	private Date beginStartdate;		// 开始 开始时间（永久没有开始时间）
	private Date endStartdate;		// 结束 开始时间（永久没有开始时间）
	private String expDate;		//有效期
	
	public ActivityIrcoupon() {
		super();
	}

	public ActivityIrcoupon(String id){
		super(id);
	}
	
	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	@ExcelField(title="ID",align=2,sort=0)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Length(min=1, max=50, message="加息券名称长度必须介于 1 和 50 之间")
	@ExcelField(title="名称",align=2,sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title="加息比例",align=2,sort=20)
	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
	
	@Length(min=0, max=4, message="加息天数(永久为 -1)长度必须介于 0 和 4 之间")
	@ExcelField(title="加息时间",align=2,sort=30)
	public String getProfittime() {
		return profittime;
	}

	public void setProfittime(String profittime) {
		this.profittime = profittime;
	}
	
	@Length(min=0, max=4, message="发放数量长度必须介于 0 和 4 之间")
	@ExcelField(title="发放数量（张）",align=2,sort=40)
	public String getSendnumber() {
		return sendnumber;
	}

	public void setSendnumber(String sendnumber) {
		this.sendnumber = sendnumber;
	}
	
	@Length(min=0, max=4, message="使用数量长度必须介于 0 和 4 之间")
	@ExcelField(title="使用数量（张）",align=2,sort=50)
	public String getUsednumber() {
		return usednumber;
	}

	public void setUsednumber(String usednumber) {
		this.usednumber = usednumber;
	}
	
	@Length(min=0, max=4, message="过期数量长度必须介于 0 和 4 之间")
	@ExcelField(title="过期数量（张）",align=2,sort=60)
	public String getExpirenumber() {
		return expirenumber;
	}

	public void setExpirenumber(String expirenumber) {
		this.expirenumber = expirenumber;
	}
	@ExcelField(title="奖励金额（元）",align=2,sort=70)
	public String getProfitamount() {
		return profitamount;
	}

	public void setProfitamount(String profitamount) {
		this.profitamount = profitamount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="有效期开始时间",align=2,sort=90)
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="有效期结束时间",align=2,sort=100)
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	@Length(min=0, max=1, message="使用条件(0无限制1有限制)长度必须介于 0 和 1 之间")
	public String getUseconditionstatus() {
		return useconditionstatus;
	}

	public void setUseconditionstatus(String useconditionstatus) {
		this.useconditionstatus = useconditionstatus;
	}
	
	public String getUseconditionamount() {
		return useconditionamount;
	}

	public void setUseconditionamount(String useconditionamount) {
		this.useconditionamount = useconditionamount;
	}
	
	@Length(min=0, max=1, message="类型（1普享标2出借计划3全部）长度必须介于 0 和 1 之间")
	public String getUsetype() {
		return usetype;
	}

	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}
	
	@Length(min=0, max=1, message="状态(1待发布 2进行中 3已结束 4已停止 )长度必须介于 0 和 1 之间")
	@ExcelField(title="状态",align=2,sort=80)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getBeginStartdate() {
		return beginStartdate;
	}

	public void setBeginStartdate(Date beginStartdate) {
		this.beginStartdate = beginStartdate;
	}
	
	public Date getEndStartdate() {
		return endStartdate;
	}

	public void setEndStartdate(Date endStartdate) {
		this.endStartdate = endStartdate;
	}
		
}