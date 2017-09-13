<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>债权列表</title>
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
		<li class="active"><a href="${ctx}/creditormanagement/creditor">债权列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="creditorVo" action="${ctx}/creditormanagement/creditor" method="post" class="breadcrumb form-search">
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
				转让类型:<form:select path="transfertype" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('TRANSFERTYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
				创建时间:	<input name="createTimeBeginTimes" readonly="readonly" id="createTimeBeginTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${creditorVo.createTimeBeginTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				   <input name="createTimeEndTimes" readonly="readonly" id="createTimeEndTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${creditorVo.createTimeEndTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li>
				完成时间:	<input name="lendingtimeBeginTimes" readonly="readonly" id="lendingtimeBeginTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${creditorVo.lendingtimeBeginTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				   <input name="lendingtimeEndTimes" readonly="readonly" id="lendingtimeEndTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${creditorVo.lendingtimeEndTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			          出让人:	<form:input id="borrowRealname" path="borrowRealname" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"/>
			</li>
			<li>
			债权编号:	<form:input id="assetCode" path="assetCode" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
					onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
			借款编号:	<form:input id="loannumber" path="loannumber" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
					onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="确定 " style="margin-right: 20px;"/>
			</li>
			<li style="text-align: right;padding-left: 200px;">
			债权数量：${creditorNumMap["creditorNum"] }人    债权价值总额：${creditorValueSumMap["sumAmount"] }元
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 30px;">序号</th>
				<th style="width: 100px;">债权编号</th>
				<th style="width: 160px;">债权名称</th>
				<th style="width: 80px;">借款编号</th>
				<th style="width: 80px;">出让人</th>
				<th style="width: 70px;">债权价值(元)</th>
				<th style="width: 50px;">出借年化利率</th>
				<th style="width: 50px;">剩余期限</th>
				<th style="width: 50px;">还款方式</th>
				<th style="width: 180px;">创建时间</th>
				<th style="width: 180px;">完成时间</th>
				<th style="width: 50px;">状态</th>
				<th style="width: 50px;">转让类型</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="creditorVo" varStatus="status" >
			<tr>
				<td><!-- 序号 -->
				${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<!-- 债权编号 -->
				<td>
					${creditorVo.assetCode}
				</td>
				<!-- 债权名称 -->
				<td>
				  <a href="${ctx}/creditormanagement/creditor/creditorListDetails?id=${creditorVo.id}&tinvestId=${creditorVo.tinvestId}&loannumber=${creditorVo.loannumber}">${creditorVo.borrowtitle}</a>
				</td>
				<!-- 借款编号 -->
				<td>
				   ${creditorVo.loannumber}
				</td>
				<!-- 出让人 -->
				<td>
					 ${creditorVo.borrowRealname}
				</td>
				<!-- 债权价值（元） -->
				<td>
					 ${creditorVo.amount}
				</td>
				<!-- 出借年化利率-->
				<td>
					 ${creditorVo.capitalRate}
				</td>
				<!-- 剩余期限 -->
				<td>
 					${creditorVo.surplusDeadlline}/${creditorVo.deadline}
				</td>
				<!-- 还款方式 -->
		        <td>
		            ${fns:getDictLabel(creditorVo.payMethod, 'pay_method', '')}
				</td>
				<!-- 创建时间 -->
				<td>
				      <fmt:formatDate value="${creditorVo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 完成时间 -->
				<td>
                      <fmt:formatDate value="${creditorVo.lendingtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 状态 -->
				<td>
				      ${fns:getDictLabel(creditorVo.borrowstatus, 'BORROWSTATUS', '')}
				</td>
				<!-- 转让类型 -->
				<td>
				    
				</td>
			</tr>
		 </c:forEach> 
			
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>