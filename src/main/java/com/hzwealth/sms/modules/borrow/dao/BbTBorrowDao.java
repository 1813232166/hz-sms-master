/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.dao;

import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;

/**
 * 标的列表DAO接口
 * @author ln
 * @version 2016-10-12
 */
@MyBatisDao
public interface BbTBorrowDao extends CrudDao<TBorrow> {

	Map<String,Object> selectBorrowCount();

	TBorrow findBorrowById(Map<String,Object> params);
	
	Map<String,Object> subjectMoney(Map<String,Object> params);
	
}