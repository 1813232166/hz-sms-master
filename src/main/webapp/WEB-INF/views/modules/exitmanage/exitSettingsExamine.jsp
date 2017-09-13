<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>退出审核查看</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#auditModal").hide();
		    $("#btnSubmitexport").click(function(){
		        top.$.jBox.confirm("确认要导出吗？","系统提示",function(v,h,f){
		          if(v=="ok"){
		            $("#searchForm").attr("action","${ctx}/exitmanage/tExitSettings/exportExitData");
		            $("#searchForm").submit();
		            $("#searchForm").attr("action","${ctx}/exitmanage/tExitSettings/exitSettingsExamine");
		          }
		        },{buttonsFocus:1});
		        top.$('.jbox-body .jbox-icon').css('top','55px');
		      });
			//提交审核
			$("#submitAudit").click(function(){
				var id='${tExitSettings.id}';
				$.post(ctx+'/exitmanage/tExitSettings/submitAudit',{id:id},function(f){
					if(f){
						alert("提交审核成功");
						location.href="${ctx}/exitmanage/tExitSettings/list";
					}else{
						alert("提交审核失败");
					}
				},"json")
			});
		});

  function reback(){
	  location.href="${ctx}/exitmanage/tExitSettings/list";
  }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exitmanage/tExitSettings/">退出审列表</a></li>
		<li class="active"><a href="${ctx}/exitmanage/tExitSettings/form?id=${tExitSettings.id}">退出管理<shiro:hasPermission name="exitmanage:tExitSettings:edit">${not empty tExitSettings.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="exitmanage:tExitSettings:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tExitSettings"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*明日到期金额:</label>
			<div class="controls">
			${findList[0].expireAmount}元
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*到期未退出金额:</label>
			<div class="controls">
			    ${findList[0].notExitedAmount}元
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*退出金额:</label>
			<div class="controls">
			    ${findList[0].quitAmount}元
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*创建时间:</label>
			<div class="controls">
			    <fmt:formatDate value="${findList[0].createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*发布时间:</label>
			<div class="controls">
			    <fmt:formatDate value="${findList[0].releaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
		</div>
		<div class="control-group" style="padding-left: 300px;">
			<label class="control-label">*备注:</label>
			<div class="controls">
			    ${findList[0].detail }
			</div>
		</div>
	</form:form>
	<form:form id="searchForm" modelAttribute="tExitSettings" action="${ctx}/exitmanage/tExitSettings/exitSettingsExamine" method="post" class="breadcrumb form-search" cssStyle="height: 32px;">
		<input id="pageNo" name="pageNo" type="hidden" value="${tExitDataPage.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${tExitDataPage.pageSize}"/>
		<ui class="ul-form">
		   <li >
		   退出日期:<input name="beginTimes" readonly="readonly" id="beginTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tExitData.beginTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				   <input name="endTimes" readonly="readonly" id="endTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tExitData.endTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" style="margin-right: 20px;"/>
			 <input id="btnSubmitexport" class="btn btn-primary" type="buttion" value="导出" style="margin-right: 20px;width: 28px;"/>
			</li>
		</ui>

	</form:form>
	<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 20px;">序号</th>
				<th style="width: 80px;">可退出金额(元)</th>
				<th style="width: 40px;">已退出金额(元)</th>
				<th style="width: 120px;">未退出金额(元)</th>
				<th style="width: 70px;">退出时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${tExitDataPage.list}" var="tExitData" varStatus="status" >
			<tr>
				<td>
				${status.count+tExitDataPage.pageSize*(tExitDataPage.pageNo-1)}
				</td>
				<td><!-- 可退出金额(元) -->
					${tExitData.canQuitAmount}
				</td>
				<td><!-- 已退出金额(元) -->
					${tExitData.quitAmount}
				</td>
				<td><!-- 未退出金额(元) -->
				    ${tExitData.canQuitAmount - tExitData.quitAmount}
				</td>
				<td><!-- 退出时间 -->
				   <fmt:formatDate value="${tExitData.quitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		 </c:forEach> 
			
		</tbody>
	</table>
	*已退出总金额：${quitAmountSum }
	<div class="pagination">${tExitDataPage}</div>
		<div class="control-group" style="padding-left: 400px;">
			<input id="submitAudit" class="btn btn-primary" type="button" value="提交审核" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnCancel" class="btn btn-primary"  type="button" value="返 回" onclick="reback()"/>
		</div>
</body>
</html>