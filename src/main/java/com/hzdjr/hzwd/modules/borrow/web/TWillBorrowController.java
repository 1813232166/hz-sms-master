package com.hzdjr.hzwd.modules.borrow.web;

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
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.borrow.entity.TWillBorrow;
import com.hzdjr.hzwd.modules.borrow.service.TWillBorrowService;

import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;

/**
 * 借款意向表Controller
 * @author xuchenglin
 * @version 2017-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/tWillBorrow")
public class TWillBorrowController extends BaseController {

	@Autowired
	private TWillBorrowService tWillBorrowService;
	
	@ModelAttribute
	public TWillBorrow get(@RequestParam(required=false) String id) {
		TWillBorrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tWillBorrowService.get(id);
		}
		if (entity == null){
			entity = new TWillBorrow();
		}
		return entity;
	}
	
//	@RequiresPermissions("borrow:tWillBorrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(TWillBorrow tWillBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TWillBorrow> page = tWillBorrowService.findPage(new Page<TWillBorrow>(request, response), tWillBorrow); 
		model.addAttribute("page", page);
		model.addAttribute("tWillBorrow", tWillBorrow);
		return "modules/borrow/tWillBorrowList";
	}

//	@RequiresPermissions("borrow:tWillBorrow:view")
	@RequestMapping(value = "form")
	public String form(TWillBorrow tWillBorrow, Model model) {
		model.addAttribute("tWillBorrow", tWillBorrow);
		return "modules/borrow/tWillBorrowForm";
	}

//	@RequiresPermissions("borrow:tWillBorrow:edit")
	@RequestMapping(value = "save")
	public String save(TWillBorrow tWillBorrow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tWillBorrow)){
			return form(tWillBorrow, model);
		}
		tWillBorrowService.save(tWillBorrow);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tWillBorrow/?repage";
	}
	
  @RequestMapping(value = "export", method=RequestMethod.POST)
  public String exportFile(TWillBorrow tWillBorrow, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
    try {
      String fileName = "借款意向" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
      Page<TWillBorrow> page = tWillBorrowService.findPage(new Page<TWillBorrow>(request, response, -1), tWillBorrow);
      new ExportExcel("借款意向", TWillBorrow.class).setDataList(page.getList()).write(response, fileName).dispose();
      return null;
    } catch (Exception e) {
      addMessage(redirectAttributes, "导出借款意向失败！失败信息：" + e.getMessage());
    }
    return "redirect:" + adminPath + "/borrow/tWillBorrow/?repage";
  }

}