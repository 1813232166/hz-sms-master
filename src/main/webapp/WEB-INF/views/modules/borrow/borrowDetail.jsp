<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>普享标详情</title>
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
		function paymentDetailsForPage(pid,borrowId) {
			var url="${ctx}/borrow/tBorrow/repayplan";
			var htmlD="";
			 $.ajax({
                 url: url,
                 type: "POST",
                 data: {
                     "borrowId": borrowId,
                     "period": pid
                 },
                 dataType: "JSON",
                 success: function (result) {
			                     var dateRoot = result.list;
			                     if (dateRoot == null || dateRoot == '' || dateRoot == 'undefined') {
			                     }else{
			                    	 $("#payment").html("");
			                    	 $.each(dateRoot, function (i, a) {
			                    		 htmlD += "<tr><td>" +
	                                                a.realName
	                                                + "</td><td>" +
	                                                a.stcapi.toFixed(2)
	                                                + "</td><td>" +
	                                                a.sinte.toFixed(2)
	                                                + "</td><td>" +
	                                                (a.stcapi + a.sinte).toFixed(2)
	                                                + "</td><td>" +
	                                                a.sDate
	                                                + "</td></tr>"
	                                    });
	                                    $("#payment").append(htmlD);
			                    	 $("#repayment").html(result.html);
			                    	// console.log(result.html);
			                    	 $(".containbox").show();
			                     }
			                 }
			 });
		}
		
		function Lock_CheckForm() {
			$(".containbox").css('display','none');
			  return false;   
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/borrow/tBorrow/">标的详情</a></li>
		<li class="active"><%-- <a href="${ctx}/borrow/tBorrow/form?borrowId=${tBorrow.borrowId}">标的详情<shiro:hasPermission name="borrow:tBorrow:edit">${not empty tBorrow.borrowId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="borrow:tBorrow:edit">查看</shiro:lacksPermission></a>--%> </li>
	</ul><br/>
            <div class="Mbox">
                <h2 class="H2 mt26">${tBorrow.borrowtitle}</h2>
                <div class="NewListConfig">
                    <div class="lL left">
                        <p>
                            <span>标的编号：</span><span class="right">${tBorrow.borrowaliasno}</span>
                        </p>
                        <p>
                            <span>借款编号：</span><span class="right">${tBorrow.loannumber}</span>
                        </p>
<%--                         <p>
                            <span>保障方式：</span><span class="right">
                                <c:if test="${empty tBorrow.bztype}">全部</c:if>
                                <c:if test="${tBorrow.bztype =='dbgs'}" >担保金保障</c:if>
                                <c:if test="${tBorrow.bztype =='fxj'}" >风险金保障</c:if>
                                <c:if test="${tBorrow.bztype =='no'}" >不保障</c:if>
                                </span>
                        </p>
                        <p>
                            <span>借款形式：</span><span class="right">
                                    <c:if test="${empty tBorrow.borrowtype}">--</c:if>
                                    <c:if test="${tBorrow.borrowtype =='xy'}">信用借款</c:if>
                                    <c:if test="${tBorrow.borrowtype =='fd'}">房贷借款</c:if>
                                    <c:if test="${tBorrow.borrowtype =='cd'}">车贷借款</c:if>
                                    <c:if test="${tBorrow.borrowtype =='gjj'}"> 公积金贷</c:if>
                            </span>
                        </p>
 --%>                        <p>
                         <!--    <span>债权来源：</span><span class="right">借款</span> -->
                        </p>
                        <p>
                            <span>借款期限：</span><span class="right">
                                ${tBorrow.deadline}个月
                            </span>
                        </p>
                        <p>
                            <span>还款日：</span><span class="right">每月${tBorrow.repaymentdate}日（没有此日期时当月最后一日）</span>
                        </p>

                        <p>
                            <span>发布时间：</span><span class="right"><fmt:formatDate value="${tBorrow.openborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                        </p>
                        <p>
                            <span>最低出借金额：</span><span class="right">${tBorrow.mintendersum}元</span>
                        </p>
                        <p>
                            <span>放款时间：</span><span class="right">
                                <c:if test="${empty tBorrow.lendingtime}">--</c:if><fmt:formatDate value="${tBorrow.lendingtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                        </p>
                        <p>
                            <span>风险提示：</span>
                            <textarea rows="" cols="" class="right" readonly>${tBorrow.riskWarning}</textarea>
                          
                        </p>
                        <p>
                            <span>项目介绍：</span>
                             <textarea rows="" cols="" class="right" readonly>${tBorrow.projectBrief}</textarea>
                        </p>



                    </div>
                    <div class="lL right">
                        <p>
                            <span class="left">标的名称：</span><span>${tBorrow.borrowalias}</span>
                        </p>
                        <p>
                            <span class="left">批贷时间：</span><span>
                            <c:choose>
                            	<c:when test="${''!=tBorrow.loantime}"><fmt:formatDate value="${tBorrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:when>
                            	<c:otherwise>--</c:otherwise>
                            </c:choose>
                            </span>
                        </p>
                        <p>
                            <span class="left">还款方式：</span><span>
                            <c:choose>
                                <c:when test="${'debx'==tBorrow.borrowway}">等额本息</c:when>
                                <c:when test="${'ycx'==tBorrow.borrowway}">一次性还本付息</c:when>
                                <c:when test="${'dqhb'==tBorrow.borrowway}">先息后本</c:when>
                             <c:otherwise>--</c:otherwise></c:choose>
                                </span>
                        </p>
                        <p>
                            <span class="left">紧急状态：</span><span>
                            <c:choose>
                                <c:when test="${'2' eq tBorrow.criticalid}">不紧急</c:when>
                                <c:when test="${'1' eq tBorrow.criticalid}">紧急</c:when>
                              <c:otherwise>--</c:otherwise>
                              </c:choose>
                                </span>
                        </p>
                        <p>
                            <span class="left">借款金额：</span><span>${tBorrow.borrowamount}元</span>
                        </p>
                        <p>
                            <span class="left">出借年化利率：</span><span>${tBorrow.anualrate}%</span>
                        </p>
                        <p>
                            <span class="left">状态：</span><span>
                            <c:choose>
                               <c:when test="${'0' eq tBorrow.borrowstatus}">前台待审核</c:when>
                                <c:when test="${'3' eq tBorrow.borrowstatus}">审核未通过</c:when>
                                <c:when test="${'4' eq tBorrow.borrowstatus}">招标中</c:when>
                                <c:when test="${'7' eq tBorrow.borrowstatus}">还款中</c:when>
                                <c:when test="${'8' eq tBorrow.borrowstatus}">已结清</c:when>
                                <c:when test="${'9' eq tBorrow.borrowstatus}">已流标</c:when>
                                <c:when test="${'10' eq tBorrow.borrowstatus}">已逾期</c:when>
                                <c:when test="${'11' eq tBorrow.borrowstatus}">已满标</c:when>
                                <%-- <c:when test="${'12' eq tBorrow.borrowstatus}">已撤销</c:when> --%>
                                <c:when test="${'13' eq tBorrow.borrowstatus}">信审过程中</c:when>
                                <c:when test="${'14' eq tBorrow.borrowstatus}">信审失败</c:when>
                                <c:when test="${'15' eq tBorrow.borrowstatus}">信审成功</c:when>
                                <c:when test="${'16' eq tBorrow.borrowstatus}">新建</c:when>
                                <c:when test="${'17' eq tBorrow.borrowstatus}">拒绝签约</c:when>
                                <c:when test="${'18' eq tBorrow.borrowstatus}">已作废</c:when>
                                <c:when test="${'19' eq tBorrow.borrowstatus}">后台待审核</c:when>
                                <c:when test="${'20' eq tBorrow.borrowstatus}">后台新建包装前已编辑</c:when>
                            	<c:when test="${'21' eq tBorrow.borrowstatus}">预发布</c:when>
                              <c:otherwise>--</c:otherwise></c:choose>
                                </span>
                        </p>
                        <p>
                            <span class="left">募集期：</span><span>
                           <c:choose>
                           <c:when test="${tBorrow.raiseterm!=''}">${tBorrow.raiseterm}</c:when>
                           <c:otherwise>--</c:otherwise></c:choose>
                           <c:choose>
                           <c:when test="${tBorrow.raisetermunit=='0'}">小时</c:when>
                           <c:otherwise>天</c:otherwise></c:choose>
                           </span>
                        </p>

                        <p>
                            <span class="left">满标时间：</span><span><fmt:formatDate value="${tBorrow.fullBorrowDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                        </p>

                    </div>


                </div><!--NewListConfig--><!--左右-->
				<form id="searchForm" action="${ctx}/borrow/tBorrow/borrowdetail">
				    <input id="borrowId" name="borrowId" type="hidden" value="${tBorrow.borrowId}"/>
				    <input id="methodType" name="methodType" type="hidden" value="${methodType}"/>
				    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				</form>
                <div class="tabBOX">
                    <div class="tabsList">
                        <ul>
                            <li <c:if test="${'0' eq methodType}">class="active"</c:if>>匹配债权</li>
                            <li <c:if test="${'1' eq methodType}">class="active"</c:if>>出借记录</li>
                            <li <c:if test="${'2' eq methodType}">class="active"</c:if>>还款计划</li>
                        </ul>
                    </div>
                    <div class="tabscontbox">
                        <div id="p1" style="display: block;">
                            <div class="Mtable2">
                                <div class="tableBox">
                                    <table cellpadding="0" cellspacing="0" style="min-width: 1200px" >
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>借款编号</th>
                                            <th>借款期限</th>
                                            <th>借款利率</th>
                                            <th>借款金额</th>
                                            <th>借款人</th>
                  						<!--<th>还款日</th>
                                            <th>保障方式</th> -->
                                            <th>还款方式</th>
                                            <!-- <th>借款形式</th>
                                            <th>债权来源</th> -->
                                            <th>紧急状态</th>
                                            <th>批贷时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr >
                                            <td>1</td>
                                            <td><a href="${ctx}/borrow/borrowApply/toBorrowDetail?id=${tBorrow.borrowId}">${tBorrow.loannumber}</a> </td>
                                            <td>${tBorrow.deadline}个月</td>
                                            <td>${tBorrow.anualrate}%</td>
                                            <td>${tBorrow.borrowamount}</td>
                                            <td>${tBorrow.userName}</td>
                                            <%-- <td>${tBorrow.repaymentdate}</td>
                                            <td>
                                                <c:if test="${'dbgs'==tBorrow.bztype}">担保金保障</c:if>
                                                <c:if test="${'fxj'==tBorrow.bztype}">风险金保障</c:if>
                                                <c:if test="${'no'==tBorrow.bztype}">不保障</c:if>

                                            </td> --%>
                                            <td>
                                                <c:if test="${'debx'==tBorrow.borrowway}">等额本息</c:if>
                                                <c:if test="${'ycx'==tBorrow.borrowway}">一次性还本付息</c:if>
                                                <c:if test="${'dqhb'==tBorrow.borrowway}">先息后本</c:if>
                                            </td>
                                            <td>
                                          <c:choose>
			                                <c:when test="${'2' eq tBorrow.criticalid}">不紧急</c:when>
			                                <c:when test="${'1' eq tBorrow.criticalid}">紧急</c:when>
			                              <c:otherwise>--</c:otherwise>
			                              </c:choose>
                                    
                                            </td>
                                            <%-- <td>
                                                <c:if test="${tBorrow.borrowtype =='xy'}">信用借款</c:if>
                                                <c:if test="${tBorrow.borrowtype =='fd'}">房贷借款</c:if>
                                                <c:if test="${tBorrow.borrowtype =='cd'}">车贷借款</c:if>
                                            </td>
                                            <td>借款</td> --%>
                                            <td><fmt:formatDate value="${tBorrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div><!--Mtable2-->
                        </div>
                        <div id="p2" class="hide" style="display: none;">
                            <div class="Mtable2">
                                <div class="tableBox">
                                    <table cellpadding="0" cellspacing="0" style="min-width: 1200px" >
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                           <!--  <th>投资编号</th> -->
                                            <th>出借人</th>
                                            <th>出借金额（元）</th>
                                            <th>出借时间</th>

                                        </tr>
                                        </thead>

                                        <tbody id="investList">
                                        <c:choose>
                                        	<c:when test="${not empty invests.list}">
											<c:forEach items="${invests.list}" var="inv" varStatus="index2">
												<tr>
													<td>${index2.count+page.pageSize*(page.pageNo-1)}</td>
													<%-- <td>${inv.investcode }</td> --%>
													<td>${inv.realName}</td>
													<td>${inv.investamount}</td>
													<td><fmt:formatDate value="${inv.investtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
                                    <div class="pagination">${invests}</div>
                                </div>
                            </div><!--Mtable2-->

                        </div>
                        <div id="p3" class="hide" style="display: none;">
                            <div class="Mtable2">
                                <div class="tableBox">
                                    <table cellpadding="0" cellspacing="0" style="min-width: 1200px" id="detailsAllId">
                                        <thead>
                                        <tr>
                                            <th>期数</th>
                                            <th>本金（元）</th>
                                            <th>利息（元）</th>
                                            <th>总额（元）</th>
                                            <th>还款时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="borrowBillplan">
									<c:choose>
										<c:when test="${not empty billPlans.list}">
											<c:forEach items="${billPlans.list}" var="var" varStatus="index3">
												<tr>
													<td>${var.period }</td>
													<td>${var.monthCapital }</td>
													<td>${var.monthInterest }</td>
													<td>
														<fmt:formatNumber value="${var.monthCapital + var.monthInterest}" pattern="#0.00"/> 
													</td>
													<td><fmt:formatDate value="${var.repayTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td><input type="hidden" value="${var.applyId}" id="applyId"/>
													<a href="javascript:;"
													<c:if test="${tBorrow.borrowstatus!='7' and tBorrow.borrowstatus!='8' and tBorrow.borrowstatus!='10' and tBorrow.borrowstatus!='11'}"> 												
													 	style="opacity: 0.2" 
                                                    </c:if>
                                                    <c:if test="${tBorrow.borrowstatus=='7' or tBorrow.borrowstatus=='8' or tBorrow.borrowstatus=='10' or tBorrow.borrowstatus=='11'}"> 												
                                                    	onclick="paymentDetailsForPage('${var.period}','${tBorrow.borrowId}')"
                                                    </c:if>>明细</a> 
                                                    </td>
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



                <!--弹窗-->
                <div class="containbox" style="width: 700px;height:500px; margin-left: -350px; top: -2%; margin-top: 0;display: none;overflow:scroll; border:1px solid;">
                    <div class="containTitle2">还款明细<a style="float: right;height: 30px;width: 20px;margin-right: 10px;" onclick="Lock_CheckForm();">X</a></div>
                    <div class="Mtable2">
                        <div class="tableBox">
                            <table cellpadding="0" cellspacing="0" style="min-width: 650px">
                                <thead>
                                <tr>
                                    <th>出借人</th>
                                    <th>本金（元）</th>
                                    <th>利息（元）</th>
                                    <th>总额（元）</th>
                                    <th>时间</th>


                                </tr>
                                </thead>
                                <tbody id="payment">
                                
                                </tbody>


                            </table>
                           


                            </div>
							<div class="pagination" id="repayment">${repayplans }</div>
                        </div>
                    </div><!--Mtable2-->
                </div>
                <div class="refreshBg"></div>


</body>
</html>