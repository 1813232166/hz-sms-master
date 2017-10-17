/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.gen.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author Administrator
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {
	
	public void deleteByGenTableId(String genTableId);
}
