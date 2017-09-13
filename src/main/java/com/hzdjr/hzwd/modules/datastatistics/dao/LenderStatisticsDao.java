package com.hzdjr.hzwd.modules.datastatistics.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.datastatistics.entity.LenderStatistics;

@MyBatisDao
public interface LenderStatisticsDao {

	/*总出借笔数*/
	Long findTotalInvest();
	
	/*总出借金额*/
	BigDecimal findSumInvestAmount();

	/*出借用户剩余金额（总）*/
	BigDecimal findSumAbleBalanceAmount();

	/*已结清笔数*/
	Long findTotalPayoff();
	
	/*已回收本金*/
	BigDecimal findSumActualCapitalAmount();
	
	

	/*已回收利息*/
	BigDecimal findSumActualInteAmount();

	/*还款中笔数*/
	Long findTotalRepaying();
	
	/*待回收本金*/
	BigDecimal findSumOughtCapitalAmount();

	/*待回收利息*/
	BigDecimal findSumOughtInteAmount();

	/*已逾期笔数*/
	Long findTotalOverdue();
	
	
	
	/*已逾期本金*/
	BigDecimal findSumOverdueCapitalAmount();
	
	void recordLenderStatistics(Map<String, Object> map);

	LenderStatistics findLenderStatisticsRecord();

}
