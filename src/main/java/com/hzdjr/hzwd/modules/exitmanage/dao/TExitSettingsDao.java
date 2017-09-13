/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.exitmanage.dao;

import java.util.List;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.exitmanage.entity.TExitSettings;

/**
 * 退出管理DAO接口
 * @author HDG
 * @version 2017-07-21
 */
@MyBatisDao
public interface TExitSettingsDao extends CrudDao<TExitSettings> {
	int updateCanQuitAmount(String id,String quitAmount);
	int submitAudit(String id);
	int audit(String id,String auditRadio,String auditArea);
	int setExitMoneyShow();
	public List<TExitSettings> findList();
}