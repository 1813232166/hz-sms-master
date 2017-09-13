package com.hzdjr.hzwd.modules.borrow.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowPicVo;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrowBillplan;
@MyBatisDao
public interface LoanFundDao {
	/**
	 * 获取借款列表
	 * @param tBorrow
	 * @return
	 */
	public List<TBorrow> getLoanList(TBorrow tBorrow);
	/**
	 * 借款总记录数
	 * @return
	 */
	public Map<String,Integer> getLoanCountList();
	/**
	 * 保存借款信息
	 * @param borrows
	 * @return
	 */
	public Integer saveBorrowInfo(List<TBorrow> borrows);
	/**
	 * 保存还款计划
	 * @param billplan
	 * @return
	 */
	public Integer saveBillPlan(TBorrowBillplan billplan);
	/**
	 * 查询是否有相应的还款计划
	 * @param applyId 借款编号
	 * @return
	 */
	public Map<String, Object> queryBilPlanByApplyId(String applyId);
	/**
	 * 查询相应标的的还款计划
	 */
	public List<TBorrowBillplan> queryBilPlanByBorrowId(TBorrowBillplan billplan);
	/**
	 * 查询借款信息
	 * @param map
	 * @return
	 */
	public TBorrow findTBorrowById(Map<String, Object> map);
	/**
	 * 保存借款资料
	 * @param map
	 */
	public void saveLoanInfo(Map<String, Object> map);
	/**
	 * 通过id删除借款资料
	 * @param id
	 * @return
	 */
	public Integer delPic(String id);
	/**
	 * 保存借款资料
	 * @param item
	 * @return
	 */
	public int batchSaveBorrowApply(List<Map<String, Object>> item);
	/**
	 * 标的是否已编辑
	 * @param bid
	 * @return
	 */
	public int updateIsEdit(String bid);
	/**
	 * 标的是否是已完成
	 * @param bid
	 * @return
	 */
	public void updateIsFinish(String bid);
	/**
	 * 获取相应标的的申请资料
	 * @param borrowId
	 * @return
	 */
	public List<BorrowPicVo> getBorrowPic(String borrowId);
	
	
}
