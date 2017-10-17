/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.financialadmis.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.financialadmis.entity.TBorrowLog;

/**
 * 标的记录DAO接口
 * @author xq
 * @version 2016-10-19
 */
@MyBatisDao
public interface TBorrowLogDao extends CrudDao<TBorrowLog> {
	
}