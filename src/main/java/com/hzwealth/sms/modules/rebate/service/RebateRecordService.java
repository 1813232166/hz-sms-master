package com.hzwealth.sms.modules.rebate.service;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.rebate.dao.RebateRecordDao;
import com.hzwealth.sms.modules.rebate.entity.InvestRebate;
import com.hzwealth.sms.modules.rebate.entity.InvestRecord;
import com.hzwealth.sms.modules.rebate.entity.RebateRecord;
@Service
@Transactional(readOnly = true)
public class RebateRecordService extends CrudService<RebateRecordDao,RebateRecord>{
	private final Logger logger = LoggerFactory.getLogger(RebateRecordService.class);
	@Autowired
	private InvestRebateService investRebateService;
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(RebateRecord rebateRecord) {
		logger.info("返佣记录："+rebateRecord.toString());
		super.save(rebateRecord);
	}
	/**
	 * 定时任务
	 * 月初，计算上月返佣记录
	 */
	@Transactional(readOnly = false)
	public void computerMonthRebateJob(){
		RebateRecord rebateRecord = new RebateRecord();
		//查询条件
		Calendar start = Calendar.getInstance(); 
		start.add(Calendar.MONTH, -1);
		start.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE , 0);
		start.set(Calendar.SECOND,0);
		Calendar end = Calendar.getInstance();
		end.add(Calendar.MONTH, -1);
		end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));  
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE , 59);
		end.set(Calendar.SECOND,59);
		
		InvestRecord investRecord = new InvestRecord();
		investRecord.setStartInvestTime(start.getTime());
		investRecord.setEndInvestTime(end.getTime());
		
		logger.info("统计月份为："+start.get(Calendar.YEAR) +"-"+(start.get(Calendar.MONTH)+1));
		
		//返佣笔数
		rebateRecord.setRebateAccount(investRebateService.queryRebateAccount(investRecord));
		//应返金额
		rebateRecord.setRebateAmount(investRebateService.queryRebateAmount(investRecord));
		//返佣人数
		rebateRecord.setRebateUserAccount(investRebateService.queryRebateUserAccount(investRecord));
		//返佣年月
		rebateRecord.setRebateYear(start.get(Calendar.YEAR));
		rebateRecord.setRebateMonth(start.get(Calendar.MONTH)+1);
		
		logger.info("统计结果："+rebateRecord.toString());
		//保存统计记录
		save(rebateRecord);
		
		//更新统计表和返佣记录表联系
		InvestRebate investRebate = new InvestRebate();
		investRebate.setRebateRecord(rebateRecord);
		investRebate.setInvestRecord(investRecord);
		investRebate.setUpdateDate(new Date());
		investRebateService.updateInvestRebateForRecordId(investRebate);
	}
}
