/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.operation.web;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.operation.entity.ActivityPoint;
import com.hzwealth.sms.modules.operation.service.ActivityPointService;

/**
 * 积分使用列表Controller
 * 
 * @author hdj
 * @version 2016-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/usePoint")
public class UsePointController extends BaseController {

	@Autowired
	private ActivityPointService activityPointService;

	@ModelAttribute
	public ActivityPoint get(@RequestParam(required = false) String id) {
		ActivityPoint entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = activityPointService.get(id);
		}
		if (entity == null) {
			entity = new ActivityPoint();
		}
		return entity;
	}

	// @RequiresPermissions("operation:usePoint:view")
	@RequestMapping(value = { "list", "" })
	public String list(ActivityPoint usePoint, HttpServletRequest request, HttpServletResponse response, Model model) {
		usePoint.setPointSign("2");

		// 查询积分总计
		Map<String, Object> usePointCounts = activityPointService.getPointCounts(usePoint);
		Page<ActivityPoint> page = activityPointService.findPage(new Page<ActivityPoint>(request, response), usePoint);
		if (usePointCounts != null) {
			if (usePointCounts.get("amounts") == null) {
				usePointCounts.put("amounts", 0);
			}
		}

		model.addAttribute("usePointCounts", usePointCounts);
		model.addAttribute("page", page);
		model.addAttribute("usePoint", usePoint);
		return "modules/operation/usePointList";
	}

	// 导出数据表格
	@RequestMapping(value = "exportPointFile", method = RequestMethod.POST)
	public String exportPointFile(ActivityPoint usePoint, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			usePoint.setPointSign("2");
			String fileName = "积分使用列表信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<ActivityPoint> pointFile = activityPointService.exportPointFile(usePoint);
			new ExportExcel("积分使用列表信息", ActivityPoint.class,1,2).setDataList(pointFile).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/operation/givePoint/?repage";
	}

	// @RequiresPermissions("operation:usePoint:view")
	@RequestMapping(value = "form")
	public String form(ActivityPoint usePoint, Model model) {
		model.addAttribute("usePoint", usePoint);
		return "modules/operation/usePointForm";
	}

	// @RequiresPermissions("operation:usePoint:edit")
	@RequestMapping(value = "save")
	public String save(ActivityPoint usePoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, usePoint)) {
			return form(usePoint, model);
		}
		activityPointService.save(usePoint);
		addMessage(redirectAttributes, "保存使用列表成功");
		return "redirect:" + Global.getAdminPath() + "/operation/usePoint/?repage";
	}

	// @RequiresPermissions("operation:usePoint:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityPoint usePoint, RedirectAttributes redirectAttributes) {
		activityPointService.delete(usePoint);
		addMessage(redirectAttributes, "删除使用列表成功");
		return "redirect:" + Global.getAdminPath() + "/operation/usePoint/?repage";
	}

}