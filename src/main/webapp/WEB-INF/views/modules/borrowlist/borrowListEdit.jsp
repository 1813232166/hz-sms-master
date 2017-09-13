<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>普享标集合-债权编辑</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctxStatic}/borrow/standardpowder.js"></script>
</head>
<script type="text/javascript">
	
	
</script>
<body>
<div class="hzAdminRight lL" id="hzAdminRightId">
		<div class="hzAdminRightC" id="hzAdminRightCId">
			<div class="Mbox">
				<h2 class="H2 mt26">
					已选债权
				</h2>
					<span class="fr" style="float: right;">
						借款总额：<span id="totalAccountRelease">${empty borrowsTotal? 0: borrowsTotal }</span>元
					</span><br/>
				<form action="" method="post" id="releaseBorrowForm">
				<input type="hidden" name="isborrow" value="0">
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
									<th>借款人</th>
									<th>还款方式</th>
									<th>紧急状态</th>
									<th>批贷时间</th>
									<th>操作</th>
								</tr>
								<c:choose>
									<c:when test="${not empty borrows}">
										<c:forEach items="${borrows}" var="vo" varStatus="status">
											<tr>
												<td>
													<a href="${ctx}/borrow/borrowApply/toBorrowDetail?id=${vo.borrowId}" >${vo.loannumber }</a>
												</td>
												<td>${vo.borrowcode }</td>
												<td>${vo.deadline }个月</td>
												<td>${vo.anualrate }%</td>
												<td>
													<fmt:formatNumber value="${vo.borrowamount }" pattern="##,###,###,##0.00" /> 
												</td>
												<td>${vo.userName }</td>
												<td>
													<c:if test="${vo.borrowway =='debx' }">等额本息</c:if> 
													<c:if test="${vo.borrowway =='dqhb' }">先息后本</c:if> 
													<c:if test="${vo.borrowway =='ycx' }">一次性还本付息</c:if>
												</td>
												<td>
													<c:if test="${vo.criticalid =='1' }">紧急</c:if> 
													<c:if test="${vo.criticalid =='2' }">不紧急</c:if>
												</td>
												<td>
													<fmt:formatDate value="${vo.loantime }" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td>
													<a href="javascript:void(0);" onclick="deleteBorrow(this,'${vo.borrowId}','${vo.loannumber}');">删除</a> 
													<input type="hidden" name="borrowAmountHidden" value="${vo.borrowamount }" />
													<input type="hidden" name="selectBorrow" value="${vo.borrowId}${','}${vo.borrowaliasno}" />
													<%-- <input type="hidden" name="borrowaliasno" value="${vo.borrowaliasno}" /> --%>
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
					<div class="NewListConfig1">
					<h2 class="H2 mt26">发布设置</h2>
						<div class="left">
							<p>
								<b>*</b>风险提示:
							</p>
							<textarea rows="4" cols="40" id="warnings" name="riskWarning" <c:if test="${''==borrows[0].riskWarning or null==borrows[0].riskWarning}">placeholder = "市场有风险 出借须谨慎"</c:if>
								onkeyup="if($(this).val().length > 100) $(this).val($(this).val().substring(0,100));"><%-- <c:if test="${''==borrows[0].riskWarning}">市场有风险，出借需谨慎</c:if> --%>${borrowList.riskWarning}</textarea>
							<div class="hot">项目描述不能为空 / 最多100个中文字符</div>
						</div>
						<div class="left">
							<p>
								<b>*</b>项目介绍:
							</p>
							<textarea rows="4" cols="40" id="usageofloan" name="usageofloan"   placeholder = "请填写项目介绍"
								onkeyup="if($(this).val().length > 1000) $(this).val($(this).val().substring(0,1000));" maxlength="1000">${borrowList.projectBrief}</textarea>
							<div class="hot">项目描述不能为空 / 最多1000个中文字符</div>
						</div>
						<div class="left">
							<p>
								<b>*</b>募集期:
							</p>
							<input type="text" class="NewListConfig1Text" id="raiseterm" name="raiseterm" value="${borrowList.raiseDay }" onkeyup="checkRaiseterm();"/>
							<select class="" style="width:160px;height: 40px" id="raisetermunit" name="raisetermunit" onchange="checkRaiseterm();">
			                   	<option value="1" selected>天</option>
			                    <!-- <option value="0">小时</option> -->
			                </select>
			                <span style = "display:none">
				                <input type="checkbox" id="fillmark" name="fillMark" value="1" checked/>
								<label for="fillmark">到期补标</label>（最少1小时，最多7天）
			                </span>
						</div>
						<div class="left">
							<p>
								<b>*</b>起投金额:
							</p>
							<input type="text" class="NewListConfig1Text" id="minTenderSum" name=minTenderSum value="${borrowList.minTenderSum }"  />元
						</div>
						<div class="left leftRadio" style = "display:none">
							<p>
								<b>*</b>发布类型:
							</p>
							<input type="radio" id="immediate" name="borrowStatus" value="19" checked />
							<label for="immediate">立即发布</label>
							<input type="radio" id="preparation" name="borrowStatus" value="1" />
							<label for="preparation">预发布</label>
						</div>
						<div class="left" id="preparationDateDiv" style="display:none">
							<p>发布时间</p>
							<input class="NewListConfig1Text data" id="openBorrowDate" name="openborrowdate" value="${openBorrowDate}" type="text" 
			                	onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',readOnly:true,minDate:CurentTime()})" 
			                	style="width:155px" />
						</div>
					</div>
					
					<div class="Mbtm wd2" align="center">
						<input type="hidden" name="borrowListId" value="${borrowList.borrowListId}" />
						<a href="javascript:void(0);" class="Mbtn2 Mbtn2Color1" style="line-height: 32px" onclick="releaseSave();">提交审核</a>
						<a href="javascript:void(0);" class="Mbtn2 Mbtn2Color2" style="line-height: 32px" onclick="history.go(-1);">上一步</a>
					</div>
				</form>
			</div>


			<!-- 模态框 -->
			<div class="containbox3" id="containboxId" style="width: 644px;display: none;">
				<div class="con-cent">				
<%-- 					<h2 class="dalateH2">您尚未完成普享标<c:forEach items="${borrows }" var="vo" varStatus="status"
						><span id = "d${vo.borrowId }">${vo.borrowcode }<c:if test="${!status.last }">、</c:if></span></c:forEach
						>的编辑设置，请完成后再发布。</h2> --%>
					<h2 class="dalateH2">您尚未完成设置，请完成后在发布。</h2>
						
				</div>
				<div class="con-cent">				
					<div style="margin-top:0px;text-align: center">
						<input type="button" value="确定" class="Mbtn2Color1 Mbtn2 SureBtn" style="float: none; display: inline-block" onclick="cancelBorrow();" />
					</div>
				</div>
				
			</div>
		
		</div>
	</div>
	<!--hzAdminRight-->
</body>
</html>