package com.hzwealth.sms.modules.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.match.dao.MatchInstallLogDao;
import com.hzwealth.sms.modules.match.entity.OperationLog;

@Service
public class MatchInstallLogService extends CrudService<MatchInstallLogDao, OperationLog>{
	@Autowired
	MatchInstallLogDao matchInstallLogDao;
	public Page<OperationLog> findPage(Page<OperationLog> page, OperationLog operationLog) {
		return super.findPage(page, operationLog);
	}
	@Transactional(readOnly = false)
	public int createMatchInstallLog(OperationLog operationLog){
		return matchInstallLogDao.createMatchInstallLog(operationLog);
	}
}
