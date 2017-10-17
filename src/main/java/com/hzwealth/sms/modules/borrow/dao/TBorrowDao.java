/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.RepayPlanVo;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.invest.entity.Invest;

/**
 * 标的列表DAO接口
 * @author ln
 * @version 2016-10-12
 */
@MyBatisDao
public interface TBorrowDao extends CrudDao<TBorrow> {

	Map<String,Object> selectBorrowCount(TBorrow tBorrow);
	
	public TBorrow get(TBorrow tBorrow);
	
	List<TBorrow> findNewList(TBorrow tBorrow);

	int changeBorrowstatus(Map<String, String> map);

	BigDecimal findcountMount(TBorrow tBorrow);
	/**
	 * 获取相应标的的出借记录
	 * @param inv
	 * @return
	 */
	public List<Invest> getInvRecByBid(Invest inv);
	/**
	 * 匹配债权
	 * @param borrowId
	 * @return
	 */
	public List<TBorrow> getLoanRecByBid(String borrowId);
	/**
	 * 获取债权列表
	 * @param list
	 * @return
	 */
	List<TBorrow> getLoanByBorrowCode(List<String> list);
	/**
	 * 获取债权列表
	 * @param list
	 * @return
	 */
	List<TBorrow> getLoanByBorrowCodeNew(List<String> list);
	
	List<RepayPlanVo>queryPaymentDetails(RepayPlanVo rpv);
	/**
	 * 
	 * @param borrows
	 * @return
	 */
	Integer releaseBorrow(TBorrow borrows);

	void writeSuggest(Map<String, Object> map);

	void deleteSuggestByBorrowId(String borrowId);

	List<TBorrow> findExcelList(TBorrow params);
	
	List<TBorrow> findBorrowByJob();
	
	void updateBorrowOpenBorrowDate(String openborrowdate,String borrowId);
	
	HashMap<String,Object> getBankCardByUserId(String userId);
	
	TBorrow getBorrowByUserId(String userId);
	
	public TBorrow  getBorrowByCode(String borrowCode);
	
	public void  updateLbIng(String borrowCode);
	
	public TBorrow  getBorrowById(String borrowId);
	
	public int getBorrowPay(String borrowCode);

	Map<String,Object> findBorrowAuditStatus(String borrowId);
	

}