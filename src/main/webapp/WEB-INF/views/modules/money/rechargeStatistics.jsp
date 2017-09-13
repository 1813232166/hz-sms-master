<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>充值统计</title>
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
		<li class="active"><a href="${ctx}/toRechargestatistics/findRechargeStatisticsList">充值统计列表</a></li>
		<%-- <shiro:hasPermission name="repaybillplan:tBorrowRepayBillplan:edit"><li><a href="${ctx}/repaybillplan/tBorrowRepayBillplan/form">查询回款列表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form id="searchForm"  action="${ctx}/toRechargestatistics/findRechargeStatisticsList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>充值时间：</label>
				<input name="beginTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${date1.beginTimes }" id="beginTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 至 
				<input name="endTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${date1.endTimes }" id="endTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/>
				<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>充值时间</th>
				<th>当日充值总金额（元）</th>
				<th>笔数</th>
				<th>总用户数（个）</th>
				<th>新增充值用户（个）</th>
				<th>PC端（元）</th>
				<th>APP端（元）</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="list" varStatus="status">
			<tr>
				<td>
					${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td>
					<fmt:formatDate value="${list.rechargeTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${list.totalAmount}
				</td>
				<td>
					${list.count}
				</td>
				<td>
					${list.userCount}
				</td>
				<td>
					${list.newUserCount}
				</td>
				<td>
					${list.pc}
				</td>
				<td>
					${list.app}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>