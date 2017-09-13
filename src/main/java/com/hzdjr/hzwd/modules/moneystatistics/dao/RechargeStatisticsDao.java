package com.hzdjr.hzwd.modules.moneystatistics.dao;

import java.util.List;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.moneystatistics.entity.RechargeStatistics;

@MyBatisDao
public interface RechargeStatisticsDao {

	List<RechargeStatistics> findRechargeStatisticsList();

}
