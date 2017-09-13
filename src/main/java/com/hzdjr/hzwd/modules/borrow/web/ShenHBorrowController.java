/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.borrow.entity.ShenHBorrow;
import com.hzdjr.hzwd.modules.borrow.service.ShenHBorrowService;

/**
 * 普享表审核未通过列表Controller
 * @author hdj
 * @version 2016-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/shenHBorrow")
public class ShenHBorrowController extends BaseController {

	@Autowired
	private ShenHBorrowService shenHBorrowService;
	
	@ModelAttribute
	public ShenHBorrow get(@RequestParam(required=false) String id) {
		ShenHBorrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shenHBorrowService.get(id);
		}
		if (entity == null){
			entity = new ShenHBorrow();
		}
		return entity;
	}
	
	/**
	 * TODO	查询审核未通过列表信息
	 * @param shenHBorrow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("borrow:shenHBorrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShenHBorrow shenHBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> shborrowCounts = shenHBorrowService.getBorrowCounts(shenHBorrow);
		if(shborrowCounts!=null){
			if(shborrowCounts.get("amountCounts")==null){
				shborrowCounts.put("amountCounts", 0);
			}
		}
		Page<ShenHBorrow> page = shenHBorrowService.findPage(new Page<ShenHBorrow>(request, response), shenHBorrow); 
		List<ShenHBorrow> list = page.getList();
		for (ShenHBorrow shenHBorrow2 : list) {
			String auditSuggest = shenHBorrowService.findAuditSuggest(shenHBorrow2.getBorrowId());
			shenHBorrow2.setAuditSuggest(auditSuggest);
		}
		model.addAttribute("shborrowCounts", shborrowCounts);
		model.addAttribute("page", page);
		return "modules/borrow/shenHBorrowList";
	}

	//@RequiresPermissions("borrow:shenHBorrow:view")
	@RequestMapping(value = "form")
	public String form(ShenHBorrow shenHBorrow, Model model) {
		model.addAttribute("shenHBorrow", shenHBorrow);
		return "modules/borrow/shenHBorrowForm";
	}

	//@RequiresPermissions("borrow:shenHBorrow:edit")
	@RequestMapping(value = "save")
	public String save(ShenHBorrow shenHBorrow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shenHBorrow)){
			return form(shenHBorrow, model);
		}
		shenHBorrowService.save(shenHBorrow);
		addMessage(redirectAttributes, "保存普享表审核未通过列表成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/shenHBorrow/?repage";
	}
	
	//@RequiresPermissions("borrow:shenHBorrow:edit")
	@RequestMapping(value = "delete")
	public String delete(ShenHBorrow shenHBorrow, RedirectAttributes redirectAttributes) {
		shenHBorrowService.delete(shenHBorrow);
		addMessage(redirectAttributes, "删除普享表审核未通过列表成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/shenHBorrow/?repage";
	}

}