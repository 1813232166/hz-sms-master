<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转让列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(function() {

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
		<li class="active"><a href="${ctx}/creditormanagement/assignment">转让列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="assignmentVo" action="${ctx}/creditormanagement/assignment" method="post" class="breadcrumb form-search">
<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="ida" type="hidden" value=""/>
		<ul class="ul-form">
			<li>
				还款方式:	<form:select path="payMethod" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
				状态:<form:select path="borrowstatus" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('BORROWSTATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
				出借时间:	<input name="investtimeBeginTimes" readonly="readonly" id="investtimeBeginTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${assignmentVo.investtimeBeginTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				   <input name="investtimeEndTimes" readonly="readonly" id="investtimeEndTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${assignmentVo.investtimeEndTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li>
				完成时间:	<input name="lendingtimeBeginTimes" readonly="readonly" id="lendingtimeBeginTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${assignmentVo.lendingtimeBeginTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				   <input name="lendingtimeEndTimes" readonly="readonly" id="lendingtimeEndTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${assignmentVo.lendingtimeEndTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			          出让人:	<form:input id="borrowRealname" path="borrowRealname" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"/>
			         受让人:	<form:input id="investRealname" path="investRealname" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    						    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"/>
			</li>
			<li>
			债权编号:	<form:input id="assetCode" path="assetCode" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
					onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="确定 " style="margin-right: 20px;"/>
			</li>
			<li style="text-align: right;padding-left: 200px;">
			受让人数：${transferNumberMap["transfernumber"] }人    受让人次：${transferPersonTimeMap["transferpersontime"] }人次   受让总额：${transferMoneySumMap["transfermoneysum"] }元
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 100px;">债权编号</th>
				<th style="width: 120px;">债权名称</th>
				<th style="width: 80px;">受让人</th>
				<th style="width: 70px;">受让金额(元)</th>
				<th style="width: 80px;">出让人</th>
				<th style="width: 50px;">出借年化利率</th>
				<th style="width: 50px;">剩余期限</th>
				<th style="width: 50px;">还款方式</th>
				<th style="width: 140px;">受让时间</th>
				<th style="width: 140px;">完成时间</th>
				<th style="width: 50px;">状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="assignmentVo" varStatus="status" >
			<tr>
				<td><!-- 序号 -->
				${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<!-- 债权编号 -->
				<td>
					${assignmentVo.assetCode}
				</td>
				<!-- 债权名称 -->
				<td>
				    <a href="${ctx}/creditormanagement/assignment/assignmentListDetails?id=${assignmentVo.id}&tinvestId=${creditorVo.tinvestId}&loannumber=${assignmentVo.loannumber}">${assignmentVo.borrowtitle}</a>
				</td>
				<!-- 受让人 -->
				<td>
				   ${assignmentVo.investRealname}
				</td>
				<!-- 受让金额(元) -->
				<td>
					 ${assignmentVo.investamount}
				</td>
				<!-- 出让人 -->
				<td>
					 ${assignmentVo.borrowRealname}
				</td>
				<!-- 出借年化利率-->
				<td>
					 ${assignmentVo.capitalRate}
				</td>
				<!-- 剩余期限 -->
				<td>
 					${assignmentVo.surplusDeadlline}/${assignmentVo.deadline}
				</td>
				<!-- 还款方式 -->
		        <td>
		            ${fns:getDictLabel(assignmentVo.payMethod, 'pay_method', '')}
				</td>
				<!-- 受让时间 -->
				<td>
				      <fmt:formatDate value="${assignmentVo.investtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 完成时间 -->
				<td>
                      <fmt:formatDate value="${assignmentVo.lendingtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 状态 -->
				<td>
				      ${fns:getDictLabel(assignmentVo.borrowstatus, 'BORROWSTATUS', '')}
				</td>
			</tr>
		 </c:forEach> 
			
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>