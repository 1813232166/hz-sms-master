<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款终审列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#myModal').hide();
			/* 导出列表 */
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/borrow/borrowApply/exportReviewTrial")
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/borrow/borrowApply/reviewTrial");
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
		<li class="active"><a href="${ctx}/borrow/borrowApply/reviewTrial">借款终审列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/borrow/borrowApply/reviewTrial" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<ul class="ul-form" style=" height: 116px;">
			<li>
				<label>借款编号：</label>
				<input name="loanNumber" id="loanNumber" htmlEscape="false" maxlength="50" class="input-medium" value="${queryVo.loanNumber }"/>
			</li>
			<li>
				<label>姓名：</label>
				<input name="realName" id="realName" htmlEscape="false" maxlength="50" class="input-medium" value="${queryVo.realName }"/>
			</li>
			<li>
				<label>产品类型：</label>
				<select name="productType" class="input-medium" id="productType">
					<option value="" <c:if test="${empty queryVo.productType }">selected</c:if> >全部</option>
					<c:forEach items="${productTypeList}" var="productItem">
						<option value="${productItem.productNo }" <c:if test="${queryVo.productType == productItem.productNo }">selected</c:if> >${productItem.productName }</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<label>状态：</label>
				<select name="borrowStatus" class="input-medium" id="borrowStatus">
					<option value="" <c:if test="${empty queryVo.borrowStatus }">selected</c:if> >全部</option>
					<c:forEach items="${borrowStatusMap}" var="borrowStatusItem">
						<option value="${borrowStatusItem.key }" <c:if test="${queryVo.borrowStatus == borrowStatusItem.key }">selected</c:if> >${borrowStatusItem.value }</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<label>借款金额：</label>
				<input name="borrowAmountLow" id="borrowAmountLow" htmlEscape="false" maxlength="32" class="input-medium" value="${queryVo.borrowAmountLow }"/> - 
				<input name="borrowAmountHigh" id="borrowAmountHigh" htmlEscape="false" maxlength="32" class="input-medium" value="${queryVo.borrowAmountHigh }"/>
			</li>
			<li>
				<label>借款期限：</label>
				<input name="periodsLow" id="periodsLow" htmlEscape="false" maxlength="32" class="input-medium" value="${queryVo.periodsLow }"/> - 
				<input name="periodsHigh" id="periodsHigh" htmlEscape="false" maxlength="32" class="input-medium" value="${queryVo.periodsHigh }"/>
			</li>
			<li>
				<label>线下推送时间：</label>
				<input name="createTimeBegin" id="createTimeBegin" type="text"  maxlength="20" class="input-medium Wdate" value="${queryVo.createTimeBegin}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="createTimeEnd" id="createTimeEnd" type="text"  maxlength="20" class="input-medium Wdate" value="${queryVo.createTimeEnd}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			
			<li class="btns" style="padding-top: 10px">
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
<!-- 				<th>申请编号</th> -->
				<th>姓名</th>
				<th>手机号</th>
				<th>产品类型</th>
				<th>借款金额（元）</th>
				<th>借款期限（期）</th>
				<th>借款年化利率（%）</th>
				<th>线下推送时间</th>
				<th>状态</th>
				<th>初审意见</th>
				<th>终审意见</th>
				<th>终审打回原因</th>
				<th>打回线下原因</th>
				<th>来源</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${borrowApplyTrials}" var="borrowApplyTrial">
			<tr>
				<td>
				    <a href="${ctx}/borrow/borrowApply/toBorrowDetail?id=${borrowApplyTrial.borrowId}">
				    	${borrowApplyTrial.loanNumber}
				    </a>
				</td>
<!-- 				<td> -->
<%-- 					<a href="${ctx}/borrow/borrowApply/find?id=${borrowApplyTrial.borrowId}"> --%>
<%-- 						${borrowApplyTrial.borrowCode} --%>
<!-- 					</a> -->
<!-- 				</td> -->
				<td>
					${borrowApplyTrial.realName}
				</td>
				<td>
					${borrowApplyTrial.mobile}
				</td>
				<td>
					${borrowApplyTrial.productName}
				</td>
				<td>
					${borrowApplyTrial.borrowAmount}
				</td>
				<td>
					${borrowApplyTrial.periods}
				</td>
				<td>
					${borrowApplyTrial.yearRate}
				</td>
				<td>
					<fmt:formatDate value="${borrowApplyTrial.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
				</td>
				<td>
					<c:forEach items="${borrowStatusMap}" var="borrowStatusItem">
						<c:if test="${borrowApplyTrial.borrowStatus == borrowStatusItem.key }">${borrowStatusItem.value }</c:if>
					</c:forEach>
				</td>
				<td>
					${borrowApplyTrial.firstPassSuggest}
				</td>
				<td>
					${borrowApplyTrial.reviewPassSuggest}
				</td>
				<td>
					${borrowApplyTrial.reviewNopassSuggest}
				</td>
				<td>
					${borrowApplyTrial.firstNopassSuggest}
				</td>
				<td>
					<c:if test="${borrowApplyTrial.borrowingSources == '1'}">线下推送</c:if>
				</td>
				<td>
					<c:if test="${borrowApplyTrial.borrowStatus == '4' }">
						<a href="${ctx}/borrow/borrowApply/reviewTrialEdit?borrowId=${borrowApplyTrial.borrowId}">审核</a>
					</c:if>
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