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
import com.hzdjr.hzwd.modules.operation.entity.ActivityIrcoupon;
import com.hzdjr.hzwd.modules.operation.service.ActivityIrcouponService;

/**
 * 加息券管理Controller
 * @author hdj
 * @version 2016-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/activityIrcoupon")
public class ActivityIrcouponController extends BaseController {

	@Autowired
	private ActivityIrcouponService activityIrcouponService;
	
	@ModelAttribute
	public ActivityIrcoupon get(@RequestParam(required=false) String id) {
		ActivityIrcoupon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = activityIrcouponService.get(id);
		}
		if (entity == null){
			entity = new ActivityIrcoupon();
		}
		return entity;
	}
	
	//@RequiresPermissions("operation:activityIrcoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(ActivityIrcoupon activityIrcoupon, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Map<String, Object> ircouponCounts = activityIrcouponService.getIrcouponCounts(activityIrcoupon);
		Page<ActivityIrcoupon> page = activityIrcouponService.findPage(new Page<ActivityIrcoupon>(request, response), activityIrcoupon); 
		/*if(ircouponCounts!=null){
			if (ircouponCounts.get("amounts") == null) {
				ircouponCounts.put("amounts", 0);
			}
		}*/
		model.addAttribute("ircouponCounts", ircouponCounts);
		model.addAttribute("page", page);
		model.addAttribute("activityIrcoupon", activityIrcoupon);
		return "modules/operation/activityIrcouponList";
	}
	
	//	导出数据表格
	@RequestMapping(value = "exportActivityIrcoupon",method=RequestMethod.POST)
	public String exportActivityIrcoupon(ActivityIrcoupon activityIrcoupon, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
	        String fileName = "加息券管理列表信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	        List<ActivityIrcoupon> exportActivityIrcoupon = activityIrcouponService.exportActivityIrcoupon(activityIrcoupon);
	        new ExportExcel("加息券管理列表信息", ActivityIrcoupon.class).setDataList(exportActivityIrcoupon).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/operation/activityIrcoupon/?repage";
	}

	//@RequiresPermissions("operation:activityIrcoupon:view")
	@RequestMapping(value = "form")
	public String form(ActivityIrcoupon activityIrcoupon, Model model) {
		model.addAttribute("activityIrcoupon", activityIrcoupon);
		return "modules/operation/activityIrcouponForm";
	}

	//修改加息券状态
	//@RequiresPermissions("operation:activityIrcoupon:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(ActivityIrcoupon activityIrcoupon, Model model, RedirectAttributes redirectAttributes) {
		if(activityIrcoupon.getId()!=null){
			activityIrcouponService.updateStatus(activityIrcoupon);
			addMessage(redirectAttributes, "操作成功");
		}
		return "redirect:"+Global.getAdminPath()+"/operation/activityIrcoupon/?repage";
	}
		
	
	//@RequiresPermissions("operation:activityIrcoupon:edit")
	@RequestMapping(value = "save")
	public String save(ActivityIrcoupon activityIrcoupon, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, activityIrcoupon)){
			return form(activityIrcoupon, model);
		}
		activityIrcouponService.save(activityIrcoupon);
		addMessage(redirectAttributes, "保存加息券成功");
		return "redirect:"+Global.getAdminPath()+"/operation/activityIrcoupon/?repage";
	}
	
	//@RequiresPermissions("operation:activityIrcoupon:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityIrcoupon activityIrcoupon, RedirectAttributes redirectAttributes) {
		activityIrcouponService.delete(activityIrcoupon);
		addMessage(redirectAttributes, "删除加息券管理成功");
		return "redirect:"+Global.getAdminPath()+"/operation/activityIrcoupon/?repage";
	}

}