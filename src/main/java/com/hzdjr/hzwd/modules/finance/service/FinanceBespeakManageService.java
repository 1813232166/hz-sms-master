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
import com.hzdjr.hzwd.modules.finance.dao.FinanceBespeakManageDao;
import com.hzdjr.hzwd.modules.finance.dao.TFinanceBespeakDao;
import com.hzdjr.hzwd.modules.finance.entity.FinanceBespeakManage;

/**
 * 预约管理Service
 * @author FYP
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class FinanceBespeakManageService extends CrudService<FinanceBespeakManageDao, FinanceBespeakManage> {
	@Autowired
	private FinanceBespeakManageDao financeBespeakManageDao ;
	
	@Autowired 
	private TFinanceBespeakDao tFinanceBespeakDao;
	
	public FinanceBespeakManage get(String id) {
		return super.get(id);
	}
	
	public List<FinanceBespeakManage> findList(FinanceBespeakManage financeBespeakManage) {
		return super.findList(financeBespeakManage);
	}
	
	public Page<FinanceBespeakManage> findPage(Page<FinanceBespeakManage> page, FinanceBespeakManage financeBespeakManage) {
		return super.findPage(page, financeBespeakManage);
	}
	
	@Transactional(readOnly = false)
	public void save(FinanceBespeakManage financeBespeakManage) {
		super.save(financeBespeakManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(FinanceBespeakManage financeBespeakManage) {
		super.delete(financeBespeakManage);
	}

	
	/**
	 * 
	* @Title: isCanCreate 
	* @Description: 是否能新增预约计划
	* @param @param productId
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws 
	* @author FYP
	 */
	public boolean isCanCreate(String productId) {
		int size =financeBespeakManageDao.queryThisProductListSize(productId);
		if (size>0) {
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 
	* @Title: queryTodayProductListSize 
	* @Description: 查询当天建了多少计划，生产计划编号用
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author FYP
	 */
	public String queryTodayProductListSize() {
		String size =financeBespeakManageDao.queryTodayProductListSize();
		return size;
	}
	/**
	 * 
	* @Title: updateFinanceBespeakStatus 
	* @Description: 更改预约管理状态
	* @param @param id
	* @param @param string    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author FYP
	 */
	@Transactional(readOnly = false)
	public void updateFinanceBespeakStatus(String id, String status) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("tFinanceBespeakId", id);
		
		tFinanceBespeakDao.updateTFinanceBespeakStatus(map);
	}
	
}