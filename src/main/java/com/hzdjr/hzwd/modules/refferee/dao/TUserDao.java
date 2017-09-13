/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.refferee.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.refferee.entity.TUser;

/**
 * 用户推荐DAO接口
 * @author ln
 * @version 2016-10-17
 */
@MyBatisDao
public interface TUserDao extends CrudDao<TUser> {
	
}