/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.operation.dao.ActivityDao;
import com.hzwealth.sms.modules.operation.entity.Activity;

/**
 * 活动列表Service
 * @author xuchenglin
 * @version 2016-10-21
 */
@Service
@Transactional(readOnly = true)
public class ActivityService extends CrudService<ActivityDao, Activity> {

	public Activity get(String id) {
		return super.get(id);
	}
	
	public List<Activity> findList(Activity activity) {
		return super.findList(activity);
	}
	
	public Page<Activity> findPage(Page<Activity> page, Activity activity) {
		return super.findPage(page, activity);
	}
	
	@Transactional(readOnly = false)
	public void save(Activity activity) {
		super.save(activity);
	}
	
	@Transactional(readOnly = false)
	public void delete(Activity activity) {
		super.delete(activity);
	}
	
}