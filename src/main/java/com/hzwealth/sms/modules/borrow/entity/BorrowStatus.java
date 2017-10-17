package com.hzwealth.sms.modules.borrow.entity;

public enum BorrowStatus {

	/*0前台待审核1预发布;2初审成功;3初审失败;4招标中;5待复审;6复审失败;
	7还款中;8已还清;9已流标;10逾期;11已经满标;12已经撤销;13信审过程中;
	14信审失败;15信审成功;16新建17拒绝签约18已作废[1、2、5、6 暂时不用]、
	19后台待审核*/
	
	borrowsStaus0("0","已签约"),
	/*borrowsStaus1("1","预发布"),
	borrowsStaus2("2","初审成功"),
	borrowsStaus3("3","初审失败"),*/
	/*borrowsStaus4("4","招标中"),
	borrowsStaus5("5","待复审"),
	borrowsStaus6("6","复审失败"),*/
	/*borrowsStaus7("7","还款中"),*/
	/*borrowsStaus8("8","已还清"),
	borrowsStaus9("9","已流标"),
	borrowsStaus10("10","逾期"),
	borrowsStaus11("11","已经满标"),*/
	borrowsStaus12("12","已撤销"),
	borrowsStaus13("13","审核中"),
	borrowsStaus14("14","审核失败"),
	borrowsStaus15("15","待签约"),
	/*borrowsStaus16("16","新建"),*/
	borrowsStaus17("17","拒绝签约"),
	borrowsStaus18("18","已作废");
  /*borrowsStaus19("19","后台待审核"),
	borrowsStaus20("20","后台新建包装前已编辑");*/
	
	public static String getNameByCode(String code){
		BorrowStatus[] borrowStatus = BorrowStatus.values();
		for (BorrowStatus borrowStatu : borrowStatus) {
			if(borrowStatu.getCode().equals(code)){
				return borrowStatu.getName();
			}
		}
		return "";
	}
	
	public static String getCodeByName(String name){
		BorrowStatus[] borrowStatus = BorrowStatus.values();
		for (BorrowStatus borrowStatu : borrowStatus) {
			if(borrowStatu.getName().equals(name)){
				return borrowStatu.getCode();
			}
		}
		return "";
	}
	
	private String code;
	private String name;
	private BorrowStatus(String code, String name) {
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
