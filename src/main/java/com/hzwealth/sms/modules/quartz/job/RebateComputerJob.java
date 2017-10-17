package com.hzwealth.sms.modules.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hzwealth.sms.modules.rebate.service.InvestRebateService;
/**
 * 计算返佣，1个小时计算一次，所以不用分页计算，全部取出计算
 * @author wzb
 *
 */
public class RebateComputerJob extends QuartzJobBean {
	private final Logger logger = LoggerFactory.getLogger(RebateComputerJob.class);
	@Autowired
	private InvestRebateService investRebateService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("返佣计算开始==================");
		investRebateService.rebateComputerJob();
		logger.info("==================返佣计算结束");
	}

}
