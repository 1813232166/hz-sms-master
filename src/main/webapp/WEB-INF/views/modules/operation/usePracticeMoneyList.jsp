<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>使用列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		//导出所选数据excal表格
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/operation/usePracticeMoney/exportPracticeMoneyFile");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/operation/usePracticeMoney/");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/operation/usePracticeMoney/">使用列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="usePracticeMoney" action="${ctx}/operation/usePracticeMoney/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>使用渠道：</label>
				<form:select path="sourcechannel" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('JiFenLaiYuan')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li ><label>使用时间：</label>
				<input name="beginUseddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${usePracticeMoney.beginUseddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUseddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${usePracticeMoney.endUseddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>姓名：</label>
				<form:input  style="width: 149px" path="realName" htmlEscape="false" maxlength="32" class="input-medium" placeholder="请输入姓名"/>
			</li>
			<li class="btns" ><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li><label>收益发放：</label><!-- 状态 -->
				<form:select path="profitstatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('GiveStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>收益时间：</label>
				<input name="beginProfitdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${usePracticeMoney.beginProfitdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endProfitdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${usePracticeMoney.endProfitdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>手机号：</label>
				<form:input style="width: 149px" path="mobile" htmlEscape="false" maxlength="32" class="input-medium" placeholder="请输入手机号"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"  onclick="return page();"/></li>
			<li class="clearfix"></li>
			<div style="float: right; padding-right: 20px;">
				<li>使用总数：${usePracticeMoneys.giftamounts }元</li>
				<li style="margin-left: 12px">使用人数：${usePracticeMoneys.userCounts }人</li>
				<li style="margin-left: 12px">使用人次：${usePracticeMoneys.counts }人次</li>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">姓名</th>
				<th style="text-align: center;">手机号</th>
				<th style="text-align: center;">使用金额（元）</th>
				<th style="text-align: center;">使用时间</th>
				<th style="text-align: center;">使用渠道</th>
				<th style="text-align: center;">用户收益</th>
				<th style="text-align: center;">收益时间</th>
				<th style="text-align: center;">收益发放</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="usePracticeMoney">
			<tr>
				<td style="text-align: center;">${usePracticeMoney.realName}</td>
				<td style="text-align: center;">${usePracticeMoney.mobile}</td>
				<td style="text-align: center;">${usePracticeMoney.usedamount}</td>
				<td style="text-align: center;"><fmt:formatDate value="${usePracticeMoney.useddate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td style="text-align: center;">${usePracticeMoney.sourcechannel}</td>
				<td style="text-align: center;">${usePracticeMoney.profitamount}</td>
				<td style="text-align: center;"><fmt:formatDate value="${usePracticeMoney.profitdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td style="text-align: center;">${usePracticeMoney.profitstatus}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>