package com.hzdjr.hzwd.modules.finance.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.finance.entity.Finance;
import com.hzdjr.hzwd.modules.finance.service.FinanceProductService;
import com.hzdjr.hzwd.modules.finance.service.LendPlanAuditService;

/**
 * 
 *
 * Description: 出借计划审核
 *
 * @author huangdegui
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年6月29日    Administrator       1.0        1.0 Version 
 * </pre>
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/lendPlanAudit")
public class LendPlanAuditController extends BaseController {
	@Autowired
	private LendPlanAuditService lendPlanAuditService;
	
	@Autowired
	private FinanceProductService financeProductService;
	/**
	 * 
	 * Description: 出借计划审核列表展示
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月28日 下午5:31:54
	 */
	@RequestMapping(value = {"list", ""})
	public String lendPlanAuditList(Finance financeLend, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Finance> pageLend = lendPlanAuditService.findPage(new Page<Finance>(request, response), financeLend); 
		model.addAttribute("pageLend", pageLend);
		model.addAttribute("financeLend", financeLend);
		model.addAttribute("financeProductList", financeProductService.findAllList());//产品名称
		return "modules/finance/lendPlanAuditList";
	}
	/**
	 * 
	 * Description: 出借计划审核-查看
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月28日 下午5:32:09
	 */
	@RequestMapping("/lendPlanDetail")
	public String lendPlanDetail(HttpServletRequest request, String id, Model model) {
		Finance lendPlanDetail = lendPlanAuditService.getLendPlanDetail(id);
		model.addAttribute("lendPlanDetail",lendPlanDetail);
		return "modules/finance/lendPlanDetail";
	}
	/**
	 * 
	 * Description: -出借计划审核-审核
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年6月28日 下午5:45:02
	 */
	@ResponseBody
	@RequestMapping("/lendPlanAudit")
	public boolean lendPlanAudit( String finance_id ,String auditRadio,String auditArea,HttpServletRequest request) {
		
		try {
			lendPlanAuditService.lendPlanAudit(finance_id,auditRadio,auditArea);
			lendPlanAuditService.updateFinanceStatus(finance_id, auditRadio);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
