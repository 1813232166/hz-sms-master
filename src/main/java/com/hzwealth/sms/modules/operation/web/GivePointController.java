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
 * 积分发放列表Controller
 * @author hdj
 * @version 2016-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/givePoint")
public class GivePointController extends BaseController {

	@Autowired
	private ActivityPointService activityPointService;
	
	@ModelAttribute
	public ActivityPoint get(@RequestParam(required=false) String id) {
		ActivityPoint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = activityPointService.get(id);
		}
		if (entity == null){
			entity = new ActivityPoint();
		}
		return entity;
	}
	
	//@RequiresPermissions("operation:givePoint:view")
	@RequestMapping(value = {"list", ""})
	public String list(ActivityPoint givePoint, HttpServletRequest request, HttpServletResponse response, Model model) {
		givePoint.setPointSign("1");

		// 查询积分总计
		Map<String, Object> givePointCounts = activityPointService.getPointCounts(givePoint);
		Page<ActivityPoint> page = activityPointService.findPage(new Page<ActivityPoint>(request, response), givePoint); 
		if(givePointCounts!=null){
			if (givePointCounts.get("amounts") == null) {
				givePointCounts.put("amounts", 0);
			}
		}
		
		model.addAttribute("givePointCounts", givePointCounts);
		model.addAttribute("page", page);
		model.addAttribute("givePoint", givePoint);
		return "modules/operation/givePointList";
	}

	//@RequiresPermissions("operation:givePoint:view")
	@RequestMapping(value = "form")
	public String form(ActivityPoint givePoint, Model model) {
		model.addAttribute("givePoint", givePoint);
		return "modules/operation/givePointForm";
	}

	
	
	//	导出数据表格
    @RequestMapping(value = "exportPointFile",method=RequestMethod.POST)
    public String exportPointFile(ActivityPoint givePoint, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			givePoint.setPointSign("1");
            String fileName = "积分发放列表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<ActivityPoint> pointFile = activityPointService.exportPointFile(givePoint);
            new ExportExcel("积分发放列表信息", ActivityPoint.class,1,1).setDataList(pointFile).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/operation/givePoint/?repage";
    }
	
	
	
	//@RequiresPermissions("operation:givePoint:edit")
	@RequestMapping(value = "save")
	public String save(ActivityPoint givePoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, givePoint)){
			return form(givePoint, model);
		}
		activityPointService.save(givePoint);
		addMessage(redirectAttributes, "保存发放列表成功");
		return "redirect:"+Global.getAdminPath()+"/operation/givePoint/?repage";
	}
	
	//@RequiresPermissions("operation:givePoint:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityPoint givePoint, RedirectAttributes redirectAttributes) {
		activityPointService.delete(givePoint);
		addMessage(redirectAttributes, "删除发放列表成功");
		return "redirect:"+Global.getAdminPath()+"/operation/givePoint/?repage";
	}

}