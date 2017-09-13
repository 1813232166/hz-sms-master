/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.exitmanage.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.exitmanage.entity.TExitData;

/**
 * 退出管理-查看DAO接口
 * @author HDG
 * @version 2017-07-25
 */
@MyBatisDao
public interface TExitDataDao extends CrudDao<TExitData> {
	public String getQuitAmountSum();
}