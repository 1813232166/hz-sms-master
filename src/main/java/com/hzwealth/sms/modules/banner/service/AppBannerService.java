/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.banner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.banner.dao.AppBannerDao;
import com.hzwealth.sms.modules.banner.entity.AppBanner;

/**
 * 图片bannerService
 * @author xq
 * @version 2016-10-09
 */
@Service
@Transactional(readOnly = true)
public class AppBannerService extends CrudService<AppBannerDao, AppBanner> {

	public AppBanner get(String id) {
		return super.get(id);
	}
	
	public List<AppBanner> findList(AppBanner appBanner) {
		return super.findList(appBanner);
	}
	
	public Page<AppBanner> findPage(Page<AppBanner> page, AppBanner appBanner) {
		return super.findPage(page, appBanner);
	}
	
	@Transactional(readOnly = false)
	public void save(AppBanner appBanner) {
		super.save(appBanner);
	}
	
	@Transactional(readOnly = false)
	public void delete(AppBanner appBanner) {
		super.delete(appBanner);
	}
	
}