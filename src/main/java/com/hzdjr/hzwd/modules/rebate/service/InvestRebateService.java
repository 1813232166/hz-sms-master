package com.hzdjr.hzwd.modules.rebate.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.rebate.dao.InvestRebateDao;
import com.hzdjr.hzwd.modules.rebate.entity.ExtendUser;
import com.hzdjr.hzwd.modules.rebate.entity.InvestRebate;
import com.hzdjr.hzwd.modules.rebate.entity.InvestRecord;
import com.hzdjr.hzwd.modules.rebate.enums.OrderTypeEnum;
import com.hzdjr.hzwd.modules.rebate.util.RebateUtil;
import com.hzdjr.hzwd.modules.salesupport.entity.Rakeback;
import com.hzdjr.hzwd.modules.salesupport.service.RakebackService;

/**
 * 投资返佣计算service
 * @author wzb
 *
 */
@Service
public class InvestRebateService extends CrudService<InvestRebateDao,InvestRebate>{
	private final Logger logger = LoggerFactory.getLogger(InvestRebateService.class);
	@Autowired
	private ExtendUserService extendUserService;
	@Autowired
	private InvestRecordService investRecordService;
	@Autowired
	private InvestRebateDao investRebateDao;
	@Autowired
	private RakebackService rakebackService;
	
	public List<InvestRebate> findList(InvestRebate investRebate){
		return super.findList(investRebate);
	}
	/**
	 * 保存返佣记录
	 */
	@Transactional(readOnly = false)
	public void saveInvestRecord(InvestRebate investRebate) {
		logger.info("返佣记录："+investRebate.toString());
		super.save(investRebate);
	}
	/**
	 * 计算定时任务开始
	 */
	public void rebateComputerJob(){
		//查询出所有需要计算返佣的订单信息
		List<InvestRecord> Investlist = investRecordService.findStatus0List();
		int i = 0;
		for(InvestRecord record : Investlist){
			logger.info("=========当前第："+ ++i+"条记录");
			try{
				dealInvestRecord(record);
			}catch(Exception e){
				logger.info("异常。");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 处理投资记录数据，计算返佣数据
	 * @param investRecord
	 */
	@Transactional(readOnly = false)
	public boolean dealInvestRecord(InvestRecord investRecord){
		logger.info("当前计算返佣记录："+investRecord.toString());
		//查询出投资账户的信息
		ExtendUser extendUser = findExtendUser(investRecord.getUserMobile());
		if(extendUser == null){
			logger.info("投资者不存在，或者没有同步账户信息:"+investRecord.getUserMobile());
			return false;
		}
		//更新投资者信息
		extendUser.setSumInvestAmount(extendUser.getSumInvestAmount().add(investRecord.getInvestAmount()));
		extendUserService.updateRebateSumInvestAmount(extendUser);
		//投资者ID
		investRecord.setUserId(extendUser.getUserId());
		//一级推荐人
		ExtendUser refferUser1 = extendUser.getRefferUser();
		
		//出借计划返佣
		switch(OrderTypeEnum.getOrderTypeEmumByType(investRecord.getOrderType())){
		case newUserSubject:
		case singleLoan:
			//散标，新手标，不计算返佣，只更新邀请人的一级，二级邀请人的累计出借金额
			if(refferUser1 != null){
				logger.info("一级邀请者信息："+refferUser1.toString());
				//更新一级邀请数据
				refferUser1.setSumAInviteInvestAmount(refferUser1.getSumAInviteInvestAmount().add(investRecord.getInvestAmount()));
				//更新数据
				extendUserService.updateRebateSumInvestAmount(refferUser1);
				ExtendUser refferUser2 = refferUser1.getRefferUser();
				//计算二级返佣
				if(refferUser2 != null){
					logger.info("二级邀请者信息："+refferUser2.toString());
					//更新一级邀请数据
					refferUser2.setSumBInviteInvestAmount(refferUser2.getSumBInviteInvestAmount().add(investRecord.getInvestAmount()));
					//更新数据
					extendUserService.updateRebateSumInvestAmount(refferUser2);
				}
			}
			break;
		case loanPlan:
			//获取返佣配置信息,status=1启用的配置
			Map<String, Object> queryParam = Maps.newHashMap();
			queryParam.put("status", "1");
			List<Rakeback> rakebackList = rakebackService.getRakebackList(queryParam);
			//出借计划，计算返佣
			if(refferUser1 != null){
				logger.info("一级邀请者信息："+refferUser1.toString());
				InvestRebate aRebate = RebateUtil.computerOneRebate(refferUser1, investRecord, rakebackList);
				//保存返佣记录
				if(aRebate != null){
					aRebate.setInvestRecord(investRecord);
					saveInvestRecord(aRebate);
					refferUser1.setSumRebateAmount(refferUser1.getSumRebateAmount().add(aRebate.getRebateAmount()));
				}
				
				//更新一级邀请数据
				refferUser1.setSumAInviteInvestAmount(refferUser1.getSumAInviteInvestAmount().add(investRecord.getInvestAmount()));
				extendUserService.updateRebateSumInvestAmount(refferUser1);
				//二级
				ExtendUser refferUser2 = refferUser1.getRefferUser();
				//计算二级返佣
				if(refferUser2 != null){
					logger.info("二级邀请者信息："+refferUser2.toString());
					InvestRebate bRebate = RebateUtil.computerTwoRebate(refferUser2, investRecord, rakebackList);
					if(bRebate != null){
						bRebate.setInvestRecord(investRecord);
						saveInvestRecord(bRebate);
						refferUser2.setSumRebateAmount(refferUser2.getSumRebateAmount().add(bRebate.getRebateAmount()));
					}
					
					//更新一级邀请数据
					refferUser2.setSumBInviteInvestAmount(refferUser2.getSumBInviteInvestAmount().add(investRecord.getInvestAmount()));
					extendUserService.updateRebateSumInvestAmount(refferUser2);
				}
			}
		}
		investRecordService.update(investRecord);
		return true;
	}
	
	
	/**
	 * 查询出投资者的信息，如果有邀请者，也要查询出邀请者的信息
	 * @param mobile
	 * @return
	 */
	public ExtendUser findExtendUser(String mobile){
		ExtendUser extendUser = new ExtendUser();
		extendUser.setUserMobile(mobile);
		//查询投资人员信息
		ExtendUser investUser = extendUserService.findExtendUser(extendUser);
		if(investUser == null){
			logger.info("用户信息不存在："+mobile);
			return null;
		}
		//查询一级推荐人信息
		findRefferUser(investUser);
		//查询二级推荐人信息
		findRefferUser(investUser.getRefferUser());
		return investUser;
	}
	//查找推荐人或理财师，不能推荐自己
	private void findRefferUser(ExtendUser user){
		
		if(user != null && StringUtils.isNotBlank(user.getRefferee()) && !user.getUserMobile().equals(user.getRefferee()) && !user.getRefferee().equals(user.getStaffId())) {
			ExtendUser extendUser = new ExtendUser();
			//查询一级邀请人 reffer 1：推荐人，2：理财师
			if(user.getRefferee().startsWith("100")){
				user.setRefferUser(null);
				return ;
			}else if(user.getRefferee().startsWith("1")){
				extendUser.setUserMobile(user.getRefferee());
			}else if(user.getRefferee().startsWith("2")){
				extendUser.setStaffId(user.getRefferee());
			}else{
				user.setRefferUser(null);
				return ;
			}
			ExtendUser refferAUser = extendUserService.findExtendUser(extendUser);
			user.setRefferUser(refferAUser);
		}
	}
	
	//**************** 计算每月返佣的 方法*******************

			
	/**
	 * 每月的应返金额
	 * @param investRecord
	 * @return
	 */
	public BigDecimal queryRebateAmount(InvestRecord investRecord){
		return investRebateDao.queryRebateAmount(investRecord);
	}
	/**
	 * 每月返佣笔数
	 * @return
	 */
	public Integer queryRebateAccount(InvestRecord investRecord){
		return investRebateDao.queryRebateAccount(investRecord);
	}
	/**
	 * 每月返佣人数
	 * @return
	 */
	public Integer queryRebateUserAccount(InvestRecord investRecord){
		return investRebateDao.queryRebateUserAccount(investRecord);
	}
	/**
	 * 返佣明细上更新返佣记录id
	 */
	public void updateInvestRebateForRecordId(InvestRebate investRebate){
		investRebateDao.updateInvestRebateForRecordId(investRebate);
	}
	//**************** 计算每月返佣的 方法  结束*******************
}
