<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function xinjian(){
			location.href= ctx+"/content/article/form";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/content/article/">文章列表</a></li>
		<%-- <shiro:hasPermission name="content:article:edit"><li><a href="${ctx}/content/article/form">文章管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="article" action="${ctx}/content/article/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属栏目：</label>
				<form:select path="articlecolumn" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('article_column')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否发布：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li style="float: right;margin-right: 50px;" class="btns"><input class="btn btn-primary" type="button" value="新建" onclick="xinjian();" /></li>
			<%-- <li><label>更新时间：</label>
				<input  name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${article.beginUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${article.endUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li><label>文章名称：</label>
				<form:input path="articletitle" htmlEscape="false" maxlength="128" class="input-medium" placeholder="请输入文章名称"/>
			</li>
			<li style="float: right;margin-right: 50px;" class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">文章标题</th>
				<th style="text-align: center;">所属栏目</th>
				<th style="text-align: center;">是否发布</th>
				<th style="text-align: center;">创建人</th>
				<th style="text-align: center;">发布时间</th>
				<th style="text-align: center;">更新时间</th>
				<shiro:hasPermission name="content:article:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="article">
			<tr>
				<td style="text-align: center;"><a href="${ctx}/content/article/form?id=${article.id}">
					${fns:abbr(article.articletitle,30)}
				</a></td>
				<td style="text-align: center;">
					${fns:getDictLabel(article.articlecolumn, 'article_column', '')}
				</td>
				<td style="text-align: center;">
				  	<c:if test="${article.status == 1}">是</c:if>
					<c:if test="${article.status == 0}">否</c:if>
				</td>
				<td style="text-align: center;">
					${article.creator}
				</td>
				<td style="text-align: center;">
					<fmt:formatDate value="${article.releasetime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align: center;">
					<fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="content:article:edit"><td width="180px;">
					<a href="${ctx}/content/article/preview?id=${article.id}" target="_blanke">预览</a>
					<a href="${ctx}/content/article/form?id=${article.id}">修改</a>
					<a href="${ctx}/content/article/delete?id=${article.id}" onclick="return confirmx('确认要删除该文章吗？', this.href)">删除</a>
					<c:if test="${article.status == 0}">
						<a href="${ctx}/content/article/updateStatus?id=${article.id}&status=1">发布</a>
					</c:if>
					<c:if test="${article.status == 1}">
					  	<a href="${ctx}/content/article/updateStatus?id=${article.id}&status=0">撤销</a>
					  	<c:if test="${article.topstatus == 0}">
							<a href="${ctx}/content/article/updateStatus?id=${article.id}&topstatus=1" onclick="return confirmx('确认要置顶该文章吗？', this.href)">置顶</a>
					  	</c:if>
					  	<c:if test="${article.topstatus == 1}">
							<a href="${ctx}/content/article/updateStatus?id=${article.id}&topstatus=0" onclick="return confirmx('确认要取消置顶该文章吗？', this.href)">取消置顶</a>
					  	</c:if>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>