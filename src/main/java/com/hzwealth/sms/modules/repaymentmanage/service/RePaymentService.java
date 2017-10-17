package com.hzwealth.sms.modules.repaymentmanage.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;
import com.hzwealth.sms.modules.repaymentmanage.dao.RePaymentDao;
import com.hzwealth.sms.modules.repaymentmanage.entity.InvestBillPlan;
import com.hzwealth.sms.modules.repaymentmanage.entity.LendPlan;
import com.hzwealth.sms.modules.repaymentmanage.entity.PuBiao;

@Service("rePaymentService")
public class RePaymentService extends CrudService<RePaymentDao,PuBiao>{
	
	@Autowired
	private RePaymentDao rePaymentDao;

	
	public List<PuBiao> getPuBiaoList(Map<String, Object> map) {
		
        List<PuBiao> puBiaoList = rePaymentDao.getPuBiaoList(map);
        for (PuBiao puBiao : puBiaoList) {
            List<TBorrowBillplan> billplans = rePaymentDao.findBorrowBillplans(puBiao);
            if (null!=billplans && billplans.size()>0) {
                puBiao.setBorrowStatus("111");//已逾期
               /* for (TBorrowBillplan bplan : billplans) {
                    if ("4".equals(bplan.getIsAdvances())) {
                        puBiao.setBorrowStatus("112");//已垫付
                    }else if ("2".equals(bplan.getIsOffset())) {
                        puBiao.setBorrowStatus("113");//已冲抵
                    }else {
                        puBiao.setBorrowStatus("111");//已逾期
                    }
                } */
            }
        }
		return puBiaoList;
    }
	
	
	
	
	@Override
    public Page<PuBiao> findPage(Page<PuBiao> page, PuBiao entity) {
	    Page<PuBiao> page2 = super.findPage(page, entity);
	    List<PuBiao> pbList = page2.getList();
	    for (PuBiao puBiao : pbList) {
	        List<TBorrowBillplan> billplans = rePaymentDao.findBorrowBillplans(puBiao);
            if (null!=billplans && billplans.size()>0) {
                puBiao.setBorrowStatus("111");//已逾期
               /* for (TBorrowBillplan bplan : billplans) {
                    if ("4".equals(bplan.getIsAdvances())) {
                        puBiao.setBorrowStatus("112");//已垫付
                    }else if ("2".equals(bplan.getIsOffset())) {
                        puBiao.setBorrowStatus("113");//已冲抵
                    }else {
                        puBiao.setBorrowStatus("111");//已逾期
                    }
                } */
            }
        }
        return page2;
    }

    public List<LendPlan> findLendList(Map<Object, String> map) {

		List<LendPlan> findLendList = rePaymentDao.findLendList(map);

		for (LendPlan findLend : findLendList) {
			String investId = findLend.getPrimaryId(); // investId 为t_invest的主键
			InvestBillPlan queryInvest = rePaymentDao.queryInvestBillPlan(investId);// 根据innvestId查询t_invest_billplan
			// System.out.println(investId+"-------------------------------------");
				if (queryInvest != null) {
					BigDecimal count = queryInvest.getCount();
					Integer capiMonth = queryInvest.getCapiMonth();
					Date rDate = queryInvest.getrDate();
					findLend.setCount(count); // 还款金额
					findLend.setRepayCount(capiMonth); // 已经还款期数
					findLend.setLastRepayDate(rDate);    //还款日期
				}
		}

		return findLendList;
 }

	

	public LendPlan toLendDetail(String id) {
	         
		return rePaymentDao.toLendDetail(id);
		
	}




	public PuBiao getPuBiaoListById(String borrowId) {
		List<PuBiao> list = rePaymentDao.getPuBiaoListById(borrowId);
		return list.get(0);
	}




	public List<Map<String, Object>> selectTermNum(String apply_id) {
		return rePaymentDao.selectTermNum(apply_id);
	}




	public BigDecimal selectBorrowAmount() {

		return rePaymentDao.selectBorrowAmount();
		
	}

	public List<Map<String, Object>> findlendDetailInfoList(Map<String, Object> map) {
		return rePaymentDao.findlendDetailInfoList(map);
	}
	
	

}
