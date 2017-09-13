<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>普享标还款列表</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmitexport").click(function(){
				/* var beginTimes = $("#beginTimes").val();
				var endTimes = $("#endTimes").val(); */
				$("#searchForm").attr("action","${ctx}/repayment/repaymentManage/exportpuList").submit();
				$("#searchForm").attr("action","${ctx}/repayment/repaymentManage/toPuList")
				
			});
			
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function chakan(borrowId,loanNumber) {
			
			location.href=ctx+"/repayment/repaymentManage/biaoDetail?borrowId="+borrowId+"&loanNumber="+loanNumber;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/repayment/repaymentManage/toPuList">普享标还款列表</a></li>
		<%-- <li><a href="${ctx}/user/usercount/form">用户添加</a></li> --%>
	</ul>
	<form id="searchForm" modelAttribute="puBiao" action="${ctx}/repayment/repaymentManage/toPuList" method="post" class="breadcrumb form-search">
	<!-- class="breadcrumb form-search" -->
	
		 <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li>
				<label>状态：</label>
				<select name="borrowStatus" style="width: 100px;" id="borrowStatus123">
					<option value="">全部</option>
					<option value="10" ${puBiao.borrowStatus=='10'?"selected":""}>已满标</option>
					<option value="11" ${puBiao.borrowStatus=='11'?"selected":""}>还款中</option>
					<option value="12" ${puBiao.borrowStatus=='12'?"selected":""}>已结清</option>
				
				</select>
			</li> --%>
			    <li>
					<label>标的编号：</label><input type="text" name="borrowaliasno" value="${puBiao.borrowaliasno }" htmlEscape="false" maxlength="32" class="input-medium"  >
				</li>
				<li>
				    <label>标的名称：</label><input type="text" name="borrowAlias"  value="${puBiao.borrowAlias }" htmlEscape="false" maxlength="32" class="input-medium">
				</li>
				<li>
				   <label>借款编号：</label><input type="text" name="loanNumber"  value="${puBiao.loanNumber }" htmlEscape="false" maxlength="32" class="input-medium" >
				</li>
				
				<li>
				   <label>借款人：</label><input type="text" name="name"  value="${puBiao.name }" htmlEscape="false" maxlength="32" class="input-medium" >
				</li>
				<li>
				   <label style="width:95px;">借款人手机号：</label><input type="text" name="mobile"  value="${puBiao.mobile }" htmlEscape="false" maxlength="32" class="input-medium" >
				</li>
				<li  style="float: right;margin-right: 50px;" class="btns">
				  <input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
				</li>
				<li  style="float: right;margin-right: 50px;" class="btns">
				  <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				</li>
				<li>
				  <p style="font-style: inherit;margin-left:900px;display:block;font-weight:800;">还款总额：${big}元</p>
				</li>
				
				
		</ul>
	</form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款编号</th>
				<th>借款人</th>
				<th>借款人手机号</th>
				<th>借款金额(元)</th>
				<th>出借年化利率</th>
				<th>出借期限</th>
				<th>还款方式</th>
				<th>还款金额(元)</th>
				<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;还款期数<br>(已还款/总还款期数)
				</th>
				<th>还款时间</th>
				<th style="width:60px;">状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${puBiaoList}" var="puBiao">
			<tr>
				<td>
					${puBiao.borrowaliasno}
				</td>
				<td>
					${puBiao.borrowAlias}
				</td>
				<td><a href="${ctx }/borrow/borrowApply/toBorrowDetail?id=${puBiao.borrowId}">
					${puBiao.loanNumber}</a>
				</td>
				<td>
					${puBiao.name}
				</td>
				<td>
					${puBiao.mobile}
				</td>
				<td>
					${puBiao.borrowAmount}
				</td>
				<td>
					${puBiao.anualrate}
				</td>
				<td>
					${puBiao.deadline}
				</td>
				<td>
					${puBiao.payMethod}
				</td>
				<td>
					${puBiao.remainAmount }
				</td>
				<td>
					${puBiao.repaymentNum}/${puBiao.repaymentNumSum}
				</td>
				<td>
					${puBiao.repaymentDate}
				</td>
				<td>
				    ${puBiao.borrowStatus}
				</td>
				<td>
					<a href="javascript:void(0)" onclick="chakan('${puBiao.borrowId }','${puBiao.loanNumber }')">查看</a>
				</td>
				
		 </c:forEach> 
			
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
</body>
</html>