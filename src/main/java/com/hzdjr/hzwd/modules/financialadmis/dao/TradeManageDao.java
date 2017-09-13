package com.hzdjr.hzwd.modules.financialadmis.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.TransactionVo;

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
