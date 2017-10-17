package com.hzwealth.sms.modules.usercount.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.usercount.entity.LendPlan;
import com.hzwealth.sms.modules.usercount.entity.PuCount;
import com.hzwealth.sms.modules.usercount.entity.UserInfo;
@MyBatisDao
public interface LendPlanDao {

	List<LendPlan> getLendPlan(Map<String, String> map);

	List<PuCount> getPuCount(Map<String, String> map);

}
