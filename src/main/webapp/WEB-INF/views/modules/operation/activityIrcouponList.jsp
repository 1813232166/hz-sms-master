<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加息券管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	//导出所选数据excal表格
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/operation/activityIrcoupon/exportActivityIrcoupon");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/operation/activityIrcoupon/");
		$("#searchForm").submit();
       	return false;
       }
	function xinjian(){
		location.href = "${ctx}/operation/activityIrcoupon/form";
	};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/operation/activityIrcoupon/">加息券管理列表</a></li>
		<%-- <shiro:hasPermission name="operation:activityIrcoupon:edit"><li><a href="${ctx}/operation/activityIrcoupon/form">加息券管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="activityIrcoupon" action="${ctx}/operation/activityIrcoupon/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>状态：单选按钮</label>
				<form:radiobuttons path="status" items="${fns:getDictList('JiFenLaiYuan')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('ircouponStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>有效期类型：</label>
				<form:select path="expDate" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('profitTime')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>有效时间：</label>
				<input name="beginStartdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${activityIrcoupon.beginStartdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endStartdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${activityIrcoupon.endStartdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns" ><input id="btnCreat" class="btn btn-primary" type="button" onclick="xinjian();" value="新建"/></li>
			<li class="btns" ><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li><label>加息时间：</label>
				<form:select path="profittime" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('profitTime')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>加息券名称：</label>
				<form:input style="width: 150px" path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>加息券ID：</label>
				<form:input style="width: 150px" path="id" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<%-- <li><label>创建者：复选框</label><a href="${ctx}/operation/activityIrcoupon/form">新建</a>
				<form:checkboxes path="createBy.id" items="${fns:getDictList('JiFenLaiYuan')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li> --%>
			
			<li style="margin-left: 201px" class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			<div style="float:right;padding-right:20px;">
				<li>发放总数：${ircouponCounts.sendNumberCount}张</li><li style="margin-left: 12px">使用数量：${ircouponCounts.usedNumberCount}张</li><li style="margin-left: 12px">过期数量：${ircouponCounts.expireNumberCount}张</li><li style="margin-left: 12px">总奖励金额：${ircouponCounts.profitAmountCount}元</li>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>名称</th>
				<th>加息比例</th>
				<th>加息时间</th>
				<th>发放数量（张）</th>
				<th>使用数量（张）</th>
				<th>过期数量（张）</th>
				<th>奖励金额（元）</th>
				<th>状态</th>
				<th>有效期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="activityIrcoupon">
			<tr>
				<td>
					${activityIrcoupon.id}
				</td>
				<td><a href="${ctx}/operation/activityIrcoupon/form?id=${activityIrcoupon.id}">
					${activityIrcoupon.name}
				</a></td>
				<td>
					${activityIrcoupon.scale}
				</td>
				<td>
					${activityIrcoupon.profittime}
				</td>
				<td>
					${activityIrcoupon.sendnumber}
				</td>
				<td>
					${activityIrcoupon.usednumber}
				</td>
				<td>
					${activityIrcoupon.expirenumber}
				</td>
				<td>
					${activityIrcoupon.profitamount}
				</td>
				<td>
					${activityIrcoupon.status}
				</td>
				<td>
					<c:if test="${activityIrcoupon.startdate==null}">
						永久
					</c:if>
					<c:if test="${activityIrcoupon.startdate!=null}">
						<fmt:formatDate value="${activityIrcoupon.startdate}" pattern="yyyy-MM-dd"/>
						至
						<fmt:formatDate value="${activityIrcoupon.enddate}" pattern="yyyy-MM-dd"/>
					</c:if>
				</td>
				<td>
					<c:if test="${activityIrcoupon.status=='待发布'}">
	    				<a href="${ctx}/operation/activityIrcoupon/form?id=${activityIrcoupon.id}">编辑</a>
						<a href="${ctx}/operation/activityIrcoupon/updateStatus?id=${activityIrcoupon.id}&status=2">发布</a>
					</c:if>
					<c:if test="${activityIrcoupon.status=='进行中'}">
						<a href="${ctx}/operation/activityIrcoupon/updateStatus?id=${activityIrcoupon.id}&status=4">停止</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>