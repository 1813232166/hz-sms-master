/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.repaybillplan.web;

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
import com.hzdjr.hzwd.modules.repaybillplan.entity.TBorrowRepayBillplan;
import com.hzdjr.hzwd.modules.repaybillplan.service.TBorrowRepayBillplanService;

/**
 * 回款管理列表Controller
 * @author yansy
 * @version 2016-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/repaybillplan/tBorrowRepayBillplan")
public class TBorrowRepayBillplanController extends BaseController {

	@Autowired
	private TBorrowRepayBillplanService tBorrowRepayBillplanService;
	
	@ModelAttribute
	public TBorrowRepayBillplan get(@RequestParam(required=false) String id) {
		TBorrowRepayBillplan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBorrowRepayBillplanService.get(id);
		}
		if (entity == null){
			entity = new TBorrowRepayBillplan();
		}
		return entity;
	}
	
	//@RequiresPermissions("repaybillplan:tBorrowRepayBillplan:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBorrowRepayBillplan tBorrowRepayBillplan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBorrowRepayBillplan> page = tBorrowRepayBillplanService.findPage(new Page<TBorrowRepayBillplan>(request, response), tBorrowRepayBillplan); 
		model.addAttribute("page", page);
		return "modules/repaybillplan/tBorrowRepayBillplanList";
	}

	//@RequiresPermissions("repaybillplan:tBorrowRepayBillplan:view")
	@RequestMapping(value = "form")
	public String form(TBorrowRepayBillplan tBorrowRepayBillplan, Model model) {
		model.addAttribute("tBorrowRepayBillplan", tBorrowRepayBillplan);
		return "modules/repaybillplan/tBorrowRepayBillplanForm";
	}

	//@RequiresPermissions("repaybillplan:tBorrowRepayBillplan:edit")
	@RequestMapping(value = "save")
	public String save(TBorrowRepayBillplan tBorrowRepayBillplan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBorrowRepayBillplan)){
			return form(tBorrowRepayBillplan, model);
		}
		tBorrowRepayBillplanService.save(tBorrowRepayBillplan);
		addMessage(redirectAttributes, "保存查询回款列表成功");
		return "redirect:"+Global.getAdminPath()+"/repaybillplan/tBorrowRepayBillplan/?repage";
	}
	
	//@RequiresPermissions("repaybillplan:tBorrowRepayBillplan:edit")
	@RequestMapping(value = "delete")
	public String delete(TBorrowRepayBillplan tBorrowRepayBillplan, RedirectAttributes redirectAttributes) {
		tBorrowRepayBillplanService.delete(tBorrowRepayBillplan);
		addMessage(redirectAttributes, "删除查询回款列表成功");
		return "redirect:"+Global.getAdminPath()+"/repaybillplan/tBorrowRepayBillplan/?repage";
	}

}