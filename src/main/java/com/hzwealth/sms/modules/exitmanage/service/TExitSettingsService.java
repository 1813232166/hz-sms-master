/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.exitmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.exitmanage.dao.TExitSettingsDao;
import com.hzwealth.sms.modules.exitmanage.entity.TExitSettings;

/**
 * 退出管理Service
 * @author HDG
 * @version 2017-07-21
 */
@Service
@Transactional(readOnly = true)
public class TExitSettingsService extends CrudService<TExitSettingsDao, TExitSettings> {
	@Autowired
	private TExitSettingsDao tExitSettingsDao;
	public TExitSettings get(String id) {
		return super.get(id);
	}
	
	public List<TExitSettings> findList(TExitSettings tExitSettings) {
		return super.findList(tExitSettings);
	}
	
	public Page<TExitSettings> findPage(Page<TExitSettings> page, TExitSettings tExitSettings) {
		return super.findPage(page, tExitSettings);
	}
	
	@Transactional(readOnly = false)
	public void save(TExitSettings tExitSettings) {
		super.save(tExitSettings);
	}
	
	@Transactional(readOnly = false)
	public void delete(TExitSettings tExitSettings) {
		super.delete(tExitSettings);
	}
	@Transactional(readOnly = false)
	public int updateCanQuitAmount(String id,String quitAmount){
		return tExitSettingsDao.updateCanQuitAmount(id,quitAmount);
	}
	@Transactional(readOnly = false)
	public int submitAudit(String id){
		return tExitSettingsDao.submitAudit(id);
	}
	@Transactional(readOnly = false)
	public int audit(String id,String auditRadio,String auditArea){
		return tExitSettingsDao.audit(id,auditRadio,auditArea);
	}
	@Transactional(readOnly = false)
	public int setExitMoneyShow(){
		return tExitSettingsDao.setExitMoneyShow();
	}
	public List<TExitSettings> findList() {
		return tExitSettingsDao.findList();
	}
}