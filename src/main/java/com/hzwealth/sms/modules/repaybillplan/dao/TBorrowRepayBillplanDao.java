/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.repaybillplan.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.repaybillplan.entity.TBorrowRepayBillplan;

/**
 * 回款管理列表DAO接口
 * @author yansy
 * @version 2016-10-12
 */
@MyBatisDao
public interface TBorrowRepayBillplanDao extends CrudDao<TBorrowRepayBillplan> {
	
}