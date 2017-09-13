package com.hzdjr.hzwd.modules.creditormanagement.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.creditormanagement.entity.AssignmentVo;
import com.hzdjr.hzwd.modules.repaybillplan.entity.TBorrowRepayBillplan;

/**
 * 转让列表DAO接口
 * @author HDG
 * @version 2017-07-27
 */
@MyBatisDao
public interface AssignmentDao extends CrudDao<AssignmentVo>{
	Map<String,Object> getTransferNumber();
	Map<String,Object> getTransferPersonTime();
	Map<String,Object> getTransferMoneySum();
	public AssignmentVo getAssignmentDeails(String id  ,String  tinvestId);
	public List<AssignmentVo> queryTransferByBorrowId(AssignmentVo assignmentVo);
	public List<TBorrowRepayBillplan> queryRepayBillplanByInvestId(TBorrowRepayBillplan tBorrowRepayBillplan);
}
