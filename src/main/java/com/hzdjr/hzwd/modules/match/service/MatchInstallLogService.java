package com.hzdjr.hzwd.modules.match.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.match.dao.MatchInstallLogDao;
import com.hzdjr.hzwd.modules.match.entity.OperationLog;

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
