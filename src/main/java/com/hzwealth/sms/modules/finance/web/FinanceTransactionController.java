/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.finance.entity.FinanceInvest;
import com.hzwealth.sms.modules.finance.entity.FinanceTransaction;
import com.hzwealth.sms.modules.finance.service.FinanceTransactionService;
import com.hzwealth.sms.modules.repaybillplan.entity.TBorrowRepayBillplan;
import com.hzwealth.sms.modules.sys.entity.Dict;

/**
 * 出借计划交易Controller
 * @author ZHF
 * @version 2017-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/financeTransaction")
public class FinanceTransactionController extends BaseController {

	@Autowired
	private FinanceTransactionService financeTransactionService;
	
	@ModelAttribute
	public FinanceTransaction get(@RequestParam(required=false) String id) {
		FinanceTransaction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = financeTransactionService.get(id);
		}
		if (entity == null){
			entity = new FinanceTransaction();
		}
		return entity;
	}
	
//	@RequiresPermissions("financetransaction:financeTransaction:view")
	@RequestMapping(value = {"list", ""})
	public String list(FinanceTransaction financeTransaction, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FinanceTransaction> queryPage = new Page<FinanceTransaction>(request, response);
		queryPage.setOrderBy("capitalInvestTime, capitalCode");
		Page<FinanceTransaction> page = financeTransactionService.findPage(queryPage, financeTransaction); 
		model.addAttribute("page", page);
		
		List<Dict> financeProducts = financeTransactionService.findFinanceProducts();
		model.addAttribute("financeProducts", financeProducts);
		List<Dict> capitalStatuses = financeTransactionService.findCapitalStatuses();
		model.addAttribute("capitalStatuses", capitalStatuses);
		
		return "modules/finance/financeTransactionList";
	}
	
	/**
	 * 
	 * @Title: listExport   
	 * @Description: 导出出借计划交易单
	 * @param financeTransaction
	 * @param request
	 * @param response
	 * @param model
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date Jun 30, 2017 6:35:12 PM
	 */
	@RequestMapping(value = "listExport")
	public void listExport(FinanceTransaction financeTransaction, HttpServletResponse response) {
		List<FinanceTransaction> exportList = financeTransactionService.findExportList(financeTransaction);

		String fileName = "出借计划交易单" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try{
			ExportExcel excel = new ExportExcel("出借计划交易单", FinanceTransaction.class).setDataList(exportList);
			excel.write(response, fileName).dispose();
		} catch (Exception e) {
			logger.error("导出出借计划交易单失败", e);
		}
	}

//	@RequiresPermissions("financetransaction:financeTransaction:view")
	@RequestMapping(value = "form")
	public String form(FinanceTransaction financeTransaction, Model model) {
		model.addAttribute("financeTransaction", financeTransaction);
		return "modules/finance/financeTransactionForm";
	}

//	@RequiresPermissions("financetransaction:financeTransaction:edit")
	@RequestMapping(value = "save")
	public String save(FinanceTransaction financeTransaction, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, financeTransaction)){
			return form(financeTransaction, model);
		}
		financeTransactionService.save(financeTransaction);
		addMessage(redirectAttributes, "保存出借计划交易成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeTransaction/?repage";
	}
	
//	@RequiresPermissions("financetransaction:financeTransaction:edit")
	@RequestMapping(value = "delete")
	public String delete(FinanceTransaction financeTransaction, RedirectAttributes redirectAttributes) {
		financeTransactionService.delete(financeTransaction);
		addMessage(redirectAttributes, "删除出借计划交易成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeTransaction/?repage";
	}
	
	@RequestMapping(value = "orderDetail")
	public ModelAndView orderDetail(String capitalId){
		FinanceTransaction financeTransaction = financeTransactionService.get(capitalId);
		List<Dict> capitalStatuses = financeTransactionService.findCapitalStatuses();
		List<FinanceInvest> financeInvests = financeTransactionService.findFinanceInvests(capitalId);
		List<Dict> investStatuses = financeTransactionService.findInvestStatuses();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("modules/finance/financeTransactionDetail");
		modelAndView.addObject("financeTransaction", financeTransaction);
		modelAndView.addObject("capitalStatuses", capitalStatuses);
		modelAndView.addObject("financeInvests", financeInvests);
		modelAndView.addObject("investStatuses", investStatuses);
		return modelAndView;
	}
	
	@RequestMapping(value = "expectedRepayBillplans", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TBorrowRepayBillplan> expectedRepayBillplans(String investId) {
		List<TBorrowRepayBillplan> expectedRepayBillplans = financeTransactionService.findExpectedRepayBillplans(investId);
		return expectedRepayBillplans;
	}

}