<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易对账-垫付对账</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/*导出垫付对账列表  */
			 $("#btnSubmitexport").click(function(){
				 var ex = $("#searchForm").attr("action","${ctx}/financialadmis/advanceManage/exportAdvance");
				 ex.submit();
				$("#searchForm").attr("action","${ctx}/financialadmis/advanceManage/advanceList");
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
		<li class="active"><a href="${ctx}/financialadmis/advanceManage/">垫付对账</a></li>
	</ul>
	<form:form id="searchForm"  action="${ctx}/financialadmis/advanceManage/advanceList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 80px;">借款编号：</label>
				<input id = "borrowNo" name="borrowNo" value="${advanceVo.borrowNo }"  class="input-medium"/>
			</li>
			<li><label style="width: 105px;">平台交易流水号：</label>
				<input id = "tradeNo" name="tradeNo" value="${advanceVo.tradeNo }"  class="input-medium"/>
			</li>

			<li><label>垫付时间：</label>
				<input id="beginTime" name="beginTime" value="${advanceVo.beginTime}" type="text"  maxlength="20" class="input-medium Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				 <input id="overTime" name="overTime"  value="${advanceVo.overTime}" type="text"  maxlength="20" class="input-medium Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="clearfix"></li>
			<li class="btns" style="padding-left:900px">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>垫付时间</th>
				<th>垫付金额（元）</th>
				<th>借款编号</th>
				<th>期数</th>
				<th>平台交易流水号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="trade" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>${trade.advanceTime}</td>
				<td>${trade.advanceAmount}</td>
				<td>${trade.borrowNo}</td>
				<td>${trade.showPeriod}</td>
				<td>${trade.tradeNo}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>