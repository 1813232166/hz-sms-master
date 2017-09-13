/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.hzdjr.hzwd.modules.borrow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.borrow.dao.TWillBorrowDao;
import com.hzdjr.hzwd.modules.borrow.entity.TWillBorrow;

/**
 * 借款意向表Service
 * @author xuchenglin
 * @version 2017-04-25
 */
@Service
@Transactional(readOnly = true)
public class TWillBorrowService extends CrudService<TWillBorrowDao, TWillBorrow> {

	public TWillBorrow get(String id) {
		return super.get(id);
	}
	
	public List<TWillBorrow> findList(TWillBorrow tWillBorrow) {
		return super.findList(tWillBorrow);
	}
	
	public Page<TWillBorrow> findPage(Page<TWillBorrow> page, TWillBorrow tWillBorrow) {
		return super.findPage(page, tWillBorrow);
	}
	
	@Transactional(readOnly = false)
	public void save(TWillBorrow tWillBorrow) {
		super.save(tWillBorrow);
	}
	
	@Transactional(readOnly = false)
	public void delete(TWillBorrow tWillBorrow) {
		super.delete(tWillBorrow);
	}
	
}