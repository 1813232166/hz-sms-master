/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.departmentdiction.dao;

import java.util.List;
import java.util.Map;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.departmentdiction.entity.City;
import com.hzdjr.hzwd.modules.departmentdiction.entity.DepartmentDiction;
import com.hzdjr.hzwd.modules.departmentdiction.entity.DeptDiction;
import com.hzdjr.hzwd.modules.departmentdiction.entity.Province;

/**
 * 营业部信息列表DAO接口
 * @author yansy
 * @version 2016-10-17
 */
@MyBatisDao
public interface DepartmentDictionDao extends CrudDao<DepartmentDiction> {

	List<Province> findProvinceList();

	List<City> findCity(String pid);

	List<DepartmentDiction> findList(Map<String, Object> map);

	List<City> findCityByProvince(String province);

	String findParendIdByCity(String city);

	DeptDiction finddeptDictionById(String id);
	
}