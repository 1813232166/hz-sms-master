/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.refferee.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.refferee.entity.TUser;

/**
 * 用户推荐DAO接口
 * @author ln
 * @version 2016-10-17
 */
@MyBatisDao
public interface TUserDao extends CrudDao<TUser> {
	
}