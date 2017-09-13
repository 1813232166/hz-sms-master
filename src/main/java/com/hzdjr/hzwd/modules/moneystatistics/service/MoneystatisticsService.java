package com.hzdjr.hzwd.modules.moneystatistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.modules.moneystatistics.dao.MoneystatisticsDao;
import com.hzdjr.hzwd.modules.moneystatistics.entity.Moneystatistics;

@Service
@Transactional
public class MoneystatisticsService {

	@Autowired
	private MoneystatisticsDao moneystatisticsDao;
	
	public List<Moneystatistics> findMoneystatisticsList() {
		
		return moneystatisticsDao.findMoneystatisticsList();
	}

}
