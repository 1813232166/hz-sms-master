package com.hzdjr.hzwd.modules.usermanage.web;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.JedisUtils;
import com.hzdjr.hzwd.common.utils.PropertiesLoader;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.sys.utils.MD5Util;
import com.hzdjr.hzwd.modules.usermanage.entity.TUser;
import com.hzdjr.hzwd.modules.usermanage.entity.TUserALL;
import com.hzdjr.hzwd.modules.usermanage.service.UserManageService;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("${adminPath}/user/userManage")
public class UserManageController extends BaseController{
	@Autowired
	private UserManageService userManageService;
	/**
	 * @Description: 跳转到用户管理-用户信息页面
	 * @author jiangqishuai
	 * @date 2016年10月17日 上午10:17:10
	 */
	@RequestMapping("/userInfo")
	public String userInfo(Model model,HttpServletRequest request){
		//获取查询条件
		String phone = request.getParameter("phone");
		if(phone!=null){
			phone=phone.trim();
		}
		String uname = request.getParameter("uname");
		if(uname!=null){
			uname=uname.trim();
		}
		String beginTimes = request.getParameter("beginTimes");
		if(beginTimes!=null){
			beginTimes=beginTimes.trim();
		}
		String endTimes = request.getParameter("endTimes");
		if(endTimes!=null){
			endTimes=endTimes.trim();
		}
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("phone", phone);
		paramMap.put("uname", uname);
		paramMap.put("beginTimes", beginTimes);
		paramMap.put("endTimes", endTimes);
		paramMap.put("type", type);
		paramMap.put("status", status);
		
		
		List<TUser> userInfo1 = userManageService.getUserInfo(paramMap);
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=10;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<TUser> page = new Page<TUser>(pageNo,pageSize,userInfo1.size(),userInfo1);
		page.initialize();
		
		ArrayList<TUser> userInfo = new ArrayList<TUser>();//返回的list集合
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
		
		//包含查询参数的map对象，用于回显
		model.addAttribute("paramMap", paramMap);
		//分页的对象，用于回显
		model.addAttribute("page", page);
		//用户集合的对象，用于列表展示
		model.addAttribute("userInfo", userInfo);
		return "modules/usermanage/userInfo";
	}
	/**
	 * @Description: 导出用户信息列表
	 * @author jiangqishuai
	 * @throws UnsupportedEncodingException 
	 * @date 2016年11月23日 下午15:36:10
	 */
	@RequestMapping("/exportuserInfo")
	public String exportuserInfo(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		
		//获取查询条件
		String phone = request.getParameter("phone");
		if(phone!=null){
			phone=phone.trim();
		}
		String uname = request.getParameter("uname");
		if(uname!=null){
			uname=uname.trim();
		}
		String beginTimes = request.getParameter("beginTimes");
		if(beginTimes!=null){
			beginTimes=beginTimes.trim();
		}
		String endTimes = request.getParameter("endTimes");
		if(endTimes!=null){
			endTimes=endTimes.trim();
		}
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("phone", phone);
		paramMap.put("uname", uname);
		paramMap.put("beginTimes", beginTimes);
		paramMap.put("endTimes", endTimes);
		paramMap.put("type", type);
		paramMap.put("status", status);
		
		List<TUser> userInfo1 = userManageService.getUserInfo(paramMap);
		
		
		String fileName = "用户信息列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			
			ExportExcel excel = new ExportExcel("用户信息列表", TUser.class).setDataList(userInfo1);
			
			
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/user/userManage/userInfo";
		
		
	}
	/**
	 * @Description: 锁定用户信息
	 * @author jiangqishuai
	 * @date 2016年10月17日 下午3:05:52
	 */
	@RequestMapping("/lockUser")
	@ResponseBody
	public boolean lockUser(String id,HttpServletRequest request){
		 boolean f = userManageService.lockUser(id);
		
		return f;
	}
	/**
	 * @Description: 解锁用户信息
	 * @author jiangqishuai
	 * @date 2016年10月17日 下午3:11:53
	 */
	@RequestMapping("/unlockUser")
	@ResponseBody
	public boolean unlockUser(String id,HttpServletRequest request){
		 boolean f = userManageService.unlockUser(id);
		 
		return f;
	}
	/**
	 * @Description: 重置密码
	 * @author jiangqishuai
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @date 2016年11月23日 下午18:02:23
	 */
	@RequestMapping("/repwd")
	@ResponseBody
	public boolean repwd(String id,HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		//重置密码，MD5加密
		String p=MD5Util.getEncryptedPwd("123456");
		boolean f = userManageService.repwd(id,p);
		
		return f;
	}
	
	/**
	 * @Description: 跳转到查看-基本信息页面
	 * @author jiangqishuai
	 * @date 2016年10月17日 下午3:51:25
	 */
	@RequestMapping("/baseInfo")
	public String selectUserById(String id,Model model){
		
		TUserALL user = userManageService.selectUserById(id);
		//用户基本信息的对象，用于页面数据展示
		model.addAttribute("user",user);
		Map<String, Object> map = userManageService.queryUserAccountById(id);
		//用户资金信息的对象，用于页面数据展示
		model.addAttribute("map",map);
		return "modules/usermanage/userBasicInfor";
	}
	
	
	//这个方法没有用到
	public String getParams(HttpServletRequest request){
			//获取查询条件
			String phone = request.getParameter("phone");
			String uname = request.getParameter("uname");
			String beginTimes = request.getParameter("beginTimes");
			String endTimes = request.getParameter("endTimes");
			String type = request.getParameter("type");
			String status = request.getParameter("status");
			
			String pageNo = request.getParameter("pageNo");
			String pageSize = request.getParameter("pageSize");
			StringBuffer sb=new StringBuffer("?");
			sb.append("phone="+phone);
			sb.append("&uname="+uname);
			sb.append("&beginTimes="+beginTimes);
			sb.append("&endTimes="+endTimes);
			sb.append("&type="+type);
			sb.append("&status="+status);
			sb.append("&pageNo="+pageNo);
			sb.append("&pageSize="+pageSize);
			return sb.toString();
	}
	
	
	/**
	 * 构造redis查询（根据手机号）
	 * @param mobile
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/redisList")
	public String redisList(String mobile,Model model) {
	List<HashMap<String,String>> objectList = new ArrayList<HashMap<String,String>>();
		if(mobile!=null&&!"".equals(mobile)){
			PropertiesLoader loader = new PropertiesLoader("hzwd.properties");
			String host =	loader.getProperty("redis.host");
			int port =	Integer.parseInt(loader.getProperty("redis.port"));
			String password =	loader.getProperty("redis.password");
			Jedis  redis = new Jedis (host,port); 
	        redis.auth(password);//验证密码
	        try {
	        	Set<String> keys  =redis.keys("*");
				if(keys!=null&&keys.size()>0){
					logger.debug("查询的KEY值总数为====count=========>"+keys.size());
					Iterator<String> it=keys.iterator();
			        while(it.hasNext()){
			            String key = (String)it.next();
			            if(key.indexOf("_"+mobile)>1){
			            	HashMap<String,String> hashMapObject = new HashMap<String,String>();
			            	String getValueByKey = JedisUtils.get(key);
			            	logger.debug(key+"=======1======="+getValueByKey);
			            	String time = getValueByKey.split("/")[1];
			            	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			                Date date= new Date(Long.parseLong(time));
			                hashMapObject.put("businessName", key.split("_")[0]);//业务类型
			            	hashMapObject.put("time", sdf.format(date));//时间
			            	hashMapObject.put("code", getValueByKey.split("/")[0]);//验证码
			            	logger.debug("======2========"+time+"====="+getValueByKey.split("/")[0]+"==============="+key.split("_")[0]);
			            	objectList.add(hashMapObject);
			            }
			        }
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(redis!=null){
					redis.close();
				}
			}
		}
	   model.addAttribute("resList", objectList);
	   return "modules/usermanage/userInfoDetail";
   }
	
}
