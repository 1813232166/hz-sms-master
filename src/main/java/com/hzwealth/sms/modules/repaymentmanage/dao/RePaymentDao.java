package com.hzwealth.sms.modules.repaymentmanage.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;
import com.hzwealth.sms.modules.repaymentmanage.entity.InvestBillPlan;
import com.hzwealth.sms.modules.repaymentmanage.entity.LendPlan;
import com.hzwealth.sms.modules.repaymentmanage.entity.PuBiao;

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
