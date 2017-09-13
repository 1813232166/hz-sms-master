/**
 * 定义角色接口
 */
package com.hzdjr.hzwd.modules.sys.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.sys.entity.Role;

/**
 * 角色DAO接口
 * @author lvwenchao
 * @version 2016-05-11
 */
@MyBatisDao
public interface RoleDao extends CrudDao<Role> {
	//根据角色名称查询
	public Role getByName(Role role);
	//根据英文名称查询
	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return 
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);
	
	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return 
	 */
	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);

}
