/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.gen.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author Administrator
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {
	
	public void deleteByGenTableId(String genTableId);
}
