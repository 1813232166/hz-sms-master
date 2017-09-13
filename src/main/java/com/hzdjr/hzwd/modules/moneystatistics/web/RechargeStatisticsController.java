package com.hzdjr.hzwd.modules.moneystatistics.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.modules.moneystatistics.entity.RechargeStatistics;
import com.hzdjr.hzwd.modules.moneystatistics.service.RechargeStatisticsService;

@Controller
@RequestMapping(value="${adminPath}/toRechargestatistics")
public class RechargeStatisticsController {

	@Autowired
	private RechargeStatisticsService rechargeStatisticsService;
	
	@RequestMapping(value="/findRechargeStatisticsList")
	public String findRechargeStatisticsList(Model model,HttpServletRequest request) {
		List<RechargeStatistics> list1 = rechargeStatisticsService.findRechargeStatisticsList();
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=3;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<RechargeStatistics> page = new Page<RechargeStatistics>(pageNo,pageSize,list1.size(),list1);
		page.initialize();
		
		ArrayList<RechargeStatistics> list = new ArrayList<RechargeStatistics>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>list1.size()){
			end=list1.size();
		}
		for (int i = start; i < end; i++) {
			list.add(list1.get(i));
		}
		
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "modules/money/rechargeStatistics";
	}
}
