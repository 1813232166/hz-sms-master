package com.hzwealth.sms.modules.rebate.enums;
/**
 * 推荐人类型
 * 和返佣配置类型不一致，refferee 是手机号1开头时，为推荐人，2开头为理财师，故有下设置
 * @author wzb
 *
 */
public enum UserRefferTypeEnum {
	referee("1","推荐人"),
	Financial_Planner("2","理财师");
	
	private String refferType;//类型
	private String refferDesc;//说明
	
	private UserRefferTypeEnum(String refferType,String refferDesc){
		this.refferType = refferType;
		this.refferDesc = refferDesc;
	}
	/**
	 * 根据类型，得到类型说明
	 * @return
	 */
	public static String getRefferDesc(String refferType){
		String result = null;
		for(UserRefferTypeEnum e :UserRefferTypeEnum.values()){
			if(e.getRefferType().equals(refferType)){
				result = e.getRefferDesc();
				break;
			}
		}
		return result;
	}
	public String getRefferType() {
		return refferType;
	}
	public void setRefferType(String refferType) {
		this.refferType = refferType;
	}
	public String getRefferDesc() {
		return refferDesc;
	}
	public void setRefferDesc(String refferDesc) {
		this.refferDesc = refferDesc;
	}
	
}
