package com.hzdjr.hzwd.modules.buss.dao;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface BussDao {

	int updateBussOne(String status1);

	int updateBussTwo(String status2);

	String findByOne();

	String findByTwo();

}
