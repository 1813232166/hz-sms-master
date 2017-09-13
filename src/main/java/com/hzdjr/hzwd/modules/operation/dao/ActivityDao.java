/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.operation.entity.Activity;

/**
 * 活动列表DAO接口
 * @author xuchenglin
 * @version 2016-10-21
 */
@MyBatisDao
public interface ActivityDao extends CrudDao<Activity> {
	
}