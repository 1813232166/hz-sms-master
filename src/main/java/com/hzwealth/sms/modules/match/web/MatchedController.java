package com.hzwealth.sms.modules.match.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.match.entity.Matched;
import com.hzwealth.sms.modules.match.service.MatchedService;

/**
 * 撮合队列Controller
 * @author FYP
 * @version 2017-06-15
 */
@Controller
@RequestMapping(value = "${adminPath}/match/matched")
public class MatchedController extends BaseController {

	@Autowired
	private MatchedService matchedService;
	
	@ModelAttribute
	public Matched get(@RequestParam(required=false) String id) {
		Matched entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = matchedService.get(id);
		}
		if (entity == null){
			entity = new Matched();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Matched matched, HttpServletRequest request, HttpServletResponse response, Model model) {
		//原始资金匹配金额
		matched.setCapitalTypeShow("1");
		Map<String, Object>   capitalType1Count = matchedService.findCapitalCount(matched);
		//原复投资金匹配金额
		matched.setCapitalTypeShow("999");
		Map<String, Object>   capitalType999Count = matchedService.findCapitalCount(matched);
		
		//原始资产匹配金额
		matched.setAssetTypeShow("1");
		Map<String, Object>   assetType1Count = matchedService.findAssetCount(matched);
		//债权转让资产匹配金额
		matched.setAssetTypeShow("2");
		Map<String, Object>   assetType2Count = matchedService.findAssetCount(matched);
		
		Page<Matched> page = matchedService.findPage(new Page<Matched>(request, response), matched); 
		model.addAttribute("page", page);
		model.addAttribute("matched", matched);
		
		model.addAttribute("capitalType1Count",capitalType1Count);
		model.addAttribute("capitalType999Count",capitalType999Count);
		model.addAttribute("assetType1Count",assetType1Count);
		model.addAttribute("assetType2Count",assetType2Count);
		
		return "modules/match/matchedList";
	}

	
	

}