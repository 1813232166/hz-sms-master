/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.operation.entity.ActivityPracticeMoney;

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