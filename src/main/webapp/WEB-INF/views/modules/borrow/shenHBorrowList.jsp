<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>普享表审核未通过列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/borrow/shenHBorrow/">未通过列表</a></li>
		<%-- <shiro:hasPermission name="borrow:shenHBorrow:edit"><li><a href="${ctx}/borrow/shenHBorrow/form">普享表审核未通过列表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="shenHBorrow" action="${ctx}/borrow/shenHBorrow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>还款方式<!-- (debx等额本息 xxhb先息后本ychbx一次性还本付息) -->：</label>
				<form:select path="payMethod" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>借款形式<!-- (xy信用借款fd房贷借款cd车贷借款) -->：</label>
				<form:select path="borrowtype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('borrowType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>发布时间：</label>
				<input name="beginOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${shenHBorrow.beginOpenborrowdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${shenHBorrow.endOpenborrowdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns" style="margin-left: 110px"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li><label>借款人：</label>
				<form:input path="borrowUserName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>标的编号：</label>
				<form:input path="borrowcode" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			
			<li><label>标的名称：</label>
				<form:input path="borrowalias" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>借款编号：</label>
				<form:input path="loannumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确定"/></li>
			<li class="clearfix"></li>
			<div style="float:right;padding-right:20px;">
				<li>借款数量：${shborrowCounts.counts }个</li><li style="margin-left: 12px">借款人：${shborrowCounts.userCounts }人</li><li style="margin-left: 12px">借款总额：${shborrowCounts.amountCounts }元</li>
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
				<th>出借年利率</th>
				<th>出借期限</th>
				<th>还款方式</th>
				<th>借款形式</th>
				<th>审核时间</th>
				<th>状态</th>
				<%-- <shiro:hasPermission name="borrow:borrow:edit"> --%><th>操作</th><%-- </shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shenHBorrow">
			<tr>
				<td><a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${shenHBorrow.borrowId}">
					${shenHBorrow.borrowcode}
				</a></td>
				<td>
					${shenHBorrow.borrowalias}
				</td>
				<td>
					${shenHBorrow.loannumber}
				</td>
				<td>
					${shenHBorrow.borrowUserName}
				</td>
				<td>
					${shenHBorrow.borrowamount}
				</td>
				<td>
					${shenHBorrow.anualrate}
				</td>
				<td>
					${shenHBorrow.deadline}
				</td>
				<td>
					${fns:getDictLabel(shenHBorrow.payMethod, 'pay_method', '')}
				</td>
				<td>
					${fns:getDictLabel(shenHBorrow.borrowtype, 'borrowType', '')}
				</td>
				<td>
					<fmt:formatDate value="${shenHBorrow.auditdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(shenHBorrow.borrowstatus, 'BORROWSTATUS', '')}
				</td>
		
				<%-- <shiro:hasPermission name="borrow:shenHBorrow:edit"> --%><td>
					<a href="javascript:void(0)" onclick="return confirmx('${shenHBorrow.auditSuggest}', this.href)">原因</a>
					<a href="${ctx}/borrow/borrow/delete?borrowId=${shenHBorrow.borrowId}" onclick="return confirmx('确认要删除该普享表申请列表吗？', this.href)">撤销</a> 
    				<a href="${ctx}/borrow/tBorrow/shenheform?id=${shenHBorrow.borrowId}">审核</a>
				</td><%-- </shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>