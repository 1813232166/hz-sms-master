/**
 * 定义日志接口
 */
package com.hzwealth.sms.modules.sys.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author lvwenchao
 * @version 2016-05-11
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {
		
}
