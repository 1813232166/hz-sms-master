/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hzwealth.sms.modules.finance.entity.FinanceTransactionS;
import com.hzwealth.sms.modules.finance.service.FinanceTransactionSService;
import com.hzwealth.sms.modules.sys.entity.Dict;

/**
 * 全部交易单Controller
 * @author XJin
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/financetransactions/financeTransaction")
public class FinanceTransactionSController extends BaseController {

	@Autowired
	private FinanceTransactionSService financeTransactionSService;
	
	@ModelAttribute
	public FinanceTransactionS get(@RequestParam(required=false) String id) {
		FinanceTransactionS entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = financeTransactionSService.get(id);
		}
		if (entity == null){
			entity = new FinanceTransactionS();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(FinanceTransactionS financeTransactions, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FinanceTransactionS> page = financeTransactionSService.findPage(new Page<FinanceTransactionS>(request, response), financeTransactions); 
		model.addAttribute("page", page);
		List<Dict> productType = new ArrayList<Dict>();
		productType.add(new Dict("1", "出借计划"));
		productType.add(new Dict("2", "普享标集合"));	
		model.addAttribute("productType", productType);
		List<Dict> capitalStatuses = new ArrayList<Dict>();
		capitalStatuses.add(new Dict("1", "投资成功"));
		capitalStatuses.add(new Dict("-1", "投资失败"));
		capitalStatuses.add(new Dict("beforeBack", "收益中"));
		capitalStatuses.add(new Dict("7", "退出中"));
		capitalStatuses.add(new Dict("10", "退出失败"));
		capitalStatuses.add(new Dict("4", "已结束"));
/*		capitalStatuses.add(new Dict("8", "投资成功"));
		capitalStatuses.add(new Dict("9", "投资失败"));
		capitalStatuses.add(new Dict("10", "退出失败"));*/
		model.addAttribute("capitalStatuses", capitalStatuses);
		model.addAttribute("financeTransactions",financeTransactions);
		return "modules/finance/financeTransactionSList";
	}

	@RequestMapping(value = "form")
	public String form(FinanceTransactionS financeTransaction, Model model) {
		model.addAttribute("financeTransaction", financeTransaction);
		return "modules/finance/financeTransactionSForm";
	}

	@RequestMapping(value = "save")
	public String save(FinanceTransactionS financeTransaction, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, financeTransaction)){
			return form(financeTransaction, model);
		}
		financeTransactionSService.save(financeTransaction);
		addMessage(redirectAttributes, "保存全部交易单成功");
		return "redirect:"+Global.getAdminPath()+"/financetransactions/financeTransaction/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(FinanceTransactionS financeTransaction, RedirectAttributes redirectAttributes) {
		financeTransactionSService.delete(financeTransaction);
		addMessage(redirectAttributes, "删除全部交易单成功");
		return "redirect:"+Global.getAdminPath()+"/financetransactions/financeTransaction/?repage";
	}

}