/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.salesupport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.salesupport.entity.ActivityInfo;
import com.hzdjr.hzwd.modules.salesupport.entity.ExcelActivityInfo;

/**
 * 活动DAO接口
 * @author zhouzhankui
 * @version 2017-06-06
 */
@MyBatisDao
public interface ActivityInfoDao extends CrudDao<ActivityInfo> {
	
	
	
	int publishHuoDong(String id); 
	
	int revokeHuoDong(String id); 
	
	int authSuccessHuoDong(String id); 
	
	int authNoFailHuoDong(String id); 
	
	
	void saveActivityInfo(ActivityInfo record);
	
	int  updateActivityInfo(ActivityInfo record);
	
	
	List<ActivityInfo> findList(ActivityInfo activityInfo);
	
	
	List<ExcelActivityInfo> excelFindList(ExcelActivityInfo excelActivityInfo);
	
	/**
	 * 根据活动名称和类型 查询活动是不错已经存在了
	 * @param activityInfo
	 * @return
	 */
	int isExtisHuoDong(ActivityInfo activityInfo); 
	
	/**
	 * 根据活动类型查询活动是否存在
	 * @param activityInfo
	 * @return
	 */
	int isExtisSystHuoDong(ActivityInfo activityInfo); 
	
	/**
	 * 活动审核之后的列表
	 * @param activityInfo
	 * @return
	 */
	List<ActivityInfo> authActivityInfoList(ActivityInfo activityInfo);
	
	
    
    List<Map<String, Object>> getCouponListByHuoDongId(@Param("mainId") String mainId);
    //查询所有活动的结束时间，ID
    List<Map<String, Object>> findAllActivity(Map<String,Object> map);
    void updateActivityStatus(List<String> list);
}