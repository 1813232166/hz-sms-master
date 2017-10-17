/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.dao;

import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.finance.entity.TCapitalBespeak;

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