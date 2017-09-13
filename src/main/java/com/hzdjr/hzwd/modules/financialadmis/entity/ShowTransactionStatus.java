package com.hzdjr.hzwd.modules.financialadmis.entity;

import com.hzdjr.hzwd.common.utils.StringUtils;

public enum ShowTransactionStatus {
	//状态[S：成功,I：新增,F:失败,P:预授权中,R:页面处理中,N:未知,L:待处理,D:后台支付系统处理中,E:异常,FZ:冻结,M:流标,C:撤标]
	S("成功"),//成功
	I("处理中"),//新增
	F("失败"),//失败
	P("处理中"),//预授权中
	R("处理中"),//页面处理中
	N("异常"),//未知
	L("处理中"),//待处理
	D("处理中"),//后台支付系统处理中
	E("异常"),//异常
	FZ("冻结"),//冻结
	M("失败"),//流标
	C("失败");//撤标

	public static String getShowNameByCode(String code){
		if (StringUtils.isBlank(code)) {
			return null;
		}

		ShowTransactionStatus[] tranStatusList = ShowTransactionStatus.values();
		for (ShowTransactionStatus tranStatus : tranStatusList) {
			if(tranStatus.name().equals(code)){
				return tranStatus.descr;
			}
		}
		return "";
	}

    public String descr;

    private ShowTransactionStatus(String descr){
        this.descr = descr;
    }

}
