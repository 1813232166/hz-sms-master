package com.hzdjr.hzwd.modules.financialadmis.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.financialadmis.entity.Overdue;

@MyBatisDao
public interface  PlatformDoorDao extends CrudDao<TBorrow>{
   List<TBorrow> exportList(Map<String,Object> map);
   Map<String, Object> findcountMount(TBorrow platformdoor);
   List<Overdue> findPayinList(Overdue overdue);
   Map<String, Object> findAdvanceCountMount(Overdue overdue);
}
