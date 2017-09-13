<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head lang="en">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="renderer" content="webkit" />
<meta name="decorator" content="default" />
<title>汇中大金融 - 网络借贷信息中介平台</title>
<meta name="keywords" content="汇中大金融,互联网金融公司排名,互联网金融产品">
<meta name="description" content="汇中大金融,互联网金融公司排名,互联网金融产品，这里提供全面互联网金融相关专业知识，为用户解答投资理财方面相关问题，排除不安全因素，理财投资更放心，选择汇中大金融。">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/content/acticlePreview.css" />
<script type="text/javascript">
  $(function() {
    var html = $("#articlecnt").text();
    var reg = /<[^>]+>/g;
    if (reg.test(html)) { 
      //判断内容里是否有页面标签
      $("#articlecnt").empty().html(html);
    }
  });
</script>
</head>
<body>
  <div class="hzAll">
    <!--头部信息-->
    <div class="header">
      <div class="hzLogo container clearfix">
        <div class="right rR">
          <ul>
            <li><a href="javascript:void(0);" style='margin-bottom: 20px;'>首页</a></li>
            <li><a href="javascript:void(0);" class="on">我要出借<i></i></a>
              <div class="triangle_border_up">
                <span class="special"></span>
              </div>
              <ul class="ulItem" style="display: none">
              </ul></li>
            <li><a href="javascript:void(0);" class="bot-line">我要借款</a></li>
            <li><a href="javascript:void(0);" class="bot-line">信息披露</a></li>
            <li><a href="javascript:void(0);" class="bot-line">风控措施</a></li>
            <li><a href="javascript:void(0);" class="bot-line">关于我们</a></li>
            <li style="margin-right: 0px;"><a href="javascript:void(0);" class="bot-line">我的账户</a></li>
          </ul>
        </div>
      </div>
    </div>
    <!--中间内容-->
    <div class="center clearfix">
      <div class="hzContainer mTop">
        <div class="hzSidebarLeft lL">
          <div class="contentLeft">
            <div class="left">
              <ul class="leftFirst">
                <h3 class="first">关于我们</h3>
                <li><a href="javascript:void(0);">公司简介</a></li>
                <li><a href="javascript:void(0);">媒体报道</a></li>
                <li><a href="javascript:void(0);">企业荣誉</a></li>
                <li><a href="javascript:void(0);">联系我们</a></li>
                <li><a href="javascript:void(0);">公司新闻</a></li>
                <li><a href="javascript:void(0);">平台公告</a></li>
              </ul>

            </div>
          </div>
        </div>
        <div class="hzSidebarRight">
          <div class="contentRight">
            <div class="right">
              <h3 style="text-align: center; font-weight: 600;">${article.articletitle}</h3>
              <p style="text-align: center; font-weight: 600;">${article.articleSubtitle}</p>
              <p style="text-align: center;">
                发布时间 |
                <fmt:formatDate value="${article.releasetime }" pattern="yyyy-MM-dd" />
                &nbsp;&nbsp;&nbsp;&nbsp; 文章来源 | ${article.source}
              </p>
              <div class="allBox">
                <!-- 正文内容  -->
                <c:if test="${not empty article.articleDigest}">
                  <div class="p">摘要:${article.articleDigest}</div>
                </c:if>
                <div class="p" id="articlecnt">${article.articlecnt}</div>
                <h5 style="margin-top: 180px;">
                  <a href="javascript:void(0);"></a>
                </h5>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--center-->
    <!-- footer开始-->
    <div class="footnew"></div>
    <!-- footer结束-->
  </div>
  <!--hzAll-->
</body>
</html>