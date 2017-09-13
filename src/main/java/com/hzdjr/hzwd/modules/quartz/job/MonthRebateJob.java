package com.hzdjr.hzwd.modules.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hzdjr.hzwd.modules.rebate.service.RebateRecordService;
/**
 * 每月计算
 * @author wzb
 *
 */
public class MonthRebateJob extends QuartzJobBean{
	private final Logger logger = LoggerFactory.getLogger(MonthRebateJob.class);
	@Autowired
	private RebateRecordService rebateRecordService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("每月统计返佣开发================");
		rebateRecordService.computerMonthRebateJob();
		logger.info("==================统计结束");
	}
	
}
