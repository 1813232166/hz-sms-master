/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.banner.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 图片bannerEntity
 * @author xq
 * @version 2016-10-09
 */
public class AppBanner extends DataEntity<AppBanner> {
	
	private static final long serialVersionUID = 1L;
	private String bannerName;	//banner姓名
	private String creator;		//创建者
	private String type;		// 类型
	private String picurl;		// 图片路径
	private String status;		// 启用状态
	private Date createtime;	// 创建时间
	private String link;		//链接
	private Integer orders;	//排序
 
	
	
	
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public AppBanner() {
		super();
	}

	public AppBanner(String id){
		super(id);
	}

	
	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Length(min=1, max=12, message="类型长度必须介于 1 和 12 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=2000, message="图片路径长度必须介于 0 和 2000 之间")
	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	
	@Length(min=0, max=2, message="启用状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
	
}