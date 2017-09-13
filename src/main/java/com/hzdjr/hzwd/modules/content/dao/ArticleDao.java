/**
 * Copyright &copy; 2016
 */
package com.hzdjr.hzwd.modules.content.dao;

import com.hzdjr.hzwd.common.persistence.CrudDao;
import com.hzdjr.hzwd.common.persistence.annotation.MyBatisDao;
import com.hzdjr.hzwd.modules.content.entity.Article;

/**
 * 文章管理DAO接口
 * @author xuchenglin
 * @version 2016-10-19
 */
@MyBatisDao
public interface ArticleDao extends CrudDao<Article> {
	
	//查询预览（根据ID查询文章）
	Article getArticlePerview(Article article);

	//修改文章 发布和置顶的状态
	void updateStatus(Article article);
	
}