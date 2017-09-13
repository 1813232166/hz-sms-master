/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.operation.entity.ActivityPracticeMoney;
import com.hzdjr.hzwd.modules.operation.entity.ActivityPracticeMoneyProfit;

/**
 * 体验金收益列表DAO接口
 * 
 * @author hdj
 * @version 2016-10-31
 */
@MyBatisDao
public interface ActivityPracticeMoneyProfitDao extends CrudDao<ActivityPracticeMoneyProfit> {

	// 获取总发放数
	Map<String, Object> getTotalmoneyCounts(ActivityPracticeMoneyProfit activityPracticeMoneyProfit);

	// 查询收益明细
	List<ActivityPracticeMoney> getProfitParticulars(ActivityPracticeMoney activityPracticeMoney);

	// 明细总数
	Map<String, Object> getParticularsCounts(ActivityPracticeMoney activityPracticeMoney);

	//获取单个收益人信息
	ActivityPracticeMoney getPracticeMoney(String id);
	
	// 体验金收益补发成功修改
	void updateProfitSuccess(String id);

	// 体验金收益补发失败修改
	void updateProfitFail(String id);

	// 体验金补发详情成功修改  
	void updateSuccess(String id);

	// 体验金补发详情失败修改
	void updateFail(String id);
	
}