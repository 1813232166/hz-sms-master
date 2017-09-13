package com.hzdjr.hzwd.modules.usermanage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.usermanage.entity.TUser;
import com.hzdjr.hzwd.modules.usermanage.entity.TUserALL;

@MyBatisDao
public interface UserManageDao {

	List<TUser> getUserInfo(Map<String, Object> paramMap);

	int lockUser(String id);

	int unlockUser(String id);

	TUserALL selectUserById(String id);

	int repwd(Map<String, Object> map);

	Map<String, Object> selectUserAccountById(String id);

}
