package com.hzdjr.hzwd.modules.borrowlist.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.modules.borrowlist.dao.AuditBorrowListDao;
import com.hzdjr.hzwd.modules.borrowlist.entity.AuditBorrowList;
import com.hzdjr.hzwd.modules.sys.utils.UserUtils;

/**
 * 普享标集合待审核列表
 * 
 * @author LiXiang
 * @version 2017-05-10
 */
@Service
@Transactional
public class AuditBorrowListService extends CrudService<AuditBorrowListDao, AuditBorrowList> {

	@Autowired
	AuditBorrowListDao auditBorrowListDao;
	
	public AuditBorrowList get(String id) {
		return super.get(id);
	}

    @Override
    public AuditBorrowList get(AuditBorrowList entity) {
        return super.get(entity);
    }

	public List<AuditBorrowList> findList(AuditBorrowList entity) {
		return super.findList(entity);
	}

	public Page<AuditBorrowList> findPage(Page<AuditBorrowList> page, AuditBorrowList entity) {
		return super.findPage(page, entity);
	}

	@Transactional(readOnly = false)
	public void save(AuditBorrowList auditBorrowList) {
		super.save(auditBorrowList);
	}

	@Transactional(readOnly = false)
	public void delete(AuditBorrowList auditBorrowList) {
		super.delete(auditBorrowList);
	}

	public Map<String, Object> getAuditDetail(String id) {
		return auditBorrowListDao.getAuditDetail(id);
	}
	
	public List<Map<String, Object>> getSubBorrowList(String id) {
		return auditBorrowListDao.getSubBorrowList(id);
	}
	
	public List<Map<String, Object>> getSubFailBorrowList(String id) {
		List<Map<String, Object>> alist=auditBorrowListDao.getSubBorrowList(id);
		List<Map<String, Object>> listclone= new ArrayList<Map<String, Object>>(alist);
		for (Map<String, Object> map : alist) {
			String borrowStatus=map.get("borrowStatus").toString();
			if("9".equals(borrowStatus)){
				listclone.remove(map);
			}
		}
		return listclone;
	}
	
	public Map<String, Object> getBorrowCounts(AuditBorrowList auditBorrowList) {
		return auditBorrowListDao.getBorrowCounts(auditBorrowList);
	}

	public int changeBorrowListStatus(String borrowListStatus, String borrowListId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("borrowListStatus", borrowListStatus);
		map.put("borrowListId", borrowListId);
		return auditBorrowListDao.changeBorrowListStatus(map);
	}
	
	public String findAuditSuggest(String borrowId) {
		// TODO Auto-generated method stub
		return auditBorrowListDao.findAuditSuggest(borrowId);
	}

	public int writeSuggest(String borrowListId, String yuanyin,
			String auditStatus) {
		
		String id = UUIDUtil.genUUIDString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("borrowListId", borrowListId);
		map.put("yuanyin", yuanyin);
		map.put("auditStatus", auditStatus);
		map.put("auditor", UserUtils.getUser().getLoginName());
		map.put("auditDate", new Date());
		map.put("auditType", "5");
		auditBorrowListDao.updateBorrowListRemark(map);
		return auditBorrowListDao.writeAuditSuggest(map);
	}

	public int deleteBorrow(String borrowId) {
		int n1=auditBorrowListDao.updateBorrowListAmount(borrowId);
		if(n1>0){
			return auditBorrowListDao.deleteBorrow(borrowId);
		}else{
			return 0;
		}
	}

	public List<AuditBorrowList> export(AuditBorrowList entity){
        List<AuditBorrowList> abList = auditBorrowListDao.export(entity);
        abList=transfer(abList);
        return abList;
    }
	
	public List<AuditBorrowList> transfer(List<AuditBorrowList> abList){
		for (AuditBorrowList auditBorrowList : abList) {
			String p=auditBorrowList.getPayMethod();
			switch (p+"") {
			case  "debx":
				auditBorrowList.setPayMethod("等额本息");
				break;

			default:
				break;
			}
			String c=auditBorrowList.getCriticalId();
			switch (c+"") {
			case  "0":
				auditBorrowList.setCriticalId("不紧急");
				break;
			case  "1":
				auditBorrowList.setCriticalId("紧急");
				break;
			default:
				break;
			}
			String s=auditBorrowList.getBorrowListStatus();
			switch (s+"") {
			case  "1":
				auditBorrowList.setBorrowListStatus("待审核");
				break;
			case  "2":
				auditBorrowList.setBorrowListStatus("审核未通过");
				break;
			case  "3":
				auditBorrowList.setBorrowListStatus("审核中");
				break;
			case  "4":
				auditBorrowList.setBorrowListStatus("审核通过");
				break;
			default:
				break;
			}
		}
		return abList;
	}

	public int updateOpenBorrowDate(String openBorrowDate, String borrowListId) {
		       auditBorrowListDao.updateOpenBorrowDate(openBorrowDate, borrowListId);
		return auditBorrowListDao.updateOpenBorrowListDate(openBorrowDate,borrowListId);
	}
}