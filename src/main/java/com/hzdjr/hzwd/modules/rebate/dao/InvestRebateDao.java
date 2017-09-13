package com.hzdjr.hzwd.modules.rebate.dao;

import java.math.BigDecimal;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.rebate.entity.InvestRebate;
import com.hzdjr.hzwd.modules.rebate.entity.InvestRecord;
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
