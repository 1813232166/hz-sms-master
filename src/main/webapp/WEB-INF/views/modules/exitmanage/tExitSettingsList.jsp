<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>退出管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$.post(ctx+'/exitmanage/tExitSettings/setExitMoneyShow',{},function(f){
				if(f){
					$("#btnSetExitMoneyDiv").show();
				}else{
					$("#btnSetExitMoneyDiv").hide();
				}
			},"json")
		});
		function setExitMoney(){
			location.href="${ctx}/exitmanage/tExitSettings/setExitMoney";
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/exitmanage/tExitSettings/">退出管理列表</a></li>
		<shiro:hasPermission name="exitmanage:tExitSettings:edit"><li><a href="${ctx}/exitmanage/tExitSettings/form">退出管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tExitSettings" action="${ctx}/exitmanage/tExitSettings/list" method="post" class="breadcrumb form-search" cssStyle="height: 66px;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ui class="ul-form">
		   <li >
		   退出日期:<input name="beginTimes" readonly="readonly" id="beginTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tExitSettings.beginTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				   <input name="endTimes" readonly="readonly" id="endTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tExitSettings.endTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  状态:<form:select path="status" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('EXIT_SETTINGS_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
			 <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" style="margin-right: 20px;"/>
			</li>
		</ui>
		<div style="float:left;padding-right:20px;display: none" id="btnSetExitMoneyDiv">
			<ul class="ul-form">
				<li style="margin-right: 550px;">
				  <input id="btnSetExitMoney" class="btn btn-primary" onclick="setExitMoney()" type="button" value="设置退出金额" />
				</li>
			</ul>
		</div>

	</form:form>
	<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 20px;">序号</th>
				<th style="width: 80px;">明天到期金额(元)</th>
				<th style="width: 40px;">到期未退出金额(元)</th>
				<th style="width: 120px;">今日可退出金额(元)</th>
				<th style="width: 70px;">今日已退出金额(元)</th>
				<th style="width: 120px;">创建时间</th>
				<th style="width: 120px;">发布时间</th>
				<th style="width: 50px;">状态</th>
				<th style="width: 120px;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tExitSettings" varStatus="status" >
			<tr>
				<td>
				${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td><!-- 明天到期金额(元) -->
					${tExitSettings.expireAmount}
				</td>
				<td><!-- 到期未退出金额(元) -->
					${tExitSettings.notExitedAmount}
				</td>
				<td><!-- 今日可退出金额(元) -->
				   ${tExitSettings.canQuitAmount}
				</td>
				<td><!-- 今日已退出金额(元) -->
					 ${tExitSettings.quitAmount}
				</td>
				<td><!-- 创建时间 -->
				  <fmt:formatDate value="${tExitSettings.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><!-- 发布时间 -->
					  <fmt:formatDate value="${tExitSettings.releaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><!-- 状态 -->
                      ${fns:getDictLabel(tExitSettings.status, 'EXIT_SETTINGS_STATUS', '')}
				</td>
		        <td>
		            <a href="${ctx}/exitmanage/tExitSettings/exitSettingsExamine?id=${tExitSettings.id}">查看</a>
		            <a href="${ctx}/exitmanage/tExitSettings/editorExitSetting?id=${tExitSettings.id}">修改</a>
					<a href="${ctx}/exitmanage/tExitSettings/delete?id=${tExitSettings.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
				</td>
			</tr>
		 </c:forEach> 
			
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>