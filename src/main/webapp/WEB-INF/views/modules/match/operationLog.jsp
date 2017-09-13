<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>操作日志记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
		<li class="active"><a href="${ctx}/match/matched/">操作日志记录</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:if test="${type=='ASSET'}">
					<c:if test="${filed=='productType'}"><th>产品类型</th></c:if>
					<c:if test="${filed=='productAmount'}"><th>借款类别</th><th>借款金额</th></c:if>
					<c:if test="${filed=='productTime'}"><th>等待时间</th></c:if>
					<th>权重</th>
				</c:if>
				<c:if test="${type=='CAPITAL'}">
					<c:if test="${filed=='productType'}"><th>产品类型</th></c:if>
					<c:if test="${filed=='productAmount'}"><th>出借类别</th><th>出借金额</th></c:if>
					<c:if test="${filed=='productTime'}"><th>等待时间</th></c:if>
					<c:if test="${filed=='repaymentTime'}"><th>距离还款日时间</th></c:if>
					<th>权重</th>
				</c:if>
				
				<c:if test="${type=='1'}"><th>出借期限(月)</th><th>借款期限(月)</th></c:if>
				
				<c:if test="${type=='3'}"><th>匹配出借产品类型</th><th>状态</th></c:if>
								
				
				<th>账号</th>
				<th>时间</th>
				

			<tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="operationLog">
			<c:if test="${type==operationLog.type}">
			<tr>
				<c:if test="${type=='ASSET'}">
					<c:if test="${filed=='productType'}"><td>${operationLog.detail}</td></c:if>
					<c:if test="${filed=='productAmount'}">
						<c:if test="${operationLog.borrowType=='1'}"><td>企业借款</td></c:if>
						<c:if test="${operationLog.borrowType=='2'}"><td>个人借款</td></c:if>
						<td>${operationLog.detail}</td>
					</c:if>
					<c:if test="${filed=='productTime'}"><td>${operationLog.detail}</td></c:if>
					<td>${operationLog.weight}</td>
				</c:if>
			
				<c:if test="${type=='CAPITAL'}">
					<c:if test="${filed=='productType'}"><td>${operationLog.detail}</td></c:if>
					<c:if test="${filed=='productAmount'}">
						<c:if test="${operationLog.borrowType=='0'}"><td>个人出借</td></c:if>
						<td>${operationLog.detail}</td>
					</c:if>
					<c:if test="${filed=='productTime'}"><td>${operationLog.detail}</td></c:if>
					<c:if test="${filed=='repaymentTime'}"><td>${operationLog.detail}</td></c:if>
					<td>${operationLog.weight}</td>
				</c:if>

				<c:if test="${type=='1'}"><td>${operationLog.capitalNper}</td><td>${operationLog.assetNper}</td></c:if>
				
				<c:if test="${type=='3'}">
					<td>出借计划--匹配散标集产品</td>
					<c:if test="${operationLog.status=='0'}"><td>否</td></c:if>
					<c:if test="${operationLog.status=='1'}"><td>是</td></c:if>				
				</c:if>
			
			<td>${operationLog.accountNumber}</td>
			<td><fmt:formatDate value="${operationLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		
			</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>