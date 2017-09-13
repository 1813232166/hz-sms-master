package com.hzdjr.hzwd.modules.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.finance.dao.FinanceProductDao;
import com.hzdjr.hzwd.modules.finance.entity.FinanceProduct;

/**
 * 出借产品Service
 * @author FYP
 * @version 2017-06-28
 */
@Service
@Transactional(readOnly = true)
public class FinanceProductService extends CrudService<FinanceProductDao, FinanceProduct> {
	
	@Autowired
	private FinanceProductDao financeProductDao;
	
	
	public FinanceProduct get(String id) {
		return super.get(id);
	}
	
	public List<FinanceProduct> findList(FinanceProduct financeProduct) {
		return super.findList(financeProduct);
	}
	
	public Page<FinanceProduct> findPage(Page<FinanceProduct> page, FinanceProduct financeProduct) {
		return super.findPage(page, financeProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(FinanceProduct financeProduct) {
		super.save(financeProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(FinanceProduct financeProduct) {
		super.delete(financeProduct);
	}
	
	
	/**
	 * 
	* @Title: findAllList 
	* @Description: 查询所有列表
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author FYP
	 */
	public List<FinanceProduct>  findAllList() {
		return financeProductDao.findAllList();
	}
	/**
	 * 
	* @Title: findListNoXS 
	* @Description: 查询所有列表
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	* @author XJ
	 */
	public List<FinanceProduct>  findListNoXS() {
		return financeProductDao.findListNoXS();
	}
	
	
}