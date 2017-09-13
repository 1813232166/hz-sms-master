/**
 * 定义日志接口
 */
package com.hzdjr.hzwd.modules.sys.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author lvwenchao
 * @version 2016-05-11
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {
		
}
