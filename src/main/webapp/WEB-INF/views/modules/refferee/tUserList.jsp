<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户推荐管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/refferee/tUser/">用户推荐列表</a></li>
		<shiro:hasPermission name="refferee:tUser:edit"><li><a href="${ctx}/refferee/tUser/form">用户推荐添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tUser" action="${ctx}/refferee/tUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li><label>推荐人手机号：</label>
				<form:input path="refferee" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>推荐人姓名：</label>
				<form:input path="tRealname" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>被推荐人手机号：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>被推荐人注册时间：</label>
				<input name="beginRegdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tUser.beginRegdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endRegdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tUser.endRegdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>被推荐人姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>推荐人手机号</th>
				<th>推荐人姓名</th>
				<th>被推荐人手机号</th>
				<th>被推荐人姓名</th>
				<th>被推荐人注册时间</th>
				
				<shiro:hasPermission name="refferee:tUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tUser">
			<tr>
				<td>
					${tUser.refferee}
				</td>
				<td>
					${tUser.tRealname}
				</td>
				<td>
					${tUser.mobile}
				</td>
				<td><a href="${ctx}/refferee/tUser/form?id=${tUser.id}">
					${tUser.realname}
				</a></td>
				
				<td>
					<fmt:formatDate value="${tUser.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				<shiro:hasPermission name="refferee:tUser:edit"><td>
    				<a href="${ctx}/refferee/tUser/form?id=${tUser.id}">修改</a>
					<a href="${ctx}/refferee/tUser/delete?id=${tUser.id}" onclick="return confirmx('确认要删除该用户推荐吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>