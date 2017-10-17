package com.hzwealth.sms.modules.financialadmis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.modules.financialadmis.dao.TradeManageDao;
import com.hzwealth.sms.modules.financialadmis.entity.TransactionVo;


@Service
@Transactional(readOnly = true)
public class TradeManageService {
	@Autowired
	private TradeManageDao tradeManageDao;

	public Page<TransactionVo> findTransactionList(Page<TransactionVo> page,TransactionVo transactionVo){
		transactionVo.setPage(page);
		Page<TransactionVo> pageList = page.setList(tradeManageDao.findTransactionList(transactionVo));
		return pageList;
	}

	public List<TransactionVo> exportTransaction(Map<String, Object> paramMap){

		return tradeManageDao.exportTransaction(paramMap);
	}

}
