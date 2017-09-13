<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>短信模板管理</title>
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
		<li><a href="${ctx}/buss/messageModules/">短信模板列表</a></li>
		<li class="active"><a href="${ctx}/buss/messageModules/form?id=${messageModules.id}">短信模板<shiro:hasPermission name="buss:messageModules:edit">${not empty messageModules.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="buss:messageModules:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="messageModules" action="${ctx}/buss/messageModules/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">接收人：</label>
			<div class="controls">
				<form:select path="receiver" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('receiver')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="messageType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('message_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信内容：</label>
			<div class="controls">
				<form:textarea path="messageContent" htmlEscape="false" rows="4" class="input-xxlarge "/>
				<br />
				<span class="help-inline">
					<font color="red">提示，短信模板替换代码如下：</font><br />
					<font color="black">[name] 发送用户名字, 如：李四先生</font><br />
					<font color="black">[code] 发送验证码</font><br />
					<font color="black">[validTime] 验证码有效时间</font><br />
					<font color="black">[dateTime] 日期, 如：2017年4月5日</font><br />
					<font color="black">[title] 出借标题, 如：普享标17040600001</font><br />
					<font color="black">[borrowId] 借款编号,如：gjk17032500002</font><br />
					<font color="black">[flag] 标示, 如：已、未</font><br />
					<font color="black">[count] 数值</font><br />
					<font color="black">[money] 金额</font>
				</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="messageStatus" items="${fns:getDictList('message_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="buss:messageModules:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>