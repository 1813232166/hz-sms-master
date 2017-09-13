package com.hzdjr.hzwd.modules.buss.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzdjr.hzwd.common.web.BaseController;
import com.hzdjr.hzwd.modules.buss.entity.Buss;
import com.hzdjr.hzwd.modules.buss.service.BussService;

/**
 * 风险评测开关Controller
 * @author yansy
 * @version 2016年11月21日11:14:41
 */
@Controller
@RequestMapping(value = "${adminPath}/buss/buss")
public class BussController extends BaseController {

	@Autowired
	private BussService bussService;
	
	@RequestMapping(value="toBussSetting")
	public String toBussSetting(Buss buss,Model model) {
		String status1 = bussService.findByOne();
		String status2 = bussService.findByTwo();
		model.addAttribute("status1", status1);
		model.addAttribute("status2", status2);
		model.addAttribute("buss", buss);
		return "modules/bussiness/bussinessSetting";
	}
	
	@RequestMapping(value="save")
	@ResponseBody
	public String save(String status1,String status2) {
		String flag = bussService.updateBuss(status1,status2);
		return flag;
	}
}
