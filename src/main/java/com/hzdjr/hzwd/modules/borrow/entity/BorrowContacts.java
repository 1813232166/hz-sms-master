package com.hzdjr.hzwd.modules.borrow.entity;
/*
 * t_borrow_contacts
 */
public class BorrowContacts {
	
	private String relation;   //关系
	private String type;    //类型1家庭联系人2工作联系人3紧急联系人',
	private String name;     //姓名
	private String mobile;    //手机号
	private String telAreaCode;//区号
	
	
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelAreaCode() {
		return telAreaCode;
	}
	public void setTelAreaCode(String telAreaCode) {
		this.telAreaCode = telAreaCode;
	}
	

	
}
