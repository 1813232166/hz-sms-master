/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.FinanceInvest;
import com.hzdjr.hzwd.modules.finance.entity.FinanceTransaction;
import com.hzdjr.hzwd.modules.repaybillplan.entity.TBorrowRepayBillplan;
import com.hzdjr.hzwd.modules.sys.entity.Dict;

/**
 * 出借计划交易DAO接口
 * @author ZHF
 * @version 2017-06-28
 */
@MyBatisDao
public interface FinanceTransactionDao extends CrudDao<FinanceTransaction> {
	
	/**
	 * 
	 * @Title: findFinanceProducts   
	 * @Description: 查询出借交易单列表
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jun 30, 2017 6:29:37 PM
	 */
	List<Dict> findFinanceProducts();
	
	/**
	 * 
	 * @Title: findExportList   
	 * @Description: 导出出借交易单列表
	 * @param entity
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jun 30, 2017 6:30:28 PM
	 */
	List<FinanceTransaction> findExportList(FinanceTransaction entity);
	
	/**
	 * 
	 * @Title: findFinanceInvests   
	 * @Description: 查询出借交易单投资列表
	 * @param paramMap
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jul 4, 2017 2:04:32 PM
	 */
	List<FinanceInvest> findFinanceInvests(Map<String, Object> paramMap);
	
	/**
	 * 
	 * @Title: findExpectedRepayBillplan   
	 * @Description: 查询预期回款   
	 * @param paramMap
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jul 6, 2017 7:41:57 PM
	 */
	List<TBorrowRepayBillplan> findExpectedRepayBillplans(Map<String, Object> paramMap);
}