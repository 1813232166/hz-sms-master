<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#newCoupon").click(function(){
				$("#searchForm").attr("action","${ctx}/coupon/couponManage/toSaveCoupon");
                $("#searchForm").submit();
			})
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
		<li class="active"><a href="${ctx}/coupon/couponManage/couponManageList">优惠券列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="coupon" action="${ctx}/coupon/couponManage/couponManageList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="endLimit" name="endLimit" type="hidden" value=""/>
		<ul class="ul-form">
			<li><label>优惠券类型：</label>
				<%-- <form:input path="biztype" htmlEscape="false" maxlength="2" class="input-medium"/> --%>
				<form:select path="couponTypeId"  class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('couponType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>优惠券状态：</label>
                <%-- <form:input path="biztype" htmlEscape="false" maxlength="2" class="input-medium"/> --%>
                <form:select path="status" class="input-medium">
                    <form:option value="" label="全部"/>
                    <form:options items="${fns:getDictList('couponstatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
			<li><label>创建时间：</label>
				<input name="startCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${coupon.startCreateDate}" 
                   id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>至
                <input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"   value="${coupon.endCreateDate}"  
                    id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li class="clearfix"></li>
			<li><input  class="btn btn-primary" type="button" value="新建优惠券" id="newCoupon"/></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>优惠券类型</th>
				<th>优惠券面额（元/%）</th>
				<th>有效期</th>
				<th>使用时间</th>
				<th>出借金额限制（元）</th>
				<th>出借期限限制</th>
				<th>出借端口限制</th>
				<th>优惠券状态</th>
				<th>创建时间</th>
				<th>操作</th>
				<%-- <shiro:hasPermission name="compaccount:coupon:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="coupon" varStatus="tfdAcc">
			<tr>
				 <td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td> 
				<td>
					   <c:if test="${'fullDown' eq coupon.couponTypeId}">满减券</c:if>
					   <c:if test="${'cash' eq coupon.couponTypeId}">现金券</c:if>
					   <c:if test="${'interest' eq coupon.couponTypeId}">增益券</c:if>
					   <c:if test="${'cashBack' eq coupon.couponTypeId}">返现券</c:if>
				</td>
				<td>${coupon.faceValue}</td>
				<td>
				  <c:choose>
					  <c:when test="${empty coupon.effectiveDays}">无</c:when>
					  <c:otherwise>
					   ${coupon.effectiveDays}天
					  </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					  <c:when test="${empty coupon.beginTime and (empty coupon.endTime)}">无</c:when>
					  <c:otherwise><fmt:formatDate value="${coupon.beginTime}" pattern="yyyy-MM-dd HH:mm"/>至
					  <fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd HH:mm"/></c:otherwise>
					</c:choose>
					
				</td>
				<td>
					<c:choose>
					  <c:when test="${'0.00'==coupon.loanAmountMin}">无限制</c:when>
					  <c:otherwise>
					   ${coupon.loanAmountMin}
					  </c:otherwise>
					</c:choose>
				</td>
				<td>
				    <c:choose>
					  <c:when test="${empty coupon.loanTermList || '全部'==coupon.loanTermList}">无</c:when>
					  <c:otherwise>
					   ${coupon.loanTermList}
					  </c:otherwise>
					</c:choose>
				
				</td>
				<td>
				  <c:choose>
					  <c:when test="${empty coupon.loanChannelList || '全部'==coupon.loanChannelList}">无</c:when>
					  <c:otherwise>
					   ${coupon.loanChannelList}
					  </c:otherwise>
					</c:choose>
				</td>
				<td>
				<c:choose>
					  <c:when test="${'1'==coupon.shixiao}">已失效</c:when>
					  <c:otherwise>
					  ${fns:getDictLabel(coupon.status,'couponstatus','')}
					  </c:otherwise>
					</c:choose>
				
				</td>
				<td><fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>
    			 <c:if test="${('0'==coupon.status or '2'==coupon.status)  and  '1'!=coupon.shixiao}">
					<a href="${ctx}/coupon/couponManage/deleteCoupon?id=${coupon.id}" onclick="return confirmx('确定要删除此条记录', this.href)">删除</a>
    				<a href="${ctx}/coupon/couponManage/toSaveCoupon?id=${coupon.id}&operate='upd'">修改</a>
    			 </c:if>
    				<a href="${ctx}/coupon/couponManage/couponDetail?id=${coupon.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>