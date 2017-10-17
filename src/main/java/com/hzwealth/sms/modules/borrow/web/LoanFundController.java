package com.hzwealth.sms.modules.borrow.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.mapper.JsonMapper;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.IdGen;
import com.hzwealth.sms.common.utils.PropertiesLoader;
import com.hzwealth.sms.common.utils.UploadUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.BorrowPicVo;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;
import com.hzwealth.sms.modules.borrow.service.LoanFundService;
@Controller
@RequestMapping(value="${adminPath}/borrow/loanFund")
public class LoanFundController extends BaseController{
	private static final PropertiesLoader property = new PropertiesLoader("hzwd.properties");
  @Autowired
  private LoanFundService loanFundService;
  /**
   * 去上传标的，及回款计划
   * @param request
   * @return
   */
  @RequestMapping("/toLoan")
  public String toLoan(@ModelAttribute TBorrow tBorrow,HttpServletRequest request,HttpServletResponse response,Model model){
    Page<TBorrow> loanList = loanFundService.getLoanList(new Page<TBorrow>(request, response),tBorrow);
    Map<String, Integer> countMap = loanFundService.getLoanCountList();
    model.addAttribute("tBorrow", tBorrow);
    model.addAttribute("page",loanList);
    model.addAttribute("countMap", countMap);
    return "modules/borrow/loanFundList";
  }
  
  /**
   * 导入标的信息
   */
  @RequestMapping("/loanInfo")
  @ResponseBody
  public void impLoanInfo(MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
    List<TBorrow> borrowList =null;
    Integer result = 0;
    try {
      if(file.getSize()>0){
        borrowList = this.readExcelBorrowList(file.getInputStream(),1);//从第二行开始读取
        result = loanFundService.saveBorrowInfo(borrowList);
        renderString(response, result);//result=0 失败
      }
    } catch (IOException e) {
      e.printStackTrace();
      logger.info("导入标的信息异常："+e.toString()+";总个数："+borrowList.size());
    }
  }
  
  
  /**
   * 导入还款计划
   */
  @RequestMapping("/billPlan")
  @ResponseBody
  public void impBillPlan(MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
      Integer result =0;
      if(file.getSize()>0){
        List<TBorrowBillplan> billplans = null;
        try {
          billplans = this.readExcel2List(file.getInputStream(),1);
          for (TBorrowBillplan tBorrowBillplan : billplans) {
            if(!loanFundService.isRepeat(tBorrowBillplan.getApplyId())){
              result = loanFundService.saveBillPlan(tBorrowBillplan);
            }else{
              result=2;
            }
          }
          renderString(response, result);
        } catch (IOException e) {
          e.printStackTrace();
          logger.info("导入回款计划异常："+e.toString()+";总个数："+billplans.size());
        }
      }
  }
  @RequestMapping("/loanDetail")
  public String toLoanDetail(HttpServletRequest request,HttpServletResponse response,Model model){
    Map<String,Object> paramMap = new HashMap<String, Object>();
    String loannumber = (String) request.getParameter("loannumber");
    String borrowId = (String) request.getParameter("borrowId");
    paramMap.put("loannumber", loannumber);
    paramMap.put("borrowId", borrowId);
    TBorrowBillplan billplan = new TBorrowBillplan();
    billplan.setApplyId(loannumber);
    //获取标的信息
    TBorrow tBorrow = loanFundService.findTBorrowById(paramMap);
    //获取还款计划
    Page<TBorrowBillplan> page = loanFundService.queryBilPlanByBorrowId(new Page<TBorrowBillplan>(request, response),billplan);
    model.addAttribute("tBorrow", tBorrow);
    model.addAttribute("billplans", page);
    Map<String, List<BorrowPicVo>> picMap = loanFundService.getBorrowPic(borrowId);
  		model.addAttribute("idCardList", picMap.get("idCardList"));
  		model.addAttribute("workList", picMap.get("workList"));
  		model.addAttribute("addressList",  picMap.get("addressList"));
  		model.addAttribute("houseList",  picMap.get("houseList"));
  		model.addAttribute("creditList",  picMap.get("creditList"));
  		model.addAttribute("incomeList",  picMap.get("incomeList"));
  		model.addAttribute("businessAddressList",  picMap.get("businessAddressList"));
    String flag=(String)request.getParameter("flag");
    if("1".equals(flag)){
      return "modules/borrow/loanFundDetail";
    }else {
      return "modules/borrow/loanFundEdit";
    }
  }
  @RequestMapping("/uploadImg")
  @ResponseBody
  public void uploadImg(@RequestParam("file") MultipartFile[] file,HttpServletRequest request,HttpServletResponse response){
	  String path = property.getProperty("web.static.banner");
	  	InputStream inputStream = null;
	  	String fileFileName = "";
	  	String relative="/static/upload/";
	  	if("0".equals( property.getProperty("isTest"))){
	  		path = path+"/loanInfo/";//生产
	  	}else {
			path=request.getSession().getServletContext().getRealPath("/")+relative;
		}
		List<BorrowPicVo> rsps = new ArrayList<BorrowPicVo>();
		BorrowPicVo rsp = null;
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			if (file != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				for(int i=0;i<file.length;i++){
					rsp = new BorrowPicVo();
					fileFileName=file[i].getOriginalFilename();
					String fileType = fileFileName.substring(fileFileName.indexOf(".")+1).toLowerCase();
					if("jpg".equals(fileType)||"jpeg".equals(fileType)
							||"gif".equals(fileType)||"png".equals(fileType)){
						long fileSize = file[i].getSize();
						BigDecimal unit = BigDecimal.valueOf(1024);
						BigDecimal size = BigDecimal.valueOf(fileSize).divide(unit.multiply(unit));
						if(BigDecimal.valueOf(0.15).compareTo(size)>=0){
							String  fileName = sdf.format(new Date())+ fileFileName.substring(fileFileName.indexOf("."));
							fileName = fileName.replace("tmp", "jpg");
							// 创建文件 /
							File dirPath = new File(path);
							if (!dirPath.exists()) {
								dirPath.mkdir();
							}
							inputStream = file[i].getInputStream();
							UploadUtils.copyFile(inputStream, path, fileName);
							//							String filepath = "http://www.huizhongwd.com"+"/images/" + fileName;
							String filepath =  relative + fileName;
							rsp.setCode("0");
							//							rsp.setPic_type(pic_type);
							rsp.setPicurl(filepath);
							rsp.setMsg("上传成功！");
							rsp.setFileName(fileName);
						}else{
							rsp.setCode("1");
							rsp.setMsg("文件过大");
						}
					}else{
						rsp.setCode("1");
						rsp.setMsg("不是png、jpg、jpeg、gif类型");
					}
					rsps.add(rsp);
				}
				
			} else {
				rsp = new BorrowPicVo();
				rsp.setCode("1");
				rsp.setMsg("上传失败");
				rsps.add(rsp);
			}	
		} catch (Exception e) {
			rsp = new BorrowPicVo();
			e.printStackTrace();
			rsp.setCode("1");
			rsp.setMsg("上传失败");
			rsps.add(rsp);
		} finally {
				renderString(response,rsps);
		}
  }
  @RequestMapping("/editLoanInfo")
  public String editLoanInfo(HttpServletRequest request){
		loanFundService.editBorrow(request);
    return "redirect:" + Global.getAdminPath() + "/borrow/loanFund/toLoan/?repage";
  }
  @RequestMapping("/delPic")
  @ResponseBody
  public String delPic(HttpServletRequest request){
	  String id = (String) request.getParameter("id");
	  Integer count =0;
		try {
			count = loanFundService.delPic(id);
			
		} catch (Exception e) {
			logger.error("删除图片报错",e);
			
		}
		return JsonMapper.toJsonString(count);
  }
  
  /**
   * 读取excel borrow
   * @return borrow类
   * */
  @SuppressWarnings("unused")
  private List<TBorrow> readExcelBorrowList(InputStream inputStream,Integer startRow) {
    List<TBorrow> borrows = new ArrayList<TBorrow>();
    //Principal principal = UserUtils.getPrincipal();
      try {
        Workbook wb = Workbook.getWorkbook(inputStream); // 从文件流中获取Excel工作区对象（WorkBook）
        Sheet sheet = wb.getSheet(0);   // 从工作区中取得页（Sheet）
        // 循环打印Excel表中的内容 循环row
        logger.debug("上传文件一共" + sheet.getRows() + "行");
        System.out.println(sheet.getRows());
        TBorrow tBorrow=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = startRow; i < sheet.getRows(); i++) {
          Cell[] cells = sheet.getRow(i); 
          tBorrow =new TBorrow();
          tBorrow.setBorrowId(IdGen.uuid());
          tBorrow.setStatus("0");
          tBorrow.setBorrowcode(cells[0].getContents());
          tBorrow.setBorrowtitle(cells[1].getContents());
          tBorrow.setBorrowamount(cells[2].getContents());
          tBorrow.setAnualrate(cells[3].getContents());
          tBorrow.setMintendersum(cells[4].getContents());
          tBorrow.setMaxtendersum(cells[5].getContents());
          tBorrow.setUserid(cells[6].getContents());
          tBorrow.setMobile(cells[7].getContents());
          tBorrow.setServicefree(cells[8].getContents());
          tBorrow.setContractsigningtime(sdf.parse(cells[9].getContents().toString()));
          tBorrow.setContractcode(cells[10].getContents());
          tBorrow.setProductcode(cells[11].getContents());
          tBorrow.setRepaymentdate(cells[12].getContents());
          tBorrow.setMonthinterest(cells[13].getContents());
          tBorrow.setMonthcapital(cells[14].getContents());
          tBorrow.setMonthprepaymentamount(cells[15].getContents());
          tBorrow.setGiveamount(cells[16].getContents());
          tBorrow.setMonthmanagementfee(cells[17].getContents());
          tBorrow.setServicecharge(cells[18].getContents());
          tBorrow.setBusinesschannel(cells[19].getContents());
          tBorrow.setLoannumber(cells[20].getContents());
          tBorrow.setBcbizdeptid(cells[21].getContents());
          tBorrow.setPayMethod(cells[22].getContents());
          tBorrow.setBorrowtype(cells[23].getContents());
          tBorrow.setCriticalid(cells[24].getContents());
          tBorrow.setBztype(cells[25].getContents());
          tBorrow.setLoantime(sdf.parse(cells[26].getContents()));
          tBorrow.setUsageofloan(cells[27].getContents());
          tBorrow.setName(cells[28].getContents());
          tBorrow.setIdcardno(cells[29].getContents());
          tBorrow.setBorrowCustomerCode(cells[30].getContents());
          tBorrow.setBorrowAccCode(cells[31].getContents());
          tBorrow.setOpenBankCode(cells[32].getContents());
          tBorrow.setCardno(cells[33].getContents());
          tBorrow.setCreditlevel(cells[34].getContents());
          tBorrow.setCity(cells[35].getContents());
          tBorrow.setAge(cells[36].getContents());
          tBorrow.setSex(cells[37].getContents());
          tBorrow.setAssetsliabilities(cells[38].getContents());
          tBorrow.setRepaysource(cells[39].getContents());
          tBorrow.setAnnualincome(cells[40].getContents());
          tBorrow.setbBName(cells[41].getContents());
          tBorrow.setType("1");
          borrows.add(tBorrow);
      }
     
    } catch (Exception e) {
      e.printStackTrace();
        logger.info("读取上传标的信息异常："+e.toString()+";总个数："+borrows.size());
    } 
      return borrows;
      
  }
  @SuppressWarnings("unused")
  private List<TBorrowBillplan> readExcel2List(InputStream inputStream,Integer startRow) {
    List<TBorrowBillplan> billplans = new ArrayList<TBorrowBillplan>();
    //Principal principal = UserUtils.getPrincipal();
      try {
         Workbook wb = Workbook.getWorkbook(inputStream);
      //  Workbook wb = Workbook.getWorkbook(file); // 从文件流中获取Excel工作区对象（WorkBook）
        Sheet sheet = wb.getSheet(0);   // 从工作区中取得页（Sheet）
        // 循环打印Excel表中的内容 循环row
        logger.debug("上传文件一共" + sheet.getRows() + "行");
        System.out.println(sheet.getRows());
        TBorrowBillplan billplan=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = startRow; i < sheet.getRows(); i++) {
          Cell[] cells = sheet.getRow(i); 
          billplan = new TBorrowBillplan();
          billplan.setId(IdGen.uuid());
          billplan.setApplyId(cells[0].getContents());
          billplan.setPeriod(cells[1].getContents());
          billplan.setMonthlyPaymentOrigin(cells[2].getContents());
          billplan.setMonthlyPaymentActual(cells[3].getContents());
          billplan.setManageFee(cells[4].getContents());
          billplan.setFailsChargeOrigin(cells[5].getContents());
          billplan.setFailsChargeActual(cells[6].getContents());
          billplan.setLateChargeOrigin(cells[7].getContents());
          billplan.setLateChargeActual(cells[8].getContents());
          billplan.setRepayStatus(cells[9].getContents());
          billplan.setRepayTime(sdf.parse(cells[10].getContents()));
          billplan.setPlanRepayAmount((cells[11].getContents().equals("null")||cells[11].getContents()==null)?"0.00":cells[11].getContents());
          billplan.setMonthCapital(cells[12].getContents());
          billplan.setMonthInterest(cells[13].getContents());
          billplan.setContractId(cells[14].getContents());
          billplan.setNoallotamount((cells[15].getContents().equals("null")||cells[15].getContents()==null)?"0.00":cells[15].getContents());
          billplan.setRemainsPrincipal(cells[16].getContents());
          billplan.setPrepaymentAmount((cells[17].getContents().equals("null")||cells[17].getContents()==null)?"0.00":cells[17].getContents());
          billplan.setPrepaymentFailscharge((cells[18].getContents().equals("null")||cells[18].getContents()==null)?"0.00":cells[18].getContents());
          billplan.setRollOutId(cells[19].getContents());
          billplan.setIsOverdue(cells[20].getContents()=="falsse"?"0":"1");
          if(!("null".equals(cells[21].getContents()) || "".equals(cells[21].getContents()))){
            billplan.setLatePaymentTime(sdf.parse(cells[21].getContents()));
          }
          billplan.setParkingFee((cells[22].getContents().equals("null")||cells[22].getContents()==null)?"0.00":cells[22].getContents());
          billplan.setCreateDate(new Date());
          billplans.add(billplan);
        }
     
      }catch (Exception e) {
        e.printStackTrace();
        logger.info("读取上传回款计划异常："+e.toString()+";总个数："+billplans.size());
      }
      return billplans;
  }
  
  
}
