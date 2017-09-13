package com.hzdjr.hzwd.modules.rebate.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.hzdjr.hzwd.modules.rebate.entity.ExtendUser;
import com.hzdjr.hzwd.modules.rebate.entity.InvestRebate;
import com.hzdjr.hzwd.modules.rebate.entity.InvestRecord;
import com.hzdjr.hzwd.modules.rebate.enums.RakeBackTypeEnum;
import com.hzdjr.hzwd.modules.rebate.enums.UserRefferLevelEnum;
import com.hzdjr.hzwd.modules.rebate.enums.UserRefferTypeEnum;
import com.hzdjr.hzwd.modules.salesupport.entity.Rakeback;

import freemarker.log.Logger;
/**
 * 返佣工具类
 * @author wzb
 *
 */
public class RebateUtil {
	/**
	 * 推荐人和理财师放着map里，返回
	 * @param rakebackList
	 * @return
	 */
	public static Map<String,Rakeback> getRakebackMap(List<Rakeback> rakebackList){
		Map<String,Rakeback> rakeBack = Maps.newConcurrentMap();
		for(Rakeback rake : rakebackList){
			if(RakeBackTypeEnum.adviser.getRakeType().equals(rake.getType())){
				rakeBack.put(UserRefferTypeEnum.Financial_Planner.getRefferType(), rake);
			}else if(RakeBackTypeEnum.refer.getRakeType().equals(rake.getType())){
				rakeBack.put(UserRefferTypeEnum.referee.getRefferType(), rake);
			}
		}
		return rakeBack;
	}
	/**
	 * 返回邀请人等级
	 * 1：普通，2：银牌，3：金牌
	 * @param user
	 * @param rakebackList
	 * @return 
	 */
	public static String getUserRefferLevel(ExtendUser user,List<Rakeback> rakebackList){
		BigDecimal sum = user.getSumInvestAmount();
		sum = sum.add(user.getSumAInviteInvestAmount());
		Rakeback rake = getRakeback(user,rakebackList);
		
		if(rake == null){
			return null;
		}
		
		if(UserRefferTypeEnum.Financial_Planner.getRefferType().equals(user.getRefferType())){
			sum = sum.add(user.getSumBInviteInvestAmount());
		}
		String level = UserRefferLevelEnum.common.getLevel();
		if(rake.getGoldamount() != null && sum.compareTo(rake.getGoldamount()) >= 0){
			level = UserRefferLevelEnum.gold.getLevel();
		}else if(rake.getSilverOneRate() != null && sum.compareTo(rake.getSliveramount()) >= 0){
			level = UserRefferLevelEnum.silver.getLevel();
		}
		return level;
	}
	/**
	 * 判断推荐人，理财师是否配置
	 * @return
	 */
	public static Rakeback getRakeback(ExtendUser user,List<Rakeback> rakebackList){
		Map<String,Rakeback> rakeMap = getRakebackMap(rakebackList);
		Rakeback rake = rakeMap.get(user.getRefferType());
		return rake;
	}
	/**
	 * 获取当前的等级的返佣利率
	 * @param user
	 * @param rakebackList
	 * @return
	 */
	public static BigDecimal[] getCurrentLevelRate(ExtendUser user,List<Rakeback> rakebackList){
		//1：推荐人，2：理财师:总出借金额需要加上二级出借金额
		Rakeback rake = getRakeback(user, rakebackList);
		
		if(rake == null){
			return null;
		}
		
		BigDecimal[] rates = {rake.getCommonOneRate().divide(new BigDecimal(100)),rake.getCommonTwoRate().divide(new BigDecimal(100))};
		switch(UserRefferLevelEnum.getLeveEnumByLevel(getUserRefferLevel(user,rakebackList))){
		case gold:
			rates = new BigDecimal[]{rake.getGoldOneRate().divide(new BigDecimal(100)),rake.getGoldTwoRate().divide(new BigDecimal(100))};
			break;
		case silver:
			rates = new BigDecimal[]{rake.getSilverOneRate().divide(new BigDecimal(100)),rake.getSilverTwoRate().divide(new BigDecimal(100))};
		default:
			break;
		}
		return rates;
	}
	/**
	 * 计算一级返佣
	 * @return
	 */
	public static InvestRebate computerOneRebate(ExtendUser extendUser,InvestRecord investRecord,List<Rakeback> rakebackList){
		Rakeback rake =	getRakeback(extendUser,rakebackList);
		//如果没有配置对应的类型的配置，则不进行计算
		if(rake == null){
			return null;
		}
		
		InvestRebate rebate = new InvestRebate();
		//投资记录
		rebate.setInvestRecord(investRecord);
		rebate.setUserId(extendUser.getUserId());
		rebate.setUserMobile(extendUser.getUserMobile());
		//几级邀请
		rebate.setInviteType(1);
		//邀请人类型：邀请人，理财师
		rebate.setInviteUserType(extendUser.getRefferType());
		//级别
		rebate.setInviteGrade(getUserRefferLevel(extendUser,rakebackList));
		
		//结算方式（线下返佣）
		rebate.setSettlementType(rake.getJsStatus());
		//一次性返佣
		rebate.setRebateType(rake.getRefferStatus());
		
		//利率
		rebate.setRebateRate(getCurrentLevelRate(extendUser,rakebackList)[0]);
		
		//应返金额
		BigDecimal deallineRate = new BigDecimal(investRecord.getDeadline()>12?12:investRecord.getDeadline()).divide(new BigDecimal(12));
		rebate.setRebateAmount(deallineRate.multiply(rebate.getRebateRate().multiply(investRecord.getInvestAmount())).setScale(2, RoundingMode.HALF_UP));
		return rebate;
	}
	/**
	 * 计算二级返佣
	 * @param extendUser
	 * @param investRecord
	 * @param rakebackList
	 * @return
	 */
	public static InvestRebate computerTwoRebate(ExtendUser extendUser,InvestRecord investRecord,List<Rakeback> rakebackList){
		// 获取对应的配置
		Rakeback rake = getRakeback(extendUser, rakebackList);
		//如果沒有查詢對應的配置，禁用，或者沒有進行配置，返回null
		if(rake == null){
			return null;
		}
		
		InvestRebate rebate = new InvestRebate();
		//投资记录
		rebate.setInvestRecord(investRecord);
		rebate.setUserId(extendUser.getUserId());
		rebate.setUserMobile(extendUser.getUserMobile());
		//几级邀请
		rebate.setInviteType(2);
		//邀请人类型：邀请人，理财师
		rebate.setInviteUserType(extendUser.getRefferType());
		
		// 级别
		rebate.setInviteGrade(getUserRefferLevel(extendUser, rakebackList));
		
		// 结算方式（线下返佣）
		rebate.setSettlementType(rake.getJsStatus());
		// 一次性返佣
		rebate.setRebateType(rake.getRefferStatus());
		//利率
		rebate.setRebateRate(getCurrentLevelRate(extendUser,rakebackList)[1]);
		
		//应返金额
		BigDecimal deallineRate = new BigDecimal(investRecord.getDeadline()>12?12:investRecord.getDeadline()).divide(new BigDecimal(12));
		rebate.setRebateAmount(deallineRate.multiply(rebate.getRebateRate().multiply(investRecord.getInvestAmount())).setScale(2, RoundingMode.HALF_UP));
		return rebate;
	}
}
