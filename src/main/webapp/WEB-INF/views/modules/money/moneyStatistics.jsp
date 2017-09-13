<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金统计</title>
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
		<li class="active"><a href="${ctx}/toMoneystatistics/findMoneystatisticsList">资金统计列表</a></li>
		<%-- <shiro:hasPermission name="repaybillplan:tBorrowRepayBillplan:edit"><li><a href="${ctx}/repaybillplan/tBorrowRepayBillplan/form">查询回款列表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form id="searchForm" action="${ctx}/toMoneystatistics/findMoneystatisticsList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>用户手机号：</label>
				<input name="phone" type="text" readonly="readonly" maxlength="20"
					 id="phone" style="width: 90px;"/>
				<label>可用余额：</label>
				<input name="minbalance" type="text" readonly="readonly" maxlength="20"
					 id="minbalance" style="width: 90px;"/> 至 
				<input name="maxbalance" type="text" readonly="readonly" maxlength="20"
					id="maxbalance" style="width: 90px;"/>
				<label>用户类型：</label>
				<select name="userType" style="width: 160px;">
					<option value="">全部</option>
					<option value="">咨询师</option>
					<option value="">普通用户-无推荐</option>
					<option value="">普通用户-咨询师推荐</option>
					<option value="">普通用户-普通用户推荐</option>
				</select>
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
				<th>用户手机号</th>
				<th>用户类型</th>
				<th>账户总额（元）</th>
				<th>可用余额（元）</th>
				<th>冻结金额（元）</th>
				<th>代收金额（元）</th>
				<th>充值总数（元）</th>
				<th>提现总数（元）</th>
				<th>投资总额（元）</th>
				<th>已收收益（元）</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="list" varStatus="status">
			<tr>
				<td>
					${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td>
					${list.phone}
				</td>
				<td>
					${list.userType}
				</td>
				<td>
					${list.totalAmount}
				</td>
				<td>
					${list.balance}
				</td>
				<td>
					${list.frozenCapital}
				</td>
				<td>
					${list.collectingAmount}
				</td>
				<td>
					${list.payTotal}
				</td>
				<td>
					${list.withdrawTotal}
				</td>
				<td>
					${list.totalInvestment}
				<td>
					${list.incomeReceived}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>