package com.hzdjr.hzwd.modules.finance.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.finance.entity.BorrowManagementVo;

/**
 * 散标管理DAO接口
 * @author HDG
 * @version 2017-07-31
 */
@MyBatisDao
public interface BorrowManagementDao extends CrudDao<BorrowManagementVo> {

}
