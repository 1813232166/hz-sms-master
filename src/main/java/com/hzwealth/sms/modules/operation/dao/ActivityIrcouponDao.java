/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.operation.entity.ActivityIrcoupon;

/**
 * 加息券管理DAO接口
 * @author hdj
 * @version 2016-11-02
 */
@MyBatisDao
public interface ActivityIrcouponDao extends CrudDao<ActivityIrcoupon> {
	
	//修改加息券状态
	void updateStatus(ActivityIrcoupon activityIrcoupon);
	
	//获取总计
	Map<String ,Object> getIrcouponCounts(ActivityIrcoupon activityIrcoupon);
	
	//导出列表信息
	List<ActivityIrcoupon>exportActivityIrcoupon(ActivityIrcoupon activityIrcoupon);
	
}