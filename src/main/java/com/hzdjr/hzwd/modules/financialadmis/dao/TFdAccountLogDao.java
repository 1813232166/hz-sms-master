/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.financialadmis.dao;

import java.util.List;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.TFdAccountLog;
import com.hzdjr.hzwd.modules.financialadmis.entity.TFdAccountLogVo;

/**
 * 财务管理DAO接口
 * @author xq
 * @version 2016-10-17
 */
@MyBatisDao
public interface TFdAccountLogDao extends CrudDao<TFdAccountLog> {

	List<TFdAccountLogVo> findexportList(TFdAccountLog tFdAccountLog);
	
}