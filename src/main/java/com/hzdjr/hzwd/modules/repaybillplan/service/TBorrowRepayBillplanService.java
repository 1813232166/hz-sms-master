/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.repaybillplan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.repaybillplan.entity.TBorrowRepayBillplan;
import com.hzdjr.hzwd.modules.repaybillplan.dao.TBorrowRepayBillplanDao;

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