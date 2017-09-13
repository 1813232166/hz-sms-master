/**
 * 
 */
package com.hzdjr.hzwd.modules.sys.dao;

import java.util.List;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.sys.entity.Dict;

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
