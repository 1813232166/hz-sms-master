/**
 * 定义日志查询实现ServiceImpl
 */
package com.hzwealth.sms.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.modules.sys.dao.LogDao;
import com.hzwealth.sms.modules.sys.entity.Log;

/**
 * 日志查询Service（分页）
 * @author lvwenchao
 * @version 2016-05-11
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

	public Page<Log> findPage(Page<Log> page, Log log) {
		
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			//设置日志开始日期
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			//设置日志结束日期
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		
		return super.findPage(page, log);
		
	}
	
}
