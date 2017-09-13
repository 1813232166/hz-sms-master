/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.match.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.match.entity.Asset;
import com.hzdjr.hzwd.modules.match.dao.AssetDao;

/**
 * 资产队列Service
 * @author HDG
 * @version 2017-06-23
 */
@Service
@Transactional(readOnly = true)
public class AssetService extends CrudService<AssetDao, Asset> {

	@Autowired
	private AssetDao assetDao;
	public Asset get(String id) {
		return super.get(id);
	}
	public  List<Asset> getAssetInfo(Map<String, Object> paramMap) {
		
		return assetDao.getAssetInfo(paramMap);
	}
	public Map<String,Object> getOriAsset(){
		return assetDao.getOriAsset();
	}
	public Map<String,Object> getOrigWaitAsset(){
		return assetDao.getOrigWaitAsset();
	}
	public Map<String,Object> getOrigResiAsset(){
		return assetDao.getOrigResiAsset();
	}
	public Map<String,Object> getTranAsset(){
		return assetDao.getTranAsset();
	}
	public Map<String,Object> getTranWaitMatcAsset(){
		return assetDao.getTranWaitMatcAsset();
	}
	public Map<String,Object> getOrigPortMatcAsset(){
		return assetDao.getOrigPortMatcAsset();
	}
	public Map<String,Object> getTranPortMatcAsset(){
		return assetDao.getTranPortMatcAsset();
	}
	public Map<String,Object> getTranResiAsset(){
		return assetDao.getTranResiAsset();
	}
	
	
	@Transactional(readOnly = false)
	public  int  updateWeight(Map<String, Object> paramMap){
		return assetDao.updateWeight(paramMap);
	}
	public List<Asset> findList(Asset asset) {
		return super.findList(asset);
	}
	
	public Page<Asset> findPage(Page<Asset> page, Asset asset) {
		return super.findPage(page, asset);
	}
	
	@Transactional(readOnly = false)
	public void save(Asset asset) {
		super.save(asset);
	}
	
	@Transactional(readOnly = false)
	public void delete(Asset asset) {
		super.delete(asset);
	}
	
}