<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>撮合队列管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		$(function() {	
			
			/*提交校验*/
			$("#btnSubmit").click(function(){
				   var  capitalAmountLow=  $("#capitalAmountLow").val();
				   var capitalAmountHigh = $("#capitalAmountHigh").val();
				   var  beginInvestTime=  $("#beginInvestTime").val();
				   var  endInvestTime = $("#endInvestTime").val();

				   var  assetAmountLow=  $("#assetAmountLow").val();
				   var assetAmountHigh = $("#assetAmountHigh").val();
				   var  beginCreateTime=  $("#beginCreateTime").val();
				   var  endCreateTime = $("#endCreateTime").val();
				   
				   if(capitalAmountLow!=null&&capitalAmountLow!="" && capitalAmountHigh!=null&&capitalAmountHigh!="") {
					   
					   if(capitalAmountLow>capitalAmountHigh){
						    alert("出借金额开始不能大于结束");
						    $("#capitalAmountLow").val("");
						    $("#capitalAmountHigh").val("");
						   return false;
					    }
				     }
				     
				   if(beginInvestTime!=null&&beginInvestTime!="" && endInvestTime!=null&&endInvestTime!="") {					
					   
					   if(beginInvestTime>endInvestTime){
						    alert("出借时间开始不能大于结束");
						    $("#beginInvestTime").val("");
						    $("#endInvestTime").val("");
						   return false;
					    }

				     }
					 
					 
					if(assetAmountLow!=null&&assetAmountLow!="" && assetAmountHigh!=null&&assetAmountHigh!="") {
					   
					   if(assetAmountLow>assetAmountHigh){
						    alert("借款金额开始不能大于结束");
						    $("#assetAmountLow").val("");
						    $("#assetAmountHigh").val("");
						   return false;
					    }
				     }
				     
				   if(beginCreateTime!=null&&beginCreateTime!="" && endCreateTime!=null&&endCreateTime!="") {					
					   
					   if(beginCreateTime>endCreateTime){	
						    alert("匹配时间开始不能大于结束");
						    $("#beginCreateTime").val("");
						    $("#endCreateTime").val("");
						   return false;
					    }

				     }
				     
			});
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/match/matched/">撮合队列列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="matched" action="${ctx}/match/matched/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<label>出借编号：</label>	
				<form:input id="capitalCode" path="capitalCode" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
			</li>
			<li><label>出借人姓名：</label>
				<form:input id="capitalRealName" path="capitalRealName" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"/>
			</li>
			<li><label>出借身份证号</label>
				<form:input id="capitalIdCard" path="capitalIdCard" htmlEscape="false" maxlength="18" class="input-medium" onkeyup="value=value.replace(/^[a-zA-Z]+\D*|^\d{0,16}[a-zA-Z]+|[^0-9Xx]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
			</li>
			
			<li>
			<label>出借撮合状态</label>
				<form:select path="capitalMatchStatus" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('MATCH_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		
			<li>
				<label>出借金额：</label>
				<input name="capitalAmountLow" id="capitalAmountLow" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${matched.capitalAmountLow}"/> 至 
				<input name="capitalAmountHigh" id="capitalAmountHigh" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${matched.capitalAmountHigh}"/>
			</li>
		
			<li>
				<label>出借时间</label>
				<input name="beginInvestTime" readonly="readonly" id="beginInvestTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${matched.beginInvestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				<input name="endInvestTime" readonly="readonly" id="endInvestTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${matched.endInvestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			
			<li><label>资金类型：</label>
				<form:select path="capitalType" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('CAPITAL_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>资金类别：</label>
				<form:select path="capitalCategory" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('CAPITAL_CATEGORY')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li>
			<label>资产类型：</label>
				<form:select path="assetType" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('ASSET_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>资产类别：</label>
				<form:select path="assetCategory" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('ASSET_CATEGORY')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		
			<li>
			<label>借款编号：</label>	
				<form:input id="assetCode" path="assetCode" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
			</li>
			<li><label>借款人姓名：</label>
				<form:input id="assetRealName" path="assetRealName" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"/>
			</li>
			<li><label>借款身份证号</label>
				<form:input id="assetIdCard" path="assetIdCard" htmlEscape="false" maxlength="18" class="input-medium" onkeyup="value=value.replace(/^[a-zA-Z]+\D*|^\d{0,16}[a-zA-Z]+|[^0-9Xx]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
			</li>
		
			<li>
			<label>借款撮合状态</label>
				<form:select path="assetMatchStatus" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('MATCH_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li>
				<label>借款金额：</label>
				<input name="assetAmountLow" id="assetAmountLow" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${matched.assetAmountLow}"/> 至 
				<input name="assetAmountHigh" id="assetAmountHigh" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${matched.assetAmountHigh}"/>
			</li>
		
			<li>
				<label>匹配时间</label>
				<input name="beginCreateTime" readonly="readonly" id="beginCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${matched.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				<input name="endCreateTime" readonly="readonly" id="endCreateTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${matched.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			
			<li style="width: 900px;">
				匹配笔数：${page.count}&nbsp;&nbsp;
				原始资金匹配金额：<c:if test="${capitalType1Count.matchMountCount ==null}">0.00</c:if>${capitalType1Count.matchMountCount}元&nbsp;&nbsp;
				复投资金匹配金额：<c:if test="${capitalType999Count.matchMountCount ==null}">0.00</c:if>${capitalType999Count.matchMountCount}元&nbsp;&nbsp;
				原始资产匹配金额：<c:if test="${assetType1Count.matchMountCount ==null}">0.00</c:if>${assetType1Count.matchMountCount}元&nbsp;&nbsp;
				债权转让资产匹配金额：<c:if test="${assetType2Count.matchMountCount ==null}">0.00</c:if>${assetType2Count.matchMountCount}元&nbsp;&nbsp;
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>出借编号</th>
				<th>出借产品</th>
				<th>资金类别</th>
				<th>出借人姓名</th>
				<th>身份证号</th>
				<th>出借金额( 元）</th>
				<th>匹配金额（元）</th>
				<th>出借期限（月）</th>
				<th>出借年化利率（%）</th>
				<th>预计收益（元）</th>
				<th>撮合状态</th>
				<th>资金类型</th>
				<th>出借时间</th>
				<th>借款编号</th>
				<th>资产类别</th>
				<th>产品类型</th>
				<th>借款人姓名</th>
				<th>身份证号</th>
				<th>借款金额（元）</th>
				<th>匹配金额（元）</th>
				<th>借款期限（月）</th>
				<th>借款年化利率（%）</th>
				<th>撮合状态</th>
				<th>资产类型</th>
				<th>匹配时间</th>
			<tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="matched">
			<tr>
			<td>${matched.capitalCode}</td>
			<td>${matched.capitalProduct}</td>
			<td>
			${fns:getDictLabel(matched.capitalCategory, 'CAPITAL_CATEGORY', '')}
			</td>
			<td>
			${fn:substring(matched.capitalRealName, 0, 1)}**
			</td>
			<td>
			${fn:substring(matched.capitalIdCard, 0, 3)}***************
			</td>
			<td>${matched.capitalAmount}</td>
			<td>${matched.capitalMatchAmount}</td>
			<td>${matched.capitalNper}</td>
			<td>${matched.capitalRate}%</td>
			<td>${matched.projectedEarnings}</td>
			<td>
			${fns:getDictLabel(matched.capitalMatchStatus, 'MATCH_STATUS', '')}
			</td>
			<td>
			${fns:getDictLabel(matched.capitalType, 'CAPITAL_TYPE', '')}
			</td>
			<td style="text-align: center;">
					<fmt:formatDate value="${matched.investTime}" pattern="yyyyMMdd HH:mm:ss"/>
			</td>
			<td>${matched.assetCode}</td>
			<td>
			${fns:getDictLabel(matched.assetCategory, 'ASSET_CATEGORY', '')}
			</td>
			
			<td>
			${fns:getDictLabel(matched.productId, 'PRODUCT_ID', '')}
			</td>
			<td>
			${fn:substring(matched.assetRealName, 0, 1)}**
			</td>
			<td>
			${fn:substring(matched.assetIdCard, 0, 3)}***************
			</td>
			<td>${matched.assetAmount}</td>
			<td>${matched.assetMatchAmount}</td>
			<td>${matched.assetNper}</td>
			<td>${matched.assetRate}%</td>
			<td>
			${fns:getDictLabel(matched.assetMatchStatus, 'MATCH_STATUS', '')}
			</td>
			<td>
			${fns:getDictLabel(matched.assetType, 'ASSET_TYPE', '')}
			</td>
			<td style="text-align: center;">
					<fmt:formatDate value="${matched.createTime}" pattern="yyyyMMdd HH:mm:ss"/>
			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>