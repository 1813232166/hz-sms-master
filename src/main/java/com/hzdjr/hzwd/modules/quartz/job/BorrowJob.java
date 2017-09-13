package com.hzdjr.hzwd.modules.quartz.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.hzdjr.hzwd.common.utils.JedisUtils;

public class BorrowJob extends QuartzJobBean {
	private final Logger logger = LoggerFactory.getLogger(BorrowJob.class);


	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {


		logger.debug("开始定时调用更新编号，time：{}",new Date());
		try {
			long del = JedisUtils.del("borrowNo");
			System.out.println(del);
		} catch (Exception e) {
			logger.error("更新编号失败", e);
		}

		logger.debug("结束定时调用更新编号，time：{}",new Date());
	}

}
