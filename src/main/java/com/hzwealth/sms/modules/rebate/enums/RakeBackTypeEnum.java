package com.hzwealth.sms.modules.rebate.enums;
/**
 * 返佣设置的配置
 * 邀请人类型1理财师2推荐人
 * 
 * @author wzb
 *
 */
public enum RakeBackTypeEnum {
	adviser("1","理财师"),
	refer("2","推荐人"),
	;
	
	private String rakeType;
	private String rakeDesc;
	
	private RakeBackTypeEnum(String rakeType,String rakeDesc){
		this.rakeType=rakeType;
		this.rakeDesc = rakeDesc;
	}
	
	public String getRakeType() {
		return rakeType;
	}
	public void setRakeType(String rakeType) {
		this.rakeType = rakeType;
	}
	public String getRakeDesc() {
		return rakeDesc;
	}
	public void setRakeDesc(String rakeDesc) {
		this.rakeDesc = rakeDesc;
	}
	
}
