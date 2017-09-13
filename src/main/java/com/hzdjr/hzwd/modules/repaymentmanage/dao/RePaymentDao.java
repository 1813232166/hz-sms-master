package com.hzdjr.hzwd.modules.repaymentmanage.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrowBillplan;
import com.hzdjr.hzwd.modules.repaymentmanage.entity.InvestBillPlan;
import com.hzdjr.hzwd.modules.repaymentmanage.entity.LendPlan;
import com.hzdjr.hzwd.modules.repaymentmanage.entity.PuBiao;

@MyBatisDao
public interface RePaymentDao extends CrudDao<PuBiao>{

    /**
     * 标的的还款计划
     * @param list
     * @return
     */
    List<TBorrowBillplan> findBorrowBillplans(PuBiao puBiao);
    
	List<PuBiao> getPuBiaoList(Map<String, Object> map);
	
	List<LendPlan> findLendList(Map<Object, String> map);

	LendPlan toLendDetail(String id);

	List<PuBiao> getPuBiaoListById(String borrowId);

	List<Map<String, Object>> selectTermNum(String apply_id);

	InvestBillPlan queryInvestBillPlan(String investorId);

	BigDecimal selectBorrowAmount();

	List<Map<String, Object>> findlendDetailInfoList(Map<String, Object> map);
	
	
}
