package com.hzwealth.sms.modules.match.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.match.dao.CapitalDao;
import com.hzwealth.sms.modules.match.entity.Capital;

/**
 * 资金列表Service
 * @author FYP
 * @version 2017-06-12
 */
@Service
@Transactional(readOnly = true)
public class CapitalService extends CrudService<CapitalDao, Capital> {

	
	@Autowired
	private CapitalDao capitalDao;
	
	public Capital get(String id) {
		return super.get(id);
	}
	
	public List<Capital> findList(Capital capital) {
		return super.findList(capital);
	}
	
	public Page<Capital> findPage(Page<Capital> page, Capital capital) {
		return super.findPage(page, capital);
	}
	
	@Transactional(readOnly = false)
	public void save(Capital capital) {
		super.save(capital);
	}
	
	@Transactional(readOnly = false)
	public void delete(Capital capital) {
		super.delete(capital);
	}

	public Map<String, Object> findOriginalCount(Capital capital) {
		return capitalDao.findOriginalCount(capital);
	}

	public Map<String, Object> findReturnCount(Capital capital) {
		return capitalDao.findReturnCount(capital);
	}

	@Transactional(readOnly = false)
	public boolean singleResetWeight(Capital capital) {
		return capitalDao.singleResetWeight(capital)>0;
	}
	
	
}