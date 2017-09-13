/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.refferee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.refferee.entity.TUser;
import com.hzdjr.hzwd.modules.refferee.dao.TUserDao;

/**
 * 用户推荐Service
 * @author ln
 * @version 2016-10-17
 */
@Service
@Transactional(readOnly = true)
public class TUserService extends CrudService<TUserDao, TUser> {

	public TUser get(String id) {
		return super.get(id);
	}
	
	public List<TUser> findList(TUser tUser) {
		return super.findList(tUser);
	}
	
	public Page<TUser> findPage(Page<TUser> page, TUser tUser) {
		return super.findPage(page, tUser);
	}
	
	@Transactional(readOnly = false)
	public void save(TUser tUser) {
		super.save(tUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(TUser tUser) {
		super.delete(tUser);
	}
	
}