/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.web;

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
import com.hzwealth.sms.modules.borrow.entity.Borrow;
import com.hzwealth.sms.modules.borrow.service.BorrowService;
import com.hzwealth.sms.modules.sys.utils.DictUtils;

/**
 * 普享表申请列表Controller
 * @author hdj
 * @version 2016-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/borrow")
public class BorrowController extends BaseController {

	@Autowired
	private BorrowService borrowService;
	
	@ModelAttribute
	public Borrow get(@RequestParam(required=false) String id) {
		Borrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = borrowService.get(id);
		}
		if (entity == null){
			entity = new Borrow();
		}
		return entity;
	}
	
	/**
	 * 
	 * TODO	查询待审核产品列表
	 * @param borrow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("borrow:borrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(Borrow borrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> borrowCounts = borrowService.getBorrowCounts(borrow);
		if(borrowCounts!=null){
			if(borrowCounts.get("amountCounts")==null){
				borrowCounts.put("amountCounts", 0);
			}
		}
		Page<Borrow> page = borrowService.findPage(new Page<Borrow>(request, response), borrow); 
		model.addAttribute("borrowCounts", borrowCounts);
		model.addAttribute("page", page);
		return "modules/borrow/borrowList";
	}

	/**
	 * TODO	导出数据表格
	 * @param borrow
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * 
	 *    生成文件名乱码暂未处理
	 */
	
	//@RequiresPermissions("borrow:borrow:view")
    @RequestMapping(value = "export",method=RequestMethod.POST)
    public String exportFile(Borrow borrow, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "普项标待审核信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Borrow> page = borrowService.exportBorrow(new Page<Borrow>(request, response,-1), borrow); 
            List<Borrow> borrowList = page.getList();
           
            for (Borrow borrowSet : borrowList) {
            	if(borrowSet!=null){
            		//还款方式  debx等额本息 xxhb先息后本ychbx一次性还本付息
            		if(borrowSet.getPayMethod().equals("debx")){
                		borrowSet.setPayMethod("等额本息");
                	}else if(borrowSet.getPayMethod().equals("xxhb")){
                		borrowSet.setPayMethod("先息后本");
                	}else if(borrowSet.getPayMethod().equals("ychbx")){
                		borrowSet.setPayMethod("一次性还本付息");
                	}
/*                	//借款形式  xy信用借款fd房贷借款cd车贷借款
            		if(borrowSet.getBorrowtype().equals("xy")){
            			borrowSet.setBorrowtype("信用借款");
            		}else if(borrowSet.getBorrowtype().equals("fd")){
            			borrowSet.setBorrowtype("房贷借款");
            		}
            		else if(borrowSet.getBorrowtype().equals("cd")){
            			borrowSet.setBorrowtype("车贷借款");
            		}*/
            		//紧急状态2不紧急1紧急
            		if(borrowSet.getCriticalid().equals("2")){
            			borrowSet.setCriticalid("不紧急");
            		}else if(borrowSet.getCriticalid().equals("1")){
            			borrowSet.setCriticalid("紧急");
            		}
            		borrowSet.setBorrowstatus(DictUtils.getDictLabel(borrowSet.getBorrowstatus(),"BORROWSTATUS", ""));
            	}
			}
            new ExportExcel("普项标待审核信息", Borrow.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/borrow/borrow/?repage";
    }
	
	
	//@RequiresPermissions("borrow:borrow:view")
	@RequestMapping(value = "form")
	public String form(Borrow borrow, Model model) {
		model.addAttribute("borrow", borrow);
		return "modules/borrow/borrowForm";
	}

	//@RequiresPermissions("borrow:borrow:edit")
	@RequestMapping(value = "save")
	public String save(Borrow borrow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, borrow)){
			return form(borrow, model);
		}
		borrowService.save(borrow);
		addMessage(redirectAttributes, "保存普享表申请列表成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/borrow/?repage";
	}
	
	//@RequiresPermissions("borrow:borrow:edit")
	@RequestMapping(value = "delete")
	public String delete(Borrow borrow, RedirectAttributes redirectAttributes) {
		borrowService.delete(borrow);
		addMessage(redirectAttributes, "删除普享表申请列表成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/borrow/?repage";
	}

}