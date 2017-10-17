/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.service;

import java.math.BigDecimal;
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
import com.hzwealth.sms.modules.borrow.dao.TBorrowDao;
import com.hzwealth.sms.modules.borrow.entity.RepayPlanVo;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.invest.entity.Invest;
import com.hzwealth.sms.modules.sys.utils.UserUtils;

/**
 * 标的列表Service
 * @author ln
 * @version 2016-10-12
 */
@Service
@Transactional
public class TBorrowService extends CrudService<TBorrowDao, TBorrow> {
	
	@Autowired
	TBorrowDao tBorrowDao;

	public TBorrow get(String id) {
		return super.get(id);
	}
	
	public List<TBorrow> findList(TBorrow tBorrow) {
		return super.findList(tBorrow);
	}
	
	public TBorrow get(TBorrow tBorrow) {
		tBorrow = tBorrowDao.get(tBorrow);
		return tBorrow;
	}
	
	public TBorrow getBorrowByUserId(String userId) {
		return  tBorrowDao.get(userId);
	}
	
	public Page<TBorrow> findPage(Page<TBorrow> page, TBorrow tBorrow) {
		return super.findPage(page, tBorrow);
	}
	
	@Transactional(readOnly = false)
	public void save(TBorrow tBorrow) {
		super.save(tBorrow);
	}
	
	@Transactional(readOnly = false)
	public void delete(TBorrow tBorrow) {
		super.delete(tBorrow);
	}

	public Map<String, Object> selectCount(TBorrow tBorrow) {
		Map<String,Object> borrowCount = tBorrowDao.selectBorrowCount(tBorrow);
		return borrowCount;
	}

	public Page<TBorrow> findNewPage(Page<TBorrow> page, TBorrow tBorrow) {
		tBorrow.setPage(page);
		page.setList(tBorrowDao.findNewList(tBorrow));
		return page;
	}

	public int changeBorrowstatus(String borrowstatus, String borrowId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("borrowstatus", borrowstatus);
		map.put("borrowId", borrowId);
		return tBorrowDao.changeBorrowstatus(map);
	}
	
	public Map<String,Object> findBorrowAuditStatus(String borrowId) {
		return tBorrowDao.findBorrowAuditStatus(borrowId);
	}
	
	public void updateBorrowOpenBorrowDate(String openborrowdate,String borrowId) {
		tBorrowDao.updateBorrowOpenBorrowDate(openborrowdate,borrowId);
	}

	public BigDecimal findcountMount(TBorrow tBorrow) {
		// TODO Auto-generated method stub
		return tBorrowDao.findcountMount(tBorrow);
	}
	

	public Page<Invest> getInvRecByBid(Page<Invest> page, Invest inv) {
		Page<Invest> setList =null;
		try {
			inv.setPage(page);
			setList = page.setList(tBorrowDao.getInvRecByBid(inv));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查询出借记录异常");
		}
		return setList;
	}
	
	public List<TBorrow> getLoanByBorrowCode(List<String> list){
		return tBorrowDao.getLoanByBorrowCode(list);
	}
	public List<TBorrow> getLoanByBorrowCodeNew(List<String> list){
		return tBorrowDao.getLoanByBorrowCodeNew(list);
	}
	

	public Page<RepayPlanVo> queryPaymentDetails(Page<RepayPlanVo> page, RepayPlanVo rpv) {
		rpv.setPage(page);
		Page<RepayPlanVo> rpvsPage = page.setList(tBorrowDao.queryPaymentDetails(rpv));
		return rpvsPage;
	}
	/**
	 * 发布标的
	 * @param borrows
	 */
	public void releaseBorrow(List<TBorrow> borrows) {
		for (TBorrow tBorrow : borrows) {
			tBorrowDao.releaseBorrow(tBorrow);
		}
	}
	
	/**
	 * 发布标的
	 * @param borrows
	 */
	public void releaseBorrow(TBorrow tBorrow) {
			tBorrowDao.releaseBorrow(tBorrow);
	}
	
	
	/**
	 * 审核信息
	 * @param borrows
	 */
	public void writeSuggest(String borrowId, String yuanyin, String auditStatus) {
		String id = UUIDUtil.genUUIDString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("borrowId", borrowId);
		map.put("yuanyin", yuanyin);
		map.put("auditStatus", auditStatus);
		map.put("auditor", UserUtils.getUser().getLoginName());
		map.put("auditDate", new Date());
		map.put("auditType", "uncheck");
		tBorrowDao.writeSuggest(map);
	}

	public void deleteSuggestByBorrowId(String borrowId) {
		// TODO Auto-generated method stub
		tBorrowDao.deleteSuggestByBorrowId(borrowId);
	}

	public List<TBorrow> findExcelList(TBorrow params) {
		// TODO Auto-generated method stub
		return tBorrowDao.findExcelList(params);
	}
	
	public List<TBorrow> findBorrowByJob(){
		return tBorrowDao.findBorrowByJob();
	}
	
	public HashMap<String,Object> getBankCardByUserId(String userId){
		return tBorrowDao.getBankCardByUserId(userId);
	}
	
	public TBorrow  getBorrowByCode(String borrowCode){
		return tBorrowDao.getBorrowByCode(borrowCode);
	}
	
	public TBorrow  getBorrowById(String borrowId){
		return tBorrowDao.getBorrowById(borrowId);
	}
	
	
	public void  updateLbIng(String borrowCode){
		tBorrowDao.updateLbIng(borrowCode);
	}
	
	public int getBorrowPay(String borrowCode){
		return  tBorrowDao.getBorrowPay(borrowCode);
	}
	
}