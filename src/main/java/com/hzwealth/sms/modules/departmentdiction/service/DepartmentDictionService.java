/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.departmentdiction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.service.CrudService;
import com.hzwealth.sms.modules.departmentdiction.dao.DepartmentDictionDao;
import com.hzwealth.sms.modules.departmentdiction.entity.City;
import com.hzwealth.sms.modules.departmentdiction.entity.DepartmentDiction;
import com.hzwealth.sms.modules.departmentdiction.entity.DeptDiction;
import com.hzwealth.sms.modules.departmentdiction.entity.Province;

/**
 * 营业部信息列表Service
 * @author yansy
 * @version 2016-10-17
 */
@Service
@Transactional(readOnly = true)
public class DepartmentDictionService extends CrudService<DepartmentDictionDao, DepartmentDiction> {

	@Autowired
	DepartmentDictionDao departmentDictionDao;
	
	public DepartmentDiction get(String id) {
		return super.get(id);
	}
	
	public List<DepartmentDiction> findList(DepartmentDiction departmentDiction) {
		return super.findList(departmentDiction);
	}
	
	public Page<DepartmentDiction> findPage(Page<DepartmentDiction> page, DepartmentDiction departmentDiction) {
		return super.findPage(page, departmentDiction);
	}
	
	@Transactional(readOnly = false)
	public void save(DepartmentDiction departmentDiction) {
		super.save(departmentDiction);
	}
	
	@Transactional(readOnly = false)
	public void delete(DepartmentDiction departmentDiction) {
		super.delete(departmentDiction);
	}

	public List<Province> findProvinceList() {
		return departmentDictionDao.findProvinceList();
	}

	public List<City> findCity(String pid) {
		// TODO Auto-generated method stub
		return departmentDictionDao.findCity(pid);
	}

	public List<City> findCityByProvince(String province) {
		// TODO Auto-generated method stub
		return departmentDictionDao.findCityByProvince(province);
	}

	public String findParendIdByCity(String city) {
		// TODO Auto-generated method stub
		return departmentDictionDao.findParendIdByCity(city);
	}

	public DeptDiction finddeptDictionById(String id) {
		// TODO Auto-generated method stub
		return departmentDictionDao.finddeptDictionById(id);
	}
	
}