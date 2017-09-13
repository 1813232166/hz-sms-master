package com.hzdjr.hzwd.modules.buss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.modules.buss.dao.BussDao;

@Service
@Transactional
public class BussServiceImpl implements BussService {

	@Autowired
	private BussDao dao;
	
	@Override
	public String updateBuss(String status1, String status2) {
		
		int i = dao.updateBussOne(status1);
		int j = dao.updateBussTwo(status2);
		if(i>0 && j>0){
			return "ok";
		}else{
			return "no";
		}
	}

	@Override
	public String findByOne() {
		return dao.findByOne();
	}

	@Override
	public String findByTwo() {
		return dao.findByTwo();
	}

}
