package com.hzwealth.sms.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface PlaceDao {
  List<Map<String, String>> getProvince();
  List<Map<String, String>> getCity(String pid);
  List<Map<String, String>> getArea(String pid);
}
