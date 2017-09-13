<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmitexport").click(function(){
				var beginTimes = $("#beginTimes").val();
				var endTimes = $("#endTimes").val();
				location.href=ctx+"/user/usercount/exportwithdraw?beginTimes="+beginTimes+"&endTimes="+endTimes;
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
		<li class="active"><a href="${ctx}/user/usercount/withdraw">提现统计列表</a></li>
		<%-- <li><a href="${ctx}/user/usercount/form">用户添加</a></li> --%>
	</ul>
	<form id="searchForm" modelAttribute="withdraw" action="${ctx}/user/usercount/withdraw" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请提现时间：</label>
				<input name="beginTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${date1.beginTimes }" id="beginTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 至 
				<input name="endTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${date1.endTimes }" id="endTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>申请提现时间</th>
				<th>当日提现总金额（元）</th>
				<th>已放款（元）</th>
				<th>待放款（元）</th>
				<th>提现用户数（个）</th>
				<th>PC端提现（元）</th>
				<th>APP端（元）</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${withdraw}" var="withdraw" varStatus="status">
			<tr>
				<td>
					<%-- <a href="${ctx}/content/withdraw/form?id=${withdraw.id}"> --%>
					${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td>
					<%-- ${fns:getDictLabel(withdraw.withdrawColumn, 'withdraw_column', '')} --%>
					
					<fmt:formatDate value="${withdraw.times}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${withdraw.withdrawamount}
				</td>
				<td>
					${withdraw.alreadyload}
				</td>
				<td>
					${withdraw.pendingload}
				</td>
				<td>
					${withdraw.withdrawnum}
				</td>
				
				<td>
					${withdraw.pcwithdraw}
				</td>
				<td>
					${withdraw.appwithdraw}
				</td>
				
			</tr>
		</c:forEach>
			<tr>
				<td>
					总计：
				</td>
				<td>
					
				</td>
				<td>
					${with.withdrawamountsum}
				</td>
				<td>
					${with.alreadyloadsum}
				</td>
				<td>
					${with.pendingloadsum}
				</td>
				<td>
					${with.withdrawnumsum}
				</td>
				<td>
					${with.pcwithdrawsum}
				</td>
				<td>
					${with.appwithdrawsum}
				</td>
				
				
			</tr>
		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>