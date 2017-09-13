/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.exitmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.exitmanage.entity.TExitData;
import com.hzdjr.hzwd.modules.exitmanage.dao.TExitDataDao;

/**
 * 退出管理-查看Service
 * @author HDG
 * @version 2017-07-25
 */
@Service
@Transactional(readOnly = true)
public class TExitDataService extends CrudService<TExitDataDao, TExitData> {

	@Autowired
	private TExitDataDao tExitDataDao;
	
	public String getQuitAmountSum(){
		return tExitDataDao.getQuitAmountSum();
	}
	public TExitData get(String id) {
		return super.get(id);
	}
	
	public List<TExitData> findList(TExitData tExitData) {
		return super.findList(tExitData);
	}
	
	public Page<TExitData> findPage(Page<TExitData> page, TExitData tExitData) {
		return super.findPage(page, tExitData);
	}
	
	@Transactional(readOnly = false)
	public void save(TExitData tExitData) {
		super.save(tExitData);
	}
	
	@Transactional(readOnly = false)
	public void delete(TExitData tExitData) {
		super.delete(tExitData);
	}
	
}