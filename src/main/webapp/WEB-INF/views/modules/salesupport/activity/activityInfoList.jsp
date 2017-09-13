<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
      <style type="text/css">
		#containbox2Id{
		  background-color: rgb(255,255,255);
		  width: 100%;
		  height: 100%;
		  z-index: 1;
		}
		
		
		
		.containbox3{
		  background-color: rgb(255,255,255);
		  width: 100%;
		  height: 100%;
		  z-index: 1;
		}
		
		
    </style>

	<script type="text/javascript">
		$(document).ready(function() {

			
			
			/* 导出列表 */
			$("#btnSubmitexport").click(function(){
				$("#searchForm").attr("action","${ctx}/activity/activityInfo/exportActivityInfo")
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/activity/activityInfo/list");
			});
			
			
			/*提交查询*/
			$("#btnSubmit").click(function(){
				
				   var  starttime1=  $("#publishtBeginTime").val();
				   var endtime1 = $("#publishtEndTime").val();

				   if(starttime1!=null&&starttime1!="" && endtime1!=null&&endtime1!="") {
					   
					   if(starttime1>endtime1){
						    alert("发布开始时间不能大于发布结束时间");
						    $("#publishtBeginTime").val("");
						    $("#publishtEndTime").val("");
						   return false;
					    }
				     }
				$("#searchForm").attr("action","${ctx}/activity/activityInfo/list");
				$("#searchForm").submit();
			});
			
	
			$("#myModal").hide();
			$("#confirmreturn").hide();
			$("#publishSuccess").hide();
			$("#revokeSuccess").hide();
			

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
			
			
			
			$(".fabu").click(function(){
				 var id=$(this).next().val();
				 $("#ida").val(id);
		
				$("#dialog1").text("您确定要发布此活动吗？");
				$("#confirmreturn").hide();
				$("#revokeSuccess").hide();
				$("#publishSuccess").show();
				$("#myModal").modal('show');
				
			});
			
			$("#publishSuccess").click(function(){
				var id=$("#ida").val();
				$.post(ctx+'/activity/activityInfo/publishHuoDong',{id:id},function(f){
					if(f){
						$("#publishSuccess").hide();
						$("#revokeSuccess").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("发布成功");
					}else{
						$("#publishSuccess").hide();
						$("#revokeSuccess").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("发布失败");
					}
				},"json")
			});
			
			

			
			
			$(".revoke").click(function(){
				 var id=$(this).next().val();
				 $("#ida").val(id);
		
				$("#dialog1").text("您确定要撤销吗？");
				$("#confirmreturn").hide();
				$("#revokeSuccess").show();
				$("#myModal").modal('show');
				
			});
			
			$("#revokeSuccess").click(function(){
				var id=$("#ida").val();
				$.post(ctx+'/activity/activityInfo/revokeHuoDong',{id:id},function(f){
					if(f){
						$("#revokeSuccess").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("撤销成功");
					}else{
						$("#revokeSuccess").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("撤销失败");
					}
				},"json")
			});
			
			
			
			//查看优惠券信息
			$(".youhuiquan").click(function(){
				 var id=$(this).next().val();
				 $("#ida").val(id);
				  
					$.ajax({                  
						url : ctx+'/activity/activityInfo/openCouponList',
						type : 'post',
						dataType: "json",
						   data:{id:id},
						success : function(result) {
							$("#couListInfo").html("");
							for(var i=0;i<result.length;i++) {
								
								  $("#couListInfo").append("<tr align='center'>");
								 $("#couListInfo").append("<td>"+(i+1)+" </td>");	
								 $("#couListInfo").append("<td>"+result[i].couponTypeId+" </td>");	
								 $("#couListInfo").append("<td>"+result[i].faceValue+" </td>");	
								 
								 if(result[i].effectiveDays==null||result[i].effectiveDays==""){
									 $("#couListInfo").append("<td>"+"无"+" </td>");	 
								 }else if(result[i].effectiveDays=="0"){
									 $("#couListInfo").append("<td>"+"无"+" </td>");	 
								 }else{
									 $("#couListInfo").append("<td>"+result[i].effectiveDays+"天"+" </td>");	
								 }
								 
								 if(result[i].begintime==null||result[i].begintime=="" && result[i].endtime==null||result[i].endtime=="" ){
									 $("#couListInfo").append("<td>"+"无"+" </td>");	 
								 }else{
									 
									 $("#couListInfo").append("<td width='135px;'>"+result[i].begintime+"至"+result[i].endtime+" </td>");	
								 }	
								 
						         if(parseFloat(result[i].loanAmountMin)==0.00 ){
	                     				 $("#couListInfo").append("<td>"+"无限制"+" </td>");	
	  							 }else{
	  								 $("#couListInfo").append("<td>"+result[i].loanAmountMin+" </td>");	
	  							 }
						         
								 if(result[i].loanTermList=="全部" ){
									 $("#couListInfo").append("<td>"+"无限制"+" </td>");	 
								 }else{
									 $("#couListInfo").append("<td>"+result[i].loanTermList+" </td>");	
								 }
								 
								 
								 if(result[i].loanChannelList=="全部" ){
									 $("#couListInfo").append("<td>"+"无限制"+" </td>");	 
								 }else{
									 $("#couListInfo").append("<td>"+result[i].loanChannelList+" </td>");	
								 }
								 
	
								  $("#couListInfo").append("</tr>");
							
							}
						
						}
					});

				   $(".refreshBg").show();
		            $("#containbox2Id").show();
				
			});
			
			
			$("#fanhui2").click(function(){
		         $(".refreshBg").hide();
		         $("#containbox2Id").hide();
			});
	
			$("#close2").click(function(){
				$("#myModal2").hide();
				$("#searchForm").submit();
			});
			
			

			$(".jianjie").click(function(){
				 var jianjieName=$(this).next().val();
		
				$("#dialog3").text(jianjieName);
				   $(".refreshBg").show();
				$("#myModal3").show();
				
			});
			
		
			$("#close3").click(function(){
		         $(".refreshBg").hide();
		     	$("#myModal3").hide();
				
			});
			
			$("#fanhui3").click(function(){
		         $(".refreshBg").hide();
			    $("#myModal3").hide();
			});
			
			
			//图片查看
			$(".block").click(function(){
				 var imageurl=$(this).next().val();
				  
				 $("#imgs").attr('src',imageurl); 
				   $(".refreshBg").show();
					$("#myModal4").show();
				
			});
			
			$("#close4").click(function(){
		
				    $(".refreshBg").hide();
					$("#myModal4").hide();
			});
			
			$("#fanhui4").click(function(){
			      $(".refreshBg").hide();
				  $("#myModal4").hide();
			});
			
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		/*新建活动信息*/
		function xinjian(){
			window.location.href= ctx+"/activity/activityInfo/addForm";
		}
		
		//关闭
		function closeInfo(){

			$("#myModal2").hide();
			$("#searchForm").submit();
		}
		

        function Lock_CheckForm(){
            $(".refreshBg").hide();
            $("#containbox2Id").hide();
        }
        
		
	</script>
	
	


</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/activity/activityInfo/list">活动列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="activityInfo" action="${ctx}/activity/activityInfo/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
				<input id="ida" name="id" type="hidden" value=""/>
		<ul class="ul-form">
			<li><label>活动名称：</label>
<%-- 				<form:input path="activityname"   htmlEscape="false" maxlength="64" class="input-medium" />
 --%>				
				<input type="text" id="activityname" name="activityname" class="input-medium" value="${paramMap.activityname }"  style="margin-right: 20px;" maxlength="64">
			</li>
			<li><label>活动类型</label>
				<form:select path="activitytype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('activityType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
	
			<li><label>活动状态</label>
				<form:select path="activitystatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('activityStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li class="btns">
			       发布时间：
				<input  name="publishtBeginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${paramMap.beginTimes }" 
                   id="publishtBeginTime" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'publishtEndTime\');}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>至
                <input  name="publishtEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"   value="${paramMap.endTimes }"  
                   id="publishtEndTime" onclick="WdatePicker({minDate:'#F{$dp.$D(\'publishtBeginTime\');}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>			
			
				
				<input id="btnAddNew" class="btn btn-primary" type="button" value="新建活动" onclick="xinjian();" style="margin-right: 20px;"/>
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询" style="margin-right: 20px;"/>
				<input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>活动名称</th>
				<th>活动简介</th>
				<th>活动类型</th>
	
				<th>开始时间</th>
				<th>结束时间</th>
				<th>创建时间</th>
				<th>发布时间</th>
				<th>审核时间</th>
				<th>活动状态</th>
				<th>活动链接</th>
				<th>活动图片</th>
				<th>优惠券</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="activityInfo" varStatus="tfdAcc">
		
			<tr>
			  <td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
				<td>
					${activityInfo.activityname}
				</td>
				<td>
					<a href="javascript:void(0)" class="jianjie">${activityInfo.introduction}</a>
					<input type="hidden" value="${activityInfo.introduction}">
				</td>
				
				<td>
					${fns:getDictLabel(activityInfo.activitytype, 'activityType', '')}
				</td>
	
				<td>
				
				  <c:if test="${empty activityInfo.begintime}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty activityInfo.begintime}">  
     					<fmt:formatDate value="${activityInfo.begintime}" pattern="yyyy-MM-dd"/>
                </c:if>
				
				</td>
				<td>
				
			    <c:if test="${empty activityInfo.endtime}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty activityInfo.endtime}">  
     					<fmt:formatDate value="${activityInfo.endtime}" pattern="yyyy-MM-dd"/>
                </c:if>
				</td>
				<td>
				
			    <c:if test="${empty activityInfo.createDate}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty activityInfo.createDate}">  
     					<fmt:formatDate value="${activityInfo.createDate}" pattern="yyyy-MM-dd"/>
                </c:if>
				
				</td>
				<td>
				
				<c:if test="${empty activityInfo.publishtime}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty activityInfo.publishtime}">  
     					<fmt:formatDate value="${activityInfo.publishtime}" pattern="yyyy-MM-dd"/>
                </c:if>
				
				</td>
				<td>
				
				<c:if test="${empty activityInfo.authtime}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty activityInfo.authtime}">  
     					<fmt:formatDate value="${activityInfo.authtime}" pattern="yyyy-MM-dd"/>
                </c:if>
				
				</td>
				<td>
					${fns:getDictLabel(activityInfo.activitystatus, 'activityStatus', '')}
				</td>
				<td>
					                
                  <c:if test="${empty activityInfo.activityhref}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty activityInfo.activityhref}">  
     					${activityInfo.activityhref}
                </c:if>
                
				</td>
				<td>
				         <c:if test="${empty activityInfo.imageurl}"> 
                		         无
		               	</c:if>
		                  <c:if test="${not empty activityInfo.imageurl}">  
					    <a href="javascript:;" class="block">查看</a>
					    	<input type="hidden" value="${activityInfo.imageurl}">
		                </c:if>
		
				</td>
				<td>
	
					<a href="javascript:void(0)" class="youhuiquan">查看</a>
					<input type="hidden" value="${activityInfo.id}">
				</td>
			
				<td>
				 <c:if test="${activityInfo.activitystatus ==0}">
						<a href="${ctx}/activity/activityInfo/getDetail?id=${activityInfo.id}">查看</a>
						<input type="hidden" value="${activityInfo.id}">
					<a href="${ctx}/activity/activityInfo/form?id=${activityInfo.id}">修改</a>
					<a href="${ctx}/activity/activityInfo/delete?id=${activityInfo.id}" onclick="return confirmx('确认要删除该活动吗？', this.href)">删除</a>
					 </c:if>
					 
					  <c:if test="${activityInfo.activitystatus ==1}">
						<a href="${ctx}/activity/activityInfo/getDetail?id=${activityInfo.id}">查看</a>
					 </c:if>
					 
					 <c:if test="${activityInfo.activitystatus ==2}">
						<a href="${ctx}/activity/activityInfo/getDetail?id=${activityInfo.id}">查看</a>
						<a href="${ctx}/activity/activityInfo/form?id=${activityInfo.id}">修改</a>
					 </c:if>
					 
						<c:if test="${activityInfo.activitystatus==3}">
						<a href="${ctx}/activity/activityInfo/getDetail?id=${activityInfo.id}">查看</a>
						<a href="javascript:void(0)" class="revoke">撤销</a>
						<input type="hidden" value="${activityInfo.id}">
					 </c:if>
				
					<c:if test="${activityInfo.activitystatus ==4}">
						<a href="${ctx}/activity/activityInfo/getDetail?id=${activityInfo.id}">查看</a>
						<a href="javascript:void(0)" class="revoke">撤销</a>
						 <input type="hidden" value="${activityInfo.id}">
					 </c:if>
					 
					<c:if test="${activityInfo.activitystatus ==5}">
					<a href="${ctx}/activity/activityInfo/getDetail?id=${activityInfo.id}">查看</a>
					<a href="${ctx}/activity/activityInfo/form?id=${activityInfo.id}">修改</a>
						<a href="javascript:void(0)" class="fabu">发布</a>
						<input type="hidden" value="${activityInfo.id}">
					 </c:if>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
		 <!-- 模态框声明 -->
	<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" style="width: 450px;" >
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
				<div class="modal-footer" align="center">
					<p class="text-center">
						<button class="btn btn-primary" id="confirmreturn" >确定</button>
						<button class="btn btn-primary" id="publishSuccess" >确定</button>
						<button class="btn btn-primary" id="revokeSuccess" >确定</button>
						<button class="btn btn-primary" id="fanhui1" >取消</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
	

	
	
	    <!--  弹窗  -->
   <div class="containbox3" id="containbox2Id" style="width: 1100px;height:490px; margin-top: 0px;z-index:2px; overflow:scroll; border:1px solid;display: none;position: absolute;top: 10px;left: 100px;right: 0px;bottom: 0px;">     
           <div class="containTitle2"><a style="float: right;height: 30px;width: 20px;margin-right: 10px;" onclick="Lock_CheckForm();">X</a></div>
     							
				<table id="contentTable" border="2px;" class="table table-striped table-bordered table-condensed">
		      <thead>
			<tr align="center">
				<th>ID</th>
				<th>优惠券类型</th>
				<th>优惠券面额(元/%)</th>
				<th>有效期</th>
				<th width="135px;">使用时间</th>
				<th>出借金额限制(元)</th>
				<th>出借期限限制</th>
				<th>出借端口限制</th>
			</tr>
		    </thead>
		  <tbody id="couListInfo">

		 </tbody>
	</table>
			<p class="text-center" >
				<button class="btn btn-primary" id="fanhui2" >关闭</button>
			</p>		
     </div><!--  弹窗  -->
     <div class="refreshBg" style="display:none; position: fixed; top: 0; left: 0; opacity: 0.3; background: black; width: 100%; height: 100%;"></div>
	

	    <!--  弹窗  -->
         <div class="containbox3" id="myModal3" style="width: 600px;height:300px; margin-top: 0px;z-index:2px; margin-left: 200px;overflow:scroll; border:1px solid;display: none;position: absolute;top: 167px;left: 190px;right: 0px;bottom: 0px; float: left">
           <div class="containTitle2"><a style="float: right;height: 30px;width: 20px;margin-right: 10px;" id="close3">X</a></div>

					<div  style="line-height: 100px;text-align: center;font-size: 16px;">
							<p id="dialog3"></p>
					</div>
			<p class="text-center" >
				<button class="btn btn-primary" id="fanhui3" >关闭</button>
			</p>		
     </div><!--  弹窗  -->

	
		 <!--  弹窗  -->
        <div class="containbox3" id="myModal4" style="width: 1100px;height:800px; margin-top: 0px;z-index:2px; margin-left: 200px;overflow:scroll; border:1px solid;display: none;position: absolute;top: 60px;left: 100px;right: 0px;bottom: 0px; float: left">
           <div class="containTitle2"><a style="float: right;height: 30px;width: 20px;margin-right: 10px;" id="close4">X</a></div>

				<div >
					<img id="imgs"  src=" alt=""  style="width:90%;height:80%;" />
				</div>
			<p class="text-center" >
					<button class="btn btn-primary" id="fanhui4" >关闭</button>
			</p>		
     </div><!--  弹窗  -->
	
	



	
</body>
</html>