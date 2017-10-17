package com.hzwealth.sms.modules.creditormanagement.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;
import com.hzwealth.sms.modules.borrow.service.LoanFundService;
import com.hzwealth.sms.modules.creditormanagement.entity.CreditorVo;
import com.hzwealth.sms.modules.creditormanagement.service.CreditorService;
import com.hzwealth.sms.modules.repaybillplan.entity.TBorrowRepayBillplan;

/**
 * 债权列表Controller
 * @author HDG
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/creditormanagement/creditor")
public class CreditorController extends BaseController{

	@Autowired
	private CreditorService creditorService;
	
	@Autowired
	private LoanFundService loanFundService;
	
	@RequestMapping(value = {"list", ""})
	public String list(CreditorVo creditorVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		//债权数量
		Map<String, Object> creditorNumMap = creditorService.getCreditorNum();
		if(creditorNumMap==null || creditorNumMap.size()<0){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("creditorNum", "0");
			model.addAttribute("creditorNumMap", map);
		}else{
			model.addAttribute("creditorNumMap", creditorNumMap);
		}
		//债权价值总额
		Map<String, Object> creditorValueSumMap = creditorService.getCreditorValueSum();
		if(creditorValueSumMap==null || creditorValueSumMap.size()<0){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("sumAmount", "0");
			model.addAttribute("creditorValueSumMap", map);
		}else{
			model.addAttribute("creditorValueSumMap", creditorValueSumMap);
		}
		Page<CreditorVo> page = creditorService.findPage(new Page<CreditorVo>(request, response), creditorVo); 
		model.addAttribute("page", page);
		model.addAttribute("creditorVo", creditorVo);
		return "modules/creditormanagement/creditorList";
	}
	/**
	 * 
	 * Description: 债权名称页面跳转
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年8月2日 下午4:04:12
	 */
	@RequestMapping("creditorListDetails")
	public String creditorListDetails(CreditorVo creditorVo,String id,String tinvestId,String loannumber,String methodType,HttpServletRequest request,HttpServletResponse response,Model model) {
		CreditorVo creditorDeails = creditorService.getCreditorDeails(id,tinvestId);
		creditorDeails.setTinvestId(tinvestId);
		model.addAttribute("creditorDeails", creditorDeails);
		TBorrowBillplan billplan = new TBorrowBillplan();
		billplan.setApplyId(loannumber);
		CreditorVo cv=new CreditorVo();
		cv.setBorrowId(creditorDeails.getBorrowId());
		//转让记录
		Page<CreditorVo> transfers = creditorService.queryTransferByBorrowId(new Page<CreditorVo>(request, response),cv);
		model.addAttribute("transfers", transfers);
		model.addAttribute("transfersSum", transfers.getList().size());
		//还款计划
		Page<TBorrowBillplan> billPlans = loanFundService.queryBilPlanByBorrowId(new Page<TBorrowBillplan>(request, response),billplan);
		model.addAttribute("billPlans", billPlans);
		model.addAttribute("methodType",methodType);
		return "modules/creditormanagement/creditorListDetails";
     }
	/**
	 * 
	 * Description: 回款计划
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年8月4日 下午4:37:05
	 */
	@RequestMapping("repayBillplan")
	public String repayBillplan(TBorrowRepayBillplan trb,String investId,HttpServletRequest request,HttpServletResponse response,Model model) {
		trb=new TBorrowRepayBillplan();
		trb.setInvestid(investId);
		model.addAttribute("trb", trb);
		Page<TBorrowRepayBillplan> repayBillplans = creditorService.queryRepayBillplanByInvestId(new Page<TBorrowRepayBillplan>(request, response),trb);
		model.addAttribute("repayBillplans", repayBillplans);
		return "modules/creditormanagement/creditorRepayBillplans";
	}
}
