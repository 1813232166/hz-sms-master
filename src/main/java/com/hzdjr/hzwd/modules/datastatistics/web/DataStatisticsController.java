package com.hzdjr.hzwd.modules.datastatistics.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzdjr.hzwd.modules.datastatistics.entity.BorrowStatistics;
import com.hzdjr.hzwd.modules.datastatistics.entity.LenderStatistics;
import com.hzdjr.hzwd.modules.datastatistics.service.BorrowStatisticsService;
import com.hzdjr.hzwd.modules.datastatistics.service.LenderStatisticsService;

@Controller
@RequestMapping(value="${adminPath}/toDataStatistics")
public class DataStatisticsController {

	@Autowired
	private BorrowStatisticsService borrowStatisticsService;
	@Autowired
	private LenderStatisticsService lenderStatisticsService;
	
	@RequestMapping(value="findDataStatistics")
	public String findMoneystatisticsList(Model model,HttpServletRequest request) {
		boolean flag=true;
		BorrowStatistics borrowStatistics=new BorrowStatistics();
		LenderStatistics lenderStatistics=new LenderStatistics();
		if(flag){
			//实时查询
			borrowStatistics = borrowStatisticsService.findborrowStatistics();
			//实时查询
			lenderStatistics = lenderStatisticsService.findLenderStatistics();
		}else{
			//查询跑批记录
			borrowStatistics = borrowStatisticsService.findBorrowStatisticsRecord();
			//查询跑批记录
			lenderStatistics = lenderStatisticsService.findLenderStatisticsRecord();
		}
		model.addAttribute("borrowStatistics",borrowStatistics);
		model.addAttribute("lenderStatistics",lenderStatistics);
		
		return "modules/datastatistics/dataStatistics";
	}
}
