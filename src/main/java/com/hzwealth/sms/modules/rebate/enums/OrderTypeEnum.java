package com.hzwealth.sms.modules.rebate.enums;
/**
 * 订单类型
 * @author wzb
 *
 */
public enum OrderTypeEnum {
	//订单类型（1：新手标，2：出借计划，3：散标）只有出借计划才返佣，其他订单只更新邀请出借金额
	newUserSubject("1","新手标"),
	loanPlan("2","出借计划"),
	singleLoan("3","散标"),;
	
	private String orderType;
	private String orderTypeDesc;
	
	private OrderTypeEnum(String orderType,String orderTypeDesc){
		this.orderType = orderType;
		this.orderTypeDesc = orderTypeDesc;
	}
	/**
	 * 根据ordertype获取枚举类型
	 * @param orderType
	 * @return
	 */
	public static OrderTypeEnum getOrderTypeEmumByType(String orderType){
		OrderTypeEnum type = null;
		for(OrderTypeEnum oe:OrderTypeEnum.values()){
			if(oe.getOrderType().equals(orderType)){
				type=oe;
				break;
			}
		}
		return type;
	}
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderTypeDesc() {
		return orderTypeDesc;
	}

	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}
	
}
