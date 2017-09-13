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
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#newCouponGroup").click(function(){
                $("#searchForm").attr("action","${ctx}/coupon/couponSend/toSaveUpdate");
                $("#searchForm").submit();
            })
            

			/*提交查询*/
			$("#btnSubmit").click(function(){

				   var  starttime1=  $("#startSendDate").val();
				   var endtime1 = $("#endSendDate").val();

				   if(starttime1!=null&&starttime1!="" && endtime1!=null&&endtime1!="") {
					   
					   if(starttime1>endtime1){
						    alert("发送开始时间不能大于发送结束时间");
						    $("#publishtBeginTime").val("");
						    $("#publishtEndTime").val("");
						   return false;
					    }
				     }
				
				$("#searchForm").attr("action","${ctx}/coupon/couponSend/couponSendList");
				$("#searchForm").submit();
			});
			
            
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        function Lock_CheckForm(){
            $(".refreshBg").hide();
            $("#containbox2Id").hide();
        }
        //弹出框
        function findCoupon(couponGroupId){
            var htmlStr="";
            
             $.ajax({
                     url: "${ctx}/coupon/couponSend/getRelCoupon",
                     type: "POST",
                     data: {
                         "couponGroupId": couponGroupId
                   },
                 dataType: "JSON",
                 success: function (result) {
                        $("#couponDet").html("");
                            $.each(result, function (i, a) {

                                if ('interest'==a.couponTypeId) {
                                    couponType="增益券";
                                }else if('fullDown'==a.couponTypeId)
                                	{
                                		couponType="满减券";
                                	}else if('cash'==a.couponTypeId)
                                	{
                                		couponType="现金券";
                                	}else if('cashBack'==a.couponTypeId){
                                		couponType="返现券";
                                	}
                                htmlStr += "<tr>";
                                htmlStr +="<td>"+(i+1)+"</td>";
                                htmlStr += "<td>" +couponType+ "</td>";
                                htmlStr +="<td>" +parseFloat(a.faceValue)+"</td>";
                  
                              if(0==a.effectiveDays){
  									htmlStr+="<td>"+"无"+"</td>";
  							 }else if(parseInt(a.effectiveDays)==0 ){
                     				htmlStr+="<td>"+"无"+"</td>";
  							 }else{
  							     htmlStr += "<td>" +parseInt(a.effectiveDays)+ "天</td>";
  							 }
           
                             if (undefined!=a.beginTime&&(undefined!=a.endTime)&&(''!=a.beginTime) && (''!=a.endTime) ) {
					        	       htmlStr += "<td>" +a.beginTime+"至"+a.endTime+ "</td>";
								}else{
								     
	                                htmlStr += "<td>" +"无"+ "</td>";
									
								}
                             
                           	 if(parseFloat(a.loanAmountMin)==0.00){
                    				htmlStr+="<td>"+"无限制"+"</td>";
 							 }else{
 							      htmlStr +="<td>" +parseFloat(a.loanAmountMin)+"</td>";
 							 }
                                            
                   			 if(a.loanTermList=="全部" ){
                   				htmlStr+="<td>"+"无限制"+"</td>";
							 }else{
								 htmlStr+="<td>"+a.loanTermList+"</td>";
							 }
                   			 if(a.loanChannelList=="全部" ){
                    				htmlStr+= "<td>" +"无限制"+"</td>";
 							 }else{
 								 htmlStr+= "<td>" +a.loanChannelList+"</td>";
 							 }
                   			 
                             htmlStr+=   "</tr>";
   
                            });
                        $("#couponDet").append(htmlStr);
                 }
             });
            $(".refreshBg").show();
            $("#containbox2Id").show();
        }
        
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/coupon/couponSend/couponSendList">优惠券发放列表</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="couponGroup" action="${ctx}/coupon/couponSend/couponSendList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <input type="hidden" name="couponGroupId" value="${couponGroup.couponGroupId}"/>
        <input id="endLimit" name="endLimit" type="hidden" value=""/>
        <ul class="ul-form">
            <li><label>优惠券名称：</label>
                <form:input path="couponGroupName" htmlEscape="false" maxlength="20" class="input-medium"/>
            </li>
            <li><label>发送状态：</label>
                <%-- <form:input path="biztype" htmlEscape="false" maxlength="20" class="input-medium"/> --%>
                <form:select path="couponGroupStatus" class="input-medium">
                    <form:option value="" label="全部"/><!--0未发送;1 审核中(待审核); 2审核未通过;3 审核通过已发送  -->
                    <form:options items="${fns:getDictList('CouponGroupStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </li>
            <li><label>发送时间：</label>
			 
			 	<input  name="startSendDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${couponGroup.startSendDate}" 
                   id="startSendDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endSendDate\');}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>至
                <input  name="endSendDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"   value="${couponGroup.endSendDate}"  
                   id="endSendDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startSendDate\');}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>		
                    
               <input  class="btn btn-primary" type="button" value="新建优惠券发放" id="newCouponGroup" style="margin-right: 20px;"/>
				<input id="btnSubmit" class="btn btn-primary" type="button" value="查询" style="margin-right: 20px;"/>
            </li>
 
 
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>ID</th>
                <th>优惠券名称</th>
                <th>优惠券</th>
                <th>发送人数</th>
                <th>发送短信</th>
                <th>短信内容</th>
                <th>创建时间</th>
                <th>发送时间</th>
                <th>审核时间</th>
                <th>发送状态</th>
                <th>操作</th>
                <%-- <shiro:hasPermission name="compaccount:coupon:edit"><th>操作</th></shiro:hasPermission> --%>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="couponGroup" varStatus="tfdAcc">
            <tr>
                <td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
                <td>${couponGroup.couponGroupName}</td>
                <td><a href="javascript:void(0);" onclick="findCoupon('${couponGroup.couponGroupId}')">查看</a></td>
                <td>${couponGroup.userCount}</td>
                <td>
                      <c:if test="${couponGroup.isSendMessage ==1}">
				     	是
					  </c:if>
					<c:if test="${couponGroup.isSendMessage ==0}">
					     否
					 </c:if>
                
                </td>
                <td>
                
                   <c:if test="${empty couponGroup.messageContent}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty couponGroup.messageContent}">  
     				 	 ${couponGroup.messageContent}
                </c:if>
                
                </td>
                <td>
                
                  <c:if test="${empty couponGroup.createDate}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty couponGroup.createDate}">  
     				 <fmt:formatDate value="${couponGroup.createDate}" pattern="yyyy-MM-dd HH:mm"/>
                </c:if>
                
             </td>
             
                 <td>
                 <c:if test="${empty couponGroup.sendTime}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty couponGroup.sendTime}">  
     				 <fmt:formatDate value="${couponGroup.sendTime}" pattern="yyyy-MM-dd HH:mm"/>
                </c:if> 
                
                </td>
             
                <td>
                 <c:if test="${empty couponGroup.auditTime}"> 
                		         无
               	</c:if>
                  <c:if test="${not empty couponGroup.auditTime}">  
     				 <fmt:formatDate value="${couponGroup.auditTime}" pattern="yyyy-MM-dd HH:mm"/>
                </c:if>
               </td>

                <td>${fns:getDictLabel(couponGroup.couponGroupStatus, 'CouponGroupStatus', '')}</td>
                
                <td>
                
                       <c:if test="${couponGroup.couponGroupStatus==0}"> 
                		  <a href="${ctx}/coupon/couponSend/deleteCouponGroup?couponGroupId=${couponGroup.couponGroupId}" onclick="return confirmx('确认要删除该优惠券组的信息吗？', this.href)">删除</a>
                  		  <a href="${ctx}/coupon/couponSend/editCouponSendInfo?couponGroupId=${couponGroup.couponGroupId}">修改</a>
                   		 <a href="${ctx}/coupon/couponSend/sendDetail?couponGroupId=${couponGroup.couponGroupId}">查看</a>
               			</c:if>
               			
               			
               			<c:if test="${couponGroup.couponGroupStatus==1}"> 
                   		 <a href="${ctx}/coupon/couponSend/sendDetail?couponGroupId=${couponGroup.couponGroupId}">查看</a>
               			</c:if>
               			
               			 <c:if test="${couponGroup.couponGroupStatus==2}"> 
                  		  <a href="${ctx}/coupon/couponSend/editCouponSendInfo?couponGroupId=${couponGroup.couponGroupId}">修改</a>
                   		 <a href="${ctx}/coupon/couponSend/sendDetail?couponGroupId=${couponGroup.couponGroupId}">查看</a>
               			</c:if>
               			               			
               			 <c:if test="${couponGroup.couponGroupStatus==3}"> 
                   		 <a href="${ctx}/coupon/couponSend/sendDetail?couponGroupId=${couponGroup.couponGroupId}">查看</a>
               			</c:if>
   				
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
    
    <!--  弹窗  -->
            <div class="containbox3" id="containbox2Id" style="width: 900px;height:490px; margin-top: 0px;z-index:2px; margin-left: 100px;overflow:scroll; border:1px solid;display: none;position: absolute;top: 10px;left: 0px;right: 0px;bottom: 0px;">
               <div class="containTitle2"><a style="float: right;height: 30px;width: 20px;margin-right: 10px;" onclick="Lock_CheckForm();">X</a></div>
               <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                   <tr>
                <th>ID</th>
                <th>优惠券类型</th>
				<th>优惠券面额(元/%)</th>
                <th>有效期</th>
                <th>使用时间</th>
                <th>出借金额限制（元）</th>
                <th>出借期限限制</th>
                <th>出借端口限制</th>
         
            </tr>
        </thead>
                <tbody id="couponDet">
                
               </tbody> 
               </table>
     </div><!--  弹窗  -->
     <div class="refreshBg" style="display:none; position: fixed; top: 0; left: 0; opacity: 0.3; background: black; width: 100%; height: 100%;"></div>
</body>
</html>