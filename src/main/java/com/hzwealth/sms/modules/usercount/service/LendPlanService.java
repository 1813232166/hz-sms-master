package com.hzwealth.sms.modules.usercount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.sms.modules.usercount.dao.LendPlanDao;
import com.hzwealth.sms.modules.usercount.dao.UserInfoDao;
import com.hzwealth.sms.modules.usercount.entity.LendPlan;
import com.hzwealth.sms.modules.usercount.entity.PuCount;
import com.hzwealth.sms.modules.usercount.entity.UserInfo;

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
