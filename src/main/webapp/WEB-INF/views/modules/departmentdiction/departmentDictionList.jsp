<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>营业部信息增删改查管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script src="${ctxStatic}/departmentDiction/departmentDiction.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/departmentdiction/departmentDiction/">营业部信息列表</a></li>
		<%-- <shiro:hasPermission name="departmentdiction:departmentDiction:edit"> --%><li><a href="${ctx}/departmentdiction/departmentDiction/form">添加</a></li><%-- </shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="departmentDiction" action="${ctx}/departmentdiction/departmentDiction/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>省份</label>
				<select id="province" name="province" onchange="changeCity()" class="input-medium">
				<option id="" value="">请选择</option>
				<c:forEach items="${provinceList}" var="list">
				    <option id="${list.id}" value="${list.name}" ${list.name==province?'selected':'' }>${list.name}</option>
				</c:forEach>
				</select>
				<label>城市</label>
				<select id="city" name="city" class="input-medium">
				   <option id="" value="">请选择</option>
				   <c:forEach items="${cityList}" var="cList">
				   <option id="${cList.id}" value="${cList.name}" ${cList.name==city?'selected':'' }>${cList.name}</option>
				   </c:forEach>
				</select>
				<label>营业部名称</label>
				<form:input path="value" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr align="center">
				<th>序号</th>
				<th>省份</th>
				<th>城市</th>
				<th>营业部名称</th>
				<%-- <shiro:hasPermission name="departmentdiction:departmentDiction:edit"> --%><th>操作</th><%-- </shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="departmentDiction" varStatus="num">
			<tr>
				<td>${num.count}</td>
				<td><%-- <a href="${ctx}/departmentdiction/departmentDiction/form?id=${departmentDiction.id}"> --%>
					${departmentDiction.province}
				<!-- </a> --></td>
				<td>
					${departmentDiction.city}
				</td>
				<td>
					${departmentDiction.value}
				</td>
				<%-- <shiro:hasPermission name="departmentdiction:departmentDiction:edit"> --%><td>
    				<a href="${ctx}/departmentdiction/departmentDiction/form?id=${departmentDiction.id}">修改</a>
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/departmentdiction/departmentDiction/delete?id=${departmentDiction.id}" onclick="return confirmx('确认要删除该营业部信息吗？', this.href)">删除</a>
				</td><%-- </shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>