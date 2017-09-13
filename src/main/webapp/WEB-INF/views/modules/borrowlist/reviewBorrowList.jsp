<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>散标集合-查看</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctxStatic}/borrowlist/borrowlist.js"></script>
</head>

<script type="text/javascript">
	
	
</script>
<body>
<div class="hzAdminRight lL" id="hzAdminRightId">
		<div class="hzAdminRightC" id="hzAdminRightCId">
			<div class="Mbox" >
				<form action="${ctx }/borrow/borrowList/releaseBorrowList" method="post" id="releaseBorrowForm">
					<!-- 表单 -->
					<div class="NewListConfig1" style="overflow:hidden;">
						<h2 class="H2 mt26" style="font-size:24px; line-height:54px; margin:20px 0;">集合信息</h2>
						<div class="left sb-bd-p">
							<p>
								集合编号:
							</p>
							${borrowList.borrowListCode}
						</div>
						<div class="left sb-bd-p" >
							<p>
								集合名称:
							</p>
							${borrowList.borrowListTitle}
						</div>
						<div class="left sb-bd-p" >
							<p>
								借款金额:
							</p>
							${borrowList.borrowListAmount}
						</div>
						<div class="left sb-bd-p" >
							<p>
								出借年化利率:
							</p>
							${borrowList.anualRate}%
						</div>
						<div class="left sb-bd-p">
							<p>
								借款期限:
							</p>
							${borrowList.deadLine}
						</div>
						<div class="left sb-bd-p">
							<p>
								还款方式:
							</p>
							<c:if test="${borrowList.payMethod =='debx' }">等额本息</c:if> 
							<c:if test="${borrowList.payMethod =='dqhb' }">先息后本</c:if> 
							<c:if test="${borrowList.payMethod =='ycx' }">一次性还本付息</c:if>
						</div>
					</div>
					
					<!--表格-->
					<div class="Mtable2">
						<div class="tableBox">
							<table cellpadding="0" cellspacing="0">
								<tr>
									<th>借款编号</th>
									<th>散标编号</th>
									<th>借款期限</th>
									<th>借款利率</th>
									<th>借款金额</th>
									<th>还款日</th>
									<th>保障方式</th>
									<th>还款方式</th>
									<th>借款形式</th>
									<th>债权来源</th>
									<th>紧急状态</th>
									<th>批贷时间</th>
								</tr>
								<c:choose>
									<c:when test="${not empty borrowList.borrows}">
										<c:forEach items="${borrowList.borrows}" var="vo" varStatus="status">
											<tr>
												<td>
													<a href="${ctx}/borrow/borrowApply/toBorrowDetail?id=${vo.borrowId}" >${vo.loannumber }</a>
												</td>
												<td>${vo.borrowaliasno }</td>
												<td>${vo.deadline }个月</td>
												<td>${vo.anualrate }%</td>
												<td>
													<fmt:formatNumber value="${vo.borrowamount }" pattern="##,###,###,##0.00" /> 
												</td>
												<td>${vo.repaymentdate }</td>
												<td>
													<c:if test="${vo.bztype =='dbgs' }">担保公司保障</c:if> 
													<c:if test="${vo.bztype =='fxj' }">风险金保障</c:if> 
													<c:if test="${vo.bztype =='no' }">不保障</c:if>
												</td>
												<td>
													<c:if test="${vo.borrowway =='debx' }">等额本息</c:if> 
													<c:if test="${vo.borrowway =='dqhb' }">先息后本</c:if> 
													<c:if test="${vo.borrowway =='ycx' }">一次性还本付息</c:if>
												</td>
												<td>
													<c:if test="${vo.borrowtype =='xy' }">信用借款</c:if> 
													<c:if test="${vo.borrowtype =='fd' }">房贷借款</c:if> 
													<c:if test="${vo.borrowtype =='cd' }">车贷借款</c:if>
													<c:if test="${vo.borrowtype =='gjj' }">公积金贷</c:if>
												</td>
												<td>借款</td>
												<td>
													<c:if test="${vo.criticalid =='1' }">紧急</c:if> 
													<c:if test="${vo.criticalid =='2' }">不紧急</c:if>
												</td>
												<td>
													<fmt:formatDate value="${vo.loantime }" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
													<input type="hidden" name="borrowCodes" value="${vo.borrowcode }" />
													<input type="hidden" name="borrowNos" value="${vo.borrowalias }" />
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
							</table>
						</div>
					</div>
					
					<!-- 表单 -->
					<div class="NewListConfig1 overHide" >
						<h2 class="H2 mt26" style="font-size:24px; line-height:54px; margin:20px 0;">标的设置</h2>
						<div class="left sb-bd-p2">
							<p>
								风险提示:
							</p>
							${borrowList.riskWarning}
						</div>
						<div class="left sb-bd-p2">
							<p>
								项目介绍:
							</p>
							${borrowList.projectBrief}
						</div>
						<div class="left sb-bd-p2">
							<p>
								募集期:
							</p>
							${borrowList.raiseDay}天
						</div> 
						<div class="left sb-bd-p2">
							<p>
								起投金额:
							</p>
							${borrowList.minTenderSum}元
						</div>
					</div>
					
					<input type="hidden" name="riskWarning" value="${borrowList.riskWarning}" />
					<input type="hidden" name="usageofloan" value="${borrowList.projectBrief}" />
					<input type="hidden" name="raiseterm" value="${borrowList.raiseDay}" />
					<input type="hidden" name="minTenderSum" value="${borrowList.minTenderSum}" />
					<input type="hidden" name="borrowListNo" value="${borrowList.borrowListNo}" />
					<div class="Mbtm wd2" align="center" style="width:590px;">
						<a href="javascript:void(0);" class="Mbtn2 Mbtn2Color2" style="line-height: 32px;float:left;margin-right:20px;" onclick="history.go(-1);">上一步</a>
						<a href="javascript:void(0);" class="Mbtn2 Mbtn2Color1" style="line-height: 32px;float:left;margin-right:20px;" onclick="confirmBorrowList();">保存并提交</a>
						<a href="${ctx}/borrow/borrowList/list" class="Mbtn2 Mbtn2Color2" style="line-height: 32px;float:left;">取消</a>
					</div>
				</form>
			</div>
			
			<!-- 模态框 -->
			<div class="containbox3" id="containboxId" style="width: 644px;display: none;">
				<div class="con-cent">				
					<h2 class="dalateH2">确定保存${borrowList.borrowListTitle}并提交审核？</h2>
				</div>
				<div class="con-cent" style="margin-left:220px;">				
					<div style="margin-top:0px;float:left;margin-right:20px;">
						<input type="button" value="确定" class="Mbtn2Color1 Mbtn2 SureBtn" style="float: none; display: inline-block" onclick="releaseBorrowList();" />
					</div>
					<div style="margin-top:0px;float:left;margin-right:20px;">
						<input type="button" value="取消" class="Mbtn2Color2 Mbtn2 SureBtn" style="float: none; display: inline-block" onclick="cancelBorrow();" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--hzAdminRight-->
</body>
</html>