package com.hzdjr.hzwd.modules.usercount.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.usercount.entity.UserInfo;
import com.hzdjr.hzwd.modules.usercount.entity.Withdraw;
@MyBatisDao
public interface UserInfoDao {

	List<UserInfo> getUserInfo(Map<String, String> map);

	List<Withdraw> getWithdraw(Map<String, String> map);

}
