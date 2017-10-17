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
import com.hzwealth.sms.modules.borrow.dao.BorrowDao;
import com.hzwealth.sms.modules.borrow.entity.Borrow;

/**
 * 普享表申请列表Service
 * @author hdj
 * @version 2016-10-13
 */
@Service
@Transactional(readOnly = true)
public class BorrowService extends CrudService<BorrowDao, Borrow> {

	@Autowired
	BorrowDao borrowDao;
	
	public Borrow get(String id) {
		return super.get(id);
	}
	
	public List<Borrow> findList(Borrow borrow) {
		return super.findList(borrow);
	}
	
	public Page<Borrow> findPage(Page<Borrow> page, Borrow borrow) {
		return super.findPage(page, borrow);
	}
	
	/**
	 * TODO	导出数据
	 * @param page
	 * @param borrow
	 * @return
	 */
	public Page<Borrow> exportBorrow(Page<Borrow> page, Borrow borrow) {
		borrow.setPage(page);
		page.setList(borrowDao.findList(borrow));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(Borrow borrow) {
		super.save(borrow);
	}
	
	@Transactional(readOnly = false)
	public void delete(Borrow borrow) {
		super.delete(borrow);
	}
	
	public Map<String,Object> getBorrowCounts(Borrow borrow){
		
		return borrowDao.getBorrowCounts(borrow);
	}
	
}