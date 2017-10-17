package com.hzwealth.sms.modules.moneystatistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.modules.moneystatistics.dao.MoneystatisticsDao;
import com.hzwealth.sms.modules.moneystatistics.entity.Moneystatistics;

@Service
@Transactional
public class MoneystatisticsService {

	@Autowired
	private MoneystatisticsDao moneystatisticsDao;
	
	public List<Moneystatistics> findMoneystatisticsList() {
		
		return moneystatisticsDao.findMoneystatisticsList();
	}

}
