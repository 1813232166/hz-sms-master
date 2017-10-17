package com.hzwealth.sms.modules.usermanage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hzwealth.sms.modules.usermanage.dao.UserManageDao;
import com.hzwealth.sms.modules.usermanage.entity.TUser;
import com.hzwealth.sms.modules.usermanage.entity.TUserALL;


@Service("userManageService")
public class UserManageService {

	@Autowired
	private UserManageDao userManageDao;

	public  List<TUser> getUserInfo(Map<String, Object> paramMap) {
		
		return userManageDao.getUserInfo(paramMap);
	}

	public boolean lockUser(String id) {
		return userManageDao.lockUser(id)>0;
	}

	public boolean unlockUser(String id) {
		return userManageDao.unlockUser(id)>0;
	}
	public TUserALL selectUserById(String id) {
		return userManageDao.selectUserById(id);
		
	}

	public boolean repwd(String id, String p) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("p", p);
		
		return userManageDao.repwd(map)>0;
	}

	public Map<String, Object> queryUserAccountById(String id) {
		return userManageDao.selectUserAccountById(id);
		
	}

	
	
}
