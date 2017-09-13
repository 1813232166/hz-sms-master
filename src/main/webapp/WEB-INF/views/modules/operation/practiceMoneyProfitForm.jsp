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
						$("#searchForm").attr("action","${ctx}/operation/");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			var profitid='${practiceMoney.profitid}';
			var identify='${particularsCounts.identify}';
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#profitid").val(profitid);
			$("#identify").val(identify);
			$("#searchForm").attr("action","${ctx}/operation/practiceMoneyProfit/getProfitParticulars");
			$("#searchForm").submit();
	    	return false;
	    }
		
		//单个点击补发
		function provideSubmit(id) {
			$.ajax({
				type : "post",
				url : "${ctx}/operation/practiceMoneyProfit/provideUpdate",
				data : {
					"id" : id,
				},
				success : function(data) {
					/* if(data){
						alert("修改成功"); */
						window.location.reload();  
					/* }else{
						alert("修改失败");
					} */
				}
			});
		};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/operation/practiceMoneyProfit">收益发放-明细</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="practiceMoney" action="${ctx}/operation/practiceMoneyProfit/getProfitParticulars" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="profitid" name="profitid" type="hidden" value="${practiceMoney.profitid}"/>
		<input id="identify" name="identify" type="hidden" value="${particularsCounts.identify}"/>
		<ul class="ul-form">
			<li><label>发放状态：</label>
				<form:select path="profitstatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('GiveStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>姓名：</label>
				<form:input  style="width: 149px" path="realName" htmlEscape="false" maxlength="32" class="input-medium" placeholder="请输入姓名"/>
			</li>
			<li><label>手机号：</label>
				<form:input style="width: 149px" path="mobile" htmlEscape="false" maxlength="32" class="input-medium" placeholder="请输入手机号"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"  onclick="return page();"/></li>
			<li class="btns" ><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
			<div style="float: right; padding-right: 20px;">
				<li>收益总数：${particularsCounts.profitAmounts}元</li>
				<li style="margin-left: 12px">收益人数：${particularsCounts.userCounts}人</li>
				<li style="margin-left: 12px">收益人次：${particularsCounts.counts}人次</li>
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
				<th style="text-align: center;">用户收益</th>
				<th style="text-align: center;">收益时间</th>
				<th style="text-align: center;">发放状态</th>
				<c:if test="${particularsCounts.identify=='1'}">
					<th style="text-align: center;">操作</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="profitParticulars">
			<tr>
				<td style="text-align: center;">${profitParticulars.realName}</td>
				<td style="text-align: center;">${profitParticulars.mobile}</td>
				<td style="text-align: center;">${profitParticulars.usedamount}</td>
				<td style="text-align: center;">${profitParticulars.profitamount}</td>
				<td style="text-align: center;"><fmt:formatDate value="${profitParticulars.profitdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td style="text-align: center;">${profitParticulars.profitstatus}</td>
				<c:if test="${particularsCounts.identify=='1'}">
					<td style="text-align: center;">
						<c:if test="${profitParticulars.profitstatus=='失败'}">
							<a href="#" onclick="provideSubmit('${profitParticulars.id}');">补发</a>
						</c:if>
						<c:if test="${profitParticulars.profitstatus=='待发'}">
							<a href="#" onclick="provideSubmit('${profitParticulars.id}');">发放</a>
						</c:if>
					</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>