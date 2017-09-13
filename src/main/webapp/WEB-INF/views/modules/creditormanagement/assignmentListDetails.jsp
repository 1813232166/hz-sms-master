<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转让信息</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$(document).ready(function() {
			var metype='${methodType}';
			 $('.tabscontbox>div:eq('+metype+')').show().siblings().hide();
			 $('.tabsList ul li').click(function(){
                 $(this).addClass('active').siblings().removeClass('active');
                 $('.tabscontbox>div:eq('+$(this).index()+')').show().siblings().hide();
                 $("#methodType").val($(this).index());
             })
		});
		function page(n,s,j){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
		
		function Lock_CheckForm() {
			$(".containbox").css('display','none');
			  return false;   
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>转让信息</li>
		<li class="active"></li>
	</ul><br/>
            <div class="Mbox">
                <div class="tableBox">
					<table style="margin-left: 80px;width: 850px;font-size: 14px;">
						<tr>
							<td style="width: 150px; "align="right">债权编号:</td>
							<td style="width: 200px;">${assignmentDeails.assetCode}</td>
							<td style="width: 150px;padding-left: 50px;" align="right">债权名称:</td>
							<td>${assignmentDeails.borrowtitle}</td>
						</tr>
						<tr>
							<td  align="right">债权价值:</td><td>${assignmentDeails.amount}元</td>
							<td style="width: 150px;padding-left: 50px;" align="right">出借年化利率:</td>
							<td>
							${assignmentDeails.capitalRate}%
							</td>
						</tr>
						<tr>
							<td align="right" >最低出借金额:</td>
							<td>${assignmentDeails.mintendersum}元</td>
							<td align="right" >还款方式:</td>
							<td> ${fns:getDictLabel(assignmentDeails.payMethod, 'pay_method', '')}</td>
						</tr>
						<tr>
							<td align="right">转让期:</td>
							<td >
							T+2
							</td>
							<td style="width: 350px;padding-left: 50px;" align="right">剩余期限:</td>
							<td style="width: 350px;">
							  		${assignmentDeails.surplusDeadlline}/${assignmentDeails.deadline}
							</td>
						</tr>
						<tr>
							<td align="right">状态:</td>
							<td>${fns:getDictLabel(assignmentDeails.borrowstatus, 'BORROWSTATUS', '')}</td>
						</tr>
					</table>

                </div><!--NewListConfig--><!--左右-->
				<form id="searchForm" action="${ctx}/creditormanagement/assignment/assignmentListDetails">
				    <input id="id" name="id" type="hidden" value="${assignmentDeails.id}"/>
				    <input id="loannumber" name="loannumber" type="hidden" value="${assignmentDeails.loannumber}"/>
				    <input id="methodType" name="methodType" type="hidden" value="${methodType}"/>
				    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				</form>
                <div class="tabBOX">
                    <div class="tabsList">
                        <ul>
                            <li  <c:if test="${'0' eq methodType}">class="active"</c:if>>债权信息</li>
                            <li  <c:if test="${'1' eq methodType}">class="active"</c:if>>转让记录(${transfersSum})</li>
                            <li <c:if test="${'2' eq methodType}">class="active"</c:if>>还款计划</li>
                        </ul>
                    </div>
                    <div class="tabscontbox">
                        <div id="p1" style="display: block;">
                         <h3>借款人信息</h3>
                            <div class="Mtable2">
                                <div class="tableBox">
										<table style="margin-left: 80px;width: 850px;font-size: 14px;">
											<tr>
												<td style="width: 150px; "align="right">姓名</td>
												<td style="width: 200px;">${fn:substring(assignmentDeails.borrowRealname,0,1)}**</td>
												<td style="width: 150px;padding-left: 50px;" align="right">所在城市</td>
												<td>${assignmentDeails.cityCode}</td>
												<td style="width: 222px;padding-left: 50px;" align="right">身份证号</td>
												<td style="width: 222px;padding-left: 50px;" align="left">${fn:substring(assignmentDeails.idcard,0,3)}******</td>
											</tr>
											<tr>
												<td  align="right">年收入</td><td>${bespeakDetail.yearIncome}</td>
												<td style="width: 150px;padding-left: 50px;" align="right">年龄</td>
												<td style="width: 50px">
												${assignmentDeails.age}
												</td>
												<td style="width: 150px;padding-left: 50px;" align="right">性别</td>
												<td>
												<c:if test="${assignmentDeails.sex=='0'}">女</c:if>
												<c:if test="${assignmentDeails.sex=='1'}">男</c:if>
												</td>
											</tr>
										</table>
                                </div>
                            </div>
                         <h3>借款信息</h3>
                            <div class="Mtable2">
                                <div class="tableBox">
										<table style="margin-left: 80px;width: 850px;font-size: 14px;">
											<tr>
												<td style="width: 150px; "align="right">借款编号</td>
												<td style="width: 200px;">${assignmentDeails.loannumber}</td>
												<td style="width: 150px;padding-left: 50px;" align="right">借款用途</td>
												<td>${assignmentDeails.usageOfLoan}</td>
											</tr>
											<tr>
												<td  align="right">借款期限</td>
												<td>${assignmentDeails.deadline}个月</td>
												<td style="width: 150px;padding-left: 50px;" align="right">还款方式</td>
												<td>
											${fns:getDictLabel(assignmentDeails.payMethod, 'pay_method', '')}
												</td>
											</tr>
											<tr>
												<td  align="right">剩余期限</td>
												<td>${assignmentDeails.surplusDeadlline}个月</td>
												<td style="width: 150px;padding-left: 50px;" align="right">借款年化利率</td>
												<td>
												${assignmentDeails.capitalRate}%
												</td>
											</tr>
										</table>
                                </div>
                            </div>
                         <h3>审核材料</h3>
                            <div class="Mtable2">
                                <div class="tableBox">
										<table style="margin-left: 80px;width: 850px;font-size: 14px;">
											<tr>
												<td style="width: 150px; "align="right">身份证明</td>
												<td style="width: 200px;">√</td>
												<td style="width: 150px;padding-left: 50px;" align="right">个人征信报告</td>
												<td>√</td>
											</tr>
											<tr>
												<td  align="right">收入及工作证明</td>
												<td>√</td>
											</tr>
										</table>
                                </div>
                            </div>
                        </div>
                        <div id="p2" class="hide" style="display: none;">
                            <div class="Mtable2">
                                <div class="tableBox">
                                    <table cellpadding="0" cellspacing="0" style="min-width: 1000px" >
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>受让人</th>
                                            <th>金额(元)</th>
                                            <th>时间</th>
                                            <th>操作</th>

                                        </tr>
                                        </thead>

                                        <tbody id="investList">
                                        <c:choose>
                                        	<c:when test="${not empty transfers.list}">
												<c:forEach items="${transfers.list}" var="inv" varStatus="index2">
													<tr>
														<td>${index2.count+page.pageSize*(page.pageNo-1)}</td>
														<td>${fn:substring(inv.investRealname,0,1)}**</td>
														<td>${inv.investamount}</td>
														<td>
														<fmt:formatDate value="${inv.investtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td><a href="${ctx}/creditormanagement/assignment/repayBillplan?investId=${inv.investId}">回款计划</a></td>
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
                                    <div class="pagination">${transfers}</div>
                                </div>
                            </div><!--Mtable2-->

                        </div>
                        <div id="p3" class="hide" style="display: none;">
                            <div class="Mtable2">
                                <div class="tableBox">
                                    <table cellpadding="0" cellspacing="0" style="min-width: 1000px" id="detailsAllId">
                                        <thead>
                                        <tr>
                                            <th>期数</th>
                                            <th>本金（元）</th>
                                            <th>利息（元）</th>
                                            <th>总额（元）</th>
                                            <th>还款时间</th>
                                            
                                        </tr>
                                        </thead>
                                        <tbody id="borrowBillplan">
									<c:choose>
										<c:when test="${not empty billPlans.list}">
											<c:forEach items="${billPlans.list}" var="var" varStatus="index3">
												<tr>
													     <c:if test="${'1' eq var.isOverdue }">【逾】</c:if>
	                                                     <c:if test="${'6' eq var.repayStatus }">【垫】</c:if>
													        第${var.period }期
													<td>${var.monthCapital }</td>
													<td>${var.monthInterest }</td>
													<td>
														<fmt:formatNumber value="${var.monthCapital + var.monthInterest}" pattern="#0.00"/> 
													</td>
													<td><fmt:formatDate value="${var.repayTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
									<div class="pagination">${billPlans}</div>
                                </div>
                            </div><!--Mtable2-->
                        </div>

                    </div>

                </div>
                </div>
                <div class="refreshBg"></div>
</body>
</html>