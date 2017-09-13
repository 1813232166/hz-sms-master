/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.operation.dao.ActivityPointDao;
import com.hzdjr.hzwd.modules.operation.entity.ActivityPoint;

/**
 * 积分发放列表Service
 * 
 * @author hdj
 * @version 2016-10-24
 */
@Service
@Transactional(readOnly = true)
public class ActivityPointService extends CrudService<ActivityPointDao, ActivityPoint> {

	@Autowired
	ActivityPointDao activityPointDao;

	public ActivityPoint get(String id) {
		return super.get(id);
	}

	public List<ActivityPoint> findList(ActivityPoint point) {
		return super.findList(point);
	}

	public Page<ActivityPoint> findPage(Page<ActivityPoint> page, ActivityPoint point) {
		return super.findPage(page, point);
	}

	@Transactional(readOnly = false)
	public void save(ActivityPoint point) {
		super.save(point);
	}

	@Transactional(readOnly = false)
	public void delete(ActivityPoint point) {
		super.delete(point);
	}

	// 获取积分总数
	public Map<String, Object> getPointCounts(ActivityPoint point) {
		
		return activityPointDao.getPointCounts(point);

	}

	// 导出积分列表
	public List<ActivityPoint> exportPointFile(ActivityPoint point) {

		return activityPointDao.exportPointFile(point);

	}

}