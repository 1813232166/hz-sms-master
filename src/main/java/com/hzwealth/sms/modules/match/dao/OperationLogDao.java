package com.hzwealth.sms.modules.match.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.match.entity.OperationLog;


@MyBatisDao
public interface OperationLogDao extends CrudDao<OperationLog> {

	int createWeightLog(OperationLog operationLog);
}
