/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.operation.entity.ActivityMoney;

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