<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<html>
<head>
	<title>散标管理</title>
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
	<form:form id="searchForm" modelAttribute="borrowManagementVo" action="${ctx}/finance/borrowmanagement" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>散标编号:</label>
				<form:input path="borrowcode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>借款编号:</label>
				<form:input path="loanNumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>状态:</label>
				<form:select path="borrowstatus" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('BORROWSTATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				 </form:select>
			</li>
			<li><label>还款方式:</label>
				<form:select path="payMethod" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				 </form:select>
			</li>
			<li><label>发布时间：</label>
				<input name="beginOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${borrowManagementVo.beginOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/> - 
				<input name="endOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${borrowManagementVo.endOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			</li>
			<li><label>出借期限:</label>
				<form:select path="deadline" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('BORROWDEADLINE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				 </form:select>
			</li>
			<li><label>借款类型:</label>
				<form:select path="borrowtype" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('borrowType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				 </form:select>
			</li>
			<li style="float: right;" class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>借款编号</th>
				<th>散标编号</th>
				<th>出借期限</th>
				<th>还款方式</th>
				<th>借款金额(元)</th>
				<th>借款利率(%)</th>
				<th>募集比例</th>
				<th>剩余金额(元)</th>
				<th>批贷时间</th>
				<th>发布时间</th>
				<th>借款类型</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="borrow" varStatus="status">
			<tr>
			    <!-- 序号 -->
				<td>
				${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<!-- 借款编号 -->
				<td>
					${borrow.loanNumber}
				</td>
				<!-- 散标编号 -->
				<td>
					${borrow.borrowcode}
				</td>
				<!-- 出借期限 -->
				<td>
					${borrow.deadline}个月
				</td>
				<!-- 还款方式-->
				<td>					
				   ${fns:getDictLabel(borrow.payMethod, 'pay_method', '')}
				</td>
				<!-- 借款金额（元） -->
				<td>
					${borrow.borrowamount}
				</td>
				<!-- 借款利率（%）-->
				<td>
					${borrow.anualrate}
				</td>
				<!-- 募集比例 -->
				<td>
					<c:choose><c:when test="${borrow.scale=='100.00' and borrow.borrowamount!=borrow.hasinvestamount}">99.99%</c:when><c:when test="${borrow.scale==0.00 and borrow.hasinvestamount!='0.00'}">0.01%</c:when><c:otherwise>${borrow.scale}%</c:otherwise></c:choose>
				</td>
				<!-- 剩余金额（元） -->
				<td>
					${borrow.residueAmount}
				</td>
				<!-- 批贷时间 -->
				<td>
					<fmt:formatDate value="${borrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 发布时间 -->
				<td>
					<fmt:formatDate value="${borrow.openborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
		       <!-- 借款类型-->
				<td>
					${fns:getDictLabel(borrow.borrowtype, 'borrowType', '')}
				</td>
		       <!-- 状态-->
				<td>
<%-- 					${fns:getDictLabel(borrow.borrowstatus, 'BORROWSTATUS', '')} --%>
					<c:if test="${borrow.borrowstatus eq 9}">
						募集中
					</c:if>
					<c:if test="${borrow.borrowstatus eq 10}">
						已满标
					</c:if>
					<c:if test="${borrow.borrowstatus eq 11}">
						还款中
					</c:if>
					<c:if test="${borrow.borrowstatus eq 16}">
						已流标
					</c:if>
					<c:if test="${borrow.borrowstatus eq 12}">
						已结束
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>