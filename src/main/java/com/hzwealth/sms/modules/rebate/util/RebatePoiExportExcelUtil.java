package com.hzwealth.sms.modules.rebate.util;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.hzwealth.sms.modules.rebate.entity.ExtendUser;
/**
 * 邀请返佣导出工具类
 * @author wzb
 *
 */
public class RebatePoiExportExcelUtil {
	/**
	 * 创建表title
	 * @param sheet
	 * @param title
	 */
	public static void createHeadRow(HSSFSheet sheet,String[] title){
		if(title == null || title.length == 0){
			return;
		}
		//表头
        HSSFRow row = sheet.createRow((int)0);//创建一行  
        for(int i = 0 ; i < title.length ; i++){
        	HSSFCell cell = row.createCell(i,HSSFCell.CELL_TYPE_STRING);
        	cell.setCellValue(title[i]);
        }
        
	}
	/**
	 * 返佣独立的方法，内容
	 */
	public static void createBody(HSSFSheet sheet,List<ExtendUser> userList,ExtendUser extendUser){
		if(userList == null || userList.isEmpty()){
			return ;
		}
        for (int i = 0; i < userList.size(); i++)  
        {  
            HSSFRow row = sheet.createRow(i+1);//创建一行
            //"ID"序号
            HSSFCell c0 = row.createCell(0,HSSFCell.CELL_TYPE_STRING);
            c0.setCellValue(String.valueOf(i+1));
            //"邀请人手机号"
            HSSFCell c1 = row.createCell(1,HSSFCell.CELL_TYPE_STRING);
            c1.setCellValue(userList.get(i).getBlurUserMobile());
            //"邀请人姓名"
            HSSFCell c2 = row.createCell(2,HSSFCell.CELL_TYPE_STRING);
            c2.setCellValue(userList.get(i).getBlurUserName());
            //"邀请人类型"
            HSSFCell c3 = row.createCell(3,HSSFCell.CELL_TYPE_STRING);
            //staffId 为空时，推荐人，不为空时理财师
            String staffId = userList.get(i).getStaffId();
            String type = StringUtils.isBlank(staffId)?"推荐人":"理财师";
            c3.setCellValue(type);
            //"邀请人等级"
            HSSFCell c4 = row.createCell(4,HSSFCell.CELL_TYPE_STRING);
            String level = "";
            BigDecimal sum = userList.get(i).getSumInvestAmount().add(userList.get(i).getSumAInviteInvestAmount());
            if(StringUtils.isBlank(staffId)){
            	if(extendUser.getRakebackConfigMap().get("goldamount1") != null && sum.compareTo(extendUser.getRakebackConfigMap().get("goldamount1")) >= 0){
            		level = "金牌";
            	}else if(extendUser.getRakebackConfigMap().get("sliveramount1") != null && sum.compareTo(extendUser.getRakebackConfigMap().get("sliveramount1")) >= 0){
            		level = "银牌";
            	}else{
            		level = "普通";
            	}
            }else{
            	sum = sum.add(userList.get(i).getSumBInviteInvestAmount());
            	if(extendUser.getRakebackConfigMap().get("goldamount2") != null && sum.compareTo(extendUser.getRakebackConfigMap().get("goldamount2")) >= 0){
            		level = "金牌";
            	}else if(extendUser.getRakebackConfigMap().get("sliveramount2") != null && sum.compareTo(extendUser.getRakebackConfigMap().get("sliveramount2")) >= 0){
            		level = "银牌";
            	}else{
            		level = "普通";
            	}
            }
            c4.setCellValue(level);
            //"邀请注册人数"
            HSSFCell c5 = row.createCell(5);
            c5.setCellValue(userList.get(i).getRefferUserCount());
            //"邀请实名人数"
            HSSFCell c6 = row.createCell(6);
            c6.setCellValue(userList.get(i).getRefferIdcardCount());
            //"邀请出借人数"
            HSSFCell c7 = row.createCell(7);
            c7.setCellValue(userList.get(i).getRefferInvestCount());
            //"累计邀请出借金额（元）"
            HSSFCell c8 = row.createCell(8);
            c8.setCellValue(sum.doubleValue());
            
            //"应返金额（元）"
            HSSFCell c9 = row.createCell(9);
            c9.setCellValue(userList.get(i).getSumRebateAmount().doubleValue());
            //"已返金额（元）"
            HSSFCell c10 = row.createCell(10);
            c10.setCellValue(userList.get(i).getSumCompleteRebateAmount().doubleValue());
        }  
	}
	
}
