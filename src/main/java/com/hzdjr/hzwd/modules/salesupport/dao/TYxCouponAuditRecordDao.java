/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.salesupport.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponAuditRecord;

/**
 * 活动审核日志DAO接口
 * @author zhouzhankui
 * @version 2017-06-10
 */
@MyBatisDao
public interface TYxCouponAuditRecordDao extends CrudDao<TYxCouponAuditRecord> {
	
	
	
	TYxCouponAuditRecord getauditRecord = null;

	void saveTYxCouponAuditRecord(TYxCouponAuditRecord record);

	void updateAuditCouponRecord(TYxCouponAuditRecord tYxCouponAuditRecord);

	TYxCouponAuditRecord getauditRecord(String id);
	
}