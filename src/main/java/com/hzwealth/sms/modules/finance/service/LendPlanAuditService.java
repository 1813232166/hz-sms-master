package com.hzwealth.sms.modules.finance.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.common.utils.UUIDUtil;
import com.hzwealth.sms.modules.finance.dao.LendPlanAuditDao;
import com.hzwealth.sms.modules.finance.entity.Finance;
import com.hzwealth.sms.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class LendPlanAuditService extends CrudService<LendPlanAuditDao, Finance> {
	
	@Autowired
	private LendPlanAuditDao lendPlanAuditDao;
	
	public List<Finance> findList(Finance finance) {
		return super.findList(finance);
	}
	
	public Page<Finance> findPage(Page<Finance> page, Finance finance) {
		return super.findPage(page, finance);
	}
	
	public Finance getLendPlanDetail(String id){
		List<Finance> list=lendPlanAuditDao.getLendPlanDetailById(id);
		return list.get(0);
	}
	@Transactional(readOnly = false)
	public int lendPlanAudit(String finance_id ,String auditRadio,String auditArea){
		String id = UUIDUtil.genUUIDString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("user_id", UserUtils.getUser().getId());
		map.put("finance_id", finance_id);
		map.put("creat_time", new Date());
		map.put("cause", auditArea);
		map.put("examine_status", auditRadio);
		return  lendPlanAuditDao.lendPlanAudit(map);
	}
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = false)
	public int updateFinanceStatus(String finance_id ,String auditRadio){
		Map<String, Object> map = new HashMap<String, Object>();
		if(auditRadio.equals("1")){//审核通过
			Date cur=new Date(System.currentTimeMillis());
			int hours = cur.getHours();
			//if(hours>=10){//每天10点后
				//map.put("status", "5");
			//}else{
				map.put("status", "4");
			//}
		}else{
			map.put("status", "3");
		}
		map.put("finance_id", finance_id);
		
		return  lendPlanAuditDao.updateFinanceStatus(map);
	}
}
