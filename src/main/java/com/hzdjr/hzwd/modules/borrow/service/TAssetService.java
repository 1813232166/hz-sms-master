package com.hzdjr.hzwd.modules.borrow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.modules.borrow.dao.TAssetDao;
import com.hzdjr.hzwd.modules.borrow.entity.Asset;

@Service
@Transactional(readOnly = false)
public class TAssetService {

	@Autowired
	TAssetDao  tAssetDao;
	
	public void insertTransferAsset(Asset asset){
		tAssetDao.insertTransferAsset(asset);
	}
	
}
