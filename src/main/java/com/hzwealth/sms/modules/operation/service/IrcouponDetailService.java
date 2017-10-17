/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.operation.dao.IrcouponDetailDao;
import com.hzwealth.sms.modules.operation.entity.IrcouponDetail;

/**
 * 加息券使用列表Service
 * 
 * @author hdj
 * @version 2016-11-02
 */
@Service
@Transactional(readOnly = true)
public class IrcouponDetailService extends CrudService<IrcouponDetailDao, IrcouponDetail> {

	@Autowired
	IrcouponDetailDao ircouponDetailDao;

	// 获取加息券总数
	public Map<String, Object> getIrcouponCounts(IrcouponDetail ircouponDetail) {

		return ircouponDetailDao.getIrcouponCounts(ircouponDetail);

	}

	// 导出加息券列表
	public List<IrcouponDetail> exportIrcouponFile(IrcouponDetail ircouponDetail) {

		return ircouponDetailDao.exportIrcouponFile(ircouponDetail);

	}

	public IrcouponDetail get(String id) {
		return super.get(id);
	}

	public List<IrcouponDetail> findList(IrcouponDetail ircouponDetail) {
		return super.findList(ircouponDetail);
	}

	public Page<IrcouponDetail> findPage(Page<IrcouponDetail> page, IrcouponDetail ircouponDetail) {
		return super.findPage(page, ircouponDetail);
	}

	@Transactional(readOnly = false)
	public void save(IrcouponDetail ircouponDetail) {
		super.save(ircouponDetail);
	}

	@Transactional(readOnly = false)
	public void delete(IrcouponDetail ircouponDetail) {
		super.delete(ircouponDetail);
	}

}