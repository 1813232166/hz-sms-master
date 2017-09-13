<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>签章生成成功管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/borrow/contractSeal/excelExport.do");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/borrow/contractSeal/");
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnSubmit").click(function(){
				$("#searchForm").attr("action","${ctx}/borrow/contractSeal/");
				$("#searchForm").submit();
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/contractSeal/">合同列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="contractSeal" action="${ctx}/borrow/contractSeal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select path="contractType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('contract_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>签订时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${contractSeal.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${contractSeal.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>借款人：</label>
				<form:input  path="borrowName" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>标的编号：</label>
				<form:input  path="borrowAliasNo" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>标的名称：</label>
				<form:input  path="borrowAlias" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>出借人：</label>
				<form:input  path="investName" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>合同编号：</label>
				<form:input  path="contractSeal" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
		</ul>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>合同编号</th>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款编号</th>
				<th>借款人</th>
				<th>出借人</th>
				<th>合同金额</th>
				<th>出借年化利率</th>
				<th>出借期限</th>
				<th>签订时间</th>
				<th>类型</th>
				<%-- <shiro:hasPermission name="borrow:contractSeal:edit"></shiro:hasPermission><th>操作</th> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="contractSeal">
			<tr>
				<td><!-- 合同编号 -->
					<a href="${ctx}/borrow/contractSeal/contractSealDetail?id=${contractSeal.id}" jerichotabindex="99">
					${contractSeal.contractSeal}
					</a>
				</td>
				<td><!-- 标的编号 -->
					${contractSeal.borrowAliasNo}
				</td><!-- 标的名称 -->
				<td><%-- <a href="${ctx}/borrow/contractSeal/form?id=${contractSeal.id}"> --%>
					${contractSeal.borrowAlias}
				<!-- </a> --></td>
				<td><!-- 借款编号 -->
					${contractSeal.loanNumber}
				</td>
				<td><!-- 借款人 -->
					<c:if test="${contractSeal.userType eq '2'}">
					${fn:substring(contractSeal.userName,0,3)}****${fn:substring(contractSeal.userName,7,11)}
					</c:if>
					<c:if test="${contractSeal.userType ne '2'}">
					---
					</c:if>
				</td>
				<td><!-- 出借人 -->
					<c:if test="${contractSeal.userType eq '1'}">
					${fn:substring(contractSeal.cuserName,0,3)}****${fn:substring(contractSeal.cuserName,7,11)}
					</c:if>
					<c:if test="${contractSeal.userType ne '1'}">
					---
					</c:if>
				</td>
				<td><!-- 合同金额 -->
					${contractSeal.investAmount}
				</td>
				<td><!-- 出借年化利率 -->
					${contractSeal.ANUALRATE}
				</td>
				<td><!--出借期限 -->
					${contractSeal.DEADLINE}
				</td>
				<td><!-- 签订时间 -->
					<fmt:formatDate value="${contractSeal.interestAccrualDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td><!--类型 -->
					<c:if test="${contractSeal.contractType eq 'PX_JKXY'}">
						借款协议
					</c:if>
					<c:if test="${contractSeal.contractType eq 'PX_CJZXXY'}">
						出借咨询与服务协议
					</c:if>
					<c:if test="${contractSeal.contractType eq 'XYZXXY'}">
						融资咨询及服务协议
					</c:if>
					<c:if test="${contractSeal.contractType eq 'HKTXH'}">
						还款提醒函
					</c:if>
					<c:if test="${contractSeal.contractType eq 'PTZCXY'}">
						平台注册协议
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>