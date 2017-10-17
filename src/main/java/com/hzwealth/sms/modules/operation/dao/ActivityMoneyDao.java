/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.operation.entity.ActivityMoney;

/**
 * 活动现金发放DAO接口
 * @author hdj
 * @version 2016-10-26
 */
@MyBatisDao
public interface ActivityMoneyDao extends CrudDao<ActivityMoney> {
	
	//获取总现金数
	Map<String,Object> getMoneyCounts(ActivityMoney activityMoney);
	
	//导出数据
	List<ActivityMoney> exportMoneyFile(ActivityMoney activityMoney);
	
}