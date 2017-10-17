package com.hzwealth.sms.modules.borrow.dao;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.Asset;

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
