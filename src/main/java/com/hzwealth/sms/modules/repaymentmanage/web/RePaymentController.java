package com.hzwealth.sms.modules.repaymentmanage.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.repaymentmanage.entity.LendPlan;
import com.hzwealth.sms.modules.repaymentmanage.entity.PuBiao;
import com.hzwealth.sms.modules.repaymentmanage.service.RePaymentService;

/**
 * 还款管理Controller
 */
@Controller
@RequestMapping("${adminPath}/repayment/repaymentManage")
public class RePaymentController extends BaseController{
	
	@Autowired
	private RePaymentService rePaymentService;
	
	/**
     * @Description: 跳转到还款管理-普享标还款列表
     * @date 2016年10月19日 下午4:36:05
     */
    @RequestMapping("/toPuList")
    public String toPuList(Model model,PuBiao p,HttpServletRequest request,HttpServletResponse response){
        Page<PuBiao> page = rePaymentService.findPage(new Page<PuBiao>(request, response), p);
        List<PuBiao> list = page.getList();
        BigDecimal sumCount = new BigDecimal(0.00);  //初始还款总金额为0.00
		for(PuBiao pu:list){
		    pu.getLoanNumber();
			if(pu.getRemainAmount()!=null && pu.getRemainAmount()!=""){
				BigDecimal count =new BigDecimal(pu.getRemainAmount());
				sumCount = sumCount.add(count);  //获取还款总金额
			}
		}
		model.addAttribute("big", sumCount);
        model.addAttribute("puBiaoList", list);
        model.addAttribute("page", page);
        model.addAttribute("puBiao", p);
        return "modules/repaymentmanage/pulist";
    }
    /**
     * @Description: 导出普享标还款列表
     * @date 2016年10月25日 下午4:36:05
     */
    @RequestMapping("/exportpuList")
    public String exportpuList(PuBiao p,String borrowStatus,HttpServletResponse response, RedirectAttributes redirectAttributes){
    	//过滤条件
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("borrowaliasno", p.getBorrowaliasno());
    	map.put("borrowAlias", p.getBorrowAlias());
    	map.put("loanNumber", p.getLoanNumber());
    	map.put("name", p.getName());
    	map.put("mobile", p.getMobile());
    	map.put("borrowCode", p.getBorrowCode());
    	map.put("endLimit", 20000);
    	
    	String fileName = "普享标还款列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    	List<PuBiao> list = rePaymentService.getPuBiaoList(map);
    	try{
    	 new ExportExcel("普享标还款列表", PuBiao.class).setDataList(list).write(response, fileName).dispose();
 		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/repayment/repaymentManage/toPuList";
    }
    
    
    /**
     * @Description: 跳转到还款管理-普享标还款详情
     * @date 2016年10月20日 下午3:07:28
     */
    @RequestMapping("/biaoDetail")
    public String biaoDetail(Model model,String borrowId,String loanNumber,HttpServletRequest req){
    	String apply_id=loanNumber;
    	PuBiao puBiao = rePaymentService.getPuBiaoListById(borrowId);
    	puBiao.setBorrowId(borrowId);
    	String mobile = puBiao.getMobile();
    	if(mobile!=null){
    		mobile=mobile.substring(0, 3)+"****"+mobile.substring(7);
    	}
    	puBiao.setMobile(mobile);
    	List<Map<String, Object>> list = rePaymentService.selectTermNum(apply_id);
    	String pageNos = req.getParameter("pageNo");
		int pageSize=10;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo,pageSize,list.size(),list);
		page.initialize();
		
		ArrayList<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>list.size()){
			end=list.size();
		}
		for (int i = start; i < end; i++) {
			list2.add(list.get(i));
		}
		
		
		model.addAttribute("list",list2);
		model.addAttribute("page",page);
		model.addAttribute("borrowId",borrowId);
		model.addAttribute("loanNumber",loanNumber);
    	
    	//model.addAttribute("list",list);
    	model.addAttribute("puBiao",puBiao);
        return "modules/repaymentmanage/biaodetail";
    }
	
    /**
	 * @Description: 跳转到还款管理-出借计划还款列表 
	 * @date 2016年10月19日 下午4:36:15
	 */
	@RequestMapping("/toLendList")
	public String toLendList(Model model,HttpServletRequest req){
		String id = req.getParameter("id");
		String productName = req.getParameter("productName");
		Map<Object,String> map = new HashMap<Object,String>();
		map.put("id",id);
		if(productName!=null){
			map.put("productName", productName.trim());
		}
		List<LendPlan> list = rePaymentService.findLendList(map);
		BigDecimal big = new BigDecimal(0.00);  //初始还款总金额为0.00
		for(LendPlan l:list){
			if(l.getCount()!=null){
				BigDecimal count = l.getCount();
				//System.out.println(count+"=====================================");
			    big = count.add(big);  //获取还款总金额
			}
				
		}
		
		String pageNos = req.getParameter("pageNo");
		int pageSize=8;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<LendPlan> page = new Page<LendPlan>(pageNo,pageSize,list.size(),list);
		page.initialize();
		
		ArrayList<LendPlan> list2 = new ArrayList<LendPlan>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>list.size()){
			end=list.size();
		}
		for (int i = start; i < end; i++) {
			list2.add(list.get(i));
		}
		
		
		model.addAttribute("list",list2);
		model.addAttribute("page",page);
		model.addAttribute("sumCount",big);
		return "modules/repaymentmanage/lendlist";
	}
	/**
	 * TODO  出借管理的详情信息
	 * @return
	 */
	@RequestMapping("/toLendDetail")
	public String toLendDetail(String id,Model model,HttpServletRequest req){
		LendPlan finance = rePaymentService.toLendDetail(id);
		//model.addAttribute("finance",finance);
		req.setAttribute("fin",finance);
		return "modules/repaymentmanage/lendDetail";
	}
	
	/**
	 * TODO  导出还款计划——还款列表
	 * @return
	 */
	@RequestMapping("/exportLendList")
	public String exportLendList(RedirectAttributes redirectAttributes,HttpServletResponse response,HttpServletRequest req){
		String id = req.getParameter("id");
		String productName = req.getParameter("productName");
		Map<Object,String> map = new HashMap<Object,String>();
		map.put("id",id);
		if(productName!=null){
			map.put("productName", productName.trim());
		}
		List<LendPlan> list = rePaymentService.findLendList(map);
		String fileName = "出借计划还款列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		 try {
			new ExportExcel("出借计划还款列表", LendPlan.class).setDataList(list).write(response, fileName).dispose();
		   return null;
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		
		 return "redirect:" + Global.getAdminPath() +"/repayment/repaymentManage/toLendList";
	}
	/**
	 * @Description: 跳转到还款管理-出借计划还款明细列表 
	 * @date 2016年10月19日 下午4:36:15
	 */
	@RequestMapping("/tolendDetailInfo")
	@ResponseBody
	public String toLendList(Model model,String rbillNum,String borrowId,HttpServletRequest req,HttpServletResponse response){

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rbillNum",rbillNum);
		map.put("borrowId",borrowId);
		
		List<Map<String, Object>> list = rePaymentService.findlendDetailInfoList(map);
		
		
		String pageNos = req.getParameter("pageNo");
		int pageSize=8;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageNo,pageSize,list.size(),list);
		page.initialize();
		
		ArrayList<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>list.size()){
			end=list.size();
		}
		for (int i = start; i < end; i++) {
			list2.add(list.get(i));
		}
		
		
		model.addAttribute("refundlist",list2);
		model.addAttribute("refundpage",page);
		return renderString(response, model);
	}

}
