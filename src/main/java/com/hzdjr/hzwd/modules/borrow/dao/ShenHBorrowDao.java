/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.dao;

import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.ShenHBorrow;

/**
 * 普享表审核未通过列表DAO接口
 * @author hdj
 * @version 2016-10-13
 */
@MyBatisDao
public interface ShenHBorrowDao extends CrudDao<ShenHBorrow> {

	Map<String,Object> getBorrowCounts(ShenHBorrow shenHBorrow);

	String findAuditSuggest(String borrowId);
	
}