/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.banner.dao;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.banner.entity.AppBanner;

/**
 * 图片bannerDAO接口
 * @author xq
 * @version 2016-10-09
 */
@MyBatisDao
public interface AppBannerDao extends CrudDao<AppBanner> {
	
}