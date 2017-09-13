<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户业务列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});//页面加载函数的结尾
		
		
		//分页
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        } 
		 
    
	
	 
	</script>
</head>
<body>
<form id="searchForm"  action="${ctx}/user/userManage/redisList" method="post" class="breadcrumb form-search">
	<li><label>手机号：</label>
		<input id="mobile" name="mobile"  maxlength="200" type="text"/>
	</li>
	<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/></li>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>验证码</th>
				<th>业务类型</th>
			</tr>
		</thead>
		<tbody id="tbody">
		   <c:forEach items="${resList}" var="re">
		      <tr>
		          <th>${re.time}</th>
		          <th>${re.code}</th>
		          <th>${re.businessName}</th>
		      </tr>
		   </c:forEach>
		</tbody>
	</table>
</form>
</body>
</html>