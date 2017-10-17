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
import com.hzwealth.sms.modules.operation.entity.ActivityPracticeMoney;
import com.hzwealth.sms.modules.operation.service.ActivityPracticeMoneyService;

/**
 * 体验金使用列表Controller
 * 
 * @author hdj
 * @version 2016-10-27
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/usePracticeMoney")
public class UsePracticeMoneyController extends BaseController {

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

	// @RequiresPermissions("operation:usePracticeMoney:view")
	@RequestMapping(value = { "list", "" })
	public String list(ActivityPracticeMoney usePracticeMoney, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		usePracticeMoney.setRemarks("2");
		// 查询总体验金
		Map<String, Object> usePracticeMoneys = activityPracticeMoneyService.getPracticeMoney(usePracticeMoney);
		Page<ActivityPracticeMoney> page = activityPracticeMoneyService.findPage(new Page<ActivityPracticeMoney>(request, response),usePracticeMoney);
		if (usePracticeMoneys != null) {
			if (usePracticeMoneys.get("giftamounts") == null) {
				usePracticeMoneys.put("giftamounts", 0);
			}
		}
		model.addAttribute("usePracticeMoney", usePracticeMoney);
		model.addAttribute("usePracticeMoneys", usePracticeMoneys);
		model.addAttribute("page", page);
		return "modules/operation/usePracticeMoneyList";
	}

	// 导出数据表格
	@RequestMapping(value = "exportPracticeMoneyFile", method = RequestMethod.POST)
	public String exportPracticeMoneyFile(ActivityPracticeMoney usePracticeMoney, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			usePracticeMoney.setRemarks("2");
			String fileName = "体验金使用列表信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<ActivityPracticeMoney> practiceMoneyFile = activityPracticeMoneyService.exportPracticeMoneyFile(usePracticeMoney);
			new ExportExcel("体验金使用列表信息", ActivityPracticeMoney.class, 1, 2).setDataList(practiceMoneyFile).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/operation/usePracticeMoney/?repage";
	}

	// @RequiresPermissions("operation:usePracticeMoney:view")
	@RequestMapping(value = "form")
	public String form(ActivityPracticeMoney usePracticeMoney, Model model) {
		model.addAttribute("usePracticeMoney", usePracticeMoney);
		return "modules/operation/usePracticeMoneyForm";
	}

	// @RequiresPermissions("operation:usePracticeMoney:edit")
	@RequestMapping(value = "save")
	public String save(ActivityPracticeMoney usePracticeMoney, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, usePracticeMoney)) {
			return form(usePracticeMoney, model);
		}
		activityPracticeMoneyService.save(usePracticeMoney);
		addMessage(redirectAttributes, "保存使用列表成功");
		return "redirect:" + Global.getAdminPath() + "/operation/usePracticeMoney/?repage";
	}

	// @RequiresPermissions("operation:usePracticeMoney:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityPracticeMoney usePracticeMoney, RedirectAttributes redirectAttributes) {
		activityPracticeMoneyService.delete(usePracticeMoney);
		addMessage(redirectAttributes, "删除使用列表成功");
		return "redirect:" + Global.getAdminPath() + "/operation/usePracticeMoney/?repage";
	}

}