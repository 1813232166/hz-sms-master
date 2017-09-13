<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拒绝放款列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//拒绝放款列表导出
			$("#refuseExport").click(function(){
				//alert("进来了");
				var ref=$("#searchForm").attr("action","${ctx}/fkuan/userFkuan/refuseExport");
				ref.submit();
				$("#searchForm").attr("action","${ctx}/fkuan/userFkuan/refuseFkuanList");
				
			});
				
		});//页面加载函数结尾处
		
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
		<li class="active"><a href="${ctx}/fkuan/userFkuan/refuseFkuanList">拒绝放款列表</a></li>
	</ul>
	<form id="searchForm"  action="${ctx}/fkuan/userFkuan/refuseFkuanList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>放款时间：</label>
				<input name="time" value="${map.time}" maxlength="200" type="text" class="input-medium Wdate required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				<input name="time2" value="${map.time2}" maxlength="200" type="text"  class="input-medium Wdate required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>标的编号：</label>
				<input name="borrowerNumber" value="${map.borrowerNumber}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li><label>标的名称：</label>
				<input name="biaoname" value="${map.biaoname}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
		</ul>
		<ul class="ul-form">
		   <li><label>借款人：</label>
				<input name="borrowerName" value="${map.borrowerName}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<input name="borrowerPhone" value="${map.borrowerPhone}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<!-- 导出excel-->
	<div style="float:right;padding-right:20px;">
		<form id=""   method="post" action="" >
        	<input type="button"  value="导出excel" size="30" id="refuseExport" />
        	<input type="file" id="f" onchange="this.form.submit()" name="files" style="position:absolute; filter:alpha(opacity=0); opacity:0; width:50px; " size="1" />
		</form>
	</div>
	<sys:message content="${message}"/>
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
				<th>放款时间</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${refuseList}" var="fkuan">
			<tr>
				<td>
					${fkuan.biaoid}
				</td>
				<td>
				  <a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${fkuan.borrowerId}">${fkuan.biaoname}</a>
				</td>
				<td>
					<a href="${ctx}/borrow/borrowApply/toFKBorrowDetail?id=${fkuan.borrowerId}">${fkuan.borrowerNumber}</a>
				</td>
				<td>
					${fkuan.borrowerName}
				</td>
				<td>
${fn:substring(fkuan.borrowerPhone,0,3)}****${fn:substring(fkuan.borrowerPhone,7,11)}				</td>
				<td>
					${fkuan.cash}
				</td>
				<td>
					${fkuan.reate}%
				</td>
				<td>
					${fkuan.dedaline}
				</td>
				<td>
					${fkuan.type}
				</td>
				<td>
					${fkuan.time}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<span>借款总额<fmt:formatNumber value="${sumCount!=null?sumCount:0.00}" pattern="#,##0.00#"/>元</span>
	<div class="pagination">${page}</div>
</body>
</html>