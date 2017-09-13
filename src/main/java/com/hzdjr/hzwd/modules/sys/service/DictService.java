/**
 * 
 */
package com.hzdjr.hzwd.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.common.utils.CacheUtils;
import com.hzdjr.hzwd.modules.sys.dao.DictDao;
import com.hzdjr.hzwd.modules.sys.entity.Dict;
import com.hzdjr.hzwd.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author Administrator
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

}
