package com.hzdjr.hzwd.modules.usercount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzdjr.hzwd.modules.usercount.dao.UserInfoDao;
import com.hzdjr.hzwd.modules.usercount.entity.UserInfo;
import com.hzdjr.hzwd.modules.usercount.entity.Withdraw;

@Service("userInfoService")
public class UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;

	public List<UserInfo> getUserInfo(Map<String, String> map){
		
		return userInfoDao.getUserInfo(map);
	}

	
	public List<Withdraw> getWithdraw(Map<String, String> map) {
		return userInfoDao.getWithdraw(map);
	}
}
