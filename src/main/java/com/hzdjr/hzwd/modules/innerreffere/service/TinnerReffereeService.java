/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.innerreffere.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.innerreffere.dao.TinnerReffereeDao;
import com.hzdjr.hzwd.modules.innerreffere.entity.TinnerRefferee;

/**
 * 用户推荐Service
 * @author ln
 * @version 2016-10-17
 */
@Service
@Transactional(readOnly = true)
public class TinnerReffereeService extends CrudService<TinnerReffereeDao, TinnerRefferee> {
	
	@Autowired
	TinnerReffereeDao tinnerReffereeDao;
	

	public TinnerRefferee get(String id) {
		return super.get(id);
	}
	
	public List<TinnerRefferee> findList(TinnerRefferee tUser) {
		return super.findList(tUser);
	}
	
	public Page<TinnerRefferee> findPage(Page<TinnerRefferee> page, TinnerRefferee tUser) {
		return super.findPage(page, tUser);
	}
	
	@Transactional(readOnly = false)
	public void save(TinnerRefferee tUser) {
		super.save(tUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(TinnerRefferee tUser) {
		super.delete(tUser);
	}

	public List<Map<String, Object>> getDepartmentCode() {
		// TODO Auto-generated method stub
		return tinnerReffereeDao.getDepartmentCode();
	}
	
	
	//修改推荐人
	@Transactional(readOnly = false)
	public void updateStaffId(String reffereeStaffId, String reffereerefferee){
		
		tinnerReffereeDao.updateStaffId(reffereeStaffId, reffereerefferee);
		
	}
	//修改推荐人
		@Transactional(readOnly = false)
	public void updateRefferee( String reffereeStaffId,String oldStaffId){
		  tinnerReffereeDao.updateRefferee(reffereeStaffId, oldStaffId);
	}

	/**
	* 流标根据优惠券的ID进行状态更新
	*/
	public int queryCouponLb(Map<String,Object> mapObject){
		return tinnerReffereeDao.queryCouponLb(mapObject);
	}
	
}