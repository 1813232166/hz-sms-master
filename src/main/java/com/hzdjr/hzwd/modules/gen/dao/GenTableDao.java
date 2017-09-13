/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.gen.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.gen.entity.GenTable;

/**
 * 业务表DAO接口
 * @author Administrator
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {
	
}
