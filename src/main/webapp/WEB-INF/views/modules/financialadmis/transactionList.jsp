<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易列表-交易列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/*导出交易列表  */
			 $("#btnSubmitexport").click(function(){
				 var ex = $("#searchForm").attr("action","${ctx}/financialadmis/tradeManage/exportTransaction");
				 ex.submit();
				$("#searchForm").attr("action","${ctx}/financialadmis/tradeManage/transactionList");
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
		<li class="active"><a href="${ctx}/financialadmis/tradeManage/">交易列表</a></li>
	</ul>
	<form:form id="searchForm"  action="${ctx}/financialadmis/tradeManage/transactionList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 105px;">平台交易流水号：</label>
				<input id = "tradeNo" name="tradeNo" value="${transactionVo.tradeNo }"  class="input-medium"/>
			</li>
			<li><label style="width: 50px;">账户：</label>
				<input id = "accCode" name="accCode" value="${transactionVo.accCode }"  class="input-medium"/>
			</li>
			<li><label>交易时间：</label>
				<input id="beginTime" name="beginTime" value="${transactionVo.beginTime}" type="text"  maxlength="20" class="input-medium Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				 <input id="overTime" name="overTime"  value="${transactionVo.overTime}" type="text"  maxlength="20" class="input-medium Wdate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<select name="tradeStatus"  class="input-medium">
					<option  value="">全部</option>
					<option  value="1" ${transactionVo.tradeStatus == '1' ? "selected":""}>成功</option>
					<option  value="2" ${transactionVo.tradeStatus == '2' ? "selected":""}>处理中</option>
					<option  value="3" ${transactionVo.tradeStatus == '3' ? "selected":""}>失败</option>
					<option  value="4" ${transactionVo.tradeStatus == '4' ? "selected":""}>冻结</option>
					<option  value="5" ${transactionVo.tradeStatus == '5' ? "selected":""}>异常</option>
				</select>
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
				<th>平台交易流水号</th>
				<th>账户</th>
				<th>交易类型</th>
				<th>交易时间</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="trade" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>${trade.tradeNo}</td>
				<td>${fn:substring(trade.showAccCode,0,3)}****${fn:substring(trade.showAccCode,7,11)}
				</td>
				<td>${trade.showTradeType}</td>
				<td>${trade.tradeTime}</td>
				<td>${trade.showTradeStatus}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>