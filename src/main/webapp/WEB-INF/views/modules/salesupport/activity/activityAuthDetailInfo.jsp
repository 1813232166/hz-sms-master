<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			$("#myModal").hide();
			$("#confirmreturn").hide();
			$("#publishSuccess").hide();
			
			
			
			$(".noPass").click(function(){
				$("#dialog2").show();
			});
			
			
			$(".pass").click(function(){
				$("#dialog2").hide();
			});
			
			
			$("#fanhui1").click(function(){
				$("#myModal").hide();
				
				
				var ids=$("#id").val();
				window.location.href= ctx+"/activity/activityInfo/getAuthDetail?id="+ids+"";
			});
			$("#confirmreturn").click(function(){
				$("#myModal").hide();
				
			});
			$("#close1").click(function(){
				$("#myModal").hide();
				
				var ids=$("#id").val();
				window.location.href= ctx+"/activity/activityInfo/getAuthDetail?id="+ids+"";
			});
			
			
			$("#btnSubmit").click(function(){
				//$("#dialog1").text("您确定要提交审核吗？");
				$("#confirmreturn").hide();
				$("#publishSuccess").show();
				$("#myModal").modal('show');
				
			});
			
			$("#publishSuccess").click(function(){
				var id=$("#id").val();
				var status= $("input[name='status']:checked").val();
				var remarks=$("#remarks").val();

			
					if(status==null ||status =="")
							{
							   alert("请选择审核状态");
							  return  false;
						 }
				   if($('#noPass').is(':checked')){
						if(remarks==null ||remarks =="")
							{
							   alert("审核意见不能为空");
							  return  false;
						 }
					
				   }
				
				$.post(ctx+'/activity/activityInfo/authHuoDongStatus',
						{id:id,
					   activitystatus:status,
					   remarks:remarks
					     },
						function(f){
					if(f){
						
						$("#publishSuccess").hide();
						$("#dialog2").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("审核成功");
						
						setTimeout(window.location.href= ctx+"/activity/activityInfo/authList",3000)	
						
					}else{
						$("#publishSuccess").hide();
						$("#dialog2").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("审核失败");
					}
				},"json")
		
			});
			
			
			
	
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/activityInfo/authList">活动审核列表</a></li>
		<li class="active"><a href="javascript:;">活动审核详情</a></li>
	      
	</ul><br/>
		<form:form id="inputForm" modelAttribute="activityInfo" action="${ctx}/activity/activityInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">活动名称：</label>
			<div class="controls">
			    <input id="id" name="id" type="hidden" value="${activityInfo.id}"/>
				<span>${activityInfo.activityname}</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">发送优惠券：</label>
			<div class="controls">
			     <table id="couponContent" style="text-align: center; width: 800px;" border="1px; ">
		      <tr align="center"><td>优惠券信息</td>   </tr>

		    	<c:forEach items="${listInfo}" var="coupon" varStatus="tfdAcc">
					  <tr align="center"><td>
							${coupon.couponTypeId}&nbsp; 
						 	        面额${coupon.faceValue}元&nbsp; 
					 	    
									 	       <c:if test="${empty coupon.effectiveDays}"> 
				                		    有效期:无&nbsp; &nbsp; 
				               	</c:if>
				                <c:if test="${not empty coupon.effectiveDays}">  
				     				 	
				     				<c:if test="${coupon.effectiveDays=='0'}">  
				     				 	有效期:无&nbsp; &nbsp; 
				               		 </c:if>
				               		 
				               		<c:if test="${coupon.effectiveDays!='0'}">  
				     				 	有效期: ${coupon.effectiveDays} 天  &nbsp; &nbsp; 
				               		 </c:if>
				     				 	
				                </c:if>
				                
				                					 	    
					 	       <c:if test="${coupon.beginTime=='' &&  coupon.endTime==''}"> 
				                		    使用时间:无&nbsp; &nbsp; 
				               	</c:if>
				                <c:if test="${coupon.beginTime !='' && coupon.endTime !=''}">  
				     				   使用时间: ${coupon.beginTime} 至 ${coupon.endTime} &nbsp; &nbsp; 
				                </c:if>
				                
					 	    	<c:if test="${coupon.loanAmountMin=='0.00'}">  
				     				 	 出借金额限制:无限制 &nbsp;&nbsp;  
				               </c:if>
					 	      	<c:if test="${coupon.loanAmountMin!='0.00'}">  
				     				 出借金额限制 :${coupon.loanAmountMin}元&nbsp; &nbsp; 
				               </c:if>
				               
				                   	<c:if test="${coupon.loanTermList=='全部'}">  
				     				 	出借期限限制:无限制 &nbsp;&nbsp;  
				               </c:if>
					 		   	<c:if test="${coupon.loanTermList!='全部'}">  
				     				 	出借期限限制:${coupon.loanTermList}&nbsp;&nbsp;  
				               </c:if>
				               
				                      <c:if test="${coupon.loanChannelList=='全部'}">  
				     				 	出借端口限制:无限制&nbsp; &nbsp; 
				               </c:if>
				                  <c:if test="${coupon.loanChannelList!='全部'}">  
				     				 	出借端口限制:${coupon.loanChannelList}&nbsp; &nbsp; 
				               </c:if>
					  </td></tr>
		 	  </c:forEach>
		     
		     </table>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">活动简介：</label>
			<div class="controls">
					<span>${activityInfo.introduction}</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">活动类型：</label>
			<div class="controls">
			${fns:getDictLabel(activityInfo.activitytype, 'activityType', '')}
	
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">活动开始时间：</label>
			<div class="controls">
					<span><fmt:formatDate value="${activityInfo.begintime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动结束时间：</label>
			<div class="controls">
				<span><fmt:formatDate value="${activityInfo.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
			<span><fmt:formatDate value="${activityInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
		</div>
		
				<div class="control-group">
			<label class="control-label">审核时间:</label>
			<div class="controls">
			<span><fmt:formatDate value="${activityInfo.authtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
		</div>
		
					<div class="control-group">
			<label class="control-label">发布时间:</label>
			<div class="controls">
			<span><fmt:formatDate value="${activityInfo.publishtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
			</div>
		</div>
		
		
		
		<div class="control-group">
			<label class="control-label">活动状态:</label>
			<div class="controls">
			${fns:getDictLabel(activityInfo.activitystatus, 'activityStatus', '')}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
			<span>${activityInfo.remarks}</span>
			</div>
		</div>

		<div class="form-actions">
		
					<c:if test="${activityInfo.activitystatus =='1'}">
						  <input id="btnSubmit" class="btn btn-primary" type="button" value="审  核"/>
					 </c:if>
					 
	
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

 <!-- 模态框声明 -->
	<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" style="width: 600px;">
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					
					<button class="close" id="close1"><span>&times;</span></button>
					<h4 class="modal-title">友好提示</h4>
				</div>
				<div class="modal-body"  style="height:120px;" align="center">
					<div>
								<p id="dialog1">
								<label><input name="status" type="radio" value="3" checked="true"  id="pass" class="pass" />审核通过 </label>
								<label><input name="status" type="radio" value="2"  id="noPass" class="noPass" />审核不通过 </label><br/> &nbsp;
								</p>
								<p id="dialog2" style="display: none;">
								<label>审核意见 </label>
								 <textarea id="remarks" rows="4" cols="5" style="width: 150px;height: 60px;"></textarea>
								</p>
					</div>
				</div>
				<div class="modal-footer" align="center">
					<p class="text-center">
						<button class="btn btn-primary" id="confirmreturn" >确定</button>
						<button class="btn btn-primary" id="publishSuccess" >确定</button>
						
						<button class="btn btn-primary" id="fanhui1">取消</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
	
	
	
</body>
</html>