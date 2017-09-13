<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人账户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/financialadmis/tFdAccountLog/exportlist").submit();
				$("#searchForm").attr("action","${ctx}/financialadmis/tFdAccountLog/list");
			});
			$("#btnSubmit").click(function(){
				$("#searchForm").attr("action","${ctx}/financialadmis/tFdAccountLog/list").submit();
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
		<li class="active"><a href="${ctx}/financialadmis/tFdAccountLog/">个人账户列表</a></li>
		<shiro:hasPermission name="financialadmis:tFdAccountLog:edit"><li><a href="${ctx}/financialadmis/tFdAccountLog/form">个人账户添加</a></li></shiro:hasPermission>
	</ul>
	<form id="searchForm"  action="${ctx}/financialadmis/tFdAccountLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户手机号：</label>
				<input name="mobile"  value="${tFdAccountLog.mobile}"  class="input-medium"/>
			</li>
			
			<li><label>交易时间：</label>
				<input name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${tFdAccountLog.beginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
                <input name="overTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${tFdAccountLog.overTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
                    id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li style="float: right;margin-right: 10px;" class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
		<ul class="ul-form">
			<li><label>交易类型：</label>
				<select name="biztype"  class="input-medium">
					<option  value="">请选择</option>
					<option  value="1" ${tFdAccountLog.biztype =="1"? "selected":"" }>充值</option>
					<option  value="5" ${tFdAccountLog.biztype =="5"? "selected":"" }>提现</option>
					<option  value="6" ${tFdAccountLog.biztype =="6"? "selected":"" }>投标</option>
					<option  value="7" ${tFdAccountLog.biztype =="7"? "selected":"" }>还款</option>
					<option  value="8" ${tFdAccountLog.biztype =="8"? "selected":"" }>放款</option>
					<option  value="10" ${tFdAccountLog.biztype =="10"? "selected":"" }>还代偿款</option>
					<option  value="14" ${tFdAccountLog.biztype =="14"? "selected":"" }>本息</option>
					<option  value="15" ${tFdAccountLog.biztype =="15"? "selected":"" }>红包奖励</option>
					<%-- <option  value="9" ${tFdAccountLog.biztype =="9"? "selected":"" }>收益</option> --%>
				</select>
			</li>
			<li>
			        <label style="width: 200px;">平台交易流水号</label>
			        <input name="tradeNo"  value="${tFdAccountLog.tradeNo}"  class="input-medium"/>
			 </li>
			
			<li  style="float: right;margin-right: 10px;" class="btns">
				  <input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
				</li>
		</ul>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户手机号</th>
				<th>交易时间</th>
				<th>交易类型</th>
				<th>收入(元)</th>
				<th>支出(元)</th>
				<th>平台交易流水号</th>
				<!-- <th>结余(元)</th> -->
				<shiro:hasPermission name="financialadmis:tFdAccountLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tFdAccountLog" varStatus="tfdAcc">
			<tr>
				<td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>${fn:substring(tFdAccountLog.mobile,0,3)}****${fn:substring(tFdAccountLog.mobile,7,11)}
				</td>
				<td>
					<fmt:formatDate value="${tFdAccountLog.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>
						<c:when test="${tFdAccountLog.biztype=='2'||tFdAccountLog.biztype=='1'||tFdAccountLog.biztype=='3'||tFdAccountLog.biztype=='4'}">充值</c:when>
						<c:when test="${tFdAccountLog.biztype=='5'}">提现</c:when>
						<%-- <c:when test="${tFdAccountLog.biztype=='5'&&tFdAccountLog.remark=='2'}">放款提现</c:when> --%>
						<c:when test="${tFdAccountLog.biztype=='6'}">投标</c:when>
						<c:when test="${tFdAccountLog.biztype=='7'}">还款</c:when>
						<c:when test="${tFdAccountLog.biztype=='8'}">放款</c:when>
						<c:when test="${tFdAccountLog.biztype=='10'}">还代偿款</c:when>
						<c:when test="${tFdAccountLog.biztype=='14'}">本息</c:when>
						<c:when test="${tFdAccountLog.biztype=='15'}">红包奖励</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
					
				</td>
				<td>
					<fmt:formatNumber value="${tFdAccountLog.debit}" pattern="#,##0.00#"/>
				</td>
				<td>
					<fmt:formatNumber value="${tFdAccountLog.credit}" pattern="#,##0.00#"/>
				</td>
				<td>
                    ${tFdAccountLog.tradeNo}
                </td>
				<%-- <td>
					<fmt:formatNumber value="${tFdAccountLog.bal}" pattern="#,##0.00#"/>
				</td> --%>
				<shiro:hasPermission name="financialadmis:tFdAccountLog:edit"><td>
    				<a href="${ctx}/financialadmis/tFdAccountLog/form?id=${tFdAccountLog.id}">修改</a>
					<a href="${ctx}/financialadmis/tFdAccountLog/delete?id=${tFdAccountLog.id}" onclick="return confirmx('确认要删除该个人账户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>