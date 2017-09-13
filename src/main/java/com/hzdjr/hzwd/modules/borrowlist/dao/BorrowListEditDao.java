/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrowlist.dao;

import java.util.List;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.borrowlist.entity.TBorrowList;

/**
 * 已选标的列表DAO接口
 * @author ln
 * @version 2016-10-12
 */
@MyBatisDao
public interface BorrowListEditDao extends CrudDao<TBorrow> {
	
	
	
	public List<TBorrow> getBorrowListId(String borrowListId);
	
	public TBorrowList getBorrowListCode(String borrowListCode);
	
	public void updateBorrowList(TBorrowList tBorrowList);
	
	Integer updateBorrow(TBorrow borrows);
	
	List<TBorrow> getByBorrowId(List<String> list);

	Integer updateDeleteBorrow(TBorrow borrows);
}