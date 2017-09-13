/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.salesupport.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.salesupport.dao.TYxCouponRelationDao;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponRelation;

/**
 * 优惠券活动关联Service
 * @author zhouzhankui
 * @version 2017-06-08
 */
@Service
@Transactional(readOnly = true)
public class TYxCouponRelationService extends CrudService<TYxCouponRelationDao, TYxCouponRelation> {

	 @Autowired
	 private TYxCouponRelationDao tYxCouponRelationDao;
	
	public TYxCouponRelation get(String id) {
		return super.get(id);
	}
	
	public List<TYxCouponRelation> findList(TYxCouponRelation tYxCouponRelation) {
		return super.findList(tYxCouponRelation);
	}
	
	public Page<TYxCouponRelation> findPage(Page<TYxCouponRelation> page, TYxCouponRelation tYxCouponRelation) {
		return super.findPage(page, tYxCouponRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(TYxCouponRelation tYxCouponRelation) {
		super.save(tYxCouponRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(TYxCouponRelation tYxCouponRelation) {
		super.delete(tYxCouponRelation);
	}
	
	
	/**
	 * 保存信息
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = false)
	public void saveTYxCouponRelation(TYxCouponRelation record) {
		tYxCouponRelationDao.saveTYxCouponRelation(record);
	}
	
	/**
	 * 根据活动id 删除优惠券关联信息
	 * @param mainId
	 */
	@Transactional(readOnly = false)
	public void deleteCouponRelationByMainId(String mainId) {
		tYxCouponRelationDao.deleteCouponRelationByMainId(mainId);
		
	}
	
}