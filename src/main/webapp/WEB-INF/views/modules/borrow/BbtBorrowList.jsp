<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标的列表保存成功管理</title>
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
		<li class="active"><a href="${ctx}/borrow/BbtBorrow/">补标列表</a></li>
		<shiro:hasPermission name="borrow:tBorrow:edit"></shiro:hasPermission><li><%-- <a href="${ctx}/borrow/tBorrow/form">标的列表保存成功添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="tBorrow" action="${ctx}/borrow/BbtBorrow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>还款方式：</label>
				<form:select path="payMethod" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>借款形式：</label>
				<form:select path="borrowtype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('borrowType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="borrowstatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('BORROWSTATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>补标时间：</label>
				<input name="beginOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrow.beginOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrow.endOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>借款人：</label>
				<form:input path="userid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>标的编号：</label>
				<form:input path="borrowaliasno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>标的名称：</label>
				<form:input path="borrowalias" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>借款编号：</label>
				<form:input path="loannumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			<div style="float:right;padding-right:20px;">
				<ul>
					<li>补标数量：${countMap.count }个</li>&nbsp;&nbsp;&nbsp;<li>补标总额：${countMap.BORROWAMOUNT }元</li>
				</ul>
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
				<th>补标人</th>
				<th>补标时间</th>
				<th>补标金额</th>
				<th>借款人</th>
				<th>借款总额</th>
				<th>年利率</th>
				<th>借款期限</th>
				
				<th>还款方式</th>
				<th>借款形式</th>
				<th>状态</th>

			<%-- 	<shiro:hasPermission name="borrow:tBorrow:edit"></shiro:hasPermission><th>操作</th>
 --%>			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBorrow">
			<tr>
				<td>
					${tBorrow.borrowaliasno}
				</td>
				<td>
					<a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${tBorrow.borrowId}">
						${tBorrow.borrowalias}
					</a>
				</td>
				<td>
					${tBorrow.loannumber}
				</td>
				<td>
					${tBorrow.bBName}
				</td>
				<td>
					<fmt:formatDate value="${tBorrow.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tBorrow.investAmount}
				</td>
				<td>
					${tBorrow.userid}
				</td>
				<td>${tBorrow.borrowamount}</td>
				<td>
					${tBorrow.anualrate}
				</td>
				<td>
					${tBorrow.deadline}
				</td>
				
				<td>
					${fns:getDictLabel(tBorrow.payMethod, 'pay_method', '')}
				</td>
				<td>
					${fns:getDictLabel(tBorrow.borrowtype, 'borrowType', '')}
				</td>
				<td>
					${fns:getDictLabel(tBorrow.borrowstatus, 'BORROWSTATUS', '')}
				</td>
				<%-- <shiro:hasPermission name="borrow:tBorrow:edit"></shiro:hasPermission><td>
					<a href="${ctx}/borrow/tBorrow/delete?id=${tBorrow.id}" onclick="return confirmx('确认要删除该标的列表保存成功吗？', this.href)">撤销</a>
    				<a href="${ctx}/borrow/tBorrow/form?id=${tBorrow.id}">审核</a>
    				<a href="${ctx}/borrow/tBorrow/form?id=${tBorrow.id}">编辑</a>
				</td> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>