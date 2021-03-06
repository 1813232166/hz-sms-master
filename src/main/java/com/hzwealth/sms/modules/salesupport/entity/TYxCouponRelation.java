/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.salesupport.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;

/**
 * 优惠券活动关联Entity
 * @author zhouzhankui
 * @version 2017-06-08
 */
public class TYxCouponRelation extends DataEntity<TYxCouponRelation> {
	
	private static final long serialVersionUID = 1L;
	private String relationType;		// 关联类型--优惠券组:group 活动:activity
	private String mainId;		// 主ID--组ID或者活动ID
	private String couponId;		// 优惠券ID
	private Date createdate;		// 创建时间格式yyyy-MM-dd hh:mm:ss
	private String createby;		// 创建人--后台账户ID
	private Date updatedate;		// 修改时间格式yyyy-MM-dd hh:mm:ss
	private String updateby;		// 修改人--后台账户ID
	private String delflag;		// 删除标记（0：正常；1：删除；2：审核）
	
	public TYxCouponRelation() {
		super();
	}

	public TYxCouponRelation(String id){
		super(id);
	}

	@Length(min=0, max=6, message="关联类型--优惠券组:group 活动:activity长度必须介于 0 和 6 之间")
	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	@Length(min=0, max=32, message="主ID--组ID或者活动ID长度必须介于 0 和 32 之间")
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	
	@Length(min=0, max=32, message="优惠券ID长度必须介于 0 和 32 之间")
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	@Length(min=0, max=20, message="创建人--后台账户ID长度必须介于 0 和 20 之间")
	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Length(min=0, max=20, message="修改人--后台账户ID长度必须介于 0 和 20 之间")
	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	
	@Length(min=0, max=20, message="删除标记（0：正常；1：删除；2：审核）长度必须介于 0 和 20 之间")
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	
}