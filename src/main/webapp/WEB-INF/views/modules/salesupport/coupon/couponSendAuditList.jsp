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
<body id="aaa">
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/coupon/couponSend/couponSendList">优惠券发放列表</a></li>
        <li class="active"><a href="${ctx}/coupon/couponSend/couponSendAuditList">优惠券发放审核列表</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="couponGroup" action="${ctx}/coupon/couponSend/couponSendAuditList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <input type="hidden" name="couponGroupId" value="${couponGroup.couponGroupId}"/>
        <input id="endLimit" name="endLimit" type="hidden" value=""/>
        <ul class="ul-form">
            <li><label>优惠券名称：</label>
                <form:input path="couponGroupName" htmlEscape="false" maxlength="20" class="input-medium"/>
                
            </li>
            <li><label>审核状态：</label>
                <%-- <form:input path="biztype" htmlEscape="false" maxlength="20" class="input-medium"/> --%>
                <form:select path="couponGroupStatus" class="input-medium">
                    <form:option value="" label="全部"/><!--0未发送;1 审核中(待审核); 2审核未通过;3 已发送  -->
                    <form:option value="1" label="待审核"/>
                    <form:option value="2" label="审核未通过"/>
                    <form:option value="3" label="已发送"/>
                    <%-- <form:options items="${fns:getDictList('CouponGroupStatus')}" itemLabel="label" itemValue="value" htmlEscape="false"/> --%>
                </form:select>
            </li>
            <li><label>发送时间：</label>
                <input name="startSendDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${couponGroup.startSendDate}" 
                   id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>至
                <input name="endSendDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"   value="${couponGroup.endSendDate}"  
                    id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            </li>
            <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
            </li>
            <li class="clearfix"></li>
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
                <%-- <shiro:hasPermission name="compaccount:couponGroup:edit"><th>操作</th></shiro:hasPermission> --%>
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
                    <c:if test="${'1'==couponGroup.isSendMessage}">是</c:if>
                    <c:if test="${'0'==couponGroup.isSendMessage}">否</c:if>
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
                
                <td>${fns:getDictLabel(couponGroup.couponGroupStatus,'CouponGroupStatus','')}</td>
                <td>
                    <a href="${ctx}/coupon/couponSend/sendAuditDetail?couponGroupId=${couponGroup.couponGroupId}">查看</a>
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
