/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.FinanceBespeakManage;

/**
 * 预约管理DAO接口
 * @author FYP
 * @version 2017-07-05
 */
@MyBatisDao
public interface FinanceBespeakManageDao extends CrudDao<FinanceBespeakManage> {
	
	int queryThisProductListSize(String productId);

	String queryTodayProductListSize();
}