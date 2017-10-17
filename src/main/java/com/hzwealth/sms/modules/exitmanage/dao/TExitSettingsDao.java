/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.exitmanage.dao;

import java.util.List;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.exitmanage.entity.TExitSettings;

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