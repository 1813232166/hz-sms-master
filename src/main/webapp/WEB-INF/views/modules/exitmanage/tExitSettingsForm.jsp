<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>退出管理查看</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#auditModal").hide();
			//取消
			$("#cancel").click(function(){
				$("#auditModal").modal('hide');
			});
			$("#close1").click(function(){
				$("#auditModal").modal('hide');
			});
			//确定
			$("#confirmreturn").click(function(){
				
				var auditRadio=$("input[name='auditRadio']:checked").val();
				var id='${tExitSettings.id}';
				var auditArea=$("#auditArea").val();
	 			$.post(ctx+'/exitmanage/tExitSettings/audit',{id:id,auditRadio:auditRadio,auditArea:auditArea},function(f){
	 				if(f){
	 					$("#confirmAddWeight").hide();
	 					$("#dialog2").hide();
	 					$(".modal-footer").hide();
	 					alert("审核成功");
	 					location.href=ctx+'/exitmanage/tExitSettings/exitAuditList';
	 				}else{
	 					$("#confirmAddWeight").hide();
	 					$("#dialog2").hide();
	 					$(".modal-footer").hide();
	 					alert.text("审核失败");
	 				}
	 			},"json")
			});
		});


		 ////审核
		 function auditFun(){
			 $("#auditModal").modal('show');
		 }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exitmanage/tExitSettings/">*退出审列表</a></li>
		<li class="active"><a href="${ctx}/exitmanage/tExitSettings/form?id=${tExitSettings.id}">退出管理<shiro:hasPermission name="exitmanage:tExitSettings:edit">${not empty tExitSettings.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="exitmanage:tExitSettings:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tExitSettings" action="${ctx}/exitmanage/tExitSettings/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*明日到期金额:</label>
			<div class="controls">
			   ${tExitSettings.expireAmount }元
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*到期未退出金额:</label>
			<div class="controls">
				${tExitSettings.notExitedAmount }元
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*退出金额:</label>
			<div class="controls">
			  ${tExitSettings.quitAmount }元
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*创建时间:</label>
			<div class="controls">
				<fmt:formatDate value="${tExitSettings.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*发布时间:</label>
			<div class="controls">
                <fmt:formatDate value="${tExitSettings.releaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*备注:</label>
			<div class="controls">
			   ${tExitSettings.detail }
			</div>
		</div>
		<div class="control-group" style="padding-left: 400px;">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="审核" onclick="auditFun()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnCancel" class="btn btn-primary"  type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
		<!-- 模态框声明 -->
	<div class="modal fade" id="auditModal" tabindex="-1" data-backdrop="static" style="width: 450px;">
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="close1"><span>&times;</span></button>
					<h4 class="modal-title">审核信息</h4>
				</div>
				<div class="modal-body"  style="height:110px;overflow: hidden;">
					<div  style="text-align: center;font-size: 18px;">
							<div id="dialog2">
							  <input name="auditRadio" id="auditYse" type="radio" value="1"/>审核通过并启用
							  <input name="auditRadio" id="auditNo"  type="radio" value="2" checked="checked"/>审核不通过

							  <textarea id="auditArea"  maxlength="500" style="width: 292px;  height: 91px;resize: none;"></textarea>
							</div>
							<p id="dialog3"></p>
					</div>
				</div>
				<div class="modal-footer">
					<p class="text-center">
						<button class="btn btn-primary" id="confirmreturn" style="margin-left: 50px;">确认</button>
						<button class="btn btn-primary" id="cancel" style="margin-right: 50px;">取消</button>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>