package com.hzwealth.sms.modules.fk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzwealth.sms.modules.fk.entity.Borrow1;
import com.hzwealth.sms.modules.fk.entity.Fkuan;

public interface FkuanService {

	public List<Fkuan> hasFkuanList(Map<String,Object> map);

	public List<Fkuan> noFkuanList(Map<String,Object> map);

	public List<Fkuan>  queryRefuseFkuanList(Map<String,Object> map);

	public List<Fkuan> errorFkuanList(Map<String,Object> map);

	public Borrow1 getBorrowTimeAndAnount(String borrowId);
	
	public void updateBorrowTime(String borrowId,String dateStr);
	
	public void insertFkSeason(String uuid,String borrowId,String message,String operator);
	
	public void updateFinishInvestTime(String id,String dateStr);

	public String finderror(String borrowId);
	
	public void deleteFkQuery(String userId,String requestNo,String code,String borrowCode);
	
	public void updateFkQuery(String userId,String requestNo,String code,String borrowCode);
	
	public List<HashMap<String,Object>>  selectFkQuery(String userId);
	
	public void insertFkQuery(HashMap<String,Object> map);
	
	public int isExistBorrow(String borrowCode);
	
	public Map<String,Object> getInvestInfo(String borrowCode);
	
}
