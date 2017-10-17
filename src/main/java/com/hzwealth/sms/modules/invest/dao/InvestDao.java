/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.invest.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.Borrow;
import com.hzwealth.sms.modules.invest.entity.Invest;

/**
 * 出借记录DAO接口
 * @author yansy
 * @version 2016-10-24
 */
@MyBatisDao
public interface InvestDao extends CrudDao<Invest> {

	Borrow findBorrowById(String borrowid);

	String findDeadline(Map<String, String> map);

	BigDecimal findSum(Map<String, Object> map);
	
	List<Invest> findInvestById(String borrowId);
	
	List<Invest> findExcel(Invest invest);
	
	int updateInvestFlag(String bizId);
	
}