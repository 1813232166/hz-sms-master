<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预约审核管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function chakan(id) {
			location.href=ctx+"/finance/financeBespeak/bespeakDetail?id="+id;
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
		<li class="active"><a href="${ctx}/finance/financeBespeak/list">预约管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tFinanceBespeak" action="${ctx}/finance/financeBespeak/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>计划名称:</label>
				<form:select id="name" path="name" class="input-medium" >
					<form:option value="" label="请选择.."/>
					 <c:forEach items="${financeProductList}" var="financeProduct">    
                         <option <c:if test="${financeProduct.name eq tFinanceBespeak.name}">selected</c:if>  value="${financeProduct.name}">${financeProduct.name}</option>  
                     </c:forEach>  
				</form:select>
			</li>		

			<li><label>预约状态:</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('BESPEAK_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>		
			
			<li>
				<label>创建时间:</label>
				<input name="beginCreateTime" readonly="readonly" id="beginCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tFinanceBespeak.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				<input name="endCreateTime" readonly="readonly" id="endCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tFinanceBespeak.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
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
				<th>出借期限</th>
				<th>出借方式</th>
				<th>年均出借回报率约(%)</th>
				<th>每日最高预约金额(元)</th>
				<th>最低预约金额(元)</th>
				<th>递增金额(元)</th>
				<th>最高预约金额(元)</th>
				<th>创建时间</th>
				<th>启用时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="finance" varStatus="st" >
			<tr>
			
				<td>${st.count+page.pageSize*(page.pageNo-1)}</td><!-- 序号 -->
				<td>${finance.name}</td><!-- 计划名称 -->
				<td>${finance.nper}个月</td><!-- 出借期限 -->
				<td>${fns:getDictLabel(finance.lendingMethod, 'LENDING_METHOD', '')}</td><!-- 出借方式 -->
				<td>${finance.rate}</td><!-- 年均出借回报率约(%) -->
				<td>${finance.collectAmount}</td><!-- 每日最高预约金额(元) -->
				<td>${finance.minTenderSum}</td><!-- 最低预约金额(元) -->
				<td>${finance.incrementalAmount}</td><!-- 递增金额(元) -->
				<td>${finance.maxTenderSum}</td><!-- 最高预约金额(元) -->
				<td><fmt:formatDate value="${finance.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td><!-- 创建时间 -->
				<td><!-- 启用时间 -->
					<fmt:formatDate value="${finance.enableTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 状态 -->
				<td>
					<c:if test="${finance.status=='1'}">未启用</c:if>
					<c:if test="${finance.status=='2'}">待审核</c:if>
					<c:if test="${finance.status=='3'}">审核失败</c:if>
					<c:if test="${finance.status=='4'}">启用中</c:if>
					<c:if test="${finance.status=='5'}">禁用中</c:if>
				</td>
				<td>
				<a href="javascript:void(0)" onclick="chakan('${finance.id}')" class="weight">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>