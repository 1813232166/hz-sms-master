/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.departmentdiction.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 营业部信息列表Entity
 * @author yansy
 * @version 2016-10-17
 */
public class DepartmentDiction extends DataEntity<DepartmentDiction> {
	
	private static final long serialVersionUID = 1L;
	private String id;			
	private String value;		// 部门
	private String type;		// 类型（1省 2市 3部门）
	private String code;		// 编码（部门编码）
	private String parendid;		// 父id(关联主键)
	private Date createtime;		// 创建时间
	private Date updatetime;		// 修改时间
	private String idvalid;		// 是否有效（默认为1有效，为0时无效）
	private String province;	//省
	private String city;		//市
	
	public DepartmentDiction() {
		super();
	}

	public DepartmentDiction(String id){
		super(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Length(min=0, max=50, message="内容（省、市、部门）长度必须介于 0 和 50 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=0, max=1, message="类型（1省 2市 3部门）长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=10, message="编码（部门编码）长度必须介于 0 和 10 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=32, message="父id(关联主键)长度必须介于 0 和 32 之间")
	public String getParendid() {
		return parendid;
	}

	public void setParendid(String parendid) {
		this.parendid = parendid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Length(min=1, max=1, message="是否有效（默认为1有效，为0时无效）长度必须介于 1 和 1 之间")
	public String getIdvalid() {
		return idvalid;
	}

	public void setIdvalid(String idvalid) {
		this.idvalid = idvalid;
	}
	
}