/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrow.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.ContractSeal;
import com.hzwealth.sms.modules.borrow.service.ContractSealService;

/**
 * 签章列表Controller
 * @author l
 * @version 2016-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/contractSeal")
public class ContractSealController extends BaseController {

	@Autowired
	private ContractSealService contractSealService;
	
	@ModelAttribute
	public ContractSeal get(@RequestParam(required=false) String id) {
		ContractSeal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = contractSealService.get(id);
		}
		if (entity == null){
			entity = new ContractSeal();
		}
		return entity;
	}
	
//	@RequiresPermissions("borrow:contractSeal:view")
	@RequestMapping(value = {"list", ""})
	public String list(ContractSeal contractSeal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ContractSeal> page = contractSealService.findPage(new Page<ContractSeal>(request, response), contractSeal); 
		model.addAttribute("page", page);
		return "modules/borrow/contractSealList";
	}
	
	@RequestMapping("excelExport")
	public void excelExport(ContractSeal contractSeal, HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			List<ContractSeal> ContractSealList = contractSealService.selectContractSealExcel(contractSeal);
			/*workbook2007用XSSFWorkbook()  文件名：*. xlsx
			workbook2003用HSSFWorkbook()  文件名：*. xls*/
			 // 1先创建工作簿对
			XSSFWorkbook workbook2007 = new XSSFWorkbook(); 
			 // 2创建工作表对象并命名
			XSSFSheet sheet = workbook2007.createSheet("合同列表信息" );
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
		  nameCell0.setCellValue("合同编号" ); 
		  nameCell1.setCellValue("标的编号" ); 
		  nameCell2.setCellValue("标的名称" ); 
		  nameCell3.setCellValue("借款编号" );
		  nameCell4.setCellValue("借款人" );
		  nameCell5.setCellValue("出借人" );
		  nameCell6.setCellValue("合同金额" );
		  nameCell7.setCellValue("出借年化利率" );
		  nameCell8.setCellValue("出借期限" );
		  nameCell9.setCellValue("签订时间" );
		  nameCell10.setCellValue("类型" );
		  XSSFRow row1= null;
		  int i=0;
		  for (i = 0; i<ContractSealList.size(); i++) {
			   ContractSeal ContractSealInfo = ContractSealList.get(i);
			   row1 = sheet.createRow(i+1);
			   row1.createCell(0).setCellValue(ContractSealInfo.getContractSeal());
			   row1.createCell(1).setCellValue(ContractSealInfo.getBorrowAliasNo());
			   row1.createCell(2).setCellValue(ContractSealInfo.getBorrowAlias());
			   row1.createCell(3).setCellValue(ContractSealInfo.getLoanNumber());
			   if("1".equals(ContractSealInfo.getUserType())){
				   row1.createCell(4).setCellValue("---");
				   row1.createCell(5).setCellValue(ContractSealInfo.getCuserName());
			   }else if("2".equals(ContractSealInfo.getUserType())){
				   row1.createCell(4).setCellValue(ContractSealInfo.getUserName());
				   row1.createCell(5).setCellValue("---");
			   }else{
				   row1.createCell(4).setCellValue("---");
				   row1.createCell(5).setCellValue("---");
			   }
			   row1.createCell(5).setCellValue(ContractSealInfo.getCuserName());
			   row1.createCell(6).setCellValue(ContractSealInfo.getInvestAmount());
			   row1.createCell(7).setCellValue(ContractSealInfo.getANUALRATE());
			   row1.createCell(8).setCellValue(ContractSealInfo.getDEADLINE());
			   Date interestAccrualDate=ContractSealInfo.getInterestAccrualDate();
			   String date;
			   if(null==interestAccrualDate){
				   date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			   }else{
				   date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ContractSealInfo.getInterestAccrualDate());
			   }
			   row1.createCell(9).setCellValue(date);
			   String contractType = ContractSealInfo.getContractType()==null?"":ContractSealInfo.getContractType();
			   switch (contractType) {
			case "PX_JKXY":
				contractType = "借款协议";
				break;
			case "PX_CJZXXY":
				contractType = "出借咨询与服务协议";
				break;
			case "XYZXXY":
				contractType = "融资咨询与服务协议";
				break;
			case "HKTXH":
				contractType = "还款提醒函";
				break;
			case "PTZCXY":
				contractType = "平台注册协议";
				break;
			default:
				break;
			}
			   row1.createCell(10).setCellValue(contractType);
      }
      // 5生成文件
      String fileName = "合同列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
      response.setContentType( "multipart/form-data"); 
      response.setHeader( "Content-Disposition", "attachment;filename=" +new String(fileName.getBytes(),"ISO-8859-1" ));
      OutputStream outputStream = response.getOutputStream();
     
      workbook2007.write(outputStream );
      outputStream.close();
		} catch (UnsupportedEncodingException e) {
		 
		} catch (IOException e) {

		}
	}

	/*@RequiresPermissions("borrow:contractSeal:view")*/
	@RequestMapping(value = "contractSealDetail")
	public String contractSealDetail(ContractSeal contractSeal, Model model) {
		model.addAttribute("contractSeal", contractSeal);
		return "modules/borrow/contractDetail";
	}

	/*@RequiresPermissions("borrow:contractSeal:view")*/
	@RequestMapping(value = "form")
	public String form(ContractSeal contractSeal, Model model) {
		model.addAttribute("contractSeal", contractSeal);
		return "modules/borrow/contractSealForm";
	}

	/*@RequiresPermissions("borrow:contractSeal:edit")*/
	@RequestMapping(value = "save")
	public String save(ContractSeal contractSeal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, contractSeal)){
			return form(contractSeal, model);
		}
		contractSealService.save(contractSeal);
		addMessage(redirectAttributes, "保存签章生成成功成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/contractSeal/?repage";
	}
	
	/*@RequiresPermissions("borrow:contractSeal:edit")*/
	@RequestMapping(value = "delete")
	public String delete(ContractSeal contractSeal, RedirectAttributes redirectAttributes) {
		contractSealService.delete(contractSeal);
		addMessage(redirectAttributes, "删除签章生成成功成功");
		return "redirect:"+Global.getAdminPath()+"/borrow/contractSeal/?repage";
	}

}