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
import com.hzwealth.sms.modules.operation.dao.ActivityPracticeMoneyDao;
import com.hzwealth.sms.modules.operation.entity.ActivityPracticeMoney;

/**
 * 体验金发放列表Service
 * 
 * @author hdj
 * @version 2016-10-27
 */
@Service
@Transactional(readOnly = true)
public class ActivityPracticeMoneyService extends CrudService<ActivityPracticeMoneyDao, ActivityPracticeMoney> {

	@Autowired
	ActivityPracticeMoneyDao activityPracticeMoneyDao;

	public ActivityPracticeMoney get(String id) {
		return super.get(id);
	}

	public List<ActivityPracticeMoney> findList(ActivityPracticeMoney givePracticeMoney) {
		return super.findList(givePracticeMoney);
	}

	public Page<ActivityPracticeMoney> findPage(Page<ActivityPracticeMoney> page,
			ActivityPracticeMoney givePracticeMoney) {
		return super.findPage(page, givePracticeMoney);
	}

	@Transactional(readOnly = false)
	public void save(ActivityPracticeMoney givePracticeMoney) {
		super.save(givePracticeMoney);
	}

	@Transactional(readOnly = false)
	public void delete(ActivityPracticeMoney givePracticeMoney) {
		super.delete(givePracticeMoney);
	}

	// 查询总体验金
	public Map<String, Object> getPracticeMoney(ActivityPracticeMoney practiceMoney) {

		return activityPracticeMoneyDao.getPracticeMoney(practiceMoney);

	}

	// 导出列表数据
	public List<ActivityPracticeMoney> exportPracticeMoneyFile(ActivityPracticeMoney practiceMoney) {

		return activityPracticeMoneyDao.exportPracticeMoneyFile(practiceMoney);

	}

}