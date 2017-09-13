<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预约管理管理</title>
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
		
		function xinjian(){
			location.href= ctx+"/finance/financeBespeakManage/form";
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/finance/financeBespeakManage/">预约管理列表</a></li>
		<shiro:hasPermission name="finance:financeBespeakManage:edit"><li><a href="${ctx}/finance/financeBespeakManage/form">预约管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="financeBespeakManage" action="${ctx}/finance/financeBespeakManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>计划名称：</label>
				<form:select id="name" path="name" class="input-medium" >
					<form:option value="" label="请选择.."/>
					 <c:forEach items="${financeProductList}" var="financeProduct">    
                         <option <c:if test="${financeProduct.name eq financeBespeakManage.name}">selected</c:if>  value="${financeProduct.name}">${financeProduct.name}</option>  
                     </c:forEach>  
				</form:select>
			</li>		
			
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('BESPEAK_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>	
			
			<li>
				<label>创建时间</label>
				<input name="beginCreateTime" readonly="readonly" id="beginCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeBespeakManage.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				<input name="endCreateTime" readonly="readonly" id="endCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${financeBespeakManage.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			
			<li style="margin-right: 420px;" class="btns"><input id="newSubmit" class="btn btn-primary" type="button"  value="预约设置" onclick="xinjian()"/></li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>计划名称</th>
				<th>出借期限</th>
				<th>出借方式</th>
				<th>年均出借回报率约（%）</th>
				<th>每日最高预约金额（元）</th>
				<th>最低预约金额（元）</th>
				<th>递增金额（元）</th>
				<th>最高预约金额（元）</th>
				<th style="text-align: center;" width="100px">创建时间</th>
				<th style="text-align: center;" width="100px">启用时间</th>
				<th>状态</th>
				<th style="text-align: center;" width="100px">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="financeBespeakManage" varStatus="st">
		
			<tr>
				<td>
				${st.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td>
				${financeBespeakManage.name}
				</td>
				<td>
				${financeBespeakManage.nper}个月
				</td>
				<td>
				${fns:getDictLabel(financeBespeakManage.lendingMethod, 'LENDING_METHOD', '')}
				</td>
				<td>
				${financeBespeakManage.rate}
				<c:if test="${financeBespeakManage.rewardRate*1>0}">+${financeBespeakManage.rewardRate}</c:if>
				</td>
				<td>
				${financeBespeakManage.collectAmount}
				</td>
				<td>
				${financeBespeakManage.minTenderSum}
				</td>
				<td>
				${financeBespeakManage.incrementalAmount}
				</td>
				<td>
				${financeBespeakManage.maxTenderSum}
				</td>
				
				<td style="text-align: center;">
					<fmt:formatDate value="${financeBespeakManage.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td style="text-align: center;">
					<fmt:formatDate value="${financeBespeakManage.enableTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
				${fns:getDictLabel(financeBespeakManage.status, 'BESPEAK_STATUS', '')}
				</td>
				
				<td style="text-align: center;">
					<c:if test="${financeBespeakManage.status=='1' or financeBespeakManage.status=='3'}">
						<a href="${ctx}/finance/financeBespeakManage/delete?id=${financeBespeakManage.id}" onclick="return confirmx('确认要删除该预约设置吗？', this.href)">删除</a>
					</c:if>
					
					<c:if test="${financeBespeakManage.status=='1' or financeBespeakManage.status=='3' or financeBespeakManage.status=='4'}">
						<a href="${ctx}/finance/financeBespeakManage/form?id=${financeBespeakManage.id}">修改</a>
					</c:if>
					
					<c:if test="${financeBespeakManage.status=='4'}">
						<a href="${ctx}/finance/financeBespeakManage/stop?id=${financeBespeakManage.id}" onclick="return confirmx('确定要禁用此预约设置吗？', this.href)">禁用</a>
					</c:if>
					<c:if test="${financeBespeakManage.status=='5'}">
						<a href="${ctx}/finance/financeBespeakManage/open?id=${financeBespeakManage.id}" onclick="return confirmx('确定要开启此预约设置吗？', this.href)">开启</a>
					</c:if>
					
					<a href="${ctx}/finance/financeBespeakManage/financeBespeakManageDetail?id=${financeBespeakManage.id}">查看</a>
				</td>
				
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>