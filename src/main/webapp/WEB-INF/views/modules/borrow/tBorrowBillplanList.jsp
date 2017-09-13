<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>还款计划管理</title>
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
		<li class="active"><a href="${ctx}/borrow/tBorrowBillplan/">还款计划列表</a></li>
		<shiro:hasPermission name="borrow:tBorrowBillplan:edit"><li><a href="${ctx}/borrow/tBorrowBillplan/form">还款计划添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tBorrowBillplan" action="${ctx}/borrow/tBorrowBillplan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>期数</th>
				<th>应还月还款额</th>
				<th>实还月还款额</th>
				<th>月管理费</th>
				<th>应还违约金</th>
				<th>实还违约金</th>
				<th>应还罚息</th>
				<th>实还罚息</th>
				<th>update_date</th>
				<th>remarks</th>
				<shiro:hasPermission name="borrow:tBorrowBillplan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBorrowBillplan">
			<tr>
				<td><a href="${ctx}/borrow/tBorrowBillplan/form?id=${tBorrowBillplan.id}">
					${tBorrowBillplan.period}
				</a></td>
				<td>
					${tBorrowBillplan.monthlyPaymentOrigin}
				</td>
				<td>
					${tBorrowBillplan.monthlyPaymentActual}
				</td>
				<td>
					${tBorrowBillplan.manageFee}
				</td>
				<td>
					${tBorrowBillplan.failsChargeOrigin}
				</td>
				<td>
					${tBorrowBillplan.failsChargeActual}
				</td>
				<td>
					${tBorrowBillplan.lateChargeOrigin}
				</td>
				<td>
					${tBorrowBillplan.lateChargeActual}
				</td>
				<td>
					<fmt:formatDate value="${tBorrowBillplan.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tBorrowBillplan.remarks}
				</td>
				<shiro:hasPermission name="borrow:tBorrowBillplan:edit"><td>
    				<a href="${ctx}/borrow/tBorrowBillplan/form?id=${tBorrowBillplan.id}">修改</a>
					<a href="${ctx}/borrow/tBorrowBillplan/delete?id=${tBorrowBillplan.id}" onclick="return confirmx('确认要删除该还款计划吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>