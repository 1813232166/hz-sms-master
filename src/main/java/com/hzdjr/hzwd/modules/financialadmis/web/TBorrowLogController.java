/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.financialadmis.web;

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
import com.hzdjr.hzwd.modules.financialadmis.entity.TBorrowLog;
import com.hzdjr.hzwd.modules.financialadmis.service.TBorrowLogService;

/**
 * 标的记录Controller
 * @author xq
 * @version 2016-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/financialadmis/tBorrowLog")
public class TBorrowLogController extends BaseController {

	@Autowired
	private TBorrowLogService tBorrowLogService;
	
	@ModelAttribute
	public TBorrowLog get(@RequestParam(required=false) String id) {
		TBorrowLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBorrowLogService.get(id);
		}
		if (entity == null){
			entity = new TBorrowLog();
		}
		return entity;
	}
	
	@RequiresPermissions("financialadmis:tBorrowLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBorrowLog tBorrowLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBorrowLog> page = tBorrowLogService.findPage(new Page<TBorrowLog>(request, response), tBorrowLog); 
		model.addAttribute("page", page);
		return "modules/financialadmis/tBorrowLogList";
	}

	@RequiresPermissions("financialadmis:tBorrowLog:view")
	@RequestMapping(value = "form")
	public String form(TBorrowLog tBorrowLog, Model model) {
		model.addAttribute("tBorrowLog", tBorrowLog);
		return "modules/financialadmis/tBorrowLogForm";
	}

	@RequiresPermissions("financialadmis:tBorrowLog:edit")
	@RequestMapping(value = "save")
	public String save(TBorrowLog tBorrowLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBorrowLog)){
			return form(tBorrowLog, model);
		}
		tBorrowLogService.save(tBorrowLog);
		addMessage(redirectAttributes, "保存标的记录成功");
		return "redirect:"+Global.getAdminPath()+"/financialadmis/tBorrowLog/?repage";
	}
	
	@RequiresPermissions("financialadmis:tBorrowLog:edit")
	@RequestMapping(value = "delete")
	public String delete(TBorrowLog tBorrowLog, RedirectAttributes redirectAttributes) {
		tBorrowLogService.delete(tBorrowLog);
		addMessage(redirectAttributes, "删除标的记录成功");
		return "redirect:"+Global.getAdminPath()+"/financialadmis/tBorrowLog/?repage";
	}

}