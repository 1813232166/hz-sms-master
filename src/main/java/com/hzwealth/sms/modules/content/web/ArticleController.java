/**
 * Copyright &copy; 2016
 */
package com.hzwealth.sms.modules.content.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hzwealth.sms.common.config.Global;
import com.hzwealth.sms.common.persistence.Page;
import com.hzwealth.sms.common.utils.PropertiesLoader;
import com.hzwealth.sms.common.utils.StringUtils;
import com.hzwealth.sms.common.web.BaseController;
import com.hzwealth.sms.modules.content.entity.Article;
import com.hzwealth.sms.modules.content.service.ArticleService;

/**
 * 文章管理Controller
 * @author xuchenglin
 * @version 2016-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/content/article")
public class ArticleController extends BaseController {

	//Logger logger = LogManager.getLogger(ArticleController.class);
	
	@Autowired
	private ArticleService articleService;
	
	private static final PropertiesLoader property = new PropertiesLoader("sms.properties");
	
	/**
	 * 图片访问的基本路径
	 */
	/*public String baseurl_img="http://localhost:8080/data/";*/
	
	@ModelAttribute
	public Article get(@RequestParam(required=false) String id) {
		Article entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = articleService.get(id);
		}
		if (entity == null){
			entity = new Article();
		}
		return entity;
	}
	
	@RequiresPermissions("content:article:view")
	@RequestMapping(value = {"list", ""})
	public String list(Article article, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Article> page = articleService.findPage(new Page<Article>(request, response), article); 
		model.addAttribute("page", page);
		return "modules/content/articleList";
	}

	@RequiresPermissions("content:article:view")
	@RequestMapping(value = "form")
	public String form(Article article, Model model) {
		model.addAttribute("article", article);
		//图片访问路径的前缀，存放在作用域中，便于jsp页面的获取
		String baseurl_img = property.getProperty("ip_image");
		
		model.addAttribute("baseurl_img", baseurl_img);
		return "modules/content/articleForm";
	}
	
	//@RequiresPermissions("content:article:view")
	@RequestMapping(value = "preview")
	public String preview(Article article, Model model) {
		Article perview = articleService.getArticlePerview(article);
		
		model.addAttribute("perview", perview);
		model.addAttribute("article", article);
		return "modules/content/articlePreview";
	}
	/**
	 * 文章发布状态和置顶状态修改功能  
	 * @param article
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("content:article:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(Article article, RedirectAttributes redirectAttributes) {
		articleService.updateStatus(article);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/content/article/?repage";
	}

	@RequiresPermissions("content:article:edit")
	@RequestMapping(value = "save")
	public String save(@RequestParam MultipartFile[] picturePath, HttpServletRequest request,Article article, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, article)){
			return form(article, model);
		}
		articleService.save(article);
		addMessage(redirectAttributes, "保存文章管理成功");
		return "redirect:"+Global.getAdminPath()+"/content/article/?repage";
	}
	
	@RequiresPermissions("content:article:edit")
	@RequestMapping(value = "delete")
	public String delete(Article article, RedirectAttributes redirectAttributes) {
		articleService.delete(article);
		addMessage(redirectAttributes, "删除文章管理成功");
		return "redirect:"+Global.getAdminPath()+"/content/article/?repage";
	}

}