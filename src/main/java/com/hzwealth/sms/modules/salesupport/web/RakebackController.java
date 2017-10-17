/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.salesupport.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.IdGen;
import com.hzwealth.sms.common.utils.PropertiesLoader;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.salesupport.entity.Rakeback;
import com.hzwealth.sms.modules.salesupport.service.RakebackService;
/**
 * 返佣设置Controller
 * @author 
 * @version 
 */
@Controller
@RequestMapping(value = "${adminPath}/rakeback/rakebackMessage")
public class RakebackController extends BaseController {

	@Autowired
	private RakebackService rakebackService;
	
	
	private static final PropertiesLoader property = new PropertiesLoader("hzwd.properties");
	@RequestMapping(value = "preAdd")
	public String form(Rakeback rakeback, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Rakeback> rakeback1 = rakebackService.getRakebackList(paramMap);
		String Str=null;
		if(rakeback1!=null){
			for (int i=0;i<rakeback1.size();i++) {
				Str=rakeback1.get(0).getType();
				if(Str.length()>0){
					Str+=","+rakeback1.get(i).getType();
				}else{
					Str=rakeback1.get(i).getType();
				}
			}
			
		}
		model.addAttribute("leixing", Str);
		model.addAttribute("rakeback", rakeback);
		return "modules/salesupport/rakeback/rakebackAdd";
	}
	
	@RequestMapping(value = "preview")
	public String preview( HttpServletRequest request,Model model) {
		
		String operate=request.getParameter("operate");
		String id=request.getParameter("id");
		Rakeback rakeback=new Rakeback();
		
		if(!"".equals(id) ){
	     rakeback = rakebackService.getRakebackPerview(id);
	     rakeback.setAmount(rakebackService.getCount(rakeback.getType()));
		}
		model.addAttribute("rakeback", rakeback);
		
		if(!"\'upd\'".equals(operate)){
			return "modules/salesupport/rakeback/rakebackview";
		}else{
		return "modules/salesupport/rakeback/rakebackAdd";
				}
	}
	/**
	 * @Description: 启用信息
	 * @author 
	 * @date 
	 */
	@RequestMapping("/updatestaus")
	public void updatestaus(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("status", status);
		paramMap.put("id", id);
		 int f = rakebackService.updatestaus(paramMap);
		 String result=null;
		 if(f>0){
		 result= String.valueOf(f);
		 }
		 try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@RequestMapping("/rakebackList")
	public String userInfo(Model model,HttpServletRequest request,Rakeback  rakeback){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//获取查询条件
		//'邀请人类型  1理财师2推荐人',
		String type = request.getParameter("type");
		if(type!=null){
			type=type.trim();
			paramMap.put("type", type);
		}
		//结算方式'0 线下返佣 1 线上返佣',
		String jsStatus = request.getParameter("jsStatus");
		if(jsStatus!=null){
			jsStatus=jsStatus.trim();
			paramMap.put("jsStatus", jsStatus);
		}
		//'返佣方式  0一次性返佣',
		String refferStatus = request.getParameter("refferStatus");
		if(refferStatus!=null){
			refferStatus=refferStatus.trim();
			paramMap.put("refferStatus", refferStatus);
			
		}
		String endTimes = request.getParameter("endTimes");
		if(endTimes!=null){
			endTimes=endTimes.trim();
		}
		List<Rakeback> rakeback1 = rakebackService.getRakebackList(paramMap);
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=10;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<Rakeback> page = new Page<Rakeback>(pageNo,pageSize,rakeback1.size(),rakeback1);
		page.initialize();
		
		ArrayList<Rakeback> rakebackList = new ArrayList<Rakeback>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>rakeback1.size()){
			end=rakeback1.size();
		}
		for (int i = start; i < end; i++) {
			rakeback1.get(i).setAmount(rakebackService.getCount(rakeback1.get(i).getType()));
			rakebackList.add(rakeback1.get(i));
		}
		
		//包含查询参数的map对象，用于回显
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("rakeback", rakeback);
		//分页的对象，用于回显
		model.addAttribute("page", page);
		//用户集合的对象，用于列表展示
		model.addAttribute("rakebackList", rakebackList);
		return "modules/salesupport/rakeback/rakebackList";
	}
	
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,Rakeback rakeback, Model model, RedirectAttributes redirectAttributes) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Rakeback> rakeback1 = rakebackService.getRakebackList(paramMap);
		if (!beanValidator(model, rakeback)){
			return form(rakeback, model);
		}
		int result=0; 
		if(rakeback1.size()>0 && !"".equals(rakeback.getId())==false){
		for (int i=0;i<=rakeback1.size()-1;i++) {
			if(rakeback1.get(i).getType().equals(rakeback.getType())){
				//rakeback.setId(rakeback1.get(i).getId());只做修改
				rakebackService.delrakeback(rakeback1.get(i).getId());//做删除，加一条数据如果type相同就删除老数据，新增新数据
			}
		}
		}
		if(rakeback.getCommonMoney()!=null && !"".equals(rakeback.getCommonMoney())){
		rakeback.setCommonamount(rakeback.getCommonamount()==null?java.math.BigDecimal.valueOf(0):rakeback.getCommonamount());
		rakeback.setCommonOneRate(rakeback.getCommonOneRate()==null?java.math.BigDecimal.valueOf(0):rakeback.getCommonOneRate());
		rakeback.setCommonTwoRate(rakeback.getCommonTwoRate()==null?java.math.BigDecimal.valueOf(0):rakeback.getCommonTwoRate());
		}
		if(rakeback.getSilverMoney()!=null && !"".equals(rakeback.getSilverMoney())){
		rakeback.setSilverOneRate(rakeback.getSilverOneRate()==null?java.math.BigDecimal.valueOf(0):rakeback.getSilverOneRate());
		rakeback.setSilverTwoRate(rakeback.getSilverTwoRate()==null?java.math.BigDecimal.valueOf(0):rakeback.getSilverTwoRate());
		}
		if(rakeback.getGoldMoney()!=null && !"".equals(rakeback.getGoldMoney())){
		rakeback.setGoldOneRate(rakeback.getGoldOneRate()==null?java.math.BigDecimal.valueOf(0):rakeback.getGoldOneRate());
		rakeback.setGoldTwoRate(rakeback.getGoldTwoRate()==null?java.math.BigDecimal.valueOf(0):rakeback.getGoldTwoRate());
		}
		if(!"".equals(rakeback.getId())){
			rakeback.setUpdateDate(new Date());
			rakebackService.updateRakeback(rakeback);
		}else{
		rakeback.setId(IdGen.uuid());
		rakeback.setCreateDate(new Date());
		rakeback.setUpdateDate(new Date());
		rakeback.setStatus("0");
		rakebackService.save(rakeback);
		}
		return "redirect:"+Global.getAdminPath()+"/rakeback/rakebackMessage/rakebackList";
	}
	
}