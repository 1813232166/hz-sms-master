/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.innerreffere.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.innerreffere.entity.TinnerRefferee;
import com.hzdjr.hzwd.modules.innerreffere.service.TinnerReffereeService;
/**
 * 用户推荐Controller
 * @author ln
 * @version 2016-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/innerRefferee/tUser")
public class TinnerReffereeController extends BaseController {

	@Autowired
	private TinnerReffereeService tUserService;
	
	@ModelAttribute
	public TinnerRefferee get(@RequestParam(required=false) String id) {
		TinnerRefferee entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tUserService.get(id);
		}
		if (entity == null){
			entity = new TinnerRefferee();
		}
		return entity;
	}
	
//	@RequiresPermissions("refferee:tUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(TinnerRefferee tUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TinnerRefferee> page = tUserService.findPage(new Page<TinnerRefferee>(request, response), tUser); 
		List<Map<String,Object>> departments = tUserService.getDepartmentCode();
		model.addAttribute("page", page);
		model.addAttribute("tUser", tUser);
		model.addAttribute("departments", departments);
		return "modules/innerRefferee/tUserList";
	}

	@RequiresPermissions("refferee:tUser:view")
	@RequestMapping(value = "form")
	public String form(TinnerRefferee tUser, Model model) {
		model.addAttribute("tUser", tUser);
		return "modules/refferee/tUserForm";
	}
	//修改推荐人编号 	reffereeStaffId(推荐人编号) reffereerefferee(推荐人手机号也是推荐人字段内容) oldStaffId(旧的推荐人编号)
	//@RequiresPermissions("refferee:tUser:edit")
	@RequestMapping(value = "updateRefferee")
	public @ResponseBody boolean updateRefferee(String reffereeStaffId,String reffereerefferee,String oldStaffId) {
		try {
			tUserService.updateStaffId(reffereeStaffId,reffereerefferee);
			tUserService.updateRefferee(reffereeStaffId, oldStaffId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@RequiresPermissions("refferee:tUser:edit")
	@RequestMapping(value = "save")
	public String save(TinnerRefferee tUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tUser)){
			return form(tUser, model);
		}
		tUserService.save(tUser);
		addMessage(redirectAttributes, "保存用户推荐成功");
		return "redirect:"+Global.getAdminPath()+"/refferee/tUser/?repage";
	}
	
	@RequiresPermissions("refferee:tUser:edit")
	@RequestMapping(value = "delete")
	public String delete(TinnerRefferee tUser, RedirectAttributes redirectAttributes) {
		tUserService.delete(tUser);
		addMessage(redirectAttributes, "删除用户推荐成功");
		return "redirect:"+Global.getAdminPath()+"/refferee/tUser/?repage";
	}

}