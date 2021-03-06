package com.hzwealth.sms.modules.financialadmis.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.financialadmis.entity.Overdue;

@MyBatisDao
public interface  PlatformDoorDao extends CrudDao<TBorrow>{
   List<TBorrow> exportList(Map<String,Object> map);
   Map<String, Object> findcountMount(TBorrow platformdoor);
   List<Overdue> findPayinList(Overdue overdue);
   Map<String, Object> findAdvanceCountMount(Overdue overdue);
}
