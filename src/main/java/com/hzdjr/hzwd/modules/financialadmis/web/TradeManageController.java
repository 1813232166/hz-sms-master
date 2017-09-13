package com.hzdjr.hzwd.modules.financialadmis.web;


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
import com.hzdjr.hzwd.modules.financialadmis.entity.TransactionVo;
import com.hzdjr.hzwd.modules.financialadmis.service.TradeManageService;

@Controller
@RequestMapping(value = "${adminPath}/financialadmis/tradeManage")
public class TradeManageController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(TradeManageController.class);

	@Autowired
	private TradeManageService tradeManageService;


	/**
	 * 交易列表
	 * 平台交易流水号、账户、交易类型、交易时间、状态
	 */
	@RequestMapping("/transactionList")
	public String transactionList(@ModelAttribute TransactionVo transactionVo,HttpServletRequest request,
			HttpServletResponse response, Model model){
		logger.debug("财务管理==》》交易列表查询 start");
		Page<TransactionVo> page = tradeManageService.findTransactionList(new Page<TransactionVo>(request, response),transactionVo);

		model.addAttribute("page",page);
		model.addAttribute("transactionVo",transactionVo);

		logger.debug("财务管理==》》交易列表查询 end");
		return "modules/financialadmis/transactionList";
	}

	/**
	 *
	 * 导出交易列表(带条件)
	 * @return
	 */
	@RequestMapping(value="/exportTransaction")
	public String exportTransaction(String tradeNo,String accCode,String beginTime,String overTime,String tradeStatus,
			HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		logger.debug("财务管理==》》交易列表导出 start");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tradeNo", tradeNo);
		paramMap.put("accCode", accCode);
		paramMap.put("beginTime", beginTime);
		paramMap.put("overTime", overTime);
		paramMap.put("tradeStatus", tradeStatus);

		List<TransactionVo> transactionList = tradeManageService.exportTransaction(paramMap);
		String fileName = "交易列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("交易列表", TransactionVo.class).setDataList(transactionList);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}

		logger.debug("财务管理==》》交易列表导出 end");
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tradeManage/transactionList";
	}

}
