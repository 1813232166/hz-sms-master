<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划交易管理</title>
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
		<li><a href="${ctx}/finance/financeTransaction/">出借计划交易列表</a></li>
		<li class="active">
			<a href="${ctx}/finance/financeTransaction/form?id=${financeTransaction.id}">出借计划交易
<%-- 			<shiro:hasPermission name="financetransaction:financeTransaction:edit">${not empty financeTransaction.id?'修改':'添加'}</shiro:hasPermission> --%>
<%-- 			<shiro:lacksPermission name="financetransaction:financeTransaction:edit">查看</shiro:lacksPermission> --%>
			</a>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="financeTransaction" action="${ctx}/finance/financeTransaction/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">资金类型  1:原始资金 ;999:复投出借;：</label>
			<div class="controls">
				<form:input path="capitalType" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资金类别  1：出借计划 2 散标集：</label>
			<div class="controls">
				<form:input path="capitalCategory" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资金状态  1:待匹配;2:匹配中;3:已匹配;4:已退出;5:部分出借失败;6:出借失败;：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="2" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原始出借总金额：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">剩余金额：</label>
			<div class="controls">
				<form:input path="surplusAmount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">匹配金额：</label>
			<div class="controls">
				<form:input path="matchedAmount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利率 （利率统一保存规则例：12.6%  则保存：12.6）：</label>
			<div class="controls">
				<form:input path="rate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">期数：</label>
			<div class="controls">
				<form:input path="nper" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户级别  1:老用户;0:新用户：</label>
			<div class="controls">
				<form:input path="customer" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否加急  1:加急;0:不加急：</label>
			<div class="controls">
				<form:input path="urgent" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否置顶  1:置顶;0:不置顶：</label>
			<div class="controls">
				<form:input path="stickTop" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出借时间：</label>
			<div class="controls">
				<input name="investTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${financeTransaction.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">匹配成功时间：</label>
			<div class="controls">
				<input name="matchedTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${financeTransaction.matchedTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">冻结资金时间：</label>
			<div class="controls">
				<input name="freezeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${financeTransaction.freezeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">解冻资金时间：</label>
			<div class="controls">
				<input name="thawTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${financeTransaction.thawTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资金来源  新资金:0;回款资金:父资金id：</label>
			<div class="controls">
				<form:input path="sourceCapitalId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">自定义权重系数：</label>
			<div class="controls">
				<form:input path="customWeight" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权重值：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资金来源：0PC1APP2M站：</label>
			<div class="controls">
				<form:input path="capitalSource" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资金产品：</label>
			<div class="controls">
				<form:input path="capitalProduct" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预计收益：</label>
			<div class="controls">
				<form:input path="projectedEarnings" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">撮合笔数：</label>
			<div class="controls">
				<form:input path="matchingPenNumber" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资金编号：</label>
			<div class="controls">
				<form:input path="capitalCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出借人ID：</label>
			<div class="controls">
				<sys:treeselect id="userId" name="userId" value="${financeTransaction.userId}" labelName="" labelValue="${financeTransaction.}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期应退出日期：</label>
			<div class="controls">
				<input name="backEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${financeTransaction.backEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计息日：</label>
			<div class="controls">
				<input name="interestDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${financeTransaction.interestDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出借产品Id（出借计划or散标集）：</label>
			<div class="controls">
				<form:input path="financeId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实退出时间：</label>
			<div class="controls">
				<input name="quitTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${financeTransaction.quitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
<%-- 			<shiro:hasPermission name="financetransaction:financeTransaction:edit"> --%>
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
<%-- 			</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>