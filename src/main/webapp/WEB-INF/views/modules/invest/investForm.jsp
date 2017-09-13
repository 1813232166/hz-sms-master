<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借记录管理</title>
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
		<li><a href="${ctx}/invest/invest/">出借记录列表</a></li>
		<li class="active"><a href="${ctx}/invest/invest/form?id=${invest.id}">出借记录<shiro:hasPermission name="invest:invest:edit">${not empty invest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="invest:invest:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="invest" action="${ctx}/invest/invest/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标的编号：</label>
			<div class="controls">
				<%-- <form:input path="investamount" htmlEscape="false" class="input-xlarge "/> --%>
				${borrow.borrowcode}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款金额：</label>
			<div class="controls">
				${borrow.borrowamount}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款形式：</label>
			<div class="controls">
				${borrow.borrowtype}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款协议编号：</label>
			<div class="controls">
				${borrow.loannumber}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标的类型：</label>
			<div class="controls">
				普享标
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款利率：</label>
			<div class="controls">
				${borrow.anualrate}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人开户行别：</label>
			<div class="controls">
				${borrow.openBank}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人银行卡号：</label>
			<div class="controls">
				${borrow.cardno}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实收金额：</label>
			<div class="controls">
				${borrow.paidinamount}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人：</label>
			<div class="controls">
				${borrow.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人编号：</label>
			<div class="controls">
				${borrow.userid}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人身份证：</label>
			<div class="controls">
				${borrow.oriuseridcard}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">放款时间：</label>
			<div class="controls">
				${borrow.lendingtime}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款时间：</label>
			<div class="controls">
				${borrow.repaymentdate}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款期数：</label>
			<div class="controls">
				${deadline}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行别代码：</label>
			<div class="controls">
				${borrow.openBankCode}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务费：</label>
			<div class="controls">
				${borrow.servicecharge}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">渠道标识：</label>
			<div class="controls">
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款计划：</label>
			<div class="controls">
				<a href="javascript:void(0)">查看</a>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出借记录：</label>
			<div class="controls">
				<a href="javascript:void(0)">查看</a>
			</div>
		</div>
		<div class="form-actions">
			<%-- <shiro:hasPermission name="invest:invest:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>