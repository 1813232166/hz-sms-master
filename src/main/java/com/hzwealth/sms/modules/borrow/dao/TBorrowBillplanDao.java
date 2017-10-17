/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;

/**
 * 还款计划DAO接口
 * @author 秦桂晶
 * @version 2016-10-11
 */
@MyBatisDao
public interface TBorrowBillplanDao extends CrudDao<TBorrowBillplan> {
	
}