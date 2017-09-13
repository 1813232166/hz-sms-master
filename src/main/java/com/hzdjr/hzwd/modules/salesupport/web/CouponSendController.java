package com.hzdjr.hzwd.modules.salesupport.web;

import java.io.IOException;
import java.util.Date;
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

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCoupon;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponGroup;
import com.hzdjr.hzwd.modules.salesupport.entity.CouponStatistics;
import com.hzdjr.hzwd.modules.salesupport.entity.TYxCouponRelation;
import com.hzdjr.hzwd.modules.salesupport.service.CouponSendService;
import com.hzdjr.hzwd.modules.salesupport.service.CouponService;
import com.hzdjr.hzwd.modules.salesupport.service.TYxCouponRelationService;
import com.hzdjr.hzwd.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.hzdjr.hzwd.modules.sys.utils.UserUtils;
@Controller
@RequestMapping("${adminPath}/coupon/couponSend")
public class CouponSendController extends BaseController {

    @Autowired
    private CouponSendService couponSendService;
    @Autowired
    private CouponService couponService;
   
	@Autowired
	private TYxCouponRelationService couponRelationService;
	
    @ModelAttribute
    public TYxCouponGroup get(@RequestParam(required=false) String id) {
        TYxCouponGroup entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = couponSendService.get(id);
        }
        if (entity == null){
            entity = new TYxCouponGroup();
        }
        return entity;
    }
    
    
    
    
  /**
   * 新建优惠券发送
   * @param request
   * @param response
   * @param couponGroup
   * @param coupon
   * @param model
   * @return
   */
    @RequestMapping("/toSaveUpdate")
    public String toSaveCouponGroup(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup,TYxCoupon coupon,Model model){
      
    	List<TYxCoupon> couponList  =couponService.authSuccessList(coupon);   //审核通过的优惠券列表
    	
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
    	
    	model.addAttribute("couponList", couponList);
        model.addAttribute("couponGroup", couponGroup);
        return "modules/salesupport/coupon/couponSendListForm";
    }
    
    
    
   /**
    * 保存优惠券组信息
    * @param request
    * @param response
    * @param couponGroup
    * @param model
    * @return
    */
    @RequestMapping("/saveCouponGroupInfo")
    public String saveCouponGroupInfo(TYxCouponGroup couponGroup,TYxCouponRelation tYxCouponRelation ,Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
  
    	
    	couponGroup.setCouponGroupId(UUIDUtil.genUUIDString());
    	if ("all".equals(couponGroup.getHolderType())) {   //如果用户类型选择的是全部的话
    		couponGroup.setMobilseList("");
    		Long totalUser = couponSendService.findUserCount().get("totalUser");
            couponGroup.setUserCount(totalUser.intValue());
		}
    	else if ("some".equals(couponGroup.getHolderType())) {  //

    		if (StringUtils.isNotBlank(couponGroup.getSingletonUser())) {   //如果用户列表不为空
    			
    			String  singletonUser=couponGroup.getSingletonUser().trim();
    			 String  singletonUserRep= singletonUser.replaceAll("；", ";");   //剔除中文分号，修改为英文
    			 String  singletonUserTemp= singletonUserRep.replaceAll(" ", "");   //剔除空格
    			
    			String[] arrayUser =singletonUserTemp.split(";");
    			couponGroup.setMobilseList(singletonUserTemp);    //获取单个用户的字段数据
    			couponGroup.setUserCount(arrayUser.length);
    			
			}else {
				couponGroup.setUserCount(0);
			}

		}
    	couponGroup.setCouponGroupStatus("0");
    	couponGroup.setCreateby(UserUtils.getUser().getId());
    	couponGroup.setCreatedate(new Date());
    	couponGroup.setRemarks("新建优惠券组");
    	couponGroup.setDelFlag("0");
   
    	
    	int flag=couponSendService.saveTYxCouponGroupInfo(couponGroup);    //保存优惠券组信息
    	if (flag>0) {    //保存优惠券组信息成功
    		
        	String strs=couponGroup.getIds();
        	if (StringUtils.isNotBlank(strs)) {   //如果选择的优惠券不为空的时候 

            	String[] test=strs.split(",");
            	for (int i = 0; i < test.length; i++) {
            		
            		String maid=couponGroup.getCouponGroupId();  //保存之后的优惠券组id
            		String cid=test[i].toString();      //优惠券的id
            		
            		tYxCouponRelation.setId(UUIDUtil.genUUIDString());
            		tYxCouponRelation.setCreateby(UserUtils.getUser().getId());
            		tYxCouponRelation.setCouponId(cid);  //优惠券id
            		tYxCouponRelation.setMainId(maid);   //优惠券组id
            		tYxCouponRelation.setCreatedate(new Date());
            		tYxCouponRelation.setRelationType("group");    //关联类型-类型id
            		couponRelationService.saveTYxCouponRelation(tYxCouponRelation);       //保存活动和优惠券关联信息

            	}
    		
    		}
		}

		addMessage(redirectAttributes, "保存优惠券组信息成功");
    	
       return "redirect:"+Global.getAdminPath()+"/coupon/couponSend/couponSendList?repage";
   }
    
    
    
    /**
     * 编辑优惠券发送
     * @param couponGroup
     * @param request
     * @param response
     * @param coupon
     * @param model
     * @return
     */
	@RequestMapping(value = "editCouponSendInfo")
	public String editCouponSendInfo(TYxCouponGroup couponGroup, HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon,Model model) {
		
		
		  String  couponGroupId=couponGroup.getCouponGroupId();

		  TYxCouponGroup couponGroupNew=couponSendService.get(couponGroupId);
	
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
		    
		    
	    	List<Map<String, Object>>  couponSelectedList=	couponService.getCouponInfolistByMainId(couponGroup.getCouponGroupId());   //优惠券组保存的优惠券
	    	
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
	    	
	    	
	    	model.addAttribute("couponGroup", couponGroupNew);
	    	model.addAttribute("couponAllList", couponAllList);
		
		  return "modules/salesupport/coupon/updateCouponSendInfo";
	}
    
	   /**
	    * 更新优惠券组信息
	    * @param request
	    * @param response
	    * @param couponGroup
	    * @param model
	    * @return
	    */
	    @RequestMapping("/updateCouponGroupInfo")
	    public String updateCouponGroupInfo(TYxCouponGroup couponGroup,TYxCouponRelation tYxCouponRelation ,Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
			couponRelationService.deleteCouponRelationByMainId(couponGroup.getCouponGroupId());  //删除优惠券组关联优惠券信息
	    	if ("all".equals(couponGroup.getHolderType())) {   //如果用户类型选择的是全部的话
	    		couponGroup.setMobilseList("");
	    		Long totalUser = couponSendService.findUserCount().get("totalUser");
	    		couponGroup.setUserCount(totalUser.intValue());
			}else if ("some".equals(couponGroup.getHolderType())) {  //

	    		if (StringUtils.isNotBlank(couponGroup.getMobilseList())) {   //如果用户列表不为空
	    			String  singletonUser=couponGroup.getMobilseList().trim();
	    			 String  singletonUserRep= singletonUser.replaceAll("；", ";");   //剔除中文分号，修改为英文
	    			 String  singletonUserTemp= singletonUserRep.replaceAll(" ", "");   //剔除空格
	    			String[] arrayUser =singletonUserTemp.split(";");
	    			couponGroup.setMobilseList(singletonUserTemp);    //获取单个用户的字段数据
	    			couponGroup.setUserCount(arrayUser.length);
	    			
				}else {
					couponGroup.setUserCount(0);
				}

			}
			  if (couponGroup.getIsSendMessage().equals("0")) {   //如果选择的是不发送短信
				   couponGroup.setMessageContent("");    //把短信内容变成空值
			   }
	    	couponGroup.setId(couponGroup.getCouponGroupId());;
	    	couponSendService.save(couponGroup);    //保存优惠券组信息
	        	String strs=couponGroup.getIds();
	        	if (StringUtils.isNotBlank(strs)) {   //如果选择的优惠券不为空的时候 

	            	String[] test=strs.split(",");
	            	for (int i = 0; i < test.length; i++) {
	            		
	            		String maid=couponGroup.getCouponGroupId();  //保存之后的优惠券组id
	            		String cid=test[i].toString();      //优惠券的id
	            		
	            		tYxCouponRelation.setId(UUIDUtil.genUUIDString());
	            		tYxCouponRelation.setCreateby(UserUtils.getUser().getId());
	            		tYxCouponRelation.setCouponId(cid);  //优惠券id
	            		tYxCouponRelation.setMainId(maid);   //优惠券组id
	            		tYxCouponRelation.setCreatedate(new Date());
	            		tYxCouponRelation.setRelationType("group");    //关联类型-类型id
	            		couponRelationService.saveTYxCouponRelation(tYxCouponRelation);       //保存活动和优惠券关联信息

	            	}
	    		
	    		}

			addMessage(redirectAttributes, "修改优惠券组信息成功");
	    	
	       return "redirect:"+Global.getAdminPath()+"/coupon/couponSend/couponSendList";
	   }
    
    /**
     * 优惠券组查看详情
     * @param couponGroup
     * @param model
     * @return
     */
    @RequestMapping("/sendDetail")
   public String sendDetail(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup,CouponStatistics couponStatistics,Model model){
        TYxCouponGroup tYxCouponGroup = couponSendService.get(couponGroup.getCouponGroupId());
        List<TYxCoupon> relCoupon = couponSendService.getRelCoupon(couponGroup.getCouponGroupId());
        String ids="";
        if (relCoupon.size()>0) {
            for (TYxCoupon tYxCoupon : relCoupon) {
                ids += "," + tYxCoupon.getId();
            }
            tYxCouponGroup.setCouponIds(ids.substring(1));
        }
        tYxCouponGroup.setCouponList(relCoupon);
        couponStatistics.setCouponGroupId(couponGroup.getCouponGroupId());
        Page<CouponStatistics> couponpage = couponSendService.couponStatistics(new Page<CouponStatistics>(request, response),couponStatistics);
        model.addAttribute("couponpage", couponpage);
        model.addAttribute("couponGroup", tYxCouponGroup);
       return "modules/salesupport/coupon/couponSendDetail";
   }
    
    /**
     * 删除优惠券组信息
     * @param couponGroup
     * @param redirectAttributes
     * @return
     */
	@RequestMapping(value = "deleteCouponGroup")
	public String deleteCouponGroup(TYxCouponGroup couponGroup, RedirectAttributes redirectAttributes) {
		
		couponSendService.deleteTYxCouponGroupInfo(couponGroup.getCouponGroupId());
		addMessage(redirectAttributes, "删除优惠券组成功");
		 return "redirect:"+Global.getAdminPath()+"/coupon/couponSend/couponSendList?repage";
	}
	
    
    /**
     * 优惠券发送列表
     * @return
     */
    @RequestMapping("/couponSendList")
    public String couponSendList(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup,Model model){
        Page<TYxCouponGroup> page = couponSendService.findCouponSendList(new Page<TYxCouponGroup>(request, response),couponGroup);

        model.addAttribute("page", page);
        model.addAttribute("couponGroup", couponGroup);
        return "modules/salesupport/coupon/couponSendList";
    }
    //发放审核详情(审核页)
    @RequestMapping("/sendAuditDetail")
   public String sendAuditDetail(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup,CouponStatistics couponStatistics,Model model){
        TYxCouponGroup tYxCouponGroup = couponSendService.get(couponGroup.getCouponGroupId());
        List<TYxCoupon> relCoupon = couponSendService.getRelCoupon(couponGroup.getCouponGroupId());
        String ids="";
        if (relCoupon.size()>0) {
            for (TYxCoupon tYxCoupon : relCoupon) {
                ids += "," + tYxCoupon.getId();
            }
            tYxCouponGroup.setCouponIds(ids.substring(1));
        }
        tYxCouponGroup.setCouponList(relCoupon);
        couponStatistics.setCouponGroupId(couponGroup.getCouponGroupId());
        Page<CouponStatistics> couponpage = couponSendService.couponStatistics(new Page<CouponStatistics>(request, response),couponStatistics);
        model.addAttribute("couponpage", couponpage);
        model.addAttribute("couponGroup", tYxCouponGroup);
       return "modules/salesupport/coupon/couponSendAuditDetail";
   }
    @RequestMapping("/couponStatistics")
    @ResponseBody
    public void couponStatistics(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup,Model model,RedirectAttributes redirectAttributes){
        TYxCouponGroup tYxCouponGroup = couponSendService.get(couponGroup.getCouponGroupId());
        Page<CouponStatistics> couponpage = couponSendService.couponStatistics(new Page<CouponStatistics>(request, response),new CouponStatistics());
        model.addAttribute("couponpage", couponpage);
        model.addAttribute("couponGroup", tYxCouponGroup);
        redirectAttributes.addAttribute(tYxCouponGroup);
        renderString(response, couponpage);
        //return "redirect:"+Global.getAdminPath()+"/coupon/couponSend/sendDetail?repage&&couponGroupId="+couponGroup.getCouponGroupId();
        
    }
    @RequestMapping("/exportCoupon")
    public String exportCouponStatics(CouponStatistics couponStatistics,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes){
        //CouponStatistics couponStatistics = new CouponStatistics();
        //couponStatistics.setEndLimit(20000);
        List<CouponStatistics> list = couponSendService.findAllCoouCouponStatistics(couponStatistics);
        String fileName = "优惠券发放统计"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
        try {
            new ExportExcel("优惠券发放统计", CouponStatistics.class).setDataList(list).write(response, fileName).dispose();
        } catch (IOException e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
        }
        return null;
    }
    /**
     * 获取组关联的优惠券
     * @param request
     * @param response
     * @param couponGroup
     */
    @RequestMapping("/getRelCoupon")
    @ResponseBody
    public void getRelCoupon(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup){
         List<TYxCoupon> relCoupon = couponSendService.getRelCoupon(couponGroup.getCouponGroupId());
         renderString(response, relCoupon);
    }
    
    /**
     * 获取用户已使用的优惠券详情
     * @param request
     * @param response
     * @param couponGroup
     */
    @RequestMapping("/getUsedCoupon")
    @ResponseBody
    public void getUsedCoupon(HttpServletRequest request,HttpServletResponse response,CouponStatistics couponStatistics){
         List<Map<String, Object>> usedCoupon = couponSendService.getUsedCoupon(couponStatistics);
         renderString(response, usedCoupon);
    }
    /**
     * 优惠券发送审核列表
     * @return
     */
    @RequestMapping("/couponSendAuditList")
    public String couponSendAuditList(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup,Model model){
        Page<TYxCouponGroup> page = couponSendService.findCouponSendAuditList(new Page<TYxCouponGroup>(request, response),couponGroup);
        model.addAttribute("page", page);
        model.addAttribute("couponGroup", couponGroup);
        return "modules/salesupport/coupon/couponSendAuditList";
    }
    
    /**
     * 提交审核(未生效——>待审核)
     * @param request
     * @param response
     * @param coupon
     * @return
     */
    @RequestMapping("/submitAudit")
    public String submitAuditCouponSend(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup){
        TYxCouponGroup tYxCouponGroup = couponSendService.get(couponGroup.getCouponGroupId());
        couponSendService.saveSubmitAuditCouponSend(tYxCouponGroup,UserUtils.getPrincipal());
        return "redirect:"+Global.getAdminPath()+"/coupon/couponSend/couponSendAuditList?repage";
    }
    
    /**
     * 审核(待审核——>审核通过)
     * @param request
     * @param response
     * @param coupon
     * @return
     */
    @RequestMapping("/auditCouponGroup")
    public String auditCouponSend(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup){
        couponSendService.saveAuditCouponSend(couponGroup,UserUtils.getPrincipal());
        return "redirect:"+Global.getAdminPath()+"/coupon/couponSend/couponSendAuditList?repage";
    }
    
  //判断优惠券是否失效
    @RequestMapping("/judgeCouponStatus")
    @ResponseBody
    public void judgeCouponStatus(HttpServletRequest request,HttpServletResponse response,TYxCouponGroup couponGroup){
        List<TYxCoupon> relCoupon = couponSendService.getRelCoupon(couponGroup.getCouponGroupId());
        Iterator<TYxCoupon> it = relCoupon.iterator();
        while (it.hasNext()) {
            TYxCoupon next = it.next();
            if (null!=next.getEndTime() && next.getEndTime().getTime()<System.currentTimeMillis()) {
                renderString(response, "1");//含有失效优惠券
                return;
            }
        }
        renderString(response, "0");
    }
}
