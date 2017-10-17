/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.match.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.match.entity.Asset;

/**
 * 资产队列DAO接口
 * @author HDG
 * @version 2017-06-23
 */
@MyBatisDao
public interface AssetDao extends CrudDao<Asset> {
	List<Asset> getAssetInfo(Map<String, Object> paramMap);
	Map<String,Object> getAssetByborrow(String borrowId);
	//原始资产（元）：
	Map<String,Object> getOriAsset();
	//原始待匹配资产（元）：
	Map<String,Object> getOrigWaitAsset();
	//原始剩余资产（元）
	Map<String,Object> getOrigResiAsset();
	//转让资产（元）
	Map<String,Object> getTranAsset();
	//转让待匹配资产(元)
	Map<String,Object> getTranWaitMatcAsset();
	//原始部分匹配资产(元)
	Map<String,Object> getOrigPortMatcAsset();
	//转让部分匹配资产(元)
	Map<String,Object> getTranPortMatcAsset();
	//转让剩余资产(元)
	Map<String,Object> getTranResiAsset();
	//修改权重
	int updateWeight(Map<String, Object> map);
}