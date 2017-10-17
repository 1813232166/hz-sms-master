/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.finance.dao.TCapitalBespeakDao;
import com.hzwealth.sms.modules.finance.entity.TCapitalBespeak;

/**
 * 预约记录管理Service
 * @author HDG
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class TCapitalBespeakService extends CrudService<TCapitalBespeakDao, TCapitalBespeak> {
 
	@Autowired
	private TCapitalBespeakDao tCapitalBespeakDao;
	
	
	public TCapitalBespeak get(String id) {
		return super.get(id);
	}
	
	public List<TCapitalBespeak> findList(TCapitalBespeak tCapitalBespeak) {
		return super.findList(tCapitalBespeak);
	}
	
	public Page<TCapitalBespeak> findPage(Page<TCapitalBespeak> page, TCapitalBespeak tCapitalBespeak) {
		return super.findPage(page, tCapitalBespeak);
	}
	
	@Transactional(readOnly = false)
	public void save(TCapitalBespeak tCapitalBespeak) {
		super.save(tCapitalBespeak);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCapitalBespeak tCapitalBespeak) {
		super.delete(tCapitalBespeak);
	}
	public Map<String, Object>  findBespeaking(TCapitalBespeak tCapitalBespeak) {
		return tCapitalBespeakDao.findBespeaking(tCapitalBespeak);
	}
	public Map<String, Object>  findCancelBespeak(TCapitalBespeak tCapitalBespeak) {
		return tCapitalBespeakDao.findCancelBespeak(tCapitalBespeak);
	}
}