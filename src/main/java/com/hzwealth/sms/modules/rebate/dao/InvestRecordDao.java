package com.hzwealth.sms.modules.rebate.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.rebate.entity.InvestRecord;
/**
 * 投资记录
 * @author wzb
 *
 */
@MyBatisDao
public interface InvestRecordDao  extends CrudDao<InvestRecord>{
	
}
