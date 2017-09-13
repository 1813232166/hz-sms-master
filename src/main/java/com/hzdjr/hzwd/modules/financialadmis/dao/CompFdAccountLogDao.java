/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.financialadmis.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.CompFdAccountLog;


/**
 * 平台账户DAO接口
 * @author xq
 * @version 2016-10-18
 */
@MyBatisDao
public interface CompFdAccountLogDao extends CrudDao<CompFdAccountLog> {
	
}