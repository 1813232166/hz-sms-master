/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.salesupport.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.content.entity.Article;
import com.hzwealth.sms.modules.salesupport.entity.Rakeback;
import com.hzwealth.sms.modules.salesupport.entity.Rakeback2;

/**
 * 返佣设置DAO接口
 * @author 
 * @version 
 */
@MyBatisDao
public interface RakebackDao extends CrudDao<Rakeback> {
	
	 /**
     * 查询预览（根据ID查询返佣）
     * @param id
     * @return
     */
	Rakeback getRakebackPerview(String id);

	 /**
     * 修改返佣状态
     * @param Rakeback
     * @return
     */
	int updateStatus(Map<String, Object> map);
	 /**
     * 返佣列表
     * @param map
     * @return
     */
	List<Rakeback> getRakebackList(Map<String, Object> paramMap);
	/**
     * 返佣新增
     * @param Rakeback
     * @return
     */
	int insert1(Rakeback rakeback);
	/**
     * 返佣修改
     * @param Rakeback
     * @return
     */
	int updateRakeback(Rakeback rakeback);
	int delrakeback(String id);

	String selectCount();

	String selctCount();
	
}