package com.hzwealth.sms.modules.moneystatistics.dao;

import java.util.List;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.moneystatistics.entity.RechargeStatistics;

@MyBatisDao
public interface RechargeStatisticsDao {

	List<RechargeStatistics> findRechargeStatisticsList();

}
