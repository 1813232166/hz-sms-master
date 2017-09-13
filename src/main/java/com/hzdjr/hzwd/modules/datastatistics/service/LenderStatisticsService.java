package com.hzdjr.hzwd.modules.datastatistics.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.modules.datastatistics.dao.LenderStatisticsDao;
import com.hzdjr.hzwd.modules.datastatistics.entity.LenderStatistics;

@Service
@Transactional
public class LenderStatisticsService {

	private static Logger logger = LoggerFactory.getLogger(BorrowStatisticsService.class);

	@Autowired
	private LenderStatisticsDao lenderStatisticsDao;
	
	public LenderStatistics findLenderStatistics() {
		logger.info("数据统计出借查询开始");
		LenderStatistics l=new LenderStatistics();
		l.setTotalInvest(lenderStatisticsDao.findTotalInvest());
		l.setSumInvestAmount(lenderStatisticsDao.findSumInvestAmount());
		l.setSumAbleBalanceAmount(lenderStatisticsDao.findSumAbleBalanceAmount());
		l.setTotalPayoff(lenderStatisticsDao.findTotalPayoff());
		l.setSumActualCapitalAmount(lenderStatisticsDao.findSumActualCapitalAmount());
		
		l.setSumActualInteAmount(lenderStatisticsDao.findSumActualCapitalAmount());
		l.setTotalRepaying(lenderStatisticsDao.findTotalRepaying());
		l.setSumOughtCapitalAmount(lenderStatisticsDao.findSumOughtCapitalAmount());
		l.setSumOughtInteAmount(lenderStatisticsDao.findSumOughtInteAmount());
		l.setTotalOverdue(lenderStatisticsDao.findTotalOverdue());
		
		l.setSumOverdueCapitalAmount(lenderStatisticsDao.findSumOverdueCapitalAmount());
		logger.info("数据统计出借查询结束");
		return l;
	}

	public void recordLenderStatistics() {
		LenderStatistics l = findLenderStatistics();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("l", l);
		map.put("from", "19700101");
		map.put("to", new Date());
		lenderStatisticsDao.recordLenderStatistics(map);
	}
	
	public LenderStatistics findLenderStatisticsRecord(){
		return lenderStatisticsDao.findLenderStatisticsRecord();
	}

}
