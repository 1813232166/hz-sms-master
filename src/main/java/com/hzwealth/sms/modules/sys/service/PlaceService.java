package com.hzwealth.sms.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.sms.modules.sys.dao.PlaceDao;

@Service
public class PlaceService {
  @Autowired
  private PlaceDao placeDao;

  public List<Map<String, String>> getProvince() {
    return placeDao.getProvince();
  }

  public List<Map<String, String>> getCity(String pid) {
    return placeDao.getCity(pid);
  }

  public List<Map<String, String>> getArea(String pid) {
    return placeDao.getArea(pid);
  }

}
