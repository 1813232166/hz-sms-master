<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划还款明细列表</title>
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
		
		function showDetail(){
			
			$('#myModal').modal('show');
		}
	</script>
</head>
<body>
      
	<h2 style="margin: 10px;">还款计划</h2>
	
	
							<table id="contentTable" class="table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>出借人</th>
											<th>本金(元)</th>
											<th>利息(元)</th>
											<th>总额(元)</th>
											<th>时间</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${list}" var="lend">
										<tr>
											<td>${lend.NAME}</td>
											<td>${lend.recivedPrincipal}</td>
											<td>${lend.recievedInterest}</td>
											<td>${lend.total}</td>
											<td>${lend.sDate}</td>
											
										</tr>	
										</c:forEach> 	
									</tbody>
							</table>
	
	<div class="pagination">${page}</div>
</body>
</html>