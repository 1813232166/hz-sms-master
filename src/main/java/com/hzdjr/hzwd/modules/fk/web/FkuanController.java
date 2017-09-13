package com.hzdjr.hzwd.modules.fk.web;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.borrow.service.BorrowService;
import com.hzdjr.hzwd.modules.borrow.service.ContractSealService;
import com.hzdjr.hzwd.modules.borrow.service.TBorrowService;
import com.hzdjr.hzwd.modules.common.BalancePay;
import com.hzdjr.hzwd.modules.common.BussinessPay;
import com.hzdjr.hzwd.modules.common.BussinessPayHx;
import com.hzdjr.hzwd.modules.common.DataHttpClient;
import com.hzdjr.hzwd.modules.common.HttpUtils;
import com.hzdjr.hzwd.modules.fk.entity.Fkuan;
import com.hzdjr.hzwd.modules.fk.service.FkuanService;
import com.hzdjr.hzwd.modules.sys.utils.UserUtils;

/**
 *Title:FkuanController
 *Description:  放款管理
 *@author:黄亚浩
 *@date:2016年10月12日 上午10:52:59
 */
@Controller
@RequestMapping(value="${adminPath}/fkuan/userFkuan")
public class FkuanController extends BaseController{
	
	@Autowired
	private FkuanService fKuanService;
	@Autowired
	private ContractSealService contractSealService;
	@Autowired
	private TBorrowService tBorrowService;

	/**
	 * TODO  已放款列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/hasFkuanList")
	public String hasFkuanList(HttpServletRequest req,Model model){
		String time = req.getParameter("time");
		String time2 = req.getParameter("time2");
		String borrowerNumber = req.getParameter("borrowerNumber");
		String biaoname = req.getParameter("biaoname");
		String borrowerName = req.getParameter("borrowerName");
		String borrowerPhone = req.getParameter("borrowerPhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("time",time);   //放款时间开始
		map.put("time2",time2);   //放款时间结束
		map.put("borrowerNumber",borrowerNumber);
		map.put("biaoname",biaoname);
		map.put("borrowerName",borrowerName);
		map.put("borrowerPhone",borrowerPhone);
		
		List<Fkuan> hasFkuanList = fKuanService.hasFkuanList(map);
		if(!hasFkuanList.isEmpty()){
			
			BigDecimal sumCount = hasFkuanList.get(0).getSumCount();
			model.addAttribute("sumCount",sumCount);
		}
		String pageNos = req.getParameter("pageNo");
		int pageSize=5;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<Fkuan> page = new Page<Fkuan>(pageNo,pageSize,hasFkuanList.size(),hasFkuanList);
		page.initialize();
		
		ArrayList<Fkuan> hasFkuanList2 = new ArrayList<Fkuan>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>hasFkuanList.size()){
			end=hasFkuanList.size();
		}
		for (int i = start; i < end; i++) {
			hasFkuanList2.add(hasFkuanList.get(i));
		}
		req.setAttribute("list", hasFkuanList2);
		req.setAttribute("page",page);
		req.setAttribute("map",map);
		
		return "modules/fkuan/hasFkuanList";
	}
	
	/*导出已经放款列表*/
	@RequestMapping(value="/exportHasFkuan")
	public String exportHasFkuan(Model model,HttpServletRequest req,HttpServletResponse response,RedirectAttributes redirectAttributes){
		
		String time = req.getParameter("time");
		String time2 = req.getParameter("time2");
		String borrowerNumber = req.getParameter("borrowerNumber");
		String biaoname = req.getParameter("biaoname");
		String borrowerName = req.getParameter("borrowerName");
		String borrowerPhone = req.getParameter("borrowerPhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("time",time);
		map.put("time2",time2);
		map.put("borrowerNumber",borrowerNumber);
		map.put("biaoname",biaoname);
		map.put("borrowerName",borrowerName);
		map.put("borrowerPhone",borrowerPhone);
		
		
		List<Fkuan> hasFkuanList = fKuanService.hasFkuanList(map);
		String fileName = "已放款列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("已放款列表", Fkuan.class,1,2).setDataList(hasFkuanList);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/fkuan/userFkuan/hasFkuanList";
		
		
		
	}
	
	
	/**
	 * TODO  未放款列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/noFkuanList")
	public String noFkuanList(Model model,HttpServletRequest req){
		
		String time = req.getParameter("time");
		String time2 = req.getParameter("time2");
		String borrowerNumber = req.getParameter("borrowerNumber");
		String biaoname = req.getParameter("biaoname");
		String borrowerName = req.getParameter("borrowerName");
		String borrowerPhone = req.getParameter("borrowerPhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("time",time);
		map.put("time2",time2);
		map.put("borrowerNumber",borrowerNumber);
		map.put("biaoname",biaoname);
		map.put("borrowerName",borrowerName);
		map.put("borrowerPhone",borrowerPhone);
		
		List<Fkuan> noFkuanList = fKuanService.noFkuanList(map);
		
		if(!noFkuanList.isEmpty()){
			List<HashMap<String,Object>> objectList = 	fKuanService.selectFkQuery("R2030");
			HashMap<String,String> hashMapObject = new HashMap<String,String>();
			
			for (int i = 0; objectList!=null && i < objectList.size(); i++) {
				String borrowCode = (String)objectList.get(i).get("borrowCode");
				String status = (String)objectList.get(i).get("status");
				hashMapObject.put(borrowCode, status);
			}
			for (int i = 0; hashMapObject!=null && i < noFkuanList.size(); i++) {
				String borrowCode = noFkuanList.get(i).getBorrowCode();
				String queryStatus = hashMapObject.get(borrowCode);
				if(queryStatus!=null&&"0".equals(queryStatus)){//0处理中1处理成功
					noFkuanList.get(i).setStatus(queryStatus);
				}
			}
			
			BigDecimal sumCount = noFkuanList.get(0).getSumCount();
			model.addAttribute("sumCount",sumCount);
		}
			String pageNos = req.getParameter("pageNo");
			Integer pageNo=1;
			if(pageNos!=null){
				pageNo=new Integer(pageNos);
			}
			//传参
			Page<Fkuan> page = new Page<Fkuan>(pageNo,10,noFkuanList.size(),noFkuanList);
			page.initialize();
			
			ArrayList<Fkuan> noFkuanList2 = new ArrayList<Fkuan>();//返回的list集合
			if(pageNo>page.getTotalPage()){
				pageNo=1;
			}
			int start = (pageNo-1)*page.getPageSize();
			int end = pageNo*page.getPageSize();
			if(end>noFkuanList.size()){
				end=noFkuanList.size();
			}
			for (int i = start; i < end; i++) {
				noFkuanList2.add(noFkuanList.get(i));
			}
			
			model.addAttribute("noList",noFkuanList2);
			model.addAttribute("page",page);
			model.addAttribute("map",map);
		
		return "modules/fkuan/noFkuanList";
	}
	
	/*导出未放款列表*/
	@RequestMapping(value="/exportFkuan")
	public String exportFkuan(Model model,HttpServletRequest req,HttpServletResponse response,RedirectAttributes redirectAttributes){
		
		String time = req.getParameter("time");
		String time2 = req.getParameter("time2");
		String borrowerNumber = req.getParameter("borrowerNumber");
		String biaoname = req.getParameter("biaoname");
		String borrowerName = req.getParameter("borrowerName");
		String borrowerPhone = req.getParameter("borrowerPhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("time",time);
		map.put("time2",time2);
		map.put("borrowerNumber",borrowerNumber);
		map.put("biaoname",biaoname);
		map.put("borrowerName",borrowerName);
		map.put("borrowerPhone",borrowerPhone);
		
		List<Fkuan> noFkuanList = fKuanService.noFkuanList(map);
		String fileName = "未放款列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("未放款列表", Fkuan.class,1,1).setDataList(noFkuanList);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/fkuan/userFkuan/noFkuanList";
		
	}
	
	
	
	/**
	 * TODO   拒绝放款列表
	 * @return
	 */
	@RequestMapping(value="/refuseFkuanList")
	public String refuseFkuanList(Model model,HttpServletRequest req,HttpServletResponse response){
		//条件查询
		String time = req.getParameter("time");
		String time2 = req.getParameter("time2");
		String borrowerNumber = req.getParameter("borrowerNumber");
		String biaoname = req.getParameter("biaoname");
		String borrowerName = req.getParameter("borrowerName");
		String borrowerPhone = req.getParameter("borrowerPhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("time",time);
		map.put("time2",time2);
		map.put("borrowerNumber",borrowerNumber);
		map.put("biaoname",biaoname);
		map.put("borrowerName",borrowerName);
		map.put("borrowerPhone",borrowerPhone);
		
		
		List<Fkuan> queryRefuseFkuanList = fKuanService.queryRefuseFkuanList(map);
		String pageNos = req.getParameter("pageNo");
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<Fkuan> page = new Page<Fkuan>(pageNo,10,queryRefuseFkuanList.size(),queryRefuseFkuanList);
		page.initialize();
		
		ArrayList<Fkuan> queryRefuseFkuanListNew = new ArrayList<Fkuan>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>queryRefuseFkuanList.size()){
			end=queryRefuseFkuanList.size();
		}
		for (int i = start; i < end; i++) {
			queryRefuseFkuanListNew.add(queryRefuseFkuanList.get(i));
		}
		model.addAttribute("map",map);
		model.addAttribute("refuseList",queryRefuseFkuanListNew);
		model.addAttribute("page",page);
		return "modules/fkuan/refuseFkuanList";
	}
	
	/**
	 * 拒绝放款导出数据
	 * TODO
	 */
	@RequestMapping(value="/refuseExport")
	public String refuseExport(Model model,HttpServletRequest req,HttpServletResponse response,RedirectAttributes redirectAttributes){
		// 根据条件查询导出
		String time = req.getParameter("time");
		String time2 = req.getParameter("time2");
		String borrowerNumber = req.getParameter("borrowerNumber");
		String biaoname = req.getParameter("biaoname");
		String borrowerName = req.getParameter("borrowerName");
		String borrowerPhone = req.getParameter("borrowerPhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("time",time);
		map.put("time2",time2);
		map.put("borrowerNumber",borrowerNumber);
		map.put("biaoname",biaoname);
		map.put("borrowerName",borrowerName);
		map.put("borrowerPhone",borrowerPhone);
		
		List<Fkuan> queryRefuseFkuanList = fKuanService.queryRefuseFkuanList(map);
		
		String fileName = "拒绝放款列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("拒绝放款列表", Fkuan.class).setDataList(queryRefuseFkuanList);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/fkuan/userFkuan/refuseFkuanList";
		
		
	}
	
	
	/**
	 * TODO   放款异常列表
	 * @return
	 */
	@RequestMapping(value="/errorFkuanList")
	public String errorFkuanList(Model model,HttpServletRequest req,HttpServletResponse response,RedirectAttributes redirectAttributes){
		/*条件查询*/
		String time = req.getParameter("time");
		String time2 = req.getParameter("time2");
		String borrowerNumber = req.getParameter("borrowerNumber");
		String biaoname = req.getParameter("biaoname");
		String borrowerName = req.getParameter("borrowerName");
		String borrowerPhone = req.getParameter("borrowerPhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("time",time);
		map.put("time2",time2);
		map.put("borrowerNumber",borrowerNumber);
		map.put("biaoname",biaoname);
		map.put("borrowerName",borrowerName);
		map.put("borrowerPhone",borrowerPhone);
		
		List<Fkuan> errorFkuanList1 = fKuanService.errorFkuanList(map);
		//分页
		String pageNos = req.getParameter("pageNo");
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<Fkuan> page = new Page<Fkuan>(pageNo,10,errorFkuanList1.size(),errorFkuanList1);
		page.initialize();
		
		ArrayList<Fkuan> errorFkuanList = new ArrayList<Fkuan>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>errorFkuanList1.size()){
			end=errorFkuanList1.size();
		}
		for (int i = start; i < end; i++) {
			errorFkuanList.add(errorFkuanList1.get(i));
		}
		BigDecimal sumCount = new BigDecimal(0.00);  //初始还款总金额为0.00
		if(!errorFkuanList1.isEmpty()){
		for (int i = 0; i < errorFkuanList1.size(); i++) {
			sumCount =sumCount.add(errorFkuanList1.get(i).getSumCount()) ;
		}
		}
		model.addAttribute("sumCount", sumCount);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		model.addAttribute("errorList",errorFkuanList);
		return "modules/fkuan/errorFkuanList";
	}
	
	/**
	 * 导出放款异常列表
	 * TODO
	 */
	@RequestMapping(value="/errorExceptor")
	public String errorExceptor(Model model,HttpServletRequest req,HttpServletResponse response,RedirectAttributes redirectAttributes){
		
		/*条件导出*/
		String time = req.getParameter("time");
		String time2 = req.getParameter("time2");
		String borrowerNumber = req.getParameter("borrowerNumber");
		String biaoname = req.getParameter("biaoname");
		String borrowerName = req.getParameter("borrowerName");
		String borrowerPhone = req.getParameter("borrowerPhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("time",time);
		map.put("time2",time2);
		map.put("borrowerNumber",borrowerNumber);
		map.put("biaoname",biaoname);
		map.put("borrowerName",borrowerName);
		map.put("borrowerPhone",borrowerPhone);
		
		List<Fkuan> errorFkuanList = fKuanService.errorFkuanList(map);
		String fileName = "异常放款列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("异常放款列表", Fkuan.class,1,4).setDataList(errorFkuanList);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/fkuan/userFkuan/errorFkuanList";
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="fkOperation")
	@ResponseBody
	public String fkOperation(String borrowId) {
		String[]  borrowIds = borrowId.split(",");
		for (int j = 0; borrowIds!=null && j < borrowIds.length; j++) {
			String operator = UserUtils.getUser().getLoginName();
			String  borrowIdArray = borrowIds[j];
			try {//创建标的在审核通过的时候操作
				String message = "";
//				String uuid = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf((int)((Math.random()*9+1)*100000));//UUIDUtil.genUUIDString();//每回生成新的请求流水号
				//此标是否签章成功
				
/*				int i=contractSealService.queryContractSealStatus(borrowIdArray);
				if(i<1){
					logger.debug("标的编号为:::::"+borrowIdArray+":::签章未成功");
					message="该标的签章未完成";
					fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowIdArray, message,operator);
					return "0";
				}*/
				//（2）调用结算接口
				String resBalancePay = BalancePay.getBalance(borrowIdArray);
				logger.debug("请求结算第一步返回=====>"+resBalancePay);
				Map<String,Object>  mapObject = JSONObject.parseObject(resBalancePay,Map.class);
				if(!(boolean)mapObject.get("success")){//成功执行
					logger.debug("调用结算第一步失败");
					message="调用内部结算接口异常";
					fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowIdArray, message,operator);
					return "0";
				}
				Map<String,Object>  dataOrMessageObject = (Map<String,Object>)mapObject.get("dataOrMessage");
				String dataOrMessage = dataOrMessageObject.toString();
				String requestNo = (String)dataOrMessageObject.get("requestNo");//存储下来供第四步调用
				//（3）2的数据返回得到调用放款接口的前置（修改数据库）
				HashMap<String,Object> reqMapFk = BussinessPayHx.reqObject("R2030", "v0001", dataOrMessage, "PC");
				logger.debug("请求放款接口数据======>"+reqMapFk.toString());
				String resFk =  HttpUtils.sendPostMessage(BussinessPayHx.reqUrlBorrowCreate, reqMapFk);
				logger.debug("请求放款接口返回=====>"+resFk);
				JSONObject jsonObject = (JSONObject)JSONObject.parse(resFk);
				String userId = tBorrowService.get(borrowIdArray).getUserid();
				if("serviceException".equals((String)jsonObject.get("status"))){
					String borrowCode = tBorrowService.get(borrowIdArray).getBorrowcode();
					fKuanService.deleteFkQuery(userId, requestNo, "R2030", borrowCode);
					message="调用第三方放款接口异常";
					fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowIdArray, message,operator);
					return "0";
				}
				//Map<String,Object>  jsonObject = JSONObject.parseObject(resInterface,Map.class);
				JSONObject dataObject= (JSONObject)jsonObject.get("data");
				String errorCode = (String)dataObject.get("errorCode");
				if(!"0".equals(errorCode)){
					String borrowCode = tBorrowService.get(borrowIdArray).getBorrowcode();
					fKuanService.deleteFkQuery(userId, requestNo, "R2030", borrowCode);
					message="调用放款接口异常";
					fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowIdArray, message,operator);
					return "0"; 
				}
				if("0".equals(errorCode)){
					String borrowCode = tBorrowService.get(borrowIdArray).getBorrowcode();
					HashMap<String,Object> map = new HashMap<String,Object>();
					map.put("code", "R2030");
					map.put("userId", userId);
					map.put("requestNo", requestNo);
					map.put("status", "0");
					Date date = new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String cdate = sdf.format(date);
					map.put("create_date", cdate);
					map.put("borrowCode", borrowCode);
					fKuanService.insertFkQuery(map);
				}
//				JSONObject fkObject = JSONObject.parseObject(resFk);
//				JSONObject fkStatus = (JSONObject)fkObject.get("status");
//				if("1".equals(fkStatus)&&"3".equals(fkStatus)){
//					logger.debug("调用放款接口失败或超时");
//					message=(String)fkObject.get("msg");
//					fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowId, message,operator);
//					return "0"; 
//				}
//				if("1".equals(fkStatus)&&"3".equals(fkStatus)){
//					logger.debug("调用放款接口失败或超时");
//					message=(String)fkObject.get("msg");
//					fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowId, message,operator);
//					return "0"; 
//				}

			} catch (Exception e) {
				e.printStackTrace();
				fKuanService.insertFkSeason(UUIDUtil.genUUIDString(), borrowIdArray, "调用放款接口异常",operator);
				return "0";
			}
		}
		return "1";
	}
	
	@RequestMapping("/finderror")
	@ResponseBody
	public String finderror(HttpServletRequest request,HttpServletResponse response){
		 	String  borrowId = (String) request.getParameter("borrowId");
		 	String repayplans = fKuanService.finderror(borrowId);
	       return renderString(response, repayplans);
	}
}
