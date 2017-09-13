<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划还款列表</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#exportExcel").click(function(){
				
				location.href=ctx+"/repayment/repaymentManage/exportLendList";
			});
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function chakanLend(id){
			location.href="${ctx}/repayment/repaymentManage/toLendDetail?id="+id;
		}
	</script>
</head>
<body>
      <ul class="nav nav-tabs">
		<li class="active"><a href="#">出借信息</a></li>
	 </ul>
	<form id="searchForm"  action="${ctx}/repayment/repaymentManage/toLendList" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li style="list-style: none">
				<label>状态：</label>
				<select name="type">
					<option value="">还款中</option>
					<option value="">已结清</option>
					<option value="">已逾期</option>
					<option value="">已垫付</option>
					<option value="">提前还款</option>
				</select>
				<label>标的编号：</label><input type="text" name="id" class="input-medium">
			    <label>标的名称：</label><input type="text" name="productName" class="input-medium">
			    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
		</ul>
	</form>
	<!-- 导出excel-->
	<div style="float:right;padding-right:20px;">
		<form id=""   method="post" action="" >
		     <span>还款总金额：${sumCount}元</span>
        	<input type="button"  value="导出excel" size="30"  id="exportExcel"/>
		</form>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款金额（元）</th>
				<th>出借年利化率</th>
				<th>出借期限</th>
				<th>还款方式</th>
				<th>还款金额（元）</th>
				<th>还款期数</th>
				<th>还款时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="lend">
			<tr>
				<td>
					${lend.id}
				</td>
				<td>
					${lend.productName}
				</td>
				<td>
					${lend.amount}
				</td>
				<td>
					${lend.rate}
				</td>
				<td>
					${lend.deadline}
				</td>
				<td>
					<%--  <c:if test="${lend.repayStyle ==2 }">
					    到期还本
					</c:if>
					<c:if test="${lend.repayStyle ==3 }">
					   一次性还款
					</c:if>  --%>
					
					      ${lend.repayStyle} 
					
				</td>
				<td>
					${lend.count}
				</td>
				<td>
					<%-- <c:if test="${lend.repayCount!=null}">
					   ${lend.repayCount}/${lend.deadline}
					</c:if> --%>
					${lend.repayNumber}
				</td>
				<td>
					<fmt:formatDate value="${lend.lastRepayDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<%-- <c:choose>
						<c:when test="${lend.status ==1}">
							收益中
						</c:when>
						<c:when test="${lend.status ==2}">
							已结清
						</c:when>
						<c:when test="${lend.status ==3}">
							待审核
						</c:when>
						<c:when test="${lend.status ==4}">
							招标中
						</c:when>
						<c:when test="${lend.status ==5}">
							审核未通过
						</c:when>
					</c:choose> --%>
					${lend.status}
					
				</td>
				<td>
					<a href="javascript:void(0)" onclick="chakanLend('${lend.id}')" >查看</a>
				</td>
			</tr>
		 </c:forEach> 
			
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>