/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.web;

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

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.operation.entity.ActivityMoney;
import com.hzdjr.hzwd.modules.operation.service.ActivityMoneyService;

/**
 * 活动现金发放Controller
 * 
 * @author hdj
 * @version 2016-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/activityMoney")
public class ActivityMoneyController extends BaseController {

	@Autowired
	private ActivityMoneyService activityMoneyService;

	@ModelAttribute
	public ActivityMoney get(@RequestParam(required = false) String id) {
		ActivityMoney entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = activityMoneyService.get(id);
		}
		if (entity == null) {
			entity = new ActivityMoney();
		}
		return entity;
	}

	// @RequiresPermissions("operation:activityMoney:view")
	@RequestMapping(value = { "list", "" })
	public String list(ActivityMoney activityMoney, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Map<String, Object> moneyCounts = activityMoneyService.getActivityMoney(activityMoney);
		Page<ActivityMoney> page = activityMoneyService.findPage(new Page<ActivityMoney>(request, response),activityMoney);
		if (moneyCounts != null) {
			if (moneyCounts.get("amounts") == null) {
				moneyCounts.put("amounts", 0);
			}
		}
		model.addAttribute("moneyCounts", moneyCounts);
		model.addAttribute("page", page);
		model.addAttribute("activityMoney", activityMoney);
		return "modules/operation/activityMoneyList";
	}

	// 导出数据表格
	@RequestMapping(value = "exportMoneyFile", method = RequestMethod.POST)
	public String exportMoneyFile(ActivityMoney activityMoney, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "现金发放列表信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<ActivityMoney> moneyFile = activityMoneyService.exportMoneyFile(activityMoney);
			new ExportExcel("现金发放列表信息", ActivityMoney.class, 1, 1).setDataList(moneyFile).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/operation/givePoint/?repage";
	}

	// @RequiresPermissions("operation:activityMoney:view")
	@RequestMapping(value = "form")
	public String form(ActivityMoney activityMoney, Model model) {
		model.addAttribute("activityMoney", activityMoney);
		return "modules/operation/activityMoneyForm";
	}

	// @RequiresPermissions("operation:activityMoney:edit")
	@RequestMapping(value = "save")
	public String save(ActivityMoney activityMoney, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, activityMoney)) {
			return form(activityMoney, model);
		}
		activityMoneyService.save(activityMoney);
		addMessage(redirectAttributes, "保存发放列表成功");
		return "redirect:" + Global.getAdminPath() + "/operation/activityMoney/?repage";
	}

	// @RequiresPermissions("operation:activityMoney:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityMoney activityMoney, RedirectAttributes redirectAttributes) {
		activityMoneyService.delete(activityMoney);
		addMessage(redirectAttributes, "删除发放列表成功");
		return "redirect:" + Global.getAdminPath() + "/operation/activityMoney/?repage";
	}

}