<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新建列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		
		function page(n,s){
			alert(1);
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function shangyiye(){
			window.location.href="${ctx}/borrow/tBorrow"
		}
	</script>
	<script type="text/javascript">
		var ishidden = "${ishidden}"
	</script>
	<script src="${ctxStatic}/borrow/borrow.js" type="text/javascript"></script>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:void(0)">新建列表</a></li>
	</ul>
	<label style="font-weight: bolder;">普享标配置</label>
	<form:form id="searchForm" modelAttribute="tBorrow" action="${ctx}/borrow/tBorrow/newBorrow" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="ishidden" name="ishidden" type="hidden" value="0"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
<%-- 			<li><label>保障方式：</label>
				<form:select id="bztype" path="bztype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('bz_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
<%-- 			<li><label>借款形式：</label>
				<form:select id="borrowtype" path="borrowtype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('borrowType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>借款期限：</label>
				<form:select id="deadline" path="deadline" class="input-medium">
				<form:option value="">全部</form:option>
				<form:option value="-1,3">3个月以下</form:option>
				<form:option value="3,6">3-6个月</form:option>
				<form:option value="6,9">6-9个月</form:option>
				<form:option value="9,12">9-12个月</form:option>
				<form:option value="12,18">12-18个月</form:option>
				<form:option value="18,24">18-24个月</form:option>
				<form:option value="24">24个月以上</form:option>
				</form:select>
			</li>
			<li><label>借款编号：</label>
			<form:input id="loannumber" path="loannumber" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>借款人：</label>
			<form:input id="userName" path="userName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
<%-- 			<li><label>还款日：</label>
				<form:select id="repaymentdate" path="repaymentdate" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('repaymentdate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
 --%>		<%-- 	<li><label>债权来源：</label>
				<form:select path="" class="input-medium">
					<form:option value="" label="借款"/>
				</form:select>
			</li>
 --%>			<li><label>还款方式：</label>
				<form:select id="payMethod" path="payMethod" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>紧急状态：</label>
				<form:select id="criticalid" path="criticalid" class="input-medium">
					<form:option value="" label="全部"/>
					<form:option value="2" label="不紧急"/>
					<form:option value="1" label="紧急"/>
				</form:select>
			</li>
			<li><label>借款金额：</label>
				<form:input id="beginBorrowamount" path="beginBorrowamount" htmlEscape="false" maxlength="50" class="input-medium"/>元&nbsp;&nbsp;至
				<form:input id="endBorrowamount" path="endBorrowamount" htmlEscape="false" maxlength="50" class="input-medium"/>元
			</li>
			<li style="margin-left: 14.5px;"><label>借款利率：</label>
				<form:input id="beginAnualrate" path="beginAnualrate" htmlEscape="false" maxlength="50" class="input-medium"/>%&nbsp;&nbsp;至
				<form:input id="endAnualrate" path="endAnualrate" htmlEscape="false" maxlength="50" class="input-medium"/>%
			</li>
			<li><label>批贷时间：</label>
				<input id="beginLoantime" name="beginLoantime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrow.beginLoantime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input id="endLoantime" name="endLoantime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrow.endLoantime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			
			<li class="clearfix"></li>
		</ul>
		<ul class="ul-form">
				<li class="btns" style="margin-left: 480px;"><input id="btnSubmitt" class="btn btn-primary" onclick="submit();"   value="筛  选"/></li>
		</ul>
		<%-- <form:input id="ishidden" type = "hidden" name = "isHidden" value = "0" />元 --%>
		
	</form:form>
	<sys:message content="${message}"/>
	<label style="font-weight: bolder;">债权列表</label>
	<label id="countMount" style="float: right;">借款总额：<fmt:formatNumber type="number"  maxFractionDigits="3" value="${countMount}" />元</label>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="display:none"><input  type="checkbox" id="qx" onclick="quanxuan()"/>全选</th>
				<th id="xuhao">序号</th>
				<th>借款编号</th>
				<th>借款期限</th>
				<th>借款利率</th>
				<th>借款金额</th>
				<th>借款人</th>
				<!-- <th>还款日</th>
				<th>保障方式</th> -->
				<th>还款方式</th>
				<!-- <th>借款形式</th> -->
				<!-- <th>债权来源</th> -->
				<th>紧急状态</th>
				<th>批货时间</th>
			</tr>
		</thead>
		<tbody id="list">
		<c:forEach items="${page.list}" var="tBorrow" varStatus="s">
			<tr>
				<td style="display:none">
					<input  type="checkbox" value="${tBorrow.borrowcode}" name="chk_list" />
				</td>
				<td class="xuhaos">
					${s.count}
				</td>
				<td>
					${tBorrow.loannumber}
				</td>
				<td>
					${tBorrow.deadline}
				</td>
				<td>
					${tBorrow.anualrate}
				</td>
				<td>
					${tBorrow.borrowamount}
				</td>
				<td>
					${tBorrow.userName}
				</td>
				
<%-- 				<td>
					${tBorrow.repaymentdate}
				</td> --%>
<%-- 				<td>
					${tBorrow.bztype}
					${fns:getDictLabel(tBorrow.bztype, 'bz_type', '')}
				</td> --%>
				
				<td>
					${fns:getDictLabel(tBorrow.payMethod, 'pay_method', '')}
				</td>
<%-- 				<td>
					${fns:getDictLabel(tBorrow.borrowtype, 'borrowType', '')}
				</td>
				<td>
				借款
				</td> --%>
				<td>
					<%-- ${tBorrow.criticalid} --%>
					 <c:choose>
			           <c:when test="${'2' eq tBorrow.criticalid}">不紧急</c:when>
			           <c:when test="${'1' eq tBorrow.criticalid}">紧急</c:when>
			           <c:otherwise>--</c:otherwise>
			        </c:choose>
					<%-- ${fns:getDictLabel(tBorrow.criticalid, 'criticalId', '')} --%>
				</td>
				<td align="center">
					<fmt:formatDate value="${tBorrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination"><%-- ${page} --%>
		<input type="button" class="btn btn-primary" value="下一步" onclick="next()" style="margin-left: 500px;"/>
		<input style="float: right; margin-right: 50px;" id="btnCancel" class="btn" type="button" value="返 回" onclick="shangyiye()"/>
	</div>
</body>

</html>