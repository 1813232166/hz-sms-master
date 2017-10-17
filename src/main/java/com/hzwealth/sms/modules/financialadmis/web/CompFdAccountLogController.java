/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.financialadmis.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;

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
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.financialadmis.entity.CompFdAccountLog;
import com.hzwealth.sms.modules.financialadmis.service.CompFdAccountLogService;
import com.hzwealth.sms.modules.repaymentmanage.entity.LendPlan;

/**
 * 平台账户Controller
 * @author xq
 * @version 2016-10-18
 */
@Controller
@RequestMapping(value = "${adminPath}/financialadmis/compFdAccountLog")
public class CompFdAccountLogController extends BaseController {

	@Autowired
	private CompFdAccountLogService compFdAccountLogService;
	
	@ModelAttribute
	public CompFdAccountLog get(@RequestParam(required=false) String id) {
		CompFdAccountLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = compFdAccountLogService.get(id);
		}
		if (entity == null){
			entity = new CompFdAccountLog();
		}
		return entity;
	}
	
	@RequiresPermissions("financialadmis:compFdAccountLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(CompFdAccountLog compFdAccountLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CompFdAccountLog> page = compFdAccountLogService.findPage(new Page<CompFdAccountLog>(request, response), compFdAccountLog); 
		model.addAttribute("page", page);
		model.addAttribute("compFdAccountLog",compFdAccountLog);
		return "modules/financialadmis/compFdAccountLogList";
	}

	@RequiresPermissions("financialadmis:compFdAccountLog:view")
	@RequestMapping(value = "form")
	public String form(CompFdAccountLog compFdAccountLog, Model model) {
		model.addAttribute("compFdAccountLog", compFdAccountLog);
		return "modules/financialadmis/compFdAccountLogForm";
	}

	@RequiresPermissions("financialadmis:compFdAccountLog:edit")
	@RequestMapping(value = "save")
	public String save(CompFdAccountLog compFdAccountLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, compFdAccountLog)){
			return form(compFdAccountLog, model);
		}
		compFdAccountLogService.save(compFdAccountLog);
		addMessage(redirectAttributes, "保存平台账户成功");
		return "redirect:"+Global.getAdminPath()+"/financialadmis/compFdAccountLog/?repage";
	}
	
	@RequiresPermissions("financialadmis:compFdAccountLog:edit")
	@RequestMapping(value = "delete")
	public String delete(CompFdAccountLog compFdAccountLog, RedirectAttributes redirectAttributes) {
		compFdAccountLogService.delete(compFdAccountLog);
		addMessage(redirectAttributes, "删除平台账户成功");
		return "redirect:"+Global.getAdminPath()+"/financialadmis/compFdAccountLog/?repage";
	}
	
	
	/**
	 * TODO   导出平台账户列表
	 * @param redirectAttributes
	 * @param compFdAccountLog
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/exportcompFdAccountList")
	public String exportcompFdAccountList(RedirectAttributes redirectAttributes,CompFdAccountLog compFdAccountLog, HttpServletRequest request, HttpServletResponse response, Model model){
		
		List<CompFdAccountLog> list = compFdAccountLogService.findList(compFdAccountLog); 
		String fileName = "平台账户列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		 try {
			new ExportExcel("平台账户列表", CompFdAccountLog.class).setDataList(list).write(response, fileName).dispose();
		   return null;
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		
		 return "redirect:" + Global.getAdminPath() +"/financialadmis/compFdAccountLog/list";
	}

	
}