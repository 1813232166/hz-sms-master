/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.finance.entity.TFinanceBespeak;
import com.hzwealth.sms.modules.finance.service.FinanceProductService;
import com.hzwealth.sms.modules.finance.service.TFinanceBespeakService;

/**
 * 预约审核Controller
 * @author HDG
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/financeBespeak")
public class TFinanceBespeakController extends BaseController {
	
	@Autowired
	private TFinanceBespeakService tFinanceBespeakService;
	
	@Autowired
	private FinanceProductService financeProductService;
	
	
	@ModelAttribute
	public TFinanceBespeak get(@RequestParam(required=false) String id) {
		TFinanceBespeak entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tFinanceBespeakService.get(id);
		}
		if (entity == null){
			entity = new TFinanceBespeak();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TFinanceBespeak tFinanceBespeak, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TFinanceBespeak> page = tFinanceBespeakService.findPage(new Page<TFinanceBespeak>(request, response), tFinanceBespeak); 
		model.addAttribute("tFinanceBespeak", tFinanceBespeak);
		model.addAttribute("page", page);
		model.addAttribute("financeProductList", financeProductService.findAllList());//产品名称
		return "modules/finance/financeBespeakList";
	}
	/**
	 * 
	 * Description: 预约审核--查看
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月4日 上午10:29:27
	 */
	@RequestMapping("/bespeakDetail")
	public String bespeakDetail(HttpServletRequest request, String id, Model model) {
		TFinanceBespeak bespeakDetail = tFinanceBespeakService.bespeakDetail(id);
		model.addAttribute("bespeakDetail",bespeakDetail);
		return "modules/finance/bespeakDetail";
	}
	/**
	 * 
	 * Description: 预约审核--审核
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月4日 下午1:52:35
	 */
	@ResponseBody
	@RequestMapping("/bespeakAudit")
	public boolean bespeakAudit(String tFinanceBespeakId ,String auditRadio,String auditArea,HttpServletRequest request) {
		
		try {
			tFinanceBespeakService.bespeakAudit(tFinanceBespeakId,auditRadio,auditArea);
			tFinanceBespeakService.updateTFinanceBespeakStatus(tFinanceBespeakId, auditRadio);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@RequestMapping(value = "form")
	public String form(TFinanceBespeak tFinanceBespeak, Model model) {
		model.addAttribute("tFinanceBespeak", tFinanceBespeak);
		return "modules/finance/tFinanceBespeakForm";
	}

	@RequiresPermissions("finance:tFinanceBespeak:edit")
	@RequestMapping(value = "save")
	public String save(TFinanceBespeak tFinanceBespeak, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tFinanceBespeak)){
			return form(tFinanceBespeak, model);
		}
		tFinanceBespeakService.save(tFinanceBespeak);
		addMessage(redirectAttributes, "保存预约审核成功");
		return "redirect:"+Global.getAdminPath()+"/finance/tFinanceBespeak/?repage";
	}
	
	@RequiresPermissions("finance:tFinanceBespeak:edit")
	@RequestMapping(value = "delete")
	public String delete(TFinanceBespeak tFinanceBespeak, RedirectAttributes redirectAttributes) {
		tFinanceBespeakService.delete(tFinanceBespeak);
		addMessage(redirectAttributes, "删除预约审核成功");
		return "redirect:"+Global.getAdminPath()+"/finance/tFinanceBespeak/?repage";
	}

}