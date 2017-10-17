package com.hzwealth.sms.modules.rebate.dao;

import java.util.List;

import com.hzwealth.sms.common.persistence.CrudDao;
import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.rebate.entity.ExtendUser;
/**
 * 用户扩展
 * @author wzb
 *
 */
@MyBatisDao
public interface ExtendUserDao extends CrudDao<ExtendUser>{
	/**
	 * 同步更新时查询方法，只查询syncFlag=0,每次查询100条
	 * @return
	 */
	List<ExtendUser> queryFlag0User();
	
	//***************统计方法**********************//
	/**
	 * 邀请人注册数量
	 */
	int queryRefferRegisterCount(ExtendUser extendUser);
	/**
	 * 被邀请实名人数
	 * @return
	 */
	int queryRefferAutonymCount(ExtendUser extendUser);
	/**
	 * 被邀请出借人数
	 * @return
	 */
	int queryRefferInvestCount(ExtendUser extendUser);
	
	//*********************统计end**********************//
	
	
	/**
	 * 每晚更新前先执行此方法，把标志位更新为0
	 */
	//void updateSyncFlagBeforeJob();
	/**
	 * 查询用户表存在用户，而延伸表不存在的用户
	 * @return
	 */
//	List<ExtendUser> queryNoUserList();
	/**
	 * 更新个人出借金额，邀请金额，返佣金额
	 */
	void updateRebateSumInvestAmount(ExtendUser extendUser);
	/**
	 * 查询邀请管理页面数据
	 * @return
	 */
	List<ExtendUser> findInviteUserList(ExtendUser extendUser);
}
