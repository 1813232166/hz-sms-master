package com.hzwealth.sms.modules.moneystatistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.modules.moneystatistics.dao.RechargeStatisticsDao;
import com.hzwealth.sms.modules.moneystatistics.entity.RechargeStatistics;

@Service
@Transactional
public class RechargeStatisticsService {

	@Autowired
	private RechargeStatisticsDao rechargeStatisticsDao;
	
	public List<RechargeStatistics> findRechargeStatisticsList() {

		return rechargeStatisticsDao.findRechargeStatisticsList();
	}

}
