package com.hzwealth.sms.modules.borrow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.modules.borrow.dao.TAssetDao;
import com.hzwealth.sms.modules.borrow.entity.Asset;

@Service
@Transactional(readOnly = false)
public class TAssetService {

	@Autowired
	TAssetDao  tAssetDao;
	
	public void insertTransferAsset(Asset asset){
		tAssetDao.insertTransferAsset(asset);
	}
	
}
