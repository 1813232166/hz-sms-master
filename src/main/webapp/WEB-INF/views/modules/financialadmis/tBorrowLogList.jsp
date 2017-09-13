<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标的记录管理</title>
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
		<li class="active"><a href="${ctx}/financialadmis/tBorrowLog/">标的账户</a></li>
		<%-- <shiro:hasPermission name="financialadmis:tBorrowLog:edit"><li><a href="${ctx}/financialadmis/tBorrowLog/form">标的账户添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="tBorrowLog" action="${ctx}/financialadmis/tBorrowLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标的编号：</label> 
				<input name="borrowcode" value="${tBorrowLog.borrowcode}" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<%-- <input name="borrowstatus" value="${tBorrowLog.borrowstatus}" class="input-medium"/> --%>
				<select name="borrowstatus"  class="input-medium">
					<option  value="">全部</option>
					<option  value="01">放款</option>
					<option  value="02">还款</option>
					<option  value="03">投标</option>
				</select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
				<input name="overTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>标的编号</th>
				<th>创建时间</th>
				<th>状态</th>
				<th>标的转入（元）</th>
				<th>标的转出（元）</th>
				<th>结余（元）</th>
				<%-- <shiro:hasPermission name="financialadmis:tBorrowLog:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBorrowLog" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>
					${tBorrowLog.borrowcode}
				</td>
				<td>
					<fmt:formatDate value="${tBorrowLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${tBorrowLog.borrowstatus=='01'}">放款</c:if>
					<c:if test="${tBorrowLog.borrowstatus=='02'}">还款</c:if>
					<c:if test="${tBorrowLog.borrowstatus=='03'}">投标</c:if>
				</td>
				<td>
					${tBorrowLog.paidinamount}
				</td>
				<td>
					${tBorrowLog.giveamount}
				</td>
				<td>
					${tBorrowLog.balance}
				</td>
				<%-- <shiro:hasPermission name="financialadmis:tBorrowLog:edit"><td>
    				<a href="${ctx}/financialadmis/tBorrowLog/form?id=${tBorrowLog.id}">修改</a>
					<a href="${ctx}/financialadmis/tBorrowLog/delete?id=${tBorrowLog.id}" onclick="return confirmx('确认要删除该标的记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>