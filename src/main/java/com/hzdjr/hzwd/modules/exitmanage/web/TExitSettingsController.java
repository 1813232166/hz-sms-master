/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.exitmanage.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.DateUtils;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.excel.ExportExcel;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.exitmanage.entity.TExitData;
import com.hzdjr.hzwd.modules.exitmanage.entity.TExitSettings;
import com.hzdjr.hzwd.modules.exitmanage.service.TExitDataService;
import com.hzdjr.hzwd.modules.exitmanage.service.TExitSettingsService;

/**
 * 退出管理Controller
 * @author HDG
 * @version 2017-07-21
 */
@Controller
@RequestMapping(value = "${adminPath}/exitmanage/tExitSettings")
public class TExitSettingsController extends BaseController {

	@Autowired
	private TExitSettingsService tExitSettingsService;
	@Autowired
	private TExitDataService tExitDataService;
	
	@ModelAttribute
	public TExitSettings get(@RequestParam(required=false) String id) {
		TExitSettings entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tExitSettingsService.get(id);
		}
		if (entity == null){
			entity = new TExitSettings();
		}
		return entity;
	}
	/**
	 * 
	 * Description: 退出管理列表
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月21日 下午3:43:23
	 */
	@RequestMapping(value = {"list", ""})
	public String list(TExitSettings tExitSettings, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TExitSettings> page = tExitSettingsService.findPage(new Page<TExitSettings>(request, response), tExitSettings); 
		model.addAttribute("page", page);
		model.addAttribute("tExitSettings", tExitSettings);
		return "modules/exitmanage/tExitSettingsList";
	}
	/**
	 * 
	 * Description: 退出审核列表
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月21日 下午3:42:34
	 */
	@RequestMapping(value = {"exitAuditList", ""})
	public String exitAuditList(TExitSettings tExitSettings, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TExitSettings> page = tExitSettingsService.findPage(new Page<TExitSettings>(request, response), tExitSettings); 
		model.addAttribute("page", page);
		model.addAttribute("tExitSettings", tExitSettings);
		return "modules/exitmanage/exitAuditList";
	}
	/**
	 * 
	 * Description: 设置退出金额-页面跳转
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月25日 上午10:39:30
	 */
	@RequestMapping("/setExitMoney")
	public String setExitMoney(TExitSettings tExitSettings, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TExitSettings> page = tExitSettingsService.findPage(new Page<TExitSettings>(request, response), tExitSettings); 
		model.addAttribute("page", page);
		model.addAttribute("tExitSettings", tExitSettings);
		return "modules/exitmanage/setExitMoney";
	}
	/**
	 * 
	 * Description: 修改页面
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月26日 下午4:01:28
	 */
	@RequestMapping("/editorExitSetting")
	public String editorExitSetting(TExitSettings tExitSettings, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TExitSettings> page = tExitSettingsService.findPage(new Page<TExitSettings>(request, response), tExitSettings); 
		model.addAttribute("page", page);
		model.addAttribute("tExitSettings", tExitSettings);
		return "modules/exitmanage/editorExitSetting";
	}
	/**
	 * 
	 * Description: 设置退出金额
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月25日 下午3:17:39
	 */
	@ResponseBody
	@RequestMapping("/updateCanQuitAmount")
	public boolean updateCanQuitAmount( String id ,String quitAmount) {
		try {
			tExitSettingsService.updateCanQuitAmount(id,quitAmount);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 
	 * Description: 设置金额按钮的可见,如果没有记录则可见,否则隐藏
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月28日 上午9:57:31
	 */
	@ResponseBody
	@RequestMapping("/setExitMoneyShow")
	public boolean setExitMoneyShow() {
		try {
			int setExitMoneyShow = tExitSettingsService.setExitMoneyShow();
			if(setExitMoneyShow>0){//有记录
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 
	 * Description: 提交审核
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月26日 下午3:22:26
	 */
	@ResponseBody
	@RequestMapping("/submitAudit")
	public boolean submitAudit(String id ) {
		try {
			tExitSettingsService.submitAudit(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 
	 * Description: 审核
	 *
	 * @param 
	 * @return boolean
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月26日 下午5:45:03
	 */
	@ResponseBody
	@RequestMapping("/audit")
	public boolean audit(String id,String auditRadio,String auditArea) {
		try {
			tExitSettingsService.audit(id,auditRadio,auditArea);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
   
	/**
	 * 
	 * Description: 修改  
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月25日 上午11:56:12
	 */
	@RequestMapping(value = "form")
	public String form(TExitSettings tExitSettings, Model model) {
		model.addAttribute("tExitSettings", tExitSettings);
		return "modules/exitmanage/tExitSettingsForm";
	}
	/**
	 * 
	 * Description: 退出管理--查看
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author huangdegui
	 * Create Date: 2017年7月21日 下午4:50:21
	 */
	@RequestMapping(value = "exitSettingsExamine")
	public String exitSettingsExamine(TExitSettings tExitSettings,TExitData tExitData, HttpServletRequest request, HttpServletResponse response, Model model) {
		String quitAmountSum = tExitDataService.getQuitAmountSum();//获取已退出总金额
		if(org.apache.commons.lang.StringUtils.isEmpty(quitAmountSum)){
			quitAmountSum="0";
		}
		Page<TExitData> tExitDataPage = tExitDataService.findPage(new Page<TExitData>(request, response), tExitData); 
		model.addAttribute("tExitDataPage", tExitDataPage);
		model.addAttribute("tExitSettings", tExitSettings);
		model.addAttribute("tExitData", tExitData);
		model.addAttribute("quitAmountSum", quitAmountSum);
		List<TExitSettings> findList = tExitSettingsService.findList();
		model.addAttribute("findList", findList);
		return "modules/exitmanage/exitSettingsExamine";
	}
	@RequestMapping(value = "exportExitData", method=RequestMethod.POST)
	public String exportExitData(TExitData tExitData,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
	    try {
	        String fileName = "退出管理详情" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
	        Page<TExitData> page = tExitDataService.findPage(new Page<TExitData>(request, response, -1), tExitData);
	        new ExportExcel("退出管理详情", TExitData.class).setDataList(page.getList()).write(response, fileName).dispose();
	        return null;
	      } catch (Exception  e) {
	        addMessage(redirectAttributes, "退出管理详情！失败信息：" + e.getMessage());
	      }
		return "redirect:" + Global.getAdminPath() + "/exitmanage/tExitSettings/exitSettingsExamine";
	}
	@RequestMapping(value = "save")
	public String save(TExitSettings tExitSettings, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tExitSettings)){
			return form(tExitSettings, model);
		}
		tExitSettingsService.save(tExitSettings);
		addMessage(redirectAttributes, "保存退出管理成功");
		return "redirect:"+Global.getAdminPath()+"/exitmanage/tExitSettings/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TExitSettings tExitSettings, RedirectAttributes redirectAttributes) {
		tExitSettingsService.delete(tExitSettings);
		addMessage(redirectAttributes, "删除退出管理成功");
		return "redirect:"+Global.getAdminPath()+"/exitmanage/tExitSettings/list";
	}

}