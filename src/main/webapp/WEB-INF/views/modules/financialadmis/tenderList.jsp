<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>财务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/*导出标的列表  */
			 $("#btnSubmitexport").click(function(){
				    /* var userMobile = $("#userMobile").val();
					var tradeNo = $("#tradeNo").val();
					var beginTime=$("#beginTime").val();
					var overTime=$("#overTime").val();
					var finBeginTime=$("#finBeginTime").val();
					var finEndTime=$("#finEndTime").val();
					location.href=ctx+"/financialadmis/tradeMoney/exportTender?userMobile="+userMobile+"&beginTime="+beginTime+"&tradeNo="+tradeNo+"&overTime="+overTime+"&finBeginTime="+finBeginTime+"&finEndTime="+finEndTime; */
					var ex = $("#searchForm").attr("action","${ctx}/financialadmis/tradeMoney/exportTender");
					ex.submit();
					$("#searchForm").attr("action","${ctx}/financialadmis/tradeMoney/tender");
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
		<li class="active"><a href="${ctx}/financialadmis/tFdAccountLog/">出借列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tenderVo" action="${ctx}/financialadmis/tradeMoney/tender/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户手机号：</label>
				<input id="userMobile" name="userMobile" value="${tenderVo.userMobile}"  class="input-medium"/>
			</li>
			<li><label style="width: 110px;">平台交易流水号：</label>
				<input id="tradeNo" name="tradeNo"  value="${tenderVo.tradeNo}"  class="input-medium"/>
			</li>
			<li><label>出借时间：</label>
				<input  name="beginTime" value="<fmt:formatDate value="${beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  type="text"  maxlength="20" class="input-medium Wdate"
					id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
				<input  name="overTime" value="<fmt:formatDate value="${overTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  type="text"  maxlength="20" class="input-medium Wdate"
					id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<%-- <li><label>完成时间：</label>
				 <input  name="finBeginTime" value="<fmt:formatDate value="${finBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  type="text"  maxlength="20" class="input-medium Wdate"
					id="d4323" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4324\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				<input   name=finEndTime value="<fmt:formatDate value="${finEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  type="text"  maxlength="20" class="input-medium Wdate"
					id="d4324" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4323\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li class="btns"  style="float: right;margin-right: 100px;"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			<li class="btns"  style="float: right;margin-right: 100px;"><input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户手机号</th>
				<th>出借时间</th>
				<th>完成时间</th>
				<th>出借金额（元）</th>
				<th>借款编号</th>
				<th>平台交易流水号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tender" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>${fn:substring(tender.userMobile,0,3)}****${fn:substring(tender.userMobile,7,11)}
				</td>
				<td>${tender.tenderTime}
				    <%-- <fmt:formatDate value="${tender.tenderTime}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
					<%-- ${fn:split(tender.tenderTime, '.')[0]} --%>
				</td>
				<td>${tender.tenderEndTime}
					<%-- <fmt:formatDate value="${tender.tenderEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
					<%-- ${fn:split(tender.tenderEndTime, '.')[0]} --%>
				</td>
				<td>${tender.tenderAmot}
					<%-- <fmt:formatNumber value="${tender.tenderAmot}" pattern="#,##0.00#"/> --%>
				</td>
				<td>
					${tender.loanNumber}
				</td>
				<td>
					${tender.tradeNo}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>