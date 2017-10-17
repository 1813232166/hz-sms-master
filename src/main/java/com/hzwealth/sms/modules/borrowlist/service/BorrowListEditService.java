/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrowlist.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrowlist.dao.BorrowListEditDao;
import com.hzwealth.sms.modules.borrowlist.entity.TBorrowList;

/**
 * 已选标的列表Service
 * @author ln
 * @version 2016-10-12
 */
@Service
@Transactional
public class BorrowListEditService extends CrudService<BorrowListEditDao, TBorrow> {
	
	@Autowired
	BorrowListEditDao borrowListEditDao;
	
	public List<TBorrow> getBorrowListId(String borrowListId){
		return borrowListEditDao.getBorrowListId(borrowListId);
	}
	public TBorrowList getBorrowListCode(String borrowListCode){
		return borrowListEditDao.getBorrowListCode(borrowListCode);
	}
	public void updateBorrowList(TBorrowList tBorrowList){
		borrowListEditDao.updateBorrowList(tBorrowList);
	}
	
	public void updateBorrow(List<TBorrow> borrows) {
		for (TBorrow tBorrow : borrows) {
			borrowListEditDao.updateBorrow(tBorrow);
		}
	}
	
	public List<TBorrow> getByBorrowId(List<String> list){
		return borrowListEditDao.getByBorrowId(list);
	}
	
	public void  updateDeleteBorrow(List<TBorrow> deleteBorrows){
		for (TBorrow tBorrow : deleteBorrows) {
			borrowListEditDao.updateDeleteBorrow(tBorrow);
		}
	}
	
	public void updateEditBorrowList(List<TBorrow> deleteBorrows, List<TBorrow> borrows, List<String> borrowsIds, TBorrowList tborrowList){
		//更新删除标的
		if(deleteBorrows.size()>0){
			this.updateDeleteBorrow(deleteBorrows);
		}
		this.updateBorrow(borrows);
		List<TBorrow> borrowId = this.getByBorrowId(borrowsIds);
		BigDecimal borrowsTotal = new BigDecimal(0);
		for(TBorrow tb:borrowId){//计算散标集金额
			BigDecimal borrowAmont=new BigDecimal(tb.getBorrowamount());
			borrowsTotal = borrowsTotal.add(borrowAmont);
		}
		tborrowList.setBorrowListAmount(borrowsTotal);
		this.updateBorrowList(tborrowList);
	}
	
}