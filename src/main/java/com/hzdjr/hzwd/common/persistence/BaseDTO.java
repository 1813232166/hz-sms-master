package com.hzdjr.hzwd.common.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
/**
 * 
* @Description: ajax 返回BaseDTO
* @version V2.0 
* @author FYP 
* <pre>
* Modification History: 
* Date               Author      Version     Description 
* ------------------------------------------------------------------ 
* 2017年6月15日      		  FYP          2.0       2.0 Version 
* </pre>
 */
@Component
public class BaseDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private List<?> list;
	
	private Map<String,?> map;
	
	private boolean rtnFlag;
	
	private int rtnCode;
	
	private String msg;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Map<String, ?> getMap() {
		return map;
	}

	public void setMap(Map<String, ?> map) {
		this.map = map;
	}

	public int getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(int rtnCode) {
		this.rtnCode = rtnCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isRtnFlag() {
		return rtnFlag;
	}

	public void setRtnFlag(boolean rtnFlag) {
		this.rtnFlag = rtnFlag;
	}

}
