package com.hzdjr.hzwd.modules.borrow.dao;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.Asset;

@MyBatisDao
public interface TAssetDao {
	
	/**
	 * 
	 * @Title: insertTransferAsset   
	 * @Description: 新增资产
	 * @param asset
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jun 16, 2017 3:22:33 PM
	 */
	int insertTransferAsset(Asset asset);
}
