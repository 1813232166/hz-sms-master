package com.hzwealth.sms.modules.moneystatistics.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.modules.moneystatistics.entity.Moneystatistics;
import com.hzwealth.sms.modules.moneystatistics.service.MoneystatisticsService;

@Controller
@RequestMapping(value="${adminPath}/toMoneystatistics")
public class MoneystatisticsController {

	@Autowired
	private MoneystatisticsService moneystatisticsService;
	
	@RequestMapping(value="findMoneystatisticsList")
	public String findMoneystatisticsList(Model model,HttpServletRequest request) {
		List<Moneystatistics> moneystatisticsList1 = moneystatisticsService.findMoneystatisticsList();
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=6;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<Moneystatistics> page = new Page<Moneystatistics>(pageNo,pageSize,moneystatisticsList1.size(),moneystatisticsList1);
		page.initialize();
		
		ArrayList<Moneystatistics> moneystatisticsList = new ArrayList<Moneystatistics>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>moneystatisticsList1.size()){
			end=moneystatisticsList1.size();
		}
		for (int i = start; i < end; i++) {
			moneystatisticsList.add(moneystatisticsList1.get(i));
		}
		
		
		
		model.addAttribute("page",page);
		model.addAttribute("list",moneystatisticsList);
		return "modules/money/moneyStatistics";
	}
}
