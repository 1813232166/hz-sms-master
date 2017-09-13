package com.hzdjr.hzwd.modules.financialadmis.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.AdvanceVo;

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
