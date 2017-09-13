<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/* 导出列表 */
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/borrow/borrowApply/exportBorrow")
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/borrow/borrowApply/borrowList");
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
		<li class="active"><a href="${ctx}/borrow/borrowApply/borrowList">借款列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/borrow/borrowApply/borrowList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>姓名：</label>
				<input name="name" id="name" value="${map.name}" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<input name="mobile" id="mobile" value="${map.mobile}" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>借款编号：</label>
				<input name="loanNumber" id="loanNumber" value="${map.loanNumber }" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>申请编号：</label>
				<input name="borrowCode" id="borrowCode" value="${map.borrowCode }" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>匹配状态：</label>
				<select name="isMatch" class="input-medium" id="isMatch">
					<option value="">全部</option>
					<option value="0" ${map.isMatch=='0'?'selected':'' }>未匹配</option>
					<option value="1" ${map.isMatch=='1'?'selected':'' }>已匹配</option>
					<%-- <option value="2" ${map.isMatch=='2'?'selected':'' }>已撤销</option> --%>
					<option value="3" ${map.isMatch=='3'?'selected':'' }>已流标</option>
				</select>
			</li>
			<li><label>编辑状态：</label>
				<select name="isEdit" class="input-medium" id="isEdit">
					<option value="">全部</option>
					<option value="0" ${map.isEdit=='0'?'selected':'' }>未编辑</option>
					<option value="1" ${map.isEdit=='1'?'selected':'' }>已编辑</option>
				</select>
			</li>
			
			
			
			<li class="btns">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			     <input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>借款编号</th>
				<th>申请编号</th>
				<th>借款人</th>
				<th>手机号</th>
				<th>借款金额</th>
				<th>借款利率</th>
				<th>借款期限（月）</th>
				<th>还款方式</th>
				<th>借款形式</th>
				<th>批货时间</th>
				<th>匹配状态</th>
				<th>编辑状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${borrow}" var="borrow">
			<tr>
				<td>
				    <a href="${ctx}/borrow/borrowApply/toBorrowDetail?id=${borrow.borrowId}">${borrow.loanNumber}</a>
				</td>
				<td>
				     ${borrow.borrowCode}
				</td>
				<td>
					${borrow.name}
				</td>
				<td>
				    ${fn:substring(borrow.mobile,0,3)}****${fn:substring(borrow.mobile,7,11)}
				</td>
				<td>
					${borrow.borrowamount}
				</td>
				<td>
					${borrow.anualrate}
				</td>
				<td>
					${borrow.deadline}
				</td>
				<td>
					${borrow.payMethod}
				</td>
				<td>
					${borrow.borrowtype}
				</td>
				<td>
					${borrow.lendingtime}
				</td>
				<td>
					${borrow.isMatch}
				</td>
				<td>
					${borrow.isEdit}
				</td>
				<td>
					<c:if test="${borrow.isMatch=='未匹配'}">
						<a href="${ctx}/borrow/borrowApply/toBorrowDetailPage?id=${borrow.borrowId}">编辑</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>