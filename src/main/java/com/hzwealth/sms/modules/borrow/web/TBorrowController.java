/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.web;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.UUIDUtil;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.RepayPlanVo;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;
import com.hzwealth.sms.modules.borrow.service.LoanFundService;
import com.hzwealth.sms.modules.borrow.service.TBorrowService;
import com.hzwealth.sms.modules.borrowlist.service.AuditBorrowListService;
import com.hzwealth.sms.modules.buss.service.MessageModulesService;
import com.hzwealth.sms.modules.common.BalancePay;
import com.hzwealth.sms.modules.common.BussinessPay;
import com.hzwealth.sms.modules.common.BussinessPayHx;
import com.hzwealth.sms.modules.common.DataHttpClient;
import com.hzwealth.sms.modules.common.HttpUtils;
import com.hzwealth.sms.modules.common.ReqUrlPreUUID;
import com.hzwealth.sms.modules.common.SendSms;
import com.hzwealth.sms.modules.fk.service.FkuanService;
import com.hzwealth.sms.modules.innerreffere.service.TinnerReffereeService;
import com.hzwealth.sms.modules.invest.entity.Invest;
import com.hzwealth.sms.modules.invest.service.InvestService;
import com.hzwealth.sms.modules.refferee.entity.TUser;
import com.hzwealth.sms.modules.refferee.service.TUserService;

/**
 * 标的列表Controller
 * @author ln
 * @version 2016-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/tBorrow")
public class TBorrowController extends BaseController {

	@Autowired
	private TBorrowService tBorrowService;
	@Autowired
	private LoanFundService loanFundService;
	@Autowired
	private InvestService investService;
	@Autowired
	private TUserService tUserService;
    @Autowired
	private TinnerReffereeService tinnerReffereeService;
    @Autowired
	private MessageModulesService messageModulesService;
    @Autowired
	private FkuanService fkuanService;
    @Autowired
	private AuditBorrowListService auditBorrowListService;
    
    
	@ModelAttribute
	public TBorrow get(@RequestParam(required=false) String id) {
		TBorrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBorrowService.get(id);
		}
		if (entity == null){
			entity = new TBorrow();
		}
		return entity;
	}
	
//	@RequiresPermissions("borrow:tBorrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBorrow tBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		String paged = request.getParameter("pageNo");
		Page<TBorrow> page = tBorrowService.findPage(new Page<TBorrow>(request, response), tBorrow); 
		if(page!=null && page.getList().size()>0){
			for (int m = 0; m < page.getList().size(); m++) {
				String borrowStatus = page.getList().get(m).getBorrowstatus();
				if("12".equals(borrowStatus)){
					int  existPay = tBorrowService.getBorrowPay(page.getList().get(m).getBorrowcode());
					if(existPay>0||"21".equals(borrowStatus)){//存在说明有支付单，是流标的概念
						page.getList().get(m).setStatusMemo("流标中");
						tBorrow.setStatusMemo("流标中");
					}else{
						page.getList().get(m).setStatusMemo("撤销中");
						tBorrow.setStatusMemo("撤销中");
					}
				}
				if("9".equals(borrowStatus)){
					int  existPay = tBorrowService.getBorrowPay(page.getList().get(m).getBorrowcode());
					if(existPay>0){//存在说明有支付单，是流标的概念
						page.getList().get(m).setStatusMemo("已流标");
						tBorrow.setStatusMemo("已流标");
					}else{
						page.getList().get(m).setStatusMemo("已撤销");
						tBorrow.setStatusMemo("已撤销");
					}
				}
			}
		}
		Map<String,Object> countMap = tBorrowService.selectCount(tBorrow);
		if(countMap!=null){
			if(countMap.get("BORROWAMOUNT")==null){
				countMap.put("BORROWAMOUNT", 0);
			}
			if(countMap.get("count")!=null){
				page.setCount(Long.valueOf(countMap.get("count")+""));
			}
			
		}
		if(StringUtils.isNotBlank(paged)){
			page.setPageNo(Integer.parseInt(paged));
		}
		model.addAttribute("page", page);
		model.addAttribute("tBorrow", tBorrow);
		model.addAttribute("countMap", countMap);
		return "modules/borrow/tBorrowList";
	}
	@RequestMapping(value="exportborrowInfo")
	public void exportuserInfo(HttpServletRequest request,HttpServletResponse response,TBorrow tBorrow,RedirectAttributes redirectAttributes) throws IOException {
		
		List<TBorrow> list = tBorrowService.findExcelList(tBorrow);
		/*workbook2007用XSSFWorkbook()  文件名：*. xlsx
        workbook2003用HSSFWorkbook()  文件名：*. xls*/
		 // 1先创建工作簿对
        XSSFWorkbook workbook2007 = new XSSFWorkbook(); 
         // 2创建工作表对象并命名
        XSSFSheet sheet = workbook2007.createSheet("标的列表信息" );
        // 遍历集合对象创建行和单元格
        //3 创建行
        XSSFRow row = sheet.createRow(0); 
        // 4开始创建单元格并赋值
       XSSFCell nameCell0 = row .createCell(0); 
       XSSFCell nameCell1 = row .createCell(1); 
       XSSFCell nameCell2 = row .createCell(2); 
       XSSFCell nameCell3 = row .createCell(3); 
       XSSFCell nameCell4 = row .createCell(4); 
       XSSFCell nameCell5 = row .createCell(5); 
       XSSFCell nameCell6 = row .createCell(6); 
       XSSFCell nameCell7 = row .createCell(7); 
       XSSFCell nameCell8 = row .createCell(8); 
       XSSFCell nameCell9 = row .createCell(9); 
       XSSFCell nameCell10 = row .createCell(10); 
       XSSFCell nameCell11 = row .createCell(11); 
       nameCell0.setCellValue("标的编号" ); 
       nameCell1.setCellValue("标的名称" ); 
       nameCell2.setCellValue("借款编号" ); 
       nameCell3.setCellValue("借款人" );
       nameCell4.setCellValue("借款金额(元)" );
       nameCell5.setCellValue("出借年华利率" );
       nameCell6.setCellValue("出借期限" );
       nameCell7.setCellValue("还款方式" );
       nameCell8.setCellValue("创建时间" );
       nameCell9.setCellValue("发布时间" );
       nameCell10.setCellValue("状态" );
       nameCell11.setCellValue("备注" );
       XSSFRow row1= null;
       int i=0;
       for (i = 0; i<list.size(); i++) {
    	   TBorrow tBorrowInfo = list.get(i);
    	   row1 = sheet.createRow(i+1);
    	   row1.createCell(0).setCellValue(tBorrowInfo.getBorrowaliasno());
           row1.createCell(1).setCellValue(tBorrowInfo.getBorrowalias());
           row1.createCell(2).setCellValue(tBorrowInfo.getLoannumber());
           row1.createCell(3).setCellValue(tBorrowInfo.getUserName());
           row1.createCell(4).setCellValue(tBorrowInfo.getBorrowamount());
           row1.createCell(5).setCellValue(tBorrowInfo.getAnualrate());
           row1.createCell(6).setCellValue(tBorrowInfo.getDeadline());
           String payMethod = tBorrowInfo.getPayMethod();
           if("debx".equals(payMethod)){
        	   payMethod = "等额本息";
           }else if("xxhb".equals(payMethod)){
        	   payMethod = "先息后本";
           }else if("ychbx".equals(payMethod)){
        	   payMethod = "一次性还本付息";
           }
           row1.createCell(7).setCellValue(payMethod);
           Date createTimes = tBorrowInfo.getCreateTime();
           SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
           row1.createCell(8).setCellValue(createTimes == null?"":sdf.format(createTimes));
           Date openborrowdate = tBorrowInfo.getOpenborrowdate();
           row1.createCell(9).setCellValue(openborrowdate == null?"":sdf.format(tBorrowInfo.getOpenborrowdate()));
           String borrowStatuas = tBorrowInfo.getBorrowstatus();
           switch (borrowStatuas) {
			case "0":
				borrowStatuas = "待审核";
				break;
			case "1":
				borrowStatuas = "预发布";
				break;
			case "2":
				borrowStatuas = "初审成功";
				break;
			case "3":
				borrowStatuas = "初审失败";
				break;
			case "4":
				borrowStatuas = "招标中";
				break;
			case "5":
				borrowStatuas = "待复审";
				break;
			case "6":
				borrowStatuas = "复审失败";
				break;
			case "8":
				borrowStatuas = "已还清";
				break;
			case "9":
				borrowStatuas = "已流标";
				break;
			case "10":
				borrowStatuas = "逾期";
				break;
			case "11":
				borrowStatuas = "已经满标";
				break;
			case "12":
				borrowStatuas = "已经撤销";
				break;
			case "13":
				borrowStatuas = "信审过程中";
				break;
			case "14":
				borrowStatuas = "信审失败";
				break;
			case "15":
				borrowStatuas = "信审成功";
				break;
			default:
				borrowStatuas = "待审核";
				break;
			}
           row1.createCell(10).setCellValue(borrowStatuas);
           row1.createCell(11).setCellValue(tBorrowInfo.getBzInfo());
       }
       // 5生成文件
       String fileName = "标的信息.xlsx" ;
       response.setContentType( "multipart/form-data"); 
       response.setHeader( "Content-Disposition", "attachment;filename=" +new String(fileName.getBytes(),"ISO-8859-1" ));
       OutputStream outputStream = response.getOutputStream();
      
       workbook2007.write(outputStream );
       outputStream.close();
     
	}

//	@RequiresPermissions("borrow:tBorrow:view")
	@RequestMapping(value = "form")
	public String form(TBorrow tBorrow, Model model) {
		model.addAttribute("tBorrow", tBorrow);
		return "modules/borrow/tBorrowForm";
	}
	
	@RequestMapping(value = "shenheform")
	public String shenheform(TBorrow tBorrow, Model model) {
		model.addAttribute("tBorrow", tBorrow);
		return "modules/borrow/shenheForm";
	}

//	@RequiresPermissions("borrow:tBorrow:edit")
	@RequestMapping(value = "save")
	public String save(TBorrow tBorrow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tBorrow)){
			return form(tBorrow, model);
		}
		tBorrowService.save(tBorrow);
		addMessage(redirectAttributes, "保存标的列表保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/tBorrow/?repage";
	}

	
	
//	@RequiresPermissions("borrow:tBorrow:edit")
	@RequestMapping(value = "delete")
	public String delete(String  borrowId, String  borrowtype,RedirectAttributes redirectAttributes) {
		HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
		String resUrl = "redirect:"+Global.getAdminPath()+"/borrow/tBorrow/?repage";
		if(borrowtype!=null && "2".equals(borrowtype)){
			resUrl = "redirect:"+Global.getAdminPath()+"/borrow/borrow/?repage";
		}
		TBorrow tBorrow = tBorrowService.getBorrowById(borrowId);
		String borrowCode = tBorrow.getBorrowcode();
		String borrowStatus = tBorrow.getBorrowstatus();
		String creditStatus = tBorrow.getCreditStatus();
		String[] statusArray ={"0","3","13","14","15","16","17","18","19","20"};
		try {
		for (int m = 0; m < statusArray.length; m++) {
			String status = statusArray[m];
			if(borrowStatus.equals(status)){
				logger.debug(BussinessPayHx.reqUrlBorrowCancel+"?borrowCode="+borrowCode+"&borrowStatus="+borrowStatus+"&creditStatus="+creditStatus);
				String  resStr = HttpUtils.getHtmlConentByUrl(BussinessPayHx.reqUrlBorrowCancel+"?borrowCode="+borrowCode+"&borrowStatus="+borrowStatus+"&creditStatus="+creditStatus);
				if("true".equals(resStr)){
					logger.debug("调用项目前端流标接口成功：{}",borrowCode);
					addMessage(redirectAttributes, "撤销标的成功");
				}else{
					logger.debug("调用项目前端流标接口异常：{}",borrowCode);
					addMessage(redirectAttributes, "撤销标的失败");
				}
				return resUrl;
			}
		}
		
//		String requestNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf((int)((Math.random()*9+1)*100000));//UUIDUtil.genUUIDString();//每回生成新的请求流水号
//		List<Invest> investObject = investService.findInvestById(borrowId);
//		HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();
//		List<HashMap<String,Object>> listObject = new ArrayList<HashMap<String,Object>>();
//		if(investObject!=null&&investObject.size()>0){
//			for (int i = 0; i < investObject.size(); i++) {
//				HashMap<String,Object> hashMap = new HashMap<String,Object>();
//				String id = investObject.get(i).getBizid();
//				String amount = investObject.get(i).getInvestamount();
//				String investor = investObject.get(i).getInvestor();
//				hashMap.put("preTransactionNo", id);
//				hashMap.put("amount", amount);
//				hashMap.put("userId", investor);
//				listObject.add(hashMap);
//			}
//		}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ReqUrlPreUUID reqUrl = new ReqUrlPreUUID();
			String requestNo = reqUrl.GetRequestNo("2039");
			objectHashMapObject.put("LOANNO", borrowCode);
			objectHashMapObject.put("requestNo", requestNo);
			String jsonToStr = JSONArray.toJSONString(objectHashMapObject);
			logger.debug("定时器流标请求数据=======>"+jsonToStr);
			HashMap<String,Object> reqMapCb = BussinessPayHx.reqObject("R2039", "v0001", jsonToStr, "PC");
			String resString = "";
			try {
				resString = BussinessPayHx.getLb(reqMapCb);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.debug("调用流标接口异常：{}",borrowCode);
				addMessage(redirectAttributes, "撤销标的请求数据失败");
				return resUrl;
			}
			logger.debug("流标返回数据=======>"+resString);
			try {
				String code="R2039";
				logger.debug("流标动作请求接口返回数据=======>"+resString);
				JSONObject  mapLbObject = JSONObject.parseObject(resString);
				 String errorCode= (String)mapLbObject.get("errorCode");
				 if(errorCode!=null && !"0".equals(errorCode)){//失败
					   int isExist = fkuanService.isExistBorrow(borrowCode);
						if(isExist>0){
					       fkuanService.deleteFkQuery(tBorrow.getUserid(), requestNo, code, borrowCode);
						}
						logger.debug("调用流标动作请求接口失败");
						addMessage(redirectAttributes, "撤销标的请求数据失败");
						return resUrl;
				 }
				 if("0".equals(errorCode)){//(成功的情况)如果成功,更新数据库为1,如果处理中不做处理，如果处理失败删除数据库
						HashMap<String,Object> map = new HashMap<String,Object>();
						map.put("code", "R2039");
						map.put("userId", tBorrow.getUserid());
						map.put("requestNo", requestNo);
						map.put("status", "0");
						Date date = new Date();
						String cdate = sdf.format(date);
						map.put("create_date", cdate);
						map.put("borrowCode", borrowCode);
						tBorrowService.updateLbIng(borrowCode);//更新流标中12的状态
						fkuanService.insertFkQuery(map);
					}
					//调用接口
//					BalancePay.fkCancelXS(borrowList.get(i).getBorrowcode());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logger.debug("调用流标动作请求接口失败：{}",borrowCode);
					addMessage(redirectAttributes, "撤销标的请求数据失败");
					return resUrl;
				}
			
				//调用接口
//				BalancePay.fkCancelXS(borrowList.get(i).getBorrowcode());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.debug("调用流标接口失败：{}",borrowCode);
				addMessage(redirectAttributes, "撤销标的请求数据失败");
				return resUrl;
			}
			//tBorrowService.changeBorrowstatus("9", borrowCode);
			logger.debug("更新流标状态,标的编号：{}",borrowCode);
			
			
//			objectHashMapObject.put("preTransactionDetails", listObject);
//			objectHashMapObject.put("requestNo", requestNo);
//			String jsonToStr = JSONArray.toJSONString(objectHashMapObject);
//			logger.debug("撤标请求数据=======>"+jsonToStr);
//			HashMap<String,Object> reqMapCb = BussinessPay.reqObject("R2029", "v0001", jsonToStr, "PC");
//			String resString = BussinessPay.getLb(reqMapCb);
//			logger.debug("撤标返回数据=======>"+resString);
//			JSONObject  mapLbObject = JSONObject.parseObject(resString);
//			String lbStatus = (String)mapLbObject.get("status");
//			if(!"0".equals(lbStatus)){
//				logger.debug("调用撤标接口失败");
//				addMessage(redirectAttributes, "撤销标的失败");
//				return resUrl;
//			}
//			boolean resFlag = true;
//			if("0".equals(lbStatus)){
//				JSONArray lbListObject = (JSONArray)mapLbObject.get("data");
//				for (int i = 0; lbListObject!=null && i < lbListObject.size(); i++) {
//					JSONObject objectInvest = (JSONObject)lbListObject.get(i);
//					if(!"0".equals(objectInvest.get("code"))){
//						resFlag = false;
//					}else{
//						String preTransactionNo = (String)objectInvest.get("preTransactionNo");
//						investService.updateInvestFlag(preTransactionNo);
//					}
//				}
//				if(!resFlag){
//					addMessage(redirectAttributes, "撤销标的部分成功,请重试");
//					return resUrl; 
//				}
//			}
//			int borrowStatus = tBorrowService.changeBorrowstatus("12",borrowId);
//			if(borrowStatus<0){
//				addMessage(redirectAttributes, "撤销标的失败");
//				return resUrl; 
//			}
//			borrowId = tBorrowService.get(borrowId).getBorrowcode();
//			BalancePay.fkCancelXS(borrowId);//调用接口
		//tBorrowService.delete(tBorrow);
		//addMessage(redirectAttributes, "删除标的列表保存成功成功");
		
		addMessage(redirectAttributes, "撤销标的请求数据成功");
		return resUrl;
	}
	
	
	
	@RequestMapping(value = "borrowdetail")
	public String borrowdetail(TBorrow tBorrow,String methodType,HttpServletRequest request,HttpServletResponse response,Model model) {
		//匹配债权(一对一)
		tBorrow = tBorrowService.get(tBorrow);
		TBorrowBillplan billplan = new TBorrowBillplan();
		Invest inv = new Invest();
		billplan.setApplyId(tBorrow.getLoannumber());
		inv.setBorrowid(tBorrow.getBorrowId());
		//出借记录
		Page<Invest> invests = tBorrowService.getInvRecByBid(new Page<Invest>(request, response),inv);
		//还款计划
		Page<TBorrowBillplan> billPlans = loanFundService.queryBilPlanByBorrowId(new Page<TBorrowBillplan>(request, response),billplan);
		model.addAttribute("tBorrow", tBorrow);
		model.addAttribute("billPlans", billPlans);
		model.addAttribute("invests", invests);
		model.addAttribute("methodType",methodType);
		return "modules/borrow/borrowDetail";
	}
	/**
	 * 回款明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/repayplan")
	@ResponseBody
	public String getRepayPlan(HttpServletRequest request,HttpServletResponse response){
		 	String  borrowId = (String) request.getParameter("borrowId");
		 	String billNum = request.getParameter("period");
		 	RepayPlanVo rpv=new RepayPlanVo();
		 	rpv.setBillNum(billNum);
		 	rpv.setBorrowId(borrowId);;
	       Page<RepayPlanVo> repayplans = tBorrowService.queryPaymentDetails(new Page<RepayPlanVo>(request, response),rpv);
	       
	       return renderString(response, repayplans);
	}
	
	/**
	 * 
	 * TODO	  新建债权列表
	 * @param tBorrow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "newBorrow")
	public String newBorrow(TBorrow tBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		String deadline = tBorrow.getDeadline();
		if(null != deadline && !"24".equals(deadline) && !"".equals(deadline)){
			String[] split = deadline.split(",");
			tBorrow.setBeginDeadline(Integer.valueOf(split[0]));
			tBorrow.setEndDeadline(Integer.valueOf(split[1]));
		}
		if("24".equals(deadline)){
			tBorrow.setBeginDeadline(24);
			tBorrow.setEndDeadline(99999);
		}
		Page<TBorrow> page = tBorrowService.findNewPage(new Page<TBorrow>(request, response,-1), tBorrow);
		BigDecimal countMount = tBorrowService.findcountMount(tBorrow);
		if(countMount==null){
			countMount = new BigDecimal(0.00);
		}
		model.addAttribute("countMount", countMount);
		model.addAttribute("tBorrow", tBorrow);
		model.addAttribute("page", page);
		String ishidden = request.getParameter("ishidden");
		model.addAttribute("ishidden", ishidden);
		return "modules/borrow/newBorrow";
	}
	
	@RequestMapping(value="showCheck")
	@ResponseBody
	public Page<TBorrow> showCheck(TBorrow tBorrow, HttpServletRequest request, HttpServletResponse response) {
		String deadline = tBorrow.getDeadline();
		if(null != deadline && !"24".equals(deadline) && !"".equals(deadline)){
			String[] split = deadline.split(",");
			tBorrow.setBeginDeadline(Integer.valueOf(split[0]));
			tBorrow.setEndDeadline(Integer.valueOf(split[1]));
		}
		Page<TBorrow> page = tBorrowService.findNewPage(new Page<TBorrow>(request, response), tBorrow);
		return page;
	}
	
	@RequestMapping(value="changeBorrowstatus")
	@ResponseBody
	public String changeBorrowstatus(String borrowstatus,String borrowId,String yuanyin,String openBorrowType,String openBorrowDate) {
		if("3".equals(borrowstatus)){
				tBorrowService.changeBorrowstatus("3",borrowId);
				//如果之前有写入未通过原因，先删除
				tBorrowService.deleteSuggestByBorrowId(borrowId);
				//将未通过原因记录入库
				String auditStatus = "nopass";
				tBorrowService.writeSuggest(borrowId,yuanyin,auditStatus);
			}
			if("4".equals(borrowstatus)){
				//创建标的
				try{
					Date d = new Date();
					String openborrowdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
					SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMdd");
					String openBorrowDateIntf =  sdf.format(d);
					if("1".equals(openBorrowType)){
						openborrowdate = openBorrowDate;
						openBorrowDateIntf = sdf.format(sdf.parse(openBorrowDate));
					}
					//String uuid = UUIDUtil.genUUIDString();//每回生成新的请求流水号
					String uuid = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+String.valueOf((int)((Math.random()*9+1)*100000));
					TBorrow tb = tBorrowService.get(borrowId);
					String hkType = tb.getPayMethod();//借款方式(debx等额本息，ycx一次性还款，dqhb到期还本)
					if("debx".equals(hkType)) hkType = "FIXED_PAYMENT_MORTGAGE";
					if("ycx".equals(hkType)) hkType = "ONE_TIME_SERVICING";
					if("dqhb".equals(hkType)) hkType = "FIRSEINTREST_LASTPRICIPAL";
					Float  anualr = Float.parseFloat(tb.getAnualrate())/100;
					//String createBorrow = BussinessPay.createBorrow(uuid, tb.getUserid(), tb.getBorrowcode(), tb.getBorrowamount(), tb.getBorrowtitle(), "test", "STANDARDPOWDER", String.valueOf(anualr), hkType);
					ReqUrlPreUUID reqUrl = new ReqUrlPreUUID();
					String userId = tb.getUserid();
					TUser tUserObject = tUserService.get(userId);//tBorrowService.getBankCardByUserId(userId);
					//HashMap<String,Object> bwListObject = BussinessPayHx.createBorrowByLend(tb.getName(),tUserObject.getACNO(), tb.getBorrowamount());
					List<HashMap<String,Object>> bwListObject = BussinessPayHx.createBorrowByLend(tUserObject.getRealname(),tUserObject.getACNO(), tb.getBorrowamount());
					
					String INVESTBEGINDATE = openBorrowDateIntf;//sdf.format(tb.getOpenborrowdate());
					Integer INVESTRANGE = Integer.parseInt(tb.getRaiseterm());
					Calendar ca = Calendar.getInstance();  
					ca.add(Calendar.DATE, INVESTRANGE);// 30为增加的天数，可以改变的  
					String INVESTENDDATE = sdf.format(ca.getTime());
					String createBorrow = BussinessPayHx.createBorrow(reqUrl.GetRequestNo("2031"),tb.getBorrowcode(),tb.getBorrowcode(),tb.getBorrowamount(),tb.getBorrowtitle(),tb.getAnualrate(),INVESTBEGINDATE,INVESTENDDATE,INVESTRANGE.toString(),"0",bwListObject);
					logger.debug("审核通过请求创建标的接口请求数据===1====>"+createBorrow);
					String resInterface = HttpUtils.sendPostMessage(BussinessPayHx.reqUrlBorrowCreate, BussinessPayHx.reqObject("R2031", "v0001", createBorrow, "PC"));
					logger.debug("审核通过请求创建标的接口返回===2====>"+resInterface);
					JSONObject jsonObject = (JSONObject)JSONObject.parse(resInterface);
					//Map<String,Object>  jsonObject = JSONObject.parseObject(resInterface,Map.class);
					JSONObject dataObject= (JSONObject)jsonObject.get("data");
					String errorCode = (String)dataObject.get("errorCode");
					if("0".equals(errorCode)){
						String createBorrowRepeat = BussinessPayHx.createBorrow(reqUrl.GetRequestNo("2031"),tb.getBorrowcode(),tb.getBorrowcode(),tb.getBorrowamount(),tb.getBorrowtitle(),tb.getAnualrate(),INVESTBEGINDATE,INVESTENDDATE,INVESTRANGE.toString(),"0",bwListObject);
						logger.debug("审核通过请求创建标的接口请求数据===1repeat====>"+createBorrowRepeat);
						String resInterfaceRepeat = HttpUtils.sendPostMessage(BussinessPayHx.reqUrlBorrowCreate, BussinessPayHx.reqObject("R2031", "v0001", createBorrowRepeat, "PC"));
						JSONObject jsonObjectRepeat = (JSONObject)JSONObject.parse(resInterfaceRepeat);
						//Map<String,Object>  jsonObject = JSONObject.parseObject(resInterface,Map.class);
						JSONObject dataObjectRepeat= (JSONObject)jsonObjectRepeat.get("data");
						String errorCodeRepeat = (String)dataObjectRepeat.get("errorCode");
						if(!"EAS020420026".equals(errorCodeRepeat)){
							errorCode = errorCodeRepeat;
							String errorMsg= (String)dataObjectRepeat.get("errorMsg");
							logger.debug("审核通过请求创建标的接口错误提示XXXXXXX3XXXXXXX>>>>>>"+errorMsg);
							return "no";
						}
					}
					if(!"EAS020420026".equals(errorCode)&&!"0".equals(errorCode)){
						String errorMsg= (String)jsonObject.get("errorMsg");
						logger.debug("审核通过请求创建标的接口错误提示XXXXXXX4XXXXXXX>>>>>>"+errorMsg+"============"+jsonObject.get("errorCode"));
						return "no";
					}
					tBorrowService.changeBorrowstatus("9",borrowId);
					tBorrowService.updateBorrowOpenBorrowDate(openborrowdate,borrowId);
					
					 //成功调用
					  HashMap<String,Object> paramMap = new HashMap<String,Object>();
					  paramMap.put("type", "9");
					  paramMap.put("remark", "创建标的");
					  paramMap.put("applyId", tb.getBorrowcode());
					  paramMap.put("borrowStatus", "9");
					  paramMap.put("operatorName", "admin");
					  paramMap.put("foreign_password", BalancePay.foreignPassword);
					  String paramJson = JSONArray.toJSONString(paramMap);
					  logger.info("创建标的调用线下接口数据请求===============>{}", paramJson);
					  String resString = DataHttpClient.postData(BalancePay.borrowListFkUrl, paramJson, "POST");
					  logger.info("创建标的调用线下接口数据返回===============>{}", resString); 
					
					String mobile = tinnerReffereeService.get(tb.getUserid()).getMobile();
					Map<String,Object> mapObjct = new HashMap<String,Object>();
					mapObjct.put("receiver", "3");		
					mapObjct.put("message_type", "10");	
					Map<String,Object> messageObject = messageModulesService.getMessage(mapObjct);
					if(messageObject!=null){
						String  message_content = (String)messageObject.get("message_content");
						String  message_status = (String)messageObject.get("message_status");
						if("1".equals(message_status)){
							String  name = tinnerReffereeService.get(tb.getUserid()).getRealname();
							String  sex = tinnerReffereeService.get(tb.getUserid()).getSex();
							if("0".equals(sex)) sex = "女士";
							if("1".equals(sex)) sex = "先生";
							message_content = message_content.replace("[title]", tb.getBorrowcode()).replace("[name]", name+sex);
							SendSms.sendSmsMesage(message_content, mobile);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "no";
				}
			}
			return "yes";
	}
	
	@RequestMapping(value="changeBorrowListStatus")
	@ResponseBody
	public String changeBorrowListstatus(String borrowstatus,String borrowListId,String yuanyin,String openBorrowType,String openBorrowDate) {
		if("2".equals(borrowstatus)){
			List<Map<String, Object>> subBorrowList=auditBorrowListService.getSubBorrowList(borrowListId);
			auditBorrowListService.changeBorrowListStatus("2",borrowListId);
			//将未通过原因记录入库
			String auditStatus = "nopass";
			auditBorrowListService.writeSuggest(borrowListId,yuanyin,auditStatus);
			//
			for (Map<String, Object> map : subBorrowList) {
				String borrowId=(String) map.get("borrowId");
				changeBorrowstatus(borrowstatus, borrowId, yuanyin, openBorrowType, openBorrowDate);
			}
		}
		if("4".equals(borrowstatus)){
			List<Map<String, Object>> subBorrowList=auditBorrowListService.getSubFailBorrowList(borrowListId);
			//普享标集合状态更新为审核中
			//auditBorrowListService.changeBorrowListStatus("3",borrowListId);
			
			boolean flag = true;
			for (Map<String, Object> map : subBorrowList) {
				String borrowId=(String) map.get("borrowId");
				String res = changeBorrowstatus(borrowstatus, borrowId, yuanyin, openBorrowType, openBorrowDate);
				if("no".equals(res)){
					tBorrowService.changeBorrowstatus("8",borrowId);//审核失败置成8--发标审核失败（打包债权后审核）
					flag = false;
				}
			}
			if(flag){//全部成功
				auditBorrowListService.changeBorrowListStatus("4",borrowListId);
			}else{
				auditBorrowListService.changeBorrowListStatus("3",borrowListId);
			}
			
			if(null!=openBorrowType&&"1".equals(openBorrowType)){
				auditBorrowListService.updateOpenBorrowDate(openBorrowDate,borrowListId);
			}else{
				openBorrowDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				auditBorrowListService.updateOpenBorrowDate(openBorrowDate,borrowListId);
			}
			
		}
		return "yes";
	}
	
	@RequestMapping(value="changeBorrowStatusAgain")
	@ResponseBody
	public String changeBorrowStatusAgain(String borrowId) {
		Map<String, Object> map = tBorrowService.findBorrowAuditStatus(borrowId);
		//return "yes";
		return changeBorrowstatus("4", borrowId, "","1", map.get("openBorrowDate").toString());
	}
	
//	public static void main(String[] args) {
//		String resString = "{\"userDevice\":\"PC\",\"status\":\"0\",\"backCode\":\"0\",\"data\":[{\"description\":\"操作成功\",\"code\":\"0\",\"preTransactionNo\":\"723846c2c11e4a68b1a46fe225edffb9\"}],\"runningNumber\":\"20161208164147824932\",\"code\":\"R2029\",\"msg\":\"调用成功\",\"payServiceType\":2,\"version\":\"v0001\"}";
//		JSONObject  mapLbObject = JSONObject.parseObject(resString);
//		String lbStatus = (String)mapLbObject.get("status");
//		boolean resFlag = true;
//		if("0".equals(lbStatus)){
//			JSONArray lbListObject = (JSONArray)mapLbObject.get("data");
//			for (int i = 0; i < lbListObject.size(); i++) {
//				JSONObject objectInvest = (JSONObject)lbListObject.get(i);
//				if(!"0".equals(objectInvest.get("code"))){
//					resFlag = false;
//				}else{
//					System.out.println("1111");
//
//				}
//			}
//		}
//	}

}
