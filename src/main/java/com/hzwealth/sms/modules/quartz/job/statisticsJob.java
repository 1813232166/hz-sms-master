package com.hzwealth.sms.modules.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hzwealth.sms.modules.datastatistics.service.BorrowStatisticsService;
import com.hzwealth.sms.modules.datastatistics.service.LenderStatisticsService;

/**
 * 数据统计定时器
 * @author LiXiang
 *
 */
public class statisticsJob extends QuartzJobBean {
	private final Logger logger = LoggerFactory.getLogger(statisticsJob.class);

	@Autowired
	private BorrowStatisticsService borrowStatisticsService;
	@Autowired
	private LenderStatisticsService lenderStatisticsService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("数据统计定时器查询开始");
		borrowStatisticsService.recordBorrowStatistics();
		lenderStatisticsService.recordLenderStatistics();
		logger.info("数据统计定时器查询结束");
	}

}
