package com.hzwealth.sms.modules.match.web;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.BaseDTO;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.common.DataHttpClient;
import com.hzwealth.sms.modules.match.entity.Capital;
import com.hzwealth.sms.modules.match.service.CapitalService;
import com.hzwealth.sms.modules.sys.utils.UserUtils;

/**
 * 资金列表Controller
 * @author FYP
 * @version 2017-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/match/capital")
public class CapitalController extends BaseController {

	@Autowired
	private CapitalService capitalService;
	
	@Autowired
	private BaseDTO dto;
	
	
	@ModelAttribute
	public Capital get(@RequestParam(required=false) String id) {
		Capital entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = capitalService.get(id);
		}
		if (entity == null){
			entity = new Capital();
		}
		return entity;
	}
	
//	@RequiresPermissions("match:capital:view")
	@RequestMapping(value = {"list", ""})
	public String list(Capital capital, HttpServletRequest request, HttpServletResponse response, Model model) {
		//原始资金
	    Map<String, Object> originalCount = capitalService.findOriginalCount(capital);
	    //回款资金
	    Map<String, Object> returnCount = capitalService.findReturnCount(capital);
	    
		Page<Capital> page = capitalService.findPage(new Page<Capital>(request, response), capital); 
		model.addAttribute("page", page);
		model.addAttribute("capital", capital);
		
		model.addAttribute("originalCount",originalCount);
		model.addAttribute("returnCount",returnCount);
		return "modules/match/capitalList";
	}


	/**
	 * 
	 * @param id
	 * @param customWeight 页面传来自定义权重系数
	 * @param stickTop  是否置顶
	 * @param request
	 * @return
	 */
	@RequestMapping("/singleResetWeight")
	@ResponseBody
	public boolean singleResetWeight(String id ,String customWeight,String stickTop,HttpServletRequest request){
		boolean flag = true;
		Capital capital = new Capital();
		capital =capitalService.get(id);
		BigDecimal weight =  new BigDecimal(capital.getWeight());//权重
		BigDecimal customWeightOld =  new BigDecimal(capital.getCustomWeight());//原来的自定义权重
		
		weight=weight.divide(customWeightOld,2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(customWeight));
		String newWeight=weight.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		
		capital.setWeight(newWeight);
		capital.setCustomWeight(customWeight);
		capital.setStickTop(stickTop);
		
		flag=capitalService.singleResetWeight(capital);
		
		return flag;
	}
	
	
	/**
	 * 
	* @Title: batchResetWeight 
	* @Description: 重新计算权重
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "/batchResetWeight", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody BaseDTO batchResetWeight(HttpServletRequest request,
			HttpServletResponse response){
		logger.info("[batchResetWeight]-BEGIN，重新计算权重开始,操作人："+UserUtils.getUser().getLoginName());
		
		try {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		boolean flag=true;
		String message="SUCCESS";
			
		String 	capital_reset_url =Global.getConfig("capital_reset_url");
		String 	capital_reset_key =Global.getConfig("weight_reset_key");
		
		HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();		
		objectHashMapObject.put("foreign_password", capital_reset_key);
		
		
		String paramJson = JSONArray.toJSONString(objectHashMapObject);
		logger.info("资金权重重置调用参数===============>{}", paramJson); 
		String resString = DataHttpClient.postData(capital_reset_url, paramJson, "POST");
		logger.info("资金权重重置调用返回=======>{}", resString);  
		Map<String, Object> resMap = JSONObject.parseObject(resString, new TypeReference<Map<String, Object>>(){});
		
		flag = (Boolean)resMap.get("success");
		message = (String)resMap.get("message");
		
		dto.setRtnFlag(flag);
		dto.setMsg(message);
		} catch (Exception e) {
			dto.setRtnFlag(false);
			dto.setMsg(e.toString());
			logger.error("重新计算权重出现异常", e);
		}
		
		logger.info("[batchResetWeight]-END，重新计算权重结束");
		return dto;
	}
}