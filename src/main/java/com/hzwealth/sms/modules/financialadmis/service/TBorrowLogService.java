/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.financialadmis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.financialadmis.dao.TBorrowLogDao;
import com.hzwealth.sms.modules.financialadmis.entity.TBorrowLog;

/**
 * 标的记录Service
 * @author xq
 * @version 2016-10-19
 */
@Service
@Transactional(readOnly = true)
public class TBorrowLogService extends CrudService<TBorrowLogDao, TBorrowLog> {

	public TBorrowLog get(String id) {
		return super.get(id);
	}
	
	public List<TBorrowLog> findList(TBorrowLog tBorrowLog) {
		return super.findList(tBorrowLog);
	}
	
	public Page<TBorrowLog> findPage(Page<TBorrowLog> page, TBorrowLog tBorrowLog) {
		return super.findPage(page, tBorrowLog);
	}
	
	@Transactional(readOnly = false)
	public void save(TBorrowLog tBorrowLog) {
		super.save(tBorrowLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBorrowLog tBorrowLog) {
		super.delete(tBorrowLog);
	}
	
}