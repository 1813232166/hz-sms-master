/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.financialadmis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.financialadmis.dao.CompFdAccountLogDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.CompFdAccountLog;


/**
 * 平台账户Service
 * @author xq
 * @version 2016-10-18
 */
@Service
@Transactional(readOnly = true)
public class CompFdAccountLogService extends CrudService<CompFdAccountLogDao, CompFdAccountLog> {

	public CompFdAccountLog get(String id) {
		return super.get(id);
	}
	
	public List<CompFdAccountLog> findList(CompFdAccountLog compFdAccountLog) {
		return super.findList(compFdAccountLog);
	}
	
	public Page<CompFdAccountLog> findPage(Page<CompFdAccountLog> page, CompFdAccountLog compFdAccountLog) {
		return super.findPage(page, compFdAccountLog);
	}
	
	@Transactional(readOnly = false)
	public void save(CompFdAccountLog compFdAccountLog) {
		super.save(compFdAccountLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(CompFdAccountLog compFdAccountLog) {
		super.delete(compFdAccountLog);
	}
	
}