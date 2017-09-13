/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.finance.web;

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
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.modules.finance.entity.FinanceProduct;
import com.hzdjr.hzwd.modules.finance.service.FinanceProductService;

/**
 * 出借产品Controller
 * @author FYP
 * @version 2017-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/financeProduct")
public class FinanceProductController extends BaseController {

	@Autowired
	private FinanceProductService financeProductService;
	
	@ModelAttribute
	public FinanceProduct get(@RequestParam(required=false) String id) {
		FinanceProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = financeProductService.get(id);
		}
		if (entity == null){
			entity = new FinanceProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("finance:financeProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(FinanceProduct financeProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FinanceProduct> page = financeProductService.findPage(new Page<FinanceProduct>(request, response), financeProduct); 
		model.addAttribute("page", page);
		return "modules/finance/financeProductList";
	}

	@RequiresPermissions("finance:financeProduct:view")
	@RequestMapping(value = "form")
	public String form(FinanceProduct financeProduct, Model model) {
		model.addAttribute("financeProduct", financeProduct);
		return "modules/finance/financeProductForm";
	}

	@RequiresPermissions("finance:financeProduct:edit")
	@RequestMapping(value = "save")
	public String save(FinanceProduct financeProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, financeProduct)){
			return form(financeProduct, model);
		}
		financeProductService.save(financeProduct);
		addMessage(redirectAttributes, "保存出借产品成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeProduct/?repage";
	}
	
	@RequiresPermissions("finance:financeProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(FinanceProduct financeProduct, RedirectAttributes redirectAttributes) {
		financeProductService.delete(financeProduct);
		addMessage(redirectAttributes, "删除出借产品成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeProduct/?repage";
	}

}