package com.hzdjr.hzwd.modules.borrowlist.web;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.borrowlist.entity.AuditBorrowList;
import com.hzdjr.hzwd.modules.borrowlist.service.AuditBorrowListService;

/**
 * 普享标集合待审核列表
 * 
 * @author LiXiang
 * @version 2017-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/borrowlist/auditBorrowList")
public class AuditBorrowListController extends BaseController {

	@Autowired
	private AuditBorrowListService auditBorrowListService;
	
	@ModelAttribute
	public AuditBorrowList get(@RequestParam(required=false) String id) {
		AuditBorrowList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = auditBorrowListService.get(id);
		}
		if (entity == null){
			entity = new AuditBorrowList();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(AuditBorrowList auditBorrowList, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> countMap=auditBorrowListService.getBorrowCounts(auditBorrowList);
		Page<AuditBorrowList> page = auditBorrowListService.findPage(new Page<AuditBorrowList>(request, response), auditBorrowList); 
		model.addAttribute("page", page);
		model.addAttribute("auditBorrowList", auditBorrowList);
		model.addAttribute("countMap", countMap);
		return "modules/borrowlist/auditBorrowList";
	}

	@RequestMapping(value = "form")
	public String form(AuditBorrowList auditBorrowList, Model model) {
		model.addAttribute("auditBorrowList", auditBorrowList);
		return "modules/borrowlist/auditBorrowListForm";
	}
	
	@RequestMapping(value = "auditform")
	public String auditForm(AuditBorrowList auditBorrowList, Model model) {
		Map<String, Object> auditDetailmap=auditBorrowListService.getAuditDetail(auditBorrowList.getBorrowListId());
		List<Map<String, Object>> subBorrowList=auditBorrowListService.getSubBorrowList(auditBorrowList.getBorrowListId());
		
		model.addAttribute("auditBorrowList", auditBorrowList);
		model.addAttribute("auditDetailmap", auditDetailmap);
		model.addAttribute("subBorrowList", subBorrowList);
		return "modules/borrowlist/auditForm";
	}
	
	@RequestMapping(value = "deleteBorrow")
	@ResponseBody
	public String deleteBorrow(String borrowId) {
		int flag=auditBorrowListService.deleteBorrow(borrowId);
		if(flag==1){
			return "yes";
		}
		return "no";
	}
	
	@RequestMapping(value="export")
	public String export(HttpServletRequest request,HttpServletResponse response,AuditBorrowList auditBorrowList,
			RedirectAttributes redirectAttributes) throws IOException {
	    List<AuditBorrowList> exportList = auditBorrowListService.export(auditBorrowList);
	    String fileName = "普享标集合待审核列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	    try {
	    	new ExportExcel("普享标集合待审核列表", AuditBorrowList.class).setDataList(exportList).write(response, fileName).dispose();
	    	return null;
	    } catch (IOException e) {
	    	addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() +"/borrow/borrowList/list";
	}

}