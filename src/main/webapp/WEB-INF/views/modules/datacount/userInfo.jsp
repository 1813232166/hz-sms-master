<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人用户统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmitexport").click(function(){
				var beginTimes = $("#beginTimes").val();
				var endTimes = $("#endTimes").val();
				location.href=ctx+"/user/usercount/exportuserInfo?beginTimes="+beginTimes+"&endTimes="+endTimes;
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
		<li class="active"><a href="${ctx}/user/usercount/userInfo">个人用户统计列表</a></li>
		<%-- <li><a href="${ctx}/user/usercount/form">用户添加</a></li> --%>
	</ul>
	<form id="searchForm" modelAttribute="userInfo" action="${ctx}/user/usercount/userInfo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>时间：</label>
				<input name="beginTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${date1.beginTimes }" id="beginTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 至 
				<input name="endTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${date1.endTimes }" id="endTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>时间</th>
				<th>当日注册（个）</th>
				<th>新增充值用户（个）</th>
				<th>投资用户（个）</th>
				<th>借款意向用户（个）</th>
				<th>登录用户（个）</th>
				<th>PC端注册量（个）</th>
				<th>APP注册量（个）</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${userInfo}" var="userInfo" varStatus="status">
			<tr>
				<td>
					<%-- <a href="${ctx}/content/userInfo/form?id=${userInfo.id}"> --%>
					${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td>
					<%-- ${fns:getDictLabel(userInfo.userInfoColumn, 'userInfo_column', '')} --%>
					
					<fmt:formatDate value="${userInfo.times}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${userInfo.currentreg}
				</td>
				<td>
					${userInfo.newuser}
				</td>
				<td>
					${userInfo.investuser}
				</td>
				<td>
					${userInfo.borrowuser}
				</td>
				<td>
					${userInfo.loginuser}
				</td>
				<td>
					${userInfo.pcreguser}
				</td>
				<td>
					${userInfo.appreguser}
				</td>
				
			</tr>
		</c:forEach>
			<tr>
				<td>
					总计：
				</td>
				<td>
					
				</td>
				<td>
					${user.currentregsum}
				</td>
				<td>
					${user.newusersum}
				</td>
				<td>
					${user.investusersum}
				</td>
				<td>
					${user.borrowusersum}
				</td>
				<td>
					${user.loginusersum}
				</td>
				<td>
					${user.pcregusersum}
				</td>
				<td>
					${user.appregusersum}
				</td>
				
			</tr>
		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>