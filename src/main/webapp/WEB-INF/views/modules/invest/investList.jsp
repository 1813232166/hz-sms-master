<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/invest/invest/exportuserInfo.do");
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/invest/invest/");
			});
			$("#btnSubmit").click(function(){
				$("#searchForm").attr("action","${ctx}/invest/invest/");
				$("#searchForm").submit();
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
		<li class="active"><a href="${ctx}/invest/invest/">出借记录列表</a></li>
		<%-- <shiro:hasPermission name="invest:invest:edit"> --%><%-- <li><a href="${ctx}/invest/invest/form">出借记录添加</a></li> --%><%-- </shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="invest" action="${ctx}/invest/invest/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>类型：</label>
				<form:input path="investtype" htmlEscape="false" maxlength="2" class="input-medium"/>
				<form:select path="investtype" id="investtype" class="input-medium">
					<form:option value="">全部</form:option>
					<form:option value="0">悦享标</form:option>
					<form:option value="1">普享标</form:option>
				</form:select>
			</li>
			<li><label>投资编号：</label>
				<form:input id="id" path="id" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li> --%>
			<li><label>出借时间：</label>
				<input id="beginInvesttime" name="beginInvesttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${invest.beginInvesttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input id="endInvesttime" name="endInvesttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${invest.endInvesttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>出借人：</label>
				<form:input id="realName" path="realName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>标的编号：</label>
				<form:input id="borrowCode" path="borrowCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
			<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<label style="float: right;">
	出借总额：
	<c:if test="${empty sum}">
		0.00
	</c:if>
	<c:if test="${not empty sum}">
		<fmt:formatNumber type="number" value="${sum}" />
	</c:if>
	元
	</label>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>借款编号</th>
				<th>出借人</th>
				<th>出借类型</th>
				<th>出借金额（元）</th>
				<th>出借时间</th>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款人</th>
				<th>还款方式</th>
				<th>出借年化利率</th>
				<th>出借期限</th>
				<th>状态</th>
				<%-- <shiro:hasPermission name="invest:invest:edit"> --%><!-- <th>操作</th> --><%-- </shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="invest">
			<tr>
				<%-- <td><a href="${ctx}/invest/invest/form?id=${invest.id}">
					${invest.id}
				<!-- </a> --></td> --%>
				<td>${invest.loanNumber}</td>
				<td>${invest.realName}</td>
				<td>
					<c:if test="${invest.isAutoBid eq 1}">
						手动投标
					</c:if>
					<c:if test="${invest.isAutoBid eq 2}">
						自动投标
					</c:if>
				</td>
				<td><fmt:formatNumber type="number" value="${invest.realamount}" /></td>
				<td>
					<fmt:formatDate value="${invest.investtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${invest.borrowAliasNo}</td>
				<td>${invest.borrowAlias}</td>
				<td>${invest.jrealName}</td>
				<td>
				    <c:if test="${invest.payMethod eq 'debx'}">等额本息</c:if>
				    <c:if test="${invest.payMethod eq 'xxhb'}">先息后本</c:if>
				    <c:if test="${invest.payMethod eq 'ychbx'}">一次性还本付息</c:if>
				</td>
				<td>${invest.anualRate}</td>
				<td>${invest.deadline}</td>
				<td>
				<c:if test="${invest.investtype eq 1}">
					<c:if test="${invest.borrowStatus eq 4}">
						募集中
					</c:if>
					<c:if test="${invest.borrowStatus eq 11}">
						已满标
					</c:if>
					<c:if test="${invest.borrowStatus eq 7}">
						还款中
					</c:if>
					<c:if test="${invest.borrowStatus eq 8}">
						已结清
					</c:if>
					<c:if test="${invest.borrowStatus eq 12}">
						流标中
					</c:if>
					<c:if test="${invest.borrowStatus!=4 and invest.borrowStatus!=11 and invest.borrowStatus!=7 and invest.borrowStatus!=8 and invest.borrowStatus!=12}">
						---
					</c:if>
				</c:if>
				<c:if test="${invest.investtype eq 2}">
					<c:if test="${invest.borrowStatus eq 9}">
						招标中
					</c:if>
					<c:if test="${invest.borrowStatus eq 10}">
						已满标
					</c:if>
					<c:if test="${invest.borrowStatus eq 11}">
						还款中
					</c:if>
					<c:if test="${invest.borrowStatus eq 12}">
						已结清
					</c:if>
					<c:if test="${invest.borrowStatus eq 13}">
						撤销中
					</c:if>
					<c:if test="${invest.borrowStatus eq 14}">
						流标中
					</c:if>
					<c:if test="${invest.borrowStatus eq 15}">
						已撤销
					</c:if>
					<c:if test="${invest.borrowStatus eq 16}">
						已流标
					</c:if>
					<c:if test="${invest.borrowStatus!=9  and invest.borrowStatus!=10 and invest.borrowStatus!=11 
							  and invest.borrowStatus!=12 and invest.borrowStatus!=13 and invest.borrowStatus!=14
							  and invest.borrowStatus!=15  and invest.borrowStatus!=16}">
						---
					</c:if>
				</c:if>
				</td>
				<%-- <td>
					${invest.realamount}
				</td> --%>
				<%-- <td>
					<c:if test="${invest.investtype eq 1}">
						普享标
					</c:if>
					<c:if test="${invest.investtype eq 0}">
						理财
					</c:if>
					${invest.investtype}
				</td> --%>
				<%-- <shiro:hasPermission name="invest:invest:edit"> --%><!-- <td> -->
    				<%-- <a href="${ctx}/invest/invest/form?borrowid=${invest.borrowid}&id=${invest.id}">详情</a> --%>
					<%-- <a href="${ctx}/invest/invest/delete?id=${invest.id}" onclick="return confirmx('确认要删除该出借记录吗？', this.href)">删除</a> --%>
				<!-- </td> --><%-- </shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>