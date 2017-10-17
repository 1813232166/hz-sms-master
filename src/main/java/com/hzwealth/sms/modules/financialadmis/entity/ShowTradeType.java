package com.hzwealth.sms.modules.financialadmis.entity;

public enum ShowTradeType {

	/*1:网银充值,5:提现,6:出借,7:还款,8:放款,9:代偿【垫付】,10:还代偿款,12:流标,13:收益明细提交,14:回款*/

	TradeType01("1","网银充值"),
	TradeType05("5","提现"),
	TradeType06("6","出借"),
	TradeType07("7","还款"),
	TradeType08("8","放款"),
	TradeType09("9","代偿"),
	TradeType10("10","还代偿款"),
	TradeType12("12","流标"),
	TradeType13("13","收益明细提交"),
	TradeType14("14","回款"),
	TradeType15("15","红包奖励");

	public static String getNameByCode(String code){
		ShowTradeType[] tradeTypes = ShowTradeType.values();
		for (ShowTradeType tradeType : tradeTypes) {
			if(tradeType.getCode().equals(code)){
				return tradeType.getName();
			}
		}
		return "";
	}

	public static String getCodeByName(String name){
		ShowTradeType[] tradeTypes = ShowTradeType.values();
		for (ShowTradeType tradeType : tradeTypes) {
			if(tradeType.getName().equals(name)){
				return tradeType.getCode();
			}
		}
		return "";
	}

	private String code;
	private String name;
	private ShowTradeType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
