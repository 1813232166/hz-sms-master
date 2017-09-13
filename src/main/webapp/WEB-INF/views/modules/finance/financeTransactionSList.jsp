<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全部交易单管理</title>
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
		<li class="active"><a href="${ctx}/financetransactions/financeTransaction/">全部交易单列表</a></li>
		<shiro:hasPermission name="financetransactions:financeTransaction:edit"><li><a href="${ctx}/financetransactions/financeTransaction/form">全部交易单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="financeTransactionS" action="${ctx}/financetransactions/financeTransaction/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品类型:</label>
				<form:select path="productType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${productType}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>交易单号:</label>
				<form:input path="transactionNumber" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>交易单状态:</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${capitalStatuses}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>			
			</li>
			<li><label>出借时间:</label>
				<input name="investTimeBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeTransactions.investTimeBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				至
				<input name="investTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeTransactions.investTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>手机号:</label>
				<form:input path="mobile" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>真实姓名:</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>手机号</th>
				<th>真实姓名</th>
				<th>标的交易单号</th>
				<th>出借时间</th>
				<th>出借金额（元）</th>
				<th>产品类型</th>
				<th>状态</th>
				<th>借款编号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="financeTransactions" varStatus="index">
			<tr>
				<td>
					${index.index +1}
				</td>
				<td>
					${financeTransactions.mobile}
				</td>
								<td>
					${financeTransactions.name}
				</td>
								<td>
					${financeTransactions.transactionNumber}
				</td>
				<td>
					<fmt:formatDate value="${financeTransactions.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
								<td>
					${financeTransactions.investAmount}
				</td>
				<td>
					<c:forEach items="${productType }" var="productType">
						<c:if test="${financeTransactions.productType == productType.value }">
							${productType.label }
						</c:if>
					</c:forEach>
				</td>
				<td>
					<c:forEach items="${capitalStatuses }" var="capitalStatus">
						<c:if test="${financeTransactions.status == capitalStatus.value }">
							${capitalStatus.label }
						</c:if>
					</c:forEach>
				</td>
				<td>
					${financeTransactions.borrowCode}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>