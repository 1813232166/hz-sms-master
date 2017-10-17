/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.entity;

import org.hibernate.validator.constraints.Length;

import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

import java.util.Date;

/**
 * 积分发放列表Entity
 * @author hdj
 * @version 2016-10-24
 */
public class ActivityPoint extends DataEntity<ActivityPoint> {
	
	private static final long serialVersionUID = 1L;
	private String activityid;		// 活动ID
	private Integer amount;		// 积分数
	private String sourcechannel;		// 渠道（1web、2安卓、3苹果、4微信、5其它）
	private String consumPoint;		// 积分消费（1兑换虚拟物品，2现金卷， 3体验金）
	private String status;		// 发放状态（1待发2已发3失败）
	private Date sendDate;		//发放时间
	private Date beginSendDate;		// 开始 发放时间
	private Date endSendDate;		// 结束 发放时间
	private String realName;    // 用户真实姓名
	private String mobile;			//用户手机号
	private String delFlag;		//积分状态（1有效 2 冻结）
	private String activityName;	//活动名称
	private Date usedDate;			//使用时间
	private Date beginUsedDate;		// 开始 使用时间
	private Date endUsedDate;		// 结束 使用时间
	private String pointSign; // 积分标记（1获得积分 2消费积分）
	
	public ActivityPoint() {
		super();
	}

	public ActivityPoint(String id){
		super(id);
	}
	
	@ExcelField(title="使用时间",align=2,sort=60,groups=2)
	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public Date getBeginUsedDate() {
		return beginUsedDate;
	}

	public void setBeginUsedDate(Date beginUsedDate) {
		this.beginUsedDate = beginUsedDate;
	}

	public Date getEndUsedDate() {
		return endUsedDate;
	}

	public void setEndUsedDate(Date endUsedDate) {
		this.endUsedDate = endUsedDate;
	}

	@ExcelField(title="发放时间",align=2,sort=60,groups=1)
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	@ExcelField(title="活动名称",align=2,sort=30,groups=1)
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@ExcelField(title="姓名",align=2,sort=0,groups={1,2,3})
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	@ExcelField(title="手机号",align=2,sort=10,groups={1,2,3})
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(min=1, max=32, message="活动ID长度必须介于 1 和 32 之间")
	public String getActivityid() {
		return activityid;
	}

	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}

	@ExcelField(title="数量(分)",align=2,sort=20,groups={1,2})
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=10, message="来源（01web、02安卓、03苹果、04微信、05其它）长度必须介于 0 和 10 之间")
	@ExcelField(title="渠道",align=2,sort=40,groups={1,2})
	public String getSourcechannel() {
		return sourcechannel;
	}

	public void setSourcechannel(String sourcechannel) {
		this.sourcechannel = sourcechannel;
	}
	
	@Length(min=0, max=10, message="去向（01兑换虚拟物品，02现金卷， 03体验金）长度必须介于 0 和 10 之间")
	@ExcelField(title="类型",align=2,sort=30,groups=2)
	public String getConsumPoint() {
		return consumPoint;
	}

	public void setConsumPoint(String consumPoint) {
		this.consumPoint = consumPoint;
	}
	@ExcelField(title="状态",align=2,sort=50,groups=1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getBeginSendDate() {
		return beginSendDate;
	}

	public void setBeginSendDate(Date beginSendDate) {
		this.beginSendDate = beginSendDate;
	}

	public Date getEndSendDate() {
		return endSendDate;
	}

	public void setEndSendDate(Date endSendDate) {
		this.endSendDate = endSendDate;
	}

  public String getPointSign() {
    return pointSign;
  }

  public void setPointSign(String pointSign) {
    this.pointSign = pointSign;
  }

}
