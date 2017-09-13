package com.hzdjr.hzwd.modules.rebate.enums;
/**
 * 等级枚举
 * @author wzb
 *
 */
public enum UserRefferLevelEnum {
	common("1","普通"),
	silver("2","银牌"),
	gold("3","金牌"),
	;
	private String level;
	private String desc;
	private UserRefferLevelEnum(String level,String desc){
		this.level = level;
		this.desc = desc;
	}
	/**
	 * 根据level获取对应的枚举
	 * @param level
	 * @return
	 */
	public static UserRefferLevelEnum getLeveEnumByLevel(String level){
		UserRefferLevelEnum resultLevel = null;
		for(UserRefferLevelEnum le : UserRefferLevelEnum.values()){
			if(le.getLevel().equals(level)){
				resultLevel = le;
				break;
			}
		}
		return resultLevel;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
