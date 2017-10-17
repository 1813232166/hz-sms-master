/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.repaybillplan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.repaybillplan.dao.TBorrowRepayBillplanDao;
import com.hzwealth.sms.modules.repaybillplan.entity.TBorrowRepayBillplan;

/**
 * 回款管理列表Service
 * @author yansy
 * @version 2016-10-12
 */
@Service
@Transactional(readOnly = true)
public class TBorrowRepayBillplanService extends CrudService<TBorrowRepayBillplanDao, TBorrowRepayBillplan> {

	public TBorrowRepayBillplan get(String id) {
		return super.get(id);
	}
	
	public List<TBorrowRepayBillplan> findList(TBorrowRepayBillplan tBorrowRepayBillplan) {
		return super.findList(tBorrowRepayBillplan);
	}
	
	public Page<TBorrowRepayBillplan> findPage(Page<TBorrowRepayBillplan> page, TBorrowRepayBillplan tBorrowRepayBillplan) {
		return super.findPage(page, tBorrowRepayBillplan);
	}
	
	@Transactional(readOnly = false)
	public void save(TBorrowRepayBillplan tBorrowRepayBillplan) {
		super.save(tBorrowRepayBillplan);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBorrowRepayBillplan tBorrowRepayBillplan) {
		super.delete(tBorrowRepayBillplan);
	}
	
}