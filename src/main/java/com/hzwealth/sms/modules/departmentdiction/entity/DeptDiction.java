package com.hzwealth.sms.modules.departmentdiction.entity;

public class DeptDiction {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String province;//省
	
	private String city;//市
	
	private String dept;//部门

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
