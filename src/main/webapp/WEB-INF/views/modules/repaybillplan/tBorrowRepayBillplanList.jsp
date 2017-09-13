<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>回款列表管理</title>
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
		<li class="active"><a href="${ctx}/repaybillplan/tBorrowRepayBillplan/">回款列表</a></li>
		<shiro:hasPermission name="repaybillplan:tBorrowRepayBillplan:edit"><li><a href="${ctx}/repaybillplan/tBorrowRepayBillplan/form">查询回款列表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tBorrowRepayBillplan" action="${ctx}/repaybillplan/tBorrowRepayBillplan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>投资编号</th>
				<th>投资期次序号</th>
				<th>利息年份</th>
				<th>利息月份</th>
				<th>应收款日期</th>
				<th>应收本金</th>
				<th>应收利息</th>
				<th>贷款余额</th>
				<th>实收款日期</th>
				<th>实收本金</th>
				<th>实收利息</th>
				<th>是否到期未收到</th>
				<th>该期次是否已收清</th>
				<shiro:hasPermission name="repaybillplan:tBorrowRepayBillplan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBorrowRepayBillplan">
			<tr>
				<td>
					${tBorrowRepayBillplan.investid}
				</td>
				<td>
					${tBorrowRepayBillplan.billnum}
				</td>
				<td>
					${tBorrowRepayBillplan.capiyear}
				</td>
				<td>
					${tBorrowRepayBillplan.capimonth}
				</td>
				<td>
					<fmt:formatDate value="${tBorrowRepayBillplan.sdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${tBorrowRepayBillplan.stcapi}
				</td>
				<td>
					${tBorrowRepayBillplan.sinte}
				</td>
				<td>
					${tBorrowRepayBillplan.bal}
				</td>
				<td>
					<fmt:formatDate value="${tBorrowRepayBillplan.rdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${tBorrowRepayBillplan.rtcapi}
				</td>
				<td>
					${tBorrowRepayBillplan.rinte}
				</td>
				<td>
					${tBorrowRepayBillplan.overflag}
				</td>
				<td>
				<c:choose>
					<c:when test="${tBorrowRepayBillplan.payoffflag eq 1}">
						已收清
					</c:when>
					<c:otherwise>
						未收清
					</c:otherwise>
				</c:choose>
				</td>
				<shiro:hasPermission name="repaybillplan:tBorrowRepayBillplan:edit"><td>
    				<a href="${ctx}/repaybillplan/tBorrowRepayBillplan/form?id=${tBorrowRepayBillplan.id}">修改</a>
					<a href="${ctx}/repaybillplan/tBorrowRepayBillplan/delete?id=${tBorrowRepayBillplan.id}" onclick="return confirmx('确认要删除该查询回款列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>