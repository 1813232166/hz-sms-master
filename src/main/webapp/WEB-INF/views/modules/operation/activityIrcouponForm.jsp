<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加息券管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/operation/activityIrcoupon/">加息券管理列表</a></li>
		<li class="active"><a href="${ctx}/operation/activityIrcoupon/form?id=${activityIrcoupon.id}">加息券管理</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="activityIrcoupon" action="${ctx}/operation/activityIrcoupon/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">加息券名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">加息比例：</label>
			<div class="controls">
				<form:input path="scale" htmlEscape="false" class="input-xlarge "/>%  加息0-1%（含）之间
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效期时间：</label>
			<div class="controls">
				<input name="startdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${activityIrcoupon.startdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<input name="enddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${activityIrcoupon.enddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				&nbsp;&nbsp;
				<form:checkbox path="startdate" value="-1"/>永久
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">加息天数：</label>
			<div class="controls">
				<form:input path="profittime" htmlEscape="false" maxlength="50" class="input-xlarge required"/>天
				&nbsp;&nbsp;
				<form:checkbox path="profittime" value="-1"/>不限
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用条件：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('useConditionStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出借金额大于等于金额：</label>
				<div class="controls">
					<form:input path="useconditionamount" htmlEscape="false" class="input-xlarge "/>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">出借产品限制（不选择默认为无限制）：</label>
			<div class="controls">
				<form:radiobuttons path="usetype" items="${fns:getDictList('investType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="operation:activityIrcoupon:edit"> --%><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;<%-- </shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>