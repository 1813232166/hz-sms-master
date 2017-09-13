/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApply;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyDeatil;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyNew;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowContacts;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowPicVo;

/**
 * 借款申请列表DAO接口
 * @author jqs
 * @version 2016-11-29
 */
@MyBatisDao
public interface BorrowApplyDao extends CrudDao<BorrowApply> {

	List<BorrowApplyNew> findBorrowNewList(Map<String, Object> map);
	
	List<BorrowApplyNew> findBorrowByCancleList(Map<String, Object> map);
	
	List<BorrowApplyDeatil> selectBorrowApplyDeatilById(String id);

	List<BorrowContacts> selectBorrowContactsById(String id);

	List<BorrowApply> selectAllList(Map<String, Object> map);

	void updateBorrowStatus(String borrowId);

	void saveBorrowImage(List<Map<String, Object>> items);

	List<BorrowPicVo> getBorrowPic(String borrowId);

	String selectProvince(String provinceCode);

	String selectCity(String cityCode);

	String selectQu(String quCode);

	String findReasonById(String borrowId);

	List<BorrowPicVo> selectBorrowImageByFlag(String id);

	int deleteImage(@Param("picUrl")String picUrl, @Param("borrowId")String borrowId);
}