package com.hzdjr.hzwd.modules.match.dao;

import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.match.entity.Matched;

/**
 * 撮合队列DAO接口
 * @author FYP
 * @version 2017-06-15
 */
@MyBatisDao
public interface MatchedDao extends CrudDao<Matched> {

	Map<String, Object> findCapitalCount(Matched matched);

	Map<String, Object> findAssetCount(Matched matched);
	
}