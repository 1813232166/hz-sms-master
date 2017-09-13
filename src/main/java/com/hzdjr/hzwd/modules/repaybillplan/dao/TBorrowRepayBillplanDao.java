/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.repaybillplan.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.repaybillplan.entity.TBorrowRepayBillplan;

/**
 * 回款管理列表DAO接口
 * @author yansy
 * @version 2016-10-12
 */
@MyBatisDao
public interface TBorrowRepayBillplanDao extends CrudDao<TBorrowRepayBillplan> {
	
}