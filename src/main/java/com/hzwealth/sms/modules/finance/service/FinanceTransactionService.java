/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.finance.dao.FinanceTransactionDao;
import com.hzwealth.sms.modules.finance.entity.FinanceInvest;
import com.hzwealth.sms.modules.finance.entity.FinanceTransaction;
import com.hzwealth.sms.modules.finance.enums.CapitalStatusEnum;
import com.hzwealth.sms.modules.finance.enums.InvestStatusEnum;
import com.hzwealth.sms.modules.repaybillplan.entity.TBorrowRepayBillplan;
import com.hzwealth.sms.modules.sys.entity.Dict;

/**
 * 出借计划交易Service
 * @author ZHF
 * @version 2017-06-28
 */
@Service
@Transactional(readOnly = true)
public class FinanceTransactionService extends CrudService<FinanceTransactionDao, FinanceTransaction> {

	public FinanceTransaction get(String id) {
		return super.get(id);
	}
	
	public List<FinanceTransaction> findList(FinanceTransaction financeTransaction) {
		return super.findList(financeTransaction);
	}
	
	public Page<FinanceTransaction> findPage(Page<FinanceTransaction> page, FinanceTransaction financeTransaction) {
		return super.findPage(page, financeTransaction);
	}
	
	@Transactional(readOnly = false)
	public void save(FinanceTransaction financeTransaction) {
		super.save(financeTransaction);
	}
	
	@Transactional(readOnly = false)
	public void delete(FinanceTransaction financeTransaction) {
		super.delete(financeTransaction);
	}
	
	/**
	 * 
	 * @Title: findFinanceProducts   
	 * @Description: 出借计划产品字典   
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jun 30, 2017 6:32:40 PM
	 */
	public List<Dict> findFinanceProducts(){
		List<Dict> financeProducts = dao.findFinanceProducts();
		return financeProducts;
	}
	
	/**
	 * 
	 * @Title: findExportList   
	 * @Description: 导出出借交易单
	 * @param financeTransaction
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jun 30, 2017 6:32:55 PM
	 */
	@Transactional(readOnly = false)
	public List<FinanceTransaction> findExportList(FinanceTransaction financeTransaction){
		List<FinanceTransaction> findExportList = dao.findExportList(financeTransaction);
		return findExportList;
	}
	
	/**
	 * 
	 * @Title: findCapitalStatuses   
	 * @Description: 出借计划状态字典
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jul 3, 2017 4:03:19 PM
	 */
	public List<Dict> findCapitalStatuses(){
		List<Dict> dictList = new ArrayList<Dict>();
		for(CapitalStatusEnum capitalStatusEnum: CapitalStatusEnum.values()) {
			dictList.add(new Dict(capitalStatusEnum.getValue(), capitalStatusEnum.getName()));
		}
		return dictList;
	}
	
	/**
	 * 
	 * @Title: findFinanceInvests   
	 * @Description: 订单投资记录
	 * @param capitalId
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jul 4, 2017 2:29:29 PM
	 */
	public List<FinanceInvest> findFinanceInvests(String capitalId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("capitalId", capitalId);
		List<FinanceInvest> financeInvests = dao.findFinanceInvests(paramMap);
		return financeInvests;
	}
	
	/**
	 * 
	 * @Title: findInvestStatuses   
	 * @Description: 出借计划投资状态字典  
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jul 4, 2017 3:52:40 PM
	 */
	public List<Dict> findInvestStatuses(){
		List<Dict> dictList = new ArrayList<Dict>();
		for(InvestStatusEnum investStatusEnum: InvestStatusEnum.values()) {
			dictList.add(new Dict(investStatusEnum.getValue(), investStatusEnum.getName()));
		}
		return dictList;
	}
	
	/**
	 * 
	 * @Title: findExpectedRepayBillplan   
	 * @Description: 查询预期回款   
	 * @param investId
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jul 6, 2017 7:48:44 PM
	 */
	public List<TBorrowRepayBillplan> findExpectedRepayBillplans(String investId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("investId", investId);
		List<TBorrowRepayBillplan> expectedRepayBillplans = dao.findExpectedRepayBillplans(paramMap);
		return expectedRepayBillplans;
	}
}

