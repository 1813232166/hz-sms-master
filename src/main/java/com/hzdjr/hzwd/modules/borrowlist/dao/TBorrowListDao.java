/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrowlist.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.Borrow;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrowBillplan;
import com.hzdjr.hzwd.modules.borrowlist.entity.TBorrowList;
import com.hzdjr.hzwd.modules.invest.entity.Invest;


/**
 * 散标列表DAO接口
 * @author xq
 * @version 2017-05-04
 */
@MyBatisDao
public interface TBorrowListDao extends CrudDao<TBorrowList> {
    /**
     * 查询标的的还款计划
     * @param tBorrow
     * @return
     */
    List<TBorrowBillplan>  findTBorrowBillplans(@Param("set")Set<TBorrow> tBorrows);
    /**
     * 散标集关联数据
     * @param tBorrowList
     * @return
     */
    List<TBorrowList>  findRelateList(List<String> blistIdS);
    /**
     * 散标集总数
     * @param tBorrowList
     * @return
     */
    Map<String,Object>  findBorrowListSum(TBorrowList tBorrowList);
    /**
     * 导出散标集列表
     * @param tBorrowList
     * @return
     */
    List<TBorrowList> exportBorrowList(TBorrowList tBorrowList);
    /**
     * 获得TBorrow
     * @param id
     * @return
     */
    List<TBorrow> getTBorrow(String id);
    /**
     * 获取相应标的的出借记录
     * @param inv
     * @return
     */
    List<Invest> getInvRecByBid(String id);
    /**
     * 新建普享标集合时查询待选列表
     * @param tBorrow
     * @return
     */
    List<TBorrow> findNewBorrowList(TBorrow tBorrow);
    /**
     * 查询出结果集数量
     * @param tBorrow
     * @return
     */
    BigDecimal findcountMount(TBorrow tBorrow);

	/**
	 * 
	 * @Title: findLoanByBorrowsCodes   
	 * @Description: 通过标的编号查询借款
	 * @param list
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 5, 2017 11:09:41 AM
	 */
	List<Borrow> findLoanByBorrowsCodes(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: releaseBorrows   
	 * @Description: 发布散标
	 * @param borrows
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 4:31:39 PM
	 */
	int releaseBorrows(List<Borrow> borrows);
	
	/**
	 * 
	 * @Title: releaseBorrowList   
	 * @Description: 发布散标集  
	 * @param borrowList
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 7:14:37 PM
	 */
	int releaseBorrowList(TBorrowList borrowList);
	/**
	 * 更新单个散标为终审成功
	 * @param bids
	 * @return
	 */
    int updatePerBorrowStatus(@Param("set")Set<TBorrow> borrows);
}
