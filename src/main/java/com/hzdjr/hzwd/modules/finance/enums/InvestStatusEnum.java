package com.hzdjr.hzwd.modules.finance.enums;

public enum InvestStatusEnum {
	
	INVEST_SUCCESS("10", "投资成功"),
	INCOMEING("11", "收益中"),
	INVEST_FAIL("12", "投资失败"),
	TRANSFER_APPLY("1", "退出中"),
	TRANSFER_SUCCESS("2", "已结束"),
	TRANSFER_FAIL("3", "退出失败"),
	;
	
	private String value;
	private String name;
	
	private InvestStatusEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
