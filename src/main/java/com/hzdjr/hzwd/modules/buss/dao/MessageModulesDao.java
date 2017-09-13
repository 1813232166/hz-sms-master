package com.hzdjr.hzwd.modules.buss.dao;

import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.buss.entity.MessageModules;

/**
 * 短信模板DAO接口
 * @author xuchenglin
 * @version 2017-03-23
 */
@MyBatisDao
public interface MessageModulesDao extends CrudDao<MessageModules> {
	
	public Map<String ,Object>  getMessage(Map<String,Object> map);
	
}