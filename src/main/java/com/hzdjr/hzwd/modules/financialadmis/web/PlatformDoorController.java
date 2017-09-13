package com.hzdjr.hzwd.modules.financialadmis.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.borrow.entity.TBorrow;
import com.hzdjr.hzwd.modules.financialadmis.entity.Overdue;
import com.hzdjr.hzwd.modules.financialadmis.service.PlatformDoorService;
/**
 * 平台户管理
 * @author xq
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/financialadmis/platformdoor")
public class PlatformDoorController extends BaseController {

    @Autowired
    private PlatformDoorService platformDoorService;
    
    @ModelAttribute
    public TBorrow get(@RequestParam(required=false) String id) {
        TBorrow entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = platformDoorService.get(id);
        }
        if (entity == null){
            entity = new TBorrow();
        }
        return entity;
    }
    /**
     * 服务费账户列表
     * @param request
     * @param response
     * @param model
     * @param platformdoor
     * @return
     */
    @RequiresPermissions("financialadmis:platformdoor:view")
    @RequestMapping(value={"list", ""})
    public String ServiceChargeAccount(HttpServletRequest request, HttpServletResponse response, Model model,TBorrow platformdoor){
        Map<String, Object> countMount = platformDoorService.findcountMount(platformdoor);
        Page<TBorrow> page = platformDoorService.findPage(new Page<TBorrow>(request, response), platformdoor);
        model.addAttribute("page", page);
        model.addAttribute("countMount",countMount);
        model.addAttribute("platformdoor", platformdoor);
        return "modules/financialadmis/platformdoor";
    }
    
    /**
     * 垫资列表
     * @param request
     * @return
     */
    @RequestMapping("/payin")
    public String overdueList(HttpServletRequest request,HttpServletResponse response, Model model,Overdue overdue){
       // Map<String, Object> requestMap = this.getRequestMap(request);
        Map<String, Object> countMount = platformDoorService.findAdvanceCountMount(overdue);
       Page<Overdue> page = platformDoorService.findPayinList(new Page<Overdue>(request, response),overdue);
       model.addAttribute("page", page);
       //查询垫资、冲抵总额
       model.addAttribute("countMount",countMount);
       model.addAttribute("overdue",overdue);
        return "modules/financialadmis/payInList";
    }
    
    
    
    /**
     * TODO   导出服务费账户列表
     * @param redirectAttributes
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportPlatformDoorList")
    public String exportPlatformDoorList(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, Model model){
       Map<String, Object> requestMap = this.getRequestMap(request);
        List<TBorrow> exportList = platformDoorService.exportList(requestMap);
        String fileName = "服务费账户"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
         try {
            new ExportExcel("服务费账户", TBorrow.class).setDataList(exportList).write(response, fileName).dispose();
           return null;
         } catch (IOException e) {
            // TODO Auto-generated catch block
            addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
        }
         return "redirect:" + Global.getAdminPath() +"/financialadmis/platformdoor/list";
    }
    
    
    
    //获取页面参数集合
    public Map<String,Object> getRequestMap(HttpServletRequest request){
        Map<String,Object> paramMap= new HashMap<String, Object>();
        @SuppressWarnings("unchecked")
        Map<String,String[]> map= request.getParameterMap();
        Set<Entry<String, String[]>> entrySet = map.entrySet();
        Iterator<Entry<String, String[]>> it = entrySet.iterator();
        String[] split = null;
        while (it.hasNext()) {
            Entry<String, String[]> next = it.next();
            String value = next.getValue()[0].toString().trim();
            if ("borrowIds".equals(next.getKey())&&(!"".equals(value))) {
                split = value.split(",");
            }
            paramMap.put(next.getKey(), value.toString());
        }
        paramMap.put("borrowIds", split);
        return paramMap;
    }
    
}
