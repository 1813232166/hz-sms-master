package com.hzwealth.sms.modules.finance.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.finance.entity.BorrowManagementVo;

/**
 * 散标管理DAO接口
 * @author HDG
 * @version 2017-07-31
 */
@MyBatisDao
public interface BorrowManagementDao extends CrudDao<BorrowManagementVo> {

}
