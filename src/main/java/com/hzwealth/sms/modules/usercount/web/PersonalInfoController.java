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
import com.hzwealth.sms.modules.usercount.entity.UserInfo;
import com.hzwealth.sms.modules.usercount.entity.Withdraw;
import com.hzwealth.sms.modules.usercount.service.UserInfoService;

@Controller
@RequestMapping("${adminPath}/user/usercount")
public class PersonalInfoController extends BaseController{
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * @Description: 跳转到个人用户页面
	 * @author jiangqishuai
	 * @date 2016年10月12日 上午11:39:40
	 */
	@RequestMapping("/userInfo")
	public String userInfo(HttpServletRequest request,HttpServletResponse response,String beginTimes,String endTimes){
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginTimes", beginTimes);
		map.put("endTimes", endTimes);
		List<UserInfo> userInfo1 = userInfoService.getUserInfo(map);
		
		
		/**
		 * 分页开始 
		 * 传入参数(pageNo,pageSize,list.size(),list)
		 * 返回值：分页后的list集合，page.toString()
		 * 作者:jiangqishuai
		 * 时间:2016年10月18日 下午14:35:21
		 */
		String pageNos = request.getParameter("pageNo");
		int pageSize=3;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<UserInfo> page = new Page<UserInfo>(pageNo,pageSize,userInfo1.size(),userInfo1);
		page.initialize();
		
		ArrayList<UserInfo> userInfo = new ArrayList<UserInfo>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>userInfo1.size()){
			end=userInfo1.size();
		}
		for (int i = start; i < end; i++) {
			userInfo.add(userInfo1.get(i));
		}
		/**
		 * 分页结束
		 */
		
		Integer currentregsum = 0;
		Integer newusersum = 0;
		Integer investusersum = 0;
		Integer borrowusersum = 0;
		Integer loginusersum = 0;
		Integer pcregusersum = 0;
		Integer appregusersum = 0;
		for (UserInfo u : userInfo) {
			currentregsum+=new Integer(u.getCurrentreg());
			newusersum+=new Integer(u.getNewuser());
			investusersum+=new Integer(u.getInvestuser());
			borrowusersum+=new Integer(u.getBorrowuser());
			loginusersum+=new Integer(u.getLoginuser());
			pcregusersum+=new Integer(u.getPcreguser());
			appregusersum+=new Integer(u.getAppreguser());
		}
		Map<String, Integer> user = new HashMap<String, Integer>();
		user.put("currentregsum", currentregsum);
		user.put("newusersum", newusersum);
		user.put("investusersum", investusersum);
		user.put("borrowusersum", borrowusersum);
		user.put("loginusersum", loginusersum);
		user.put("pcregusersum", pcregusersum);
		user.put("appregusersum", appregusersum);
		Map<String, String> date1 = new HashMap<String, String>();
		date1.put("beginTimes", beginTimes);
		date1.put("endTimes", endTimes);
		request.setAttribute("date1", date1);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("user", user);
		request.setAttribute("page", page);
		return "modules/datacount/userInfo";
	}
	
	/**
	 * @Description: 跳转到提现统计页面
	 * @author jiangqishuai
	 * @date 2016年10月13日 上午11:02:06
	 */
	@RequestMapping("/withdraw")
	public String withdraw(HttpServletRequest request,String beginTimes,String endTimes){
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginTimes", beginTimes);
		map.put("endTimes", endTimes);
		List<Withdraw> withdraw1 = userInfoService.getWithdraw(map);
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=3;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<Withdraw> page = new Page<Withdraw>(pageNo,pageSize,withdraw1.size(),withdraw1);
		page.initialize();
		
		ArrayList<Withdraw> withdraw = new ArrayList<Withdraw>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>withdraw1.size()){
			end=withdraw1.size();
		}
		for (int i = start; i < end; i++) {
			withdraw.add(withdraw1.get(i));
		}
		
		BigDecimal withdrawamountsum = new BigDecimal(0.00);
		BigDecimal alreadyloadsum = new BigDecimal(0.00);
		BigDecimal pendingloadsum = new BigDecimal(0.00);
		Integer withdrawnumsum = 0;
		BigDecimal pcwithdrawsum = new BigDecimal(0.00);
		BigDecimal appwithdrawsum = new BigDecimal(0.00);
		for (Withdraw w : withdraw) {
			if(w.getWithdrawamount()!=null){
				withdrawamountsum=w.getWithdrawamount().add(withdrawamountsum);
			}
			if(w.getAlreadyload()!=null){
				alreadyloadsum=w.getAlreadyload().add(alreadyloadsum);
			}
			if(w.getPendingload()!=null){
				pendingloadsum=w.getPendingload().add(pendingloadsum);
			}
			if(w.getWithdrawnum()!=null){
				withdrawnumsum+=new Integer(w.getWithdrawnum());
			}
			if(w.getPcwithdraw()!=null){
				pcwithdrawsum=w.getPcwithdraw().add(pcwithdrawsum);
			}
			if(w.getAppwithdraw()!=null){
				appwithdrawsum=w.getAppwithdraw().add(appwithdrawsum);
			}
		}
		Map<String, Object> with = new HashMap<String, Object>();
		with.put("withdrawamountsum", withdrawamountsum);
		with.put("alreadyloadsum", alreadyloadsum);
		with.put("pendingloadsum", pendingloadsum);
		with.put("withdrawnumsum", withdrawnumsum);
		with.put("pcwithdrawsum", pcwithdrawsum);
		with.put("appwithdrawsum", appwithdrawsum);
		
		Map<String, String> date1 = new HashMap<String, String>();
		date1.put("beginTimes", beginTimes);
		date1.put("endTimes", endTimes);
		request.setAttribute("withdraw", withdraw);
		request.setAttribute("with", with);
		request.setAttribute("date1", date1);
		request.setAttribute("page", page);
		return "modules/datacount/withdraw";
	}
	
	/**
	 * @Description: 导出个人用户统计列表
	 * @author jiangqishuai
	 * @throws IOException 
	 * @date 2016年10月13日 下午3:22:02
	 */
	@RequestMapping("/exportuserInfo")
	public String exportuserInfo(String beginTimes,String endTimes,HttpServletResponse response,RedirectAttributes redirectAttributes){
		Map<String, String> map = new HashMap<String, String>();
		/*map.put("beginTimes", beginTimes);
		map.put("endTimes", endTimes);*///不带条件查询，导出全部数据
		List<UserInfo> userInfo = userInfoService.getUserInfo(map);
		for (int i = 0; i < userInfo.size(); i++) {
			UserInfo userInfo2 = userInfo.get(i);
			userInfo2.setNum(i+1);
		}
		Integer currentregsum = 0;
		Integer newusersum = 0;
		Integer investusersum = 0;
		Integer borrowusersum = 0;
		Integer loginusersum = 0;
		Integer pcregusersum = 0;
		Integer appregusersum = 0;
		for (UserInfo u : userInfo) {
			currentregsum+=new Integer(u.getCurrentreg());
			newusersum+=new Integer(u.getNewuser());
			investusersum+=new Integer(u.getInvestuser());
			borrowusersum+=new Integer(u.getBorrowuser());
			loginusersum+=new Integer(u.getLoginuser());
			pcregusersum+=new Integer(u.getPcreguser());
			appregusersum+=new Integer(u.getAppreguser());
		}
		String fileName = "个人用户统计列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			
			ExportExcel excel = new ExportExcel("个人用户统计列表", UserInfo.class).setDataList(userInfo);
			Row r = excel.addRow();
			excel.addCell(r, 0, "总计：",2,Class.class);
			excel.addCell(r, 1, "",2,Class.class);
			excel.addCell(r, 2, currentregsum+"",2,Class.class);
			excel.addCell(r, 3, newusersum+"",2,Class.class);
			excel.addCell(r, 4, investusersum+"",2,Class.class);
			excel.addCell(r, 5, borrowusersum+"",2,Class.class);
			excel.addCell(r, 6, loginusersum+"",2,Class.class);
			excel.addCell(r, 7, pcregusersum+"",2,Class.class);
			excel.addCell(r, 8, appregusersum+"",2,Class.class);
			
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/user/usercount/userInfo";
		
	}
	/**
	 * @Description: 导出提现统计列表
	 * @return void
	 * @author jiangqishuai
	 * @date 2016年10月13日 下午4:02:45
	 */
	@RequestMapping("/exportwithdraw")
	public String exportwithdraw(String beginTimes,String endTimes,HttpServletResponse response,RedirectAttributes redirectAttributes) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		/*map.put("beginTimes", beginTimes);
		map.put("endTimes", endTimes);*/
		List<Withdraw> withdraw = userInfoService.getWithdraw(map);
		
		for (int i = 0; i < withdraw.size(); i++) {
			Withdraw wi = withdraw.get(i);
			wi.setNum(i+1);
		}
		
		BigDecimal withdrawamountsum = new BigDecimal(0.00);
		BigDecimal alreadyloadsum = new BigDecimal(0.00);
		BigDecimal pendingloadsum = new BigDecimal(0.00);
		Integer withdrawnumsum = 0;
		BigDecimal pcwithdrawsum = new BigDecimal(0.00);
		BigDecimal appwithdrawsum = new BigDecimal(0.00);
		for (Withdraw w : withdraw) {
			if(w.getWithdrawamount()!=null){
				withdrawamountsum=w.getWithdrawamount().add(withdrawamountsum);
			}
			if(w.getAlreadyload()!=null){
				alreadyloadsum=w.getAlreadyload().add(alreadyloadsum);
			}
			if(w.getPendingload()!=null){
				pendingloadsum=w.getPendingload().add(pendingloadsum);
			}
			if(w.getWithdrawnum()!=null){
				withdrawnumsum+=new Integer(w.getWithdrawnum());
			}
			
			if(w.getPcwithdraw()!=null){
				pcwithdrawsum=w.getPcwithdraw().add(pcwithdrawsum);
			}
			if(w.getAppwithdraw()!=null){
				appwithdrawsum=w.getAppwithdraw().add(appwithdrawsum);
			}
		}
		Map<String, Object> with = new HashMap<String, Object>();
		with.put("withdrawamountsum", withdrawamountsum);
		with.put("alreadyloadsum", alreadyloadsum);
		with.put("pendingloadsum", pendingloadsum);
		with.put("withdrawnumsum", withdrawnumsum);
		with.put("pcwithdrawsum", pcwithdrawsum);
		with.put("appwithdrawsum", appwithdrawsum);
		
		String fileName = "提现统计列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			
			ExportExcel excel = new ExportExcel("提现统计列表", Withdraw.class).setDataList(withdraw);
			Row r = excel.addRow();
			excel.addCell(r, 0, "总计：",2,Class.class);
			excel.addCell(r, 1, "",2,Class.class);
			excel.addCell(r, 2, withdrawamountsum+"",2,Class.class);
			excel.addCell(r, 3, alreadyloadsum+"",2,Class.class);
			excel.addCell(r, 4, pendingloadsum+"",2,Class.class);
			excel.addCell(r, 5, withdrawnumsum+"",2,Class.class);
			excel.addCell(r, 6, pcwithdrawsum+"",2,Class.class);
			excel.addCell(r, 7, appwithdrawsum+"",2,Class.class);
			
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/user/usercount/withdraw";
		
	}

}
