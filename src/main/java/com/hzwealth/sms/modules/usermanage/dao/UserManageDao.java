package com.hzwealth.sms.modules.usermanage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.usermanage.entity.TUser;
import com.hzwealth.sms.modules.usermanage.entity.TUserALL;

@MyBatisDao
public interface UserManageDao {

	List<TUser> getUserInfo(Map<String, Object> paramMap);

	int lockUser(String id);

	int unlockUser(String id);

	TUserALL selectUserById(String id);

	int repwd(Map<String, Object> map);

	Map<String, Object> selectUserAccountById(String id);

}
