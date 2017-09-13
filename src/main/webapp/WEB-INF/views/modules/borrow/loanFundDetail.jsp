<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		$(document).ready(function() {
			
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
<c:set var="project" value="${pageContext.request.contextPath}"/>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/tBorrowBillplan/">借款详情</a></li>
	</ul>
	<div class="hzAdminRight lL" id="hzAdminRightId">
				<div class="hzAdminRightC" id="hzAdminRightCId">
					<div class="Mbox">
						<h2 class="H2 mt26">借款信息</h2>
						<div class="NewListConfig">
							<div class="lL left">
								<p>
									<span>借款编号：</span><span class="right">${tBorrow.loannumber}</span>
								</p>
								<p>
									<span>借款利率：</span><span class="right">${tBorrow.anualrate}</span>
								</p>
								 <p>
                                <span>借款形式：</span>
                                	<span class="right">
                                		<c:if test="${tBorrow.borrowtype=='xy'}">
                                			信用借款
                                		</c:if>
                                		<c:if test="${tBorrow.borrowtype=='fd'}">
                                			房贷借款
                                		</c:if>
                                		<c:if test="${tBorrow.borrowtype=='cd'}">
                                			车贷借款
                                		</c:if>
                                	</span>
                            </p>
								<p>
									<span>实收金额：</span><span class="right">${tBorrow.paidinamount}元</span>
								</p>
								<p>
									<span>借款期限：</span><span class="right">${tBorrow.deadline}个月</span>
								</p>
								<p>
									<span>还款日：</span><span class="right">${tBorrow.repaymentdate}日</span>
								</p>
								<p>
									<span>借款用途：</span><span class="right">${tBorrow.usageofloan}</span>
								</p>

								<p>
									<span>保障方式：</span><span class="right">${tBorrow.bztype}</span>
								</p>



							</div>
							<div class="lL right">
								<p>
									<span class="left">借款标题：</span><span>${tBorrow.borrowtitle}</span>
								</p>
								<p>
									<span class="left">批贷时间：</span><span><fmt:formatDate value="${tBorrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</span>
								</p>
								<p>
									<span class="left">借款金额：</span><span>${tBorrow.borrowamount}元</span>
								</p>
								<p>
									<span class="left">服务费：</span><span>${tBorrow.servicecharge}元</span>
								</p>
								<p>
									<span class="left">还款方式：</span><span>${tBorrow.borrowway}</span>
								</p>
								<p>
									<span class="left">紧急状态：</span><span>${tBorrow.criticalid}</span>
								</p>
								<!-- <p>
									<span class="left">借款协议：</span><span><a
										href="javascript:;">借款协议01868903</a> </span>
								</p>
								 -->

							</div>


						</div>
						<!--NewListConfig-->
						<!--左右-->
						<h2 class="H2 mt26">个人信息</h2>
						<div class="NewListConfig">
							<div class="lL left">
								<p>
									<span>信用评级：</span><span class="right">${tBorrow.creditrating}</span>
								</p>
								<p>
									<span>姓名：</span><span class="right">${tBorrow.name}</span>
								</p>
								<p>
									<span>年龄：</span><span class="right">${tBorrow.age}</span>
								</p>
								<p>
									<span>年收入：</span><span class="right">${tBorrow.annualincome}万</span>
								</p>
								<p>
									<span>还款来源：</span><span class="right">${tBorrow.repaysource}</span>
								</p>




							</div>
							<div class="lL right">
								<p>
									<span class="left">身份证号：</span><span>${tBorrow.idcardno}</span>
								</p>
								<p>
									<span class="left">性别：</span><span>${tBorrow.sex}</span>
								</p>
								<p>
									<span class="left">所在城市：</span><span>${tBorrow.city}</span>
								</p>
								<p>
									<span class="left">资产负债情况：</span><span>${tBorrow.assetsliabilities}</span>
								</p>


							</div>


						</div>
						<!--NewListConfig-->
						<!--左右-->
						<h2 class="H2 mt26">还款计划</h2>
						<div class="Mtable2">
							<div class="tableBox">
								<table cellpadding="0" cellspacing="0">
									<tr>
										<th>期数</th>
										<th>本金（元）</th>
										<th>利息（元）</th>
										<th>总额（元）</th>
										<th>还款时间</th>
									</tr>
									<c:choose>
										<c:when test="${not empty billplans.list}">
											<c:forEach items="${billplans.list}" var="var">
												<tr>
													<td>${var.period }</td>
													<td>${var.monthCapital }</td>
													<td>${var.monthInterest }</td>
													<td>
														<fmt:formatNumber value="${var.monthCapital + var.monthInterest}" pattern="#0.00" var="x"></fmt:formatNumber> 
													</td>
													<td><fmt:formatDate value="${var.repayTime }"
															pattern="yyyy-MM-dd" /></td>
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
							<!--统计-->
							<div class="pagination">${page}</div>
						</div>

						<h2 class="H2 mt26">审核资料</h2>
						<div class="NewListConfig3" id="NewListConfig3Id">
							<div class="lL left">
								<div class="centers">
									<p class="rR twoLeft">身份证</p>
									<div class="rR twoRight">
										<a href="javascript:;"><c:forEach items="${idCardList}"
												var="p" varStatus="v">
												<span class="pic" id="${v.index}">
													<img src="${project}${p.picurl}"/>
												</span>
											</c:forEach></a>
									</div>
									
								</div>
								<div class="centers">
									<p class="rR twoLeft">工作证明</p>
									<div class="rR twoRight">
										<a href="javascript:;"><c:forEach items="${workList}"
												var="p" varStatus="v">
												<span class="pic" id="${v.index}">
													<img src="${project}${p.picurl}"/>
												</span>
											</c:forEach></a>
									</div>
									
								</div>
								<div class="centers">
									<p class="rR twoLeft">住址证明</p>
									<div class="rR twoRight">
										<a href="javascript:;"><c:forEach items="${addressList}"
												var="p" varStatus="v">
												<span class="pic" id="${v.index}">
													<img src="${project}${p.picurl}"/>
												</span>
											</c:forEach></a>
									</div>
									
								</div>
								<div class="centers">
									<p class="rR twoLeft">房产证明</p>
									<div class="rR twoRight">
										<a href="javascript:;"><c:forEach items="${houseList}"
												var="p" varStatus="v">
												<span class="pic" id="${v.index}">
													<img src="${project}${p.picurl}"/>
												</span>
											</c:forEach></a>
									</div>
									
								</div>
							</div>
							<div class="lL right">
								<p class="lL twoLeft">征信报告</p>
								<div class="lL twoRight">
									<a href="javascript:;"><c:forEach items="${creditList}"
											var="p" varStatus="v">
											<span class="pic" id="${v.index}">
												<img src="${project}${p.picurl}"/>
											</span>
										</c:forEach></a>
								</div>
								<p class="lL twoLeft">收入证明</p>
								<div class="lL twoRight">
									<a href="javascript:;"><c:forEach items="${incomeList}"
											var="p" varStatus="v">
											<span class="pic" id="${v.index}">
												<img src="${project}${p.picurl}"/>
											</span>
										</c:forEach></a>
								</div>
								<p class="lL twoLeft">经营地址证明</p>
								<div class="lL twoRight">
									<a href="javascript:;"><c:forEach
											items="${businessAddressList}" var="p" varStatus="v">
											<span class="pic" id="${v.index}">
												<img src="${project}${p.picurl}"/>
											</span>
										</c:forEach></a>
								</div>

							</div>
						</div>
						<!--NewListConfig-->
						<!--左右-->


					</div>

				</div>
				
			</div>
	
</body>
</html>