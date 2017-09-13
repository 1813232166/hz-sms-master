<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划交易单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/* 导出列表 */
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/finance/financeTransaction/listExport")
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/finance/financeTransaction/");
			});
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
		<li class="active"><a href="${ctx}/finance/financeTransaction/">出借计划交易单</a></li>
<%-- 		<shiro:hasPermission name="financetransaction:financeTransaction:edit">
			<li><a href="${ctx}/finance/financeTransaction/form">出借计划交易添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="financeTransaction" action="${ctx}/finance/financeTransaction/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>计划名称：</label>
				<form:select path="financeProductId" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${financeProducts}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>计划编号：</label>
				<form:input path="financeCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>交易单状态：</label>
				<form:select path="capitalStatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${capitalStatuses}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>出借时间：</label>
				<input name="capitalInvestTimeBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeTransaction.capitalInvestTimeBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				至
				<input name="capitalInvestTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeTransaction.capitalInvestTimeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>交易单号：</label>
				<form:input path="capitalCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>计划名称</th>
				<th>计划编号</th>
				<th>手机号</th>
				<th>真实姓名</th>
				<th>交易单号</th>
				<th>出借时间</th>
				<th>出借金额（元）</th>
				<th>优惠券类型</th>
				<th>优惠券面额</th>
				<th>出借方式</th>
				<th>出借端口</th>
				<th>是否提前退出</th>
				<th>状态</th>
<%-- 				<shiro:hasPermission name="financetransaction:financeTransaction:edit"> --%>
					<th>操作</th>
<%-- 				</shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="financeTransaction" varStatus="status">
			<tr>
				<td>
					${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td><a href="${ctx}/finance/financeTransaction/orderDetail?capitalId=${financeTransaction.capitalId}">
					${financeTransaction.financeName}
				</a></td>
				<td>
					${financeTransaction.financeCode}
				</td>
				<td>
					${financeTransaction.mobile}
				</td>
				<td>
					${financeTransaction.userName}
				</td>
				<td>
					${financeTransaction.capitalCode}
				</td>
				<td>
					<fmt:formatDate value="${financeTransaction.capitalInvestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatNumber value="${financeTransaction.capitailAmount}" pattern="#,#00.0#"/>
				</td>
				<td></td>
				<td></td>
				<td>
					${fns:getDictLabel(financeTransaction.lendingMethod, 'LENDING_METHOD', '')}
				</td>
				<td>
					${fns:getDictLabel(financeTransaction.capitalSource, 'CAPITAL_SOURCE', '')}
				</td>
				<td>
					${financeTransaction.earlyExit}
				</td>
				<td>
					<c:forEach items="${capitalStatuses }" var="capitalStatus">
						<c:if test="${financeTransaction.capitalStatus == capitalStatus.value }">
							${capitalStatus.label }
						</c:if>
					</c:forEach>
				</td>
<%-- 				<shiro:hasPermission name="financetransaction:financeTransaction:edit"> --%>
					<td>
	    				<%-- <a href="${ctx}/finance/financeTransaction/form?id=${financeTransaction.id}">修改</a>
						<a href="${ctx}/finance/financeTransaction/delete?id=${financeTransaction.id}" onclick="return confirmx('确认要删除该出借计划交易吗？', this.href)">删除</a> --%>
						<a href="${ctx}/finance/financeTransaction/orderDetail?capitalId=${financeTransaction.capitalId}">查看</a>
					</td>
<%-- 				</shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>