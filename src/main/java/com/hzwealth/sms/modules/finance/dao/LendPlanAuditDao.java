package com.hzwealth.sms.modules.finance.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.finance.entity.Finance;

@MyBatisDao
public interface LendPlanAuditDao extends CrudDao<Finance>{
	List<Finance> getLendPlanDetailById(String id);
	int lendPlanAudit(Map<String,Object> map);
	int updateFinanceStatus(Map<String,Object> map);
}
