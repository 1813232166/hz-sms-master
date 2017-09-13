/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.financialadmis.web;

import java.util.HashMap;
import java.util.List;
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
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.modules.financialadmis.entity.TFdAccountLog;
import com.hzdjr.hzwd.modules.financialadmis.entity.TFdAccountLogVo;
import com.hzdjr.hzwd.modules.financialadmis.service.TFdAccountLogService;
import com.hzdjr.hzwd.modules.fk.entity.Fkuan;

/**
 * 账户资金-个人账户Controller
 * @author xq
 * @version 2016-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/financialadmis/tFdAccountLog")
public class TFdAccountLogController extends BaseController {

	@Autowired
	private TFdAccountLogService tFdAccountLogService;
	
	@ModelAttribute
	public TFdAccountLog get(@RequestParam(required=false) String id) {
		TFdAccountLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tFdAccountLogService.get(id);
		}
		if (entity == null){
			entity = new TFdAccountLog();
		}
		return entity;
	}
	
	@RequiresPermissions("financialadmis:tFdAccountLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(TFdAccountLog tFdAccountLog,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TFdAccountLog> page = tFdAccountLogService.findPage(new Page<TFdAccountLog>(request, response), tFdAccountLog); 
		model.addAttribute("page", page);
		model.addAttribute("tFdAccountLog",tFdAccountLog);
		return "modules/financialadmis/tFdAccountLogList";
	}

	@RequiresPermissions("financialadmis:tFdAccountLog:view")
	@RequestMapping(value = "form")
	public String form(TFdAccountLog tFdAccountLog, Model model) {
		model.addAttribute("tFdAccountLog", tFdAccountLog);
		return "modules/financialadmis/tFdAccountLogForm";
	}

	@RequiresPermissions("financialadmis:tFdAccountLog:edit")
	@RequestMapping(value = "save")
	public String save(TFdAccountLog tFdAccountLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tFdAccountLog)){
			return form(tFdAccountLog, model);
		}
		tFdAccountLogService.save(tFdAccountLog);
		addMessage(redirectAttributes, "保存个人账户成功");
		return "redirect:"+Global.getAdminPath()+"/financialadmis/tFdAccountLog/?repage";
	}
	
	@RequiresPermissions("financialadmis:tFdAccountLog:edit")
	@RequestMapping(value = "delete")
	public String delete(TFdAccountLog tFdAccountLog, RedirectAttributes redirectAttributes) {
		tFdAccountLogService.delete(tFdAccountLog);
		addMessage(redirectAttributes, "删除个人账户成功");
		return "redirect:"+Global.getAdminPath()+"/financialadmis/tFdAccountLog/?repage";
	}
	
	/*导出已经放款列表*/
	@RequestMapping(value="/exportlist")
	public String exportHasFkuan(TFdAccountLog tFdAccountLog,Model model,HttpServletRequest req,HttpServletResponse response,RedirectAttributes redirectAttributes){
		
		
		List<TFdAccountLogVo> hasFkuanList = tFdAccountLogService.findexportList(tFdAccountLog);
		String fileName = "个人账户列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("个人账户列表", TFdAccountLogVo.class).setDataList(hasFkuanList);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tFdAccountLogList";
	}

}