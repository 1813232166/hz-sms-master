package com.hzwealth.sms.modules.financialadmis.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.financialadmis.entity.AdvanceVo;

@MyBatisDao
public interface AdvanceManageDao {
	/**
	 * 垫付对账列表
	 * @param
	 * @return
	 */
	public List<AdvanceVo> findAdvanceList(AdvanceVo advanceVo);

	/**
	 * 导出垫付对账列表
	 */
	public List<AdvanceVo> exportAdvance(Map<String, Object> paramMap);

}
