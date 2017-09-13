package com.hzdjr.hzwd.modules.creditormanagement.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.creditormanagement.entity.CreditorVo;
import com.hzdjr.hzwd.modules.repaybillplan.entity.TBorrowRepayBillplan;

/**
 * 债权列表DAO接口
 * @author HDG
 * @version 2017-08-02
 */
@MyBatisDao
public interface CreditorDao extends CrudDao<CreditorVo>{
	public CreditorVo getCreditorDeails(String id,String tinvestId);
	public List<CreditorVo> queryTransferByBorrowId(CreditorVo creditorVo);
	public List<TBorrowRepayBillplan> queryRepayBillplanByInvestId(TBorrowRepayBillplan tBorrowRepayBillplan);
	Map<String,Object> getCreditorNum();
	Map<String,Object> getCreditorValueSum();
}
