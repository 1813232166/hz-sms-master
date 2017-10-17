/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.financialadmis.dao;

import java.util.List;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.financialadmis.entity.TFdAccountLog;
import com.hzwealth.sms.modules.financialadmis.entity.TFdAccountLogVo;

/**
 * 财务管理DAO接口
 * @author xq
 * @version 2016-10-17
 */
@MyBatisDao
public interface TFdAccountLogDao extends CrudDao<TFdAccountLog> {

	List<TFdAccountLogVo> findexportList(TFdAccountLog tFdAccountLog);
	
}