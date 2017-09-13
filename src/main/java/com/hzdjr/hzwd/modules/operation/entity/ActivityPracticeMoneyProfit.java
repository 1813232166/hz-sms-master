/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

/**
 * 体验金收益列表Entity
 * @author hdj
 * @version 2016-10-31
 */
public class ActivityPracticeMoneyProfit extends DataEntity<ActivityPracticeMoneyProfit> {
	
	private static final long serialVersionUID = 1L;
	private String totalmoney;		// 发放总金额
	private String status;		// 发放状态（1已发2待发3失败）
	private Date beginCreateDate;		// 开始 创建时间/收益时间
	private Date endCreateDate;		// 结束 创建时间/收益时间
	private Date createDate;	//受益时间
	
	public ActivityPracticeMoneyProfit() {
		super();
	}

	public ActivityPracticeMoneyProfit(String id){
		super(id);
	}
	@ExcelField(title="收益时间",align=2,sort=0)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ExcelField(title="金额（元）",align=2,sort=10)
	public String getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}
	@ExcelField(title="发放状态",align=2,sort=30)
	@Length(min=1, max=1, message="发放状态（1已发2待发3失败）长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}