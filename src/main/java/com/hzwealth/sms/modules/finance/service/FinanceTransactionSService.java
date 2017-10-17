/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.finance.dao.FinanceTransactionSDao;
import com.hzwealth.sms.modules.finance.entity.FinanceTransactionS;

/**
 * 全部交易单Service
 * @author XJin
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class FinanceTransactionSService extends CrudService<FinanceTransactionSDao, FinanceTransactionS> {

	public FinanceTransactionS get(String id) {
		return super.get(id);
	}
	
	public List<FinanceTransactionS> findList(FinanceTransactionS financeTransaction) {
		return super.findList(financeTransaction);
	}
	
	public Page<FinanceTransactionS> findPage(Page<FinanceTransactionS> page, FinanceTransactionS financeTransaction) {
		return super.findPage(page, financeTransaction);
	}
	
	@Transactional(readOnly = false)
	public void save(FinanceTransactionS financeTransaction) {
		super.save(financeTransaction);
	}
	
	@Transactional(readOnly = false)
	public void delete(FinanceTransactionS financeTransaction) {
		super.delete(financeTransaction);
	}
	
}