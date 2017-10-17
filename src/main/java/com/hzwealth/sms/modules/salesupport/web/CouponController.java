package com.hzwealth.sms.modules.salesupport.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.salesupport.entity.TYxCoupon;
import com.hzwealth.sms.modules.salesupport.entity.TYxCouponAuditRecord;
import com.hzwealth.sms.modules.salesupport.service.CouponService;
import com.hzwealth.sms.modules.salesupport.service.TYxCouponAuditRecordService;
import com.hzwealth.sms.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.hzwealth.sms.modules.sys.utils.UserUtils;
@Controller
@RequestMapping("${adminPath}/coupon/couponManage")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;
    @Autowired
	private TYxCouponAuditRecordService couponAuditRecordService;
    @ModelAttribute
    public TYxCoupon get(@RequestParam(required=false) String id) {
        TYxCoupon entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = couponService.get(id);
        }
        if (entity == null){
            entity = new TYxCoupon();
        }
        return entity;
    }
    /**
     * 优惠券详情
     * @param coupon
     * @param model
     * @return
     */
    @RequestMapping("/couponDetail")
    public String couponDetail(TYxCoupon coupon,Model model){
    	 if(!"".equals(coupon.getEffectiveDays()) && coupon.getEffectiveDays()!=null){
         	coupon.setStatusshow("1");
         }else if(!"".equals(coupon.getBeginTime()) && coupon.getBeginTime()!=null){
         	coupon.setStatusshow("2");
         }
    	 if(coupon.getEndTime()!=null){
           	if(coupon.getEndTime().getTime()<(new Date()).getTime()){
           		coupon.setShixiao("1");;
           		
           	}
           }
        model.addAttribute("coupon", coupon);
        return "modules/salesupport/coupon/couponDetail";
    }
    /**
     * 优惠券详情
     * @param coupon
     * @param model
     * @return
     */
    @RequestMapping("/couponAuditDetail")
    public String couponAuditDetail(TYxCoupon coupon,Model model){
    	 if(!"".equals(coupon.getEffectiveDays()) && coupon.getEffectiveDays()!=null){
         	coupon.setStatusshow("1");
         }else if(!"".equals(coupon.getBeginTime()) && coupon.getBeginTime()!=null){
         	coupon.setStatusshow("2");
         }
    	 if(coupon.getEndTime()!=null){
            	if(coupon.getEndTime().getTime()<(new Date()).getTime()){
            		coupon.setShixiao("1");;
            		
            	}
            }
        model.addAttribute("coupon", coupon);
        return "modules/salesupport/coupon/couponAuditDetail";
    }
    //去新建，修改
    @RequestMapping("/toSaveCoupon")
    public String toSaveCoupon(TYxCoupon coupon,Model model){
        //下拉列表回显
        if (null!=coupon) {
            this.couponConvert(coupon);
        }
        
        if(!"".equals(coupon.getEffectiveDays()) && coupon.getEffectiveDays()!=null){
        	coupon.setStatusshow("1");
        }else if(!"".equals(coupon.getBeginTime()) && coupon.getBeginTime()!=null){
        	coupon.setStatusshow("2");
        }
        model.addAttribute("coupon", coupon);
        return "modules/salesupport/coupon/couponManageListForm";
    }
    /**
     * 新建、修改
     * @param request
     * @param response
     * @param coupon
     * @return
     */
    @RequestMapping("/saveUpdateCoupon")
    public String saveCoupon(HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon){
        Principal principal = UserUtils.getPrincipal();
        //下拉列表数据
        if( coupon.getTermList()!=null){
        if(coupon.getTermList().size()>0  ){
        coupon.setLoanTermList(StringUtils.join(coupon.getTermList().size()>0?removeOut(coupon.getTermList()):coupon.getTermList(),","));
        }else{
        	coupon.setLoanTermList("全部");
        }}else{
        	coupon.setLoanTermList("全部");
        }
        if( coupon.getChannelList()!=null){
           if(coupon.getChannelList().size()>0 ){
          coupon.setLoanChannelList(StringUtils.join(coupon.getChannelList().size()>0?removeOut(coupon.getChannelList()):coupon.getChannelList(),","));
           }else{
        	coupon.setLoanChannelList("全部");
            }
        }else{
        	coupon.setLoanChannelList("全部");
        }
        couponService.saveUpdateCoupon(coupon,principal);
        return "redirect:"+Global.getAdminPath()+"/coupon/couponManage/couponManageList?repage";
    }
    
    @RequestMapping("/deleteCoupon")
    public String deleteCoupon(HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon){
        Principal principal = UserUtils.getPrincipal();
        coupon.setUpdateby(principal.getId());
        couponService.delete(coupon);
        return "redirect:"+Global.getAdminPath()+"/coupon/couponManage/couponManageList?repage";
    }
    
    /**
     * 优惠券管理列表
     * @return
     */
    @RequestMapping("/couponManageList")
    public String couponManage(HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon,Model model){
    	Page<TYxCoupon> page = couponService.findCouponManageList(new Page<TYxCoupon>(request, response),coupon);
        model.addAttribute("page", page);
        model.addAttribute("coupon", coupon);
        return "modules/salesupport/coupon/couponManageList";
    }
    /**
     * 提交审核(未生效——>待审核)
     * @param request
     * @param response
     * @param coupon
     * @return
     */
    @RequestMapping("/submitAudit")
    public String submitAuditCoupon(HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon){
        Principal principal = UserUtils.getPrincipal();
        coupon.setUpdateby(principal.getId());
        couponService.saveSubmitAuditCoupon(coupon,principal);
        return "redirect:"+Global.getAdminPath()+"/coupon/couponManage/couponAuditList?repage";
    }
    
    /**
     * 审核(待审核——>审核通过)
     * @param request
     * @param response
     * @param coupon
     * @return
     */
    @RequestMapping("/auditCoupon")
    public String auditCoupon(HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon){
        Principal principal = UserUtils.getPrincipal();
        coupon.setUpdateby(principal.getId());
        couponService.saveAuditCoupon(coupon,principal );
        return "redirect:"+Global.getAdminPath()+"/coupon/couponManage/couponAuditList?repage";
    }
    
    /**
     * 优惠券审核列表
     */
    @RequestMapping("/couponAuditList")
    public String couponAuditList(HttpServletRequest request,HttpServletResponse response,TYxCoupon coupon,Model model){
    	/*couponService.updstatus();*/
    	Page<TYxCoupon> page = couponService.findCouponAuditList(new Page<TYxCoupon>(request, response),coupon);
        model.addAttribute("page", page);
        model.addAttribute("coupon", coupon);
        return "modules/salesupport/coupon/couponAuditList";
    }
   //转换数据
   public TYxCoupon couponConvert(TYxCoupon coupon){
       //下拉列表数据
       if (!StringUtils.isBlank(coupon.getCouponTypeId())) {
           String types = coupon.getCouponTypeId().trim();
           String[] split = types.split(",");
           List<String> typeList = Arrays.asList(split);
           if (split.length<1) {
               typeList.add(types);
           }
           coupon.setCouponTypeList(typeList);
       }
       if (!StringUtils.isBlank(coupon.getLoanTermList())) {
           String terms = coupon.getLoanTermList().trim();
           String[] split = terms.split(",");
           List<String> termList = Arrays.asList(split);
           if (split.length<1) {
               termList.add(terms);
           }
           coupon.setTermList(termList);
       }
       if (!StringUtils.isBlank(coupon.getLoanChannelList())) {
           String channels = coupon.getLoanChannelList().trim();
           String[] split = channels.split(",");
           List<String> channelList = Arrays.asList(split);
           if (split.length<1) {
               channelList.add(channels);
           }
           coupon.setChannelList(channelList);;
       }
       return coupon;
   }
   //遍历去重
   public  List<String> removeOut(List<String> n){
	   if(n.size()>0){
	        for  ( int  i  =   0 ; i  <  n.size()  -   1 ; i ++ )   { 
	            for  ( int  j  =  n.size()  -   1 ; j  >  i; j -- )   { 
	              if  (n.get(j).equals(n.get(i)))   { 
	            	  n.remove(j); 
	              } 
	            } 
	          } 
	          System.out.println(n); 
	        }
	   if(n.size()>1){
	   for( int  i  =   0 ; i  <  n.size()  -   1 ; i ++ ){
		   if("全部".equals(n.get(i))){
			   n.remove(i);
		   }
	   }
	   }
	   return  n;
   }
}
