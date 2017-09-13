package com.hzdjr.hzwd.modules.salesupport.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCoupon;

@MyBatisDao
public interface CouponDao extends CrudDao<TYxCoupon>{
    //优惠券列表
    List<TYxCoupon> findCouponManageList(TYxCoupon TYxCoupon);
    //审核列表
    List<TYxCoupon> findCouponAuditList(TYxCoupon TYxCoupon);
    //审核
    int saveAuditCoupon(TYxCoupon coupon);
    //提交审核
    int saveSubmitAuditCoupon(TYxCoupon coupon);
    //已通过审核的优惠券
    List<TYxCoupon> findPastAudit(TYxCoupon coupon);

    
    /**
     * 根据id数组 获取优惠券列表
     * @param ids
     * @return
     */
    List<Map<String, Object>> getCouponListByIds(@Param("ids") String ids);
    
    List<Map<String, Object>> getCouponListByMainId(@Param("mainId") String mainId);
    
    List<Map<String, Object>> getCouponInfolistByMainId(@Param("mainId") String mainId);
    
    
    /**
     * 
     * @param TYxCoupon
     * @return
     */
    List<TYxCoupon> authSuccessList(TYxCoupon TYxCoupon);
	List<TYxCoupon> findCoupon();
	//查询有使用期限的优惠券
	List<TYxCoupon> findCouponUseTime();
    void updateCouponStatus(List<String> idsList);
    //查询log表 失效数据
    List<Map<String, Object>> findAllPastData(Map<String, Object> map);
    Map<String, Long> findCountPastData(Map<String, Object> map);
    /**
     * 两天后失效的优惠券
     * @param map
     * @return
     */
    List<String> findAssignTimePast(Map<String, Object> map);
	
}
