/**
 * 
 */
package com.hzdjr.hzwd.modules.sys.dao;

import com.hzdjr.hzwd.common.persistence.TreeDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author Administrator
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
