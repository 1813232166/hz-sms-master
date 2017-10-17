/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.operation.entity.Activity;

/**
 * 活动列表DAO接口
 * @author xuchenglin
 * @version 2016-10-21
 */
@MyBatisDao
public interface ActivityDao extends CrudDao<Activity> {
	
}