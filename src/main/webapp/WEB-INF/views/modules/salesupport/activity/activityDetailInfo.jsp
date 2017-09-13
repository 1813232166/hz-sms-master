<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/salesupport/couponsend.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			$("#myModal").hide();
			$("#confirmreturn").hide();
			$("#publishSuccess").hide();
			
			
			$("#fanhui1").click(function(){
				$("#myModal").hide();
				
				var ids=$("#id").val();
				window.location.href= ctx+"/activity/activityInfo/getDetail?id="+ids+"";
			});
			$("#confirmreturn").click(function(){
				$("#myModal").hide();
				
			});
			$("#close1").click(function(){
				$("#myModal").hide();
				
				var ids=$("#id").val();
				window.location.href= ctx+"/activity/activityInfo/getDetail?id="+ids+"";
			});
			
			
			$("#btnSubmit").click(function(){
				$("#dialog1").text("您确定要提交审核此条记录吗？");
				$("#confirmreturn").hide();
				$("#publishSuccess").show();
				$("#myModal").modal('show');
				
			});
			
			$("#publishSuccess").click(function(){
				var id=$("#id").val();
				$.post(ctx+'/activity/activityInfo/publishHuoDong',{id:id},function(f){
					if(f){
						$("#publishSuccess").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("发布成功");
						
						setTimeout(window.location.href= ctx+"/activity/activityInfo/list",3000)	
						
					}else{
						$("#publishSuccess").hide();
						$("#confirmreturn").show();
						$("#dialog1").text("发布失败");
					}
				},"json")
			});
			
			//返回列表页面
			$("#btnCancel").click(function(){
				window.location.href= ctx+"/activity/activityInfo/list";
				});
			
			
	        //导出统计数据
	        $("#exportCoupon").click(function () {
	        	var activityId='${activityInfo.id}';
	        	window.location.href=ctx+"/activity/activityInfo/exportCoupon?activityId="+activityId+"&&endLimit="+20000;
			})
			
	
		});
		
        function Lock_CheckForm(){
            $(".refreshBg").hide();
            $("#containbox2Id").hide();
        }
        function usedCoupon(obj){
        	var activityId='${activityInfo.id}';
        	var url="${ctx}/coupon/couponSend/getUsedCoupon"
        	var couponSend = new CouponSend(obj,"",activityId,url);
            couponSend.showUsedCoupon();
        }
		
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#staticsForm").submit();
            return false;
        }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/activityInfo/list">活动列表</a></li>
	    <li class="active"><a href="javascript:;">活动详情</a></li>
	      
	</ul><br/>
		<form:form id="inputForm" modelAttribute="activityInfo" action="${ctx}/activity/activityInfo/save" method="post" class="form-horizontal">
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
		     <table id="couponContent" style="text-align: center; width: 800px;" border="1px; " >
		      <tr align="center"><td>优惠券信息</td>   </tr>

		    <c:forEach items="${listInfo}" var="coupon" varStatus="tfdAcc">
					  <tr align="center"><td>
							${coupon.couponTypeId}&nbsp; &nbsp; 
					 	        面额${coupon.faceValue}元&nbsp; &nbsp; 
					 	    
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
		
					<c:if test="${activityInfo.activitystatus ==0}">
					<input id="btnSubmit" class="btn btn-primary" type="button" value="提交审核"/>&nbsp;
					</c:if>
		
				   <c:if test="${activityInfo.activitystatus ==2}">
					<input id="btnSubmit" class="btn btn-primary" type="button" value="提交审核"/>&nbsp;
					</c:if>
			
			<input id="btnCancel" class="btn" type="button" value="返 回" />
		</div>
	</form:form>



 <!--发送用户数：xA券使用数量：xB券使用数量：xC券使用数量：xD券使用数量：x-->
     <div class="Mtable2">
             <form id="staticsForm" action="${ctx}/activity/activityInfo/getDetail" method="post" >
            <input id="pageNo" name="pageNo" type="hidden" value="${couponpage.pageNo}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${couponpage.pageSize}"/>
            <input id="id" name="id" type="hidden" value="${activityInfo.id}"/>
           <!-- <input id="endLimit" name="endLimit" type="hidden" value="20000"/> -->
        </form>
     
        <ul class="ul-form">
            <li style="float: left;">发送用户数：${couponpage.count}</li>
            <li><input  style="float: right;" class="btn btn-primary" type="button" value="导 出" id="exportCoupon"/></li>
            <!-- 
            <li style="float: left;">A券使用数量：</li>
            <li style="float: left;">B券使用数量：</li>
            <li style="float: left;">C券使用数量：</li>
            <li style="float: left;">D券使用数量：</li>
            -->        
       </ul>
       <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>ID</th>
                <th>手机号</th>
                <th>发送优惠券数量</th>
                 <th>发送时间</th>
                <th>已使用优惠券数量</th>
                <th>未使用优惠券数量</th>
                <th>已过期优惠券数量</th>
                <%-- <shiro:hasPermission name="compaccount:coupon:edit"><th>操作</th></shiro:hasPermission> --%>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${couponpage.list}" var="coupon" varStatus="tfdAcc">
            <tr>
                <td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
             	 <td>${coupon.userMobile}</td>
                 
                 
            <%--       <td> ${fn:substring(coupon.userMobile,0,3)}****${fn:substring(coupon.userMobile,7,11)} <td> --%>
                <td>${coupon.sentCouponSum}</td>
                <td><fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
 <%--                <td>${coupon.usedCouponSum}</td> --%>
                 <td><a href="javascript:;" id="usedCouponSum" onclick="usedCoupon(${coupon.allMobile})">${coupon.usedCouponSum}</a></td>
                <td>${coupon.unusedCouponSum} </td>
                <td>${coupon.expiredCouponSum}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${couponpage}</div>
  </div>



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
				<div class="modal-footer" align="center">
					<p class="text-center">
						<button class="btn btn-primary" id="confirmreturn" >确定</button>
						<button class="btn btn-primary" id="publishSuccess" >确定</button>
						<button class="btn btn-primary" id="fanhui1" >取消</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
	
	
	
   <!--  弹窗  -->
            <div class="containbox3" id="containbox2Id" style="width: 900px;height:490px;z-index:16; border:1px solid;display: none;position: absolute;left: 0px; right:0; top:0;overflow:scroll; margin-left:100px; margin-top:450px;background: #fff;">
               <div class="containTitle2"><a style="float: right;height: 30px;width: 20px;margin-right: 10px;" onclick="Lock_CheckForm();">X</a></div>
               <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                   <tr>
                <th>ID</th>
                <th>优惠券类型</th>
                <th>优惠券面额（元/%）</th>
                <th>有效期</th>
                <th>使用期限</th>
                <th>出借金额限制（元）</th>
                <th>出借期限限制</th>
                <th>出借端口限制</th>
                <th>使用时间</th>
                <!-- <th>标的名称</th> -->
                <%-- <shiro:hasPermission name="compaccount:coupon:edit"><th>操作</th></shiro:hasPermission> --%>
            </tr>
        </thead>
                <tbody id="couponUseDet">
                
               </tbody> 
               </table>

     </div><!--  弹窗  -->
     <div class="refreshBg" style="display:none; position: fixed; top: 0; left: 0; opacity: 0.3; background: black; width: 100%; height: 100%;"></div>
</body>
</html>