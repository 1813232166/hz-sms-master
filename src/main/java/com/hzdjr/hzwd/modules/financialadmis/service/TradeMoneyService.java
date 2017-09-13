package com.hzdjr.hzwd.modules.financialadmis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.modules.financialadmis.dao.TradeMoneyDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.CapitalVo;
import com.hzdjr.hzwd.modules.financialadmis.entity.RechargeVo;
import com.hzdjr.hzwd.modules.financialadmis.entity.TenderVo;
import com.hzdjr.hzwd.modules.financialadmis.entity.WithdrawVo;


@Service
@Transactional(readOnly = true)
public class TradeMoneyService {
	@Autowired
	private TradeMoneyDao tradeMoneyDao;

	public Page<RechargeVo> findRechangeList(Page<RechargeVo> page,RechargeVo rechargeVo){
		rechargeVo.setPage(page);
		Page<RechargeVo> pageList = page.setList(tradeMoneyDao.findRechangeList(rechargeVo));
		return pageList;
	}
	public Page<WithdrawVo> findWithdrawList(Page<WithdrawVo> page,WithdrawVo withdrawVo){
		withdrawVo.setPage(page);
		return page.setList(tradeMoneyDao.findWithdrawList(withdrawVo));
	}
	public Page<TenderVo> findTenderList(Page<TenderVo> page,TenderVo tenderVo){
		tenderVo.setPage(page);
		return page.setList(tradeMoneyDao.findTenderList(tenderVo));
	}
	public Page<CapitalVo> findLoanList(Page<CapitalVo> page,CapitalVo capitalVo){
		capitalVo.setPage(page);
		return page.setList(tradeMoneyDao.findLoanList(capitalVo));
	}
	public Page<CapitalVo> findRepayList(Page<CapitalVo> page,CapitalVo capitalVo){
		capitalVo.setPage(page);
		return page.setList(tradeMoneyDao.findRepayList(capitalVo));
	}
	public Page<CapitalVo> findPaymentList(Page<CapitalVo> page,CapitalVo capitalVo){
		capitalVo.setPage(page);
		return page.setList(tradeMoneyDao.findPaymentList(capitalVo));
	}
	public List<RechargeVo> exportRecharge(Map<String, Object> paramMap){

		return tradeMoneyDao.exportRecharge(paramMap);
	}
	public List<WithdrawVo> exportWithdraw(Map<String, Object> map) {

		return tradeMoneyDao.findWithdrawList(map);
	}
	public List<TenderVo> exporttenderVo(Map<String, Object> map) {

		return tradeMoneyDao.findTenderList(map);
	}
	public List<CapitalVo> exportLoan(Map<String, Object> map) {

		return tradeMoneyDao.exportLoan(map);
	}
	public List<CapitalVo> exportRepay(Map<String, Object> map) {

		return tradeMoneyDao.exportRepay(map);
	}
	public List<CapitalVo> exportPayment(Map<String, Object> map) {

		return tradeMoneyDao.exportPayment(map);
	}


}
