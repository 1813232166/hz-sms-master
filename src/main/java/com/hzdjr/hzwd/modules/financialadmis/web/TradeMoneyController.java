package com.hzdjr.hzwd.modules.financialadmis.web;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.financialadmis.entity.CapitalVo;
import com.hzdjr.hzwd.modules.financialadmis.entity.RechargeVo;
import com.hzdjr.hzwd.modules.financialadmis.entity.TenderVo;
import com.hzdjr.hzwd.modules.financialadmis.entity.WithdrawVo;
import com.hzdjr.hzwd.modules.financialadmis.service.TradeMoneyService;

@Controller
@RequestMapping(value = "${adminPath}/financialadmis/tradeMoney")
public class TradeMoneyController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(TradeMoneyController.class);

	@Autowired
	private TradeMoneyService tradeMoneyService;


	/**
	 * 充值列表
	 * 用户手机号、充值时间、充值金额（元）、手续费（元）、手续费承担方类型、平台交易流水号
	 */
	@RequestMapping("/recharge")
	public String rechargeList(@ModelAttribute RechargeVo rechargeVo,HttpServletRequest request,HttpServletResponse response, Model model){

		String userMobile = request.getParameter("userMobile");
		String tradeNo = request.getParameter("tradeNo");
		String feesBearer = request.getParameter("feesBearer");
		String beginTime = request.getParameter("beginTime");
		String overTime = request.getParameter("overTime");

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userMobile", userMobile);
		map.put("tradeNo", tradeNo);
		map.put("feesBearer", feesBearer);
		map.put("beginTime", beginTime);
		map.put("overTime", overTime);
		Page<RechargeVo> page = tradeMoneyService.findRechangeList(new Page<RechargeVo>(request, response),rechargeVo);

		model.addAttribute("map",map);
		model.addAttribute("page",page);
		model.addAttribute("rechargeVo",rechargeVo);
		return "modules/financialadmis/rechargeList";
	}

	/**
	 * 提现列表
	 */
	@RequestMapping("/withdraw")
	public String withdrawList(WithdrawVo withdrawVo,HttpServletRequest request,HttpServletResponse response, Model model) {
		Page<WithdrawVo> page = tradeMoneyService.findWithdrawList(new Page<WithdrawVo>(request, response), withdrawVo);
		String beginTime = withdrawVo.getBeginTime();
		String overTime = withdrawVo.getOverTime();
		Date finBeginTime = withdrawVo.getFinBeginTime();
		Date finendTime = withdrawVo.getFinEndTime();
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("overTime", overTime);
		model.addAttribute("finBeginTime", finBeginTime);
		model.addAttribute("finEndTime", finendTime);
		model.addAttribute("page", page);
		model.addAttribute("withdrawVo", withdrawVo);
		return "modules/financialadmis/withdrawList";
	}
	/**
	 * 投标列表
	 */
	@RequestMapping("/tender")
	public String tenderList(@ModelAttribute TenderVo tenderVo,HttpServletRequest request,HttpServletResponse response, Model model) {
		Page<TenderVo> page = tradeMoneyService.findTenderList(new Page<TenderVo>(request, response), tenderVo);
		Date beginTime = tenderVo.getBeginTime();
		Date overTime = tenderVo.getOverTime();
		Date finBeginTime = tenderVo.getFinBeginTime();
		Date finEndTime = tenderVo.getFinEndTime();
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("overTime", overTime);
		model.addAttribute("finBeginTime", finBeginTime);
		model.addAttribute("finEndTime", finEndTime);
		model.addAttribute("page", page);
		model.addAttribute("tenderVo", tenderVo);
		return "modules/financialadmis/tenderList";
	}
	/**
	 * 放款列表
	 */
	@RequestMapping("/loanList")
	public String loanList(CapitalVo capitalVo,HttpServletRequest request,HttpServletResponse response, Model model) {
		Page<CapitalVo> page = tradeMoneyService.findLoanList(new Page<CapitalVo>(request, response), capitalVo);
		model.addAttribute("page", page);
		model.addAttribute("refundVo", capitalVo);
		return "modules/financialadmis/loanList";
	}
	/**
	 * 还款列表
	 */
	@RequestMapping("/repay")
	public String repayList(CapitalVo capitalVo,HttpServletRequest request,HttpServletResponse response, Model model) {
		Page<CapitalVo> page = tradeMoneyService.findRepayList(new Page<CapitalVo>(request, response), capitalVo);
		model.addAttribute("page", page);
		model.addAttribute("refundVo", capitalVo);
		return "modules/financialadmis/repayList";
	}
	/**
	 * 回款列表
	 */
	@RequestMapping("/paymentList")
	public String paymentList(CapitalVo capitalVo,HttpServletRequest request,HttpServletResponse response, Model model) {
		Page<CapitalVo> page = tradeMoneyService.findPaymentList(new Page<CapitalVo>(request, response), capitalVo);
		model.addAttribute("page", page);
		model.addAttribute("refundVo", capitalVo);
		return "modules/financialadmis/paymentList";
	}

	/**
	 *
	 * 导出充值列表(带条件)
	 * @return
	 */
	@RequestMapping(value="/exportRecharge")
	public String exportRecharge(String userMobile,String tradeNo,String beginTime,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		String overTime = request.getParameter("overTime");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userMobile", userMobile);
		paramMap.put("tradeNo", tradeNo);
		paramMap.put("beginTime", beginTime);
		paramMap.put("overTime", overTime);

		List<RechargeVo> RechargeList = tradeMoneyService.exportRecharge(paramMap);
		String fileName = "资金充值列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("资金充值列表", RechargeVo.class).setDataList(RechargeList);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tradeMoney/recharge";
	}


    /**
     *
     * TODO  导出提现列表(带条件)
     * @param withdrawVo
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/exportWithdraw")
    public String exportWithdraw(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String userMobile = request.getParameter("userMobile");
		String tradeNo = request.getParameter("tradeNo");
		String beginTime = request.getParameter("beginTime");
		String overTime = request.getParameter("overTime");
		String finBeginTime = request.getParameter("finBeginTime");
		String finEndTime = request.getParameter("finEndTime");

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userMobile", userMobile);
		map.put("tradeNo", tradeNo);
		map.put("overTime", overTime);
		map.put("finBeginTime", finBeginTime);
		map.put("finEndTime", finEndTime);
		map.put("beginTime", beginTime);
    	try {
            String fileName = "提现信息列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<WithdrawVo> widthList  = tradeMoneyService.exportWithdraw(map);
            new ExportExcel("提现信息列表", WithdrawVo.class).setDataList(widthList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tradeMoney/withdraw";
    }

    /**
     *
     * TODO  导出标的列表(带条件)
     * @param withdrawVo
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/exportTender")
    public String exportTender(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String userMobile = request.getParameter("userMobile");
		String tradeNo = request.getParameter("tradeNo");
		String beginTime = request.getParameter("beginTime");
		String overTime = request.getParameter("overTime");
		String finBeginTime = request.getParameter("finBeginTime");
		String finEndTime = request.getParameter("finEndTime");
		System.out.println(finBeginTime);
		System.out.println(finEndTime);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userMobile", userMobile);
		map.put("tradeNo", tradeNo);
		map.put("overTime", overTime);
		map.put("finBeginTime", finBeginTime);
		map.put("finEndTime", finEndTime);
		map.put("beginTime", beginTime);
    	try {
            String fileName = "标的信息列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<TenderVo> tenderVoList  = tradeMoneyService.exporttenderVo(map);
            new ExportExcel("标的信息列表", TenderVo.class).setDataList(tenderVoList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tradeMoney/tender";
    }

    /**
     *
     * TODO  导出放款列表(带条件)
     * @param withdrawVo
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/exportLoan")
    public String exportLoan(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String userMobile = request.getParameter("userMobile");
		String tradeNo = request.getParameter("tradeNo");
		String beginTime = request.getParameter("beginTime");
		String overTime = request.getParameter("overTime");

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userMobile", userMobile);
		map.put("tradeNo", tradeNo);
		map.put("overTime", overTime);
		map.put("beginTime", beginTime);
    	try {
            String fileName = "放款信息列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<CapitalVo> capitalVoList  = tradeMoneyService.exportLoan(map);
            new ExportExcel("放款信息列表", CapitalVo.class, 1, 8).setDataList(capitalVoList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tradeMoney/loanList";
    }
    /**
    *
    * TODO  导出还款列表(带条件)
    * @param withdrawVo
    * @param request
    * @param response
    * @param redirectAttributes
    * @return
    */
   @RequestMapping(value = "/exportRepay")
   public String exportRepay(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String userMobile = request.getParameter("userMobile");
		String repayType = request.getParameter("repayType");
		String loanNumber = request.getParameter("loanNumber");
		String tradeNo = request.getParameter("tradeNo");
		String beginTime = request.getParameter("beginTime");
		String overTime = request.getParameter("overTime");

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userMobile", userMobile);
		map.put("repayType", repayType);
		map.put("loanNumber", loanNumber);
		map.put("tradeNo", tradeNo);
		map.put("overTime", overTime);
		map.put("beginTime", beginTime);
   	try {
           String fileName = "还款信息列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           List<CapitalVo> capitalVoList  = tradeMoneyService.exportRepay(map);
           new ExportExcel("还款信息列表", CapitalVo.class, 1, 7).setDataList(capitalVoList).write(response, fileName).dispose();
   		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tradeMoney/repay";
   }
   /**
   *
   * TODO  导出回款列表(带条件)
   * @param withdrawVo
   * @param request
   * @param response
   * @param redirectAttributes
   * @return
   */
  @RequestMapping(value = "/exportPayment")
  public String exportPayment(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String userMobile = request.getParameter("userMobile");
		String loanNumber = request.getParameter("loanNumber");
		String tradeNo = request.getParameter("tradeNo");
		String beginTime = request.getParameter("beginTime");
		String overTime = request.getParameter("overTime");

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userMobile", userMobile);
		map.put("loanNumber", loanNumber);
		map.put("tradeNo", tradeNo);
		map.put("overTime", overTime);
		map.put("beginTime", beginTime);
  	try {
          String fileName = "回款信息列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
          List<CapitalVo> capitalVoList  = tradeMoneyService.exportPayment(map);
          new ExportExcel("回款信息列表", CapitalVo.class, 1, 14).setDataList(capitalVoList).write(response, fileName).dispose();
  		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tradeMoney/paymentList";
  }

}
