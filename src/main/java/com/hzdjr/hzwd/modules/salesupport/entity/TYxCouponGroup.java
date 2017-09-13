/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.salesupport.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 优惠券组Entity
 * @author xq
 * @version 2017-06-05
 */
public class TYxCouponGroup extends DataEntity<TYxCouponGroup> {
	
	private static final long serialVersionUID = 1L;

	private String couponGroupId;		// ID名称
	private String couponGroupName;		// 优惠券名称
	private String holderType;		// 收券人类型--全部用户:all；单个用户:some
	private String isSendMessage;		// 0:否 1:是
	private String messageContent;		// 短信内容
	private String couponGroupStatus;		// 优惠券组状态--0未发送:unsent 1 审核中(待审核):auditing 2审核未通过:failed 3 审核通过已发送:sent
	private String mobilseList;		// 用户列表
	private Date createdate;		// 创建时间格式yyyy-MM-dd hh:mm:ss
	private String createby;		// 创建人
	private Date updatedate;		// 修改时间格式yyyy-MM-dd hh:mm:ss
	private String updateby;		// 修改人
	private String delflag;		// 删除标记（0：正常；1：删除；2：审核）
	private Date auditTime;    //审核时间
	private String auditStatus; //审核状态  0未通过1通过  
	private Date sendTime;     //发送时间
	private Integer userCount; //用户人数
	private List<TYxCoupon> couponList;//关联优惠券
	private String couponIds; //关联优惠券ID
	
	private String startSendDate;
	private String endSendDate;
	private String singletonUser;
	
	
	private String ids;   //优惠券id集合
	
	
	public TYxCouponGroup() {
		super();
	}

	public TYxCouponGroup(String id){
		super(id);
	}

	@Length(min=1, max=32, message="ID名称长度必须介于 1 和 32 之间")
	public String getCouponGroupId() {
		return couponGroupId;
	}

	public void setCouponGroupId(String couponGroupId) {
		this.couponGroupId = couponGroupId;
	}
	
	@Length(min=0, max=60, message="优惠券名称长度必须介于 0 和 60 之间")
	public String getCouponGroupName() {
		return couponGroupName;
	}

	public void setCouponGroupName(String couponGroupName) {
		this.couponGroupName = couponGroupName;
	}
	
	@Length(min=0, max=6, message="收券人类型--全部用户:all；单个用户:some长度必须介于 0 和 6 之间")
	public String getHolderType() {
		return holderType;
	}

	public void setHolderType(String holderType) {
		this.holderType = holderType;
	}
	
	@Length(min=0, max=4, message="0:否 1:是长度必须介于 0 和 4 之间")
	public String getIsSendMessage() {
		return isSendMessage;
	}

	public void setIsSendMessage(String isSendMessage) {
		this.isSendMessage = isSendMessage;
	}
	
	@Length(min=0, max=3000, message="短信内容长度必须介于 0 和 3000 之间")
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	@Length(min=0, max=6, message="优惠券组状态--0未发送:unsent 1 审核中(待审核):auditing 2审核未通过:failed 3 审核通过已发送:sent长度必须介于 0 和 6 之间")
	public String getCouponGroupStatus() {
		return couponGroupStatus;
	}

	public void setCouponGroupStatus(String couponGroupStatus) {
		this.couponGroupStatus = couponGroupStatus;
	}
	
	public String getMobilseList() {
		return mobilseList;
	}

	public void setMobilseList(String mobilseList) {
		this.mobilseList = mobilseList;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	@Length(min=0, max=20, message="创建人长度必须介于 0 和 20 之间")
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
	
	@Length(min=0, max=20, message="修改人长度必须介于 0 和 20 之间")
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

	
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    
    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    
    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    
    public List<TYxCoupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<TYxCoupon> couponList) {
        this.couponList = couponList;
    }


    public String getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(String couponIds) {
        this.couponIds = couponIds;
    }

    public String getStartSendDate() {
        return startSendDate;
    }

    public void setStartSendDate(String startSendDate) {
        this.startSendDate = startSendDate;
    }

    public String getEndSendDate() {
        return endSendDate;
    }

    public void setEndSendDate(String endSendDate) {
        this.endSendDate = endSendDate;
    }

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getSingletonUser() {
		return singletonUser;
	}

	public void setSingletonUser(String singletonUser) {
		this.singletonUser = singletonUser;
	}

}
