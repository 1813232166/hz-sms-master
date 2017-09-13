<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划管理详情</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	
	<style type="text/css">
		.Mtable2{ margin-top:5px; border:1px solid #dcdcdc;}
		.Mtable2 table tr td{ height:40px; line-height:40px; font-size:14px; border-bottom:1px dashed #dcdcdc;}
	</style>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			/* 导出列表 */
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/finance/lendPlanManage/listExport")
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/finance/lendPlanManage/");
			});
			
			
		});
		
		function lendingAgreement(){
			alert("该功能因需求不明确，无法进行操作...");
		}

		/*提交审核  */
		function auditSubmit(){
			  if (confirm("您确定要提交审核词条记录?"))
			    {
				  $("#searchForm").attr("action","${ctx}/finance/lendPlanManage/auditSubmit")
				  $("#searchForm").submit();
			    }
			
		}
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
			<!-- Top -->
 	<div class="tabBOX">
       			 <div class="">
       			 		<div id="p1" style="display: block;">
       			 				<p>
       			 					<span>当前位置：出借管理>出借计划管理->计划详情</span>
       			 				</p>
       			 				<h2>${lendPlanDetail.name}</h2>
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table>
										                     <tr>
											                     <td style="width:150px;"><strong>计划名称</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.name}</td>
											                     <td style="width:150px;"><strong>募集开始时间:</strong></td>
											                     <td style="width:150px;"><fmt:formatDate value="${lendPlanDetail.startTimeOfCollection}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											                 </tr>
											                 <tr>
											                     <td style="width:150px;"><strong>计划编号:</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.financeCode}</td>
											                     <td style="width:150px;"><strong>*募集结束时间:</strong></td>
											                     <td style="width:150px;"><fmt:formatDate value="${lendPlanDetail.endTimeOfCollection}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											                 </tr>
											                <tr>
											                     <td style="width:150px;"><strong>*募集总额:</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.collectAmount}</td>
											                     <td style="width:150px;"><strong>*创建时间:</strong></td>
											                     <td style="width:150px;"><fmt:formatDate value="${lendPlanDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											                 </tr>
											                 
															 <tr>
											                     <td style="width:150px;"><strong>*实际募集金额:</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.alreadyCollectedAmount}</td>
											                     <td style="width:150px;"><strong></strong>*募集比例(%):</td>
											                     <td style="width:150px;">
											                     <c:choose><c:when test="${lendPlanDetail.scale=='100.00' and finance.collectAmount!=finance.alreadyCollectedAmount}">99.99%</c:when><c:when test="${lendPlanDetail.scale==0.00 and finance.alreadyCollectedAmount!='0.00'}">0.01%</c:when><c:otherwise> ${lendPlanDetail.scale}%</c:otherwise></c:choose>
											                     </td>
											                 </tr>
															 <tr>
											                     <td style="width:150px;"><strong>*审核时间:</strong></td>
											                     <td style="width:150px;"><fmt:formatDate value="${lendPlanDetail.auditCreatTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											                     <td style="width:150px;"><strong>*出借期限:</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.nper}个月</td>
											                 </tr>
															 <tr>
											                     <td style="width:150px;"><strong>*提前赎回手续费(%):</strong></td>
											                     <td style="width:150px;">期限未过半${lendPlanDetail.poundageMore}%，期限过半${lendPlanDetail.poundage}%</td>
											                     <td style="width:150px;"><strong>*出借方式:</strong></td>
											                     <td style="width:150px;">${fns:getDictLabel(lendPlanDetail.lendingMethod, 'LENDING_METHOD', '')}</td>
											                 </tr>
															 <tr>
											                     <td style="width:150px;"><strong>*年均出借回报率约:</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.rate}</td>
											                     <td style="width:150px;"><strong>*退出方式:</strong></td>
											                     <td style="width:150px;">${fns:getDictLabel(lendPlanDetail.exitMode, 'EXIT_MODE', '')}</td>
											                 </tr>

															  <tr>
											                     <td style="width:150px;"><strong>*起息日:</strong></td>
											                     <td style="width:150px;">${fns:getDictLabel(lendPlanDetail.interestDateType, 'INTEREST_DATE_TYPE', '')}</td>
											                     <td style="width:150px;"><strong>*保障方式:</strong></td>
											                     <td style="width:150px;">${fns:getDictLabel(lendPlanDetail.guaranteeMode, 'GUARANTEE_MODE', '')}</td>
											                 </tr>
															  <tr>
											                     <td style="width:150px;"><strong>*最低出借金额（元）:</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.minTenderSum}</td>
											                     <td style="width:150px;"><strong></strong>*介绍:</td>
											                     <td style="width:150px;">${lendPlanDetail.detail}</td>
											                 </tr>

															   <tr>
											                     <td style="width:150px;"><strong>*本期产品最高出借金额（元）</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.maxTenderSum}</td>
											                     <td style="width:150px;"><strong>*备注:</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.cause}</td>
											                 </tr>
															   <tr>
											                     <td style="width:150px;"><strong>*递增金额（元）:</strong></td>
											                     <td style="width:150px;">${lendPlanDetail.incrementalAmount}</td>
											                     <td style="width:150px;"><strong>*协议:</strong></td>
											                     <td style="width:150px;"><a onclick="lendingAgreement()" >${lendPlanDetail.lendingAgreement}</a></td>
											                 </tr>
											        </table>
       			 						 </div>
       			 				</div>
       			 				
       			 		
       			 		
       			 		
       			 </div>
        </div>
        
       </div>


	<form:form id="searchForm" modelAttribute="lendPlanManage" action="${ctx}/finance/lendPlanManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="financeId" name="financeId" type="hidden" value="${finance.id}"/>
		<ul class="ul-form">
			
			<li>
			*募集总额（元）：<c:if test="${finance.collectAmount ==null}">0.00</c:if>${finance.collectAmount}&nbsp;&nbsp;&nbsp;&nbsp;*实际募集金额（万元）：  <c:if test="${finance.alreadyCollectedAmount ==null}">0.00</c:if>${finance.alreadyCollectedAmount}&nbsp;&nbsp;&nbsp;&nbsp; *预计本息总计（元）： <c:if test="${principalInterestCount.bxCount ==null}">0.00</c:if>${principalInterestCount.bxCount}&nbsp;&nbsp;&nbsp;&nbsp; *预计利息合计（元）： <c:if test="${principalInterestCount.xCount ==null}">0.00</c:if>${principalInterestCount.xCount} &nbsp;&nbsp;&nbsp;&nbsp; 
			</li>
			<li class="btns">
				<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li>
			*返现券合计（元）：0.00&nbsp;&nbsp;&nbsp;&nbsp; *增益券合计（元）：0.00 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>手机号</th>
				<th>出借时间</th>
				<th>出借金额（元）</th>
				<th>交易单号</th>
				<th>出借端口</th>
				<th>预计收益（元）</th>
				<th>优惠券类型</th>
				<th>优惠券面额</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lendPlanManage" varStatus="st" > 
			<tr>
				<td>
					${st.count+page.pageSize*(page.pageNo-1)}
				</td>
				
				<td>
					${lendPlanManage.mobile}
				</td>
				
				<td>
				<fmt:formatDate value="${lendPlanManage.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				<td>
				${lendPlanManage.amount}
				</td>
				
				<td>
				${lendPlanManage.capitalCode}
				</td>
				
				<td>
				${fns:getDictLabel(lendPlanManage.capitalSource, 'CAPITAL_SOURCE', '')}
				</td>
				
				<td>
				${lendPlanManage.projectedEarnings}
				</td>
				
				<td>
				未使用
				</td>
				
				<td>
				未使用
				</td>
				
				<td>
					${lendPlanManage.status}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
		<div class="pagination">
		
		
		<input type="button" class="btn" value="返 回" onclick="history.go(-1)" style="margin-left: 500px;"/>
		
		<c:if test="${finance.status=='1' or finance.status=='3'}">
		<input type="button" class="btn btn-primary" value="提交审核" onclick="auditSubmit()" style="margin-left: 50px;"/>
		</c:if>
	</div>
	
	
</body>
</html>