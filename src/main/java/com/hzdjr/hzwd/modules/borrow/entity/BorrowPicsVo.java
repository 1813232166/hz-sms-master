package com.hzdjr.hzwd.modules.borrow.entity;

import java.util.ArrayList;
import java.util.List;

public class BorrowPicsVo {
	
	private String picType;
	private List<BorrowPicVo> frontPicList = new ArrayList<BorrowPicVo>();
	private List<BorrowPicVo> backPicList = new ArrayList<BorrowPicVo>();
	
	public BorrowPicsVo() {
	}
	
	public BorrowPicsVo(String picType) {
		this.picType = picType;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public List<BorrowPicVo> getFrontPicList() {
		return frontPicList;
	}
	public void setFrontPicList(List<BorrowPicVo> frontPicList) {
		this.frontPicList = frontPicList;
	}
	public List<BorrowPicVo> getBackPicList() {
		return backPicList;
	}
	public void setBackPicList(List<BorrowPicVo> backPicList) {
		this.backPicList = backPicList;
	}
	
}
