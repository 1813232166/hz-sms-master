<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易资金-充值</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var feesBear='${rechargeVo.feesBearer}';
			$("#feesBearer").val(feesBear);
			/*导出充值列表  */
			 $("#btnSubmitexport").click(function(){
				 /* var mobile = $("#userMobile").val();
				 var tradeNo = $("#tradeNo").val();
				 var beginTime = $("#beginTime").val();
				 var overTime = $("#overTime").val();
				 location.href= ctx +"/financialadmis/tradeMoney/exportRecharge?mobile="+mobile+"&tradeNo="+tradeNo+"&beginTime="+beginTime+"&overTime="+overTime; */
				 var ex = $("#searchForm").attr("action","${ctx}/financialadmis/tradeMoney/exportRecharge");
					ex.submit();
					$("#searchForm").attr("action","${ctx}/financialadmis/tradeMoney/recharge"); 
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
		<li class="active"><a href="${ctx}/financialadmis/tradeMoney/">充值列表</a></li>
	</ul>
	<form:form id="searchForm"  action="${ctx}/financialadmis/tradeMoney/recharge/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户手机号：</label> 
				<input id="userMobile" name="userMobile" value="${map.userMobile}" class="input-medium"/>
			</li>
			<li><label style="width: 110px;">平台交易流水号：</label>
				<input id = "tradeNo" name="tradeNo" value="${map.tradeNo }"  class="input-medium"/>
			</li>
			<%-- <li><label style="width: 120px;">手续费承担方类型：</label>
				<select name="feesBearer"  id="feesBearer" class="input-medium">
					<option  value="">全部</option>
					<option  value="1" ${map.feesBearer == 1 ? "selected":""}>交易方承担</option>
					<option  value="2" ${map.feesBearer == 2 ? "selected":""}>手续费账户承担</option>
				</select>
			</li> --%>
			<li><label>充值时间：</label>
				<input  name="beginTime" value="${map.beginTime}" type="text"  maxlength="20" class="input-medium Wdate required"
					id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
				 <input  name="overTime"  value="${map.overTime}" type="text"  maxlength="20" class="input-medium Wdate required"
					id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns" style="float: right;margin-right: 100px;"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
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
				<th>充值时间</th>
				<th>充值金额（元）</th>
				<th>手续费（元）</th>
				<th>手续费承担方类型</th>
				<th>平台交易流水号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recharge" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>
				${fn:substring(recharge.userMobile,0,3)}****${fn:substring(recharge.userMobile,7,11)}
				</td>
				<td>${recharge.beginTime}
					<%--  <fmt:formatDate value="${recharge.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
					<%-- ${fn:split(recharge.beginTime, '.')[0]} --%>
				</td>
				<td>${recharge.amount}
					<%-- <fmt:formatNumber value="${recharge.amount}" pattern="#,##0.00#"/> --%>
				</td>
				<td>
					<fmt:formatNumber value="${recharge.fee}" pattern="#,##0.00#"/>
				</td>
				<td>
					${recharge.feesBearer}
				</td>
				<td>
					${recharge.tradeNo}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>