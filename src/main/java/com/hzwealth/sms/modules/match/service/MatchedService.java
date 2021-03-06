package com.hzwealth.sms.modules.match.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.match.dao.CapitalDao;
import com.hzwealth.sms.modules.match.dao.MatchedDao;
import com.hzwealth.sms.modules.match.entity.Matched;

/**
 * 撮合队列Service
 * @author FYP
 * @version 2017-06-15
 */
@Service
@Transactional(readOnly = true)
public class MatchedService extends CrudService<MatchedDao, Matched> {

	
	@Autowired
	private MatchedDao matchedDao;
	
	public Matched get(String id) {
		return super.get(id);
	}
	
	public List<Matched> findList(Matched matched) {
		return super.findList(matched);
	}
	
	public Page<Matched> findPage(Page<Matched> page, Matched matched) {
		return super.findPage(page, matched);
	}

	/**
	 * 
	* @Title: findOriginalCapitalCount 
	* @Description: 原始资金匹配金额
	* @param @param matched
	* @param @return    设定文件 
	* @return 
	* @throws 
	* @author FYP
	 */
	public Map<String, Object> findCapitalCount(Matched matched) {
		return matchedDao.findCapitalCount(matched);
	}

	public Map<String, Object> findAssetCount(Matched matched) {
		return matchedDao.findAssetCount(matched);
	}
	
}