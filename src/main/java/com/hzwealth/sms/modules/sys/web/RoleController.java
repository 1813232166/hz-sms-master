/**
 * 角色管理Controller
 */
package com.hzwealth.sms.modules.sys.web;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.Collections3;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.sys.entity.Office;
import com.hzwealth.sms.modules.sys.entity.Role;
import com.hzwealth.sms.modules.sys.entity.User;
import com.hzwealth.sms.modules.sys.service.OfficeService;
import com.hzwealth.sms.modules.sys.service.SystemService;
import com.hzwealth.sms.modules.sys.utils.UserUtils;
/**
 * 角色管理Action
 * @author lvwenchao
 * @version 2016-05-12
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/role")
public class RoleController extends BaseController {
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute("role")
	public Role get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getRole(id);
		}else{
			return new Role();
		}
	}
	
	/**@author lvwenchao
	 * name 为 “sys:role:view” 有权限看角色列表
	 * @param model 视图模型
	 * @param role 角色实体
	 * @return  String
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value={"list",""})
	public String list(Model model ,Role role){
		List<Role> list = systemService.findAllRole();
		//把查询到的数据放入视图模型中
		model.addAttribute("list", list);
		//返回视图
		return "modules/sys/roleList";
	}
	
	/**
	 * @author lvwenchao
	 * name 为 “sys:role:view” 有权限操作角色添加
	 * @param model 视图模型
	 * @param role 角色实体
	 * @param sid 机构id
	 * @return String
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value="form")
	public String form(Model model,Role role,String sid){
		//系统以及机构的关系维护
		if(role.getOffice()==null){
			role.setOffice(UserUtils.getUser().getOffice());
		}
		if(!StringUtils.isEmpty(sid)){
			role.setOffice(officeService.get(sid));
		}
		//把所需的数据放入视图模型中
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("officeList", officeService.findAll());
		model.addAttribute("role", role);
		//返回视图
		return "modules/sys/roleForm";
	}
	
	/**角色删除
	 * @author lvwenchao
	 * name 为 “sys:role:edit” 有权限操作角色删除
	 * @param redirectAttributes 重置参数
	 * @param role 角色实体
	 * @return String
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="delete")
	public String delete(RedirectAttributes redirectAttributes,Role role){
		//用户级别（管理员）判断操作动向
		if(!UserUtils.getUser().isAdmin()&&role.getSysData().equals(Global.YES)){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		systemService.deleteRole(role);
		addMessage(redirectAttributes, "删除角色成功！");
		return "redirect:" + adminPath + "/sys/role/?repage";
	}
	
	/**
	 * 角色添加保存
	 * @author  lvwenchao
	 * name 为 “sys:role:edit” 有权限操作角色添加保存
	 * @param role 角色实体
	 * @param model 视图模型
	 * @param redirectAttributes 重置参数 提示信息
	 * @return String
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="save")
	public String save(Role role, Model model, String sid,RedirectAttributes redirectAttributes){
		//用户级别（管理员）判断操作动向
				if(!UserUtils.getUser().isAdmin()&&role.getSysData().equals(Global.YES)){
					addMessage(redirectAttributes, "越权操作，只有超级管理员才能修改此数据！");
					return "redirect:" + adminPath + "/sys/role/?repage";
				}
				if(Global.isDemoMode()){
					addMessage(redirectAttributes, "演示模式，不允许操作！");
					return "redirect:" + adminPath + "/sys/role/?repage";
				}
				if(!beanValidator(model, role)){
					return form(model, role, sid);
				}
				if(!"true".equals(checkName(role.getOldName(), role.getName()))){
					addMessage(model, "保存角色'"+role.getName()+"'失败,角色名已经存在！");
					return form(model, role, sid);
				}
				systemService.saveRole(role);
				addMessage(redirectAttributes, "保存角色'"+role.getName()+"'成功！");
				return "redirect:" + adminPath + "/sys/role/?repage";
	}
	
	/**@author lvwenchao
	 * 验证角色名是否有效
	 * @param oldName
	 * @param name
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "checkName")
	public String checkName(String oldName, String name) {
		if (name!=null && name.equals(oldName)) {
			return "true";
		} else if (name!=null && systemService.getRoleByName(name) == null) {
			return "true";
		}
		return "false";
	}
	
	/**角色分配页面
	 * @author lvwenchao
	 * name 为 'sys:role:edit' 有权限访问角色分配页面
	 * @param model 视图模型
	 * @param role 角色实体
	 * @return String
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="assign")
	public String assign(Model model,Role role){
		User user = new User();
		Role role1 = new Role();
		role1.setId(role.getId());
		user.setRole(role1);
		//new User(new Role(role.getId()));
		List<User> userList = systemService.findUser(user);
		model.addAttribute("userList", userList);
		return "modules/sys/roleAssign";
	}
	
	/**@author lvwenchao
	 * 角色分配 --name为“sys:role:view” 才能打开角色分配对话框
	 * @param role 角色实体
	 * @param model 视图模型
	 * @return
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "usertorole")
	public String selectUserToRole(Role role, Model model) {
		//查询人员列表
		List<User> userList = systemService.findUser(new User(new Role(role.getId())));
		//把所需数据存到视图模型中
		model.addAttribute("role", role);
		model.addAttribute("userList", userList);
		//提取查询到人员中的姓名存到视图模型中
		model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
		//获取当前用户的有权限访问的所有机构存到视图模型中
		model.addAttribute("officeList", officeService.findAll());
		//返回视图
		return "modules/sys/selectUserToRole";
	}
	
	/**
	 * @author lvwenchao
	 * 角色分配---根据部门编号获取用户列表（name为“sys:role:view”有权操作）
	 * @param response 请求响应体
	 * @param officeId 机构id
	 * @return list
	 */
	@RequiresPermissions("sys:role:view")
	@ResponseBody
	@RequestMapping(value="users")
	public List<Map<String, Object>> users(HttpServletResponse response,String officeId){
		List<Map<String,Object>> mapList=Lists.newArrayList();
		User user = new User();
		Office office = new Office();
		office.setId(officeId);
		user.setOffice(office);
		//根据登录名获取用户
		Page<User> page = systemService.findUser(new Page<User>(1,-1), user);
		for (User u : page.getList()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", u.getId());
			map.put("pId", 0);
			map.put("name", u.getName());
			mapList.add(map);
		}
		return mapList;
	}
	
	/***
	 * @author lvwenchao
	 * 角色分配 -- 从角色中移除用户(name为“sys:role:edit”有权操作)
	 * @param userId 用户Id
	 * @param roleId 角色Id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value="outrole")
	public String outrole(String userId,String roleId,RedirectAttributes redirectAttributes){
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/assign?id="+roleId;
		}
		Role role = systemService.getRole(roleId);
		User user = systemService.getUser(userId);
		if(UserUtils.getUser().getId().equals(userId)){
			addMessage(redirectAttributes, "无法从角色【" + role.getName() + "】中移除用户【" + user.getName() + "】自己！");
		}else{
				if(user.getRoleList().size()<=1){
					addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除失败！这已经是该用户的唯一角色，不能移除。");
					}else{
						Boolean flag = systemService.outUserInRole(role, user);
						if (!flag) {
							addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除失败！");
						}else {
							addMessage(redirectAttributes, "用户【" + user.getName() + "】从角色【" + role.getName() + "】中移除成功！");
						}
					}
			}
		return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
	}
	
	/**@author lvwenchao
	 * 角色分配(name为“sys:role:edit”有权操作)
	 * @param role 角色 实体
	 * @param idsArr 数组
	 * @param redirectAttributes
	 * @return String
	 */
	@RequiresPermissions("sys:role:edit")
	@RequestMapping(value = "assignrole")
	public String assignRole(Role role, String[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			User user = systemService.assignUserToRole(role, systemService.getUser(idsArr[i]));
			if (null != user) {
				msg.append("<br/>新增用户【" + user.getName() + "】到角色【" + role.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 "+newNum+" 个用户"+msg);
		return "redirect:" + adminPath + "/sys/role/assign?id="+role.getId();
	}

}
