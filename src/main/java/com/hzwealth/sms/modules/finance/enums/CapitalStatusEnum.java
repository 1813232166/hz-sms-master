package com.hzwealth.sms.modules.finance.enums;


public enum CapitalStatusEnum {
	
	MATCHING("1", "匹配中"),
	MATCH_FAIL("6", "匹配失败"),
	INCOME_NOT_EXPIRED("beforeBack", "收益中（未到期）"),
	INCOME_EXPIRED("afterBack", "收益中（已到期）"),
	TRANSFER_APPLY("7", "退出中"),
	TRANSFER_SUCCESS("4", "已结束")
	;
	
	private String value;
	private String name;
	
	private CapitalStatusEnum(String value, String name) {
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
