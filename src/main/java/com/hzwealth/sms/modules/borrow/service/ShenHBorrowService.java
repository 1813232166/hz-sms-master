/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.borrow.dao.ShenHBorrowDao;
import com.hzwealth.sms.modules.borrow.entity.ShenHBorrow;

/**
 * 普享表审核未通过列表Service
 * 
 * @author hdj
 * @version 2016-10-13
 */
@Service
@Transactional(readOnly = true)
public class ShenHBorrowService extends CrudService<ShenHBorrowDao, ShenHBorrow> {

	@Autowired
	ShenHBorrowDao shenHBorrowDao;
	
	public ShenHBorrow get(String id) {
		return super.get(id);
	}

	public List<ShenHBorrow> findList(ShenHBorrow shenHBorrow) {
		return super.findList(shenHBorrow);
	}

	public Page<ShenHBorrow> findPage(Page<ShenHBorrow> page, ShenHBorrow shenHBorrow) {
		return super.findPage(page, shenHBorrow);
	}

	@Transactional(readOnly = false)
	public void save(ShenHBorrow shenHBorrow) {
		super.save(shenHBorrow);
	}

	@Transactional(readOnly = false)
	public void delete(ShenHBorrow shenHBorrow) {
		super.delete(shenHBorrow);
	}

	public Map<String, Object> getBorrowCounts(ShenHBorrow shenHBorrow) {

		return shenHBorrowDao.getBorrowCounts(shenHBorrow);
	}

	public String findAuditSuggest(String borrowId) {
		// TODO Auto-generated method stub
		return shenHBorrowDao.findAuditSuggest(borrowId);
	}

}