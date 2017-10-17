package com.hzwealth.sms.modules.usercount.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.usercount.entity.LendPlan;
import com.hzwealth.sms.modules.usercount.entity.PuCount;
import com.hzwealth.sms.modules.usercount.service.LendPlanService;

@Controller
@RequestMapping("${adminPath}/biao/biaocount")
public class BiaoCountController extends BaseController{
	@Autowired
	private LendPlanService lendPlanService;
	
	/**
	 * @Description: 跳转到出借计划统计页面
	 * @author jiangqishuai
	 * @date 2016年10月12日 下午5:49:33
	 */
	@RequestMapping("/lendPlan")
	public String lendPlan(HttpServletRequest request,String beginTimes,String endTimes){
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginTimes", beginTimes);
		map.put("endTimes", endTimes);
		List<LendPlan> lendplan = lendPlanService.getLendPlan(map);
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=3;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<LendPlan> page = new Page<LendPlan>(pageNo,pageSize,lendplan.size(),lendplan);
		page.initialize();
		
		ArrayList<LendPlan> lendplan1 = new ArrayList<LendPlan>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>lendplan.size()){
			end=lendplan.size();
		}
		for (int i = start; i < end; i++) {
			lendplan1.add(lendplan.get(i));
		}
		
		
		BigDecimal lendPlanAmountsum = new BigDecimal(0.00);
		for (LendPlan l : lendplan1) {
			BigDecimal amountsum = l.getAmountsum();
			if(amountsum!=null){
				lendPlanAmountsum=amountsum.add(lendPlanAmountsum);
			}
		}
		Map<String, Object> plan = new HashMap<String, Object>();
		plan.put("lendPlanAmountsum", lendPlanAmountsum+"");
		
		Map<String, String> date1 = new HashMap<String, String>();
		date1.put("beginTimes", beginTimes);
		date1.put("endTimes", endTimes);
		request.setAttribute("date1", date1);
		request.setAttribute("lendplan", lendplan1);
		request.setAttribute("plan", plan);
		request.setAttribute("page", page);
		return "modules/datacount/lendplan";
	}
	/**
	 * @Description:跳转到普享标统计页面 
	 * @author jiangqishuai
	 * @date 2016年10月13日 上午9:38:53
	 */
	@RequestMapping("/puCount")
	public String puCount(HttpServletRequest request,String beginTimes,String endTimes){
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginTimes", beginTimes);
		map.put("endTimes", endTimes);
		List<PuCount> pucount1 = lendPlanService.getPuCount(map);
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=3;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<PuCount> page = new Page<PuCount>(pageNo,pageSize,pucount1.size(),pucount1);
		page.initialize();
		
		ArrayList<PuCount> pucount = new ArrayList<PuCount>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>pucount1.size()){
			end=pucount1.size();
		}
		for (int i = start; i < end; i++) {
			pucount.add(pucount1.get(i));
		}
		
		
		BigDecimal borrowAmountsum = new BigDecimal(0.00);
		for (PuCount l : pucount) {
			BigDecimal borrowAmount = l.getBorrowamount();
			if(borrowAmount!=null){
				borrowAmountsum=borrowAmount.add(borrowAmountsum);
			}
		}
		
		
		Map<String, Object> pu = new HashMap<String, Object>();
		pu.put("borrowAmountsum", borrowAmountsum);
		Map<String, String> date1 = new HashMap<String, String>();
		date1.put("beginTimes", beginTimes);
		date1.put("endTimes", endTimes);
		request.setAttribute("date1", date1);
		request.setAttribute("pucount", pucount);
		request.setAttribute("pu", pu);
		request.setAttribute("page", page);
		return "modules/datacount/pucount";
	}

	/**
	 * @Description: 导出出借计划统计列表
	 * @return void
	 * @author jiangqishuai
	 * @date 2016年10月13日 下午4:40:03
	 */
	@RequestMapping("/exportlendPlan")
	public String exportlendPlan(String beginTimes,String endTimes,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		/*map.put("beginTimes", beginTimes);
		map.put("endTimes", endTimes);*///不带条件查询，导出全部数据
		List<LendPlan> lendplan = lendPlanService.getLendPlan(map);
		for (int i = 0; i < lendplan.size(); i++) {
			LendPlan le = lendplan.get(i);
			le.setNum(i+1);
		}
		BigDecimal lendPlanAmountsum = new BigDecimal(0.00);
		for (LendPlan l : lendplan) {
			if(l.getAmountsum()!=null){
				lendPlanAmountsum=lendPlanAmountsum.add(l.getAmountsum());
			}
		}
		
		String fileName = "出借计划统计列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			
			ExportExcel excel = new ExportExcel("出借计划统计列表", LendPlan.class).setDataList(lendplan);
			Row r = excel.addRow();
			excel.addCell(r, 0, "总计：",2,Class.class);
			excel.addCell(r, 1, "",2,Class.class);
			excel.addCell(r, 2, "",2,Class.class);
			excel.addCell(r, 3, "",2,Class.class);
			excel.addCell(r, 4, "",2,Class.class);
			excel.addCell(r, 5, lendPlanAmountsum+"",2,Class.class);
			excel.addCell(r, 6, "",2,Class.class);
			excel.addCell(r, 7, "",2,Class.class);
			
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/biao/biaocount/lendPlan";
		
		
	}
	/**
	 * @Description: 导出普享标统计列表
	 * @return void
	 * @author jiangqishuai
	 * @date 2016年10月13日 下午4:57:15
	 */
	@RequestMapping("/exportpuCount")
	public String exportpuCount(String beginTimes,String endTimes,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		/*map.put("beginTimes", beginTimes);
		map.put("endTimes", endTimes);*/
		List<PuCount> pucount = lendPlanService.getPuCount(map);
		
		for (int i = 0; i < pucount.size(); i++) {
			PuCount pu = pucount.get(i);
			pu.setNum(i+1);
		}
		
		BigDecimal borrowAmountsum = new BigDecimal(0.00);
		for (PuCount l : pucount) {
			BigDecimal borrowAmount = l.getBorrowamount();
			if(borrowAmount!=null){
				borrowAmountsum=borrowAmount.add(borrowAmountsum);
			}
		}
		
		
		String fileName = "普享标统计列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			
			ExportExcel excel = new ExportExcel("普享标统计列表", PuCount.class).setDataList(pucount);
			Row r = excel.addRow();
			excel.addCell(r, 0, "总计：",2,Class.class);
			excel.addCell(r, 1, "",2,Class.class);
			excel.addCell(r, 2, "",2,Class.class);
			excel.addCell(r, 3, "",2,Class.class);
			excel.addCell(r, 4, "",2,Class.class);
			excel.addCell(r, 5, borrowAmountsum+"",2,Class.class);
			excel.addCell(r, 6, "",2,Class.class);
			excel.addCell(r, 7, "",2,Class.class);
			excel.addCell(r, 8, "",2,Class.class);
			
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/biao/biaocount/puCount";
		
		
	}
}
