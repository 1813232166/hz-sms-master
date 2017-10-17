package com.hzwealth.sms.modules.sys.dao;

import com.hzwealth.sms.common.persistence.TreeDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.sys.entity.Office;
/**
 * 机构DAO接口
 * @author LiDuoRui
 * @version 2016-05-11
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {

}
