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
import com.hzdjr.hzwd.modules.operation.dao.ActivityPracticeMoneyProfitDao;
import com.hzdjr.hzwd.modules.operation.entity.ActivityPracticeMoney;
import com.hzdjr.hzwd.modules.operation.entity.ActivityPracticeMoneyProfit;

/**
 * 体验金收益列表Service
 * 
 * @author hdj
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class ActivityPracticeMoneyProfitService
		extends CrudService<ActivityPracticeMoneyProfitDao, ActivityPracticeMoneyProfit> {

	@Autowired
	ActivityPracticeMoneyProfitDao activityPracticeMoneyProfitDao;

	public ActivityPracticeMoneyProfit get(String id) {
		return super.get(id);
	}

	public List<ActivityPracticeMoneyProfit> findList(ActivityPracticeMoneyProfit activityPracticeMoneyProfit) {
		return super.findList(activityPracticeMoneyProfit);
	}

	public Page<ActivityPracticeMoneyProfit> findPage(Page<ActivityPracticeMoneyProfit> page,
			ActivityPracticeMoneyProfit activityPracticeMoneyProfit) {
		return super.findPage(page, activityPracticeMoneyProfit);
	}

	@Transactional(readOnly = false)
	public void save(ActivityPracticeMoneyProfit activityPracticeMoneyProfit) {
		super.save(activityPracticeMoneyProfit);
	}

	@Transactional(readOnly = false)
	public void delete(ActivityPracticeMoneyProfit activityPracticeMoneyProfit) {
		super.delete(activityPracticeMoneyProfit);
	}

	// 获取总发放数
	public Map<String, Object> getTotalmoneyCounts(ActivityPracticeMoneyProfit activityPracticeMoneyProfit) {

		return activityPracticeMoneyProfitDao.getTotalmoneyCounts(activityPracticeMoneyProfit);

	}

	// 查询收益明细
	public Page<ActivityPracticeMoney> getProfitParticulars(Page<ActivityPracticeMoney> page,
			ActivityPracticeMoney activityPracticeMoney) {
		activityPracticeMoney.setPage(page);
		page.setList(activityPracticeMoneyProfitDao.getProfitParticulars(activityPracticeMoney));
		return page;

	}

	// 查询收益明细
	public List<ActivityPracticeMoney> getUpdateProfitParticulars(ActivityPracticeMoney activityPracticeMoney) {
		
		return activityPracticeMoneyProfitDao.getProfitParticulars(activityPracticeMoney);

	}

	// 明细总发放数
	public Map<String, Object> getParticularsCounts(ActivityPracticeMoney activityPracticeMoney) {

		return activityPracticeMoneyProfitDao.getParticularsCounts(activityPracticeMoney);

	}
	
	//获取单个收益人信息
	public ActivityPracticeMoney getPracticeMoney(String id){
		return activityPracticeMoneyProfitDao.getPracticeMoney(id);
	}

	// 体验金收益补发成功修改
	@Transactional(readOnly = false)
	public void updateProfitSuccess(String id) {
		activityPracticeMoneyProfitDao.updateProfitSuccess(id);
	}

	// 体验金收益补发失败修改
	@Transactional(readOnly = false)
	public void updateProfitFail(String id) {
		activityPracticeMoneyProfitDao.updateProfitFail(id);
	}

	// 体验金补发详情成功修改  
	@Transactional(readOnly = false)
	public void updateSuccess(String id) {
		activityPracticeMoneyProfitDao.updateSuccess(id);
	}

	// 体验金补发详情失败修改
	@Transactional(readOnly = false)
	public void updateFail(String id) {
		activityPracticeMoneyProfitDao.updateFail(id);
	}

}