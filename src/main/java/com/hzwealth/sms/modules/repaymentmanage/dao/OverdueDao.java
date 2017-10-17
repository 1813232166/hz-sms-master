package com.hzwealth.sms.modules.repaymentmanage.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.BaseDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.repaymentmanage.entity.OverdueDTO;

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
