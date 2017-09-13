package com.hzdjr.hzwd.modules.fk.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.modules.fk.dao.FkuanDAO;
import com.hzdjr.hzwd.modules.fk.entity.Borrow1;
import com.hzdjr.hzwd.modules.fk.entity.Fkuan;
import com.hzdjr.hzwd.modules.fk.service.FkuanService;

@Service
@Transactional
public class FkuanServiceImpl implements FkuanService{

	@Autowired
	private  FkuanDAO  fkuanDao;

	
	public List<Fkuan> hasFkuanList(Map<String,Object> map) {
		
		return fkuanDao.hasFkuanList(map);
		
	}
	
	
	public List<Fkuan> noFkuanList(Map<String,Object> map) {
		
		return fkuanDao.noFkuanList(map);
		
	}




	@Override
	public List<Fkuan> queryRefuseFkuanList(Map<String,Object> map) {
		
		  return  fkuanDao.queryRefuseFkuanList(map);
		
	}


	@Override
	public List<Fkuan> errorFkuanList(Map<String,Object> map) {
		
		return fkuanDao.errorFkuanList(map);
		 
	}

	//根据borrowId 得到borrowID,期数，放款时间、标的月还款额
	public Borrow1 getBorrowTimeAndAnount(String borrowId) {
		List<Borrow1> list=fkuanDao.getBorrowTimeAndAnount(borrowId);
		 Borrow1 borrow = list.get(0);
		return borrow;
	}
	
	
	//根据borrowId 更改放款时间
	public void updateBorrowTime(String borrowId,String dateStr) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("borrowId", borrowId);
		map.put("dateStr", dateStr);
		fkuanDao.updateBorrowTime(map);
	}


	@Override
	public void insertFkSeason(String uuid, String borrowId, String message,String operator) {
		fkuanDao.insertFkSeason(uuid,borrowId,message,operator);
	}


	@Override
	public void updateFinishInvestTime(String id, String dateStr) {
		// TODO Auto-generated method stub
		fkuanDao.updateFinishInvestTime(id,dateStr);
	}


	@Override
	public String finderror(String borrowId) {
		return fkuanDao.finderror(borrowId);
	}


	@Override
	public void deleteFkQuery(String userId, String requestNo, String code,String borrowCode) {
		// TODO Auto-generated method stub
		fkuanDao.deleteFkQuery(userId, requestNo, code, borrowCode);
	}


	@Override
	public void updateFkQuery(String userId, String requestNo, String code,String borrowCode) {
		// TODO Auto-generated method stub
		fkuanDao.updateFkQuery(userId, requestNo, code, borrowCode);
	}


	@Override
	public List<HashMap<String, Object>> selectFkQuery(String code) {
		// TODO Auto-generated method stub
		return fkuanDao.selectFkQuery(code);
	}


	@Override
	public void insertFkQuery(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		fkuanDao.insertFkQuery(map);
	}


	@Override
	public int isExistBorrow(String borrowCode) {
		// TODO Auto-generated method stub
		return fkuanDao.isExistBorrow(borrowCode);
	}


	@Override
	public Map<String, Object> getInvestInfo(String borrowCode) {
		// TODO Auto-generated method stub
		return fkuanDao.getInvestInfo(borrowCode);
	}
	
	
}
