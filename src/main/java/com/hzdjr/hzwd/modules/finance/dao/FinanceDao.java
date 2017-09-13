package com.hzdjr.hzwd.modules.finance.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.Finance;

/**
 * 出借计划管理DAO接口
 * @author FYP
 * @version 2017-06-28
 */
@MyBatisDao
public interface FinanceDao extends CrudDao<Finance> {

	List<Map<String, Object>> groupFinaceStatus(Finance finance);

	String queryCollectSumAmount(Finance finance);

	int updateFinanceStatus(Map<String, Object> map);

	int queryThisProductListSize(String productId);

	String queryTodayProductListSize();
	
}