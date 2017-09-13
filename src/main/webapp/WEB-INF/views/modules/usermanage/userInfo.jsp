<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人用户统计</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		
	
		$(function() {
			/* 导出列表 */
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/user/userManage/exportuserInfo")
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/user/userManage/userInfo");
			});
			
			/*  $(".chakan").click(function(){
				id=$(this).next().val();
				location.href=ctx+"/user/userManage/baseInfo?id="+id;
			}); */ 
			
			
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
				$("#searchForm").submit();
			});
			$("#close1").click(function(){
				$("#myModal").hide();
				$("#searchForm").submit();
			});
			
			$(".lock").click(function(){
				 var id=$(this).next().val();
				 var phone=$(this).next().next().val();
				 $("#ida").val(id);
				$("#dialog1").text("您确定要锁定"+phone+"的用户吗？");
				$("#confirmreturn").hide();
				$("#confirmlock").show();
				$("#confirmunlock").hide();
				$("#confirmrepwd").hide();
				$("#myModal").modal('show');
				
				
			});
			
			$("#confirmlock").click(function(){
				var id=$("#ida").val();
				$.post(ctx+'/user/userManage/lockUser',{id:id},function(f){
					if(f){
						$("#confirmlock").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("锁定成功");
					}else{
						$("#confirmlock").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("锁定失败");
					}
				},"json")
			});
			
			$(".unlock").click(function(){
				 var id=$(this).next().val();
				 var phone=$(this).next().next().val();
				
			 	$("#ida").val(id);
				$("#dialog1").text("您确定要解锁"+phone+"的用户吗？");
				$("#confirmreturn").hide();
				$("#confirmlock").hide();
				$("#confirmunlock").show();
				$("#confirmrepwd").hide();
				$("#myModal").modal('show');
			});
			
			$("#confirmunlock").click(function(){
				var id=$("#ida").val();
				$.post(ctx+'/user/userManage/unlockUser',{id:id},function(f){
					if(f){
						$("#confirmunlock").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("解锁成功");
					}else{
						$("#confirmunlock").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("解锁失败");
					}
				},"json")
			});
			
			
			$('.repwd').click(function(){
				var id=$(this).prev().val();
				$("#ida").val(id);
				$("#dialog1").text("您确定要重置密码吗?重置后的密码为123456");
				$("#confirmreturn").hide();
				$("#confirmlock").hide();
				$("#confirmunlock").hide();
				$("#confirmrepwd").show();
				$("#myModal").modal('show');
				
			})
			$("#confirmrepwd").click(function(){
				var id=$("#ida").val();
				$.post(ctx+'/user/userManage/repwd',{id:id},function(f){
					if(f){
						$("#confirmrepwd").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("重置密码成功");
					}else{
						$("#confirmrepwd").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("重置密码失败");
					}
				},"json")
			});
		
			
			
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
		<li class="active"><a href="${ctx}/user/userManage/userInfo">用户信息</a></li>
		<%-- <li><a href="${ctx}/user/usercount/form">用户添加</a></li> --%>
	</ul>
	<form id="searchForm" modelAttribute="userInfo" action="${ctx}/user/userManage/userInfo" method="post" class="breadcrumb form-search" >
	<!-- class="breadcrumb form-search" -->
	
		 <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="ida" name="id" type="hidden" value=""/>
		<ul class="ul-form">
			<li>
				手机号：<input type="text" name="phone" class="input-medium" value="${paramMap.phone }" id="phone" style="margin-right: 20px;">
				姓名：<input type="text" name="uname" class="input-medium" value="${paramMap.uname }" id="uname" style="margin-right: 20px;">
				
			    注册时间：
				<input name="beginTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="${paramMap.beginTimes }" id="beginTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> 至 
				<input name="endTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="${paramMap.endTimes }" id="endTimes"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				
				
			</li>
			<li class="btns">
			
				类型：
				<select name="type" id="type" style="margin-right: 130px;width: 100px;">
					<option value="">全部</option>
					<option value="1" ${paramMap.type=='1'?'selected':'' }>出借人</option>
					<option value="2" ${paramMap.type=='2'?'selected':'' }>借款人</option>
				</select>
				状态：
				<select name="status" id="status" style="margin-right: 320px;">
					<option value="">全部</option>
					<option value="0" ${paramMap.status=='0'?'selected':'' }>正常</option>
					<option value="1" ${paramMap.status=='1'?'selected':'' }>锁定</option>
				</select>
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" style="margin-right: 20px;"/>
				<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			
		</ul>
	</form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 80px;">序号</th>
				<th style="width: 90px;">手机号</th>
				<th style="width: 40px;">姓名</th>
				<th style="width: 120px;">电子邮箱</th>
				<th style="width: 70px;">开通第三方</th>
				<th style="width: 120px;">注册时间</th>
				<th style="width: 50px;">类型</th>
				<th style="width: 50px;">状态</th>
				<th style="width: 130px;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${userInfo}" var="userInfo" varStatus="status" >
			<tr>
				<td>
					${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<td>
					${fn:substring(userInfo.phone,0,3)}****${fn:substring(userInfo.phone,7,11)}
				</td>
				<td>
					${userInfo.uname}
				</td>
				<td>
					${userInfo.email}
				</td>
				<td>
					${userInfo.isThird}
				</td>
				<td>
					${userInfo.regDate}
				</td>
				<td>
					${userInfo.type}
				</td>
				
				<td>
					${userInfo.status}
							
				</td>
				<td>
					<a href="${ctx}/user/userManage/baseInfo?id=${userInfo.id}" >查看</a>
					<input type="hidden" value="${userInfo.id}">
					<a href="javascript:void(0)" class="repwd">重置密码</a>
					<c:choose>
						<c:when test="${userInfo.status == '正常'}">
							<a href="javascript:void(0)" class="lock">锁定</a>
							<input type="hidden" value="${userInfo.id}">
							<input type="hidden" value="${userInfo.phone}">
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0)" class="unlock">解锁</a>
							<input type="hidden" value="${userInfo.id}">
							<input type="hidden" value="${userInfo.phone}">
						</c:otherwise>
						
					</c:choose>
					
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
						<button class="btn btn-primary" id="confirmlock" style="margin-right: 50px;">确认</button>
						<button class="btn btn-primary" id="confirmunlock"  style="margin-right: 50px;">确认</button>
						<button class="btn btn-primary" id="confirmrepwd" style="margin-right: 50px;">确认</button>
						<button class="btn btn-primary" id="fanhui1" style="margin-left: 50px;">返回</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
	
	
</body>
</html>