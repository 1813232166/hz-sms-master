package com.hzdjr.hzwd.modules.repaymentmanage.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.BaseDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.repaymentmanage.entity.OverdueDTO;

/**
 * 逾期Dao层
 * @author steven
 * 
 */

@MyBatisDao
public interface OverdueDao extends BaseDao{

  List<OverdueDTO> listOverdueDTO(Map map);
  
  List<OverdueDTO> listHighRistOverdueDTO(Map map);
  
  void updateAdvanceStatus(String repaymentId);
  
  String getAdvanceId(String repaymentId);
}
