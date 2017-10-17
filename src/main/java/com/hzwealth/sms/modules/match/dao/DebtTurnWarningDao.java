package com.hzwealth.sms.modules.match.dao;

import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.match.entity.DebtTurnWarning;

/**
 * 债权转让预警DAO接口
 * @author FYP
 * @version 2017-06-19
 */
@MyBatisDao
public interface DebtTurnWarningDao extends CrudDao<DebtTurnWarning> {

	Map<String, Object> findWarningAsset(DebtTurnWarning debtTurnWarning);
	
}