package com.hzdjr.hzwd.modules.match.dao;

import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.match.entity.Capital;

/**
 * 资金列表DAO接口
 * @author FYP
 * @version 2017-06-12
 */
@MyBatisDao
public interface CapitalDao extends CrudDao<Capital> {

	//原始金额汇总
	Map<String, Object> findOriginalCount(Capital capital);
	//回款金额汇总
	Map<String, Object> findReturnCount(Capital capital);
	//更新权重
	int singleResetWeight(Capital capital);
	
}