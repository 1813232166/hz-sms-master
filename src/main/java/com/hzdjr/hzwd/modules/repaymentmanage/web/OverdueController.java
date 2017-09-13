package com.hzdjr.hzwd.modules.repaymentmanage.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzdjr.hzwd.common.config.Global;
import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.utils.ParamterUtils;
import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.repaymentmanage.entity.OverdueDTO;
import com.hzdjr.hzwd.modules.repaymentmanage.entity.OverdueVO;
import com.hzdjr.hzwd.modules.repaymentmanage.service.OverdueService;

/**
 * 普享标逾期
 * @author xuchenglin
 *
 */
@Controller
@RequestMapping("${adminPath}/repayment/OverdueManage")
public class OverdueController extends BaseController{

  @Autowired
  private OverdueService overdueService;

  @SuppressWarnings("all")
  @RequestMapping("/toPuList")
  public String toPuList(Model model, HttpServletRequest request, HttpServletResponse response) {
    Map readOnlyMap = ParamterUtils.getParamMap(request);
    //统计返回列表
    List<OverdueDTO> overdueDTOList = overdueService.listOverdueDTO(readOnlyMap);
    List<OverdueVO> overdueVOList = new ArrayList<>();
    //统计逾期总金额
    BigDecimal overdueTotalAmount = new BigDecimal(0.00); // 初始还款总金额为0.00
    for (OverdueDTO overdue : overdueDTOList) {
      OverdueVO overdueVO = new OverdueVO();
      //总额 = 本金+利息+应还罚息+应还违约金
      BeanUtils.copyProperties(overdue, overdueVO);
      overdueVO.setTotalAmount(overdue.getMonthCapital().add(overdue.getMonthInterest()
          .add(overdue.getFailsChargeOrigin().add(overdue.getLateChargeOrigin()))));
      overdueVOList.add(overdueVO);
      //计算逾期总计
      overdueTotalAmount = overdueTotalAmount.add(overdueVO.getTotalAmount());
    }
    /**
     * 分页开始
     */
    String pageNos = request.getParameter("pageNo");
    int pageSize = 10;
    Integer pageNo = 1;
    if (pageNos != null) {
      pageNo = new Integer(pageNos);
    }
    // 传参
    Page<OverdueVO> page = new Page<>(pageNo, pageSize, overdueVOList.size(), overdueVOList);
    page.initialize();
    ArrayList<OverdueVO> list = new ArrayList<>();// 返回的list集合
    if (pageNo > page.getTotalPage()) {
      pageNo = 1;
    }
    int start = (pageNo - 1) * page.getPageSize();
    int end = pageNo * page.getPageSize();
    if (end > overdueVOList.size()) {
      end = overdueVOList.size();
    }
    for (int i = start; i < end; i++) {
      list.add(overdueVOList.get(i));
    }
    /**
     * 分页结束
     */
    model.addAttribute("overdueTotalAmount", overdueTotalAmount);
    model.addAttribute("overdueVOList", list);
    model.addAttribute("page", page);
    model.addAttribute("view", readOnlyMap);
    return "modules/repaymentmanage/overdueList";
  }
  
  @SuppressWarnings("all")
  @RequestMapping("/toHighRiskOverdueList")
  public String toHighRiskOverdueList(Model model, HttpServletRequest request, HttpServletResponse response) {
    Map readOnlyMap = ParamterUtils.getParamMap(request);
    //统计返回列表
    List<OverdueDTO> overdueDTOList = overdueService.listHighRistOverdueDTO(readOnlyMap);
    List<OverdueVO> overdueVOList = new ArrayList<>();
    //统计逾期总金额
    BigDecimal overdueTotalAmount = new BigDecimal(0.00); // 初始还款总金额为0.00
    for (OverdueDTO overdue : overdueDTOList) {
      OverdueVO overdueVO = new OverdueVO();
      //总额 = 本金+利息+应还罚息+应还违约金
      BeanUtils.copyProperties(overdue, overdueVO);
      overdueVO.setTotalAmount(overdue.getMonthCapital().add(overdue.getMonthInterest()
          .add(overdue.getFailsChargeOrigin().add(overdue.getLateChargeOrigin()))));
      overdueVOList.add(overdueVO);
      //计算逾期总计
      overdueTotalAmount = overdueTotalAmount.add(overdueVO.getTotalAmount());
    }
    /**
     * 分页开始
     */
    String pageNos = request.getParameter("pageNo");
    int pageSize = 10;
    Integer pageNo = 1;
    if (pageNos != null) {
      pageNo = new Integer(pageNos);
    }
    // 传参
    Page<OverdueVO> page = new Page<>(pageNo, pageSize, overdueVOList.size(), overdueVOList);
    page.initialize();
    ArrayList<OverdueVO> list = new ArrayList<>();// 返回的list集合
    if (pageNo > page.getTotalPage()) {
      pageNo = 1;
    }
    int start = (pageNo - 1) * page.getPageSize();
    int end = pageNo * page.getPageSize();
    if (end > overdueVOList.size()) {
      end = overdueVOList.size();
    }
    for (int i = start; i < end; i++) {
      list.add(overdueVOList.get(i));
    }
    /**
     * 分页结束
     */
    model.addAttribute("overdueTotalAmount", overdueTotalAmount);
    model.addAttribute("overdueVOList", list);
    model.addAttribute("page", page);
    model.addAttribute("view", readOnlyMap);
    return "modules/repaymentmanage/highRiskOverdueList";
  }
  
  @RequestMapping("/toRepayment")
  public String toRepayment(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
    //垫付list
    String repaymentIdList = request.getParameter("repaymentIdList");
    if (repaymentIdList != null && repaymentIdList != "") {
      //更新垫付状态
      if(overdueService.updateAdvanceStatus(repaymentIdList)){
        addMessage(redirectAttributes, "垫付操作成功");
      }else{
        addMessage(redirectAttributes, "您选择的用户尚有未处理完成的数据，待系统处理完再试 谢谢");
      }
    }
    return "redirect:"+Global.getAdminPath()+"/repayment/OverdueManage/toPuList";
  }
  
  @RequestMapping("/toHighRiskRepayment")
  public String toHighRiskRepayment(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
    //垫付list
    String repaymentIdList = request.getParameter("repaymentIdList");
    if (repaymentIdList != null && repaymentIdList != "") {
      //更新垫付状态
      if(overdueService.updateAdvanceStatus(repaymentIdList)){
        addMessage(redirectAttributes, "垫付操作成功");
      }else{
        addMessage(redirectAttributes, "您选择的用户尚有未处理完成的数据，待系统处理完再试 谢谢");
      }
    }
    return "redirect:"+Global.getAdminPath()+"/repayment/OverdueManage/toHighRiskOverdueList";
  }
}
