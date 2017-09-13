package com.hzdjr.hzwd.modules.borrowlist.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrowlist.entity.AuditBorrowList;

/**
 * 普享标集合待审核列表
 * 
 * @author LiXiang
 * @version 2017-05-10
 */
@MyBatisDao
public interface AuditBorrowListDao extends CrudDao<AuditBorrowList> {

	Map<String,Object> getBorrowCounts(AuditBorrowList auditBorrowList);

	String findAuditSuggest(String borrowId);
	
	Map<String, Object> getAuditDetail(String id);
	
	List<Map<String, Object>> getSubBorrowList(String id);

	int changeBorrowListStatus(Map<String, String> map);

	int writeAuditSuggest(Map<String,Object> map);

	int deleteBorrow(String borrowId);
	
	List<AuditBorrowList> export(AuditBorrowList tBorrowList);

	int updateBorrowListAmount(String borrowId);

	int updateOpenBorrowDate(String openBorrowDate, String borrowListId);
	
	int updateOpenBorrowListDate(String openBorrowDate, String borrowListId);

	int updateBorrowListRemark(Map<String, Object> map);
}