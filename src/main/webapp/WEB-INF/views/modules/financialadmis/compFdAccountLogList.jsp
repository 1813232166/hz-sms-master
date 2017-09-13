<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台账户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#exportExcel").click(function(){
				$("#endLimit").val(20000);
				$("#searchForm").attr("action","${ctx}/financialadmis/compFdAccountLog/exportcompFdAccountList");
                $("#searchForm").submit();
			})
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
		<li class="active"><a href="${ctx}/financialadmis/compFdAccountLog/">平台账户列表</a></li>
		<%-- <shiro:hasPermission name="financialadmis:compFdAccountLog:edit"><li><a href="${ctx}/financialadmis/compFdAccountLog/form">平台账户添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="compFdAccountLog" action="${ctx}/financialadmis/compFdAccountLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="endLimit" name="endLimit" type="hidden" value=""/>
		<ul class="ul-form">
			<li><label>账户类型：</label>
				<%-- <form:input path="biztype" htmlEscape="false" maxlength="2" class="input-medium"/> --%>
				<select name="userId"  class="input-medium">
					<option  value="">全部</option>
					<option  value="SYS_GENERATE_001"  ${compFdAccountLog.userId == '平台垫付账户' ? "selected":""}>平台垫付账户</option>
					<option  value="SYS_GENERATE_003"  ${compFdAccountLog.userId == '服务费账户' ? "selected":""}>服务费账户</option>
				</select>
			</li>
			<li><label>交易时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${compFdAccountLog.beginTime}" 
                   id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
                <input name="overTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"   value="${compFdAccountLog.overTime}"  
                    id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li>
                    <label style="width: 200px;">平台交易流水号</label>
                    <input name="tradeNo"  value="${compFdAccountLog.tradeNo}"  class="input-medium"/>
             </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			   <input  class="btn btn-primary" type="button" value="导出" id="exportExcel"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>账户类型</th>
				<th>交易时间</th>
				<th>平台交易流水号</th>
				<th>收入（元）</th>
				<th>支出（元）</th>
				<th>备注</th>
				<%-- <shiro:hasPermission name="compaccount:compFdAccountLog:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="compFdAccountLog" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>
					${compFdAccountLog.userId}
				</td>
				<td>
					<fmt:formatDate value="${compFdAccountLog.acdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				  ${compFdAccountLog.tradeNo}
				</td>
				<td>
					${compFdAccountLog.debit}
				</td>
				<td>
					${compFdAccountLog.credit}
				</td>
				<td>
					${fn:split(compFdAccountLog.remark, "金额：")[0]}
				</td>
				<%-- <shiro:hasPermission name="compaccount:compFdAccountLog:edit"><td>
    				<a href="${ctx}/compaccount/compFdAccountLog/form?id=${compFdAccountLog.id}">修改</a>
					<a href="${ctx}/compaccount/compFdAccountLog/delete?id=${compFdAccountLog.id}" onclick="return confirmx('确认要删除该平台账户吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>