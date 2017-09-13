/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.salesupport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.salesupport.dao.ActivityInfoDao;
import com.hzdjr.hzwd.modules.salesupport.entity.ActivityInfo;
import com.hzdjr.hzwd.modules.salesupport.entity.ExcelActivityInfo;

/**
 * 活动Service
 * @author zhouzhankui
 * @version 2017-06-06
 */
@Service
@Transactional(readOnly = true)
public class ActivityInfoService extends CrudService<ActivityInfoDao, ActivityInfo> {

	@Autowired
	private ActivityInfoDao activityInfoDao;
	public ActivityInfo get(String id) {
		return super.get(id);
	}
	
	public List<ActivityInfo> findList(ActivityInfo activityInfo) {
		return activityInfoDao.findList(activityInfo);
	}
	
	public Page<ActivityInfo> findPage(Page<ActivityInfo> page, ActivityInfo activityInfo) {
		return super.findPage(page, activityInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(ActivityInfo activityInfo) {
		super.save(activityInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(ActivityInfo activityInfo) {
		super.delete(activityInfo);
	}
	
	
	/**
	 * 导出excel列表
	 * @param activityInfo
	 * @return
	 */
	public List<ExcelActivityInfo> excelFindList(ExcelActivityInfo activityInfo) {
		return activityInfoDao.excelFindList(activityInfo);
	}
	
	
	public 	List<ActivityInfo> authActivityInfoList(ActivityInfo activityInfo) {
		return activityInfoDao.authActivityInfoList(activityInfo);
			}
	
	
	
	@Transactional(readOnly = false)
	public boolean updateActivityInfo(ActivityInfo activityInfo) {
		return activityInfoDao.updateActivityInfo(activityInfo)>0;
	} 

	
	
	/**
	 * 根据活动名称和类型 查询活动是不错已经存在了
	 * @param activityInfo
	 * @return
	 */
	public int isExtisHuoDong(ActivityInfo activityInfo) {
		return activityInfoDao.isExtisHuoDong(activityInfo);
	} 
	
	
	/**
	 * 根据活动类型查询活动是否存在
	 * @param activityInfo
	 * @return
	 */
	public int isExtisSystHuoDong(ActivityInfo activityInfo) {
		return activityInfoDao.isExtisSystHuoDong(activityInfo);
	} 
	
	
	
	/**
	 * 1审核中
	 * 发布活动
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean publishHuoDong(String id) {
		return activityInfoDao.publishHuoDong(id)>0;
	} 
	
	/**5代表已撤销
	 * 撤销活动
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean revokeHuoDong(String id) {
		return activityInfoDao.revokeHuoDong(id)>0;
	} 
	
	/**3代表进行中
	 * 审核成功
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean authSuccessHuoDong(String id) {
		return activityInfoDao.authSuccessHuoDong(id)>0;
	} 
	
	/**
	 * 2代表审核未通过
	 * 审核不通过
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean authNoFailHuoDong(String id) {
		return activityInfoDao.authNoFailHuoDong(id)>0;
	} 
	
	
	/**
	 * 保存活动信息
	 * @param activityInfo
	 */
	@Transactional(readOnly = false)
	public void saveActivityInfo(ActivityInfo activityInfo) {
		activityInfoDao.saveActivityInfo(activityInfo);
	}
	
	/**
	 * 活动保存的优惠券
	 * @param id
	 * @return
	 */
    public List<Map<String, Object>> getCouponListByHuoDongId(String id) {
		return activityInfoDao.getCouponListByHuoDongId(id);
	}
	//查询所有活动
    public List<Map<String, Object>> findAlList(Map<String,Object> map){
        return activityInfoDao.findAllActivity(map);
    }
    @Transactional
    public void updateActivityStatus(List<String> list){
        activityInfoDao.updateActivityStatus(list);
    }
}
