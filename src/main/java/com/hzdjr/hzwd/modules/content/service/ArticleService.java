/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.content.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hzdjr.hzwd.common.persistence.Page;
import com.hzdjr.hzwd.common.service.CrudService;
import com.hzdjr.hzwd.modules.content.entity.Article;
import com.hzdjr.hzwd.modules.content.dao.ArticleDao;

/**
 * 文章管理Service
 * 
 * @author xuchenglin
 * @version 2016-10-19
 */
@Service
@Transactional(readOnly = true)
public class ArticleService extends CrudService<ArticleDao, Article> {

	@Autowired
	ArticleDao articleDao;

	public Article get(String id) {
		return super.get(id);
	}

	public List<Article> findList(Article article) {
		return super.findList(article);
	}

	public Page<Article> findPage(Page<Article> page, Article article) {
		return super.findPage(page, article);
	}

	@Transactional(readOnly = false)
	public void save(Article article) {
		super.save(article);
	}

	@Transactional(readOnly = false)
	public void delete(Article article) {
		super.delete(article);
	}

	// 查询预览（根据ID查询文章）
	public Article getArticlePerview(Article article) {

		return articleDao.getArticlePerview(article);

	}
	@Transactional(readOnly = false)
	public void updateStatus(Article article) {

		articleDao.updateStatus(article);
		
	}

}