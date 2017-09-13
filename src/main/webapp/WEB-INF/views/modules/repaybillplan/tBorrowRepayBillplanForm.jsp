<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>查询回款列表管理</title>
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
		<li><a href="${ctx}/repaybillplan/tBorrowRepayBillplan/">查询回款列表列表</a></li>
		<li class="active"><a href="${ctx}/repaybillplan/tBorrowRepayBillplan/form?id=${tBorrowRepayBillplan.id}">查询回款列表<shiro:hasPermission name="repaybillplan:tBorrowRepayBillplan:edit">${not empty tBorrowRepayBillplan.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="repaybillplan:tBorrowRepayBillplan:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tBorrowRepayBillplan" action="${ctx}/repaybillplan/tBorrowRepayBillplan/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">投资编号：</label>
			<div class="controls">
				<form:input path="investid" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投资期次序号：</label>
			<div class="controls">
				<form:input path="billnum" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利息年份：</label>
			<div class="controls">
				<form:input path="capiyear" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利息月份：</label>
			<div class="controls">
				<form:input path="capimonth" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应收款日期：</label>
			<div class="controls">
				<form:input path="sdate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应收本金：</label>
			<div class="controls">
				<form:input path="stcapi" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应收利息：</label>
			<div class="controls">
				<form:input path="sinte" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">贷款余额：</label>
			<div class="controls">
				<form:input path="bal" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实收款日期：</label>
			<div class="controls">
				<form:input path="rdate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实收本金：</label>
			<div class="controls">
				<form:input path="rtcapi" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实收利息：</label>
			<div class="controls">
				<form:input path="rinte" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否到期未收到：</label>
			<div class="controls">
				<form:input path="overflag" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">该期次是否已收清：</label>
			<div class="controls">
				<form:input path="payoffflag" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="repaybillplan:tBorrowRepayBillplan:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>