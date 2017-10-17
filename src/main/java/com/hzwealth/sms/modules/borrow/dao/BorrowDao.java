/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.borrow.entity.Borrow;
import com.hzwealth.sms.modules.borrow.entity.BorrowApplyDeatil;
import com.hzwealth.sms.modules.borrow.entity.BorrowApplyTrialQueryVo;
import com.hzwealth.sms.modules.borrow.entity.BorrowApplyTrialVo;
import com.hzwealth.sms.modules.borrow.entity.BorrowPicVo;

/**
 * 普享表申请列表DAO接口
 * @author hdj
 * @version 2016-10-13
 */
@MyBatisDao
public interface BorrowDao extends CrudDao<Borrow> {
	
	Map<String,Object> getBorrowCounts(Borrow borrow);
	
	/**
	 * 
	 * @Title: findBorrowApplyTrials   
	 * @Description: 查询线下进件标的
	 * @param paramMap
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Apr 24, 2017 5:51:15 PM
	 */
	List<BorrowApplyTrialVo> findBorrowApplyTrials(BorrowApplyTrialQueryVo queryVo);
	
	/**
	 * 
	 * @Title: countBorrowApplyTrials   
	 * @Description: 查询线下进件标的数量
	 * @param queryVo
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jul 5, 2017 9:09:58 PM
	 */
	int countBorrowApplyTrials(BorrowApplyTrialQueryVo queryVo);
	
	/**
	 * 
	 * @Title: findProductTypeList   
	 * @Description: 查询产品类型字典
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Apr 25, 2017 3:50:36 PM
	 */
	List<Map<String, String>> findProductTypeList();
	
	BorrowApplyDeatil getBorrowDetailByBorrowId(Map<String, Object> paramMap);
	
	List<Map<String, String>> findProvinceList();
	
	List<Map<String, String>> findCityList(Map<String, Object> paramMap);
	
	List<Map<String, String>> findAreaList(Map<String, Object> paramMap);
	
	List<BorrowPicVo> findBorrowPicList(Map<String, Object> paramMap);
	
	int insertSuggest(Map<String, Object> paramMap);
	
	Map<String, Object> getSuggest(Map<String, Object> paramMap);
	
	int updateSuggest(Map<String, Object> paramMap);

	int updateBorrowWithStatus(Map<String, Object> paramMap);
}