/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.service;

import java.math.BigDecimal;
import java.util.HashMap;import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.mapper.JsonMapper;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.common.utils.Encodes;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.modules.borrow.dao.BbTBorrowDao;
import com.hzdjr.hzwd.modules.borrow.dao.TBorrowDao;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.borrow.util.HttpClientPostUtil;
import com.hzdjr.hzwd.modules.sys.entity.User;

/**
 * 标的列表Service
 * @author ln
 * @version 2016-10-12
 */
@Service
@Transactional(readOnly = true)
public class BbTBorrowService extends CrudService<BbTBorrowDao, TBorrow> {
	
	@Autowired
	BbTBorrowDao tBorrowDao;

	public TBorrow get(String id) {
		return super.get(id);
	}
	
	public List<TBorrow> findList(TBorrow tBorrow) {
		return super.findList(tBorrow);
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

	public Map<String, Object> selectCount() {
		Map<String,Object> borrowCount = tBorrowDao.selectBorrowCount();
		return borrowCount;
	}
	
	/**
	 * 补标操作
	 * @throws Exception 
	 */
	public String investFillBorrow(User user, TBorrow tBorrow) throws Exception {
		Map<String,String> objectHashMap = new HashMap<String,String>();
		objectHashMap.put("userId", user.getId());
		objectHashMap.put("userName", user.getName());
		objectHashMap.put("lendAmt", String.valueOf(tBorrow.getLendAmt()));//借款金额
		objectHashMap.put("applyrate", String.valueOf(tBorrow.getAnualrate()));//年利率
		objectHashMap.put("deadline", String.valueOf(tBorrow.getDeadline()));//期限
		objectHashMap.put("loannumber", String.valueOf(tBorrow.getLoannumber()));//借款编号
		objectHashMap.put("borrowcode", String.valueOf(tBorrow.getBorrowcode()));//标的编号
		objectHashMap.put("borrowId", String.valueOf(tBorrow.getBorrowId()));//普享标id
		objectHashMap.put("channel", "mgmt");//写死，渠道为补标，统计用
		
		String reqString = JsonMapper.toJsonString(objectHashMap);
		logger.info("补标远程調用前台服務器，参数："+reqString);
		String encodeReqString = Encodes.encodeBase64(reqString);

		HttpClientPostUtil httpClient = new HttpClientPostUtil();
		String respString ="1";
		//String respString = httpClient.getDataToUrl(encodeReqString, "POST");
		logger.info("补标远程調用前台服務器，返回值："+respString);
		return respString;
		
	}
	/**
	 * 查询表的状态及计算出需要补标的金额
	 */

	public Map<String,Object> findInvestAmount(Map<String,Object> params) {
		Map<String,Object> map = new HashMap<String, Object>();
		TBorrow borrow = tBorrowDao.findBorrowById(params);
		if (!"4".equals(borrow.getBorrowstatus())) {
			map.put("error", 0);
			return map;
		}
		Map<String, Object> sMoney = tBorrowDao.subjectMoney(params);
		BigDecimal sumMoney = BigDecimal.ZERO;//已投总金额
		if(sMoney!=null&&sMoney.get("sumMoney")!=null){
			 sumMoney =	 BigDecimal.valueOf(Double.valueOf(String.valueOf(sMoney.get("sumMoney"))));
		}
		BigDecimal borrowAmount = BigDecimal.valueOf(Double.valueOf(String.valueOf(borrow.getBorrowamount())));//产品总金额
		map.put("success", 1);
		map.put("amount", String.valueOf(borrowAmount.subtract(sumMoney)));
		return map;
	}
	
	
	
	
}