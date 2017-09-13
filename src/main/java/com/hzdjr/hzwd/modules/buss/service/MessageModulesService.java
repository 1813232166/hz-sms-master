package com.hzdjr.hzwd.modules.buss.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.buss.dao.MessageModulesDao;
import com.hzdjr.hzwd.modules.buss.entity.MessageModules;
import com.hzdjr.hzwd.modules.content.dao.ArticleDao;

/**
 * 短信模板Service
 * @author xuchenglin
 * @version 2017-03-23
 */
@Service
@Transactional(readOnly = true)
public class MessageModulesService extends CrudService<MessageModulesDao, MessageModules> {

	
	@Autowired
	MessageModulesDao messageModulesDao;
	public MessageModules get(String id) {
		return super.get(id);
	}
	
	public List<MessageModules> findList(MessageModules messageModules) {
		return super.findList(messageModules);
	}
	
	public Page<MessageModules> findPage(Page<MessageModules> page, MessageModules messageModules) {
		return super.findPage(page, messageModules);
	}
	
	@Transactional(readOnly = false)
	public void save(MessageModules messageModules) {
		super.save(messageModules);
	}
	
	@Transactional(readOnly = false)
	public void delete(MessageModules messageModules) {
		super.delete(messageModules);
	}
	
	public Map<String,Object>  getMessage(Map<String,Object> map){
		return  messageModulesDao.getMessage(map);
	}
	
}