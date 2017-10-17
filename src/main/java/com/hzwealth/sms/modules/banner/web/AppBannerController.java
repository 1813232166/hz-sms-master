/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.banner.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.PropertiesLoader;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.UUIDUtil;
import com.hzwealth.sms.common.utils.UploadUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.banner.entity.AppBanner;
import com.hzwealth.sms.modules.banner.service.AppBannerService;
import com.hzwealth.sms.modules.sys.utils.UserUtils;

/**
 * 图片bannerController
 * @author xq
 * @version 2016-10-09
 */
@Controller
@RequestMapping(value = "${adminPath}/banner/appBanner")
public class AppBannerController extends BaseController {
	/**
	 * 图片访问的基本路径
	 */
	/*public String baseurl_img="http://localhost:8080/data/";*/
	
	
	
	@Autowired
	private AppBannerService appBannerService;
	private static final PropertiesLoader property = new PropertiesLoader("sms.properties");
	@ModelAttribute
	public AppBanner get(@RequestParam(required=false) String id) {
		AppBanner entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = appBannerService.get(id);
		}
		if (entity == null){
			entity = new AppBanner();
		}
		return entity;
	}
	
	@RequiresPermissions("banner:appBanner:view")
	@RequestMapping(value = {"list", ""})
	public String list(AppBanner appBanner, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AppBanner> page = appBannerService.findPage(new Page<AppBanner>(request, response), appBanner); 
		model.addAttribute("page", page);
		//图片访问路径的前缀，存放在作用域中，便于jsp页面的获取
		String baseurl_img = property.getProperty("ip_image");
		
		model.addAttribute("baseurl_img", baseurl_img);
		return "modules/banner/appBannerList";
	}

	@RequiresPermissions("banner:appBanner:view")
	@RequestMapping(value = "form")
	public String form(AppBanner appBanner, Model model) {
		model.addAttribute("appBanner", appBanner);
		//图片访问路径的前缀，存放在作用域中，便于jsp页面的获取
		String baseurl_img = property.getProperty("ip_image");
		model.addAttribute("baseurl_img", baseurl_img);
		return "modules/banner/appBannerForm";
	}

	@RequiresPermissions("banner:appBanner:edit")
	@RequestMapping(value = "save")
	public String save(AppBanner appBanner, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, appBanner)){
			return form(appBanner, model);
		}
		appBannerService.save(appBanner);
		addMessage(redirectAttributes, "保存Banner成功");
		return "redirect:"+Global.getAdminPath()+"/banner/appBanner/?repage";
	}
	
	@RequiresPermissions("banner:appBanner:edit")
	@RequestMapping(value = "delete")
	public String delete(AppBanner appBanner, RedirectAttributes redirectAttributes) {
		appBannerService.delete(appBanner);
		addMessage(redirectAttributes, "删除Banner成功");
		return "redirect:"+Global.getAdminPath()+"/banner/appBanner/?repage";
	}

	//这个方法没有用到
	@RequestMapping("/upload")
	public String upload(@RequestParam MultipartFile[] files,RedirectAttributes redirectAttributes, 
			HttpServletRequest request) {
		
		String path = property.getProperty("web.static.banner");
		String filePath="";
		String fileName="";
		AppBanner app = new AppBanner();
		app.setCreateBy(UserUtils.getUser());
		app.setCreateDate(new Date());
		app.setCreatetime(new Date());
		app.setUpdateBy(UserUtils.getUser());
		app.setUpdateDate(new Date());
		app.setRemarks("--");
		app.setDelFlag("0");
		app.setStatus("0");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		for(MultipartFile file:files){
			//String fileId=UUIDUtil.genUUIDString();
			String contentType = file.getOriginalFilename();
			String fileType =contentType.substring(contentType.indexOf(".")+1).toLowerCase();
			fileName="banner_"+UUIDUtil.generateSortIdx()+"."+fileType;
			app.setType(fileType);
			filePath=path+ymd + "/";
			app.setPicurl(filePath+fileName);
			try {
				UploadUtils.copyFile(file.getInputStream(), filePath, fileName);
			} catch (IOException e) {
				e.printStackTrace();
				logger.info("上传Banner异常："+e.toString());
			}
			appBannerService.save(app);
			addMessage(redirectAttributes, "上传Banner成功");
		}
		
		return  "redirect:"+Global.getAdminPath()+"/banner/appBanner/?repage";
	}
	
	//文章封面图片和banner图片的上传的具体实现方法
	@RequestMapping(value="/uploadImg")
	@ResponseBody
	public String uploadImg(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String name = file.getOriginalFilename();
		
		String realPath=property.getProperty("uploadimg_baseurl");//文件上传路径，从sms.properties配置文件中获取
		
		File f2 = new File(realPath);
		if(!f2.exists()){
			f2.mkdir();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//重命名图片
		String  fileName = sdf.format(new Date())+ name.substring(name.indexOf("."));
		
		
		String path = realPath+File.separator+fileName;
		File f3 = new File(path);
		FileCopyUtils.copy(file.getBytes(), f3);
		//图片的文件夹名称
		String relativePath="/";//"image/";
		
		return relativePath+fileName;
		
		
	}
	
	//ueditor插件上传图片的controller
	@RequestMapping(value="/upload_ueditor")
	@ResponseBody
	public Map<String,String> upload_ueditor(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String name = file.getOriginalFilename();
		
		String realPath=property.getProperty("uploadimg_baseurl");//文件上传路径，从sms.properties配置文件中获取
		String baseurl_img=property.getProperty("ip_image");//文件访问基本路径，从sms.properties配置文件中获取
		
		File f2 = new File(realPath);
		if(!f2.exists()){
			f2.mkdir();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//重命名图片
		String  fileName = sdf.format(new Date())+ name.substring(name.indexOf("."));
		
		
		String path = realPath+File.separator+fileName;
		File f3 = new File(path);
		FileCopyUtils.copy(file.getBytes(), f3);
		//图片的文件夹名称
		String relativePath="/";//"image/";
		
		
		
		//返回结果信息(UEditor需要)
    	Map<String,String> map = new HashMap<String,String >();
    	//是否上传成功
    	map.put("state", "SUCCESS");
    	//现在文件名称
    	map.put("title", relativePath+fileName);
    	//文件原名称 
    	map.put("original", name);
    	//文件类型 .+后缀名
    	map.put("type", name.substring(name.indexOf(".")));
    	//文件路径
    	map.put("url", baseurl_img+relativePath+fileName);
    	//文件大小（字节数）
    	map.put("size", file.getSize()+"");

    	return map;
		
	}
	
	
}