/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.operation.dao.ActivityIrcouponDao;
import com.hzwealth.sms.modules.operation.entity.ActivityIrcoupon;

/**
 * 加息券管理Service
 * @author hdj
 * @version 2016-11-02
 */
@Service
@Transactional(readOnly = true)
public class ActivityIrcouponService extends CrudService<ActivityIrcouponDao, ActivityIrcoupon> {

	@Autowired
	ActivityIrcouponDao activityIrcouponDao;
	
	//获取总计
	public Map<String ,Object> getIrcouponCounts(ActivityIrcoupon activityIrcoupon){
		return activityIrcouponDao.getIrcouponCounts(activityIrcoupon);
	}
		
	//修改加息券状态
	@Transactional(readOnly = false)
	public void updateStatus(ActivityIrcoupon activityIrcoupon){
		activityIrcouponDao.updateStatus(activityIrcoupon);
	}
	
	//导出列表信息
	public List<ActivityIrcoupon> exportActivityIrcoupon(ActivityIrcoupon activityIrcoupon){
		return activityIrcouponDao.exportActivityIrcoupon(activityIrcoupon);
	}

	public ActivityIrcoupon get(String id) {
		return super.get(id);
	}
	
	public List<ActivityIrcoupon> findList(ActivityIrcoupon activityIrcoupon) {
		return super.findList(activityIrcoupon);
	}
	
	public Page<ActivityIrcoupon> findPage(Page<ActivityIrcoupon> page, ActivityIrcoupon activityIrcoupon) {
		return super.findPage(page, activityIrcoupon);
	}
	
	@Transactional(readOnly = false)
	public void save(ActivityIrcoupon activityIrcoupon) {
		super.save(activityIrcoupon);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActivityIrcoupon activityIrcoupon) {
		super.delete(activityIrcoupon);
	}
	
}