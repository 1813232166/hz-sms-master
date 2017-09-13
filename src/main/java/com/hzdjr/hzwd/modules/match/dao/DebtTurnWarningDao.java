package com.hzdjr.hzwd.modules.match.dao;

import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.match.entity.DebtTurnWarning;

/**
 * 债权转让预警DAO接口
 * @author FYP
 * @version 2017-06-19
 */
@MyBatisDao
public interface DebtTurnWarningDao extends CrudDao<DebtTurnWarning> {

	Map<String, Object> findWarningAsset(DebtTurnWarning debtTurnWarning);
	
}