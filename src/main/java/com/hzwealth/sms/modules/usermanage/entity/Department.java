package com.hzwealth.sms.modules.usermanage.entity;

import java.util.Date;

public class Department {

	private String id;   //逻辑主键',
	private String value;   //内容（省、市、部门）',
	private String type;    //类型（1省 2市 3部门）'
	private String code;   //编码（部门编码）',
	private String parentId;  //父id(关联主键)'
	private Date createTime;   //创建时间'
	private Date updateTime;   //修改时间
	private String idValid;    //是否有效（默认为1有效，为0时无效）',
	
}
