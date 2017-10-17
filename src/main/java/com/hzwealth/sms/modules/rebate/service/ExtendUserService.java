package com.hzwealth.sms.modules.rebate.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.rebate.dao.ExtendUserDao;
import com.hzwealth.sms.modules.rebate.entity.ExtendUser;
import com.hzwealth.sms.modules.rebate.enums.UserRefferLevelEnum;
import com.hzwealth.sms.modules.rebate.enums.UserRefferTypeEnum;
import com.hzwealth.sms.modules.rebate.util.RebateUtil;
import com.hzwealth.sms.modules.salesupport.entity.Rakeback;
import com.hzwealth.sms.modules.salesupport.service.RakebackService;
import com.hzwealth.sms.modules.sys.entity.Dict;

/**
 * 用户表延伸
 * @author wzb
 *
 */
@Service
@Transactional(readOnly = true)
public class ExtendUserService extends CrudService<ExtendUserDao,ExtendUser>{
	private final Logger logger = LoggerFactory.getLogger(ExtendUserService.class);
	@Autowired
	private ExtendUserDao extendUserDao;
	@Autowired
	private RakebackService rakebackService;
	
	@Transactional(readOnly = false)
	public void save(ExtendUser extendUser) {
		super.save(extendUser);
	}
	
	/**
	 * 根据条件查询出用户信息
	 * @param extendUser
	 * @return
	 */
	public ExtendUser findExtendUser(ExtendUser extendUser){
		return super.get(extendUser);
	}
	
	/**
	 * 每天晚上更新数据时，每次查询100条
	 */
	public List<ExtendUser> queryFlag0User(){
		return dao.queryFlag0User();
	}
	/**
	 * 每晚更新用户信息
	 */
//	@Transactional(readOnly = false)
//	public void updateExtendUserStatistics(){
//		while(true){
//			List<ExtendUser> userList = queryFlag0User();
//			if(userList == null || userList.isEmpty()){
//				break;
//			}
//			for(ExtendUser user:userList){
//				dealUserInfo(user);
//			}
//		}
//	}
	//更新单个用户信息
//	private void dealUserInfo(ExtendUser user){
//		//统计邀请人注册数
//		user.setRefferUserCount(extendUserDao.queryRefferRegisterCount(user));
//		//实名认证
//		user.setRefferIdcardCount(extendUserDao.queryRefferAutonymCount(user));
//		user.setRefferInvestCount(extendUserDao.queryRefferInvestCount(user));
//		
//		BigDecimal sum = user.getSumInvestAmount();
//		sum = sum.add(user.getSumAInviteInvestAmount());
//		
//		//获取返佣配置信息
//		List<Rakeback> rakebackList = rakebackService.getRakebackList(null);
//		//设置等级
//		user.setRefferLevel(RebateUtil.getUserRefferLevel(user,rakebackList));
//		user.setSyncFlag("1");
//		user.setUpdateDate(new Date());
//		extendUserDao.update(user);
//	}
	/**
	 * 清除更新标志
	 */
//	@Transactional(readOnly = false)
//	public void updateSyncFlagBeforeJob(){
//		extendUserDao.updateSyncFlagBeforeJob();
//	}
	/**
	 * 新增人员同步到延伸表中
	 */
//	@Transactional(readOnly = false)
//	public void updateNoUserBeforeJob(){
//		while(true){
//			List<ExtendUser> noUserList = extendUserDao.queryNoUserList();
//			if(noUserList == null || noUserList.isEmpty()){
//				break;
//			}
//			for(ExtendUser user : noUserList){
//				save(user);
//			}
//		}
//	}
	/**
	 * 更新一级或者二级返佣，更新投资金额
	 */
	@Transactional(readOnly = false)
	public void updateRebateSumInvestAmount(ExtendUser extendUser){
		//判断t_yx_rel_user表是否已经保存user,则增加一条记录
		if(StringUtils.isBlank(extendUser.getId())){
			logger.info("t_yx_rel_user不存在记录，需要新增一条记录");
			save(extendUser);
		}
		logger.info("更新数据："+extendUser.toString());
		extendUserDao.updateRebateSumInvestAmount(extendUser);
	}
	/**
	 * 邀请管理Service
	 * @param extendUser
	 * @return
	 */
	public List<ExtendUser> findInviteUserList(ExtendUser extendUser){
		//获取返佣配置信息,status=1启用的配置
		Map<String, Object> queryParam = Maps.newHashMap();
		queryParam.put("status", "1");
		List<Rakeback> rakebackList = rakebackService.getRakebackList(queryParam);
		Map<String,Rakeback> rakeMap = RebateUtil.getRakebackMap(rakebackList);
		Map<String,BigDecimal> rakebackConfigMap = new HashMap<String,BigDecimal>();
		//推荐人
		Rakeback referee = rakeMap.get(UserRefferTypeEnum.referee.getRefferType());
		if(referee != null){
			rakebackConfigMap.put("commonamount1", referee.getCommonamount());
			rakebackConfigMap.put("sliveramount1", referee.getSliveramount());
			rakebackConfigMap.put("goldamount1", referee.getGoldamount());
		}
		//理财师
		Rakeback planner = rakeMap.get(UserRefferTypeEnum.Financial_Planner.getRefferType());
		if(planner != null){
			rakebackConfigMap.put("commonamount2", planner.getCommonamount());
			rakebackConfigMap.put("sliveramount2", planner.getSliveramount());
			rakebackConfigMap.put("goldamount2", planner.getGoldamount());
		}
		extendUser.setRakebackConfigMap(rakebackConfigMap);
		List<ExtendUser> list = extendUserDao.findInviteUserList(extendUser);
		return list;
	}
	/**
	 * 邀请管理Service
	 * @param extendUser
	 * @return
	 */
	public Page<ExtendUser> findInviteUserPage(Page<ExtendUser> page,ExtendUser extendUser){
		extendUser.setPage(page);
		page.setList(findInviteUserList(extendUser));
		return page;
	}
	/**
	 * 获取配置好的返佣类型
	 * @return
	 */
	public List<Dict> getRakeList(){
		//获取返佣配置信息,status=1启用的配置
		Map<String, Object> queryParam = Maps.newHashMap();
		queryParam.put("status", "1");
		List<Rakeback> rakebackList = rakebackService.getRakebackList(queryParam);
		Map<String,Rakeback> rakeMap = RebateUtil.getRakebackMap(rakebackList);
		
		List<Dict> dickList = Lists.newArrayList();
		if(!rakeMap.isEmpty()){
			for(Map.Entry<String, Rakeback> rake:rakeMap.entrySet()){
				Dict d = new Dict();
				d.setValue(rake.getKey());
				d.setLabel(UserRefferTypeEnum.getRefferDesc(rake.getKey()));
				dickList.add(d);
			}
		}
		return dickList;
	}
	/**
	 * 根据推荐人，或者理财师类型，获取配置的等级
	 * @param type
	 * @return
	 */
	public Map<String,String> getRefferLevelConfig(String type){
		//获取返佣配置信息,status=1启用的配置
		Map<String, Object> queryParam = Maps.newHashMap();
		queryParam.put("status", "1");
		List<Rakeback> rakebackList = rakebackService.getRakebackList(queryParam);
		Map<String,Rakeback> rakeMap = RebateUtil.getRakebackMap(rakebackList);
		
		Map<String,String> resultMap = Maps.newConcurrentMap();
		Rakeback rake = rakeMap.get(type);
		if(rake == null){
			return resultMap;
		}
		if(rake.getCommonamount() == null){
			return resultMap;
		}
		resultMap.put(UserRefferLevelEnum.common.getLevel(),UserRefferLevelEnum.common.getDesc());
		if(rake.getSliveramount() == null){
			return resultMap;
		}
		resultMap.put(UserRefferLevelEnum.silver.getLevel(), UserRefferLevelEnum.silver.getDesc());
		if(rake.getGoldamount() == null){
			return resultMap;
		}
		resultMap.put(UserRefferLevelEnum.gold.getLevel(), UserRefferLevelEnum.gold.getDesc());
		return resultMap;
	}
}
