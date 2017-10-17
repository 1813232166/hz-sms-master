/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.innerreffere.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.innerreffere.entity.TinnerRefferee;

/**
 * 用户推荐DAO接口
 * @author ln
 * @version 2016-10-17
 */
@MyBatisDao
public interface TinnerReffereeDao extends CrudDao<TinnerRefferee> {

	List<Map<String, Object>> getDepartmentCode();
	
	//修改推荐人
	void updateStaffId(@Param("reffereeStaffId") String reffereeStaffId,@Param("reffereerefferee") String reffereerefferee);
	
	//修改被推荐人的推荐人关系
	void updateRefferee(@Param("newRefferee") String newRefferee,@Param("oldStaffId") String oldStaffId);
	
	public int queryCouponLb(Map<String,Object> mapObject);
	
}