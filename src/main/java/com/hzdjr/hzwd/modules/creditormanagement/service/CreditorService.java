package com.hzdjr.hzwd.modules.creditormanagement.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.creditormanagement.dao.CreditorDao;
import com.hzdjr.hzwd.modules.creditormanagement.entity.CreditorVo;
import com.hzdjr.hzwd.modules.repaybillplan.entity.TBorrowRepayBillplan;

/**
 * 债权列表Service
 * @author HDG
 * @version 2017-08-02
 */
@Service
@Transactional(readOnly = true)
public class CreditorService extends CrudService<CreditorDao, CreditorVo>{
   
	@Autowired 
	private CreditorDao creditorDao;
	
	public	CreditorVo getCreditorDeails(String id,String tinvestId){
		return creditorDao.getCreditorDeails(id,tinvestId);
	}
	public Page<CreditorVo> queryTransferByBorrowId(Page<CreditorVo> page,CreditorVo creditorVo){
		creditorVo.setPage(page);
		return page.setList(creditorDao.queryTransferByBorrowId(creditorVo));
	}
	public Page<TBorrowRepayBillplan> queryRepayBillplanByInvestId(Page<TBorrowRepayBillplan> page,TBorrowRepayBillplan tBorrowRepayBillplan){
		tBorrowRepayBillplan.setPage(page);
		return page.setList(creditorDao.queryRepayBillplanByInvestId(tBorrowRepayBillplan));
	}
	public Map<String,Object> getCreditorNum(){
		return creditorDao.getCreditorNum();
	}
	public Map<String,Object> getCreditorValueSum(){
		return creditorDao.getCreditorValueSum();
	}
}
