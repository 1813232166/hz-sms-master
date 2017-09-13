<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		$(function() {

		});
		
		function chakan(id) {
			location.href=ctx+"/finance/lendPlanAudit/lendPlanDetail?id="+id;
		}
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
		<li class="active"><a href="${ctx}/finance/lendPlanAudit/list">出借计划审核</a></li>
<%-- 		<shiro:hasPermission name="finance:finance:edit"><li><a href="${ctx}/finance/finance/form">出借计划添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="financeLend" action="${ctx}/finance/lendPlanAudit/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${pageLend.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${pageLend.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>计划名称：</label>
				<form:select id="name" path="name" class="input-medium" >
					<form:option value="" label="请选择.."/>
					 <c:forEach items="${financeProductList}" var="financeProduct">    
                         <option <c:if test="${financeProduct.name eq finance.name}">selected</c:if>  value="${financeProduct.name}">${financeProduct.name}</option>  
                     </c:forEach>  
				</form:select>
			</li>		


			<li><label>计划编号：</label>
				<form:input id="financeCode" path="financeCode" htmlEscape="false" maxlength="32" class="input-medium" />
			</li>

			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('FINANCE_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>		
			
			<li>
				<label>创建时间</label>
				<input name="beginCreateTime" readonly="readonly" id="beginCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeLend.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				<input name="endCreateTime" readonly="readonly" id="endCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeLend.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>



			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>计划名称</th>
				<th>计划编号</th>
				<th>出借期限</th>
				<th>出借方式</th>
				<th>年均出借回报率约(%)</th>
				<th>募集总额(元)</th>
				<th>实际募集金额(元)</th>
				<th>募集比例</th>
				<th>最低出借金额(元)</th>
				<th>募集开始时间</th>
				<th>募集结束时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageLend.list}" var="finance" varStatus="st" >
			<tr>
			
				<td>
					${st.count+page.pageSize*(page.pageNo-1)}
				</td>
				
				<td>${finance.name}</td>
				<td>${finance.financeCode}</td>
				<td>${finance.nper}个月</td>
				<td>${fns:getDictLabel(finance.lendingMethod, 'LENDING_METHOD', '')}</td>
				<td>${finance.rate}</td>
				<td>${finance.collectAmount}</td>
				<td>${finance.alreadyCollectedAmount}</td>
				<td>${finance.scale}%</td>
				<td>${finance.minTenderSum}</td>
				
				
				<td style="text-align: center;">
					<fmt:formatDate value="${finance.startTimeOfCollection}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align: center;">
					<fmt:formatDate value="${finance.endTimeOfCollection}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
				
				<c:if test="${finance.status=='1'}">未发布</c:if>
				<c:if test="${finance.status=='2'}">待审核</c:if>
				<c:if test="${finance.status=='3'}">审核未通过</c:if>
				<c:if test="${finance.status=='4'or finance.status=='5'}">进行中</c:if>
				<c:if test="${finance.status=='6' or finance.status=='7'or finance.status=='8'}">已结束</c:if>
				
				</td>
			
				<td>
				<a href="javascript:void(0)" onclick="chakan('${finance.id}')" class="weight">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${pageLend}</div>
</body>
</html>