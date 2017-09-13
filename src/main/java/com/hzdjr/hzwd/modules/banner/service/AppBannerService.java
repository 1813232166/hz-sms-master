/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.banner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.banner.entity.AppBanner;
import com.hzdjr.hzwd.modules.banner.dao.AppBannerDao;

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