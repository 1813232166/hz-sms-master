package com.hzdjr.hzwd.modules.financialadmis.entity;

import com.hzdjr.hzwd.common.utils.StringUtils;

public enum ShowSysAccount {
	SYS_GENERATE_001("平台垫付账户"),//商户垫资账户
	SYS_GENERATE_002("平台垫付账户"),//风险保证金
	SYS_GENERATE_003("服务费账户"),//收费账户
	SYS_GENERATE_004("服务费账户");//奖励金账户

	public static String getShowNameByCode(String code){
		if (StringUtils.isBlank(code)) {
			return null;
		}

		ShowSysAccount[] sysAccounts = ShowSysAccount.values();
		for (ShowSysAccount sysAccount : sysAccounts) {
			if(sysAccount.name().equals(code)){
				return sysAccount.descr;
			}
		}
		return "";
	}

    public String descr;

    private ShowSysAccount(String descr){
        this.descr = descr;
    }
}
