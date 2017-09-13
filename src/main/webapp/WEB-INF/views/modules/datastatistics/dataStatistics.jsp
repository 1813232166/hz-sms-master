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
		<li class="active"><a href="${ctx}/toMoneystatistics/findMoneystatisticsList">借款/出借统计</a></li>
		<%-- <shiro:hasPermission name="repaybillplan:tBorrowRepayBillplan:edit"><li><a href="${ctx}/repaybillplan/tBorrowRepayBillplan/form">查询回款列表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<sys:message content="${message}"/>
	<br>
	<h4>借款统计</h4>
	<br>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tbody>
			<tr>
				<td>
					总借款金额
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.sumBorrowAmount}" />
				</td>
				<td>
					发布借款笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.totalPublishedBorrow}" />
				</td>
				<td>
					逾期本息总和
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.sumOverdueAmount}" />
				</td>
			</tr>
			<tr>
				<td>
					正常还款次数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.totalNormalRefund}" />
				</td>
				<td>
					累积成功借款笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.totalSuccessFulBorrow}" />
				</td>
				<td>
					已垫付逾期费用
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.sumAdvancesAmount}" />
				</td>
			</tr>
			<tr>
				<td>
					已还本息总额
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.sumRefundAmount}" />
				</td>
				<td>
					正常还清笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.totalNormalRefundBorrow}" />
				</td>
				<td>
					待垫付逾期费用
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.sumToAdvancesAmount}" />
				</td>
			</tr>
			<tr>
				<td>
					待还本息总额
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.sumToRefundAmount}" />
				</td>
				<td>
					逾期还清笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.totalOverdueRefund}" />
				</td>
				<td>
					逾期累积次数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.totalOverdue}" />
				</td>
			</tr>
			<tr>
				<td>
					已收借款服务费
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.sumServiceCharge}" />
				</td>
				<td>
					未还清笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.totalToRefund}" />
				</td>
				<td>
					严重逾期笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.totalSeriousOverdue}" />
				</td>
			</tr>
			<tr>
				<td>
					募集中借款总额
				</td>
				<td>
					<fmt:formatNumber type="number" value="${borrowStatistics.sumCollectingBorrowAmount}" />
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<h4>出借统计</h4>
	<br>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tbody>
			<tr>
				<td>
					总出借笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.totalInvest}" />
				</td>
				<td>
					总出借金额
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.sumInvestAmount}" />
				</td>
				<td>
					出借用户剩余金额（总）
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.sumAbleBalanceAmount}" />
				</td>
			</tr>
			<tr>
				<td>
					已结清笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.totalPayoff}" />
				</td>
				<td>
					已回收本金
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.sumActualCapitalAmount}" />
				</td>
				<td>
					已回收利息
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.sumActualInteAmount}" />
				</td>
			</tr>
			<tr>
				<td>
					还款中笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.totalRepaying}" />
				</td>
				<td>
					待回收本金
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.sumOughtCapitalAmount}" />
				</td>
				<td>
					待回收利息
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.sumOughtInteAmount}" />
				</td>
			</tr>
			<tr>
				<td>
					已逾期笔数
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.totalOverdue}" />
				</td>
				<td>
					已逾期本金
				</td>
				<td>
					<fmt:formatNumber type="number" value="${lenderStatistics.sumOverdueCapitalAmount}" />
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>