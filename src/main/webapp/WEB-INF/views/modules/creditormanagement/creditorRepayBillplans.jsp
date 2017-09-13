<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>债权列表</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
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
		<li class="active">回款计划</li>
	</ul>
	<sys:message content="${message}"/>
			<form id="searchForm" action="${ctx}/creditormanagement/creditor/repayBillplan">
				    <input id="investId" name="investId" type="hidden" value="${trb.investid}"/>
				    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			</form>
                            <div class="Mtable2">
                                <div class="tableBox">
                                    <table cellpadding="0" cellspacing="0" style="min-width: 1000px" id="detailsAllId">
                                        <thead>
                                        <tr>
                                            <th>期数</th>
                                            <th>本金（元）</th>
                                            <th>利息（元）</th>
                                            <th>总额（元）</th>
                                            <th>时间</th>
                                            
                                        </tr>
                                        </thead>
                                        <tbody id="borrowBillplan">
									<c:choose>
										<c:when test="${not empty repayBillplans.list}">
											<c:forEach items="${repayBillplans.list}" var="var" varStatus="index3">
												<tr>
													<td>第${var.billnum }期</td>
													<td>${var.stcapi }</td>
													<td>${var.sinte }</td>
													<td>
														<fmt:formatNumber value="${var.stcapi + var.sinte}" pattern="#0.00"/> 
													</td>
													<td><fmt:formatDate value="${var.sdate}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr class="main_info">
												<td colspan="100" class="">没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
                                    </tbody>
                                    </table>
                                    <!--统计-->
                                    <div class="foot foot_margin">
                                        <p></p>
                                        <style type="text/css">
                                            .foot .p2{
                                                padding-right: 61px;
                                            }
                                        </style>
                                    </div>
									<div class="pagination">${repayBillplans}</div>
                                </div>
                            </div><!--Mtable2-->
</body>
</html>