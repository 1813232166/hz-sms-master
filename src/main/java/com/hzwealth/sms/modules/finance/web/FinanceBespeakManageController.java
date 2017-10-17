/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.finance.web;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.BaseDTO;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.finance.entity.FinanceBespeakManage;
import com.hzwealth.sms.modules.finance.entity.FinanceProduct;
import com.hzwealth.sms.modules.finance.entity.TFinanceBespeak;
import com.hzwealth.sms.modules.finance.service.FinanceBespeakManageService;
import com.hzwealth.sms.modules.finance.service.FinanceProductService;
import com.hzwealth.sms.modules.finance.service.TFinanceBespeakService;

/**
 * 预约管理Controller
 * @author FYP
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/financeBespeakManage")
public class FinanceBespeakManageController extends BaseController {

	@Autowired
	private FinanceBespeakManageService financeBespeakManageService;
	
	@Autowired
	private FinanceProductService financeProductService;
	
	@Autowired
	private TFinanceBespeakService tFinanceBespeakService;
	
	@Autowired
	private BaseDTO dto;
	
	@ModelAttribute
	public FinanceBespeakManage get(@RequestParam(required=false) String id) {
		FinanceBespeakManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = financeBespeakManageService.get(id);
		}
		if (entity == null){
			entity = new FinanceBespeakManage();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(FinanceBespeakManage financeBespeakManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		model.addAttribute("financeBespeakManage", financeBespeakManage);
		model.addAttribute("financeProductList", financeProductService.findAllList());//产品名称
		
		Page<FinanceBespeakManage> page = financeBespeakManageService.findPage(new Page<FinanceBespeakManage>(request, response), financeBespeakManage); 
		model.addAttribute("page", page);
		return "modules/finance/financeBespeakManageList";
	}

	@RequestMapping(value = "form")
	public String form(FinanceBespeakManage financeBespeakManage, Model model) {
		model.addAttribute("financeProductList", financeProductService.findListNoXS());//产品名称
		model.addAttribute("financeBespeakManage", financeBespeakManage);
		return "modules/finance/financeBespeakManageForm";
	}

	@RequestMapping(value = "save")
	public String save(FinanceBespeakManage financeBespeakManage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, financeBespeakManage)){
			return form(financeBespeakManage, model);
		}
		

		/**
		 * 保存
		 */
		if (StringUtils.isBlank(financeBespeakManage.getId())) {
			financeBespeakManage.setStatus("1");
			financeBespeakManage.setProductId(financeBespeakManage.getName().split("-")[0]);
			financeBespeakManage.setName(financeBespeakManage.getName().split("-")[1]);
			financeBespeakManage.setCreateTime(new Date());
			financeBespeakManage.setAlreadyCollectedAmount("0");
		}
		
		financeBespeakManageService.save(financeBespeakManage);
		addMessage(redirectAttributes, "保存预约管理成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeBespeakManage/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(FinanceBespeakManage financeBespeakManage, RedirectAttributes redirectAttributes) {
		financeBespeakManage.setStatus("6");
		financeBespeakManageService.save(financeBespeakManage);
		addMessage(redirectAttributes, "删除预约管理成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeBespeakManage/?repage";
	}

	
	/**
	 * 
	* @Title: queryProductDetail 
	* @Description: 查询产品详情
	* @param @param idName
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return BaseDTO    返回类型 
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "/queryProductDetail", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody BaseDTO queryProductDetail(String idName ,HttpServletRequest request,
			HttpServletResponse response){
		
		String productId =idName.split("-")[0];
		/**
		 * 查询产品详情
		 */
		FinanceProduct financeProduct=financeProductService.get(productId);
		
		Map<String, FinanceProduct> map =new HashMap<String, FinanceProduct>();
		map.put("financeProduct", financeProduct);
		dto.setMap(map);
		
		dto.setRtnFlag(financeBespeakManageService.isCanCreate(productId));
		
		/**
		 * 出借编号
		 */
		String nper=financeProduct.getNper();
		if (nper.length()==1) {
			nper="0"+nper;
		}
		String size=(Integer.valueOf(financeBespeakManageService.queryTodayProductListSize())+1)+"";
		String msg=DateUtils.getDate("yyyyMMdd")+nper+"-"+size;
		dto.setMsg(msg);
		
		return dto;
	}
	
	/**
	 * 
	* @Title: stop 
	* @Description: 预约计划停用
	* @param @param financeBespeakManage
	* @param @param redirectAttributes
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "stop")
	public String stop(FinanceBespeakManage financeBespeakManage, RedirectAttributes redirectAttributes) {
		financeBespeakManage.setStatus("5");
		financeBespeakManageService.save(financeBespeakManage);
		addMessage(redirectAttributes, "禁用成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeBespeakManage/?repage";
	}
	
	/**
	 * 
	* @Title: open 
	* @Description: 预约计划开启
	* @param @param financeBespeakManage
	* @param @param redirectAttributes
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "open")
	public String open(FinanceBespeakManage financeBespeakManage, RedirectAttributes redirectAttributes) {
		financeBespeakManage.setStatus("4");
		financeBespeakManage.setEnableTime(new Date());
		financeBespeakManageService.save(financeBespeakManage);
		addMessage(redirectAttributes, "开启成功");
		return "redirect:"+Global.getAdminPath()+"/finance/financeBespeakManage/?repage";
	}
	
	
	/**
	 * 
	* @Title: financeBespeakManageDetail 
	* @Description: 查看预约详情
	* @param @param request
	* @param @param id
	* @param @param model
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author FYP
	 */
	@RequestMapping("/financeBespeakManageDetail")
	public String financeBespeakManageDetail(HttpServletRequest request, String id, Model model) {
		
		FinanceBespeakManage financeBespeakManage=financeBespeakManageService.get(id);
		model.addAttribute("financeBespeakManage",financeBespeakManage);
		
		TFinanceBespeak bespeakDetail = tFinanceBespeakService.bespeakDetail(id);
		model.addAttribute("bespeakDetail",bespeakDetail);
		return "modules/finance/financeBespeakManageDetail";
	}
	
	/**
	 * 	
	* @Title: auditSubmit 
	* @Description: 更改审核状态
	* @param @param financeBespeakManage
	* @param @param redirectAttributes
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "auditSubmit")
	public String auditSubmit(FinanceBespeakManage financeBespeakManage, RedirectAttributes redirectAttributes) {
		financeBespeakManageService.updateFinanceBespeakStatus(financeBespeakManage.getId(), "2");
		return "redirect:"+Global.getAdminPath()+"/finance/financeBespeakManage/?repage";
	}
	
}