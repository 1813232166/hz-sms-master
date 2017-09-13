<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台账户管理</title>
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
		<li><a href="${ctx}/financialadmis/compFdAccountLog/">平台账户列表</a></li>
		<li class="active"><a href="${ctx}/financialadmis/compFdAccountLog/form?id=${compFdAccountLog.id}">平台账户<shiro:hasPermission name="compaccount:compFdAccountLog:edit">${not empty compFdAccountLog.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="compaccount:compFdAccountLog:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="compFdAccountLog" action="${ctx}/financialadmis/compFdAccountLog/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">业务编号：</label>
			<div class="controls">
				<form:input path="bizid" htmlEscape="false" maxlength="36" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户类型：</label>
			<div class="controls">
				<%-- <form:input path="biztype" htmlEscape="false" maxlength="2" class="input-xlarge required"/> --%>
				<select name="acctype"  class="input-xlarge required">
					<option  value="">全部</option>
					<option  value="01">现金账户</option>
					<option  value="02">手续费账户</option>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易时间：</label>
			<div class="controls">
				<input name="acdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${compFdAccountLog.acdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借方金额(收入)：</label>
			<div class="controls">
				<form:input path="debit" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">贷方金额（支出）：</label>
			<div class="controls">
				<form:input path="credit" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户余额（有些涉及余额的不是实时确定，需考虑哪些业务类型需要更新，该字段需慎重考虑）：</label>
			<div class="controls">
				<form:input path="bal" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${compFdAccountLog.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态(1-新增，2-成功，3-失败, 4-理财投资成功, 5-理财预约投资(扣款), 6-理财预约投资失败(回款)),7-(理财投资回款)：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他终端返回流水号：</label>
			<div class="controls">
				<form:input path="termno" htmlEscape="false" maxlength="36" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">后台用户号：</label>
			<div class="controls">
				<form:input path="operuserid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="compaccount:compFdAccountLog:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>