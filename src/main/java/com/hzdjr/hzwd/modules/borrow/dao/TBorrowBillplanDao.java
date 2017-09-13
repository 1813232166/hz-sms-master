/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrowBillplan;

/**
 * 还款计划DAO接口
 * @author 秦桂晶
 * @version 2016-10-11
 */
@MyBatisDao
public interface TBorrowBillplanDao extends CrudDao<TBorrowBillplan> {
	
}