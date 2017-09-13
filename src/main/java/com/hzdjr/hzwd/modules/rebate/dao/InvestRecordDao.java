package com.hzdjr.hzwd.modules.rebate.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.rebate.entity.InvestRecord;
/**
 * 投资记录
 * @author wzb
 *
 */
@MyBatisDao
public interface InvestRecordDao  extends CrudDao<InvestRecord>{
	
}
