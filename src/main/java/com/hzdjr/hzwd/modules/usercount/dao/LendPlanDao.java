package com.hzdjr.hzwd.modules.usercount.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.usercount.entity.LendPlan;
import com.hzdjr.hzwd.modules.usercount.entity.PuCount;
import com.hzdjr.hzwd.modules.usercount.entity.UserInfo;
@MyBatisDao
public interface LendPlanDao {

	List<LendPlan> getLendPlan(Map<String, String> map);

	List<PuCount> getPuCount(Map<String, String> map);

}
