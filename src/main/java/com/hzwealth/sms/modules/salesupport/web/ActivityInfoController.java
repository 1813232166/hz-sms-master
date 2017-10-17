/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.salesupport.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.PropertiesLoader;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.UUIDUtil;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.salesupport.entity.ActivityInfo;
import com.hzwealth.sms.modules.salesupport.entity.CouponStatistics;
import com.hzwealth.sms.modules.salesupport.entity.ExcelActivityInfo;
import com.hzwealth.sms.modules.salesupport.entity.ExcelCouponStatistics;
import com.hzwealth.sms.modules.salesupport.entity.TYxCoupon;
import com.hzwealth.sms.modules.salesupport.entity.TYxCouponAuditRecord;
import com.hzwealth.sms.modules.salesupport.entity.TYxCouponRelation;
import com.hzwealth.sms.modules.salesupport.service.ActivityInfoService;
import com.hzwealth.sms.modules.salesupport.service.CouponSendService;
import com.hzwealth.sms.modules.salesupport.service.CouponService;
import com.hzwealth.sms.modules.salesupport.service.TYxCouponAuditRecordService;
import com.hzwealth.sms.modules.salesupport.service.TYxCouponRelationService;
import com.hzwealth.sms.modules.sys.utils.UserUtils;

/**
 * 活动Controller
 * @author zhouzhankui
 * @version 2017-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/activityInfo")
public class ActivityInfoController extends BaseController {

	@Autowired
	private ActivityInfoService activityInfoService;
	
    @Autowired
    private CouponSendService couponSendService;
	@Autowired
	private TYxCouponRelationService couponRelationService;

	@Autowired
	private TYxCouponAuditRecordService couponAuditRecordService;
	
	
    @Autowired
    private CouponService couponService;
    //读取配置文件
	private static final PropertiesLoader property = new PropertiesLoader("hzwd.properties");
	@ModelAttribute
	public ActivityInfo get(@RequestParam(required=false) String id) {
		ActivityInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = activityInfoService.get(id);
		}
		if (entity == null){
			entity = new ActivityInfo();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(ActivityInfo activityInfo,TYxCoupon coupon,HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String   beginTimes=activityInfo.getPublishtBeginTime();
		String   endTimes=activityInfo.getPublishtEndTime();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("activityname", activityInfo.getActivityname());
		paramMap.put("beginTimes", beginTimes);
		paramMap.put("endTimes", endTimes);
	
		
		List<Map<String, Object>> couponList=activityInfoService.getCouponListByHuoDongId(activityInfo.getId());
		Page<ActivityInfo> page = activityInfoService.findPage(new Page<ActivityInfo>(request, response), activityInfo); 
		model.addAttribute("page", page);
		model.addAttribute("paramMap", paramMap);
		
		model.addAttribute("couponList", couponList);
		return "modules/salesupport/activity/activityInfoList";
	}
	

	/**
	 * 导出活动信息
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/exportActivityInfo")
	public String exportActivityInfo(ExcelActivityInfo activityInfo,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");

		
		List<ExcelActivityInfo> activityInfoList = activityInfoService.excelFindList(activityInfo); 
	
		
		String fileName = "活动信息列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		try{
			
			ExportExcel excel = new ExportExcel("活动信息列表", ExcelActivityInfo.class).setDataList(activityInfoList);
			
			excel.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/activity/activityInfo/list";
		
		
	}
    /**
     * 优惠券管理弹出列表
     * @return
     */
	@ResponseBody
    @RequestMapping("/openCouponList")
    public String couponManageList(ActivityInfo activityInfo,HttpServletRequest request,HttpServletResponse response,Model model){

    	
		List<Map<String, Object>> couponList=activityInfoService.getCouponListByHuoDongId(activityInfo.getId());
		model.addAttribute("couponList", couponList);
		
		String json=JSON.toJSON(couponList).toString();
		return json;
    }
	
    
    /**
     * 根据选择的id集合查询优惠券的信息
     * @param coupon
     * @param request
     * @param response
     * @param model
     * @return  json
     */
    @ResponseBody
	@RequestMapping(value = {"addCouponList"})
	public String addCouponList(TYxCoupon coupon,HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		
    	StringBuffer  buffer =new StringBuffer();
    	
    	String strs=request.getParameter("ids");
    	if (StringUtils.isBlank(strs)) {
			addMessage(redirectAttributes, "您没有选择数据");
			return null;
		}
    	
    	
    	String[] test=strs.split(",");
    	
    	for (int i = 0; i < test.length; i++) {
    		test[i]="'"+test[i]+"'"+",";
    		buffer.append(test[i]);
    	}
    	
    	String  ids= buffer.toString();
    	ids=ids.substring(0,ids.length()-1);
    
    	List<Map<String, Object>>  listInfo=	couponService.getCouponListByIds(ids);
		
		String json=JSON.toJSON(listInfo).toString();
	
		
		return json;
	
		

	}
	
	/**
	 * 活动新建或者修改
	 * @param activityInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(ActivityInfo activityInfo, HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon,Model model) {
		model.addAttribute("activityInfo", activityInfo);
		
		//图片访问路径的前缀，存放在作用域中，便于jsp页面的获取
		String baseurl_img = property.getProperty("ip_image");
		model.addAttribute("baseurl_img", baseurl_img);
		
		    List<TYxCoupon> couponAllList  =couponService.authSuccessList(coupon);  //所有的优惠券列表---审核之后的
		     Iterator<TYxCoupon> iterator = couponAllList.iterator();    //处理已经过期 失效的优惠券
		          
		        		while (iterator.hasNext()) {
		        		TYxCoupon  tYxCoupon  =iterator.next();
						if (null!=tYxCoupon.getEndTime() && !tYxCoupon.getEndTime().toString().equals("")) {
							 Date today =new Date();
							if (tYxCoupon.getEndTime().getTime() <today.getTime() ) {
								iterator.remove();
							}
							
						}
						
					}
		    
	    	List<Map<String, Object>>  couponSelectedList=	couponService.getCouponInfolistByMainId(activityInfo.getId());   //活动保存的优惠券
	    	
	    	for (TYxCoupon allcoup : couponAllList) {
	    		String  allcoupId = allcoup.getId();
	    		allcoup.setIsCheck("0");
	    		for (Map<String, Object> selectMap : couponSelectedList) {
	    			String   selectCouponId = String.valueOf(selectMap.get("couponId"));
	    			if(allcoupId.equals(selectCouponId)){
	    				allcoup.setIsCheck("1");
	    			}
				}
			}
	    	
		       Date currentTime = new Date();
		       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		      String currenDate = formatter.format(currentTime);
		        		       
		     model.addAttribute("currenDate", currenDate);
	    	model.addAttribute("couponAllList", couponAllList);
			
		return "modules/salesupport/activity/activityInfoForm";
	}
	
	
	/**
	 * 活动新建
	 * @param activityInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addForm")
	public String addForm(ActivityInfo activityInfo, HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon,Model model) {
		model.addAttribute("activityInfo", activityInfo);
		
		//图片访问路径的前缀，存放在作用域中，便于jsp页面的获取
		String baseurl_img = property.getProperty("ip_image");
		model.addAttribute("baseurl_img", baseurl_img);
	
		 List<TYxCoupon> couponList  =couponService.authSuccessList(coupon);
	       Iterator<TYxCoupon> iterator = couponList.iterator();    //处理已经过期 失效的优惠券
	          
	        		while (iterator.hasNext()) {
	        		TYxCoupon  tYxCoupon  =iterator.next();
					if (null!=tYxCoupon.getEndTime() && !tYxCoupon.getEndTime().toString().equals("")) {
						 Date today =new Date();
						if (tYxCoupon.getEndTime().getTime() <today.getTime() ) {
							iterator.remove();
						}
						
					}
					
				}
	        
	       Date currentTime = new Date();
	       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	      String currenDate = formatter.format(currentTime);
	        		       
	     model.addAttribute("currenDate", currenDate);
        model.addAttribute("couponList", couponList);
        model.addAttribute("coupon", coupon);
		return "modules/salesupport/activity/addActivityInfo";
	}
	
	/**
	 * 查看活动详情页面
	 * @param activityInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getDetail")
	public String getDetail(ActivityInfo activityInfo, HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon,CouponStatistics couponStatistics,Model model) {

		ActivityInfo activityInfoNew=activityInfoService.get(activityInfo.getId());
		model.addAttribute("activityInfo", activityInfoNew);
		
    	List<Map<String, Object>>  listInfo=	couponService.getCouponListByMainId(activityInfo.getId());
		model.addAttribute("listInfo", listInfo);
      
		couponStatistics.setActivityId(activityInfo.getId());
        Page<CouponStatistics> couponpage = couponSendService.couponStatistics(new Page<CouponStatistics>(request, response),couponStatistics);
        model.addAttribute("couponpage", couponpage);
		
		return "modules/salesupport/activity/activityDetailInfo";
	}

	
	/**
	 * 导出优惠券
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/exportCoupon")
    public void exportCouponStatics(ExcelCouponStatistics excelCouponStatistics, HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
        List<ExcelCouponStatistics> list = couponSendService.getAllCouponStatistics(excelCouponStatistics);
        String fileName = "优惠券发放统计"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
        try {
            new ExportExcel("优惠券发放统计", ExcelCouponStatistics.class).setDataList(list).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
        }
        
    }
	
	
	/**
	 * 保存活动信息
	 * @param activityInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(ActivityInfo activityInfo,TYxCouponRelation tYxCouponRelation ,Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {

		try {
			
			//活动类型---1表注册，2表开户，3表首次投资
			if (StringUtils.isNotBlank(activityInfo.getActivitytype()) && activityInfo.getActivitytype().equals("1") || activityInfo.getActivitytype().equals("2")|| activityInfo.getActivitytype().equals("3")  )
			{
				int isSysExtis=activityInfoService.isExtisSystHuoDong(activityInfo);   //如果	//活动类型---1表注册，2表开户，3表首次投资  这三种类型的活动已经存在了 提示
				if (isSysExtis>0) {
					addMessage(redirectAttributes, "已经存在此活动信息");
				    return "redirect:"+Global.getAdminPath()+"/activity/activityInfo/addForm";
				}
			
			}else if (StringUtils.isNotBlank(activityInfo.getActivitytype()) && activityInfo.getActivitytype().equals("4") ) {   //4表运营活动
				int isExtis=activityInfoService.isExtisHuoDong(activityInfo);
				if (isExtis>0) {
						addMessage(redirectAttributes, "已经存在此活动信息");
					    return "redirect:"+Global.getAdminPath()+"/activity/activityInfo/addForm";
				}
			}
			

        	String ids=activityInfo.getIds();   //优惠券集合
			
	    	if (StringUtils.isNotBlank(ids)) {   //如果选择的优惠券不为空的时候 
	            StringBuffer buffer=new StringBuffer();
	        	String[] test=ids.split(",");
	        	for (int i = 0; i < test.length; i++) {
	        		
	        		String cid=test[i].toString();      //优惠券的id
	        		TYxCoupon coupon  =couponService.get(cid);   //根据优惠券id查询优惠券信息
	        		Date  endDate=  coupon.getEndTime();  //优惠券结束日期
	        		Date  huoEndTime=activityInfo.getEndtime();   //活动结束日期
	        		
	        		if (endDate!=null && endDate.getTime()<huoEndTime.getTime()) {   //优惠券结束日期小于活动结束日期,不让保存
	        			buffer.append(coupon.getFaceValue());
	        			addMessage(redirectAttributes, "选择的优惠券中:面额"+buffer+" (元/%)的优惠券结束日期小于活动的结束日期，活动创建失败");
	        			 return "redirect:"+Global.getAdminPath()+"/activity/activityInfo/addForm";
					}

	        	}
	    	}
					activityInfo.setCreatedate(new Date());
					activityInfo.setCreateBy(UserUtils.getUser());
					activityInfo.setLastchangedtime(new Date());
					activityInfo.setActivitystatus("0");
					activityInfoService.save(activityInfo);               //保存活动的信息
		   
		        	
					if (StringUtils.isNotBlank(ids)) {   //如果选择的优惠券不为空的时候 
						String[] test=ids.split(",");
						for (int i = 0; i < test.length; i++) {
		        		
		        			String maid=activityInfo.getId();   //保存之后的活动的id
		        			String cid=test[i].toString();      //优惠券的id
		
			        		tYxCouponRelation.setId(UUIDUtil.genUUIDString());
			        		tYxCouponRelation.setCreateBy(UserUtils.getUser());
			        		tYxCouponRelation.setCouponId(cid);  //优惠券id
			        		tYxCouponRelation.setMainId(maid);   //活动id
			        		tYxCouponRelation.setCreatedate(new Date());
			        		tYxCouponRelation.setRelationType("activity");
			        		couponRelationService.saveTYxCouponRelation(tYxCouponRelation);       //保存活动和优惠券关联信息

		        	}
				
				}
		  
				addMessage(redirectAttributes, "保存活动信息成功");
			    return "redirect:"+Global.getAdminPath()+"/activity/activityInfo/list?repage";
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	/**
	 * 根据优惠券id的集合查询优惠券到期日期
	 * 如果优惠券到期日期小于活动的到期日期
	 * 就让活动保存失败
	 * @param ids
	 * @return
	 */
	public String checkCouponEndTime(ActivityInfo activityInfo,RedirectAttributes redirectAttributes,String ids) {
		
    	if (StringUtils.isNotBlank(ids)) {   //如果选择的优惠券不为空的时候 
            StringBuffer buffer=new StringBuffer();
        	String[] test=ids.split(",");
        	for (int i = 0; i < test.length; i++) {
        		
        		String cid=test[i].toString();      //优惠券的id
        		TYxCoupon coupon  =couponService.get(cid);   //根据优惠券id查询优惠券信息
        		Date  endDate=  coupon.getEndTime();  //优惠券结束日期
        		Date  huoEndTime=activityInfo.getEndtime();   //活动结束日期
        		
        		if (endDate!=null && endDate.getTime()<huoEndTime.getTime()) {   //优惠券结束日期小于活动结束日期,不让保存
        			buffer.append(coupon.getName());
        			addMessage(redirectAttributes, "选择的优惠券中："+buffer+"结束日期不符合条件，活动创建失败");
        			 return "redirect:"+Global.getAdminPath()+"/activity/activityInfo/addForm";
				}

        	}
    	}
		return null;

		
	}
	
	
	
	
	
	/**
	 * 保存更新活动信息
	 * @param activityInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "updateActivityInfo")
	public String updateActivityInfo(ActivityInfo activityInfo,TYxCouponRelation tYxCouponRelation ,Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
	

    	String ids=activityInfo.getIds();   //优惠券集合
		
    	if (StringUtils.isNotBlank(ids)) {   //如果选择的优惠券不为空的时候 
            StringBuffer buffer=new StringBuffer();
        	String[] test=ids.split(",");
        	for (int i = 0; i < test.length; i++) {
        		
        		String cid=test[i].toString();      //优惠券的id
        		TYxCoupon coupon  =couponService.get(cid);   //根据优惠券id查询优惠券信息
        		Date  endDate=  coupon.getEndTime();  //优惠券结束日期
        		Date  huoEndTime=activityInfo.getEndtime();   //活动结束日期
        		
        		if (endDate!=null && endDate.getTime()<huoEndTime.getTime()) {   //优惠券结束日期小于活动结束日期,不让保存
        			buffer.append(coupon.getFaceValue());
        			addMessage(redirectAttributes, "选择的优惠券中:面额"+buffer+" (元/%)的优惠券结束日期小于活动的结束日期，活动修改失败");
        			 return "redirect:"+Global.getAdminPath()+"/activity/activityInfo/addForm";
				}

        	}
    	}
		
		activityInfoService.delete(activityInfo);     //先删除活动信息--然后再从新保存
		couponRelationService.deleteCouponRelationByMainId(activityInfo.getId());  //删除活动关联优惠券信息

		activityInfo.setId(UUIDUtil.genUUIDString());
		activityInfo.setActivityname(activityInfo.getActivityname());
		activityInfo.setActivitytype(activityInfo.getActivitytype());
		activityInfo.setIntroduction(activityInfo.getIntroduction());
		activityInfo.setBegintime(activityInfo.getBegintime());
		activityInfo.setEndtime(activityInfo.getEndtime());
		activityInfo.setActivityhref(activityInfo.getActivityhref());
		activityInfo.setImageurl(activityInfo.getImageurl());
		
		activityInfo.setCreatedate(new Date());
		activityInfo.setCreateBy(UserUtils.getUser());
		activityInfo.setLastchangedtime(new Date());
		
		if ("5".equals(activityInfo.getActivitystatus())) {  //7.已撤销的活动不能删除，可以修改，撤销（发布）和查看；修改后，状态变为未发布，需要再次审核；
			activityInfo.setActivitystatus("0");
		}else {
			activityInfo.setActivitystatus(activityInfo.getActivitystatus());
		}
		
		activityInfoService.saveActivityInfo(activityInfo);           //保存活动的信息
		
    	String strs=activityInfo.getIds();
    	if (StringUtils.isNotBlank(strs)) {   //如果选择的优惠券不为空的时候 

        	String[] test=strs.split(",");
        	for (int i = 0; i < test.length; i++) {
        		
        		String maid=activityInfo.getId();   //保存之后的活动的id
        		String cid=test[i].toString();      //优惠券的id
        		
        		tYxCouponRelation.setId(UUIDUtil.genUUIDString());
        		tYxCouponRelation.setCreateBy(UserUtils.getUser());
        		tYxCouponRelation.setCouponId(cid);  //优惠券id
        		tYxCouponRelation.setMainId(maid);   //活动id
        		tYxCouponRelation.setCreatedate(new Date());
        		tYxCouponRelation.setRelationType("activity");
        		couponRelationService.saveTYxCouponRelation(tYxCouponRelation);       //保存活动和优惠券关联信息

        	}
		
		}
    	
		addMessage(redirectAttributes, "保存活动信息成功");
	    return "redirect:"+Global.getAdminPath()+"/activity/activityInfo/list?repage";
	}
	
	
	
	
	/**
	 * 删除活动信息
	 * @param activityInfo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(ActivityInfo activityInfo, RedirectAttributes redirectAttributes) {
		activityInfoService.delete(activityInfo);
		addMessage(redirectAttributes, "删除活动成功");
		return "redirect:"+Global.getAdminPath()+"/activity/activityInfo/list?repage";
	}
	
	
	/**
	 * 发布活动---更新为待审核
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/publishHuoDong")
	@ResponseBody
	public boolean publishHuoDong(String id,HttpServletRequest request){
		
   
		TYxCouponAuditRecord tYxCouponAuditRecord  =new TYxCouponAuditRecord();
		tYxCouponAuditRecord.setCouponAuditRecordId(UUIDUtil.genUUIDString());
		tYxCouponAuditRecord.setRelationType("activity");
		tYxCouponAuditRecord.setMainId(id);
		tYxCouponAuditRecord.setSubmitterId(UserUtils.getUser().getId());
		tYxCouponAuditRecord.setAuditFlag("0");
		tYxCouponAuditRecord.setAuditContent("提交审核活动");
		tYxCouponAuditRecord.setCreatedate(new Date());
		couponAuditRecordService.saveTYxCouponAuditRecord(tYxCouponAuditRecord);   //保存审核记录
		
		
		
		 boolean f = activityInfoService.publishHuoDong(id);
		 return f;
	}
	
	
	
	/**
	 * 撤销活动--更新为已撤销
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/revokeHuoDong")
	@ResponseBody
	public boolean revokeHuoDong(String id,HttpServletRequest request){
		 boolean f = activityInfoService.revokeHuoDong(id);
		 return f;
	}
	
	
	/**审核通过--更新为进行中
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/authSuccessHuoDong")
	@ResponseBody
	public boolean authSuccessHuoDong(String id,HttpServletRequest request){
		 boolean f = activityInfoService.authSuccessHuoDong(id);
		 
		 return f;
	}
	
	/**
	 * 审核不通过
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/authNoFailHuoDong")
	@ResponseBody
	public boolean authNoFailHuoDong(String id,HttpServletRequest request){
		 boolean f = activityInfoService.authNoFailHuoDong(id);
		 return f;
	}
	
	
	/**
	 * 根据id 和状态审核
	 * @param id
	 * @param activitystatus
	 * @param remarks
	 * @param request
	 * @return
	 */
	@RequestMapping("/authHuoDongStatus")
	@ResponseBody
	public boolean authHuoDongStatus(String id,String activitystatus,String remarks,HttpServletRequest request){
		
		
		ActivityInfo activityInfo=new ActivityInfo();
		activityInfo.setId(id);
		activityInfo.setActivitystatus(activitystatus);
		activityInfo.setAuthtime(new Date());
		activityInfo.setRemarks(remarks);
		
		TYxCouponAuditRecord tYxCouponAuditRecord  =new TYxCouponAuditRecord();
		tYxCouponAuditRecord.setCouponAuditRecordId(UUIDUtil.genUUIDString());
		tYxCouponAuditRecord.setRelationType("activity");
		tYxCouponAuditRecord.setMainId(id);
		
		if ("2".equals(activitystatus)) {  //代表前台选择的是未通过审核
			tYxCouponAuditRecord.setAuditFlag("2");  //审核不通过
		}else if ("3".equals(activitystatus)) {    //代表审核通过
			tYxCouponAuditRecord.setAuditFlag("1");//日志记录表 审核通过
		}
		
		tYxCouponAuditRecord.setAuditContent(remarks);
		tYxCouponAuditRecord.setAuditTime(new Date());
		tYxCouponAuditRecord.setAuditorId(UserUtils.getUser().getId());
		tYxCouponAuditRecord.setCreatedate(new Date());
		couponAuditRecordService.saveTYxCouponAuditRecord(tYxCouponAuditRecord);   //保存审核记录
		
		 boolean f = activityInfoService.updateActivityInfo(activityInfo);
		 return f;
	}
	
	
	
	
	
	/**
	 * 活动审核列表
	 * @param activityInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"authList", ""})
	public String authList(ActivityInfo activityInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		

		String   beginTimes=activityInfo.getPublishtBeginTime();
		String   endTimes=activityInfo.getPublishtEndTime();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("activityname", activityInfo.getActivityname());
		paramMap.put("beginTimes", beginTimes);
		paramMap.put("endTimes", endTimes);
	
		
		List<ActivityInfo> authActivityInfoList=activityInfoService.authActivityInfoList(activityInfo);
		
		
		String pageNos = request.getParameter("pageNo");
		int pageSize=10;
		Integer pageNo=1;
		if(pageNos!=null){
			pageNo=new Integer(pageNos);
		}
		//传参
		Page<ActivityInfo> page = new Page<ActivityInfo>(pageNo,pageSize,authActivityInfoList.size(),authActivityInfoList);
		page.initialize();
		
		ArrayList<ActivityInfo> activityInfoList = new ArrayList<ActivityInfo>();//返回的list集合
		if(pageNo>page.getTotalPage()){
			pageNo=1;
		}
		int start = (pageNo-1)*page.getPageSize();
		int end = pageNo*page.getPageSize();
		if(end>authActivityInfoList.size()){
			end=authActivityInfoList.size();
		}
		for (int i = start; i < end; i++) {
			activityInfoList.add(authActivityInfoList.get(i));
		}
		

		//分页的对象，用于回显
		model.addAttribute("page", page);
		//用户集合的对象，用于列表展示
		model.addAttribute("activityInfoList", activityInfoList);
		model.addAttribute("paramMap", paramMap);
		
		
		return "modules/salesupport/activity/activityInfoAuthList";
	}


	@RequestMapping(value = "getAuthDetail")
	public String getAuthDetail(ActivityInfo activityInfo, Model model) {
		
    	List<Map<String, Object>>  listInfo=	couponService.getCouponListByMainId(activityInfo.getId());
		model.addAttribute("listInfo", listInfo);
		model.addAttribute("activityInfo", activityInfo);
		
		return "modules/salesupport/activity/activityAuthDetailInfo";
	}
	
	
}
