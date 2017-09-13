/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.banner.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.banner.entity.AppBanner;

/**
 * 图片bannerDAO接口
 * @author xq
 * @version 2016-10-09
 */
@MyBatisDao
public interface AppBannerDao extends CrudDao<AppBanner> {
	
}