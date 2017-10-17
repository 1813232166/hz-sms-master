package com.hzwealth.sms.modules.buss.dao;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface BussDao {

	int updateBussOne(String status1);

	int updateBussTwo(String status2);

	String findByOne();

	String findByTwo();

}
