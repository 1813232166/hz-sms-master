/**
 * Copyright &copy; 2016
 */
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
import com.hzwealth.sms.modules.match.entity.Asset;
import com.hzwealth.sms.modules.match.service.AssetService;
import com.hzwealth.sms.modules.sys.utils.UserUtils;
/**
 * 资产队列Controller
 * @author HDG
 * @version 2017-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/match/asset")
public class AssetController extends BaseController {

	@Autowired
	private AssetService assetService;
	@Autowired
	private BaseDTO dto;
	
	
	@ModelAttribute
	public Asset get(@RequestParam(required=false) String id) {
		Asset entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = assetService.get(id);
		}
		if (entity == null){
			entity = new Asset();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(Asset asset, HttpServletRequest request, HttpServletResponse response, Model model) {
		
				Map<String, Object> oriAssetMap = assetService.getOriAsset();
				Map<String, Object> origWaitAssetMap = assetService.getOrigWaitAsset();
				Map<String, Object> origResiAssetMap = assetService.getOrigResiAsset();
				Map<String, Object> tranAssetMap = assetService.getTranAsset();
				Map<String, Object> tranWaitMatcAssetMap = assetService.getTranWaitMatcAsset();
				Map<String, Object> origPortMatcAssetMap = assetService.getOrigPortMatcAsset();
				Map<String, Object> tranPortMatcAssetMap = assetService.getTranPortMatcAsset();
				Map<String, Object> tranResiAssetMap = assetService.getTranResiAsset();
				
				Page<Asset> page = assetService.findPage(new Page<Asset>(request, response), asset); 

				//分页的对象，用于回显
				model.addAttribute("page", page);
				//用户集合的对象，用于列表展示
				model.addAttribute("asset", asset);
				//原始资产(元)
				model.addAttribute("oriAssetMap", oriAssetMap);
				//原始待匹配资产(元)
				model.addAttribute("origWaitAssetMap", origWaitAssetMap);
				//原始剩余资产(元)
				model.addAttribute("origResiAssetMap", origResiAssetMap);
				//转让资产(元)
				model.addAttribute("tranAssetMap", tranAssetMap);
				//转让待匹配资产(元)
				model.addAttribute("tranWaitMatcAssetMap", tranWaitMatcAssetMap);
				//原始部分匹配资产(元)
				model.addAttribute("origPortMatcAssetMap", origPortMatcAssetMap);
				//转让部分匹配资产(元)
				model.addAttribute("tranPortMatcAssetMap", tranPortMatcAssetMap);
				//转让剩余资产(元)
				model.addAttribute("tranResiAssetMap", tranResiAssetMap);

		return "modules/match/assetList";
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
		Asset asset = new Asset();
		asset =assetService.get(id);
		BigDecimal weight =  new BigDecimal(asset.getWeight());//权重
		BigDecimal customWeightOld =  new BigDecimal(asset.getCustomWeight());//原来的自定义权重
		
		weight=weight.divide(customWeightOld,2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(customWeight));
		Integer newWeight=weight.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id", id);
		paramMap.put("isStickTop", stickTop);
		paramMap.put("addWeight", customWeight);
		paramMap.put("cus", newWeight);
		if(assetService.updateWeight(paramMap)>0){
			return true;
		}
		return false;
		
	}
	/**
	 * 
	* @Title: batchResetWeight 
	* @Description: 重新计算权重
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "/batchResetWeight", method = { RequestMethod.POST,RequestMethod.GET })
	public @ResponseBody BaseDTO batchResetWeight(HttpServletRequest request,
			HttpServletResponse response){
		logger.info("[batchResetWeight]-BEGIN，重新计算权重开始,操作人："+UserUtils.getUser().getLoginName());
		
		try {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		boolean flag=true;
		String message="SUCCESS";
			
		String 	capital_reset_url =Global.getConfig("asset_reset_url");
		String 	capital_reset_key =Global.getConfig("weight_reset_key");
		
		HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();		
		objectHashMapObject.put("foreign_password", capital_reset_key);
		
		
		String paramJson = JSONArray.toJSONString(objectHashMapObject);
		logger.info("资产权重重置调用参数===============>{}", paramJson); 
		String resString = DataHttpClient.postData(capital_reset_url, paramJson, "POST");
		logger.info("资产权重重置调用返回=======>{}", resString);  
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