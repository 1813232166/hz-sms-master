<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>普享表审核未通过列表管理</title>
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
		<li><a href="${ctx}/borrow/shenHBorrow/">普享表审核未通过列表列表</a></li>
		<li class="active"><a href="${ctx}/borrow/shenHBorrow/form?borrowId=${shenHBorrow.borrowId}">普享表审核未通过列表<shiro:hasPermission name="borrow:shenHBorrow:edit">${not empty shenHBorrow.borrowId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="borrow:shenHBorrow:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shenHBorrow" action="${ctx}/borrow/shenHBorrow/save" method="post" class="form-horizontal">
		<form:hidden path="borrowId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">满标状态：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">borrow_id：</label>
			<div class="controls">
				<form:input path="borrowId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">1信用标2抵押标：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款标题：</label>
			<div class="controls">
				<form:input path="borrowtitle" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款方式(debx等额本息，ycx一次性还款，dqhb到期还本)：</label>
			<div class="controls">
				<form:input path="borrowway" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标的编号：</label>
			<div class="controls">
				<form:input path="borrowcode" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款总额：</label>
			<div class="controls">
				<form:input path="borrowamount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款期限：</label>
			<div class="controls">
				<form:input path="deadline" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年利率：</label>
			<div class="controls">
				<form:input path="anualrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最小投资金额：</label>
			<div class="controls">
				<form:input path="mintendersum" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款方式(debx等额本息 xxhb先息后本ychbx一次性还本付息)：</label>
			<div class="controls">
				<form:select path="payMethod" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最大投资金额：</label>
			<div class="controls">
				<form:input path="maxtendersum" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">筹标期限(募集期)：</label>
			<div class="controls">
				<form:input path="raiseterm" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款形式(xy信用借款fd房贷借款cd车贷借款)：</label>
			<div class="controls">
				<form:select path="borrowtype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('borrowType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态(0未匹配1预发布;2初审成功;3初审失败;4招标中;5待复审;6复审失败;7还款中;8已还清;9已流标;10逾期;11已经满标)：</label>
			<div class="controls">
				<form:select path="borrowstatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('BORROWSTATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">筹标期限单位（0小时 1天）：</label>
			<div class="controls">
				<form:input path="raisetermunit" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款详情：</label>
			<div class="controls">
				<form:input path="detail" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开标时间：</label>
			<div class="controls">
				<input name="openborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shenHBorrow.openborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人：</label>
			<div class="controls">
				<form:input path="userid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款来源：</label>
			<div class="controls">
				<form:input path="repaysource" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标的中文别名编号：</label>
			<div class="controls">
				<form:input path="borrowaliasno" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标的中文别名：</label>
			<div class="controls">
				<form:input path="borrowalias" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ip：</label>
			<div class="controls">
				<form:input path="ip" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款编号：</label>
			<div class="controls">
				<form:input path="loannumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原始借款人：</label>
			<div class="controls">
				<form:input path="oriuserid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原借款人身份证：</label>
			<div class="controls">
				<form:input path="oriuseridcard" htmlEscape="false" maxlength="18" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核时间：</label>
			<div class="controls">
				<input name="auditdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shenHBorrow.auditdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已投总额：</label>
			<div class="controls">
				<form:input path="hasinvestamount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已投数量：</label>
			<div class="controls">
				<form:input path="hasinvestnum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shenHBorrow.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务费率：</label>
			<div class="controls">
				<form:input path="servicefree" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">散标满标时间：</label>
			<div class="controls">
				<input name="fullBorrowDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shenHBorrow.fullBorrowDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同签订时间：</label>
			<div class="controls">
				<input name="contractsigningtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shenHBorrow.contractsigningtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同编号：</label>
			<div class="controls">
				<form:input path="contractcode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品编号：</label>
			<div class="controls">
				<form:input path="productcode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款日：</label>
			<div class="controls">
				<form:input path="repaymentdate" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月利息：</label>
			<div class="controls">
				<form:input path="monthinterest" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月本金：</label>
			<div class="controls">
				<form:input path="monthcapital" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月还款额：</label>
			<div class="controls">
				<form:input path="monthprepaymentamount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到手金额：</label>
			<div class="controls">
				<form:input path="giveamount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">月管理费率：</label>
			<div class="controls">
				<form:input path="monthmanagementfee" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务费：</label>
			<div class="controls">
				<form:input path="servicecharge" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户渠道：</label>
			<div class="controls">
				<form:input path="businesschannel" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">营业部编码：</label>
			<div class="controls">
				<form:input path="bcbizdeptid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保障方式(dbgs担保金保障fxj风险金保障no不保障)：</label>
			<div class="controls">
				<form:input path="bztype" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批贷时间：</label>
			<div class="controls">
				<input name="loantime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shenHBorrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">散标包ID：</label>
			<div class="controls">
				<form:input path="borrowfid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急状态2不紧急1紧急：</label>
			<div class="controls">
				<form:input path="criticalid" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险提示：</label>
			<div class="controls">
				<form:input path="warnings" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实收金额：</label>
			<div class="controls">
				<form:input path="paidinamount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款用途：</label>
			<div class="controls">
				<form:input path="usageofloan" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信用评级：</label>
			<div class="controls">
				<form:input path="creditrating" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号码：</label>
			<div class="controls">
				<form:input path="idcardno" htmlEscape="false" maxlength="18" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别(0女,1男)：</label>
			<div class="controls">
				<form:input path="sex" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年龄：</label>
			<div class="controls">
				<form:input path="age" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在城市：</label>
			<div class="controls">
				<form:input path="city" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">年收入：</label>
			<div class="controls">
				<form:input path="annualincome" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资产负债情况：</label>
			<div class="controls">
				<form:input path="assetsliabilities" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">编辑状态(0未编辑1已编辑)：</label>
			<div class="controls">
				<form:input path="isEdit" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否匹配(0是未匹配1是匹配)：</label>
			<div class="controls">
				<form:input path="isMatch" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款名称：</label>
			<div class="controls">
				<form:input path="loanName" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">放款时间：</label>
			<div class="controls">
				<input name="lendingtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${shenHBorrow.lendingtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人客户编号：</label>
			<div class="controls">
				<form:input path="borrowCustomerCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人金账户号：</label>
			<div class="controls">
				<form:input path="borrowAccCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行别代码：</label>
			<div class="controls">
				<form:input path="openBankCode" htmlEscape="false" maxlength="12" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行账号：</label>
			<div class="controls">
				<form:input path="cardno" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信用评级：</label>
			<div class="controls">
				<form:input path="creditlevel" htmlEscape="false" maxlength="12" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否完成(0未完成,1完成)：</label>
			<div class="controls">
				<form:input path="isFinish" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行：</label>
			<div class="controls">
				<form:input path="openBank" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否到期补标(0不补 1补标)：</label>
			<div class="controls">
				<form:input path="fillMark" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险提示：</label>
			<div class="controls">
				<form:input path="riskWarning" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款人应还款金额：</label>
			<div class="controls">
				<form:input path="remainamount" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="borrow:shenHBorrow:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>