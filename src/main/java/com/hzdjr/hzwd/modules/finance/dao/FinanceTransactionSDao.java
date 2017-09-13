/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.FinanceTransactionS;

/**
 * 全部交易单DAO接口
 * @author XJin
 * @version 2017-07-03
 */
@MyBatisDao
public interface FinanceTransactionSDao extends CrudDao<FinanceTransactionS> {
	
}