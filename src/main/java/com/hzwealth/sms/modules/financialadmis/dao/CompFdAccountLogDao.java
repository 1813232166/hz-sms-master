/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.financialadmis.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.financialadmis.entity.CompFdAccountLog;


/**
 * 平台账户DAO接口
 * @author xq
 * @version 2016-10-18
 */
@MyBatisDao
public interface CompFdAccountLogDao extends CrudDao<CompFdAccountLog> {
	
}