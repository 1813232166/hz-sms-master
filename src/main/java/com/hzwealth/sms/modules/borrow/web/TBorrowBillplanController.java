/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.web;

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

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;
import com.hzwealth.sms.modules.borrow.service.TBorrowBillplanService;

/**
 * 还款计划Controller
 * @author 秦桂晶
 * @version 2016-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/tBorrowBillplan")
public class TBorrowBillplanController extends BaseController {

	@Autowired
	private TBorrowBillplanService tBorrowBillplanService;
	
	@ModelAttribute
	public TBorrowBillplan get(@RequestParam(required=false) String id) {
		TBorrowBillplan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBorrowBillplanService.get(id);
		}
		if (entity == null){
			entity = new TBorrowBillplan();
		}
		return entity;
	}
	
	@RequiresPermissions("borrow:tBorrowBillplan:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBorrowBillplan tBorrowBillplan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBorrowBillplan> page = tBorrowBillplanService.findPage(new Page<TBorrowBillplan>(request, response), tBorrowBillplan); 
		model.addAttribute("page", page);
		return "modules/borrow/tBorrowBillplanList";
	}

	@RequiresPermissions("borrow:tBorrowBillplan:view")
	@RequestMapping(value = "form")
	public String form(TBorrowBillplan tBorrowBillplan, Model model) {
		model.addAttribute("tBorrowBillplan", tBorrowBillplan);
		return "modules/borrow/tBorrowBillplanForm";
	}

	@RequiresPermissions("borrow:tBorrowBillplan:edit")
	@RequestMapping(value = "save")
	public String save(TBorrowBillplan tBorrowBillplan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBorrowBillplan)){
			return form(tBorrowBillplan, model);
		}
		tBorrowBillplanService.save(tBorrowBillplan);
		addMessage(redirectAttributes, "保存还款计划成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tBorrowBillplan/?repage";
	}
	
	@RequiresPermissions("borrow:tBorrowBillplan:edit")
	@RequestMapping(value = "delete")
	public String delete(TBorrowBillplan tBorrowBillplan, RedirectAttributes redirectAttributes) {
		tBorrowBillplanService.delete(tBorrowBillplan);
		addMessage(redirectAttributes, "删除还款计划成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tBorrowBillplan/?repage";
	}

}