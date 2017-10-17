/**
 * 
 */
package com.hzwealth.sms.modules.sys.dao;

import java.util.List;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author Administrator
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	public List<Dict> findByType(Dict dict);
	
}
