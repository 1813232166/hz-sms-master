package com.hzdjr.hzwd.modules.moneystatistics.dao;

import java.util.List;

import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.moneystatistics.entity.Moneystatistics;

@MyBatisDao
public interface MoneystatisticsDao {

	List<Moneystatistics> findMoneystatisticsList();

}
