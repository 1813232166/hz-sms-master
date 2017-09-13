/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.finance.entity.TCapitalBespeak;
import com.hzdjr.hzwd.modules.finance.service.FinanceProductService;
import com.hzdjr.hzwd.modules.finance.service.TCapitalBespeakService;

/**
 * 预约记录管理Controller
 * @author HDG
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/tCapitalBespeak")
public class TCapitalBespeakController extends BaseController {

	@Autowired
	private TCapitalBespeakService tCapitalBespeakService;
	
	@Autowired
	private FinanceProductService financeProductService;
	@ModelAttribute
	public TCapitalBespeak get(@RequestParam(required=false) String id) {
		TCapitalBespeak entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tCapitalBespeakService.get(id);
		}
		if (entity == null){
			entity = new TCapitalBespeak();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TCapitalBespeak tCapitalBespeak, HttpServletRequest request, HttpServletResponse response, Model model) {

		model.addAttribute("tCapitalBespeak", tCapitalBespeak);
		model.addAttribute("financeProductList", financeProductService.findAllList());//产品名称
	    Map<String, Object> findBespeakingMap = tCapitalBespeakService.findBespeaking(tCapitalBespeak);
	    Map<String, Object> findCancelBespeakMap = tCapitalBespeakService.findCancelBespeak(tCapitalBespeak);
	    if(findBespeakingMap.get("amountSumOne")==null ||findBespeakingMap.get("amountSumOne")=="" ){
	    	findBespeakingMap.put("amountSumOne", 0);
	    }
	    if(findCancelBespeakMap.get("amountSumTwo")==null ||findCancelBespeakMap.get("amountSumTwo")=="" ){
	    	findCancelBespeakMap.put("amountSumTwo", 0);
	    }
	    model.addAttribute("findBespeakingMap", findBespeakingMap);
	    model.addAttribute("findCancelBespeakMap", findCancelBespeakMap);
		Page<TCapitalBespeak> page = tCapitalBespeakService.findPage(new Page<TCapitalBespeak>(request, response), tCapitalBespeak); 
		model.addAttribute("page", page);
		return "modules/finance/capitalBespeakList";
	}

	@RequestMapping(value = "form")
	public String form(TCapitalBespeak tCapitalBespeak, Model model) {
		model.addAttribute("tCapitalBespeak", tCapitalBespeak);
		return "modules/finance/tCapitalBespeakForm";
	}

	@RequiresPermissions("finance:tCapitalBespeak:edit")
	@RequestMapping(value = "save")
	public String save(TCapitalBespeak tCapitalBespeak, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tCapitalBespeak)){
			return form(tCapitalBespeak, model);
		}
		tCapitalBespeakService.save(tCapitalBespeak);
		addMessage(redirectAttributes, "保存预约记录管理成功");
		return "redirect:"+Global.getAdminPath()+"/finance/tCapitalBespeak/?repage";
	}
	
	@RequiresPermissions("finance:tCapitalBespeak:edit")
	@RequestMapping(value = "delete")
	public String delete(TCapitalBespeak tCapitalBespeak, RedirectAttributes redirectAttributes) {
		tCapitalBespeakService.delete(tCapitalBespeak);
		addMessage(redirectAttributes, "删除预约记录管理成功");
		return "redirect:"+Global.getAdminPath()+"/finance/tCapitalBespeak/?repage";
	}

}