package com.hzdjr.hzwd.modules.usermanage.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hzdjr.hzwd.common.utils.excel.annotation.ExcelField;

public class TUser {
	private String id;
	private String phone;
	private String uname;
	private String email;
	private String isThird;
	private Date regDate;
	private String type;
	
	private Integer status;
	
	@ExcelField(title="用户ID",type=1,align=2,sort=10)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ExcelField(title="手机号",type=1,align=2,sort=20)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ExcelField(title="姓名",type=1,align=2,sort=30)
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	@ExcelField(title="电子邮箱",type=1,align=2,sort=40)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@ExcelField(title="开通第三方",type=1,align=2,sort=50)
	public String getIsThird() {
		if("1".equals(isThird)){
			return "是";
		}else if("0".equals(isThird)){
			return "否";
		}else{
			return "";
		}
		
	}
	public void setIsThird(String isThird) {
		this.isThird = isThird;
	}
	@ExcelField(title="注册时间",type=1,align=2,sort=60)
	public String getRegDate(){
		if(regDate!=null){
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(regDate);
			return str;
		}else{
			return "";
		}
		
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@ExcelField(title="类型",type=1,align=2,sort=70)
	public String getType() {
		if("1".equals(type)){
			return "出借人";
		}else if("2".equals(type)){
			return "借款人";
		}else{
			return "";
		}
		
	}
	public void setType(String type) {
		this.type = type;
	}
	@ExcelField(title="状态",type=1,align=2,sort=80)
	public String getStatus() {
		if(status==null){
			return "";
		}
		if(status==0){
			return "正常";
		}else if(status==1){
			return "锁定";
		}else{
			return "";
		}
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
