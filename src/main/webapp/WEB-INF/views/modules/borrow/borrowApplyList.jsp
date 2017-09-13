<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款申请列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#myModal').hide();
			/* 导出列表 */
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/borrow/borrowApply/exportBorrowApply")
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/borrow/borrowApply/");
			});
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		/* 点击 处理结果 */
		function result(id){
			$.ajax({
				url:"${ctx}/borrow/borrowApply/selectResult",
				type:"POST",
				data:{"borrowId":id},
				dataType:"text",
				success:function(data){
					$("#resonContent").empty();
					$("#reasonContent").html(data);
					$('#myModal').modal('show');
				}
				
			})
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/borrowApply/">借款申请列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/borrow/borrowApply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		
			<li><label>姓名：</label>
				<input name="name" id="name" htmlEscape="false" maxlength="50" class="input-medium" value="${map.name }"/>
			</li>
			<li><label>手机号：</label>
				<input name="mobile" id="mobile" htmlEscape="false" maxlength="32" class="input-medium" value="${map.mobile }"/>
			</li>
			<li><label>申请状态：</label>
				<select name="borrowstatus" class="input-medium" id="borrowstatus">
					<option value="">全部</option>
					<option value="0"  ${map.borrowstatus=='0'?'selected':'' }>已签约</option>
					<%-- <option value="12" ${map.borrowstatus=='12'?'selected':'' }>已撤销</option> --%>
					<option value="13" ${map.borrowstatus=='13'?'selected':'' }>审核中</option>
					<option value="14" ${map.borrowstatus=='14'?'selected':'' }>审核失败</option>
					<option value="15" ${map.borrowstatus=='15'?'selected':'' }>待签约</option>
					<option value="17" ${map.borrowstatus=='17'?'selected':'' }>拒绝签约</option>
					<option value="18" ${map.borrowstatus=='18'?'selected':'' }>已作废</option>
				</select>
			</li>
			
			<li><label>申请时间：</label>
				<input name="beginCreateTime" id="beginCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="${map.beginCreateTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" id="endCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="${map.endCreateTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>申请编号</th>
				<th>姓名</th>
				<th>手机号码</th>
				<th>公积金账号</th>
				<th>申请金额（元）</th>
				<th>申请时间</th>
				<th>申请状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="borrowApply">
			<tr>
				<td><a href="${ctx}/borrow/borrowApply/find?id=${borrowApply.borrowId}">
					${borrowApply.borrowCode}
				</a></td>
				<td>
					${borrowApply.name}
				</td>
				<td>
					${fn:substring(borrowApply.mobile,0,3)}****${fn:substring(borrowApply.mobile,7,11)}
				</td>
				<td>
					${fn:substring(borrowApply.accountNo,0,10)}****
				</td>
				<td>
					${borrowApply.borrowamount}
				</td>
				<td>
					${borrowApply.createTime}
				</td>
				<td>
					${borrowApply.borrowstatus}
				</td>
				<td>
					<a href="javascript:void(0)" onclick="result('${borrowApply.borrowId}')">处理结果</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<!-- 模态框声明 -->
	<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" style="width: 600px;">
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal"><span>&times;</span></button>
					<h4 class="modal-title">处理详情</h4>
				</div>
				<div class="modal-body"  style="height:200px;">
					<div id="reasonContent" style="text-align: center;font-size: 18px;">
							
					</div>
				</div>
				<div class="modal-footer">
					<p class="text-center">
						<button class="btn btn-primary" data-dismiss="modal" >确认</button>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>