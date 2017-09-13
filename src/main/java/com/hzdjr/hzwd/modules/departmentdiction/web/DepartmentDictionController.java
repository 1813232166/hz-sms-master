/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.departmentdiction.web;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.common.utils.StringUtils;
import com.hzdjr.hzwd.common.utils.UUIDUtil;
import com.hzdjr.hzwd.modules.departmentdiction.entity.City;
import com.hzdjr.hzwd.modules.departmentdiction.entity.DepartmentDiction;
import com.hzdjr.hzwd.modules.departmentdiction.entity.DeptDiction;
import com.hzdjr.hzwd.modules.departmentdiction.entity.Province;
import com.hzdjr.hzwd.modules.departmentdiction.service.DepartmentDictionService;

/**
 * 营业部信息列表Controller
 * @author yansy
 * @version 2016-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/departmentdiction/departmentDiction")
public class DepartmentDictionController extends BaseController {

	@Autowired
	private DepartmentDictionService departmentDictionService;
	
	@ModelAttribute
	public DepartmentDiction get(@RequestParam(required=false) String id) {
		DepartmentDiction entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = departmentDictionService.get(id);
		}
		if (entity == null){
			entity = new DepartmentDiction();
		}
		return entity;
	}
	
	//@RequiresPermissions("departmentdiction:departmentDiction:view")
	@RequestMapping(value = {"list", ""})
	public String list(DepartmentDiction departmentDiction, HttpServletRequest request, HttpServletResponse response, Model model) {
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		departmentDiction.setProvince(province);
		departmentDiction.setCity(city);
		List<Province> provinceList = departmentDictionService.findProvinceList();
		List<City> cityList = departmentDictionService.findCityByProvince(province);
		Page<DepartmentDiction> page = departmentDictionService.findPage(new Page<DepartmentDiction>(request, response), departmentDiction); 
		page.setPageSize(10);
		model.addAttribute("page", page);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("province", province);
		model.addAttribute("city", city);
		model.addAttribute("cityList", cityList);
		return "modules/departmentdiction/departmentDictionList";
	}
	
	@RequestMapping(value="findCity")
	@ResponseBody
	public List<City> findCity(Model model,String pid) {
		List<City> cityList = departmentDictionService.findCity(pid);
		return cityList;
	}

	//@RequiresPermissions("departmentdiction:departmentDiction:view")
	@RequestMapping(value = "form")
	public String form(DepartmentDiction departmentDiction, Model model) {
		DeptDiction deptDiction = departmentDictionService.finddeptDictionById(departmentDiction.getId());
		if(null != deptDiction){
			if(null != deptDiction.getCity()){
				departmentDiction.setCity(deptDiction.getCity());
			}
			if(null != deptDiction.getProvince()){
				departmentDiction.setProvince(deptDiction.getProvince());
			}
		}
		List<Province> provinceList = departmentDictionService.findProvinceList();
		String province = departmentDiction.getProvince();
		List<City> cityList = departmentDictionService.findCityByProvince(province);
		model.addAttribute("cityList", cityList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("departmentDiction", departmentDiction);
		return "modules/departmentdiction/departmentDictionForm";
	}

	//@RequiresPermissions("departmentdiction:departmentDiction:edit")
	@RequestMapping(value = "save")
	public String save(DepartmentDiction departmentDiction, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		departmentDiction.setProvince(province);
		departmentDiction.setCity(city);
		String parendId = departmentDictionService.findParendIdByCity(city);
		departmentDiction.setParendid(parendId);
		departmentDiction.setType("3");
		if(null == departmentDiction.getId() || "".equals(departmentDiction.getId())){
			departmentDiction.setCreatetime(new Date());
			departmentDiction.setIsNewRecord(true);
			departmentDiction.setId(UUIDUtil.genUUIDString());
		}else{
			departmentDiction.setUpdatetime(new Date());
		}
		departmentDiction.setIdvalid("1");
		if (!beanValidator(model, departmentDiction)){
			return form(departmentDiction, model);
		}
		departmentDictionService.save(departmentDiction);
		addMessage(redirectAttributes, "保存营业部信息成功");
		return "redirect:"+Global.getAdminPath()+"/departmentdiction/departmentDiction/?repage";
	}
	
	//@RequiresPermissions("departmentdiction:departmentDiction:edit")
	@RequestMapping(value = "delete")
	public String delete(DepartmentDiction departmentDiction, RedirectAttributes redirectAttributes) {
		departmentDictionService.delete(departmentDiction);
		addMessage(redirectAttributes, "删除营业部信息成功");
		return "redirect:"+Global.getAdminPath()+"/departmentdiction/departmentDiction/?repage";
	}

}