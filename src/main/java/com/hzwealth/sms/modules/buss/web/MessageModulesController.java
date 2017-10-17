package com.hzwealth.sms.modules.buss.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
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
import com.hzwealth.sms.modules.buss.entity.MessageModules;
import com.hzwealth.sms.modules.buss.service.MessageModulesService;

/**
 * 短信模板Controller
 * @author xuchenglin
 * @version 2017-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/buss/messageModules")
public class MessageModulesController extends BaseController {

	@Autowired
	private MessageModulesService messageModulesService;
	
	@ModelAttribute
	public MessageModules get(@RequestParam(required=false) String id) {
		MessageModules entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = messageModulesService.get(id);
		}
		if (entity == null){
			entity = new MessageModules();
		}
		return entity;
	}
	
	@RequiresPermissions("buss:messageModules:view")
	@RequestMapping(value = {"list", ""})
	public String list(MessageModules messageModules, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MessageModules> page = messageModulesService.findPage(new Page<MessageModules>(request, response), messageModules); 
		model.addAttribute("page", page);
		return "modules/buss/messageModulesList";
	}

	@RequiresPermissions("buss:messageModules:view")
	@RequestMapping(value = "form")
	public String form(MessageModules messageModules, Model model) {
		model.addAttribute("messageModules", messageModules);
		return "modules/buss/messageModulesForm";
	}

	@RequiresPermissions("buss:messageModules:edit")
	@RequestMapping(value = "save")
	public String save(MessageModules messageModules, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, messageModules)){
			return form(messageModules, model);
		}
		//防止注入攻击，转义html
		String messageContent = messageModules.getMessageContent();
		messageContent = messageContent == null ? null : StringEscapeUtils.unescapeHtml4(messageContent.trim());
		messageModules.setMessageContent(messageContent);
		messageModulesService.save(messageModules);
		addMessage(redirectAttributes, "保存短信模板成功");
		return "redirect:"+Global.getAdminPath()+"/buss/messageModules/?repage";
	}
	
	@RequiresPermissions("buss:messageModules:edit")
	@RequestMapping(value = "delete")
	public String delete(MessageModules messageModules, RedirectAttributes redirectAttributes) {
		messageModulesService.delete(messageModules);
		addMessage(redirectAttributes, "删除短信模板成功");
		return "redirect:"+Global.getAdminPath()+"/buss/messageModules/?repage";
	}

}