package com.hzwealth.sms.modules.financialadmis.web;


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

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.financialadmis.entity.AdvanceVo;
import com.hzwealth.sms.modules.financialadmis.service.AdvanceManageService;

@Controller
@RequestMapping(value = "${adminPath}/financialadmis/advanceManage")
public class AdvanceManageController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(AdvanceManageController.class);

	@Autowired
	private AdvanceManageService advanceManageService;


	/**
	 * 交易对账===>>垫付
	 * 借款编号、平台交易流水号、垫付时间
	 */
	@RequestMapping("/advanceList")
	public String transactionList(@ModelAttribute AdvanceVo advanceVo,HttpServletRequest request,
			HttpServletResponse response, Model model){
		logger.debug("财务管理==》》交易对账==》》垫付 start");

		Page<AdvanceVo> page = advanceManageService.findAdvanceList(new Page<AdvanceVo>(request, response),advanceVo);

		model.addAttribute("page",page);
		model.addAttribute("advanceVo",advanceVo);

		logger.debug("财务管理==》》交易对账==》》垫付  end");
		return "modules/financialadmis/advanceList";
	}

	/**
	 *
	 * 导出交易列表(带条件)
	 * @return
	 */
	@RequestMapping(value="/exportAdvance")
	public String exportTransaction(String borrowNo,String tradeNo,String beginTime,String overTime,
			HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		logger.debug("财务管理==》》交易对账==》》垫付列表导出 start");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tradeNo", tradeNo);
		paramMap.put("borrowNo", borrowNo);
		paramMap.put("beginTime", beginTime);
		paramMap.put("overTime", overTime);

		List<AdvanceVo> advanceList = advanceManageService.exportAdvance(paramMap);
		String fileName = "垫付对账列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("垫付对账列表", AdvanceVo.class).setDataList(advanceList);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}

		logger.debug("财务管理==》》交易对账==》》垫付列表导出 end");
		return "redirect:" + Global.getAdminPath() + "/financialadmis/tradeManage/advanceList";
	}

}
