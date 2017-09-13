/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.operation.entity.ActivityPoint;

/**
 * 积分列表DAO接口
 * 
 * @author hdj
 * @version 2016-10-24
 */
@MyBatisDao
public interface ActivityPointDao extends CrudDao<ActivityPoint> {

	// 获取积分总数
	Map<String, Object> getPointCounts(ActivityPoint point);

	// 导出积分列表
	List<ActivityPoint> exportPointFile(ActivityPoint point);

}