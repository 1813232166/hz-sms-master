<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预约记录管理</title>
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
		<li class="active"><a href="${ctx}/finance/tCapitalBespeak/">预约记录管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tCapitalBespeak" action="${ctx}/finance/tCapitalBespeak/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>计划名称：</label>
				<form:select id="name" path="name" class="input-medium" >
					<form:option value="" label="请选择.."/>
					 <c:forEach items="${financeProductList}" var="financeProduct">    
                         <option <c:if test="${financeProduct.name eq tCapitalBespeak.name}">selected</c:if>  value="${financeProduct.name}">${financeProduct.name}</option>  
                     </c:forEach>  
				</form:select>
			</li>		

			<li><label>手机号：</label>
				<form:input id="mobile" path="mobile" htmlEscape="false" maxlength="11" class="input-medium" />
			</li>
			<li><label>真实姓名：</label>
				<form:input id="realName" path="realName" htmlEscape="false" maxlength="11" class="input-medium" />
			</li>

			<li><label>预约状态:</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('BESPEAK_CAPITAL_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>		
			
			<li>
				<label>预约时间</label>
				<input name="beginBespeakTime" readonly="readonly" id="beginBespeakTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tCapitalBespeak.beginBespeakTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				<input name="endBespeakTime" readonly="readonly" id="endBespeakTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tCapitalBespeak.endBespeakTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			
		</ul>
		<ul class="ul-form">
		<li>一共${findBespeakingMap["capOne"]}笔预约中,${findCancelBespeakMap["capTwo"]}笔取消预约，预约中金额${findBespeakingMap["amountSumOne"]}元，取消预约金额${findCancelBespeakMap["amountSumTwo"]}元</li>
		</ul>
		
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>手机号</th>
				<th>真实姓名</th>
				<th>预约单号</th>
				<th>预约时间</th>
				<th>预约金额(元)</th>
				<th>预约产品</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="finance" varStatus="st" >
			<tr>
			
				<td>
					${st.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td>${finance.mobile}</td>
				<td>${finance.realName}</td>
				<td>${finance.capitalCode}</td>
				<td><fmt:formatDate value="${finance.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${finance.amount}</td>
				<td>${finance.name}</td>
				<!-- 状态 -->
				<td>
					<c:if test="${finance.status=='1'}">预约中</c:if>
					<c:if test="${finance.status=='2'}">取消预约</c:if>
					<c:if test="${finance.status=='3'}">已投资</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>