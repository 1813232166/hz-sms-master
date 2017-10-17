package com.hzwealth.sms.modules.salesupport.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.UUIDUtil;
import com.hzwealth.sms.modules.salesupport.dao.CouponDao;
import com.hzwealth.sms.modules.salesupport.entity.TYxCoupon;
import com.hzwealth.sms.modules.salesupport.entity.TYxCouponAuditRecord;
import com.hzwealth.sms.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service
public class CouponService extends CrudService<CouponDao, TYxCoupon>{

    @Autowired
    private CouponDao couponDao;
    @Autowired
    private CouponAuditService couponAuditService;
	@Autowired
	private TYxCouponAuditRecordService couponAuditRecordService;

    public Page<TYxCoupon> findCouponManageList(Page<TYxCoupon> page, TYxCoupon coupon) {
    	coupon.setPage(page);
    	 List<TYxCoupon> tYxCoupon=couponDao.findCouponManageList(coupon);
         if(tYxCoupon!=null){
         	 for(int i=0;i<tYxCoupon.size();i++){
              	if(tYxCoupon.get(i).getEndTime()!=null){
              	if(tYxCoupon.get(i).getEndTime().getTime()<(new Date()).getTime()){
              		tYxCoupon.get(i).setShixiao("1");;
              		
              	}
              }
              }
         }
    	return page.setList(tYxCoupon);
    }
    

    public Page<TYxCoupon> findCouponAuditList(Page<TYxCoupon> page, TYxCoupon coupon) {
        coupon.setPage(page);
        List<TYxCoupon> tYxCoupon=couponDao.findCouponAuditList(coupon);
        if(tYxCoupon!=null){
        	 for(int i=0;i<tYxCoupon.size();i++){
             	if(tYxCoupon.get(i).getEndTime()!=null){
             	if(tYxCoupon.get(i).getEndTime().getTime()<(new Date()).getTime()){
             		tYxCoupon.get(i).setShixiao("1");;
             		
             	}
             }
             }
        }
        return page.setList(tYxCoupon);
    }
    @Transactional
    public void saveUpdateCoupon(TYxCoupon coupon,Principal principal) {
        if (StringUtils.isBlank(coupon.getId())) {
            this.packCoupon(coupon);
            coupon.setCreateby(principal.getId());
            coupon.setDelflag("0");
            coupon.setStatus("0");
            couponDao.insert(coupon);
        }else {
            coupon.setUpdateby(principal.getId());
            if("1".equals(coupon.getStatusshow())){
            	coupon.setBeginTime(null);
            	coupon.setEndTime(null);
            }else if("2".equals(coupon.getStatusshow())){
            	coupon.setEffectiveDays("");
            	
            	
            }
            coupon.setUpdatedate(new Date());
            coupon.setDelflag(coupon.getDelFlag());
            couponDao.update(coupon);
        }
    }
    
    
    public List<TYxCoupon> findCouponList(TYxCoupon TYxCoupon) {
		return couponDao.findCouponManageList(TYxCoupon);
	}
    
    
    /**
     * 审核通过之后的优惠券列表
     * @param TYxCoupon
     * @return
     */
    public List<TYxCoupon> authSuccessList(TYxCoupon TYxCoupon) {
		return couponDao.authSuccessList(TYxCoupon);
	}
    

    
    /**
     * 根据id数组 获取优惠券列表
     * @param ids
     * @return
     */
    public List<Map<String, Object>> getCouponListByIds(String ids) {
		return couponDao.getCouponListByIds(ids);
	}

    
    public List<Map<String, Object>> getCouponListByMainId(String mainId) {
		return couponDao.getCouponListByMainId(mainId);
	}

    
    public List<Map<String, Object>> getCouponInfolistByMainId(String mainId) {
		return couponDao.getCouponInfolistByMainId(mainId);
	}

    public TYxCoupon packCoupon(TYxCoupon coupon){
        coupon.setId(UUIDUtil.genUUIDString());
        coupon.setStatus("0");
        coupon.setCreatedate(new Date());
        coupon.setUpdatedate(new Date());
        if (StringUtils.isBlank(coupon.getLoanChannelList())) {
            coupon.setLoanChannelList("全部");
        }
        if (StringUtils.isBlank(coupon.getLoanTermList())) {
            coupon.setLoanTermList("全部");
        }
        return coupon;
    }

   
    @Transactional
    public int saveAuditCoupon(TYxCoupon coupon,Principal principal) {
      //2审核中，3审核未通过，4已生效
        int auditRecord = 0;
        int saveAuditCoupon = couponDao.saveAuditCoupon(coupon);
        if (saveAuditCoupon>0) {
        	/*TYxCouponAuditRecord tYxCouponAuditRecordcoupon=couponAuditRecordService.getauditRecord(coupon.getId());
        	 TYxCouponAuditRecord couponAuditRecord = this.convertObject(coupon,principal);
            if( tYxCouponAuditRecordcoupon!=null){
            	couponAuditRecord.setCouponAuditRecordId(tYxCouponAuditRecordcoupon.getCouponAuditRecordId());
            }
          //更新审核记录
          auditRecord = couponAuditService.updateAuditCouponRecord(couponAuditRecord);*/
        	auditRecord = couponAuditService.saveAuditCouponRecord(this.convertObject(coupon,principal));
        }
       return auditRecord;
    }
    @Transactional
    public int saveSubmitAuditCoupon(TYxCoupon coupon,Principal principal) {
        //待审核
        int submitAudit = couponDao.saveSubmitAuditCoupon(coupon);
        //保存提交审核记录
        coupon.setAuditStatus("0");
      submitAudit = couponAuditService.saveAuditCouponRecord(this.convertObject(coupon,principal));
        return   submitAudit;      
    }
    
    public TYxCouponAuditRecord convertObject(TYxCoupon coupon,Principal principal){
        TYxCouponAuditRecord couponAuditRecord = new TYxCouponAuditRecord();
        couponAuditRecord.setAuditContent(coupon.getAuditContent());
        couponAuditRecord.setAuditFlag(coupon.getAuditStatus());
        couponAuditRecord.setAuditorId(principal.getId());//审核人
        couponAuditRecord.setMainId(coupon.getId());
        couponAuditRecord.setRelationType("coupon");//优惠券coupon;优惠券组:group;活动:activity
        couponAuditRecord.setRemarks(coupon.getAuditContent());
        couponAuditRecord.setSubmitterId(principal.getId());//提交审核人
        couponAuditRecord.setAuditTime(new Date());
        return couponAuditRecord;
    }


    public Page<TYxCoupon> findPastAudit(Page<TYxCoupon> page, TYxCoupon coupon) {
        coupon.setPage(page);
        return page.setList(couponDao.findPastAudit(coupon));
    }
    /**
     * 定时更新优惠券失效
     */
    @Transactional(readOnly = false)
    public void updstatus(){
   	 List<TYxCoupon> couponList= couponDao.findCouponUseTime();
   	 List<String> idsList= new ArrayList<String>();
            if(null!=couponList&&couponList.size()>0){
                for (TYxCoupon tYxCoupon : couponList) {
                    tYxCoupon.getStatus();
                        idsList.add(tYxCoupon.getId());
                }
            }
            if (idsList.size()>0) {
                couponDao.updateCouponStatus(idsList);
            }
        }
    //查询log表 失效数据
    public List<Map<String,Object>> findAllPastData(Map<String,Object> map){
        return couponDao.findAllPastData(map);
    }
    public Map<String,Long> findCountPastData(Map<String,Object> map){
        return couponDao.findCountPastData(map); 
    }
    public List<String> findAssignTimePast(Map<String,Object> map){
        return couponDao.findAssignTimePast(map);
    }
}
