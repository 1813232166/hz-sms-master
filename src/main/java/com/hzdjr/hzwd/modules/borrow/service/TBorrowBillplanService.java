/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrowBillplan;
import com.hzdjr.hzwd.modules.borrow.dao.TBorrowBillplanDao;

/**
 * 还款计划Service
 * @author 秦桂晶
 * @version 2016-10-11
 */
@Service
@Transactional(readOnly = true)
public class TBorrowBillplanService extends CrudService<TBorrowBillplanDao, TBorrowBillplan> {

	public TBorrowBillplan get(String id) {
		return super.get(id);
	}
	
	public List<TBorrowBillplan> findList(TBorrowBillplan tBorrowBillplan) {
		return super.findList(tBorrowBillplan);
	}
	
	public Page<TBorrowBillplan> findPage(Page<TBorrowBillplan> page, TBorrowBillplan tBorrowBillplan) {
		return super.findPage(page, tBorrowBillplan);
	}
	
	@Transactional(readOnly = false)
	public void save(TBorrowBillplan tBorrowBillplan) {
		super.save(tBorrowBillplan);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBorrowBillplan tBorrowBillplan) {
		super.delete(tBorrowBillplan);
	}
	
}