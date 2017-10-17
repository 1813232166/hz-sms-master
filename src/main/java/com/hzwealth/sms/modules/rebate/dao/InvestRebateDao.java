package com.hzwealth.sms.modules.rebate.dao;

import java.math.BigDecimal;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.rebate.entity.InvestRebate;
import com.hzwealth.sms.modules.rebate.entity.InvestRecord;
/**
 * 返佣
 * @author wzb
 *
 */
@MyBatisDao
public interface InvestRebateDao extends CrudDao<InvestRebate>{
	/**
	 * 每月的应返金额
	 * @param investRecord
	 * @return
	 */
	public BigDecimal queryRebateAmount(InvestRecord investRecord);
	/**
	 * 每月返佣笔数
	 * @return
	 */
	public Integer queryRebateAccount(InvestRecord investRecord);
	/**
	 * 每月返佣人数
	 * @return
	 */
	public Integer queryRebateUserAccount(InvestRecord investRecord);
	/**
	 * 返佣明细上更新返佣记录id
	 */
	public void updateInvestRebateForRecordId(InvestRebate investRebate);
}
