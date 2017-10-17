/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.exitmanage.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.exitmanage.entity.TExitData;

/**
 * 退出管理-查看DAO接口
 * @author HDG
 * @version 2017-07-25
 */
@MyBatisDao
public interface TExitDataDao extends CrudDao<TExitData> {
	public String getQuitAmountSum();
}