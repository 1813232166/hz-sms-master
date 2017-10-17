/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.operation.dao.ActivityMoneyDao;
import com.hzwealth.sms.modules.operation.entity.ActivityMoney;

/**
 * 活动现金Service
 * @author hdj
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class ActivityMoneyService extends CrudService<ActivityMoneyDao, ActivityMoney> {

	@Autowired
	ActivityMoneyDao activityMoneyDao;
	
	public ActivityMoney get(String id) {
		return super.get(id);
	}
	
	public List<ActivityMoney> findList(ActivityMoney activityMoney) {
		return super.findList(activityMoney);
	}
	
	public Page<ActivityMoney> findPage(Page<ActivityMoney> page, ActivityMoney activityMoney) {
		return super.findPage(page, activityMoney);
	}
	
	@Transactional(readOnly = false)
	public void save(ActivityMoney activityMoney) {
		super.save(activityMoney);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActivityMoney activityMoney) {
		super.delete(activityMoney);
	}
	
	//获取总现金数
	public Map<String,Object> getActivityMoney(ActivityMoney activityMoney){
		
		return activityMoneyDao.getMoneyCounts(activityMoney);
		
	}
	
	//导出数据
	public List<ActivityMoney> exportMoneyFile(ActivityMoney activityMoney){
		
		return activityMoneyDao.exportMoneyFile(activityMoney);
	}
		
		
}