<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>普享标统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmitexport").click(function(){
				var beginTimes = $("#beginTimes").val();
				var endTimes = $("#endTimes").val();
				location.href=ctx+"/biao/biaocount/exportpuCount?beginTimes="+beginTimes+"&endTimes="+endTimes;
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
		<li class="active"><a href="${ctx}/biao/biaocount/puCount">普享标统计列表</a></li>
		<%-- <li><a href="${ctx}/user/usercount/form">用户添加</a></li> --%>
	</ul>
	<form id="searchForm" action="${ctx}/biao/biaocount/puCount" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>发标时间：</label>
				<input name="beginTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${date1.beginTimes }" id="beginTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 至 
				<input name="endTimes" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${date1.endTimes }" id="endTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/>
				<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>发标时间</th>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款编号</th>
				<th>借款金额（元）</th>
				<th>借款类型</th>
				<th>借款期限（月）</th>
				<th>年利率</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pucount}" var="pucount" varStatus="status">
			<tr>
				<td>
					<%-- <a href="${ctx}/content/pucount/form?id=${pucount.id}"> --%>
					${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td>
					<%-- ${fns:getDictLabel(pucount.pucountColumn, 'pucount_column', '')} --%>
					<fmt:formatDate value="${pucount.times}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${pucount.biaonum}
				</td>
				<td>
					${pucount.biaoname}
				</td>
				<td>
					${pucount.borrownum}
				</td>
				<td>
					${pucount.borrowamount}
				</td>
				<td>
					${pucount.borrowtype}
				</td>
				<td>
					${pucount.deadline}
				</td>
				<td>
					${pucount.anualrate}
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
				</td>
				<td>
				</td>
				<td>
				</td>
				
				<td>
					${pu.borrowAmountsum }
				</td>
				<td>
					
				</td>
				<td>
					
				</td>
				<td>
					
				</td>
				
			</tr>
		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>