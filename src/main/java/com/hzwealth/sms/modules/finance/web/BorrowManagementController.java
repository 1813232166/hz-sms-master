package com.hzwealth.sms.modules.finance.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.finance.entity.BorrowManagementVo;
import com.hzwealth.sms.modules.finance.service.BorrowManagementService;

/**
 * 散标管理Controller
 * 
 * @author hdg
 * @version 2016-10-13
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/borrowmanagement")
public class BorrowManagementController extends BaseController {

	@Autowired
	private BorrowManagementService borrowManagementService;
	/**
	 * 
	 * Description: 散标管理列表
	 *
	 * @param
	 * @return String
	 * @throws @Author
	 *             huangdegui Create Date: 2017年7月31日 下午1:46:05
	 */
	@RequestMapping(value = { "list", "" })
	public String list(BorrowManagementVo borrowManagementVo, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		model.addAttribute("borrowManagementVo", borrowManagementVo);
		Page<BorrowManagementVo> page = borrowManagementService.findPage(new Page<BorrowManagementVo>(request, response), borrowManagementVo); 
		model.addAttribute("page", page);
		return "modules/finance/borrowmanagement";
	}
}
