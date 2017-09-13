package com.hzdjr.hzwd.modules.usercount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzdjr.hzwd.modules.usercount.dao.LendPlanDao;
import com.hzdjr.hzwd.modules.usercount.dao.UserInfoDao;
import com.hzdjr.hzwd.modules.usercount.entity.LendPlan;
import com.hzdjr.hzwd.modules.usercount.entity.PuCount;
import com.hzdjr.hzwd.modules.usercount.entity.UserInfo;

@Service("lendPlanService")
public class LendPlanService {
	@Autowired
	private LendPlanDao lendPlanDao;

	public List<LendPlan> getLendPlan(Map<String, String> map){
		
		return lendPlanDao.getLendPlan(map);
	}
	
	
	public List<PuCount> getPuCount(Map<String, String> map){
		
		return lendPlanDao.getPuCount(map);
	}
}
