/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.operation.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.beanvalidator.BeanValidators;
import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.excel.ImportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.operation.entity.Activity;
import com.hzdjr.hzwd.modules.operation.entity.ActivityPoint;
import com.hzdjr.hzwd.modules.operation.service.ActivityPointService;
import com.hzdjr.hzwd.modules.operation.service.ActivityService;

/**
 * 活动列表Controller
 * @author xuchenglin
 * @version 2016-10-21
 */
@Controller
@RequestMapping(value = "${adminPath}/operation/activity")
public class ActivityController extends BaseController {

	@Autowired
	private ActivityService activityService;
	
  @Autowired
  private ActivityPointService activityPointService;
	
	@ModelAttribute
	public Activity get(@RequestParam(required=false) String id) {
		Activity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = activityService.get(id);
		}
		if (entity == null){
			entity = new Activity();
		}
		return entity;
	}
	
	@RequiresPermissions("operation:activity:view")
	@RequestMapping(value = {"list", ""})
	public String list(Activity activity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Activity> page = activityService.findPage(new Page<Activity>(request, response), activity); 
		model.addAttribute("page", page);
		return "modules/operation/activityList";
	}

	//新建自定义活动 | 编辑自定义活动
	@RequiresPermissions("operation:activity:view")
	@RequestMapping(value = "form")
	public String form(Activity activity, Model model) {
		model.addAttribute("activity", activity);
		return "modules/operation/activityEdit";
	}
	
  //保存自定义活动
	@RequiresPermissions("operation:activity:edit")
	@RequestMapping(value = "save")
	public String save(Activity activity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, activity)){
			return form(activity, model);
		}
		activityService.save(activity);
		addMessage(redirectAttributes, "保存自定义活动成功");
		return "redirect:"+Global.getAdminPath()+"/operation/activity/?repage";
	}

	//预览自定义活动
  @RequiresPermissions("operation:activity:edit")
  @RequestMapping(value = "preview")
  public String preview(Activity activity, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
    model.addAttribute("activity", activity);
    ActivityPoint point = new ActivityPoint();
    point.setActivityid(activity.getId());
    Page<ActivityPoint> page = activityPointService.findPage(new Page<ActivityPoint>(request, response), point);
    model.addAttribute("page", page);
    return "modules/operation/activityPreview";
  }
	
  //编辑上传页面
  @RequiresPermissions("operation:activity:edit")
  @RequestMapping(value = "editForm")
  public String editForm(Activity activity, Model model, HttpServletRequest request, HttpServletResponse response) {
    ActivityPoint point = new ActivityPoint();
    point.setActivityid(activity.getId());
    Page<ActivityPoint> page = activityPointService.findPage(new Page<ActivityPoint>(request, response), point);
    model.addAttribute("page", page);
    return "modules/operation/activityUpload";
  }
  
  //@RequiresPermissions("operation:activity:edit")
  @RequestMapping(value = "import", method=RequestMethod.POST)
  public String importFile(Activity activity, MultipartFile file, RedirectAttributes redirectAttributes) {
    if(Global.isDemoMode()){
      addMessage(redirectAttributes, "演示模式，不允许操作！");
      return "redirect:" + adminPath + "/operation/activity/list?repage";
    }
    int successNum = 0;
    int failureNum = 0;
    StringBuilder failureMsg = new StringBuilder();
    Activity activityTmp = activityService.get(activity.getId());
    String type = activityTmp.getRewardtype();//1现金2加息卷3积分4体验金
    if(type.equals("1")){
      System.out.println("现金导入");
    }
    else if(type.equals("2")){
      System.out.println("加息卷导入");
    }
    else if(type.equals("3")){
      System.out.println("积分导入");
      String rewardCount = activityTmp.getRewardcount();
      try {
        ImportExcel ei = new ImportExcel(file, 1, 0);
        List<ActivityPoint> pointList = ei.getDataList(ActivityPoint.class, 3);
        for(ActivityPoint point : pointList){
          try {
            point.setActivityid(activity.getId());
            point.setAmount(Integer.parseInt(rewardCount));
            point.setStatus("2");
            point.setPointSign("1");
            point.setSourcechannel("5");
            activityPointService.save(point);
            successNum++;
          } catch (ConstraintViolationException ex) {
            failureMsg.append("<br/>登录名 "+point.getMobile()+" 导入失败：");
            List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            for (String message : messageList){
              failureMsg.append(message+"; ");
              failureNum++;
            }
          } catch (Exception ex) {
            failureMsg.append("<br/>登录名 "+point.getMobile()+" 导入失败：" + ex.getMessage());
          }
        }
        if (failureNum>0){
          failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
        }
        addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
      } catch (Exception e) {
        addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
      }
    }
    else if(type.equals("4")){
      System.out.println("体验金导入");
    }
    else{
      addMessage(redirectAttributes, "没有你所导入的类型 ");
    }
    return "redirect:" + adminPath + "/operation/activity/list?repage";
  }
  
	@RequiresPermissions("operation:activity:edit")
	@RequestMapping(value = "delete")
	public String delete(Activity activity, RedirectAttributes redirectAttributes) {
		activityService.delete(activity);
		addMessage(redirectAttributes, "删除保存活动列表成功成功");
		return "redirect:"+Global.getAdminPath()+"/operation/activity/?repage";
	}

}