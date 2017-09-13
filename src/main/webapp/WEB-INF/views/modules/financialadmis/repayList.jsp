<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>财务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/* 导出还款列表 */
			$("#btnExport").click(function(){
				var ex = $("#searchForm").attr("action","${ctx}/financialadmis/tradeMoney/exportRepay");
				ex.submit();
				$("#searchForm").attr("action","${ctx}/financialadmis/tradeMoney/repay");
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
		<li class="active"><a href="${ctx}/financialadmis/tradeMoney/">还款列表</a></li>
	</ul>
	<form id="searchForm"  action="${ctx}/financialadmis/tradeMoney/repay/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户手机号：</label>
				<input id="userMobile" name="userMobile"   value="${capitalVo.userMobile}"  class="input-medium"/>
			</li>
			<li><label>还款类型：</label>
				<select name="repayType"  class="input-medium">
					<option  value="">全部</option>
					<option  value="1" ${capitalVo.repayType == '1' ? "selected":""}>正常还款</option>
					<option  value="2" ${capitalVo.repayType == '2' ? "selected":""}>逾期还款</option>
				</select>
			</li>
			<li><label style="width: 80px;">借款编号：</label>
				<input id = "loanNumber" name="loanNumber" value="${capitalVo.loanNumber }"  class="input-medium"/>
			</li>
			<li><label style="width: 110px;">平台交易流水号：</label>
				<input id="tradeNo" name="tradeNo"  value="${capitalVo.tradeNo}"  class="input-medium"/>
			</li>
			<li><label>还款时间：</label>
				<input id="beginTime"  value="${capitalVo.beginTime}" name="beginTime" type="text"  maxlength="20" class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				<input id="overTime"  value="${capitalVo.overTime}" name="overTime" type="text"  maxlength="20" class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户手机号</th>
				<th>还款时间</th>
				<th>还款金额（元）</th>
				<th>借款编号</th>
				<th>期数</th>
				<th>还款类型</th>
				<th>平台交易流水号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="refund" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>
				${fn:substring(refund.userMobile,0,3)}****${fn:substring(refund.userMobile,7,11)}
				</td>
				<td>${refund.transactionTime}</td>
				<td>${refund.transactionAmot}</td>
				<td>${refund.loanNumber}</td>
				<td>${refund.showPeriod}</td>
				<td>${refund.showRepayType}</td>
				<td>${refund.tradeNo}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>