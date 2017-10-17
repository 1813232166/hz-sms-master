/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.finance.entity.FinanceBespeakManage;

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