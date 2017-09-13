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
import com.hzdjr.hzwd.modules.operation.entity.ActivityPracticeMoneyProfit;
import com.hzdjr.hzwd.modules.operation.service.ActivityPracticeMoneyProfitService;

/**
 * 体验金收益列表Controller
 * 
 * @author hdj
 * @version 2016-10-31
 */
@Controller	
@RequestMapping(value = "${adminPath}/operation/practiceMoneyProfit")
public class PracticeMoneyProfitController extends BaseController {

	@Autowired
	private ActivityPracticeMoneyProfitService activityPracticeMoneyProfitService;

	@ModelAttribute
	public ActivityPracticeMoneyProfit get(@RequestParam(required = false) String id) {
		ActivityPracticeMoneyProfit entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = activityPracticeMoneyProfitService.get(id);
		}
		if (entity == null) {
			entity = new ActivityPracticeMoneyProfit();
		}
		return entity;
	}

	// @RequiresPermissions("operation:practiceMoneyProfit:view")
	@RequestMapping(value = { "list", "" })
	public String list(ActivityPracticeMoneyProfit activityPracticeMoneyProfit, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object> totalmoneyCounts = activityPracticeMoneyProfitService
				.getTotalmoneyCounts(activityPracticeMoneyProfit);
		Page<ActivityPracticeMoneyProfit> page = activityPracticeMoneyProfitService
				.findPage(new Page<ActivityPracticeMoneyProfit>(request, response), activityPracticeMoneyProfit);
		model.addAttribute("totalmoneyCounts", totalmoneyCounts);
		model.addAttribute("page", page);
		model.addAttribute("activityPracticeMoneyProfit", activityPracticeMoneyProfit);
		return "modules/operation/practiceMoneyProfitList";
	}
	
	//	导出数据表格
    @RequestMapping(value = "exportPracticeMoneyProfit",method=RequestMethod.POST)
    public String exportPracticeMoneyProfit(ActivityPracticeMoneyProfit activityPracticeMoneyProfit, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "收益发放列表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ActivityPracticeMoneyProfit> page = activityPracticeMoneyProfitService.findPage(new Page<ActivityPracticeMoneyProfit>(request, response), activityPracticeMoneyProfit);
            new ExportExcel("收益发放列表信息", ActivityPracticeMoneyProfit.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/operation/practiceMoneyProfit/?repage";
    }
	

	// 查询明细
	// @RequiresPermissions("operation:practiceMoneyProfit:view")
	@RequestMapping(value = "getProfitParticulars")
	public String getProfitParticulars(String identify, ActivityPracticeMoney practiceMoney,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			if (practiceMoney.getProfitid() != null) {
				Map<String, Object> particularsCounts = activityPracticeMoneyProfitService
						.getParticularsCounts(practiceMoney);
				Page<ActivityPracticeMoney> page = activityPracticeMoneyProfitService
						.getProfitParticulars(new Page<ActivityPracticeMoney>(request, response), practiceMoney);
				particularsCounts.put("identify", identify);
				model.addAttribute("particularsCounts", particularsCounts);
				model.addAttribute("practiceMoney", practiceMoney);
				model.addAttribute("page", page);
			}
			return "modules/operation/practiceMoneyProfitForm";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 全部直接补发
	@RequestMapping(value = "provideUpdates")
	public String provideUpdates(ActivityPracticeMoney practiceMoney,HttpServletRequest request) {
		try {
			if (practiceMoney.getProfitid() != null) {
				List<ActivityPracticeMoney> updateProfitParticulars = activityPracticeMoneyProfitService.getUpdateProfitParticulars(practiceMoney);
				Integer i=0;
				for (ActivityPracticeMoney apracticeMoney : updateProfitParticulars) {
					if(apracticeMoney.getProfitstatus().equals("失败")||apracticeMoney.getProfitstatus().equals("待发")){
						try {
							activityPracticeMoneyProfitService.updateSuccess(apracticeMoney.getId());
						} catch (Exception e) {
							if(i==0){i++;}
							activityPracticeMoneyProfitService.updateFail(apracticeMoney.getId());
						}
					}
				}
				if(i==0){
					activityPracticeMoneyProfitService.updateProfitSuccess(practiceMoney.getProfitid());
				}else {
					activityPracticeMoneyProfitService.updateProfitFail(practiceMoney.getProfitid());
				}
			}
			return "redirect:" + Global.getAdminPath() + "/operation/practiceMoneyProfit/?repage";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:" + Global.getAdminPath() + "/operation/practiceMoneyProfit/?repage";
		}
	}
	
	// 单个点击补发
	@RequestMapping(value = "provideUpdate")
	public void provideUpdate(ActivityPracticeMoney practiceMoney,HttpServletRequest request) {
		if(practiceMoney!=null){
			try {
				activityPracticeMoneyProfitService.updateSuccess(practiceMoney.getId());
				List<ActivityPracticeMoney> updateProfitParticulars = activityPracticeMoneyProfitService.getUpdateProfitParticulars(practiceMoney);
				Integer i=0;
				for (ActivityPracticeMoney activityPracticeMoney : updateProfitParticulars) {
					if(activityPracticeMoney.getProfitstatus().equals("失败")||activityPracticeMoney.getProfitstatus().equals("待发")){
						i++;
				}
				if(i==0){
					try {
						activityPracticeMoneyProfitService.updateProfitSuccess(practiceMoney.getProfitid());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
					activityPracticeMoneyProfitService.updateFail(practiceMoney.getId());
			}
		}
	}	

	// @RequiresPermissions("operation:practiceMoneyProfit:view")
	@RequestMapping(value = "form")
	public String form(ActivityPracticeMoneyProfit activityPracticeMoneyProfit, Model model) {
		model.addAttribute("activityPracticeMoneyProfit", activityPracticeMoneyProfit);
		return "modules/operation/practiceMoneyProfitForm";
	}

	// @RequiresPermissions("operation:practiceMoneyProfit:edit")
	@RequestMapping(value = "save")
	public String save(ActivityPracticeMoneyProfit activityPracticeMoneyProfit, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, activityPracticeMoneyProfit)) {
			return form(activityPracticeMoneyProfit, model);
		}
		activityPracticeMoneyProfitService.save(activityPracticeMoneyProfit);
		addMessage(redirectAttributes, "保存收益发放成功");
		return "redirect:" + Global.getAdminPath() + "/operation/practiceMoneyProfit/?repage";
	}

	// @RequiresPermissions("operation:practiceMoneyProfit:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityPracticeMoneyProfit activityPracticeMoneyProfit,
			RedirectAttributes redirectAttributes) {
		activityPracticeMoneyProfitService.delete(activityPracticeMoneyProfit);
		addMessage(redirectAttributes, "删除收益发放成功");
		return "redirect:" + Global.getAdminPath() + "/operation/practiceMoneyProfit/?repage";
	}

}