package com.hzdjr.hzwd.modules.match.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.BaseDTO;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.common.DataHttpClient;
import com.hzdjr.hzwd.modules.match.entity.DebtTurnWarning;
import com.hzdjr.hzwd.modules.match.service.DebtTurnWarningService;
import com.hzdjr.hzwd.modules.sys.utils.UserUtils;

/**
 * 债权转让预警Controller
 * @author FYP
 * @version 2017-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/match/debtTurnWarning")
public class DebtTurnWarningController extends BaseController {

	@Autowired
	private DebtTurnWarningService debtTurnWarningService;
	
	@Autowired
	private BaseDTO dto;
	
	@ModelAttribute
	public DebtTurnWarning get(@RequestParam(required=false) String id) {
		DebtTurnWarning entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = debtTurnWarningService.get(id);
		}
		if (entity == null){
			entity = new DebtTurnWarning();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(DebtTurnWarning debtTurnWarning, HttpServletRequest request, HttpServletResponse response, Model model) {
		String checkIds=request.getParameter("checkIds");
		System.out.println(checkIds);
		if (StringUtils.isNotBlank(checkIds)) {
			model.addAttribute("checkIds", checkIds);
		}
				
		//原始资金
	    Map<String, Object> warningAssetAmount = debtTurnWarningService.findWarningAsset(debtTurnWarning);
		Page<DebtTurnWarning> page = debtTurnWarningService.findPage(new Page<DebtTurnWarning>(request, response), debtTurnWarning); 
		model.addAttribute("page", page);
		model.addAttribute("debtTurnWarning", debtTurnWarning);
		model.addAttribute("warningAssetAmount", warningAssetAmount);
		
		return "modules/match/debtTurnWarningList";
	}
	/**
	 * 
	* @Title: batchResetWeight 
	* @Description: 预警重新计算权重
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "/batchResetWeight", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody BaseDTO batchResetWeight(String checkIds ,String customWeight,String stickTop,HttpServletRequest request,
			HttpServletResponse response){
		logger.info("[batchResetWeight]-BEGIN，预警重新计算权重开始,操作人："+UserUtils.getUser().getLoginName());
		
		try {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		boolean flag=true;
		String message="SUCCESS";
			
		String 	warning_reset_url =Global.getConfig("warning_reset_url");
		String 	weight_reset_key =Global.getConfig("weight_reset_key");
		
		List<HashMap<String,Object>> weightEarlyWarningList  = new ArrayList<HashMap<String,Object>>();
		String ids []=checkIds.split(",");
		
		for (String id : ids) {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("foreign_password", weight_reset_key);
			paramMap.put("assetId", id);
			paramMap.put("customWeight", customWeight);
			paramMap.put("stickTop", stickTop);
			
			weightEarlyWarningList.add(paramMap);
		}
		
		String paramJson = JSONArray.toJSONString(weightEarlyWarningList);
		logger.info("预警权重重置调用参数===============>{}", paramJson);  
		String resString = DataHttpClient.postData(warning_reset_url, paramJson, "POST");
		logger.info("预警权重重置调用结果===============>{}", resString);  
		
		Map<String, Object> resMap = JSONObject.parseObject(resString, new TypeReference<Map<String, Object>>(){});
		
		flag = (Boolean)resMap.get("success");
		message = (String)resMap.get("message");
		dto.setRtnFlag(flag);
		dto.setMsg(message);
		} catch (Exception e) {
			dto.setRtnFlag(false);
			dto.setMsg(e.toString());
			logger.error("预警重新计算权重出现异常", e);
		}
		
		logger.info("[batchResetWeight]-END，预警重新计算权重结束");
		return dto;
	}

}