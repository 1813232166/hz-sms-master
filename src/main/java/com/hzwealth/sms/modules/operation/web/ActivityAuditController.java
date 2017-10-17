package com.hzwealth.sms.modules.operation.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.modules.operation.entity.Activity;
import com.hzwealth.sms.modules.operation.service.ActivityService;


@Controller
@RequestMapping(value = "${adminPath}/operation/activityAudit")
public class ActivityAuditController {
  
  @Autowired
  private ActivityService activityService;
  
  //@RequiresPermissions("operation:activity:view")
  @RequestMapping(value = {"list", ""})
  public String list(Activity activity, HttpServletRequest request, HttpServletResponse response, Model model) {
    Page<Activity> page = activityService.findPage(new Page<Activity>(request, response), activity); 
    model.addAttribute("page", page);
    return "modules/operation/activityAuditList";
  }
}
