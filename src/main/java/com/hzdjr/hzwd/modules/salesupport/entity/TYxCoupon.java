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
 * 优惠券Entity
 * @author xq
 * @version 2017-06-05
 */
public class TYxCoupon extends DataEntity<TYxCoupon> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 优惠券名称
	private String couponTypeId;		// '满减券:fullDown,现金券:cash,利息券:interest,返现券:cashBack
	private java.math.BigDecimal  faceValue;		// 面值
	private String effectiveDays;		// 有效期
	private Date beginTime;		// 使用期限(开始)
	private Date endTime;		// 使用期限(结束)
	private java.math.BigDecimal loanAmountMin;		// 最小出借金额
	private java.math.BigDecimal loanAmountSum;		// 累计最小出借金额
	private String loanTermList;		// 出借期限列表---各出借期限以分号隔开，并且最后一个也要添加分号
	private String loanChannelList;		// 出借端列表--各出借端以分号隔开，并且最后一个也要添加分号
	private Integer  couponCount;		// 优惠券张数--0没有限制
	private String isSend;		// 是否已发送--0:待审核 1:审核通过 2:审核不通过
	private Date createdate;		// 创建时间
	private String createby;		// 创建人
	private Date updatedate;		// 修改时间
	private String updateby;		// 修改人
	private String delflag;		// 删除标记（0：正常；1：删除；2：审核）
	private String status;		// 1未生效，2审核中，3审核未通过，4已生效，5已失效
	private Date auditTime;     //审核时间
	private String auditStatus; //审核状态
	private String auditContent;//审核原因
	private List<String> couponTypeList; //优惠券类型
	private List<String> termList; //出借期限
	private List<String> channelList; //出借端期限
	private String startCreateDate;//创建开始时间
	private String endCreateDate;//
	private String statusshow;
	private String shixiao;//页面展示
	
	private Date deadTime;      // 过期时间
	public String getShixiao() {
		return shixiao;
	}

	public void setShixiao(String shixiao) {
		this.shixiao = shixiao;
	}

	private String isCheck;// 0未选中，1 已选中
	
	public TYxCoupon() {
		super();
	}

	public TYxCoupon(String id){
		super(id);
	}

	public String getStatusshow() {
		return statusshow;
	}

	


	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public void setStatusshow(String statusshow) {
		this.statusshow = statusshow;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Length(min=0, max=20, message="优惠券名称长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=6, message="'满减券:fullDown,现金券:cash,利息券:interest,返现券:cashBack长度必须介于 0 和 6 之间")
	public String getCouponTypeId() {
		return couponTypeId;
	}

	public void setCouponTypeId(String couponTypeId) {
		this.couponTypeId = couponTypeId;
	}
	
	
	
	public java.math.BigDecimal getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(java.math.BigDecimal faceValue) {
		this.faceValue = faceValue;
	}

	@Length(min=0, max=3, message="有效期长度必须介于 0 和 3 之间")
	public String getEffectiveDays() {
		return effectiveDays;
	}

	public void setEffectiveDays(String effectiveDays) {
		this.effectiveDays = effectiveDays;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	public java.math.BigDecimal getLoanAmountMin() {
		return loanAmountMin;
	}

	public void setLoanAmountMin(java.math.BigDecimal loanAmountMin) {
		this.loanAmountMin = loanAmountMin;
	}

	public java.math.BigDecimal getLoanAmountSum() {
		return loanAmountSum;
	}

	public void setLoanAmountSum(java.math.BigDecimal loanAmountSum) {
		this.loanAmountSum = loanAmountSum;
	}

	@Length(min=0, max=60, message="出借期限列表---各出借期限以分号隔开，并且最后一个也要添加分号长度必须介于 0 和 60 之间")
	public String getLoanTermList() {
		return loanTermList;
	}

	public void setLoanTermList(String loanTermList) {
		this.loanTermList = loanTermList;
	}
	
	@Length(min=0, max=20, message="出借端列表--各出借端以分号隔开，并且最后一个也要添加分号长度必须介于 0 和 20 之间")
	public String getLoanChannelList() {
		return loanChannelList;
	}

	public void setLoanChannelList(String loanChannelList) {
		this.loanChannelList = loanChannelList;
	}
	
	
	

	@Length(min=0, max=11, message="是否已发送--0:待审核 1:审核通过 2:审核不通过长度必须介于 0 和 11 之间")
	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
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
	
	@Length(min=0, max=2, message="1未生效，2审核中，3审核未通过，4已生效，5已失效长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    
    public List<String> getCouponTypeList() {
        return couponTypeList;
    }

    public void setCouponTypeList(List<String> couponTypeList) {
        this.couponTypeList = couponTypeList;
    }

    public List<String> getTermList() {
        return termList;
    }

    public void setTermList(List<String> termList) {
        this.termList = termList;
    }

    public List<String> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<String> channelList) {
        this.channelList = channelList;
    }

    public String getStartCreateDate() {
        return startCreateDate;
    }

    
    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    public void setStartCreateDate(String startCreateDate) {
        this.startCreateDate = startCreateDate;
    }

    public String getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(String endCreateDate) {
        this.endCreateDate = endCreateDate;
    }
    
    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    @Override
    public String toString() {
        return "TYxCoupon [name=" + name + ", couponTypeId=" + couponTypeId + ", faceValue=" + faceValue
                + ", effectiveDays=" + effectiveDays + ", beginTime=" + beginTime + ", endTime=" + endTime
                + ", loanAmountMin=" + loanAmountMin + ", loanAmountSum=" + loanAmountSum + ", loanTermList="
                + loanTermList + ", loanChannelList=" + loanChannelList + ", couponCount=" + couponCount + ", isSend="
                + isSend + ", createdate=" + createdate + ", createby=" + createby + ", updatedate=" + updatedate
                + ", updateby=" + updateby + ", delflag=" + delflag + ", status=" + status + ", startCreateDate="
                + startCreateDate + ", endCreateDate=" + endCreateDate + ",isCheck="+isCheck+"]";
    }

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	
}
