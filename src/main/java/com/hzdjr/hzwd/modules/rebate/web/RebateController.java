package com.hzdjr.hzwd.modules.rebate.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.rebate.entity.ExtendUser;
import com.hzdjr.hzwd.modules.rebate.service.ExtendUserService;
import com.hzdjr.hzwd.modules.rebate.service.RebateRecordService;
import com.hzdjr.hzwd.modules.rebate.util.RebatePoiExportExcelUtil;
/**
 * 邀请管理
 * @author wzb
 *
 */
@Controller
@RequestMapping("${adminPath}/rebate")
public class RebateController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(RebateRecordService.class);
	
	@Autowired
	private ExtendUserService extendUserService;
	/**
	 * 邀请管理页面查询
	 * @param request
	 * @param response
	 * @param extendUser
	 * @return
	 */
	@RequiresPermissions("rebate:inviteUser:view")
	@RequestMapping("/inviteUser")
	public ModelAndView inviteUser(HttpServletRequest request, HttpServletResponse response,ExtendUser extendUser){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("modules/rebate/invite");
		mav.addObject("extendUser", extendUser);
		mav.addObject("page", extendUserService.findInviteUserPage(new Page<ExtendUser>(request, response), extendUser));
		mav.addObject("rakeList", extendUserService.getRakeList());
		return mav;
	}
	/**
	 * 根据推荐人，或者理财师类型，获取配置的等级
	 * @param type
	 * @return
	 */
	@RequestMapping("/getRefferLevelConfig")
	@ResponseBody
	public String getRefferLevelConfig(String type){
		return JSON.toJSONString(extendUserService.getRefferLevelConfig(type));
	}
	
	/**
	 *  导出excel方法  
	 * @param request
	 * @param response
	 * @param extendUser
	 */
	 @RequestMapping("/exportExcel")  
	 public void exportExcel(HttpServletRequest request, HttpServletResponse response,ExtendUser extendUser){  
	     HttpSession session = request.getSession();  
	     session.setAttribute("state", null);  
	     // 生成提示信息，  
	     response.setContentType("application/vnd.ms-excel");  
	     String codedFileName = null;  
	     OutputStream fOut = null;  
	     try{  
	         // 进行转码，使其支持中文文件名  
	         codedFileName = java.net.URLEncoder.encode(UUIDUtil.genUUIDString(), "UTF-8");  
	         response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");  
	         // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");  
	         // 产生工作簿对象  
	         HSSFWorkbook workbook = new HSSFWorkbook();  
	         //产生工作表对象  
	         HSSFSheet sheet = workbook.createSheet();
	         
	         String[] title = {
	        		 "ID",
	        		 "邀请人手机号",
	        		 "邀请人姓名",
	        		 "邀请人类型",
	        	     "邀请人等级",
	        	     "邀请注册人数",
	        	     "邀请实名人数",
	        	     "邀请出借人数",
	        	     "累计邀请出借金额（元）",
	        	     "应返金额（元）",
	        	     "已返金额（元）"
	         };
	         //表头
	         RebatePoiExportExcelUtil.createHeadRow(sheet, title);
	         //查询出列表
	         extendUser.setExportFlag("1");
	         List<ExtendUser> userList = extendUserService.findInviteUserList(extendUser);
	         RebatePoiExportExcelUtil.createBody(sheet, userList,extendUser);
	         //写入
	         fOut = response.getOutputStream();  
	         workbook.write(fOut);  
	     }  
	     catch (UnsupportedEncodingException e1){
	    	 logger.error(e1.getMessage());
	    	 e1.printStackTrace();
	     }  
	     catch (Exception e)  
	     {
	    	 logger.error(e.getMessage());
	    	 e.printStackTrace();
	     }  
	     finally {  
	         try {  
	             fOut.flush();  
	             fOut.close();  
	         }  
	         catch (IOException e){}  
	         session.setAttribute("state", "open");  
	     }  
	     logger.info("文件生成...");  
	 }  
}
