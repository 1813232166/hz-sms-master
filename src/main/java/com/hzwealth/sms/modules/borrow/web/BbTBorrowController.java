/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrow.service.BbTBorrowService;
import com.hzwealth.sms.modules.borrow.service.TBorrowService;
import com.hzwealth.sms.modules.sys.entity.User;
import com.hzwealth.sms.modules.sys.utils.UserUtils;

/**
 * 标的列表Controller
 * @author ln
 * @version 2016-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/BbtBorrow")
public class BbTBorrowController extends BaseController {

	@Autowired
	private BbTBorrowService tBorrowService;
	
	@ModelAttribute
	public TBorrow get(@RequestParam(required=false) String id) {
		TBorrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBorrowService.get(id);
		}
		if (entity == null){
			entity = new TBorrow();
		}
		return entity;
	}
	
//	@RequiresPermissions("borrow:tBorrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBorrow tBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TBorrow> page = tBorrowService.findPage(new Page<TBorrow>(request, response), tBorrow); 
		Map<String,Object> countMap = tBorrowService.selectCount();
		model.addAttribute("page", page);
		model.addAttribute("tBorrow", tBorrow);
		model.addAttribute("countMap", countMap);
		return "modules/borrow/BbtBorrowList";
	}

//	@RequiresPermissions("borrow:tBorrow:view")
	@RequestMapping(value = "form")
	public String form(TBorrow tBorrow, Model model) {
		model.addAttribute("tBorrow", tBorrow);
		return "modules/borrow/tBorrowForm";
	}

//	@RequiresPermissions("borrow:tBorrow:edit")
	@RequestMapping(value = "save")
	public String save(TBorrow tBorrow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBorrow)){
			return form(tBorrow, model);
		}
		tBorrowService.save(tBorrow);
		addMessage(redirectAttributes, "保存标的列表保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tBorrow/?repage";
	}
	
//	@RequiresPermissions("borrow:tBorrow:edit")
	@RequestMapping(value = "delete")
	public String delete(TBorrow tBorrow, RedirectAttributes redirectAttributes) {
		tBorrowService.delete(tBorrow);
		addMessage(redirectAttributes, "删除标的列表保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tBorrow/?repage";
	}
	/***
	 * 补标
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="fillBorrow")
	@ResponseBody
	public String fillBorrow(TBorrow tBorrow,HttpServletRequest request,HttpServletResponse response){
		String result="0";
		User user = UserUtils.getUser();
		try {
			result = tBorrowService.investFillBorrow(user, tBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(user.getName()+"  补标异常："+e.toString());
		}
		return renderString(response, result);
	}
	
	@RequestMapping(value="findInvestAmount")
	@ResponseBody
	public String findInvestAmount(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("borrowId", (String)request.getParameter("borrowId"));
		Map<String, Object> amount = tBorrowService.findInvestAmount(params);
		return renderString(response, amount);
	}
}