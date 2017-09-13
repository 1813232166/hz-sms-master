/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.exitmanage.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;
import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

/**
 * 退出管理-查看Entity
 * @author HDG
 * @version 2017-07-25
 */
public class TExitData extends DataEntity<TExitData> {
	
	private static final long serialVersionUID = 1L;
	private String canQuitAmount;		// 今日可退出金额（元）
	private String quitAmount;		// 今日已退出金额（元）
	private Date quitTime;		// 退出时间
	private String status;		// 状态(1:未发布，2:待审核，3:审核未通过,3:进行中)
	private String detail;		// 备注
	private String num;		// 退出笔数
	
	//查询条件
	private Date beginTimes;
	private Date endTimes;
	public TExitData() {
		super();
	}

	public TExitData(String id){
		super(id);
	}

	@ExcelField(title="今日可退出金额(元)", align=1, sort=10)
	public String getCanQuitAmount() {
		return canQuitAmount;
	}

	public void setCanQuitAmount(String canQuitAmount) {
		this.canQuitAmount = canQuitAmount;
	}
	
	@ExcelField(title="今日已退出金额(元)", align=1, sort=20)
	public String getQuitAmount() {
		return quitAmount;
	}

	public void setQuitAmount(String quitAmount) {
		this.quitAmount = quitAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="退出时间", align=1, sort=30)
	public Date getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}
	
	@Length(min=0, max=2, message="状态(1:未发布，2:待审核，3:审核未通过,3:进行中)长度必须介于 0 和 2 之间")
	@ExcelField(title="状态", align=1, sort=40)
	public String getStatus() {
		if(status.equals("1")){
			return "未发布";
		}else if(status.equals("2")){
			return "待审核";
		}else if(status.equals("3")){
			return "审核未通过";
		}else if(status.equals("4")){
			return "进行中";
		}else{
			return "未知状态";
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	@ExcelField(title="备注", align=1, sort=50)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=0, max=11, message="退出笔数长度必须介于 0 和 11 之间")
	@ExcelField(title="退出笔数", align=1, sort=60)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Date getBeginTimes() {
		return beginTimes;
	}

	public void setBeginTimes(Date beginTimes) {
		this.beginTimes = beginTimes;
	}

	public Date getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(Date endTimes) {
		this.endTimes = endTimes;
	}
	
}