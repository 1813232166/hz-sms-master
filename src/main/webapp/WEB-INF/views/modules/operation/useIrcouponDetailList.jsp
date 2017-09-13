<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>使用管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	//导出所选数据excal表格
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/operation/useIrcouponDetail/exportIrcouponFile");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/operation/useIrcouponDetail/");
		$("#searchForm").submit();
       	return false;
       }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/operation/useIrcouponDetail/">使用列表</a></li>
		<%-- <shiro:hasPermission name="operation:ircouponDetail:edit"><li><a href="${ctx}/operation/ircouponDetail/form">使用添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ircouponDetail" action="${ctx}/operation/useIrcouponDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>状态：</label>
				<form:select  style="width: 136px;" path="uesdstatus" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('uesdStatus1')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>使用渠道：</label>
				<form:select  style="width: 136px;" path="consumchannel" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('JiFenLaiYuan')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>项目类型：</label>
				<form:select  style="width: 136px;" path="investtype" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('investType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>加息时间：</label>
				<form:select  style="width: 136px;" path="profitTime" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('profitTime')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns" ><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li><label>使用时间：</label>
				<input name="beginUseddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ircouponDetail.beginUseddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUseddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ircouponDetail.endUseddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>姓名：</label>
				<form:input style="width: 122px;" path="realname" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input style="width: 122px;" path="mobile" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			<div style="float:right;padding-right:20px;">
				<li>使用总数：${ircouponCounts.amounts }元</li><li style="margin-left: 12px">使用人数：${ircouponCounts.userCounts }人</li><li style="margin-left: 12px">使用人次：${ircouponCounts.counts }人次</li>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>手机号</th>
				<th>加息券名称</th>
				<th>出借项目</th>
				<th>项目类型</th>
				<th>出借金额（元）</th>
				<th>加息比例</th>
				<th>加息时间</th>
				<th>收益</th>
				<th>消费渠道</th>
				<th>使用时间</th>
				<th>使用状态</th>
				<%-- <shiro:hasPermission name="operation:ircouponDetail:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ircouponDetail">
			<tr>
				<td>${ircouponDetail.realname}</td>
				<td>${ircouponDetail.mobile}</td>
				<td>${ircouponDetail.ircouponName}</td>
				<td>${ircouponDetail.investname}</td>
				<td>${ircouponDetail.investtype}</td>
				<td>${ircouponDetail.investmoney}</td>
				<td>${ircouponDetail.scale}</td>
				<td>${ircouponDetail.profitTime}</td>
				<td>${ircouponDetail.profit}</td>
				<td>${ircouponDetail.consumchannel}</td>
				<td><fmt:formatDate value="${ircouponDetail.useddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${ircouponDetail.uesdstatus}</td>
				<%-- <shiro:hasPermission name="operation:ircouponDetail:edit"><td>
    				<a href="${ctx}/operation/ircouponDetail/form?id=${ircouponDetail.id}">修改</a>
					<a href="${ctx}/operation/ircouponDetail/delete?id=${ircouponDetail.id}" onclick="return confirmx('确认要删除该使用吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>