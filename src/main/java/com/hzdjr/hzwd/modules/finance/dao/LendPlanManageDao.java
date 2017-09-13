/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.LendPlanManage;

/**
 * 出借计划管理详情DAO接口
 * @author FYP
 * @version 2017-07-03
 */
@MyBatisDao
public interface LendPlanManageDao extends CrudDao<LendPlanManage> {

	Map<String, Object> queryPrincipalInterest(Map<String, Object> map);

	List<LendPlanManage> findExportList(LendPlanManage lendPlanManage);
	
}