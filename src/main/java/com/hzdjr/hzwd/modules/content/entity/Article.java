
package com.hzdjr.hzwd.modules.content.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.hzdjr.hzwd.common.persistence.DataEntity;

/**
 * 文章管理Entity
 * @author xuchenglin
 * @version 2016-10-19
 */
public class Article extends DataEntity<Article> {
	
	private static final long serialVersionUID = 1L;
	private String articletitle;		// 文章标题
	private String articleSubtitle;   // 文章副标题
	private String artclepic;		// 封面图片
	private String author;		// 作者
	private String creator;		//创建人
	private String source;		// 来源
	private String articlecolumn;		// 栏目
	private String keyword;		// 关键字
	private String articleDigest; // 文章摘要
	private String topstatus;		// 是否置顶
	private String articlecnt;		// 文章内容
	private String status;		// 状态
	private Date releasetime;		// 发布时间
	private Date beginUpdateDate;		// 开始 更新时间
	private Date endUpdateDate;		// 结束 更新时间
	
	public Article() {
		super();
	}

	public Article(String id){
		super(id);
	}
	
	public String getArticleSubtitle() {
    return articleSubtitle;
  }

  public void setArticleSubtitle(String articleSubtitle) {
    this.articleSubtitle = articleSubtitle;
  }

  public String getArticleDigest() {
    return articleDigest;
  }

  public void setArticleDigest(String articleDigest) {
    this.articleDigest = articleDigest;
  }

  public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Length(min=1, max=128, message="文章标题长度必须介于 1 和 128 之间")
	public String getArticletitle() {
		return articletitle;
	}

	public void setArticletitle(String articletitle) {
		this.articletitle = articletitle;
	}
	
	@Length(min=0, max=255, message="封面图片长度必须介于 0 和 255 之间")
	public String getArtclepic() {
		return artclepic;
	}

	public void setArtclepic(String artclepic) {
		this.artclepic = artclepic;
	}
	
	@Length(min=0, max=64, message="作者长度必须介于 0 和 64 之间")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Length(min=0, max=64, message="来源长度必须介于 0 和 64 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=1, max=32, message="栏目长度必须介于 1 和 32 之间")
	public String getArticlecolumn() {
		return articlecolumn;
	}

	public void setArticlecolumn(String articlecolumn) {
		this.articlecolumn = articlecolumn;
	}
	
	@Length(min=0, max=255, message="关键字长度必须介于 0 和 255 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=0, max=1, message="是否置顶长度必须介于 0 和 1 之间")
	public String getTopstatus() {
		return topstatus;
	}

	public void setTopstatus(String topstatus) {
		this.topstatus = topstatus;
	}
	
	public String getArticlecnt() {
		return articlecnt;
	}

	public void setArticlecnt(String articlecnt) {
		this.articlecnt = articlecnt;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(Date releasetime) {
		this.releasetime = releasetime;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
		
}