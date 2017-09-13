package com.hzdjr.hzwd.modules.usermanage.entity;

import java.util.Date;

/**
 *Title:TUserDetail
 *Description: 用户详情
 *@author:黄亚浩
 *@date:2016年10月17日 下午6:47:28
 */
public class TUserDetail {

	private String userId;   //用户id
	private String qq;   //QQ
	private String provinceCode;   //省
	private String cityCode;  //市',
	private String areaCode;   //县',
	private String addressDetail;   //详细地址
	private String contractUser;     //紧急联系人
	private String contractPhone;    //紧急联系人电话
	private String isPurchase;    //是否购房(0否，1是)',
	private String houseType;      //房产类型
	private String idBuyCar;    //是否购车
	private String carType;      // 购车类型
	private String company;   // 公司名称',
	private String companyNature;  //公司性质
	private String workLevel;    //职工级别
	private String workYear;  //工作年限
	private String monthIncome;   //月收入
	private String marriageState;   //婚姻状况
	private String marryName;     //配偶名称
	private String marryMothIncome;   //配偶月收入
	private String educationLevel;   //最高学历
	private String school;    //学院名称
	private String master;    //专业
	private Date masterStartDate;   //入学开始时间
	private Date masterEndDate;   //入学结束时间
	private String email;     //邮箱
	private String headImg;   //头像
	private Date birthday;    //出生日期',
	private String sex;   //性别0女1男
	private String idcard;  //身份证号
	private Integer idcardStatus;   //身份认证状态(0未认证1已认证)'
	private Date idcardAuthDate;  //身份认证时间',
	private Integer emailStatus;   //邮箱认证状态(0未认证1已认证)',
	private Date emailAuthDate;    //邮箱认证时间'
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
