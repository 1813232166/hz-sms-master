/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.TFinanceBespeak;

/**
 * 预约审核DAO接口
 * @author HDG
 * @version 2017-07-03
 */
@MyBatisDao
public interface TFinanceBespeakDao extends CrudDao<TFinanceBespeak> {
	List<TFinanceBespeak> bespeakDetailById(String id);
	int bespeakAudit(Map<String,Object> map);
	int updateTFinanceBespeakStatus(Map<String,Object> map);
}