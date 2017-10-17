/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.invest.web;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.Borrow;
import com.hzwealth.sms.modules.invest.entity.Invest;
import com.hzwealth.sms.modules.invest.service.InvestService;
import com.hzwealth.sms.modules.sys.utils.DictUtils;

/**
 * 出借记录Controller
 * @author yansy
 * @version 2016-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/invest/invest")
public class InvestController extends BaseController {

	@Autowired
	private InvestService investService;
	
	@ModelAttribute
	public Invest get(@RequestParam(required=false) String id) {
		Invest entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = investService.get(id);
		}
		if (entity == null){
			entity = new Invest();
		}
		return entity;
	}
	
	//@RequiresPermissions("invest:invest:view")
	@RequestMapping(value = {"list", ""})
	public String list(Invest invest, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Invest> page = investService.findPage(new Page<Invest>(request, response), invest); 
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("beginInvesttime",request.getParameter("beginInvesttime"));
		map.put("endInvesttime",request.getParameter("endInvesttime"));
		map.put("realName",request.getParameter("realName"));
		map.put("borrowCode",request.getParameter("borrowCode"));
		BigDecimal sum = investService.findSum(map);
		model.addAttribute("sum",sum);
		model.addAttribute("page", page);
		return "modules/invest/investList";
	}
	
	@RequestMapping(value="exportuserInfo")
	public void exportuserInfo(HttpServletResponse response,Invest invest) throws IOException, ParseException {
		List<Invest> list = investService.findExcel(invest);
		/*workbook2007用XSSFWorkbook()  文件名：*. xlsx
        workbook2003用HSSFWorkbook()  文件名：*. xls*/
		 // 1先创建工作簿对
        XSSFWorkbook workbook2007 = new XSSFWorkbook(); 
         // 2创建工作表对象并命名
        XSSFSheet sheet = workbook2007.createSheet("出借记录表" );
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
       
       nameCell0.setCellValue("借款编号" );
       nameCell1.setCellValue("出借人" );
       nameCell2.setCellValue("出借类型" );
       nameCell3.setCellValue("出借金额(元)" );
       nameCell4.setCellValue("出借时间" );
       nameCell5.setCellValue("标的编号" ); 
       nameCell6.setCellValue("标的名称" ); 
       nameCell7.setCellValue("借款人" );
       nameCell8.setCellValue("还款方式" );
       nameCell9.setCellValue("出借年化利率" );
       nameCell10.setCellValue("出借期限" );
       nameCell11.setCellValue("状态" );
       //nameCell0.setCellValue("投资编号" ); 
       //nameCell6.setCellValue("类型" );
       XSSFRow row1= null;
       int i=0;
       for (i = 0; i<list.size(); i++) {
    	   Invest invest2 = list.get(i);
    	   row1 = sheet.createRow(i+1);
    	   row1.createCell(0).setCellValue(invest2.getLoanNumber());
    	   row1.createCell(1).setCellValue(invest2.getRealName());
    	   row1.createCell(2).setCellValue((invest2.getIsAutoBid().equals("1"))?"手动投标":"自动投标");
    	   
    	   NumberFormat n=NumberFormat.getInstance();
    	   String amount=n.format(new Double(invest2.getRealamount().toString()));
    	   row1.createCell(3).setCellValue(amount);
    	   String date;
		   if(null==invest2.getInvesttime()||"".equals(invest2.getInvesttime())){
			   date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		   }else{
			   date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(invest2.getInvesttime());
		   }
    	   row1.createCell(4).setCellValue(date);
           row1.createCell(5).setCellValue(invest2.getBorrowAliasNo());
           row1.createCell(6).setCellValue(invest2.getBorrowAlias());
    	   row1.createCell(7).setCellValue(invest2.getJrealName());
           row1.createCell(8).setCellValue(DictUtils.getDictLabel(invest2.getPayMethod(), "pay_method", "") );
           row1.createCell(9).setCellValue(invest2.getAnualRate()+"%");
           row1.createCell(10).setCellValue(invest2.getDeadline());
           String status="---";
           if(invest2.getInvesttype().equals("1")){
           switch (invest2.getBorrowStatus()) {
			case "4":
				status="募集中";
				break;
			case "11":
				status="已满标";
				break;
			case "7":
				status="还款中";
				break;
			case "8":
				status="已结清";
				break;
			case "12":
				status="流标中";
				break;
			default:
				break;
			}
           }
           if(invest2.getInvesttype().equals("2")){
               switch (invest2.getBorrowStatus()) {
    			case "9":
    				status="招标中";
    				break;
    			case "10":
    				status="已满标";
    				break;
    			case "11":
    				status="还款中";
    				break;
    			case "12":
    				status="已结清";
    				break;
    			case "13":
    				status="撤销中";
    				break;
    			case "14":
    				status="流标中";
    				break;
    			case "15":
    				status="已撤销";
    				break;
    			case "16":
    				status="已流标";
    				break;
    			default:
    				break;
    			}
               }
           row1.createCell(11).setCellValue(status);

         }
       // 5生成文件
       String fileName = "出借记录表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
       response.setContentType( "multipart/form-data"); 
       response.setHeader( "Content-Disposition", "attachment;filename=" +new String(fileName.getBytes(),"ISO-8859-1" ));
       OutputStream outputStream = response.getOutputStream();
      
       workbook2007.write(outputStream );
       outputStream.close();
	}
	
	//@RequiresPermissions("invest:invest:view")
	@RequestMapping(value = "form")
	public String form(Invest invest, Model model,String borrowid,String id) {
		Borrow borrow = investService.findBorrowById(borrowid);
		String deadline = investService.findDeadline(borrowid,id);
		model.addAttribute("borrow", borrow);
		model.addAttribute("deadline", deadline);
		return "modules/invest/investForm";
	}

	//@RequiresPermissions("invest:invest:edit")
	/*@RequestMapping(value = "save")
	public String save(Invest invest, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, invest)){
			return form(invest, model);
		}
		investService.save(invest);
		addMessage(redirectAttributes, "保存出借记录成功");
		return "redirect:"+Global.getAdminPath()+"/invest/invest/?repage";
	}*/
	
	//@RequiresPermissions("invest:invest:edit")
	@RequestMapping(value = "delete")
	public String delete(Invest invest, RedirectAttributes redirectAttributes) {
		investService.delete(invest);
		addMessage(redirectAttributes, "删除出借记录成功");
		return "redirect:"+Global.getAdminPath()+"/invest/invest/?repage";
	}

}
