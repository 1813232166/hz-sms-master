/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.salesupport.dao;

import org.apache.ibatis.annotations.Param;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponRelation;

/**
 * 优惠券活动关联DAO接口
 * @author zhouzhankui
 * @version 2017-06-08
 */
@MyBatisDao
public interface TYxCouponRelationDao extends CrudDao<TYxCouponRelation> {
	
	
	
	/**
	 * 保存信息
	 * @param record
	 * @return
	 */
	void saveTYxCouponRelation(TYxCouponRelation record);
	
	/**
	 * 根据活动id 删除优惠券关联信息
	 * @param mainId
	 */
	void deleteCouponRelationByMainId(@Param("mainId") String mainId);
	
	
}