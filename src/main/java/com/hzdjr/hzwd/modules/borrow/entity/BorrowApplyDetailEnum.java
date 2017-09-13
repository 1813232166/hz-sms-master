package com.hzdjr.hzwd.modules.borrow.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public enum BorrowApplyDetailEnum {

	BORROW_TYPE_XYB(1, "1", "信用标"),
	BORROW_TYPE_DYB(1, "2", "抵押标"),
	BORROW_TYPE_GJJDK(1, "3", "公积金贷款"),
	BORROW_TYPE_JYD(1, "4", "精英贷"),
	CRITICALID_YES(2, "1", "是"),
	CRITICALID_NO(2, "2", "否"),
	SEX_MAN(3, "1", "男"),
	SEX_WOMAN(3, "0", "女"),
	HEDUCATION_SS(4, "10", "研究生"),
	HEDUCATION_BK(4, "20", "大学本科"),
	HEDUCATION_DZ(4, "30", "大学专科和专科学校"),
	HEDUCATION_ZZ(4, "40", "中等专业学校或中等技术学校"),
	HEDUCATION_JSXX(4, "50", "技术学校"),
	HEDUCATION_GZ(4, "60", "高中"),
	HEDUCATION_CZ(4, "70", "初中"),
	HEDUCATION_XX(4, "80", "小学"),
	HEDUCATION_WM(4, "90", "文盲半文盲"),
	HEDUCATION_WZ(4, "99", "未知"),
	MARITALSTAUTS_WH(5, "10", "未婚"),
	MARITALSTAUTS_YH(5, "20", "已婚"),
	MARITALSTAUTS_CH(5, "21", "初婚"),
	MARITALSTAUTS_ZH(5, "22", "再婚"),
	MARITALSTAUTS_FH(5, "23", "复婚"),
	MARITALSTAUTS_SO(5, "30", "丧偶"),
	MARITALSTAUTS_LY(5, "40", "离婚"),
	HASCHILDREN_Y(6, "1", "有"),
	HASCHILDREN_MY(6, "2", "没有"),
	REALESTATESITUATION_WDK(7, "1", "有房无贷款"),
	REALESTATESITUATION_YDK(7, "2", "有房有贷款"),
	REALESTATESITUATION_QT(7, "3", "其他"),
	CORESIDENT_FM(8, "1", "父母"),
	CORESIDENT_PO(8, "2", "配偶及子女"),
	CORESIDENT_PY(8, "3", "朋友"),
	CORESIDENT_DJ(8, "4", "独居"),
	CORESIDENT_QT(8, "5", "其他"),
	COMPANYSTYLE_JG(9, "1", "机关事业"),
	COMPANYSTYLE_GY(9, "2", "国有企业"),
	COMPANYSTYLE_MY(9, "3", "民营"),
	COMPANYSTYLE_SZQY(9, "4", "三资企业"),
	COMPANYSTYLE_QT(9, "5", "其他"),
	COMPANYSTYLE_WZ(9, "6", "外资"),
	COMPANYSTYLE_HZ(9, "7", "合资"),
	COMPANYSTYLE_GT(9, "8", "个体"),
	COMPANYASSUME_CSR(10, "1", "负责人"),
	COMPANYASSUME_GCGLRY(10, "2", "高层管理人员"),
	COMPANYASSUME_ZCGLRY(10, "3", "中层管理人员"),
	COMPANYASSUME_YBGLRY(10, "4", "一般管理人员"),
	COMPANYASSUME_ZSYG(10, "5", "一般正式员工"),
	COMPANYASSUME_PQYG(10, "6", "派遣员工"),
	COMPANYASSUME_FZSYG(10, "7", "非正式员工"),
	COMPANYASSUME_TXYG(10, "8", "退休人员"),
	COMPANYASSUME_FMBYG(10, "9", "非目标客户"),
	COMPANYASSUME_QT(10, "10", "其他"),
	CONTACTS_TYPE_JT(11, "3", "家庭"),
	CONTACTS_TYPE_GZ(11, "5", "工作"),
	CONTACTS_TYPE_JJ(11, "4", "紧急"),
	ACCOUNTTYPE_SFZ(12, "1", "身份证"),
	ACCOUNTTYPE_LMK(12, "2", "联名卡"),
	ACCOUNTTYPE_YHM(12, "3", "用户名"),
	PIC_TYPE_IDCARD(13, "B", "身份证明"),
	PIC_TYPE_WORKANDINCOME(13, "C", "工作及收入证明"),
	PIC_TYPE_CREDIT(13, "E", "个人征信报告证明"),
	//PIC_TYPE_MOBILE(13, "8", "手机通话记录详单"),
	//PIC_TYPE_OTHER(13, "7", "其他证明"),
	
	;
	
	private int enumType;
	private String code;
	private String name;
	
	private BorrowApplyDetailEnum(int enumType, String code, String name){
		this.enumType = enumType;
		this.code = code;
		this.name = name;
	}
	
	public static Map<String, String> getEnumMapByType(int enumType){
		Map<String, String> enumMap = new LinkedHashMap<String, String>();
		for(BorrowApplyDetailEnum detailEnum: BorrowApplyDetailEnum.values()){
			if(detailEnum.getEnumType() == enumType){
				enumMap.put(detailEnum.getCode(), detailEnum.getName());
			}
		}
		return enumMap;
	}
	
	public int getEnumType() {
		return enumType;
	}
	public void setEnumType(int enumType) {
		this.enumType = enumType;
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
