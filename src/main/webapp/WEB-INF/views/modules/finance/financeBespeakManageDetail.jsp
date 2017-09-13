<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预约详情</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	
	<style type="text/css">
		.Mtable2{ margin-top:5px; border:1px solid #dcdcdc;}
		.Mtable2 table tr td{ height:40px; line-height:40px; font-size:14px; border-bottom:1px dashed #dcdcdc;}
	</style>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			
		});
		
		function lendingAgreement(){
			alert("该功能因需求不明确，无法进行操作...");
		}

		
		/*提交审核  */
		function auditSubmit(){
			
			  if (confirm("您确定要提交审核词条记录?"))
			    {
				  $("#searchForm").attr("action","${ctx}/finance/financeBespeakManage/auditSubmit")
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

		<form:form id="searchForm"  modelAttribute="financeBespeakManage" action="${ctx}/finance/financeBespeakManage/" method="post" class="breadcrumb form-search">
		
		<input id="id" name="id" type="hidden" value="${financeBespeakManage.id}"/>
		
		</form:form>

	<!-- Top -->
 	<div class="tabBOX">
       			 <div class="">
       			 		<div id="p1" style="display: block;">
       			 				<p>
       			 					<span>当前位置：出借管理>预约管理->预约详情</span>
       			 				</p>
       			 				<h2>${bespeakDetail.name}</h2>
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table>
										                     <tr>
											                     <td style="width:150px;"><strong>计划名称</strong></td>
											                     <td style="width:150px;">${bespeakDetail.name}</td>
											                     <td style="width:150px;"><strong>创建时间:</strong></td>
											                     <td style="width:150px;"><fmt:formatDate value="${bespeakDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											                 </tr>
											                 <tr>
											                     <td style="width:150px;"><strong>每日最高预约金额(元):</strong></td>
											                     <td style="width:150px;">${bespeakDetail.collectAmount}元</td>
											                     <td style="width:150px;"><strong>*审核时间:</strong></td>
											                     <td style="width:150px;"><fmt:formatDate value="${bespeakDetail.auditCreatTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											                 </tr>
											                <tr>
											                     <td style="width:150px;"><strong>*出借期限:</strong></td>
											                     <td style="width:150px;">${bespeakDetail.nper}个月</td>
											                     <td style="width:150px;"><strong>*出借方式:</strong></td>
											                     <td style="width:150px;">${fns:getDictLabel(bespeakDetail.lendingMethod, 'LENDING_METHOD', '')}</td>
											                 </tr>
											                 
															 <tr>
											                     <td style="width:150px;"><strong>*提前赎回手续费(%):</strong></td>
											                     <td style="width:150px;">期限未过半${bespeakDetail.poundageMore}%,期限过半${bespeakDetail.poundage}%			</td>
											                     <td style="width:150px;"><strong></strong>*年均出借回报率约:</td>
											                     <td style="width:150px;">
											                     ${bespeakDetail.rate}%
											                     </td>
											                 </tr>
															 <tr>
											                     <td style="width:150px;"><strong>*退出方式:</strong></td>
											                     <td style="width:150px;">${fns:getDictLabel(bespeakDetail.exitMode, 'EXIT_MODE', '')}</td>
											                     <td style="width:150px;"><strong>*起息日:</strong></td>
											                      <td style="width:150px;">${fns:getDictLabel(bespeakDetail.interestDateType, 'INTEREST_DATE_TYPE', '')}</td>
											                 </tr>
															 <tr>
											                     <td style="width:150px;"><strong>*保障方式:</strong></td>
											                     <td style="width:150px;">${fns:getDictLabel(bespeakDetail.guaranteeMode, 'GUARANTEE_MODE', '')}</td>
											                     <td style="width:150px;"><strong>*最低预约金额(元):</strong></td>
											                     <td style="width:150px;">${bespeakDetail.minTenderSum}</td>
											                 </tr>
															 <tr>
											                     <td style="width:150px;"><strong>*介绍:</strong></td>
											                     <td style="width:150px;">${bespeakDetail.detail}</td>
											                     <td style="width:150px;"><strong>*最高预约金额(元):</strong></td>
											                     <td style="width:150px;">${bespeakDetail.maxTenderSum}</td>
											                 </tr>

															  <tr>
											                     <td style="width:150px;"><strong>*备注:</strong></td>
											                     <td style="width:150px;">${bespeakDetail.cause}</td>
											                     <td style="width:150px;"><strong>*递增金额（元）:</strong></td>
											                     <td style="width:150px;">${bespeakDetail.incrementalAmount}</td>
											                 </tr>
															  <tr>
											                     <td style="width:150px;"><strong>*协议:</strong></td>
											                     <td style="width:150px;"><a onclick="lendingAgreement()" >${bespeakDetail.lendingAgreement}</a></td>
											                 </tr>

											        </table>
       			 						 </div>
       			 				</div>
       			 				
       			 		
       			 </div>
        </div>
        
       </div>

	
	
		<div class="pagination">
		
		<input type="button" class="btn" value="返 回" onclick="history.go(-1)" style="margin-left: 500px;"/>
		<c:if test="${financeBespeakManage.status=='1' or financeBespeakManage.status=='3'}">
		<input type="button" class="btn btn-primary" value="提交审核" onclick="auditSubmit()" style="margin-left: 50px;"/>
		</c:if>
	</div>
	
	
</body>
</html>