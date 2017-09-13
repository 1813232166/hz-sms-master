package com.hzdjr.hzwd.modules.sys.dao;

import com.hzdjr.hzwd.common.persistence.TreeDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.sys.entity.Office;
/**
 * 机构DAO接口
 * @author LiDuoRui
 * @version 2016-05-11
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {

}
