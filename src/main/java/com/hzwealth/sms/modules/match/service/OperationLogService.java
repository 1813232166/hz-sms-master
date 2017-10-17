package com.hzwealth.sms.modules.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.match.dao.OperationLogDao;
import com.hzwealth.sms.modules.match.entity.OperationLog;

@Service
public class OperationLogService extends CrudService<OperationLogDao, OperationLog>{
	@Autowired
	OperationLogDao operationLogDao;
	public Page<OperationLog> findPage(Page<OperationLog> page, OperationLog operationLog) {
		return super.findPage(page, operationLog);
	}
	@Transactional(readOnly = false)
	public int createWeightLog(OperationLog operationLog){
		return operationLogDao.createWeightLog(operationLog);
	}
}
