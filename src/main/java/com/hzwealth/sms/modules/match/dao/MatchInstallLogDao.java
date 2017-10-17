package com.hzwealth.sms.modules.match.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.match.entity.OperationLog;


@MyBatisDao
public interface MatchInstallLogDao extends CrudDao<OperationLog> {
	int createMatchInstallLog(OperationLog operationLog);
}
