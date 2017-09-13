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
import com.hzdjr.hzwd.modules.operation.entity.IrcouponDetail;
import com.hzdjr.hzwd.modules.operation.service.IrcouponDetailService;

/**
 * 加息券使用列表Controller
 * @author hdj
 * @version 2016-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/useIrcouponDetail")
public class UseIrcouponDetailController extends BaseController {

	@Autowired
	private IrcouponDetailService ircouponDetailService;
	
	@ModelAttribute
	public IrcouponDetail get(@RequestParam(required=false) String id) {
		IrcouponDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ircouponDetailService.get(id);
		}
		if (entity == null){
			entity = new IrcouponDetail();
		}
		return entity;
	}
	
	//@RequiresPermissions("operation:ircouponDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(IrcouponDetail ircouponDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		ircouponDetail.setMyStatus("1");
		
		Map<String, Object> ircouponCounts = ircouponDetailService.getIrcouponCounts(ircouponDetail);
		Page<IrcouponDetail> page = ircouponDetailService.findPage(new Page<IrcouponDetail>(request, response), ircouponDetail); 
		/*if (ircouponCounts != null) {
			if (ircouponCounts.get("amounts") == null) {
				ircouponCounts.put("amounts", 0);
			}
		}*/
		model.addAttribute("ircouponCounts", ircouponCounts);
		model.addAttribute("page", page);
		model.addAttribute("ircouponDetail", ircouponDetail);
		return "modules/operation/useIrcouponDetailList";
	}

	// 导出数据表格
	@RequestMapping(value = "exportIrcouponFile", method = RequestMethod.POST)
	public String exportIrcouponFile(IrcouponDetail ircouponDetail, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			ircouponDetail.setMyStatus("1");
			String fileName = "加息券使用列表信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<IrcouponDetail> exportIrcouponFile = ircouponDetailService.exportIrcouponFile(ircouponDetail);
			new ExportExcel("加息券使用列表信息", IrcouponDetail.class,1,1).setDataList(exportIrcouponFile).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/operation/useIrcouponDetail/?repage";
	}

	//@RequiresPermissions("operation:ircouponDetail:view")
	@RequestMapping(value = "form")
	public String form(IrcouponDetail ircouponDetail, Model model) {
		model.addAttribute("ircouponDetail", ircouponDetail);
		return "modules/operation/ircouponDetailForm";
	}

	//@RequiresPermissions("operation:ircouponDetail:edit")
	@RequestMapping(value = "save")
	public String save(IrcouponDetail ircouponDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ircouponDetail)){
			return form(ircouponDetail, model);
		}
		ircouponDetailService.save(ircouponDetail);
		addMessage(redirectAttributes, "保存使用成功");
		return "redirect:"+Global.getAdminPath()+"/operation/useIrcouponDetail/?repage";
	}
	
	//@RequiresPermissions("operation:ircouponDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(IrcouponDetail ircouponDetail, RedirectAttributes redirectAttributes) {
		ircouponDetailService.delete(ircouponDetail);
		addMessage(redirectAttributes, "删除使用成功");
		return "redirect:"+Global.getAdminPath()+"/operation/useIrcouponDetail/?repage";
	}

}