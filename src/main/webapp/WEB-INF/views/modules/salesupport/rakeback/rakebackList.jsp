<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>返佣设置</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.input-medium {
    width: 102px;
}
	
	</style>
	<script type="text/javascript">
		
	
		$(function() {
			
			$("#myModal").hide();
			$("#confirmlock").hide();
			$("#confirmunlock").hide();
			$("#confirmrepwd").hide();
			$("#confirmreturn").hide();
			
			$("#fanhui1").click(function(){
				$("#myModal").hide();
				 $("#searchForm").submit(); 
			});
			$("#confirmreturn").click(function(){
				$("#myModal").hide();
				location.href=""
				$("#searchForm").submit();
			});
			$("#close1").click(function(){
				$("#myModal").hide();
				$("#searchForm").submit();
			});
		});
		$(document).on('click',"#endStatus",function(){
			
			 var id=$(this).next().val();
			 $("#ida").val(id);
			$("#dialog1").text("您确定要禁用吗？");
			$("#confirmreturn").hide();
			$("#confirmsendstats").show();
			$("#confirmunstartstatus").hide();
			$("#myModal").modal('show');
		})
		$(document).on('click',"#confirmsendstats",function(){
			
			var id=$("#ida").val();
			$.ajax({
				url : ctx+"/rakeback/rakebackMessage/updatestaus?id="+id+"&status="+0,
				success : function(data) {
					if(data!=""){
						$("#confirmsendstats").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("禁用成功");
					}														
				},
				error : function() {
					$("#confirmsendstats").hide();
					$("#confirmreturn").show();
					$("#dialog1").text("禁用失败");
				}				
			}); 
			
		})
		$(document).on('click',"#startstatus",function(){
				
				 var id=$(this).next().val();
				 $("#ida").val(id);
				$("#dialog1").text("您确定要启用吗？");
				$("#confirmreturn").hide();
				$("#confirmunstartstatus").show();
				$("#confirmsendstats").hide();
				$("#myModal").modal('show');
			})
		$(document).on('click',"#confirmunstartstatus",function(){
		
			var id=$("#ida").val();
			$.ajax({
				url : ctx+"/rakeback/rakebackMessage/updatestaus?id="+id+"&status="+1,
				success : function(data) {
					if(data!=""){
						$("#confirmunstartstatus").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("启用成功");
					}														
				},
				error : function() {
					$("#confirmunstartstatus").hide();
					$("#confirmreturn").show();
					$("#dialog1").text("启用失败");
				}				
			}); 
		})
		
		
		function xinjian(){
			/* if($("td.tongjizhuangtai").size()==2){
				alert("目前只有理财师和普通用户两种");
			return false;
			} */
			location.href= ctx+"/rakeback/rakebackMessage/preAdd";
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/rakeback/rakebackMessage/rakebackList">返佣设置</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="rakeback" action="${ctx}/rakeback/rakebackMessage/rakebackList" method="post" class="breadcrumb form-search" >
	<!-- class="breadcrumb form-search" -->
	
		 <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="ida" name="id" type="hidden" value=""/>
		<ul class="ul-form">
			
			<li class="btns">
			
			<label>邀请人类型</label>
			<form:select path="type"  class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<label>返佣类型</label>
			<form:select path="refferStatus"  class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('refferStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<label>结算类型</label>
			<form:select path="jsStatus"  class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('jsStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<input style="margin-left:110px;" id="btnSubmit" class="btn btn-primary" type="submit" value="查询" style="margin-right: 20px;"/>
				<input class="btn btn-primary" type="button" value="新建" onclick="xinjian();" />
			</li>
			
		</ul>
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 16px;">ID</th>
				<th style="width: 62px;">邀请人类型</th>
				<th style="width: 40px;">人数</th>
				<th style="width: 72px;">返佣类型</th>
				<th style="width: 70px;">结算类型</th>
				<th style="width: 41px;">状态</th>
				<th style="width: 107px;">创建时间</th>
				<th style="width: 115px;">启用时间</th>
				<th style="width: 105px;">最后一次修改时间</th>
				<th style="width: 78px;" text—align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${rakebackList}" var="rakeback" varStatus="status" >
			<tr>
				<td>
					${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td class="tongjizhuangtai">
					${fns:getDictLabel(rakeback.type,'type','')}
				</td>
				<td>
					${rakeback.amount}
				</td>
				<td>
					${fns:getDictLabel(rakeback.refferStatus,'refferStatus','')}
				</td>
				<td>
					${fns:getDictLabel(rakeback.jsStatus,'jsStatus','')}
				</td>
				<td>
				${fns:getDictLabel(rakeback.status,'rakebackstatus','')}
				</td>
				<td>
					<fmt:formatDate value='${rakeback.createDate}' pattern="yyyy-MM-dd HH:mm" />
				</td>
				
				<td>
				<fmt:formatDate value='${rakeback.beginTime}' pattern="yyyy-MM-dd HH:mm" />
				</td>
				<td>
				<fmt:formatDate value='${rakeback.updateDate}' pattern="yyyy-MM-dd HH:mm" />
				</td>
				<td>
				<c:choose>
						<c:when test="${rakeback.status == '0'}"><!-- '0 禁用 1 启用' -->
							<a href="javascript:void(0)" id="startstatus">启用</a><!-- 当status=0是禁用状态有启用操作 -->
							<input type="hidden" value="${rakeback.id}">
						</c:when>
						<c:when test="${rakeback.status =='1'}">
							<a href="javascript:void(0)" id="endStatus">禁用</a>
							<input type="hidden" value="${rakeback.id}">
						</c:when>
				</c:choose>
					<a href="${ctx}/rakeback/rakebackMessage/preview?id=${rakeback.id}&operate='upd'">修改</a>
					<input type="hidden" value="${userInfo.id}">
					<a href="${ctx}/rakeback/rakebackMessage/preview?id=${rakeback.id}" >查看</a>
					
					
				</td>
				
			</tr>
		 </c:forEach> 
			
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	 <!-- 模态框声明 -->
	<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" style="width: 450px;">
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					
					<button class="close" id="close1"><span>&times;</span></button>
					<h4 class="modal-title">友好提示</h4>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="line-height: 80px;text-align: center;font-size: 18px;">
							<p id="dialog1"></p>
					</div>
				</div>
				<div class="modal-footer">
					<p class="text-center">
						<button class="btn btn-primary" id="confirmreturn" style="margin-right: 50px;">确认</button>
						<button class="btn btn-primary" id="confirmsendstats" style="margin-right: 50px;">确认</button>
						<button class="btn btn-primary" id="confirmunstartstatus"  style="margin-right: 50px;">确认</button>
						<button class="btn btn-primary" id="confirmrepwd" style="margin-right: 50px;">确认</button>
						<button class="btn btn-primary" id="fanhui1" style="margin-left: 50px;">返回</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>