package com.hzwealth.sms.modules.creditormanagement.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.creditormanagement.entity.AssignmentVo;
import com.hzwealth.sms.modules.repaybillplan.entity.TBorrowRepayBillplan;

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
