/**
 * 
 */
package com.hzwealth.sms.modules.sys.dao;

import com.hzwealth.sms.common.persistence.TreeDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author Administrator
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
