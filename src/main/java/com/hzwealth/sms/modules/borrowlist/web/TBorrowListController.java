/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.borrowlist.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.DateUtils;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.utils.excel.ExportExcel;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.borrow.entity.Borrow;
import com.hzwealth.sms.modules.borrow.entity.TBorrow;
import com.hzwealth.sms.modules.borrow.service.TBorrowService;
import com.hzwealth.sms.modules.borrow.util.BorrowAliasUtils;
import com.hzwealth.sms.modules.borrowlist.entity.TBorrowList;
import com.hzwealth.sms.modules.borrowlist.service.BorrowListEditService;
import com.hzwealth.sms.modules.borrowlist.service.TBorrowListService;
import com.hzwealth.sms.modules.invest.entity.Invest;


/**
 * 散标集列表Controller
 * @author xq
 * @version 2017-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/borrow/borrowList")
public class TBorrowListController extends BaseController {

	@Autowired
	private TBorrowListService tBorrowListService;
	@Autowired
	private BorrowListEditService borrowListEditService;
	@Autowired
	private TBorrowService tBorrowService;
	@ModelAttribute
	public TBorrowList get(@RequestParam(required=false) String tBorrowListId) {
	    TBorrowList entity = null;
		if (StringUtils.isNotBlank(tBorrowListId)){
			entity = tBorrowListService.get(tBorrowListId);
		}
		if (entity == null){
			entity = new TBorrowList();
		}
		return entity;
	}
	
	@ModelAttribute
	public TBorrow getTWillBorrow(@RequestParam(required=false) String id) {
		TBorrow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tBorrowService.get(id);
		}
		if (entity == null){
			entity = new TBorrow();
		}
		return entity;
	}
	/**
	 * 散标集列表
	 * @param tBorrow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("borrow:tBorrow:view")
	@RequestMapping(value = {"list", ""})
	public String list(TBorrowList tBorrowList, HttpServletRequest request, HttpServletResponse response, Model model) {
	    Map<String, Object> borrowListSum = tBorrowListService.findBorrowListSum(tBorrowList);
	    Page<TBorrowList> page = tBorrowListService.findPage(new Page<TBorrowList>(request, response), tBorrowList);
	    model.addAttribute("page", page);
	    borrowListSum.put("borrowNum",page.getCount());//集合个数
	    model.addAttribute("countMap",borrowListSum);
	    model.addAttribute("tBorrowList", tBorrowList);
	    return "modules/borrowlist/tBorrowList";
	}

//	@RequiresPermissions("borrow:tBorrow:view")
  @RequestMapping(value = "detailList")
  public String detailList(TBorrowList tBorrowList, HttpServletRequest request, HttpServletResponse response, Model model) {
    model.addAttribute("tBorrowList", tBorrowList);
    // 匹配债权(一对多)
    List<TBorrow> tborrow_List = tBorrowListService.getTBorrow(tBorrowList.getBorrowListId());
    // tborrow_List 债权列表
    model.addAttribute("tborrow_List", tborrow_List);
    // 出借记录
    Invest inv = new Invest();
    Page<Invest> page = tBorrowListService.getInvRecByBid(new Page<Invest>(request, response), tBorrowList.getBorrowListId(), inv);
    model.addAttribute("page", page);
    //普享标集状态
    String statusMemo = request.getParameter("statusMemo");
    try {
      statusMemo = URLDecoder.decode(statusMemo, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    model.addAttribute("statusMemo", statusMemo);
    return "modules/borrowlist/tBorrowListForm";
  }
	/**
	 *  导出散标集列表
	 * @param request
	 * @param response
	 * @param tBorrow
	 * @param redirectAttributes
	 * @throws IOException
	 */
	@RequestMapping(value="exportBorrowList")
	public String exportBorrowList(HttpServletRequest request,HttpServletResponse response,TBorrowList tBorrowList,RedirectAttributes redirectAttributes) throws IOException {
	    List<TBorrowList> exportList = tBorrowListService.exportBorrowList(tBorrowList);
	    String fileName = "散标集列表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	    try {
            new ExportExcel("散标集列表", TBorrowList.class).setDataList(exportList).write(response, fileName).dispose();
           return null;
         } catch (IOException e) {
            // TODO Auto-generated catch block
            addMessage(redirectAttributes, "导出信息失败！失败信息："+e.getMessage());
        }
        
         return "redirect:" + Global.getAdminPath() +"/borrow/borrowList/list"; 
	}
	/**
	 * 去编辑页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEdit")
	public String toNewBorrowEdit(HttpServletRequest request,Model model){
		
		String borrowListCode = request.getParameter("borrowListIds");
		TBorrowList borrowList = borrowListEditService.getBorrowListCode(borrowListCode);
		try {
			List<TBorrow> borrows = borrowListEditService.getBorrowListId(borrowList.getBorrowListId());
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
			model.addAttribute("borrowList",borrowList);
		
		} catch (Exception e) {
			logger.info("toNewBorrowEditList fail :", e);
		}
		return "modules/borrowlist/borrowListEdit";
	}
	/**
	 * 撤销散标集
	 * @param tBorrowList
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(TBorrowList tBorrowList, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes){
	    try {
            tBorrowListService.delete(tBorrowList);
            TBorrowList tBorrowList2 = tBorrowListService.get(tBorrowList.getBorrowListId());
            //更新单个散标
            tBorrowListService.updatePerBorrowStatus(tBorrowList2.gettBorrow());
            addMessage(redirectAttributes, "撤销散标集成功");
        } catch (Exception e) {
            logger.info("撤销散标集异常"+e.getMessage());
            addMessage(redirectAttributes, "撤销散标集异常："+e.getMessage());
            e.printStackTrace();
        }
	    return "redirect:"+Global.getAdminPath()+"/borrow/borrowList/?repage";
	}
	/**
	 * 保存编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveBorrowListEdit")
	public String saveBorrowListEdit(HttpServletRequest request){
		try {
			String[] borrowIdArray = request.getParameterValues("selectBorrow");//标的ID
			String borrowListId = request.getParameter("borrowListId");//标的ID
			
			String riskWarning = request.getParameter("riskWarning");//风险提示
			String usageofloan = request.getParameter("usageofloan");//项目介绍
			String raiseTerm = request.getParameter("raiseterm");//募集期
			String mintenderSum = request.getParameter("minTenderSum");//起投金额
			String raisetermunit = request.getParameter("raisetermunit");//筹标期限单位（0小时 1天）
			
			//散标集信息
			TBorrowList tborrowList = new TBorrowList();
			tborrowList.setBorrowListId(borrowListId);
			tborrowList.setRiskWarning(riskWarning);
			tborrowList.setProjectBrief(usageofloan);
			tborrowList.setRaiseDay(raiseTerm);
			tborrowList.setBorrowListStatus("1");//1待审核
			tborrowList.setMinTenderSum(new BigDecimal(mintenderSum));
			
			Map<String,Integer> map=new HashMap<String,Integer>();
			//原散标
			List<TBorrow> borrowsList = borrowListEditService.getBorrowListId(borrowListId);
			for(TBorrow tb:borrowsList){
				map.put(tb.getBorrowId(), 1);
			}
			//剩余的散标
			for(String borrowId: borrowIdArray){
				String bid = borrowId.split(",")[0];
				if(map.get(bid)!=null){
					map.put(bid, 2);
				}
			}
			List<TBorrow> borrows = new ArrayList<TBorrow>();
			List<TBorrow> deleteBorrows = new ArrayList<TBorrow>();
			List<String> borrowsIds = new ArrayList<String>();
			TBorrow borrow =null;
			for(Map.Entry<String, Integer> set:map.entrySet()){
				borrow = new TBorrow();
				if(set.getValue()==1){//删除标的
					borrow.setBorrowaliasno(null);// 标的中文别名编号
					borrow.setBorrowalias(null);// 标的中文别名
					borrow.setBorrowtitle(null);// 借款标题
					borrow.setBorrowListId(null);
					borrow.setMaxtendersum(null);// 最大投资金额
					
					borrow.setBorrowstatus("6");// 状态
					borrow.setBorrowId(set.getKey());
					borrow.setMintendersum(null);//起投金额
					borrow.setRaiseterm(null);//募集期
					borrow.setProjectBrief(null);//项目介绍
					borrow.setRiskWarning(null);//风险提示
					borrow.setRaisetermunit(null);//筹标期限单位（0小时 1天）
					deleteBorrows.add(borrow);
				}else{//更新标的信息
					borrow.setBorrowstatus("18");//状态
					borrow.setBorrowId(set.getKey());
					borrow.setMintendersum(mintenderSum);//起投金额
					borrow.setRaiseterm(raiseTerm);//募集期
					borrow.setProjectBrief(usageofloan);//项目介绍
					borrow.setRiskWarning(riskWarning);//风险提示
					borrow.setRaisetermunit(raisetermunit);//筹标期限单位（0小时 1天）
					borrows.add(borrow);
					borrowsIds.add(set.getKey());
				}
			}
			borrowListEditService.updateEditBorrowList(deleteBorrows, borrows, borrowsIds, tborrowList);
			/*//更新删除标的
			if(deleteBorrows.size()>0){
				borrowListEditService.updateDeleteBorrow(deleteBorrows);
			}
			borrowListEditService.updateBorrow(borrows);
			List<TBorrow> borrowId = borrowListEditService.getByBorrowId(borrowsIds);
			BigDecimal borrowsTotal = new BigDecimal(0);
			for(TBorrow tb:borrowId){//计算散标集金额
				BigDecimal borrowAmont=new BigDecimal(tb.getBorrowamount());
				borrowsTotal = borrowsTotal.add(borrowAmont);
			}
			tborrowList.setBorrowListAmount(borrowsTotal);
			borrowListEditService.updateBorrowList(tborrowList);*/
		} catch (Exception e) {
			logger.info("saveBorrowList fail :", e);
		}
		return "redirect:"+Global.getAdminPath()+"/borrow/borrowList/?repage";			
	}
	/**
	 * 
	 * TODO	  新建散标集集合
	 * @param tBorrow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryBorrowListForEdit")
	public String newBorrow(TBorrow tBorrow, HttpServletRequest request, HttpServletResponse response, Model model) {
		String deadline = tBorrow.getDeadline();
		if(null != deadline && !"24".equals(deadline) && !"".equals(deadline)){
			String[] split = deadline.split(",");
			tBorrow.setBeginDeadline(Integer.valueOf(split[0]));
			tBorrow.setEndDeadline(Integer.valueOf(split[1]));
		}
		if("24".equals(deadline)){
			tBorrow.setBeginDeadline(24);
			tBorrow.setEndDeadline(99999);
		}
		Page<TBorrow> page = tBorrowListService.findNewPage(new Page<TBorrow>(request, response,-1), tBorrow);
		BigDecimal countMount = tBorrowListService.findcountMount(tBorrow);
		if(countMount==null){
			countMount = new BigDecimal(0.00);
		}
		model.addAttribute("countMount", countMount);
		model.addAttribute("tBorrow", tBorrow);
		model.addAttribute("page", page);
		String ishidden = request.getParameter("ishidden");
		model.addAttribute("ishidden", ishidden);
		return "modules/borrowlist/queryBorrowListForEdit";
	}
	
	/**
	 * 
	 * @Title: newBorrowList   
	 * @Description: 创建散标集页 
	 * @param borrowCodes
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 10:21:15 AM
	 */
	@RequestMapping(value="newBorrowList")
	public ModelAndView newBorrowList(String borrowCodes) {
		List<Borrow> loans = tBorrowListService.findLoans(Arrays.asList(borrowCodes.split(",")));
		BigDecimal loanTotal = tBorrowListService.sumLoanTotal(loans);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("borrows", loans);
		modelAndView.addObject("loanTotal", loanTotal);
		modelAndView.setViewName("modules/borrowlist/newBorrowList");
	    return modelAndView;
	}
	
	/**
	 * 
	 * @Title: reviewBorrowList   
	 * @Description: 复显散标集页
	 * @param borrowCodes
	 * @param riskWarning
	 * @param usageofloan
	 * @param raiseterm
	 * @param minTenderSum
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 10:21:36 AM
	 */
	@RequestMapping(value="reviewBorrowList")
	public ModelAndView reviewBorrowList(String[] borrowCodes, String riskWarning, String usageofloan,
			String raiseterm, String minTenderSum){
		TBorrowList borrowList = tBorrowListService.parseBorrowList(Arrays.asList(borrowCodes), riskWarning, usageofloan, raiseterm, minTenderSum);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("borrowList", borrowList);
		modelAndView.setViewName("modules/borrowlist/reviewBorrowList");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: releaseBorrowList   
	 * @Description: 发布散标集   
	 * @param borrowCodes
	 * @param borrowNos
	 * @param riskWarning
	 * @param usageofloan
	 * @param raiseterm
	 * @param minTenderSum
	 * @param borrowListNo
	 * @return        
	 * @throws   
	 * @author zhf
	 * @date May 9, 2017 5:58:04 PM
	 */
	@RequestMapping(value="releaseBorrowList")
	public String releaseBorrowList(String[] borrowCodes, String[] borrowNos, String riskWarning, 
			String usageofloan, String raiseterm, String minTenderSum, String borrowListNo){
		tBorrowListService.createBorrowList(Arrays.asList(borrowCodes), borrowNos, riskWarning, 
				usageofloan, raiseterm, minTenderSum, borrowListNo);
		return "redirect:" + Global.getAdminPath() +  "/borrow/borrowList/list";
	}

}
