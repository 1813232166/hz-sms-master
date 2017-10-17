/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.salesupport.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;

/**
 * 活动Entity
 * @author zhouzhankui
 * @version 2017-06-06
 */
public class ActivityInfo extends DataEntity<ActivityInfo> {
	
	private static final long serialVersionUID = 1L;


	private String activityname;		// 活动名称
	private String activitytype;		// 活动类型---1表注册，2表开户，3表首次投资，4表运营活动
	private String introduction;		// 活动简介
	private Date begintime;		// 开始时间格式yy-MM-dd hh:mm:ss
	private Date endtime;		// 结束时间格式yyyy-MM-dd hh:mm:ss
	private Date createDate;		// 创建时间格式yyyy-MM-dd hh:mm:ss
	private Date updatedate;		// 修改时间格式yyyy-MM-dd hh:mm:ss
	private Date publishtime;		// 发布时间格式yyyy-MM-dd hh:mm:ss
	private Date authtime;		// 审核时间格式yyyy-MM-dd hh:mm:ss
	private String createby;		// 创建人
	private String updateby;		// 修改人
	private Date lastchangedtime;		// 最后的时间
	private String activitystatus;		// 活动状态--0代表未发布--1审核中，2代表审核未通过，3代表进行中，4代表已结束，5代表已撤销
	private String activityhref;		// 活动链接
	private String imageurl;		// 活动图片路径
	private String couponid;		// 优惠卷-优惠卷表外键
	private String remark1;		// 备注字段1
	private String remark2;		// 备注字段2
	private String delflag;		// 删除标记（0：正常；1：删除；2：审核）
	
	private String publishtBeginTime;		// 发布开始时间
	private String publishtEndTime;		// 发布结束时间
	
	private String remarks;		// 备注
	
	private String ids;		// 优惠券id集合
	
	
	public ActivityInfo() {
		super();
	}

	public ActivityInfo(String id){
		super(id);
	}

	@ExcelField(title="活动名称",type=1,align=2,sort=10)
	@Length(min=1, max=64, message="活动名称长度必须介于 1 和 64 之间")
	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	

	@ExcelField(title="活动类型",type=1,align=2,sort=20)
	@Length(min=0, max=2, message="活动类型---1表注册，2表开户，3表首次投资，4表运营活动长度必须介于 0 和 2 之间")
	public String getActivitytype() {
		
			return activitytype;
	}

	public void setActivitytype(String activitytype) {
		this.activitytype = activitytype;
	}
	

	@ExcelField(title="活动简介",type=1,align=2,sort=30)
	@Length(min=0, max=300, message="活动简介长度必须介于 0 和 300 之间")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@ExcelField(title="开始时间",type=1,align=2,sort=40)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	
	@ExcelField(title="结束时间",type=1,align=2,sort=50)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	@ExcelField(title="创建时间",type=1,align=2,sort=60)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedate() {
		return createDate;
	}

	public void setCreatedate(Date createDate) {
		this.createDate = createDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@ExcelField(title="发布时间",type=1,align=2,sort=70)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	
	@ExcelField(title="审核时间",type=1,align=2,sort=80)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuthtime() {
		return authtime;
	}

	public void setAuthtime(Date authtime) {
		this.authtime = authtime;
	}
	
	@Length(min=0, max=32, message="创建人长度必须介于 0 和 32 之间")
	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
	@Length(min=0, max=32, message="修改人长度必须介于 0 和 32 之间")
	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastchangedtime() {
		return lastchangedtime;
	}

	public void setLastchangedtime(Date lastchangedtime) {
		this.lastchangedtime = lastchangedtime;
	}
	
	@ExcelField(title="活动状态",type=1,align=2,sort=90)
	@Length(min=0, max=2, message="活动状态--0代表未发布--1审核中，2代表审核未通过，3代表进行中，4代表已结束，5代表已撤销长度必须介于 0 和 2 之间")
	public String getActivitystatus() {

			return activitystatus;
	}

	public void setActivitystatus(String activitystatus) {
		this.activitystatus = activitystatus;
	}
	
	@ExcelField(title="活动链接",type=1,align=2,sort=100)
	@Length(min=0, max=300, message="活动链接长度必须介于 0 和 300 之间")
	public String getActivityhref() {
		return activityhref;
	}

	public void setActivityhref(String activityhref) {
		this.activityhref = activityhref;
	}
	
	
	@ExcelField(title="活动图片",type=1,align=2,sort=110)
	@Length(min=0, max=300, message="活动图片路径长度必须介于 0 和 300 之间")
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	@Length(min=0, max=32, message="优惠卷-优惠卷表外键长度必须介于 0 和 32 之间")
	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}
	
	@Length(min=0, max=3000, message="备注字段1长度必须介于 0 和 3000 之间")
	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	@Length(min=0, max=3000, message="备注字段2长度必须介于 0 和 3000 之间")
	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	@Length(min=0, max=20, message="删除标记（0：正常；1：删除；2：审核）长度必须介于 0 和 20 之间")
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getPublishtBeginTime() {
		return publishtBeginTime;
	}

	public void setPublishtBeginTime(String publishtBeginTime) {
		this.publishtBeginTime = publishtBeginTime;
	}

	public String getPublishtEndTime() {
		return publishtEndTime;
	}

	public void setPublishtEndTime(String publishtEndTime) {
		this.publishtEndTime = publishtEndTime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}