package com.hzdjr.hzwd.modules.finance.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.Finance;

@MyBatisDao
public interface LendPlanAuditDao extends CrudDao<Finance>{
	List<Finance> getLendPlanDetailById(String id);
	int lendPlanAudit(Map<String,Object> map);
	int updateFinanceStatus(Map<String,Object> map);
}
