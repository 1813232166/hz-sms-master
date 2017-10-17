/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.finance.entity.FinanceTransactionS;

/**
 * 全部交易单DAO接口
 * @author XJin
 * @version 2017-07-03
 */
@MyBatisDao
public interface FinanceTransactionSDao extends CrudDao<FinanceTransactionS> {
	
}