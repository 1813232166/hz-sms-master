/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.salesupport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.salesupport.dao.TYxCouponAuditRecordDao;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponAuditRecord;

/**
 * 活动审核日志Service
 * @author zhouzhankui
 * @version 2017-06-10
 */
@Service
@Transactional(readOnly = true)
public class TYxCouponAuditRecordService extends CrudService<TYxCouponAuditRecordDao, TYxCouponAuditRecord> {

	@Autowired
	private TYxCouponAuditRecordDao couponAuditRecordDao;
	
	public TYxCouponAuditRecord get(String id) {
		return super.get(id);
	}
	
	public List<TYxCouponAuditRecord> findList(TYxCouponAuditRecord tYxCouponAuditRecord) {
		return super.findList(tYxCouponAuditRecord);
	}
	
	public Page<TYxCouponAuditRecord> findPage(Page<TYxCouponAuditRecord> page, TYxCouponAuditRecord tYxCouponAuditRecord) {
		return super.findPage(page, tYxCouponAuditRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(TYxCouponAuditRecord tYxCouponAuditRecord) {
		super.save(tYxCouponAuditRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(TYxCouponAuditRecord tYxCouponAuditRecord) {
		super.delete(tYxCouponAuditRecord);
	}
	
	
	@Transactional(readOnly = false)
	public void saveTYxCouponAuditRecord(TYxCouponAuditRecord tYxCouponAuditRecord) {
		couponAuditRecordDao.saveTYxCouponAuditRecord(tYxCouponAuditRecord);
	}

	public void updateAuditCouponRecord(TYxCouponAuditRecord tYxCouponAuditRecord) {
		// TODO Auto-generated method stub
		couponAuditRecordDao.updateAuditCouponRecord(tYxCouponAuditRecord);
		
	}

	public TYxCouponAuditRecord getauditRecord(String id) {
		// TODO Auto-generated method stub
		return couponAuditRecordDao.getauditRecord(id);
	}
	
	
}