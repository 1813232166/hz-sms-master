package com.hzwealth.sms.modules.borrow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.IdGen;
import com.hzwealth.sms.modules.borrow.dao.LoanFundDao;
import com.hzwealth.sms.modules.borrow.entity.BorrowPicVo;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrow.entity.TBorrowBillplan;

@Service
@Transactional(readOnly = false)
public class LoanFundService {
	@Autowired
	private LoanFundDao loanFundDao;
	
	public Page<TBorrow> getLoanList(Page<TBorrow> page,TBorrow tBorrow){
		tBorrow.setPage(page);
		return page.setList(loanFundDao.getLoanList(tBorrow));
	}
	public Map<String,Integer> getLoanCountList(){
		return loanFundDao.getLoanCountList();
	}
	@Transactional
	public Integer saveBorrowInfo(List<TBorrow> borrows){
		return loanFundDao.saveBorrowInfo(borrows);
	}
	@Transactional
	public Integer saveBillPlan(TBorrowBillplan billplan){
		return loanFundDao.saveBillPlan(billplan);
	}
	public boolean isRepeat(String applyId){
		Map<String, Object> counts = loanFundDao.queryBilPlanByApplyId(applyId);
		if ((Long)counts.get("count")>0) {
			return true;
		}
		return false;
	}
	public TBorrow findTBorrowById(Map<String, Object> map){
		return loanFundDao.findTBorrowById(map);
	}
	public Page<TBorrowBillplan> queryBilPlanByBorrowId(Page<TBorrowBillplan> page,TBorrowBillplan billplan){
		billplan.setPage(page);
		return page.setList(loanFundDao.queryBilPlanByBorrowId(billplan));
	}
	public void saveLoanInfo(Map<String, Object> map){
		loanFundDao.saveLoanInfo(map);
	}
	public Integer delPic(String id){
		return loanFundDao.delPic(id);
	}
	/**
	 * 图片类型(0-身份证1-工作证明2-住址证明3-房产证明4-征信报告5-收入证明6-经营地址证明)
	 * @param request
	 * @return
	 */
	public int editBorrow(HttpServletRequest request) {
		String addressImg = request.getParameter("addressImg");
		String houseImg =  request.getParameter("houseImg");
		String incomeImg = request.getParameter("incomeImg");
		String idcardImg =request.getParameter("idcardImg");
		String workImg = request.getParameter("workImg");
		String businessAddressImg = request.getParameter("businessAddressImg");
		String creditImg = request.getParameter("creditImg");
		
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		if(idcardImg != null){
			String[] idcardImgs = idcardImg.split(",");
			for (int i = 0; i < idcardImgs.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borrowId"));
				 map.put("id", IdGen.uuid());
				 map.put("picdesc", "身份证");
				 map.put("pictype", 0);
				 map.put("picurl", idcardImgs[i].trim());
				 items.add(map);
			}
		}
		
		if(workImg != null){
			String[] workImgs = workImg.split(",");
			for (int i = 0; i < workImgs.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borrowId"));
				map.put("id", IdGen.uuid());
				map.put("picdesc", "工作证明");
				map.put("pictype", 1);
				map.put("picurl", workImgs[i].trim());
				items.add(map);
			}
		}
		if(addressImg != null){
			String[] addressImgs = addressImg.split(",");
			for (int i = 0; i < addressImgs.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borrowId"));
				map.put("id", IdGen.uuid());
				map.put("picdesc", "住址证明");
				map.put("pictype", 2);
				map.put("picurl", addressImgs[i].trim());
				items.add(map);
			}
		}
		if(houseImg != null){
			String[] houseImgs = houseImg.split(",");	
			for (int i = 0; i < houseImgs.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borrowId"));
				map.put("id", IdGen.uuid());
				 map.put("picdesc", "房产证明");
				 map.put("pictype", 3);
				 map.put("picurl", houseImgs[i].trim());
				 items.add(map);
			}
		}
		if(creditImg != null){
			String[] creditImgs = creditImg.split(",");
			for (int i = 0; i < creditImgs.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borrowId"));
				 map.put("id", IdGen.uuid());
				 map.put("picdesc", "征信报告");
				 map.put("pictype", 4);
				 map.put("picurl", creditImgs[i].trim());
				 items.add(map);
			}
		}
		
		
		if(incomeImg != null){
			String[] incomeImgs = incomeImg.split(",");
			for (int i = 0; i < incomeImgs.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borrowId"));
				map.put("id", IdGen.uuid());
				map.put("picdesc", "收入证明");
				map.put("pictype", 5);
				map.put("picurl", incomeImgs[i].trim());
				items.add(map);
			}
		}
		
		
		if(businessAddressImg != null){
			String[] businessAddressImgs = businessAddressImg.split(",");
			for (int i = 0; i < businessAddressImgs.length; i++) {
				map = new HashMap<String, Object>();
				map.put("bid", request.getParameter("borrowId"));
				 map.put("id", IdGen.uuid());
				 map.put("picdesc", "经营地址证明");
				 map.put("pictype", 6);
				 map.put("picurl", businessAddressImgs[i].trim());
				 items.add(map);
			}
		}
		
		String type = (String) request.getParameter("type");
		if("2".equals(type)){
			loanFundDao.updateIsFinish(String.valueOf(request.getParameter("borrowId")));
		}
		if (null!=items && items.size()>0) {
			int batchSaveBorrowApply = loanFundDao.batchSaveBorrowApply(items);
			int count = loanFundDao.updateIsEdit(String.valueOf(request.getParameter("borrowId")));
			return batchSaveBorrowApply + count;
		}
		return 0;
	}
	//图片类型(0-身份证,1-工作证明,2-住址证明,3-房产证明,4-征信报告,5-收入证明,6-经营地址证明)
	public Map<String, List<BorrowPicVo>> getBorrowPic(String borrowId) {
		List<BorrowPicVo> borrowPic = loanFundDao.getBorrowPic(borrowId);
		Map<String, List<BorrowPicVo>> picMap = new HashMap<String, List<BorrowPicVo>>();
		List<BorrowPicVo> idCardList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> workList = new ArrayList<BorrowPicVo>();	
		List<BorrowPicVo> addressList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> houseList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> creditList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> incomeList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> businessAddressList = new ArrayList<BorrowPicVo>();
		for (BorrowPicVo borrowPicVo : borrowPic) {
			if("0".equals(borrowPicVo.getPictype())){
				idCardList.add(borrowPicVo);
			}
			if("1".equals(borrowPicVo.getPictype())){
				workList.add(borrowPicVo);
			}
			if("2".equals(borrowPicVo.getPictype())){
				addressList.add(borrowPicVo);
			}
			
			if("3".equals(borrowPicVo.getPictype())){
				houseList.add(borrowPicVo);
			}
			if("4".equals(borrowPicVo.getPictype())){
				creditList.add(borrowPicVo);
			}
			if("5".equals(borrowPicVo.getPictype())){
				incomeList.add(borrowPicVo);
			}
			if("6".equals(borrowPicVo.getPictype())){
				businessAddressList.add(borrowPicVo);
			}
		}
		picMap.put("idCardList", idCardList);
		picMap.put("workList", workList);
		picMap.put("addressList", addressList);
		picMap.put("houseList", houseList);
		picMap.put("creditList", creditList);
		picMap.put("incomeList", incomeList);
		picMap.put("businessAddressList", businessAddressList);
		return picMap;
	}
	//图片类型(0-身份证,1-工作证明,2-住址证明,3-房产证明,4-征信报告,5-收入证明,6-经营地址证明)
	public Map<String, List<BorrowPicVo>> getshenhePic(String borrowId) {
		List<BorrowPicVo> borrowPic = loanFundDao.getBorrowPic(borrowId);
		Map<String, List<BorrowPicVo>> picMap = new HashMap<String, List<BorrowPicVo>>();
		List<BorrowPicVo> idCardList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> workAndincomeList = new ArrayList<BorrowPicVo>();	
		List<BorrowPicVo> creditList = new ArrayList<BorrowPicVo>();
		List<BorrowPicVo> otherList = new ArrayList<BorrowPicVo>();
		
		for (BorrowPicVo borrowPicVo : borrowPic) {
			//0 身份证明
			if("0".equals(borrowPicVo.getPictype())){
				idCardList.add(borrowPicVo);
			}
			
			//工作及收入证明
			if("1".equals(borrowPicVo.getPictype()) || "5".equals(borrowPicVo.getPictype())){
				workAndincomeList.add(borrowPicVo);
			}
			
			
			
			
			//个人征信报告证明
			if("4".equals(borrowPicVo.getPictype())){
				creditList.add(borrowPicVo);
			}
			
			//其他证明
			if("2".equals(borrowPicVo.getPictype()) || "3".equals(borrowPicVo.getPictype()) || "6".equals(borrowPicVo.getPictype())){
				otherList.add(borrowPicVo);
			}
			
			
		}
		picMap.put("idCardList", idCardList);
		picMap.put("workAndincomeList", workAndincomeList);
		picMap.put("creditList", creditList);
		picMap.put("otherList", otherList);
		return picMap;
	}
	
}
