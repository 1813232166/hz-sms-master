package com.hzwealth.sms.modules.datastatistics.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.datastatistics.entity.BorrowStatistics;

@MyBatisDao
public interface BorrowStatisticsDao {

	/*总借款金额*/
	BigDecimal findSumBorrowAmount();
	
	/*发布借款笔数*/
	Long findTotalPublishedBorrow();

	/*逾期本息总额*/
	BigDecimal findSumOverdueAmount();
	
	/*正常还款次数*/
	Long findTotalNormalRefund();
	
	/*累积成功借款笔数*/
	Long findTotalSuccessFulBorrow();
	
	
	
	/*已垫付逾期费用*/
	BigDecimal findSumAdvancesAmount();
	
	/*已还本息总额*/
	BigDecimal findSumRefundAmount();
	
	/*正常还清笔数*/
	Long findTotalNormalRefundBorrow();
	
	/*待垫付逾期费用*/
	BigDecimal findSumToAdvancesAmount();
	
	/*待还本息总额*/
	BigDecimal findSumToRefundAmount();
	
	
	
	/*逾期还清笔数*/
	Long findTotalOverdueRefund();
	
	/*逾期累积次数*/
	Long findTotalOverdue();
	
	/*已收借款服务费*/
	BigDecimal findSumServiceCharge();
	
	/*未还清笔数*/
	Long findTotalToRefund();
	
	/*严重逾期笔数*/
	Long findTotalSeriousOverdue();
	
	
	
	/*募集中借款总额*/
	BigDecimal findSumCollectingBorrowAmount();

	void recordBorrowStatistics(Map<String, Object> map);

	BorrowStatistics findBorrowStatisticsRecord();
}
