package com.hzdjr.hzwd.modules.match.web;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.modules.match.entity.OperationLog;
import com.hzdjr.hzwd.modules.match.entity.TMatchInstall;
import com.hzdjr.hzwd.modules.match.entity.TWeight;
import com.hzdjr.hzwd.modules.match.service.MatchInstallLogService;
import com.hzdjr.hzwd.modules.match.service.OperationLogService;
import com.hzdjr.hzwd.modules.match.service.StrategySetService;
import com.hzdjr.hzwd.modules.sys.entity.User;
import com.hzdjr.hzwd.modules.sys.utils.UserUtils;
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
@Controller
@RequestMapping("${adminPath}/match")
public class StrategySetController {
	@Autowired
	private StrategySetService strategySetService;
	@Autowired
	private OperationLogService operationLogService;
	@Autowired
	private MatchInstallLogService matchInstallLogService;
	/**
	 * 
	 * Description: 策略设置列表
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月19日 上午11:11:30
	 */
	@RequestMapping("/strategySetList")
	public String strategySetList(Model model,HttpServletRequest request){
		List<TWeight> weightList = strategySetService.getWeightListByAsset();//t_weight表
/*		List<TMatchInstall> tMatchInstallList = strategySetService.gettMatchInstallList();//t_match_install 表
		List<TWeightLog> tWeightLogList = strategySetService.gettWeightLogList();//t_weight_log 表
		List<TMatchInstallLog> matchInstallLogList = strategySetService.getMatchInstallLogList();//t_match_install_log  表
*/		model.addAttribute("weightInfoList",weightList);
/*		model.addAttribute("matchInstallInfoList",tMatchInstallList);
		model.addAttribute("tWeightLogList",tWeightLogList);
		model.addAttribute("matchInstallLogList",matchInstallLogList);*/
		return "modules/match/assetStrategy";
	}
	
	@RequestMapping("/capitalStrategy")
	public String capitalStrategy(Model model,HttpServletRequest request){
		List<TWeight> weightList = strategySetService.getWeightListByCapital();//t_weight表
		model.addAttribute("weightInfoList",weightList);
		return "modules/match/capitalStrategy";
	}
	@RequestMapping("/matchStrategy")
	public String matchStrategy(Model model,HttpServletRequest request){
		List<TMatchInstall> tMatchInstallList = strategySetService.gettMatchInstallList();//t_match_install 表
		model.addAttribute("matchInstallInfoList",tMatchInstallList);
		return "modules/match/matchStrategy";
	}
	/**
	 * 日志记录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/operationLog/{type}/{filed}")
	public String operationLog(@PathVariable String type,@PathVariable String filed ,Model model,HttpServletRequest request, HttpServletResponse response){		
		OperationLog operationLog = new OperationLog();
		operationLog.setFiled(filed);
		operationLog.setType(type);
		Page<OperationLog> page = operationLogService.findPage(new Page<OperationLog>(request, response), operationLog);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		model.addAttribute("filed", filed);
		return "modules/match/operationLog";
	}
	/**
	 * 日志记录
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/matchInstallLog/{type}")
	public String matchInstallLog(@PathVariable String type,Model model,HttpServletRequest request, HttpServletResponse response){		
		OperationLog operationLog = new OperationLog();
		operationLog.setType(type);
		Page<OperationLog> page = matchInstallLogService.findPage(new Page<OperationLog>(request, response), operationLog);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		return "modules/match/operationLog";
	}
	/**
	 * 
	 * Description: 修改权重
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月21日 下午1:49:27
	 */
	@ResponseBody
	@RequestMapping("/updateWeightStrategy")
	public boolean updateWeightStrategy( String updateWeight,String id,HttpServletRequest request){
		if(StringUtils.isEmpty(updateWeight)||StringUtils.isEmpty(id)){
			return false;
		}
		if(!isNumeric(updateWeight)||Integer.valueOf(updateWeight)<=0){
			return false;
		}
		TWeight t = strategySetService.findTWeightByid(id);
		t.setWeight(Integer.valueOf(updateWeight));
		operationLogService.createWeightLog(zOperationLog(t));
		return strategySetService.updateWeightStrategy(updateWeight,id);
	}
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
	}
	public OperationLog zOperationLog(TWeight t){
		OperationLog operationLog = new OperationLog();
		User user = UserUtils.getUser();
		String id = UUID.randomUUID().toString().replace("-", "");
		operationLog.setId(id);
		operationLog.setType(t.getType());
		operationLog.setCreateTime(new Date());
		operationLog.setName(user.getName());
		operationLog.setAccountNumber(user.getLoginName());
		operationLog.setFiled(t.getFiled());
		operationLog.setAmount(t.getValue());
		operationLog.setValue(t.getValue());
		operationLog.setWeight(t.getWeight());
		operationLog.setBorrowType(t.getBorrowType());
		operationLog.setDetail(t.getDescription());
		return operationLog;
	}
	/**
	 * 
	 * Description: 策略设置  修改借款期限(月)
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月21日 下午4:56:37
	 */
	@ResponseBody
	@RequestMapping("/deadlineStrategy")
	public boolean updateDeadlineStrategy( String deadline,String id,HttpServletRequest request){
		if(StringUtils.isEmpty(deadline)||StringUtils.isEmpty(id)){
			return false;
		}
		if(!isNum(deadline)){
			return false;
		}
		TMatchInstall tMatchInstall = strategySetService.findTMatchInstallByid(id);
		tMatchInstall.setAssetNper(deadline);
		matchInstallLogService.createMatchInstallLog(zOperationLog(tMatchInstall));		
		return strategySetService.updateDeadlineStrategy(deadline,id);
	}
	public boolean isNum(String deadline){
		boolean istrue = true;
		if(deadline.indexOf(",")==-1){
			return isNumeric(deadline);
		}else{
			String [] ds = deadline.split(",");
			for(String d:ds){
				if(!isNumeric(d)){
					istrue = false;
				}
			}	
			return istrue;	
		}
		
	}
	public OperationLog zOperationLog(TMatchInstall t){
		OperationLog operationLog = new OperationLog();
		User user = UserUtils.getUser();
		String id = UUID.randomUUID().toString().replace("-", "");
		operationLog.setId(id);
		operationLog.setType(t.getType());
		operationLog.setCreateTime(new Date());
		operationLog.setName(user.getName());
		operationLog.setAccountNumber(user.getLoginName());
		operationLog.setAssetNper(t.getAssetNper());
		operationLog.setCapitalNper(t.getCapitalNper());
		operationLog.setStatus(t.getWhetherToOpen());
		return operationLog;
	}
	
	
	@ResponseBody
	/**
	 * 
	 * Description: 修改 资金是否可匹配其他出借产品 状态
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月21日 下午4:00:48
	 */
	@RequestMapping("/updateWhetherToOpen")
	public boolean updateWhetherToOpen( String isCapital,String id,HttpServletRequest request){
		if(StringUtils.isEmpty(isCapital)||StringUtils.isEmpty(id)){
			return false;
		}
		TMatchInstall tMatchInstall = strategySetService.findTMatchInstallByid(id);
		tMatchInstall.setWhetherToOpen(isCapital);
		matchInstallLogService.createMatchInstallLog(zOperationLog(tMatchInstall));		
		return strategySetService.updateWhetherToOpen(isCapital,id);
	}
}
