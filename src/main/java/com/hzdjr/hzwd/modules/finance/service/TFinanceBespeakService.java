/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.modules.finance.dao.TFinanceBespeakDao;
import com.hzdjr.hzwd.modules.finance.entity.TFinanceBespeak;
import com.hzdjr.hzwd.modules.sys.utils.UserUtils;

/**
 * 预约审核Service
 * @author HDG
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class TFinanceBespeakService extends CrudService<TFinanceBespeakDao, TFinanceBespeak> {

	@Autowired 
	private TFinanceBespeakDao tFinanceBespeakDao;
	public TFinanceBespeak get(String id) {
		return super.get(id);
	}
	
	public List<TFinanceBespeak> findList(TFinanceBespeak tFinanceBespeak) {
		return super.findList(tFinanceBespeak);
	}
	
	public Page<TFinanceBespeak> findPage(Page<TFinanceBespeak> page, TFinanceBespeak tFinanceBespeak) {
		return super.findPage(page, tFinanceBespeak);
	}
	
	@Transactional(readOnly = false)
	public void save(TFinanceBespeak tFinanceBespeak) {
		super.save(tFinanceBespeak);
	}
	
	@Transactional(readOnly = false)
	public void delete(TFinanceBespeak tFinanceBespeak) {
		super.delete(tFinanceBespeak);
	}
	public TFinanceBespeak bespeakDetail(String id){
		List<TFinanceBespeak> list=tFinanceBespeakDao.bespeakDetailById(id);
		return list.get(0);
	}
	@Transactional(readOnly = false)
	public int bespeakAudit(String tFinanceBespeakId ,String auditRadio,String auditArea){
		String id = UUIDUtil.genUUIDString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("user_id", UserUtils.getUser().getId());
		map.put("tFinanceBespeakId", tFinanceBespeakId);
		map.put("creat_time", new Date());
		map.put("cause", auditArea);
		map.put("examine_status", auditRadio);
		return  tFinanceBespeakDao.bespeakAudit(map);
	}
	@Transactional(readOnly = false)
	public int updateTFinanceBespeakStatus(String tFinanceBespeakId ,String auditRadio){
		Map<String, Object> map = new HashMap<String, Object>();
		if(auditRadio.equals("1")){//审核通过
			map.put("status", "4");//审核通过后 直接就是启用中
		}else{
			map.put("status", "3");
		}
		map.put("tFinanceBespeakId", tFinanceBespeakId);
		
		return  tFinanceBespeakDao.updateTFinanceBespeakStatus(map);
	}
}