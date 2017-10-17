/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.hzwealth.sms.modules.borrow.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.TWillBorrow;

/**
 * 借款意向表DAO接口
 * @author xuchenglin
 * @version 2017-04-25
 */
@MyBatisDao
public interface TWillBorrowDao extends CrudDao<TWillBorrow> {
	
}