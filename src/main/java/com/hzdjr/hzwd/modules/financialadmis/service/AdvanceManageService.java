package com.hzdjr.hzwd.modules.financialadmis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.modules.financialadmis.dao.AdvanceManageDao;
import com.hzdjr.hzwd.modules.financialadmis.entity.AdvanceVo;


@Service
@Transactional(readOnly = true)
public class AdvanceManageService {
	@Autowired
	private AdvanceManageDao advanceManageDao;

	public Page<AdvanceVo> findAdvanceList(Page<AdvanceVo> page,AdvanceVo advanceVo){
		advanceVo.setPage(page);
		Page<AdvanceVo> pageList = page.setList(advanceManageDao.findAdvanceList(advanceVo));
		return pageList;
	}

	public List<AdvanceVo> exportAdvance(Map<String, Object> paramMap){

		return advanceManageDao.exportAdvance(paramMap);
	}

}
