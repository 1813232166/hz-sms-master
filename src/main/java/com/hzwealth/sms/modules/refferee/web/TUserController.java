/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.refferee.web;

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

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.refferee.entity.TUser;
import com.hzwealth.sms.modules.refferee.service.TUserService;

/**
 * 用户推荐Controller
 * @author ln
 * @version 2016-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/refferee/tUser")
public class TUserController extends BaseController {

	@Autowired
	private TUserService tUserService;
	
	@ModelAttribute
	public TUser get(@RequestParam(required=false) String id) {
		TUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tUserService.get(id);
		}
		if (entity == null){
			entity = new TUser();
		}
		return entity;
	}
	
//	@RequiresPermissions("refferee:tUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(TUser tUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TUser> page = tUserService.findPage(new Page<TUser>(request, response), tUser); 
		model.addAttribute("page", page);
		model.addAttribute("tUser", tUser);
		return "modules/refferee/tUserList";
	}

	@RequiresPermissions("refferee:tUser:view")
	@RequestMapping(value = "form")
	public String form(TUser tUser, Model model) {
		model.addAttribute("tUser", tUser);
		return "modules/refferee/tUserForm";
	}

	@RequiresPermissions("refferee:tUser:edit")
	@RequestMapping(value = "save")
	public String save(TUser tUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tUser)){
			return form(tUser, model);
		}
		tUserService.save(tUser);
		addMessage(redirectAttributes, "保存用户推荐成功");
		return "redirect:"+Global.getAdminPath()+"/refferee/tUser/?repage";
	}
	
	@RequiresPermissions("refferee:tUser:edit")
	@RequestMapping(value = "delete")
	public String delete(TUser tUser, RedirectAttributes redirectAttributes) {
		tUserService.delete(tUser);
		addMessage(redirectAttributes, "删除用户推荐成功");
		return "redirect:"+Global.getAdminPath()+"/refferee/tUser/?repage";
	}

}