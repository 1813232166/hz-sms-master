/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.match.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.match.dao.CapitalDao;
import com.hzwealth.sms.modules.match.dao.DebtTurnWarningDao;
import com.hzwealth.sms.modules.match.entity.DebtTurnWarning;

/**
 * 债权转让预警Service
 * @author FYP
 * @version 2017-06-19
 */
@Service
@Transactional(readOnly = true)
public class DebtTurnWarningService extends CrudService<DebtTurnWarningDao, DebtTurnWarning> {
	@Autowired
	private DebtTurnWarningDao debtTurnWarningDao;
	
	public DebtTurnWarning get(String id) {
		return super.get(id);
	}
	
	
	public Page<DebtTurnWarning> findPage(Page<DebtTurnWarning> page, DebtTurnWarning debtTurnWarning) {
		return super.findPage(page, debtTurnWarning);
	}


	public Map<String, Object> findWarningAsset(DebtTurnWarning debtTurnWarning) {
		return debtTurnWarningDao.findWarningAsset(debtTurnWarning);
	}
	
}