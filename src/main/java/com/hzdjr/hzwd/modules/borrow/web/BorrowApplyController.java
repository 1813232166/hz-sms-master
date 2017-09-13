/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.borrow.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.CommonResultVo;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.PropertiesLoader;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.common.utils.UploadUtils;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApply;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyDeatil;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyNew;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyTrialQueryVo;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowApplyTrialVo;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowContacts;
import com.hzdjr.hzwd.modules.borrow.entity.BorrowPicVo;
import com.hzdjr.hzwd.modules.borrow.service.BorrowApplyService;
import com.hzdjr.hzwd.modules.borrow.service.FinalJudgmentService;
import com.hzdjr.hzwd.modules.borrow.util.FinalJudgmentBorrowException;
import com.hzdjr.hzwd.modules.common.DataHttpClient;
import com.hzdjr.hzwd.modules.match.dao.AssetDao;

/**
 * 借款申请列表Controller
 * @author jqs
 * @version 2016-11-29
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/borrowApply")
public class BorrowApplyController extends BaseController {

	protected Logger logger = LoggerFactory.getLogger(BorrowApplyController.class);
	private static final PropertiesLoader property = new PropertiesLoader("hzwd.properties");
	//上传路径
	private static final String  FilePath = property.getProperty("uploadimg_baseurl"); 
	//访问路径
	private static final String  baseUrl= property.getProperty("ip_image");  
	// 后台进件ftp公网路径
	private static final String offlinePublicUrl = property.getProperty("offline_public_url");  
	@Autowired
	private BorrowApplyService borrowApplyService;
    @Autowired
    private FinalJudgmentService finalJudgmentService;
	@Autowired
	private AssetDao assetDao;
	/**
	 * @Title: 跳转到借款管理-借款申请列表页面
	 */
	/*@RequiresPermissions("borrow:borrowApply:view")*/
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request,Model model) {
		//获取筛选条件
		String name = request.getParameter("name");
		if(name!=null){
			name=name.trim();
		}
		String mobile = request.getParameter("mobile");
		if(mobile!=null){
			mobile=mobile.trim();
		}
		String borrowstatus = request.getParameter("borrowstatus");
		String beginCreateTime = request.getParameter("beginCreateTime");
		String endCreateTime = request.getParameter("endCreateTime");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("borrowstatus", borrowstatus);
		map.put("beginCreateTime", beginCreateTime);
		map.put("endCreateTime", endCreateTime);
		
		List<BorrowApply> list1=borrowApplyService.selectAllList(map);
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=10;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<BorrowApply> page = new Page<BorrowApply>(pageNo,pageSize,list1.size(),list1);
		page.initialize();
		
		ArrayList<BorrowApply> list = new ArrayList<BorrowApply>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>list1.size()){
			end=list1.size();
		}
		for (int i = start; i < end; i++) {
			list.add(list1.get(i));
		}
		
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "modules/borrow/borrowApplyList";
	}
	/**
	 * 
	 * @Description: 导出筛选后的借款申请列表
	 */
	@RequestMapping("exportBorrowApply")
	public String exportBorrowApply(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		//获取筛选条件
		String name = request.getParameter("name");
		if(name!=null){
			name=name.trim();
		}
		String mobile = request.getParameter("mobile");
		if(mobile!=null){
			mobile=mobile.trim();
		}
		String borrowstatus = request.getParameter("borrowstatus");
		String beginCreateTime = request.getParameter("beginCreateTime");
		String endCreateTime = request.getParameter("endCreateTime");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("borrowstatus", borrowstatus);
		map.put("beginCreateTime", beginCreateTime);
		map.put("endCreateTime", endCreateTime);
		
		List<BorrowApply> list1=borrowApplyService.selectAllList(map);
		
		
		String fileName = "借款申请列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			ExportExcel excel = new ExportExcel("借款申请列表", BorrowApply.class).setDataList(list1);
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/";
		
		
	}

	/**
	 * @Title: 跳转到借款管理-借款申请详情页面
	 */
	/*@RequiresPermissions("borrow:borrowApply:view")*/
	@RequestMapping(value = "find")
	public String find(String id, HttpServletRequest request,Model model) {
			//存放访问图片IP
			model.addAttribute("IP",offlinePublicUrl);  //http://localhost:8080/data/
			 //获取前台 上传图片
			 Map<String, List<BorrowPicVo>> picMap = borrowApplyService.getshenhePic(id);
		  	 model.addAttribute("idCardList", picMap.get("idCardList"));
		  	 model.addAttribute("creditList",  picMap.get("creditList"));
		  	 model.addAttribute("workAndincomeList", picMap.get("workAndincomeList"));
			 //字典
			 Map<String, Object> dictionaryMap = borrowApplyService.findDictionaryMap();
			 model.addAttribute("dictionaryMap", dictionaryMap);
			 BorrowApplyDeatil borrowDetail = borrowApplyService.getBorrowDetail(id);
			 model.addAttribute("showFlag", "firstTrial");
			 model.addAttribute("borrowDetail", borrowDetail);
		  	 //model.addAttribute("otherList",  picMap.get("otherList"));
		  	 //model.addAttribute("mobileList",  picMap.get("mobileList"));
		return "modules/borrow/borrowApplyForm";
	}


	/*
	 * 借款申请详情页面  下载图片
	 */
	@RequestMapping(value="/borrowApplyDownLoad")
	public void borrowApplyDownLoad(String fileType,String id,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//生成的ZIP文件名为xxx.zip  
        String tmpFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+".zip";  
        byte[] buffer = new byte[1024]; 
        String strZipPath = FilePath +"//"+ tmpFileName; //存放zip压缩文件的路径
        String newFile="";
        List<File> FileList = new ArrayList<File>();
        ZipOutputStream out =null;
        //从前台获取压缩的图片
        Map<String, List<BorrowPicVo>> picMap = borrowApplyService.getshenhePic(id);
        Set<String> keySet = picMap.keySet();
        //循环 前台的所有图片
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext()){
        	String key = iterator.next();
        	List<BorrowPicVo> list = picMap.get(key);
         try {
			 out = new ZipOutputStream(new FileOutputStream(strZipPath));
			 //获取图片的url
			 this.getUrl(list,strZipPath,newFile,FileList);
	       	 
		   } catch (FileNotFoundException e) {
			e.printStackTrace();
		   } 
       	 
         }
      	 this.readFile(FileList, out, buffer);
      	 out.close();
      	 this.downFile(response, tmpFileName);
	   
	    //删除 /front下的所有生成的.zip文件
   	 	try{
	   	 	File file = new File(FilePath);
	        if(file.isDirectory()){
	      	  File[] listFiles = file.listFiles();
	      	 for(File l:listFiles){
	      		String name = l.getName();
	      		if(name.endsWith(".zip")){
	      			l.delete();
	      		}
	      	  }
	        }
   	 	}catch(Exception e){
   	 		logger.error("删除服务端的zip文件失败!",e);
   	 	}
        
	}

	/**
	 * @Title: 跳转到借款管理-借款列表页面
	 */
	@RequestMapping(value = "/borrowList")
	public String borrowList(HttpServletRequest request) {
		List<BorrowApplyNew> borrowList = new ArrayList<BorrowApplyNew>();
		ArrayList<BorrowApplyNew> borrowCObject = new ArrayList<BorrowApplyNew>();//返回的list集合
		String pageNos = request.getParameter("pageNo");
		int pageSize=10;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//获取参数
		String name = request.getParameter("name");
		if(name!=null){
			name=name.trim();
		}
		String loanNumber = request.getParameter("loanNumber");
		if(loanNumber!=null){
			loanNumber=loanNumber.trim();
		}
		String borrowCode = request.getParameter("borrowCode");
		if(borrowCode!=null){
			borrowCode=borrowCode.trim();
		}
		String mobile = request.getParameter("mobile");
		if(mobile!=null){
			mobile=mobile.trim();
		}
		String isMatch = request.getParameter("isMatch");
		String isEdit = request.getParameter("isEdit");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("loanNumber", loanNumber);
		map.put("borrowCode", borrowCode);
		map.put("isMatch", isMatch);
		map.put("isEdit", isEdit);
		map.put("mobile", mobile);
		if(isMatch!=null&&!"".equals(isMatch)&&"2".equals(isMatch)){
			borrowList=  borrowApplyService.findBorrowByCancleList(map);  
		}else{
			borrowList = borrowApplyService.findBorrowNewList(map);
		}
		//传参
		Page<BorrowApplyNew> page = new Page<BorrowApplyNew>(pageNo,pageSize,borrowList.size(),borrowList);
		page.initialize();
		
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end >borrowList.size()){
			end=borrowList.size();
		}
		for (int i = start; i < end; i++) {
			borrowCObject.add(borrowList.get(i));
		}
		request.setAttribute("page",page);
		request.setAttribute("map",map);
		request.setAttribute("borrow", borrowCObject);
		return "modules/borrow/borrowNewList";
	}
	
	/**
	 * 
	 * @Description: 导出筛选后的借款列表
	 */
	@RequestMapping("exportBorrow")
	public String exportBorrow(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
		//获取参数
		String name = request.getParameter("name");
		if(name!=null){
			name=name.trim();
		}
		String loanNumber = request.getParameter("loanNumber");
		if(loanNumber!=null){
			loanNumber=loanNumber.trim();
		}
		String borrowCode = request.getParameter("borrowCode");
		if(borrowCode!=null){
			borrowCode=borrowCode.trim();
		}
		String isMatch = request.getParameter("isMatch");
		String isEdit = request.getParameter("isEdit");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("loanNumber", loanNumber);
		map.put("borrowCode", borrowCode);
		map.put("isMatch", isMatch);
		map.put("isEdit", isEdit);
		//点击撤销查询条件
		if(isMatch!=null&&!"".equals(isMatch)&&"2".equals(isMatch)){
			List<BorrowApplyNew> borrowList =  borrowApplyService.findBorrowByCancleList(map); 
			String fileName = "借款列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			try{
				ExportExcel excel = new ExportExcel("借款列表", BorrowApplyNew.class).setDataList(borrowList);
				excel.write(response, fileName).dispose();
				return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
			}
		}else{
			List<BorrowApplyNew> borrowList = borrowApplyService.findBorrowNewList(map);
			String fileName = "借款列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			try{
				ExportExcel excel = new ExportExcel("借款列表", BorrowApplyNew.class).setDataList(borrowList);
				excel.write(response, fileName).dispose();
				return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
			}
		}
		
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/borrowList";
		
		
	}
	
	
	/**
	 * 去借款列表 详情页面
	 * 
	 */
	@RequestMapping(value="/toBorrowDetailPage")
    public String toBorrowDetailPage(String id, HttpServletRequest request,Model model){
		 //存放访问图片IP
		 model.addAttribute("IP",baseUrl);  //http://localhost:8080/data/
		 model.addAttribute("IP_FTP",offlinePublicUrl);  //图片ftp地址
		 BorrowApplyDeatil detail = borrowApplyService.selectBorrowApplyDeatilById(id);
		 List<BorrowContacts> list = borrowApplyService.selectBorrowContactsById(id);
		 detail.setList(list);
		 String provinceCode = detail.getRegistryProvince();   //省的code
		 String cityCode = detail.getRegistryCity();   //市的code
		 String quCode = detail.getRegistryQu();   //区的codepro
		 
		 String familyProvince = detail.getFamilyProvince();  //省的家庭code
		 String familyCity = detail.getFamilyCity();  //市的家庭code
		 String familyQu = detail.getFamilyQu();   //区的家庭code
		 
		 String compPro = detail.getCompanyPorvince();   //单位省
		 String compCity = detail.getCompanyCity();     //单位市
		 String companyQu = detail.getCompanyQu();   //单位区
		 
		
		 //查询户籍地址
		 HashMap<String, String> map = borrowApplyService.selectAddress(provinceCode,cityCode,quCode);
		 String province = map.get("pro");
		 String cit = map.get("cit");
		 String qu = map.get("qu");
		 //查询家庭地址
		 HashMap<String, String> familyMap = borrowApplyService.selectAddress(familyProvince,familyCity,familyQu);
		 String fpro = familyMap.get("pro");
		 String fcit = familyMap.get("cit");
		 String fqu = familyMap.get("qu");
		 //查询单位地址
		 HashMap<String, String> compMap = borrowApplyService.selectAddress(compPro,compCity,companyQu);
		 String cPro = compMap.get("pro");
		 String ccit = compMap.get("cit");
		 String cqu = compMap.get("qu");
		 //添加户籍地址
		 detail.setRegistryProvince(province);
		 detail.setRegistryCity(cit);
		 detail.setRegistryQu(qu);
		 
		 //添加家庭住址
		 detail.setFamilyProvince(fpro);
		 detail.setFamilyCity(fcit);
		 detail.setFamilyQu(fqu);
		 
		 //添加单位地址
		 detail.setCompanyPorvince(cPro);
		 detail.setCompanyCity(ccit);
		 detail.setCompanyQu(cqu);
		 
		 request.setAttribute("detail", detail);
		 
	    //这是前台上传的图片
		Map<String, List<BorrowPicVo>> picMap = borrowApplyService.getshenhePic(id);
  		model.addAttribute("idCardList1", picMap.get("idCardList"));
  		model.addAttribute("creditList1",  picMap.get("creditList"));
  		model.addAttribute("workAndincomeList1", picMap.get("workAndincomeList"));
  		//XJin update 2017-05-08
  		//model.addAttribute("otherList1",  picMap.get("otherList"));
  		//model.addAttribute("mobileList1",  picMap.get("mobileList"));
  		//查询后台上传的图片
		 Map<String, List<BorrowPicVo>> imageMap = borrowApplyService.selectBorrowImageByFlag(id);
  		//这是后台根据status 查询到的图片
  		model.addAttribute("IdCardList2",imageMap.get("IdCardList"));
  		model.addAttribute("WorkAndincomeList2",imageMap.get("WorkAndincomeList"));
  		model.addAttribute("CreditList2",imageMap.get("CreditList"));
  		//model.addAttribute("OtherList2",imageMap.get("OtherList"));
  		//model.addAttribute("MobileList2",imageMap.get("MobileList"));
		
    	return "modules/borrow/borrowDetailPage";
    }
	
	/*
	 * 
	 * 点击借款编号  进入不可编辑的借款详情页面
	 */
	@RequestMapping(value="/toBorrowDetail")
	public String toBorrowDetail(String id, HttpServletRequest request,Model model){
		
		 //存放访问图片IP
		 model.addAttribute("IP",baseUrl);  //http://localhost:8080/data/
		 model.addAttribute("IP_FTP",offlinePublicUrl);  //图片ftp地址);
		//XJin update 2017-05-08
	    //这是前台上传的图片
		Map<String, List<BorrowPicVo>> picMap = borrowApplyService.getshenhePic(id);
 		model.addAttribute("idCardList1", picMap.get("idCardList"));
 		model.addAttribute("creditList1",  picMap.get("creditList"));
 		model.addAttribute("workAndincomeList1", picMap.get("workAndincomeList"));
 		//model.addAttribute("otherList1",  picMap.get("otherList"));
 		//model.addAttribute("mobileList1",  picMap.get("mobileList"));
 		//查询后台上传的图片
		 Map<String, List<BorrowPicVo>> imageMap = borrowApplyService.selectBorrowImageByFlag(id);
 		//这是后台根据status 查询到的图片
 		model.addAttribute("IdCardList2",imageMap.get("IdCardList"));
 		model.addAttribute("WorkAndincomeList2",imageMap.get("WorkAndincomeList"));
 		model.addAttribute("CreditList2",imageMap.get("CreditList"));
		 //字典
		 Map<String, Object> dictionaryMap = borrowApplyService.findDictionaryMap();
		 model.addAttribute("dictionaryMap", dictionaryMap);
		 BorrowApplyDeatil borrowDetail = borrowApplyService.getBorrowDetail(id);
		 model.addAttribute("showFlag", "firstTrial");
		 model.addAttribute("borrowDetail", borrowDetail);
		
		return "modules/borrow/borrowDetailOnlyView";
	}
	
	
	
	
	/*
	 * 借款列表  点击 保存 更改t_borrow状态
	 */
	@RequestMapping(value="/updateBorrowStatus")
	public String updateBorrowStatus(HttpServletRequest req){
		
		borrowApplyService.updateBorrowStatus(req);
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/borrowList";
		
	}
	
	/*
	 * 上传图片  614行  设置后台上传图片的路径  当前是在/front 下
	 */
	@RequestMapping(value="/uploadImg")
	@ResponseBody
	public void uploadImg(@RequestParam("file") MultipartFile[] file,HttpServletRequest request,HttpServletResponse response){
		
	    String path = property.getProperty("web.static.banner");
	  	InputStream inputStream = null;
	  	String fileFileName = "";
	  	if("0".equals( property.getProperty("isTest"))){
	  		path = path+"/loanInfo/";//生产
	  	}else {
			path=FilePath;  //d:\\upload\\data\\manager
		}
	  	List<BorrowPicVo> rsps = new ArrayList<BorrowPicVo>();
		BorrowPicVo rsp = null;
	  	try{
	  		response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			if(file!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				for(int i=0;i<file.length;i++){
					rsp = new BorrowPicVo();
					fileFileName=file[i].getOriginalFilename();
					String fileType = fileFileName.substring(fileFileName.indexOf(".")+1).toLowerCase();
					if("jpg".equals(fileType)||"jpeg".equals(fileType)
							||"gif".equals(fileType)||"png".equals(fileType)){
						long fileSize = file[i].getSize();
						BigDecimal unit = BigDecimal.valueOf(1024);
						BigDecimal size = BigDecimal.valueOf(fileSize).divide(unit.multiply(unit));   //1M
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
									String filepath = fileName; 
									rsp.setCode("0");
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
			}else{
				
				rsp = new BorrowPicVo();
				rsp.setCode("1");
				rsp.setMsg("上传失败");
				rsps.add(rsp);
			}
	  		
	  	}catch(Exception e){
	  		rsp = new BorrowPicVo();
			e.printStackTrace();
			rsp.setCode("1");
			rsp.setMsg("上传失败");
			rsps.add(rsp);
	  	}finally{
	  		renderString(response,rsps);
	  	}
	  	
	
   }
	
	/*
	 * 申请借款列表 -处理结果
	 * 
	 */
	@RequestMapping(value="/selectResult")
	@ResponseBody
	public String selectResult(String borrowId){
		String content = borrowApplyService.findReasonById(borrowId);
		return content;
	}
	
	/*
	 * 压缩 文件
	 */
	@RequestMapping(value="/downloadZipImage")
	public void downloadZipImage(HttpServletRequest request, HttpServletResponse response,Model model,String id) throws IOException{
		
		//生成的ZIP文件名为Demo.zip  
        String tmpFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+".zip";  
        byte[] buffer = new byte[1024]; 
        String strZipPath = FilePath +"//"+tmpFileName;  //存放zip的位置
        logger.debug("图片路径存放的zip位置"+strZipPath);
        String newFile="";
        List<File> FileList = new ArrayList<File>();
        ZipOutputStream out =null;
        //从后台获取压缩的图片类型  这些都是前台上传的
        Map<String, List<BorrowPicVo>> picMap = borrowApplyService.getshenhePic(id);
        Set<String> keySet = picMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext()){
        	String key = iterator.next();
        	List<BorrowPicVo> list = picMap.get(key);
         try {
			 out = new ZipOutputStream(new FileOutputStream(strZipPath));
			 //获取每种图片的url
			 this.getUrl(list,strZipPath,newFile,FileList);
			 logger.debug("===="+strZipPath+"====="+newFile);
			 this.readFile(FileList, out, buffer);
		   } catch (FileNotFoundException e) {
			e.printStackTrace();
		   } 
       	 
         }
      	 out.close();
      	 //删除 /front下的所有生成的.zip文件
	   	 	try{
	   	 	    this.downFile(response, tmpFileName);
		   	 	File file = new File(FilePath);
		        if(file.isDirectory()){
		      	  File[] listFiles = file.listFiles();
		      	 for(File l:listFiles){
		      		String name = l.getName();
		      		if(name.endsWith(".zip")){
		      			l.delete();
		      		}
		      	  }
		        }
	   	 	}catch(Exception e){
	   	 		e.printStackTrace();
	   	 		logger.error("删除服务端的zip文件失败!",e);
	   	 	}
		}
	
	/*
	 * 下载文件
	 */
	public void downFile(HttpServletResponse response, String str){
		
		try{
			    String path = FilePath +"/"+ str;    //压缩文件.zip的路径
	            File file = new File(path); 
			    if(file.exists()){
			    	InputStream ins = new FileInputStream(path);  
	                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面  
	                OutputStream outs = response.getOutputStream();// 获取文件输出IO流  
	                BufferedOutputStream bouts = new BufferedOutputStream(outs);  
	                //response.setContentType("application/x-download");// 设置response内容的类型
	                response.setHeader(  
	                        "Content-disposition",  
	                        "attachment;filename="  
	                                + URLEncoder.encode(str, "UTF-8"));// 设置头部信息  
	                int bytesRead = 0;  
	                byte[] buffer = new byte[8192];  
	                // 开始向网络传输文件流  
	                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {  
	                    bouts.write(buffer, 0, bytesRead);  
	                }  
	                bouts.flush();// 这里一定要调用flush()方法  
	                ins.close();  
	                bins.close();  
	                outs.close();  
	                bouts.close();  
			    	
			    }
		}catch(Exception e){
			e.printStackTrace();
			logger.error("文件下载错误",e);  
			
		}
			
	}
	 /*
	  * 读取文件
	  */
	public  void readFile(List<File> FileList,ZipOutputStream out,byte[] buffer) throws IOException{
		 
		try{
			for (int i = 0; i < FileList.size(); i++) {  
	             FileInputStream fis = new FileInputStream(FileList.get(i));  
	             out.putNextEntry(new ZipEntry(FileList.get(i).getName()));  
	             //设置压缩文件内的字符编码，不然会变成乱码  
	             out.setEncoding("GBK");  
	             int len;  
	             // 读入需要下载的文件的内容，打包到zip文件  
	             while ((len = fis.read(buffer)) > 0) {  
	                 out.write(buffer, 0, len);  
	             }  
	             out.closeEntry();  
	             fis.close();  
	         }  
		}catch(Exception e){
			e.printStackTrace();
			logger.error("读取文件失败!",e);
		}
		
	}
	/*
	 * 获取图片的url  后期存放图片的路径front改变时 一定要更改 787行的l.getPicurl().substring(5);  或者用String picurl2 = l.getPicurl().substring(l.getPicurl().lastIndexOf("/"));
	 */
	public void getUrl(List<BorrowPicVo> list,String strZipPath,String newFile,List<File> FileList) throws FileNotFoundException{
	
		try {
			for (BorrowPicVo l : list) {
				//这个主要是 截取图片的名称的 front/20170116171826789.jpg  
				// l.getPicurl().substring(5)==20170116171826789.jpg
				//String picurl = l.getPicurl().substring(5);
				String picurl2 = l.getPicurl();
				if(picurl2.indexOf("/")>-1){
					picurl2 = picurl2.substring(picurl2.lastIndexOf("/"));
				}else{
					picurl2 = "/"+picurl2;
				}
				//String picurl2 = l.getPicurl().substring(l.getPicurl().lastIndexOf("/"));
				newFile = FilePath + picurl2;
				File file = new File(newFile);
				if (file.exists()) { // 判断图片在服务器是否存在
					FileList.add(file);
				}
			}

		} catch (Exception e) {
            e.printStackTrace();
			logger.error("获取图片路径失败!", e);
		}

	}
	
	/*
	 * 删除后台上传的图片（数据库删除）
	 * 
	 */
	@RequestMapping(value="/deleteImageByOwner")
	@ResponseBody
	public String deleteImageByOwner(String picUrl,String borrowId){
		String delFlag="";
		int flag = borrowApplyService.deleteImage(picUrl,borrowId);
		if(flag == 1){
			delFlag ="1";
			return delFlag;
		}
		return "";
		
	}
	/**
	 * 放款 标详情
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toFKBorrowDetail")
	public String toFKBorrowDetail(String id, HttpServletRequest request,Model model){
		
		 //存放访问图片IP
		 model.addAttribute("IP",baseUrl);  //http://localhost:8080/data/
		 model.addAttribute("IP_FTP",offlinePublicUrl);  //图片ftp地址
		 
	    //这是前台上传的图片
		Map<String, List<BorrowPicVo>> picMap = borrowApplyService.getshenhePic(id);
 		model.addAttribute("idCardList1", picMap.get("idCardList"));
 		model.addAttribute("creditList1",  picMap.get("creditList"));
 		model.addAttribute("workAndincomeList1", picMap.get("workAndincomeList"));
 		//model.addAttribute("otherList1",  picMap.get("otherList"));
 		//model.addAttribute("mobileList1",  picMap.get("mobileList"));
 		//查询后台上传的图片
		 Map<String, List<BorrowPicVo>> imageMap = borrowApplyService.selectBorrowImageByFlag(id);
 		//这是后台根据status 查询到的图片
 		model.addAttribute("IdCardList2",imageMap.get("IdCardList"));
 		model.addAttribute("WorkAndincomeList2",imageMap.get("WorkAndincomeList"));
 		model.addAttribute("CreditList2",imageMap.get("CreditList"));
 		//model.addAttribute("OtherList2",imageMap.get("OtherList"));
 		//model.addAttribute("MobileList2",imageMap.get("MobileList"));
		 //字典
		 Map<String, Object> dictionaryMap = borrowApplyService.findDictionaryMap();
		 model.addAttribute("dictionaryMap", dictionaryMap);
		 BorrowApplyDeatil borrowDetail = borrowApplyService.getBorrowDetail(id);
		 model.addAttribute("showFlag", "firstTrial");
		 model.addAttribute("borrowDetail", borrowDetail);
		return "modules/borrow/fkborrowDetailOnlyView";
	}
	
	//=============================线下进件==================================
	
	/**
	 * 
	 * @Title: firstTrial   
	 * @Description: 初审列表
	 * @param queryVo
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:07:44 PM
	 */
	@RequestMapping(value = "firstTrial")
	public ModelAndView firstTrial(BorrowApplyTrialQueryVo queryVo, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> borrowStatusMap = new LinkedHashMap<String, String>();
		borrowStatusMap.put("0", "未开户");
		borrowStatusMap.put("1", "待签约");
		borrowStatusMap.put("2", "待编辑");
		borrowStatusMap.put("3,5,17,19", "打回线下");
		borrowStatusMap.put("4", "待终审");
		//borrowStatusMap.put("5", "打回线下");
		borrowStatusMap.put("6", "终审完成");
		borrowStatusMap.put("7", "终审打回");
//		borrowStatusMap.put("15", "打回线下");
//		borrowStatusMap.put("16", "打回线下");
		//新添加
		borrowStatusMap.put("9", "募集中");
		borrowStatusMap.put("10", "待放款");
		borrowStatusMap.put("11", "还款中");
		borrowStatusMap.put("12", "正常结清");
		borrowStatusMap.put("20", "拒绝借款");
		borrowStatusMap.put("100", "提前结清");
		borrowStatusMap.put("101", "逾期中");

		queryVo.setBorrowStatusMap(borrowStatusMap);
		
		List<Map<String, String>> productTypeList = borrowApplyService.productTypeList();
		// 分页开始
		Page<BorrowApplyTrialVo> page = new Page<BorrowApplyTrialVo>(request, response);
		int countBorrowApplyTrials = borrowApplyService.countBorrowApplyTrials(queryVo);
		page = new Page<BorrowApplyTrialVo>(page.getPageNo(), page.getPageSize(), countBorrowApplyTrials);
		page.initialize();
		
		queryVo.setPageStart((page.getPageNo() - 1) * page.getPageSize());
		queryVo.setPageSize(page.getPageSize());
		queryVo.setIsExcel("no");
		List<BorrowApplyTrialVo> borrowApplyTrials = borrowApplyService.borrowApplyTrials(queryVo);
		/*int pageSize = 10;
		int pageNo = 1;
		if (StringUtils.isNotBlank(queryVo.getPageNo())) {
			pageNo = Integer.parseInt(queryVo.getPageNo());
		}
		Page<BorrowApplyTrialVo> page = new Page<BorrowApplyTrialVo>(pageNo, pageSize, borrowApplyTrials.size());
		page.initialize();
		if (pageNo > page.getTotalPage()) {
			pageNo = 1;
		}
		int start = (pageNo - 1) * page.getPageSize();
		int end = pageNo * page.getPageSize();
		if (end > borrowApplyTrials.size()) {
			end = borrowApplyTrials.size();
		}

		List<BorrowApplyTrialVo> borrowApplyTrialsTemp = new ArrayList<BorrowApplyTrialVo>();
		for (int i = start; i < end; i++) {
			borrowApplyTrialsTemp.add(borrowApplyTrials.get(i));
		}*/
		// 分页结束
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("productTypeList", productTypeList);
		modelAndView.addObject("borrowStatusMap", borrowStatusMap);
		modelAndView.addObject("queryVo", queryVo);
		modelAndView.addObject("borrowApplyTrials", borrowApplyTrials);
		modelAndView.addObject("page", page);
		modelAndView.setViewName("modules/borrow/borrowApplyFirstTrial");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: exportFirstTrial   
	 * @Description: 初审列表导出 
	 * @param queryVo
	 * @param response
	 * @param redirectAttributes
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:08:01 PM
	 */
	@RequestMapping("exportFirstTrial")
	public String exportFirstTrial(BorrowApplyTrialQueryVo queryVo, HttpServletResponse response, RedirectAttributes redirectAttributes){
		Map<String, String> borrowStatusMap = new LinkedHashMap<String, String>();
		borrowStatusMap.put("0", "未开户");
		borrowStatusMap.put("1", "待签约");
		borrowStatusMap.put("2", "待编辑");
		borrowStatusMap.put("3", "打回线下");
		borrowStatusMap.put("4", "待终审");
		borrowStatusMap.put("5", "打回线下");
		borrowStatusMap.put("6", "终审完成");
		borrowStatusMap.put("7", "终审打回");
		borrowStatusMap.put("17", "打回线下");
		borrowStatusMap.put("19", "打回线下");
		queryVo.setBorrowStatusMap(borrowStatusMap);
		queryVo.setIsExcel("yes");
		List<BorrowApplyTrialVo> borrowApplyTrials = borrowApplyService.borrowApplyTrials(queryVo);
		borrowApplyService.parseBorrowApplyTrialsForExcel(borrowApplyTrials, borrowStatusMap);
		
		String fileName = "借款初审列表" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try{
			ExportExcel excel = new ExportExcel("借款初审列表", BorrowApplyTrialVo.class).setDataList(borrowApplyTrials);
			excel.write(response, fileName).dispose();
		} catch (Exception e) {
			logger.error("导出借款初审列表失败", e);
			addMessage(redirectAttributes, "导出借款初审列表失败：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/firstTrial";
	}
	
	/**
	 * 
	 * @Title: firstTrialEidt   
	 * @Description: 初审编辑
	 * @param borrowId
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:08:13 PM
	 */
	@RequestMapping(value = "firstTrialEdit/{borrowId}")
	public ModelAndView firstTrialEidt(@PathVariable("borrowId") String borrowId){
		BorrowApplyDeatil borrowDetail = borrowApplyService.getBorrowDetail(borrowId);
		//字典
		Map<String, Object> dictionaryMap = borrowApplyService.findDictionaryMap();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("showFlag", "firstTrial");
		modelAndView.addObject("imgShowPath", baseUrl);
		modelAndView.addObject("offlineImgShowPath", offlinePublicUrl);
		modelAndView.addObject("dictionaryMap", dictionaryMap);
		modelAndView.addObject("borrowDetail", borrowDetail);
		modelAndView.setViewName("modules/borrow/borrowApplyFirstTrialEdit");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: downLoadFrontPics   
	 * @Description: 根据标的、图片类型下载图片压缩包 
	 * @param borrowId
	 * @param picType
	 * @param response
	 * @throws Throwable        
	 * @throws   
	 * @author zhf
	 * @date May 3, 2017 1:46:49 PM
	 */
	@RequestMapping(value = "downLoadFrontPics/{borrowId}/{picType}")
	public void downLoadFrontPics(@PathVariable("borrowId") String borrowId, @PathVariable("picType") String picType, HttpServletResponse response) throws Throwable{
		String timeName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".zip";
		String zipPath = borrowApplyService.getPicZip(borrowId, picType, timeName);
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(timeName, "UTF-8"));// 设置头部信息  
		InputStream in = new FileInputStream(zipPath);
		OutputStream out = response.getOutputStream();
        borrowApplyService.downFile(in, out);

		File imgDir = new File(FilePath);
		if (imgDir.isDirectory()) {
			File[] imgFiles = imgDir.listFiles();
			for (File imgFile : imgFiles) {
				String imgFileName = imgFile.getName();
				if (imgFileName.endsWith(".zip")) {
					imgFile.delete();
				}
			}
		}
	}
	
	/**
	 * 
	 * @Title: firstTrialSave   
	 * @Description: 初审保存 
	 * @param request
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:08:24 PM
	 */
	@RequestMapping(value = "firstTrialSave")
	public String firstTrialSave(HttpServletRequest request) {
		String borrowId = request.getParameter("borrowId");
		String[] idcardImg = request.getParameterValues("BImg"); // 身份证明 0
		String[] creditImg = request.getParameterValues("EImg"); // 征信证明 4
		String[] workAndIncom = request.getParameterValues("CImg"); // 工作和收入1
		String[] mobileImg = request.getParameterValues("8Img"); // 手机通话记录详单 8
		String[] other = request.getParameterValues("7Img"); // 其他证明 7
		borrowApplyService.saveImgs(idcardImg, creditImg, workAndIncom, other, mobileImg, borrowId);
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/firstTrial";
	}
	
	/**
	 * 
	 * @Title: firstTrialUpdate   
	 * @Description: 初审通过
	 * @param request
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:08:36 PM
	 */
	@RequestMapping(value = "firstTrialUpdate")
	public String firstTrialUpdate(HttpServletRequest request) {
		String borrowId = request.getParameter("borrowId");
		String[] idcardImg = request.getParameterValues("BImg"); // 身份证明 0
		String[] creditImg = request.getParameterValues("EImg"); // 征信证明 4
		String[] workAndIncom = request.getParameterValues("CImg"); // 工作和收入1
		String[] mobileImg = request.getParameterValues("8Img"); // 手机通话记录详单 8
		String[] other = request.getParameterValues("7Img"); // 其他证明 7
		String msgText = request.getParameter("msgText");
		borrowApplyService.firstTrialPass(idcardImg, creditImg, workAndIncom, other, mobileImg, borrowId, msgText);
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/firstTrial";
	}
	
	/**
	 * 
	 * @Title: firstTrialReturn   
	 * @Description: 初审打回
	 * @param request
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:08:45 PM
	 */
	@RequestMapping(value = "firstTrialReturn")
	public String firstTrialReturn(HttpServletRequest request) {
		String borrowId = request.getParameter("borrowId");
		String msgText = request.getParameter("msgText");
		borrowApplyService.firstTrialReturn(borrowId, msgText);
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/firstTrial";
	}
	
	/**
	 * 
	 * @Title: reviewTrial   
	 * @Description: 终审列表
	 * @param queryVo
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:08:54 PM
	 */
	@RequestMapping(value = "reviewTrial")
	public ModelAndView reviewTrial(BorrowApplyTrialQueryVo queryVo, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> borrowStatusMap = new LinkedHashMap<String, String>();
		borrowStatusMap.put("4", "待终审");
		borrowStatusMap.put("6", "终审完成");
		borrowStatusMap.put("7", "终审打回");
		//新添加
		borrowStatusMap.put("9", "募集中");
		borrowStatusMap.put("10", "待放款");
		borrowStatusMap.put("11", "还款中");
		borrowStatusMap.put("12", "正常结清");
		borrowStatusMap.put("20", "拒绝借款");
		borrowStatusMap.put("100", "提前结清");
		borrowStatusMap.put("101", "逾期中");
		queryVo.setBorrowStatusMap(borrowStatusMap);
		List<Map<String, String>> productTypeList = borrowApplyService.productTypeList();

		// 分页开始
		Page<BorrowApplyTrialVo> page = new Page<BorrowApplyTrialVo>(request, response);
		int countBorrowApplyTrials = borrowApplyService.countBorrowApplyTrials(queryVo);
		page = new Page<BorrowApplyTrialVo>(page.getPageNo(), page.getPageSize(), countBorrowApplyTrials);
		page.initialize();
		
		queryVo.setPageStart((page.getPageNo() - 1) * page.getPageSize());
		queryVo.setPageSize(page.getPageSize());
		queryVo.setIsExcel("no");
		List<BorrowApplyTrialVo> borrowApplyTrials = borrowApplyService.borrowApplyTrials(queryVo);
		/*List<BorrowApplyTrialVo> borrowApplyTrials = borrowApplyService.borrowApplyTrials(queryVo);
		int pageSize = 10;
		int pageNo = 1;
		if (StringUtils.isNotBlank(queryVo.getPageNo())) {
			pageNo = Integer.parseInt(queryVo.getPageNo());
		}
		Page<BorrowApplyTrialVo> page = new Page<BorrowApplyTrialVo>(pageNo, pageSize, borrowApplyTrials.size());
		page.initialize();
		if (pageNo > page.getTotalPage()) {
			pageNo = 1;
		}
		int start = (pageNo - 1) * page.getPageSize();
		int end = pageNo * page.getPageSize();
		if (end > borrowApplyTrials.size()) {
			end = borrowApplyTrials.size();
		}

		List<BorrowApplyTrialVo> borrowApplyTrialsTemp = new ArrayList<BorrowApplyTrialVo>();
		for (int i = start; i < end; i++) {
			borrowApplyTrialsTemp.add(borrowApplyTrials.get(i));
		}*/
		// 分页结束

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("productTypeList", productTypeList);
		modelAndView.addObject("borrowStatusMap", borrowStatusMap);
		modelAndView.addObject("queryVo", queryVo);
		modelAndView.addObject("borrowApplyTrials", borrowApplyTrials);
		modelAndView.addObject("page", page);
		modelAndView.setViewName("modules/borrow/borrowApplyReviewTrial");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: exportReviewTrial   
	 * @Description: 终审导出  
	 * @param queryVo
	 * @param response
	 * @param redirectAttributes
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:09:16 PM
	 */
	@RequestMapping("exportReviewTrial")
	public String exportReviewTrial(BorrowApplyTrialQueryVo queryVo, HttpServletResponse response, RedirectAttributes redirectAttributes){
		Map<String, String> borrowStatusMap = new LinkedHashMap<String, String>();
		borrowStatusMap.put("4", "待终审");
		borrowStatusMap.put("6", "终审完成");
		borrowStatusMap.put("7", "终审打回");
		queryVo.setBorrowStatusMap(borrowStatusMap);
		queryVo.setIsExcel("yes");
		List<BorrowApplyTrialVo> borrowApplyTrials = borrowApplyService.borrowApplyTrials(queryVo);
		borrowApplyService.parseBorrowApplyTrialsForExcel(borrowApplyTrials, borrowStatusMap);

		String fileName = "借款终审列表" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		try{
			ExportExcel excel = new ExportExcel("借款终审列表", BorrowApplyTrialVo.class).setDataList(borrowApplyTrials);
			excel.write(response, fileName).dispose();
		} catch (Exception e) {
			logger.error("导出借款终审列表失败", e);
			addMessage(redirectAttributes, "导出借款终审列表失败：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/reviewTrial";
	}
	
	/**
	 * 
	 * @Title: reviewTrialEidt   
	 * @Description: 终审编辑 
	 * @param borrowId
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:09:43 PM
	 */
	@RequestMapping(value = "/reviewTrialEdit")
	public String reviewTrialEidt(HttpServletRequest request){
		String borrowId = request.getParameter("borrowId"); 
		BorrowApplyDeatil borrowDetail = borrowApplyService.getBorrowDetail(borrowId);
		//字典
		Map<String, Object> dictionaryMap = borrowApplyService.findDictionaryMap();
		
/*		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("showFlag", "reviewTrial");
		modelAndView.addObject("imgShowPath", baseUrl);
		modelAndView.addObject("offlineImgShowPath", offlinePublicUrl);
		modelAndView.addObject("dictionaryMap", dictionaryMap);
		modelAndView.addObject("borrowDetail", borrowDetail);
		modelAndView.setViewName("modules/borrow/borrowApplyReviewTrialEdit");
		request.setAttribute("borrowDetail", borrowDetail);*/
		request.setAttribute("showFlag", "reviewTrial");
		request.setAttribute("imgShowPath", baseUrl);
		request.setAttribute("offlineImgShowPath", offlinePublicUrl);
		request.setAttribute("dictionaryMap", dictionaryMap);
		request.setAttribute("borrowDetail", borrowDetail);
		return "modules/borrow/borrowApplyReviewTrialEdit";
	}
	
	/**
	 * 
	 * @Title: reviewTrialUpdate   
	 * @Description: 终审通过  
	 * @param request
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:09:56 PM
	 */
	@RequestMapping(value = "reviewTrialUpdate")
	@ResponseBody
	public String reviewTrialUpdate(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String borrowId = request.getParameter("borrowId");
		String msgText = request.getParameter("msgText");		
		Map<String,Object> m= assetDao.getAssetByborrow(borrowId);
		if(m!=null){
			return JSONObject.toJSONString(new CommonResultVo(false,"终审失败，该标的已存在资产！"));
		}		
		String assetId = UUIDUtil.genUUIDString();
		try {
			finalJudgmentService.finalJudgmentSuccess(borrowId, msgText,assetId);			
		} catch (FinalJudgmentBorrowException e) {
			//addMessage(redirectAttributes, e.getIntfRetnJson());
			return JSONObject.toJSONString(new CommonResultVo(false,e.getIntfRetnJson()));
		}
		try {
			String 	capital_reset_url =Global.getConfig("calculate_single_asset_weight");
			String 	capital_reset_key =Global.getConfig("weight_reset_key");
			HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();		
			objectHashMapObject.put("foreign_password", capital_reset_key);
			objectHashMapObject.put("assetId", assetId);
			String paramJson = JSONArray.toJSONString(objectHashMapObject);
			logger.info("资产权重重置调用参数===============>{}", paramJson); 
			String resString;
			resString = DataHttpClient.postData(capital_reset_url, paramJson, "POST");
			logger.info("资产权重重置调用返回=======>{}", resString);  
		} catch (Exception e) {
			logger.debug("权重计算异常",e);
		}
		try {
			String 	do_match_by_asset =Global.getConfig("do_match_by_asset");
			String 	do_match_by_asset_key =Global.getConfig("weight_reset_key");
			HashMap<String,Object> objectHashMapObject = new HashMap<String,Object>();		
			objectHashMapObject.put("foreign_password", do_match_by_asset_key);
			objectHashMapObject.put("assetId", assetId);
			String paramJson = JSONArray.toJSONString(objectHashMapObject);
			logger.info("资产首次进入时匹配调用参数===============>{}", paramJson); 
			String resString;
			resString = DataHttpClient.postData(do_match_by_asset, paramJson, "POST");
			logger.info("资产首次进入时匹配调用返回=======>{}", resString);  
		} catch (Exception e) {
			logger.debug("资产首次进入时匹配异常",e);
		}
		return JSONObject.toJSONString(new CommonResultVo(true,"资产首次进入时匹配成功！"));
	}
	
	
	/**
	 * 
	 * @Title: reviewTrialReturn   
	 * @Description: 终审打回
	 * @param request
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 2, 2017 6:10:19 PM
	 */
	@RequestMapping(value = "reviewTrialReturn")
	public String reviewTrialReturn(HttpServletRequest request) {
		String borrowId = request.getParameter("borrowId");
		String msgText = request.getParameter("msgText");
		borrowApplyService.reviewTrialReturn(borrowId, msgText);
		return "redirect:" + Global.getAdminPath() + "/borrow/borrowApply/reviewTrial";
	}
	
	public static void main(String[] args) {
		String ss = "/ssssss";
		System.out.println(ss.substring(ss.lastIndexOf("/")));
	}

}
