package com.hzwealth.sms.modules.repaymentmanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzwealth.sms.modules.repaymentmanage.dao.OverdueDao;
import com.hzwealth.sms.modules.repaymentmanage.entity.OverdueDTO;

@Service("overdueService")
public class OverdueService {
  
  @Autowired
  private OverdueDao overdueDao;

  public List<OverdueDTO> listOverdueDTO(Map map){
    return overdueDao.listOverdueDTO(map);
  }
  
  public List<OverdueDTO> listHighRistOverdueDTO(Map map){
    return overdueDao.listHighRistOverdueDTO(map);
  }
  
  public boolean updateAdvanceStatus(String repaymentIdList){
    boolean flag = true;
    String repaymentId[] = repaymentIdList.split(",");
    for (String id : repaymentId){
      //判断前置条件(该垫付逾期是否是最早逾期)
      if(id.equals(overdueDao.getAdvanceId(id))){
        //更新状态，进入待批处理
        overdueDao.updateAdvanceStatus(id);
        flag &= true;
      }else{
        flag &= false;
      }
    }
    return flag;
  }
}
