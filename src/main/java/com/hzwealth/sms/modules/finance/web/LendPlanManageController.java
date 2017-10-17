package com.hzwealth.sms.modules.finance.web;

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

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.finance.entity.Finance;
import com.hzwealth.sms.modules.finance.entity.LendPlanManage;
import com.hzwealth.sms.modules.finance.service.FinanceService;
import com.hzwealth.sms.modules.finance.service.LendPlanAuditService;
import com.hzwealth.sms.modules.finance.service.LendPlanManageService;

/**
 * 出借计划管理详情Controller
 * @author FYP
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/lendPlanManage")
public class LendPlanManageController extends BaseController {

	@Autowired
	private LendPlanManageService lendPlanManageService;
	
	@Autowired
	private LendPlanAuditService lendPlanAuditService;
	
	@Autowired
	private FinanceService financeService;
	
	@ModelAttribute
	public LendPlanManage get(@RequestParam(required=false) String id) {
		LendPlanManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lendPlanManageService.get(id);
		}
		if (entity == null){
			entity = new LendPlanManage();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(LendPlanManage lendPlanManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Finance lendPlanDetail = lendPlanAuditService.getLendPlanDetail(lendPlanManage.getFinanceId());
		model.addAttribute("lendPlanDetail",lendPlanDetail);
		
		Finance finance =financeService.get(lendPlanManage.getFinanceId());
		model.addAttribute("finance",finance);
		
		//查询本金总和
	    Map<String, Object> principalInterestCount = lendPlanManageService.queryPrincipalInterest(lendPlanManage.getFinanceId());
	    model.addAttribute("principalInterestCount", principalInterestCount);
		Page<LendPlanManage> page = lendPlanManageService.findPage(new Page<LendPlanManage>(request, response), lendPlanManage); 
		model.addAttribute("page", page);
		return "modules/finance/lendPlanManageList";
	}

	/**
	 * 
	* @Title: listExport 
	* @Description: 出借计划详情导出
	* @param @param lendPlanManage
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "listExport")
	public void listExport(LendPlanManage lendPlanManage, HttpServletResponse response) {
		List<LendPlanManage> exportList = lendPlanManageService.findExportList(lendPlanManage);

		String fileName = "出借计划详情" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try{
			ExportExcel excel = new ExportExcel("出借计划详情", LendPlanManage.class).setDataList(exportList);
			excel.write(response, fileName).dispose();
		} catch (Exception e) {
			logger.error("导出出借计划详情失败", e);
		}
	}
	
	/**
	 * 
	* @Title: auditSubmit 
	* @Description: 提交审核
	* @param @param lendPlanManage
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = {"auditSubmit", ""})
	public String auditSubmit(LendPlanManage lendPlanManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		lendPlanManageService.updateFinanceStatus(lendPlanManage.getFinanceId(), "2");
		return "redirect:" + Global.getAdminPath() + "/finance/finance/";
	}
	

}