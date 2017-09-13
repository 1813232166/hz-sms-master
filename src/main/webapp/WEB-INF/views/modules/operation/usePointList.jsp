<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>使用列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		
		//导出所选数据excal表格
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/operation/usePoint/exportPointFile");
						$("#searchForm").submit(); 
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/operation/usePoint/")
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/operation/usePoint/">使用列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="usePoint" action="${ctx}/operation/usePoint/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select path="consumPoint" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('consumptionChannel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>使用渠道：</label>
				<form:select path="sourcechannel" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('JiFenLaiYuan')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>使用时间：</label>
				<input name="beginUsedDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${usePoint.beginUsedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUsedDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${usePoint.endUsedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns" ><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li><label>姓名：</label>
				<form:input  style="width: 150px" path="realName" htmlEscape="false" maxlength="32" class="input-medium" placeholder="请输入姓名"/>
			</li>
			<li><label>手机号：</label>
				<form:input style="width: 149px" path="mobile" htmlEscape="false" maxlength="32" class="input-medium" placeholder="请输入手机号"/>
			</li>
			<li style="margin-left: 459px" class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
			<div style="float:right;padding-right:20px;">
				<li>使用总数：${usePointCounts.amounts }分</li><li style="margin-left: 12px">使用人数：${usePointCounts.userCounts }人</li><li style="margin-left: 12px">使用人次：${usePointCounts.counts }人次</li>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">姓名</th>
				<th style="text-align: center;">手机号 </th>
				<th style="text-align: center;">使用数量(分)</th>
				<th style="text-align: center;">类型</th>
				<th style="text-align: center;">使用渠道 </th>
				<th style="text-align: center;">使用时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="usePoint">
			<tr>
				<td style="text-align: center;">${usePoint.realName}</td>
				<td style="text-align: center;">${usePoint.mobile}</td>
				<td style="text-align: center;">${usePoint.amount}</td>
				<td style="text-align: center;">${usePoint.consumPoint}</td>
				<td style="text-align: center;">${usePoint.sourcechannel}</td>
				<td style="text-align: center;">
					<fmt:formatDate value="${usePoint.usedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>