package com.hzwealth.sms.modules.buss.entity;

public class Buss {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String type;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
