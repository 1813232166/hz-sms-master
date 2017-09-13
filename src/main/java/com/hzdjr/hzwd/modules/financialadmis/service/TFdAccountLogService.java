/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.financialadmis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.financialadmis.entity.TFdAccountLog;
import com.hzdjr.hzwd.modules.financialadmis.entity.TFdAccountLogVo;
import com.hzdjr.hzwd.modules.financialadmis.dao.TFdAccountLogDao;
import com.hzdjr.hzwd.modules.financialadmis.dao.TradeMoneyDao;

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