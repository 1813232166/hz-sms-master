package com.hzdjr.hzwd.modules.match.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.match.entity.OperationLog;


@MyBatisDao
public interface OperationLogDao extends CrudDao<OperationLog> {

	int createWeightLog(OperationLog operationLog);
}
