package com.hzwealth.sms.modules.usercount.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.usercount.entity.UserInfo;
import com.hzwealth.sms.modules.usercount.entity.Withdraw;
@MyBatisDao
public interface UserInfoDao {

	List<UserInfo> getUserInfo(Map<String, String> map);

	List<Withdraw> getWithdraw(Map<String, String> map);

}
