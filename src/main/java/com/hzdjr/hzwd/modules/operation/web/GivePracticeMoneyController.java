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
import com.hzdjr.hzwd.modules.operation.entity.ActivityPracticeMoney;
import com.hzdjr.hzwd.modules.operation.service.ActivityPracticeMoneyService;

/**
 * 体验金发放列表Controller
 * 
 * @author hdj
 * @version 2016-10-27
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/givePracticeMoney")
public class GivePracticeMoneyController extends BaseController {

	@Autowired
	private ActivityPracticeMoneyService activityPracticeMoneyService;

	@ModelAttribute
	public ActivityPracticeMoney get(@RequestParam(required = false) String id) {
		ActivityPracticeMoney entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = activityPracticeMoneyService.get(id);
		}
		if (entity == null) {
			entity = new ActivityPracticeMoney();
		}
		return entity;
	}

	// @RequiresPermissions("operation:givePracticeMoney:view")
	@RequestMapping(value = { "list", "" })
	public String list(ActivityPracticeMoney givePracticeMoney, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		givePracticeMoney.setRemarks("1");
		// 查询总体验金
		Map<String, Object> givePracticeMoneys = activityPracticeMoneyService.getPracticeMoney(givePracticeMoney);
		Page<ActivityPracticeMoney> page = activityPracticeMoneyService.findPage(new Page<ActivityPracticeMoney>(request, response),
				givePracticeMoney);
		if (givePracticeMoneys != null) {
			if (givePracticeMoneys.get("giftamounts") == null) {
				givePracticeMoneys.put("giftamounts", 0);
			}
		}
		model.addAttribute("givePracticeMoney", givePracticeMoney);
		model.addAttribute("givePracticeMoneys", givePracticeMoneys);
		model.addAttribute("page", page);
		return "modules/operation/givePracticeMoneyList";
	}

	// 导出数据表格
	@RequestMapping(value = "exportPracticeMoneyFile", method = RequestMethod.POST)
	public String exportPracticeMoneyFile(ActivityPracticeMoney givePracticeMoney, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			givePracticeMoney.setRemarks("1");
			String fileName = "体验金发放列表信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<ActivityPracticeMoney> practiceMoneyFile = activityPracticeMoneyService.exportPracticeMoneyFile(givePracticeMoney);
			new ExportExcel("体验金发放列表信息", ActivityPracticeMoney.class, 1, 1).setDataList(practiceMoneyFile).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/operation/givePracticeMoney/?repage";
	}

	// @RequiresPermissions("operation:givePracticeMoney:view")
	@RequestMapping(value = "form")
	public String form(ActivityPracticeMoney givePracticeMoney, Model model) {
		model.addAttribute("givePracticeMoney", givePracticeMoney);
		return "modules/operation/givePracticeMoneyForm";
	}

	// @RequiresPermissions("operation:givePracticeMoney:edit")
	@RequestMapping(value = "save")
	public String save(ActivityPracticeMoney givePracticeMoney, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, givePracticeMoney)) {
			return form(givePracticeMoney, model);
		}
		activityPracticeMoneyService.save(givePracticeMoney);
		addMessage(redirectAttributes, "保存发放列表成功");
		return "redirect:" + Global.getAdminPath() + "/operation/givePracticeMoney/?repage";
	}

	// @RequiresPermissions("operation:givePracticeMoney:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityPracticeMoney givePracticeMoney, RedirectAttributes redirectAttributes) {
		activityPracticeMoneyService.delete(givePracticeMoney);
		addMessage(redirectAttributes, "删除发放列表成功");
		return "redirect:" + Global.getAdminPath() + "/operation/givePracticeMoney/?repage";
	}

}