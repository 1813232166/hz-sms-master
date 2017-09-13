package com.hzdjr.hzwd.modules.finance.web;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.BaseDTO;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.finance.entity.Finance;
import com.hzdjr.hzwd.modules.finance.entity.FinanceProduct;
import com.hzdjr.hzwd.modules.finance.service.FinanceProductService;
import com.hzdjr.hzwd.modules.finance.service.FinanceService;

/**
 * 出借计划管理Controller
 * @author FYP
 * @version 2017-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/finance")
public class FinanceController extends BaseController {

	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private FinanceProductService financeProductService;

	
	@Autowired
	private BaseDTO dto;
	
	@ModelAttribute
	public Finance get(@RequestParam(required=false) String id) {
		Finance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = financeService.get(id);
		}
		if (entity == null){
			entity = new Finance();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Finance finance, HttpServletRequest request, HttpServletResponse response, Model model) {
	
		model.addAttribute("finance", finance);
		/**
		 * status 总结个数
		 */
		List<Map<String, Object>> finaceStatusMapList =financeService.groupFinaceStatus(finance);
		Map<String, Integer> statusMap = new HashMap<String, Integer>();
		
		for (Map<String, Object> map : finaceStatusMapList) {
			statusMap.put(map.get("status").toString(), Integer.valueOf(map.get("statusCount").toString()));
		}
		
		if (statusMap.containsKey("2")) {
			model.addAttribute("status2", statusMap.get("2"));
		} 
		if (statusMap.containsKey("3")) {
			model.addAttribute("status3", statusMap.get("3"));
		} 
		
		Integer status4=0;
		if (statusMap.containsKey("4")) {
			status4+=statusMap.get("4");
		} 
		
		if (statusMap.containsKey("5")) {
			status4+=statusMap.get("5");
		} 
		
		model.addAttribute("status4", status4);
		
		model.addAttribute("financeProductList", financeProductService.findAllList());//产品名称
		String collectSumAmount = financeService.queryCollectSumAmount(finance);
		model.addAttribute("collectSumAmount",collectSumAmount);
		
		
		Page<Finance> page = financeService.findPage(new Page<Finance>(request, response), finance); 
		model.addAttribute("page", page);
		
		return "modules/finance/financeList";
	}

	
	@RequestMapping(value = "form")
	public String form(Finance finance, Model model) {
		
		model.addAttribute("financeProductList", financeProductService.findAllList());//产品名称
		model.addAttribute("finance", finance);
		return "modules/finance/financeForm";
	}

	@RequestMapping(value = "save")
	public String save(Finance finance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, finance)){
			return form(finance, model);
		}
		
		/**
		 * 保存
		 */
		if (StringUtils.isBlank(finance.getId())) {
			finance.setStatus("1");
			finance.setProductId(finance.getName().split("-")[0]);
			finance.setName(finance.getName().split("-")[1]);
			finance.setCreateTime(new Date());
			finance.setAlreadyCollectedAmount("0");
		}
		
		financeService.save(finance);
		addMessage(redirectAttributes, "保存出借计划成功");
		return "redirect:"+Global.getAdminPath()+"/finance/finance/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Finance finance, RedirectAttributes redirectAttributes) {
		finance.setStatus("10");
		financeService.save(finance);
		addMessage(redirectAttributes, "删除出借计划成功");
		return "redirect:"+Global.getAdminPath()+"/finance/finance/?repage";
	}
	
	/**
	 * 
	* @Title: complement 
	* @Description: 手动补标
	* @param @param finance
	* @param @param redirectAttributes
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author FYP
	 */
	@RequestMapping(value = "complement")
	public String complement(Finance finance, RedirectAttributes redirectAttributes) {
		financeService.updateFinanceStatus(finance.getId(), "8");
		addMessage(redirectAttributes, "补标成功");
		return "redirect:"+Global.getAdminPath()+"/finance/finance/?repage";
	}

	
	/**
	* 
	* @Title: queryProductDetail 
	* @Description: aJax 查询产品详情
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
		dto.setRtnFlag(financeService.isCanCreate(productId));
		
		
		/**
		 * 出借编号
		 */
		String nper=financeProduct.getNper();
		if (nper.length()==1) {
			nper="0"+nper;
		}
		String size=(Integer.valueOf(financeService.queryTodayProductListSize())+1)+"";
		String msg=DateUtils.getDate("yyyyMMdd")+nper+"-"+size;
		dto.setMsg(msg);
		return dto;
	}
	
}