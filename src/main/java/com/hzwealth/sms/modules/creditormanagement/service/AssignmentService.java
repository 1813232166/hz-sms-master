package com.hzwealth.sms.modules.creditormanagement.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.creditormanagement.dao.AssignmentDao;
import com.hzwealth.sms.modules.creditormanagement.entity.AssignmentVo;
import com.hzwealth.sms.modules.repaybillplan.entity.TBorrowRepayBillplan;

/**
 * 转让列表Service
 * @author HDG
 * @version 2017-07-27
 */
@Service
@Transactional(readOnly = true)
public class AssignmentService  extends CrudService<AssignmentDao, AssignmentVo>{
	@Autowired
	private AssignmentDao assignmentDao;
	
	public Map<String,Object> getTransferNumber(){
		return assignmentDao.getTransferNumber();
	}
	public Map<String,Object> getTransferPersonTime(){
		return assignmentDao.getTransferPersonTime();
	}
	public Map<String,Object> getTransferMoneySum(){
		return assignmentDao.getTransferMoneySum();
	}
	public	AssignmentVo getAssignmentDeails(String id ,String tinvestId){
		return assignmentDao.getAssignmentDeails(id,tinvestId);
	}
	public Page<AssignmentVo> queryTransferByBorrowId(Page<AssignmentVo> page,AssignmentVo assignmentVo){
		assignmentVo.setPage(page);
		return page.setList(assignmentDao.queryTransferByBorrowId(assignmentVo));
	}
	public Page<TBorrowRepayBillplan> queryRepayBillplanByInvestId(Page<TBorrowRepayBillplan> page,TBorrowRepayBillplan tBorrowRepayBillplan){
		tBorrowRepayBillplan.setPage(page);
		return page.setList(assignmentDao.queryRepayBillplanByInvestId(tBorrowRepayBillplan));
	}
}
