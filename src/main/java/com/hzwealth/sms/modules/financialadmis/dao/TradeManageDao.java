package com.hzwealth.sms.modules.financialadmis.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.financialadmis.entity.TransactionVo;

@MyBatisDao
public interface TradeManageDao {
	/**
	 * 交易列表
	 * @param
	 * @return
	 */
	public List<TransactionVo> findTransactionList(TransactionVo transactionVo);

	/**
	 * 导出交易列表
	 */
	public List<TransactionVo> exportTransaction(Map<String, Object> paramMap);

}
