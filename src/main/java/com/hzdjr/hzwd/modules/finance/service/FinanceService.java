package com.hzdjr.hzwd.modules.finance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.finance.entity.Finance;
import com.hzdjr.hzwd.modules.finance.dao.FinanceDao;

/**
 * 出借计划管理Service
 * @author FYP
 * @version 2017-06-28
 */
@Service
@Transactional(readOnly = true)
public class FinanceService extends CrudService<FinanceDao, Finance> {

	@Autowired
	private FinanceDao financeDao ;
	
	public Finance get(String id) {
		return super.get(id);
	}
	
	public List<Finance> findList(Finance finance) {
		return super.findList(finance);
	}
	
	public Page<Finance> findPage(Page<Finance> page, Finance finance) {
		return super.findPage(page, finance);
	}
	
	@Transactional(readOnly = false)
	public void save(Finance finance) {
		super.save(finance);
	}
	
	@Transactional(readOnly = false)
	public void delete(Finance finance) {
		super.delete(finance);
	}

	/**
	 * 
	* @Title: groupFinaceStatus 
	* @Description: 分组查询 出借状态
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws 
	* @author FYP
	 */
	public List<Map<String, Object>> groupFinaceStatus(Finance finance) {
		return financeDao.groupFinaceStatus(finance);
	}

	/**
	 * 
	* @Title: queryCollectSumAmount 
	* @Description: 查询募集总额
	* @param @param finance
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author FYP
	 */
	public String queryCollectSumAmount(Finance finance) {
		return financeDao.queryCollectSumAmount(finance);
	}
	
	/**
	 * 
	* @Title: updateFinanceStatus 
	* @Description: 更改出借计划状态
	* @param @param financeId
	* @param @param status
	* @throws 
	* @author FYP
	 */
	@Transactional(readOnly = false)
	public int updateFinanceStatus(String financeId ,String status){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("financeId", financeId);
		return  financeDao.updateFinanceStatus(map);
	}

	/**
	 * 
	* @Title: isCanCreate 
	* @Description: 判断是否让创建出借计划
	* @param @param productId
	* @param @return    设定文件 
	* @return Boolean    返回类型 
	* @throws 
	* @author FYP
	 */
	public Boolean isCanCreate(String productId) {

		int size =financeDao.queryThisProductListSize(productId);
		
		if (size>0) {
			return false;
		}else{
			return true;
		}
	}

	
	public String queryTodayProductListSize() {
		String size =financeDao.queryTodayProductListSize();
		return size;
	}
	
}