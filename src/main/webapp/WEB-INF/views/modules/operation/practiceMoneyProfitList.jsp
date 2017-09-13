<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收益发放管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	//导出所选数据excal表格
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/operation/practiceMoneyProfit/exportPracticeMoneyProfit");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/operation/practiceMoneyProfit/");
		$("#searchForm").submit();
       	return false;
       }
	//补发弹框
	function go(id) {
		$("#id").val(id);
		$('#myModal').modal('show');
	};
	//确定补发
	function submit() {
		var id=$("#id").val();
		$.ajax({
			type : "post",
			url : "${ctx}/operation/practiceMoneyProfit/provideUpdates",
			data : {
				"profitid" : id
			},
			success : function(data) {
				if(data){
					alert("修改成功");
					window.location.reload();  
				}else{
					alert("修改失败");
				}
			}
		});
	};
	//取消补发
	function canlessDetail() {
		var id=$("#id").val();
		location.href = "${ctx}/operation/practiceMoneyProfit/getProfitParticulars?identify=1&profitid="+id;
	};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/operation/practiceMoneyProfit/">收益发放列表</a></li>
		<%-- <shiro:hasPermission name="operation:practiceMoneyProfit:edit"><li><a href="${ctx}/operation/practiceMoneyProfit/form">收益发放添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="activityPracticeMoneyProfit" action="${ctx}/operation/practiceMoneyProfit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>发放状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('GiveStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>收益时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${activityPracticeMoneyProfit.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${activityPracticeMoneyProfit.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns" ><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
			<div style="float: right; padding-right: 20px;">
				<li>发放总数：${totalmoneyCounts.totalMoneys}元</li>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>收益时间</th>
				<th>金额（元）</th>
				<th>发放状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="practiceMoneyProfit">
			<tr>
				<td><fmt:formatDate value="${practiceMoneyProfit.createDate}" pattern="yyyy-MM-dd"/></td>
				<td>${practiceMoneyProfit.totalmoney}</td>
				<td>${practiceMoneyProfit.status}</td>
				<%-- <shiro:hasPermission name="operation:practiceMoneyProfit:edit"> --%><td>
    				<c:if test="${practiceMoneyProfit.status=='失败'}">
						<a href="#" onclick="go('${practiceMoneyProfit.id}');">补发</a>
					</c:if>
					<c:if test="${practiceMoneyProfit.status=='待发'}">
						<a href="#" onclick="go('${practiceMoneyProfit.id}');">发放</a>
					</c:if>
					<a style="float: right;" href="${ctx}/operation/practiceMoneyProfit/getProfitParticulars?profitid=${practiceMoneyProfit.id}">明细</a>
				</td><%-- </shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		<!-- 补发弹窗 	 -->
	<div style="width: 400px;" class="modal fade" id="myModal" tabindex="-1"
		data-backdrop="static" keyboard="true">
		<div class="modal-dialog">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 style="text-align: center;">发放</h4>
				</div>
				<div>
					<form>
						<input type='hidden' id="id" />
						<div align="center">
							<table >
								</br>
								<tr>确定发放111人，222收益？</tr>
							</table>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal" onclick="canlessDetail();">返回</button>
					<button class="btn btn-primary" data-dismiss="modal" onclick="submit();">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>