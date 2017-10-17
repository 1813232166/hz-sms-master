package com.hzwealth.sms.modules.match.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.match.dao.StrategySetDao;
import com.hzwealth.sms.modules.match.entity.Capital;
import com.hzwealth.sms.modules.match.entity.TMatchInstall;
import com.hzwealth.sms.modules.match.entity.TMatchInstallLog;
import com.hzwealth.sms.modules.match.entity.TWeight;
import com.hzwealth.sms.modules.match.entity.TWeightLog;

@Service("strategySetService")
public class StrategySetService{
	@Autowired
	private StrategySetDao strategySetDao;

	public TWeight findTWeightByid(String id){
		return strategySetDao.findTWeightByid(id);
	}
	public TMatchInstall findTMatchInstallByid(String id){
		return strategySetDao.findTMatchInstallByid(id);
	}
	
	
	public List<TWeight> getWeightListByAsset() {
		return strategySetDao.getWeightListByAsset();
	}
	public List<TWeight> getWeightListByCapital() {
		return strategySetDao.getWeightListByCapital();
	}
	public List<TMatchInstall> gettMatchInstallList() {
		return strategySetDao.gettMatchInstallList();
	}
	public List<TWeightLog> gettWeightLogList() {
		return strategySetDao.gettWeightLogList();
	}
	public List<TMatchInstallLog> getMatchInstallLogList() {
		return strategySetDao.getMatchInstallLogList();
	}
	public boolean updateWeightStrategy(String updateWeight,String id) {
		return strategySetDao.updateWeightStrategy(updateWeight,id)>0;
	}
	public boolean updateDeadlineStrategy(String deadline,String id) {
		return strategySetDao.updateDeadlineStrategy(deadline,id)>0;
	}
	public boolean updateWhetherToOpen(String isCapital,String id) {
		return strategySetDao.updateWhetherToOpen(isCapital,id)>0;
	}

}
