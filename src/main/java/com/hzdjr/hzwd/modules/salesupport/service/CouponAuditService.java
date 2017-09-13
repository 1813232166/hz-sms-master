package com.hzdjr.hzwd.modules.salesupport.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.modules.salesupport.dao.TYxCouponAuditRecordDao;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponAuditRecord;

@Service
public class CouponAuditService extends CrudService<TYxCouponAuditRecordDao, TYxCouponAuditRecord>{

    @Autowired
    private TYxCouponAuditRecordDao couponAuditDao;

    @Transactional
    public int saveAuditCouponRecord(TYxCouponAuditRecord couponAuditRecord) {
        couponAuditRecord.setCouponAuditRecordId(UUIDUtil.genUUIDString());
        couponAuditRecord.setCreatedate(new Date());
        couponAuditRecord.setDelflag("0");
       return couponAuditDao.insert(couponAuditRecord);
    }
    @Transactional
    public int updateAuditCouponRecord(TYxCouponAuditRecord couponAuditRecord) {
        couponAuditRecord.setAuditTime(new Date());
        couponAuditRecord.setUpdatedate(new Date());
       return couponAuditDao.update(couponAuditRecord);
    }
    @Transactional
    public int deleteAuditCouponRecord(TYxCouponAuditRecord couponAuditRecord) {
       return couponAuditDao.delete(couponAuditRecord);
    }
    public TYxCouponAuditRecord getauditRecord(String id) {
        return couponAuditDao.getauditRecord(id);
    }
}
