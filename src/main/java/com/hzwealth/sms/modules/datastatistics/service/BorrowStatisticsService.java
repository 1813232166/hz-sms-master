package com.hzwealth.sms.modules.datastatistics.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.modules.datastatistics.dao.BorrowStatisticsDao;
import com.hzwealth.sms.modules.datastatistics.entity.BorrowStatistics;

@Service
@Transactional
public class BorrowStatisticsService {

	private static Logger logger = LoggerFactory.getLogger(BorrowStatisticsService.class);

	@Autowired
	private BorrowStatisticsDao borrowStatisticsDao;
	
	public BorrowStatistics findborrowStatistics() {
		logger.info("数据统计借款查询开始");
		BorrowStatistics b=new BorrowStatistics();
		b.setSumBorrowAmount(borrowStatisticsDao.findSumBorrowAmount());
		b.setTotalPublishedBorrow(borrowStatisticsDao.findTotalPublishedBorrow());
		b.setSumOverdueAmount(borrowStatisticsDao.findSumOverdueAmount());
		b.setTotalNormalRefund(borrowStatisticsDao.findTotalNormalRefund());
		b.setTotalSuccessFulBorrow(borrowStatisticsDao.findTotalSuccessFulBorrow());
		
		b.setSumAdvancesAmount(borrowStatisticsDao.findSumAdvancesAmount());
		b.setSumRefundAmount(borrowStatisticsDao.findSumRefundAmount());
		b.setTotalNormalRefundBorrow(borrowStatisticsDao.findTotalNormalRefundBorrow());
		b.setSumToAdvancesAmount(borrowStatisticsDao.findSumToAdvancesAmount());
		b.setSumToRefundAmount(borrowStatisticsDao.findSumToRefundAmount());
		
		b.setTotalOverdueRefund(borrowStatisticsDao.findTotalOverdueRefund());
		b.setTotalOverdue(borrowStatisticsDao.findTotalOverdue());
		b.setSumServiceCharge(borrowStatisticsDao.findSumServiceCharge());
		b.setTotalToRefund(borrowStatisticsDao.findTotalToRefund());
		b.setTotalSeriousOverdue(borrowStatisticsDao.findTotalSeriousOverdue());
		
		b.setSumCollectingBorrowAmount(borrowStatisticsDao.findSumCollectingBorrowAmount());
		logger.info("数据统计借款查询结束");
		return b;
	}
	
	public void recordBorrowStatistics() {
		BorrowStatistics b = findborrowStatistics();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("b", b);
		map.put("from", "19700101");
		map.put("to", new Date());
		borrowStatisticsDao.recordBorrowStatistics(map);
	}
	
	public BorrowStatistics findBorrowStatisticsRecord(){
		return borrowStatisticsDao.findBorrowStatisticsRecord();
	}

}
