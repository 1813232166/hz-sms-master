/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.finance.dao.LendPlanAuditDao;
import com.hzdjr.hzwd.modules.finance.dao.LendPlanManageDao;
import com.hzdjr.hzwd.modules.finance.entity.LendPlanManage;

/**
 * 出借计划管理详情Service
 * @author FYP
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class LendPlanManageService extends CrudService<LendPlanManageDao, LendPlanManage> {

	@Autowired
	private LendPlanManageDao lendPlanManageDao ;
	
	@Autowired
	private LendPlanAuditDao lendPlanAuditDao;
	
	public LendPlanManage get(String id) {
		return super.get(id);
	}
	
	public List<LendPlanManage> findList(LendPlanManage lendPlanManage) {
		return super.findList(lendPlanManage);
	}
	
	public Page<LendPlanManage> findPage(Page<LendPlanManage> page, LendPlanManage lendPlanManage) {
		return super.findPage(page, lendPlanManage);
	}
	
	@Transactional(readOnly = false)
	public void save(LendPlanManage lendPlanManage) {
		super.save(lendPlanManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(LendPlanManage lendPlanManage) {
		super.delete(lendPlanManage);
	}

	public Map<String, Object> queryPrincipalInterest(String financeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("financeId", financeId);
		return lendPlanManageDao.queryPrincipalInterest(map);
	}
	
	/**
	 * 
	* @Title: findExportList 
	* @Description: 出借详情导出List
	* @param @param lendPlanManage
	* @param @return    设定文件 
	* @return List<LendPlanManage>    返回类型 
	* @throws 
	* @author FYP
	 */
	public List<LendPlanManage> findExportList(LendPlanManage lendPlanManage) {
		List<LendPlanManage> findExportList = dao.findExportList(lendPlanManage);
		return findExportList;
	}
	
	
	/**
	 * 
	* @Title: updateFinanceStatus 
	* @Description: 更改出借计划状态
	* @param @param financeId
	* @param @param status
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws 
	* @author FYP
	 */
	@Transactional(readOnly = false)
	public int updateFinanceStatus(String financeId ,String status){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("finance_id", financeId);
		return  lendPlanAuditDao.updateFinanceStatus(map);
	}
	
}