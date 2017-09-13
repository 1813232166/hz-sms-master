package com.hzdjr.hzwd.modules.borrow.entity;

import java.io.Serializable;
import java.util.Date;

public class BorrowPicVo implements Serializable{

	private static final long serialVersionUID = -2891211602141857975L;

	private String id;		//图片id
	private String bid;		//标的ID
	private String status;	//图片显示类型（0前台1后台）
	private String pictype;	//图片类型
	private String picurl;	//图片路径
	private Date createtime;//图片上传时间
	
	private String picdesc;	//图片描述
	private String code;
	private String msg;
	private String fileName;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getPictype() {
		return pictype;
	}
	public void setPictype(String pictype) {
		this.pictype = pictype;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPicdesc() {
		return picdesc;
	}
	public void setPicdesc(String picdesc) {
		this.picdesc = picdesc;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
