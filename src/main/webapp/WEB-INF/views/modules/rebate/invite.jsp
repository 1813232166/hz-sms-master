<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default"/>
<title>返佣测试页面</title>
<script type="text/javascript">
		$(document).ready(function() {
			$("#staffId").change(function(){
				var staffId = $("#staffId").val()
				init(staffId);
			});
			$("#exportExcel").click(function(){
				window.open('${ctx}/rebate/exportExcel');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function init(staffId){
			$("#refferLevel option").remove();
			var tbody = '<option value="" selected="selected">全部</option>';
			if(staffId == ''){
				$("#refferLevel").html(tbody);
            	$("#s2id_refferLevel span.select2-chosen").text("全部");
				return false;
			}
			$.ajax({
	            url:'${ctx}/rebate/getRefferLevelConfig?type='+staffId,
	            data:'{type:"'+staffId+'"}',
	            type:'post',
	            dataType:'json',
	            success:function(msg){
	            	$.each(msg, function (n, value) {
	                    var option = '<option value="'+n+'">'+value+'</option>';
	                    tbody += option;
	                });
	            	$("#refferLevel").html(tbody);
	            	var refferLevel = "${extendUser.refferLevel}";
	            	$("#refferLevel").val(refferLevel);
	            	$("#s2id_refferLevel span.select2-chosen").text( $("#refferLevel").find("option:selected").text());
	            }
	             
	        });
			
		}
		var staffId = "${extendUser.staffId}";
		init(staffId);
		
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="extendUser" action="${ctx}/rebate/inviteUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width:100px">邀请人手机号：</label>
				<form:input path="userMobile" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>邀请人姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>邀请人类型：</label>
				<form:select path="staffId"  class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${rakeList }" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>邀请人等级：</label>
				<select id="refferLevel" name="refferLevel" class="input-medium">
					<option value="">全部</option>
				</select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="exportExcel" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>邀请人手机号</th>
				<th>邀请人姓名</th>
				<th>邀请人类型</th>
				<th>邀请人等级</th>
				<th>邀请注册人数</th>
				<th>邀请实名人数</th>
				<th>邀请出借人数</th>
				<th>累计邀请出借金额（元）</th>
				<th>应返金额（元）</th>
				<th>已返金额（元）</th>
 			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="user" varStatus="status">
			<tr>
				<td>
					${(page.pageNo-1)*page.pageSize + status.index + 1}
				</td>
				<td>
					${user.blurUserMobile }
				</td>
				<td>
					${user.blurUserName}
				</td>
				<td>
					<c:choose>
					   <c:when test="${user.staffId == null}">
					   		推荐人
					   </c:when>
					   <c:otherwise>
					   		理财师
					   </c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
					   <c:when test="${user.staffId == null}">
					   		<c:choose>
					   			<c:when test="${extendUser.rakebackConfigMap.goldamount1 != null and user.sumInvestAmount + user.sumAInviteInvestAmount >= extendUser.rakebackConfigMap.goldamount1}">
					   				金牌
					   			</c:when>
					   			<c:when test="${extendUser.rakebackConfigMap.sliveramount1 != null and user.sumInvestAmount + user.sumAInviteInvestAmount >= extendUser.rakebackConfigMap.sliveramount1}">
					   				银牌
					   			</c:when>
					   			<c:otherwise>
					   				普通
					   			</c:otherwise>
					   		</c:choose>
					   </c:when>
					   <c:otherwise>
					   		<c:choose>
					   			<c:when test="${extendUser.rakebackConfigMap.goldamount2 != null and user.sumInvestAmount + user.sumAInviteInvestAmount + user.sumBInviteInvestAmount >= extendUser.rakebackConfigMap.goldamount2}">
					   				金牌
					   			</c:when>
					   			<c:when test="${extendUser.rakebackConfigMap.sliveramount2 != null and user.sumInvestAmount + user.sumAInviteInvestAmount + user.sumBInviteInvestAmount >= extendUser.rakebackConfigMap.sliveramount2}">
					   				银牌
					   			</c:when>
					   			<c:otherwise>
					   				普通
					   			</c:otherwise>
					   		</c:choose>
					   </c:otherwise>
					</c:choose>
				</td>
				<td>
					${user.refferUserCount}
				</td>
				<td>
					${user.refferIdcardCount}
				</td>
				<td>
					${user.refferInvestCount}
				</td>
				<td>
					<c:choose>
					   <c:when test="${user.staffId == null}">
					   		${user.sumInvestAmount + user.sumAInviteInvestAmount}
					   </c:when>
					   <c:otherwise>
					   		${user.sumInvestAmount + user.sumAInviteInvestAmount + user.sumBInviteInvestAmount}
					   </c:otherwise>
					</c:choose>
				</td>
				<td>
					${user.sumRebateAmount}
				</td>
				<td>
					${user.sumCompleteRebateAmount}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>