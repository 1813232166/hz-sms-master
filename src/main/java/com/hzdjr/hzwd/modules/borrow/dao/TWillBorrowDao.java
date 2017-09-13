/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.hzdjr.hzwd.modules.borrow.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.TWillBorrow;

/**
 * 借款意向表DAO接口
 * @author xuchenglin
 * @version 2017-04-25
 */
@MyBatisDao
public interface TWillBorrowDao extends CrudDao<TWillBorrow> {
	
}