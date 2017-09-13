<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自定义活动列表</title>
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
		<li class="active"><a href="${ctx}/operation/activity/">自定义活动列表</a></li>
		<shiro:hasPermission name="operation:activity:edit"><li><a href="${ctx}/operation/activity/form">新建</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="activity" action="${ctx}/operation/activity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活动名称：</label>
				<form:input path="activityname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>奖励类型：</label>
				<form:select path="rewardtype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('activity_rewardType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('activity_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>活动名称</th>
				<th>奖励类型</th>
				<th>活动说明</th>
				<th>状态</th>
				<th>创建时间</th>
				<shiro:hasPermission name="operation:activity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="activity">
			<tr>
				<td><a href="${ctx}/operation/activity/preview?id=${activity.id}">
					${activity.activityname}
				</a></td>
				<td>
					${fns:getDictLabel(activity.rewardtype, 'activity_rewardType', '')}
				</td>
				<td>
					${activity.activitydescribe}
				</td>
				<td>
					${fns:getDictLabel(activity.status, 'activity_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${activity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="operation:activity:edit"><td>
    			<a href="${ctx}/operation/activity/form?id=${activity.id}">编辑</a>
    			<a href="${ctx}/operation/activity/editForm?id=${activity.id}">上传</a>
					<a href="${ctx}/operation/activity/delete?id=${activity.id}" onclick="return confirmx('确认要删除该保存活动列表成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>