package com.hzwealth.sms.modules.rebate.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.modules.rebate.dao.InvestRecordDao;
import com.hzwealth.sms.modules.rebate.entity.InvestRecord;

@Service
@Transactional(readOnly = true)
public class InvestRecordService extends CrudService<InvestRecordDao,InvestRecord> {
	
	private final Logger logger = LoggerFactory.getLogger(InvestRecordService.class);
	@Autowired
	private InvestRecordDao investRecordDao;
	/**
	 * 保存投资记录
	 */
	@Transactional(readOnly = false)
	public void saveInvestRecord(InvestRecord investRecord) {
		logger.info("投资记录："+investRecord.toString());
		super.save(investRecord);
	}
	/**
	 * 更新指定的ID的状态为1
	 * @param investRecord
	 */
	@Transactional(readOnly = false)
	public void update(InvestRecord investRecord){
		investRecordDao.update(investRecord);
	}
	//*********************记录投资单*****************
	/**
	 * 保存投资记录
	 */
	@Transactional(readOnly = false)
	public void saveInvestRecord(Map<String,Object> recordMap) {
		InvestRecord investRecord = new InvestRecord();
		investRecord.setUserMobile(String.valueOf(recordMap.get("userMobile")));
		investRecord.setUserName(String.valueOf(recordMap.get("userName")));
		investRecord.setInvestTime(DateUtils.parseDate(recordMap.get("investTime")));
		investRecord.setInvestAmount(new BigDecimal(String.valueOf(recordMap.get("investAmount"))));
		investRecord.setDeadline(new Integer(String.valueOf(recordMap.get("deadline"))));
		investRecord.setProjectNo(String.valueOf(recordMap.get("projectNo")));
		investRecord.setProjectName(String.valueOf(recordMap.get("projectName")));
		investRecord.setOrderType(String.valueOf(recordMap.get("orderType")));
		investRecord.setResource(String.valueOf(recordMap.get("resource")));
		investRecord.setOrderId(String.valueOf(recordMap.get("orderId")));
		saveInvestRecord(investRecord);
	}
	/**
	 * 保存投资记录List
	 */
	@Transactional(readOnly = false)
	public void saveInvestRecordList(List<InvestRecord> recordList){
		for(InvestRecord record:recordList){
			saveInvestRecord(record);
		}
	}
	//*********************保存投资单结束*****************
	
	/**
	 * 查询投资列表
	 */
	public List<InvestRecord> findList(InvestRecord investRecord) {
		return super.findList(investRecord);
	}
	
	//*****************定时任务查询没有计算返佣的单子***************
	/**
	 * 查询没有生成返佣单的投资列表
	 */
	public List<InvestRecord> findStatus0List() {
		InvestRecord investRecord = new InvestRecord();
		investRecord.setStatus("0");
		return findList(investRecord);
	}
	//*****************定时任务查询没有计算返佣的单子结束***************
}
