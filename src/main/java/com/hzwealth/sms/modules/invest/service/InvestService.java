/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.invest.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.borrow.entity.Borrow;
import com.hzwealth.sms.modules.invest.dao.InvestDao;
import com.hzwealth.sms.modules.invest.entity.Invest;

/**
 * 出借记录Service
 * @author yansy
 * @version 2016-10-24
 */
@Service
@Transactional(readOnly = true)
public class InvestService extends CrudService<InvestDao, Invest> {
	
	@Autowired
	private InvestDao investDao;

	public Invest get(String id) {
		return super.get(id);
	}
	
	public List<Invest> findList(Invest invest) {
		return super.findList(invest);
	}
	
	public Page<Invest> findPage(Page<Invest> page, Invest invest) {
		return super.findPage(page, invest);
	}
	
	@Transactional(readOnly = false)
	public void save(Invest invest) {
		super.save(invest);
	}
	
	@Transactional(readOnly = false)
	public void delete(Invest invest) {
		super.delete(invest);
	}
	
	@Transactional(readOnly = false)
	public int updateInvestFlag(String bizId) {
		return investDao.updateInvestFlag(bizId);
	}

	public Borrow findBorrowById(String borrowid) {
		// TODO Auto-generated method stub
		return investDao.findBorrowById(borrowid);
	}
	
	public List<Invest> findInvestById(String borrowid) {
		// TODO Auto-generated method stub
		return investDao.findInvestById(borrowid);
	}

	public List<Invest> findExcel(Invest invest) {
		return investDao.findExcel(invest);
	}

	public String findDeadline(String borrowid, String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("borrowid", borrowid);
		map.put("id", id);
		return investDao.findDeadline(map);
	}

	public BigDecimal findSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return investDao.findSum(map);
	}
	
}