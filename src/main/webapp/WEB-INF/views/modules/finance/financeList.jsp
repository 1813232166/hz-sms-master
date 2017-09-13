<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	<!-- 初始化加载计划名称菜单-->
	
		$(function() {
			
			
		});
		
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		
		function xinjian(){
			location.href= ctx+"/finance/finance/form";
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/finance/finance/">出借计划列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="finance" action="${ctx}/finance/finance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
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
					value="<fmt:formatDate value="${finance.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				<input name="endCreateTime" readonly="readonly" id="endCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${finance.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			
			<div style="float:left;padding-right:20px;">
				 <ul>
				     <li style="margin-right: 420px;" class="btns"><input id="newSubmit" class="btn btn-primary" type="button"  value="新建计划" onclick="xinjian()"/></li>
					<li>
					一共${page.count}&nbsp;个产品，进行中<c:if test="${status4 ==null}">0</c:if>${status4}个，待审核<c:if test="${status2 ==null}">0</c:if>${status2}个，审核未通过<c:if test="${status3 ==null}">0</c:if>${status3}个，累计募集金额<c:if test="${collectSumAmount ==null}">0.00</c:if>${collectSumAmount}元
					</li>
				</ul>
			</div>
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
		<c:forEach items="${page.list}" var="finance" varStatus="st" >
			<tr>
			
				<td>
					${st.count+page.pageSize*(page.pageNo-1)}
				</td>
				
				<td>${finance.name}</td>
				<td>${finance.financeCode}</td>
				<td>${finance.nper}个月</td>
				
				<td>
				${fns:getDictLabel(finance.lendingMethod, 'LENDING_METHOD', '')}
				</td>
				<td>${finance.rate}
				<c:if test="${finance.rewardRate*1>0}">+${finance.rewardRate}</c:if>
				</td>
				<td>${finance.collectAmount}</td>
				<td>${finance.alreadyCollectedAmount}</td>
				<td>
				
				<c:choose><c:when test="${finance.scale=='100.00' and finance.collectAmount!=finance.alreadyCollectedAmount}">99.99%</c:when><c:when test="${finance.scale==0.00 and finance.alreadyCollectedAmount!='0.00'}">0.01%</c:when><c:otherwise>${finance.scale}%</c:otherwise></c:choose>
				
				</td>
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
				<c:if test="${finance.status=='1' or finance.status=='3'}">
					<a href="${ctx}/finance/finance/form?id=${finance.id}">修改</a>
					<a href="${ctx}/finance/finance/delete?id=${finance.id}" onclick="return confirmx('确认要删除该出借计划吗？', this.href)">删除</a>
				</c:if>
				
				
				<c:if test="${finance.status=='4'or finance.status=='5'}">
				<a href="${ctx}/finance/finance/complement?id=${finance.id}" onclick="return confirmx('确定要通过补标补满此产品？', this.href)">补标</a>
				</c:if>
				
    			<a href="${ctx}/finance/lendPlanManage/list?financeId=${finance.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>