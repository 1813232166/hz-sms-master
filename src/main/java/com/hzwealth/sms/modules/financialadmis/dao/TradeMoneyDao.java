package com.hzwealth.sms.modules.financialadmis.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.financialadmis.entity.CapitalVo;
import com.hzwealth.sms.modules.financialadmis.entity.RechargeVo;
import com.hzwealth.sms.modules.financialadmis.entity.TenderVo;
import com.hzwealth.sms.modules.financialadmis.entity.WithdrawVo;

@MyBatisDao
public interface TradeMoneyDao {
	/**
	 * 充值列表
	 * @param
	 * @return
	 */
	public List<RechargeVo> findRechangeList(RechargeVo rechargeVo);
	/**
	 * 提现列表
	 * @param withdrawVo
	 * @return
	 */
	public List<WithdrawVo> findWithdrawList(WithdrawVo withdrawVo);
	/**
	 * 投标列表
	 * @param tenderVo
	 * @return
	 */
	public List<TenderVo> findTenderList(TenderVo tenderVo);
	/**
	 * 放款列表
	 * @param refundVo
	 * @return
	 */
	public List<CapitalVo> findLoanList(CapitalVo capitalVo);
	/**
	 * 还款列表
	 * @param refundVo
	 * @return
	 */
	public List<CapitalVo> findRepayList(CapitalVo capitalVo);
	/**
	 * 回款列表
	 * @param refundVo
	 * @return
	 */
	public List<CapitalVo> findPaymentList(CapitalVo capitalVo);

	/**
	 * 导出充值列表
	 */
	public List<RechargeVo> exportRecharge(Map<String, Object> paramMap);
	/**
	 *
	 * 导出提现列表
	 *
	 */
	public List<WithdrawVo> findWithdrawList(Map<String, Object> map);

	/**
	 *
	 * 导出标的列表
	 *
	 */
	public List<TenderVo> findTenderList(Map<String, Object> map);

	/**
	 *
	 * 导出放款列表
	 */
	public List<CapitalVo> exportLoan(Map<String, Object> map);
	/**
	 *
	 * 导出还款列表
	 */
	public List<CapitalVo> exportRepay(Map<String, Object> map);
	/**
	 *
	 * 导出回款列表
	 */
	public List<CapitalVo> exportPayment(Map<String, Object> map);


}
