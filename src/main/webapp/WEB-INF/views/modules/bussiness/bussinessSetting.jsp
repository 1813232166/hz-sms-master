<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

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
		
		function tijiao(){
			var status1 = $("input[name='status1']:checked").val();
			var status2 = $("input[name='status2']:checked").val();
			$.ajax({
				type:"post",
				url:ctx+"/buss/buss/save",
				data:{status1:status1,status2:status2},
				dataType:"text",
				success:function(data){
					if(data=="ok"){
						confirmx('修改成功！', ctx+"/buss/buss/toBussSetting");
					}else{
						confirmx('修改失败，请稍后重试！！', ctx+"/buss/buss/toBussSetting");
					}
					
				}
			});
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">风险测评开关</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="buss" class="form-horizontal">
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">出借测评：</label>
			<div class="controls">
				<input type="radio" name="status1" value="1" ${status1==1?"checked":""}/>开启
				<input type="radio" name="status1" value="0" ${status1==0?"checked":""}/>关闭
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户后台测评：</label>
			<div class="controls">
				<input type="radio" name="status2" value="1" ${status2==1?"checked":""}/>开启
				<input type="radio" name="status2" value="0" ${status2==0?"checked":""}/>关闭
			</div>
		</div>
		<div class="form-actions">
			<input class="btn btn-primary" type="button" onclick="tijiao()" value="提交"/>&nbsp;
		</div>
	</form:form>

</body>
</html>