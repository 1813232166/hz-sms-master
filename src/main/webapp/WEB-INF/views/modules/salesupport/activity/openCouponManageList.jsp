<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			/*全选事件*/
			$("#controlAll").click(function(){
				 var checklist = document.getElementsByName ("selected");
				   if(document.getElementById("controlAll").checked)
				   {
				   for(var i=0;i<checklist.length;i++)
				   {
				      checklist[i].checked = 1;
				   } 
				 }else{
				  for(var j=0;j<checklist.length;j++)
				  {
				     checklist[j].checked = 0;
				  }
				 }
			})
			
	
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

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
		
			<th><input  type="checkbox"   name="controlAll" style="controlAll" id="controlAll"/>全选<br></th>
				<th>优惠券类型</th>
				<th>优惠券面额(元)</th>
				<th>有效期</th>
				<th>出借金额限制(元)</th>
				<th>出借期限限制</th>
				<th>出借端口限制</th>
				<th>累计出借金额限制(元)</th>
		
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="coupon" varStatus="tfdAcc">
			<tr>
			
				<td><input type="checkbox" name="selected" value="${coupon.id}"/></td>
				<td>${coupon.couponTypeId}  </td>
				<td>${coupon.faceValue} </td>
				<td>${coupon.effectiveDays} 天</td>
				<td>${coupon.loanAmountMin} </td>
				<td>${coupon.loanTermList}</td>
				<td>${coupon.loanChannelList}</td>
				<td>${coupon.loanAmountSum}</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
					<div class="modal-footer">
					<p class="text-center">
						<button class="btn btn-primary" id="confirmreturn" style="margin-right: 50px;">确认</button>
						
						<input id="btnCancel" class="btn" type="button" value="取消" />
					</p>
				</div>
</body>
</html>