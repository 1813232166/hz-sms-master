<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>营业部信息管理</title>
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
	<script src="${ctxStatic}/departmentDiction/departmentDiction.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/departmentdiction/departmentDiction/">营业部信息列表</a></li>
		<li class="active"><a href="${ctx}/departmentdiction/departmentDiction/form?id=${departmentDiction.id}">营业部信息<%-- <shiro:hasPermission name="departmentdiction:departmentDiction:edit"> --%>${not empty departmentDiction.id?'修改':'添加'}<%-- </shiro:hasPermission><shiro:lacksPermission name="departmentdiction:departmentDiction:edit">查看</shiro:lacksPermission> --%></a><%--</li> --%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="departmentDiction" action="${ctx}/departmentdiction/departmentDiction/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">省份：</label>
			<div class="controls">
				<select id="province" name="province" onchange="changeCity()">
				<option id="" value="">请选择</option>
				<c:forEach items="${provinceList}" var="list">
				    <option id="${list.id}" value="${list.name}" ${list.name==departmentDiction.province?'selected':'' }>${list.name}</option>
				</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市：</label>
			<div class="controls">
				<select id="city" name="city">
				   <option id="" value="">请选择</option>
				   <c:forEach items="${cityList}" var="cList">
				   <option id="${cList.id}" value="${cList.name}" ${cList.name==departmentDiction.city?'selected':'' }>${cList.name}</option>
				   </c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">营业部：</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="departmentdiction:departmentDiction:edit"> --%><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;<%-- </shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>