package com.hzdjr.hzwd.common.utils;

import org.joda.time.DateTime;

import com.hzdjr.hzwd.common.utils.redis.RedisCacheHelper;

public class FinanceCodeUtil {

	public static String getFinanceyyjhNo() {
		return "yyjh"+getNo("financeyyjh");
	}
	
	
	public static String getNo(String signNo) {
		Object signNoObj = RedisCacheHelper.read(signNo);
		if(signNoObj == null){
			RedisCacheHelper.set(signNo, "0", 86400); // 秒
		}
		Long number = RedisCacheHelper.incr(signNo);
		String borrowName = new DateTime().toString("yyyyMMdd") + String.format("%05d", number);
		return borrowName;
	}
	
	
	public static void main(String[] args) {
		System.out.println("yyjh"+getNo("financeyyjh"));
	}
}
