/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.dao;

import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.TCapitalBespeak;

/**
 * 预约记录管理DAO接口
 * @author HDG
 * @version 2017-07-05
 */
@MyBatisDao
public interface TCapitalBespeakDao extends CrudDao<TCapitalBespeak> {
	public Map<String, Object>  findBespeaking(TCapitalBespeak tCapitalBespeak);
	public Map<String, Object>  findCancelBespeak(TCapitalBespeak tCapitalBespeak);
}