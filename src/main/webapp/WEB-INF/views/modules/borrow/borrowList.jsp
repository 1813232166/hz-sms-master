<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<html>
<head>
	<title>普享表申请列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/borrow/borrow/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/borrow/borrow");
						
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
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
		<li class="active"><a href="${ctx}/borrow/borrow/">待审核产品</a></li>
		<%-- <shiro:hasPermission name="borrow:borrow:edit"><li><a href="${ctx}/borrow/borrow/form">普享表申请列表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="borrow" action="${ctx}/borrow/borrow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>还款方式：</label>
				<form:select path="payMethod" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>借款人：</label>
				<form:input path="borrowUserName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>标的编号：</label>
				<form:input path="borrowcode" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>借款编号：</label>
				<form:input path="loannumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns" style="float: right;"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
<%-- 			<li><label>借款形式：</label>
				<form:select path="borrowtype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('borrowType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
 --%>			<li><label>标的名称：</label>
				<form:input path="borrowalias" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>发布时间：</label>
				<input name="beginOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${borrow.beginOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${borrow.endOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li style="float: right;" class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			<div style="float:right;padding-right:20px;">
				<li>借款数量：${borrowCounts.counts }个</li><li style="margin-left: 12px">借款人：${borrowCounts.userCounts }人</li><li style="margin-left: 12px">借款总额：<fmt:formatNumber type="number" value="${borrowCounts.amountCounts }" />元</li>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款编号</th>
				<th>借款人</th>
				<th>借款金额(元)</th>
				<th>出借年化利率</th>
				<th>出借期限</th>
				<th>还款方式</th>
				<th>创建时间</th>
				<th>紧急状态</th>
				<th>状态</th>
				<%-- <shiro:hasPermission name="borrow:borrow:edit"> --%><th>操作</th><%-- </shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="borrow">
			<tr>
				<td><a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${borrow.borrowId}">
					${borrow.borrowaliasno}
				</a></td>
				<td>
					${borrow.borrowalias}
				</td>
				<td>
					${borrow.loannumber}
				</td>
				<td>
					${borrow.borrowUserName}
				</td>
				<td>					
					<fmt:formatNumber type="number" value="${borrow.borrowamount}" />
				</td>
				<td>
					${borrow.anualrate}
				</td>
				<td>
					${borrow.deadline}
				</td>
				<td>
					${fns:getDictLabel(borrow.payMethod, 'pay_method', '')}
				</td>
				<td>
					<fmt:formatDate value="${borrow.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${borrow.criticalid=='1'}">紧急</c:if>
					<c:if test="${borrow.criticalid=='2'}">不紧急</c:if>
				</td>
				<td>
					${fns:getDictLabel(borrow.borrowstatus, 'BORROWSTATUS', '')}
				</td>
		
				<%-- <shiro:hasPermission name="borrow:borrow:edit"> --%><td>
				    <c:if test="${borrow.borrowstatus=='12'}">流标中</c:if>
				    <c:if test="${borrow.borrowstatus=='0' or borrow.borrowstatus=='3' or borrow.borrowstatus=='4' or borrow.borrowstatus=='13' or borrow.borrowstatus=='21' or borrow.borrowstatus=='14' or borrow.borrowstatus=='15' or borrow.borrowstatus=='16' or borrow.borrowstatus=='17' or borrow.borrowstatus=='18' or borrow.borrowstatus=='19' or borrow.borrowstatus=='20'}"><a href="${ctx}/borrow/tBorrow/delete?borrowId=${borrow.borrowId}&borrowtype=2" onclick="return confirmx('您确定撤销${borrow.borrowalias}的募集？', this.href)">撤销</a></c:if>
					 
    				<a href="${ctx}/borrow/tBorrow/shenheform?id=${borrow.borrowId}">审核</a>
<%--     				<a href="${ctx}/borrow/convert/toEdit?borrowIds=${borrow.borrowcode}">编辑</a>
					<a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${borrow.borrowId}">详情</a> --%>
				</td><%-- </shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>