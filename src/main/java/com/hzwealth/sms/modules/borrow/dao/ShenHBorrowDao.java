/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.dao;

import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.ShenHBorrow;

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