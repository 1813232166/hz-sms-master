<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信模板管理</title>
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
		<li class="active"><a href="${ctx}/buss/messageModules/">短信模板列表</a></li>
		<shiro:hasPermission name="buss:messageModules:edit"><li><a href="${ctx}/buss/messageModules/form">短信模板添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="messageModules" action="${ctx}/buss/messageModules/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>接收人：</label>
				<form:select path="receiver" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('receiver')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>类型：</label>
				<form:select path="messageType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('message_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:radiobuttons path="messageStatus" items="${fns:getDictList('message_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>接收人</th>
				<th>类型</th>
				<th>短信内容</th>
				<th>更新时间</th>
				<shiro:hasPermission name="buss:messageModules:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="messageModules">
			<tr>
				<td><a href="${ctx}/buss/messageModules/form?id=${messageModules.id}">
					${fns:getDictLabel(messageModules.receiver, 'receiver', '')}
				</a></td>
				<td>
					${fns:getDictLabel(messageModules.messageType, 'message_type', '')}
				</td>
				<td>
					${messageModules.messageContent}
				</td>
				<td>
					${fns:getDictLabel(messageModules.messageStatus, 'message_status', '')}
				</td>
				<shiro:hasPermission name="buss:messageModules:edit"><td>
    				<a href="${ctx}/buss/messageModules/form?id=${messageModules.id}">修改</a>
					<a href="${ctx}/buss/messageModules/delete?id=${messageModules.id}" onclick="return confirmx('确认要删除该短信模板吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>