/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.hzwealth.sms.modules.borrow.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzwealth.sms.common.persistence.DataEntity;
import com.hzwealth.sms.common.utils.excel.annotation.ExcelField;



/**
 * 借款意向表Entity
 * @author xuchenglin
 * @version 2017-04-25
 */
public class TWillBorrow extends DataEntity<TWillBorrow> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 借款人姓名
	private String mobile;		// 借款人手机
	private String borrowamount;		// 借款金额
	private String status;		// 状态
	private String workplace;		// 工作地点
	private Date beginCreateDate;		// 开始 申请时间
	private Date endCreateDate;		// 结束 申请时间
	private String province; //省
	private String city; //市
	private String area; //区
	
	public TWillBorrow() {
		super();
	}

	public TWillBorrow(String id){
		super(id);
	}

	@Length(min=1, max=32, message="借款人姓名长度必须介于 1 和 32 之间")
	@ExcelField(title="姓名", align=1, sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="借款人手机长度必须介于 1 和 20 之间")
	@ExcelField(title="手机号码", align=1, sort=20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@ExcelField(title="借款金额(万元)", align=1, sort=30)
	public String getBorrowamount() {
		return borrowamount;
	}

	public void setBorrowamount(String borrowamount) {
		this.borrowamount = borrowamount;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1和 1 之间")
	@ExcelField(title="状态(0未处理 1已处理)", align=1, sort=60)
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=128, message="工作地点长度必须介于 0 和 128 之间")
	@ExcelField(title="工作地点", align=1, sort=40)
	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
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

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }
  
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ExcelField(title="申请时间", align=1, sort=50)
  public Date getCreateDate() {
    return createDate;
  }
}