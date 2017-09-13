/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.operation.entity.ActivityPracticeMoney;

/**
 * 体验金列表DAO接口
 * @author hdj
 * @version 2016-10-27
 */
@MyBatisDao
public interface ActivityPracticeMoneyDao extends CrudDao<ActivityPracticeMoney> {
	
	//查询总体验金
	Map<String,Object> getPracticeMoney(ActivityPracticeMoney practiceMoney);
	
	//导出列表数据
	List<ActivityPracticeMoney> exportPracticeMoneyFile(ActivityPracticeMoney practiceMoney);
	
}