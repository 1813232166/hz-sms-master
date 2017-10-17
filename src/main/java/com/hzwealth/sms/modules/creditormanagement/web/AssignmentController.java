package com.hzwealth.sms.modules.creditormanagement.web;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * Copyright &copy; 2016
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;
import com.hzwealth.sms.modules.borrow.service.LoanFundService;
import com.hzwealth.sms.modules.creditormanagement.entity.AssignmentVo;
import com.hzwealth.sms.modules.creditormanagement.service.AssignmentService;
import com.hzwealth.sms.modules.repaybillplan.entity.TBorrowRepayBillplan;
/**
 * 转让列表Controller
 * @author HDG
 * @version 2017-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/creditormanagement/assignment")
public class AssignmentController extends BaseController {
	
	@Autowired
	private AssignmentService assignmentService;
	
	@Autowired
	private LoanFundService loanFundService;
	
	@RequestMapping(value = {"list", ""})
	public String list(AssignmentVo assignmentVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		//受让人数
		Map<String, Object> transferNumberMap = assignmentService.getTransferNumber();
		if(transferNumberMap==null || transferNumberMap.size()<0){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("transfernumber", "0");
			model.addAttribute("transferNumberMap", map);
		}else{
			model.addAttribute("transferNumberMap", transferNumberMap);
		}
		
		//受让人次
		Map<String, Object> transferPersonTimeMap = assignmentService.getTransferPersonTime();
		if(transferPersonTimeMap==null || transferPersonTimeMap.size()<0){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("transferpersontime", "0");
			model.addAttribute("transferPersonTimeMap", map);
		}else{
			model.addAttribute("transferPersonTimeMap", transferPersonTimeMap);
		}
		//受让总额
		Map<String, Object> transferMoneySumMap = assignmentService.getTransferMoneySum();
		if(transferMoneySumMap==null || transferMoneySumMap.size()<0){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("transfermoneysum", "0");
			model.addAttribute("transferMoneySumMap", map);
		}else{
			model.addAttribute("transferMoneySumMap", transferMoneySumMap);
		}
		Page<AssignmentVo> page = assignmentService.findPage(new Page<AssignmentVo>(request, response), assignmentVo); 
		model.addAttribute("page", page);
		model.addAttribute("assignmentVo", assignmentVo);
		return "modules/creditormanagement/assignmentList";
	}
	/**
	 * 
	 * Description: 债权名称页面跳转
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年8月4日 下午8:05:20
	 */
	@RequestMapping("assignmentListDetails")
	public String assignmentListDetails(AssignmentVo assignmentVo,String id,String tinvestId,String loannumber,String methodType,HttpServletRequest request,HttpServletResponse response,Model model) {
		AssignmentVo assignmentDeails = assignmentService.getAssignmentDeails(id,tinvestId);
		assignmentDeails.setTinvestId(tinvestId);
		model.addAttribute("assignmentDeails", assignmentDeails);
		TBorrowBillplan billplan = new TBorrowBillplan();
		billplan.setApplyId(loannumber);
		AssignmentVo ass=new AssignmentVo();
		ass.setBorrowId(assignmentDeails.getBorrowId());
		//转让记录
		Page<AssignmentVo> transfers = assignmentService.queryTransferByBorrowId(new Page<AssignmentVo>(request, response),ass);
		model.addAttribute("transfers", transfers);
		model.addAttribute("transfersSum", transfers.getList().size());
		//还款计划
		Page<TBorrowBillplan> billPlans = loanFundService.queryBilPlanByBorrowId(new Page<TBorrowBillplan>(request, response),billplan);
		model.addAttribute("billPlans", billPlans);
		model.addAttribute("methodType",methodType);
		return "modules/creditormanagement/assignmentListDetails";
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
		Page<TBorrowRepayBillplan> repayBillplans = assignmentService.queryRepayBillplanByInvestId(new Page<TBorrowRepayBillplan>(request, response),trb);
		model.addAttribute("repayBillplans", repayBillplans);
		return "modules/creditormanagement/assignmentRepayBillplans";
	}
}
