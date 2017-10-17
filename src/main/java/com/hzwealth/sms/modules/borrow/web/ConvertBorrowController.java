package com.hzwealth.sms.modules.borrow.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrow.service.TBorrowService;
import com.hzwealth.sms.modules.borrow.util.BorrowAliasUtils;
/**
 * 普享标编辑
 * @author xq
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/convert")
public class ConvertBorrowController extends BaseController {
	
	@Autowired
	private TBorrowService tBorrowService;
	
	@RequestMapping("/toEdit")
	public String toNewBorrowEdit(HttpServletRequest request,Model model){
		try {
			String borrowIds = request.getParameter("borrowIds");
			String[] bws = borrowIds.split(",");
			List<String> parm=new ArrayList<String>();
			if (bws.length>0) {
				for (int i = 0; i < bws.length; i++) {
					if (bws[i].trim().length()>0) {
						parm.add(bws[i]);
					}
				}
			}else{//只有一个标的
				parm.add(borrowIds.trim());
			}
			List<TBorrow> borrows = tBorrowService.getLoanByBorrowCode(parm);
			BigDecimal borrowsTotal = new BigDecimal(0);
			for (TBorrow tBorrow : borrows) {
				String borrowAlias = BorrowAliasUtils.getBorrowNo();
				String borrowAliasNo = "PX"+borrowAlias.substring(3, borrowAlias.length());
				tBorrow.setBorrowaliasno(borrowAliasNo);
				tBorrow.setBorrowalias(borrowAlias);
				tBorrow.setMintendersum("0.0");
				BigDecimal borrowAmont=new BigDecimal(tBorrow.getBorrowamount());
				borrowsTotal = borrowsTotal.add(borrowAmont);
			}
			model.addAttribute("borrows", borrows);
			model.addAttribute("borrowsTotal", borrowsTotal);
		} catch (Exception e) {
			logger.info("toNewBorrowEdit fail :", e);
		}
		return "modules/borrow/newBorrowEdit";
	}
	
	@RequestMapping("/toEditNew")
	public String toNewBorrowEditNew(HttpServletRequest request,Model model){
		try {
			String borrowIds = request.getParameter("borrowIds");
			String[] bws = borrowIds.split(",");
			List<String> parm=new ArrayList<String>();
			if (bws.length>0) {
				for (int i = 0; i < bws.length; i++) {
					if (bws[i].trim().length()>0) {
						parm.add(bws[i]);
					}
				}
			}else{//只有一个标的
				parm.add(borrowIds.trim());
			}
			List<TBorrow> borrows = tBorrowService.getLoanByBorrowCodeNew(parm);
			BigDecimal borrowsTotal = new BigDecimal(0);
			for (TBorrow tBorrow : borrows) {
				String borrowAlias = BorrowAliasUtils.getBorrowNo();
				String borrowAliasNo = "PX"+borrowAlias.substring(3, borrowAlias.length());
				tBorrow.setBorrowaliasno(borrowAliasNo);
				tBorrow.setBorrowalias(borrowAlias);
				BigDecimal borrowAmont=new BigDecimal(tBorrow.getBorrowamount());
				borrowsTotal = borrowsTotal.add(borrowAmont);
			}
			model.addAttribute("borrows", borrows);
			model.addAttribute("borrowsTotal", borrowsTotal);
		} catch (Exception e) {
			logger.info("toNewBorrowEdit fail :", e);
		}
		return "modules/borrow/newBorrowEditNew";
	}

	
	
	@RequestMapping("/releaseBorrow")
	public String releaseBorrow(HttpServletRequest request){
		String isBorrow = request.getParameter("isborrow");
		try {
			String[] borrowIdArray = request.getParameterValues("selectBorrow");//标的ID
			String riskWarning = request.getParameter("riskWarning");//风险提示
			String raiseTerm = request.getParameter("raiseterm");//募集期
			String raiseTermUnit = request.getParameter("raisetermunit");//募集期单位
			String fillMark = request.getParameter("fillmark");//到期补标
			String usageofloan = request.getParameter("usageofloan");//项目介绍
			if(null==fillMark||"null".equals(fillMark)||"".equals(fillMark)){
				fillMark = "0";
			}
			String mintenderSum = request.getParameter("minTenderSum");//起投金额
			String borrowStatus = request.getParameter("borrowStatus");//发布类型
			String isMatch = "1";//是否匹配
			String openborrowDateStr = request.getParameter("openBorrowDate");//发布时间
			Date openborrowDate = null;
			if (null!=openborrowDateStr) {				
				if((!"null".equals(openborrowDateStr)) && (!"".equals(openborrowDateStr))){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					openborrowDate = sdf.parse(openborrowDateStr);
				}
			}
			
			List<TBorrow> borrows = new ArrayList<TBorrow>();
			TBorrow borrow =null;
			for(String borrowId: borrowIdArray){
				borrow = new TBorrow();
				String bid = borrowId.split(",")[0];
				String blno = borrowId.split(",")[1];
				borrow.setBorrowaliasno(blno);
				borrow.setBorrowalias("普享标"+blno.substring(2));
				borrow.setBorrowId(bid);
				borrow.setRiskWarning(riskWarning);
				borrow.setRaiseterm(raiseTerm);;
				borrow.setRaisetermunit(raiseTermUnit);;
				borrow.setFillMark(fillMark);
				borrow.setMintendersum(mintenderSum);
				borrow.setBorrowstatus(borrowStatus);
				borrow.setIsMatch(isMatch);
				borrow.setOpenborrowdate(openborrowDate);
				borrow.setProjectBrief(usageofloan);
				borrows.add(borrow);
			}
			tBorrowService.releaseBorrow(borrows);
		} catch (Exception e) {
			logger.info("releaseBorrow fail :", e);
		}
		if("1".equals(isBorrow)){
			return "redirect:"+Global.getAdminPath()+"/borrow/borrow/?repage";	
		}else{
			return "redirect:"+Global.getAdminPath()+"/borrow/tBorrow/?repage";			
		}
	}
	
}
