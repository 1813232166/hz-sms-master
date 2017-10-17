/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.dao;

import java.util.List;
import java.util.Map;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.operation.entity.IrcouponDetail;

/**
 * 加息券使用列表DAO接口
 * 
 * @author hdj
 * @version 2016-11-02
 */
@MyBatisDao
public interface IrcouponDetailDao extends CrudDao<IrcouponDetail> {

	// 获取加息券总数
	Map<String, Object> getIrcouponCounts(IrcouponDetail ircouponDetail);

	// 导出加息券列表
	List<IrcouponDetail> exportIrcouponFile(IrcouponDetail ircouponDetail);

}