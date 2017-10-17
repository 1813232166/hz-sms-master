package com.hzwealth.sms.modules.match.dao;

import java.util.List;

import com.hzwealth.sms.common.persistence.annotation.MyBatisDao;
import com.hzwealth.sms.modules.match.entity.TMatchInstall;
import com.hzwealth.sms.modules.match.entity.TMatchInstallLog;
import com.hzwealth.sms.modules.match.entity.TWeight;
import com.hzwealth.sms.modules.match.entity.TWeightLog;

/**
 * 
 *
 * Description: 策略设置
 *
 * @author huangdegui
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月19日    Administrator       1.0        1.0 Version 
 * </pre>
 */
@MyBatisDao
public interface StrategySetDao {
	/**
	 * 
	 * Description: 策略设置,资产资金列表
	 *
	 * @param 
	 * @return List<TWeight>
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月19日 下午7:40:41
	 */
	List<TWeight> getWeightList();
	
	List<TWeight> getWeightListByAsset();
	List<TWeight> getWeightListByCapital();
	
	TWeight findTWeightByid(String id);
	TMatchInstall findTMatchInstallByid(String id);
	
	/**
	 * 
	 * Description: 策略设置匹配策略列表
	 *
	 * @param 
	 * @return List<TMatchInstall>
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月19日 下午7:41:23
	 */
	List<TMatchInstall> gettMatchInstallList();
	/**
	 * 
	 * Description: 策略设置修改记录
	 *
	 * @param 
	 * @return List<TWeightLog>
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月20日 上午11:17:09
	 */
	List<TWeightLog> gettWeightLogList();
	/**
	 * 
	 * Description: 匹配策略  修改记录
	 *
	 * @param 
	 * @return List<TMatchInstallLog>
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月20日 下午3:54:05
	 */
	List<TMatchInstallLog> getMatchInstallLogList();
	/**
	 * 
	 * Description: 修改权重
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月21日 上午11:49:27
	 */
	int updateWeightStrategy(String updateWeight,String id);
	/**
	 * 
	 * Description: 修改借款期限(月)
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月21日 下午4:55:15
	 */
	int updateDeadlineStrategy(String deadline,String id);
	/**
	 * 
	 * Description: 修改 资金是否可匹配其他出借产品 状态
	 *
	 * @param 
	 * @return int
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月21日 下午6:04:45
	 */
	int updateWhetherToOpen(String isCapital,String id);
}
