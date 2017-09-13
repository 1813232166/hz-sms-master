<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人账户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/* 导出提现列表 */
			$("#btnExport").click(function(){
				var ex = $("#searchForm").attr("action","${ctx}/financialadmis/tradeMoney/exportWithdraw");
				ex.submit();
				$("#searchForm").attr("action","${ctx}/financialadmis/tradeMoney/withdraw");
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
		<li class="active"><a href="${ctx}/financialadmis/tradeMoney/withdraw">提现列表</a></li>
	</ul>
	<form id="searchForm" modelAttribute="withdrawVo" action="${ctx}/financialadmis/tradeMoney/withdraw" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户手机号：</label>
				<input id="userMobile" name="userMobile" value="${withdrawVo.userMobile}"  class="input-medium"/>
			</li>
			<li><label style="width: 110px;">平台交易流水号：</label>
				<input id ="tradeNo" name="tradeNo"  value="${withdrawVo.tradeNo}"  class="input-medium"/>
			</li>
			<%-- <li><label style="width: 120px;">手续费承担方类型：</label>
				<select name="feesBearer"  class="input-medium">
					<option  value="">全部</option>
					<option  value="1" ${withdrawVo.feesBearer==1 ? "selected":""}>交易方承担</option>
					<option  value="2" ${withdrawVo.feesBearer==2 ? "selected":""}>手续费账户承担</option>
				</select>
			</li> --%>
			<li><label>提现时间：</label>
				<%-- <input id="beginTime" value="<fmt:formatDate value="${beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="beginTime" type="text"  maxlength="20" class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				<input id="overTime" value="<fmt:formatDate value="${overTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="overTime" type="text"  maxlength="20" class="input-medium Wdate"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> --%>
					<input  value="${beginTime}" name="beginTime" type="text"  maxlength="20" class="input-medium Wdate"
					id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
				<input  value="${overTime}" name="overTime" type="text"  maxlength="20" class="input-medium Wdate"
					id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<%-- <li><label>完成时间：</label>
				<input  value="<fmt:formatDate value="${finBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="finBeginTime" type="text"  maxlength="20" class="input-medium Wdate"
					id="d4323" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4324\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				<input  value="<fmt:formatDate value="${finEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="finEndTime" type="text"  maxlength="20" class="input-medium Wdate"
					id="d4324" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4323\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<li class="btns" style="float: right;margin-right: 100px;"><input id=""  class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			<li class="btns"  style="float: right;margin-right: 100px;"><input id="btnExport"  class="btn btn-primary" type="button" value="导出"/></li>
		</ul>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户手机号</th>
				<th>提现时间</th>
				<th>提现金额（元）</th>
				<th>手续费（元）</th>
				<th>手续费承担方类型</th>
				<th>平台交易流水号</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="withdraw" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>${fn:substring(withdraw.userMobile,0,3)}****${fn:substring(withdraw.userMobile,7,11)}
				</td>
				<td>${withdraw.beginTime}
					<%-- <fmt:formatDate value="${withdraw.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/> --%> 
				</td>
				<td>${withdraw.amount}
					<%-- <fmt:formatNumber value="${withdraw.amount}" pattern="#,##0.00#"/> --%>
				</td>
				<td>
                    <fmt:formatNumber value="${withdraw.fee}" pattern="#,##0.00#"/>
                </td>
                <td>
                    ${withdraw.feesBearer}
                </td>
				<td>
					${withdraw.tradeNo}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>