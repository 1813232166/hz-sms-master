/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.financialadmis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.financialadmis.dao.TFdAccountLogDao;
import com.hzwealth.sms.modules.financialadmis.dao.TradeMoneyDao;
import com.hzwealth.sms.modules.financialadmis.entity.TFdAccountLog;
import com.hzwealth.sms.modules.financialadmis.entity.TFdAccountLogVo;

/**
 * 财务管理Service
 * @author xq
 * @version 2016-10-17
 */
@Service
@Transactional(readOnly = true)
public class TFdAccountLogService extends CrudService<TFdAccountLogDao, TFdAccountLog> {
	@Autowired
	private TFdAccountLogDao tFdAccountLogDao;
	public TFdAccountLog get(String id) {
		return super.get(id);
	}
	
	public List<TFdAccountLog> findList(TFdAccountLog tFdAccountLog) {
		return super.findList(tFdAccountLog);
	}
	
	public Page<TFdAccountLog> findPage(Page<TFdAccountLog> page, TFdAccountLog tFdAccountLog) {
		return super.findPage(page, tFdAccountLog);
	}
	
	@Transactional(readOnly = false)
	public void save(TFdAccountLog tFdAccountLog) {
		super.save(tFdAccountLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(TFdAccountLog tFdAccountLog) {
		super.delete(tFdAccountLog);
	}

	public List<TFdAccountLogVo> findexportList(TFdAccountLog tFdAccountLog) {
		return tFdAccountLogDao.findexportList(tFdAccountLog);
	}
	
}