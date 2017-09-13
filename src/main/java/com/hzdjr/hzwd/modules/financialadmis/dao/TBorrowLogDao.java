/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.financialadmis.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.TBorrowLog;

/**
 * 标的记录DAO接口
 * @author xq
 * @version 2016-10-19
 */
@MyBatisDao
public interface TBorrowLogDao extends CrudDao<TBorrowLog> {
	
}