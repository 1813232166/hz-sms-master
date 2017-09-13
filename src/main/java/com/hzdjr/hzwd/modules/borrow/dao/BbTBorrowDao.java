/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.dao;

import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;

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