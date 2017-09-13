<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>优惠券发放管理</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
    <link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctxStatic}/borrow/standardpowder.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
        	
        	
			
			/*全选事件*/
			$("#controlAll").click(function(){
				 var checklist = document.getElementsByName ("selected");
				   if(document.getElementById("controlAll").checked)
				   {
				   for(var i=0;i<checklist.length;i++)
				   {
				      checklist[i].checked = 1;
				   } 
				 }else{
				  for(var j=0;j<checklist.length;j++)
				  {
				     checklist[j].checked = 0;
				  }
				 }
			});
        	
			
			/*是否发送短信选择事件*/
	        $("input:radio[name='isSendMessage']").change(function () {
	        	
				var messageType= $("input[name='isSendMessage']:checked").val();
				if(messageType=="1")
					{
						$("#montent").show();
					
					}
				else{
					$("#montent").hide();
		
				 }
            });
			

            //$("#name").focus();
            $("#inputForm").validate({
            	
                submitHandler: function(form){
            		var couponGroupName= $("#couponGroupName").val();
    				if(couponGroupName==null ||couponGroupName =="")
    					{
    					   alert("优惠券名称不能为空");
    					  return  false;
    				 }
    				
                    if (checkCouponGroupName(couponGroupName)) {
                        alert("优惠券名称只能中文、数字和英文！");
                        $("#couponGroupName").val("");
                       return false;
                   }
                    
                    if(couponGroupName.length<2)
                    {
                       alert("优惠券名称不能少于两个中文汉字或英文字符");
                      return  false;
                    }
                	
    				//收券人类型--全部用户:all；单个用户:some
    				var holderType= $("#holderType").val();
    				if(holderType=="some")
    					{
 
    				 		var singletonUser= $("#mobilseList").val();
    	    				if(singletonUser==null ||singletonUser =="")
    	    					{
    	    					   alert("单个用户内容不能为空");
    	    					  return  false;
    	    				 }
                            if (segmentSpecial(singletonUser)) {
                                alert("请以英文  ; 分号隔开");
                                return false;
                            }
    					
    					}
    				
    				
 				   if($('#radio1').is(':checked')){
					   var messContent= $("#messageContent").val();
							if(messContent==null ||messContent =="")
    					{
    					   alert("短信内容不能为空");	
    					  return  false;
    				   }
                            if (containSpecial(messContent)) {
                                alert("短信内容不能含有非法字符@#$%^……等！");
                                return false;
                            }
                            var txtLeng = messContent.length;      //把输入字符的长度赋给txtLeng
                            //拿输入的值做判断
                            if( txtLeng>140 ){
                              alert("短信内容不能大于140个字");   
                              return  false;
                            }  
				   }
    	
					var id_array=new Array();  
					$('input[name="selected"]:checked').each(function(){  
					    id_array.push($(this).val());//向数组中添加元素  
					});  
					var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  
					$("#ids").val(idstr);
                	
					var ids= $("#ids").val();
					if(ids==null ||ids =="")
					{
					   alert("请选择优惠券信息");
					  return  false;
				 	}
					
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
          
        });
       
        
		//用户类型改变事件
		function typeChannge()
		{
			//收券人类型--全部用户:all；单个用户:some
			var holderType= $("#holderType").val();
			if(holderType=="some")
				{
					$("#danUser").show();
				}
			else{
				$("#danUser").hide();
	
			 }
		}
		function segmentSpecial(s){      
            var containSpecial =new RegExp("[`~!@#$^&*()=|{}':',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"); 
            return ( containSpecial.test(s) );      
        }  
        function containSpecial(s){      
            var containSpecial = new RegExp("[`~!@#$^&*=|{}\\[\\]<>/~@#￥……&*——|{}]");
               // containSpecial = RegExp("[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)  (\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)(\<)(\>)(\?)(\)]");      
            return ( containSpecial.test(s) );      
        } 
        function checkCouponGroupName(username){
            var reg = new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+$");
               return (!reg.test(username));
        }
        $(function(){
        	var holderType= $("#holderType").val();
            var messageType= $("input[name='isSendMessage']:checked").val();
            if(holderType=="some")
                {
                    $("#danUser").show();
                }
            else{
                $("#danUser").hide();
    
             }
            if(messageType=="1")
                {
                    $("#montent").show();
                
                }
            else{
                $("#montent").hide();
    
             }
        });
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/coupon/couponSend/couponSendList">优惠券发放列表</a></li>
        <li class="active"><a href="javascript:;">修改优惠券发放</a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="couponGroup" action="${ctx}/coupon/couponSend/updateCouponGroupInfo" method="post" class="form-horizontal">
        <sys:message content="${message}"/>     
            <input type="hidden" name="couponGroupId" value="${couponGroup.couponGroupId}"/>
            <input type="hidden" name="couponGroupStatus" value="${couponGroup.couponGroupStatus}"/>
            <input type="hidden" name="remarks" value="${couponGroup.remarks}"/>
        <div class="control-group">
            <label class="control-label">*优惠券名称：</label>
            <div class="controls">
                <form:input path="couponGroupName" value="${couponGroup.couponGroupName}" htmlEscape="false" maxlength="20" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">收券人:</label>
            <div class="controls">
                <form:select path="holderType" htmlEscape="false" maxlength="6" class="input-xlarge " onchange="typeChannge();">
                    <form:option value="all">全部用户</form:option>
                    <form:option value="some">单个用户</form:option>
                </form:select>
            </div>
        </div>
    
        <div class="control-group" id="danUser" >
            <label class="control-label">*单个用户：</label>
            <div class="controls">
                <form:textarea path="mobilseList" value="${couponGroup.mobilseList}" htmlEscape="false"  class="input-xlarge "/>
                <br/><span>填写发放用户手机号,请用";"分号隔开</span>
            </div>
        </div>
       
		<div class="control-group">
			<label class="control-label">选择优惠券：</label>
			<div class="controls">	
			<div id="myModal"style="width: 1000px;height:500px;  top: 40px; left: 65px; padding-top: 30px; overflow: scroll;">
			
			<form:hidden path="ids"  htmlEscape="false"   class="input-xlarge "  />
		<table id="contentTable" border="2px;" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr align="center">
		
			<th><input  type="checkbox"   name="controlAll" style="controlAll" id="controlAll"/>全选<br></th>
				<th>优惠券类型</th>
				<th>优惠券面额(元/%)</th>
				<th>有效期</th>
				<th>使用时间</th>
				<th>出借金额限制(元)</th>
				<th>出借期限限制</th>
				<th>出借端口限制</th>
			</tr>
		</thead>
		<tbody>


	<c:forEach items="${couponAllList}" var="coupon" varStatus="tfdAcc">
		
		<tr align="center">
		 
			<td>
					<c:if test="${coupon.isCheck =='1'}">
						   <input type="checkbox" name="selected" checked="checked" value="${coupon.id}" />
					 </c:if>
					 
					 <c:if test="${coupon.isCheck =='0'}">
						   <input type="checkbox" name="selected" value="${coupon.id}" />
					 </c:if>

			</td>
				<td>
					<c:if test="${coupon.couponTypeId =='fullDown'}">
						满减券
					 </c:if>
					<c:if test="${coupon.couponTypeId =='cash'}">
					现金券
					 </c:if>
					 
					 <c:if test="${coupon.couponTypeId =='interest'}">
						增益券
					 </c:if>
					 
					 <c:if test="${coupon.couponTypeId =='cashBack'}">
						返现券
					 </c:if>
				
				</td>
				<td>${coupon.faceValue}</td>
				<td>
				
					<c:if test="${not empty coupon.effectiveDays}">
						<c:if test="${coupon.effectiveDays=='0'}">
						    无
					  	 </c:if>
						
					   <c:if test="${coupon.effectiveDays!='0'}">
						${coupon.effectiveDays}天
					   </c:if>
					 </c:if>
					 
					<c:if test="${ empty coupon.effectiveDays}">
						无
					 </c:if>
				</td>
				<td>

				 <c:if test="${not empty coupon.beginTime && not empty coupon.endTime }">
						<fmt:formatDate value="${coupon.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>	
							至
						<fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				  </c:if>
					 
					<c:if test="${ empty coupon.beginTime && empty coupon.endTime}">
						无
					  </c:if> 
				</td>

				<td>
					<c:if test="${not empty coupon.loanAmountMin}">
						<c:if test="${ coupon.loanAmountMin=='0.00'}">
							无限制
						 </c:if>
												
						<c:if test="${ coupon.loanAmountMin!='0.00'}">
							${coupon.loanAmountMin}
						 </c:if>
					 </c:if>
					 
					<c:if test="${ empty coupon.loanAmountMin}">
						无
					 </c:if>
				
				</td>
						<td>
					<c:if test="${not empty coupon.loanTermList}">
				
					 	<c:if test="${ coupon.loanTermList =='全部'}">
						无限制
				  		</c:if>
				  		
				  		<c:if test="${ coupon.loanTermList !='全部'}">
						${coupon.loanTermList}
				  		</c:if>
					 </c:if>
				
					<c:if test="${ empty coupon.loanTermList}">
						无
				   </c:if>
				
				</td>
				<td>
				
					<c:if test="${not empty coupon.loanChannelList}">
				
							<c:if test="${ coupon.loanChannelList =='全部'}">
							无限制
						  </c:if>
					  
					  		<c:if test="${ coupon.loanChannelList !='全部'}">
							${coupon.loanChannelList}
						  </c:if>
					</c:if>
					 
					<c:if test="${ empty coupon.loanChannelList}">
						无
				   </c:if>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</div>  
</div>
</div>
 
        <div class="control-group">
            <label class="control-label">*是否发送短信：</label>
            <div class="controls">
         <%--        <form:radiobutton path="isSendMessage" value="1"  htmlEscape="false"  maxlength="4" class="input-xlarge "/>是
                <form:radiobutton path="isSendMessage" value="0" htmlEscape="false" maxlength="4" class="input-xlarge "/>否
                 --%>
                
                         <c:if test="${couponGroup.isSendMessage ==1}">
				                 <form:radiobutton path="isSendMessage" value="1" id="radio1" checked="true"   htmlEscape="false" check="true" maxlength="4" class="input-xlarge "/>是
				                  <form:radiobutton path="isSendMessage" value="0" id="radio2" htmlEscape="false" maxlength="4"   class="input-xlarge "/>否
					  </c:if>
					<c:if test="${couponGroup.isSendMessage ==0}">
					                 <form:radiobutton path="isSendMessage" value="1"  htmlEscape="false"  maxlength="4" class="input-xlarge "/>是
				            <form:radiobutton path="isSendMessage" value="0" htmlEscape="false" maxlength="4" check="true"  class="input-xlarge "/>否
					 </c:if>
                
                
            </div>
        </div>
        <div class="control-group" id="montent">
            <label class="control-label">短信内容：</label>
            <div class="controls">
                <form:textarea path="messageContent" value="${couponGroup.messageContent}" htmlEscape="false" maxlength="140" class="input-xlarge" style="width:940px;height:102px;" />
                <br/><span>输入短信为140字以内</span>
            </div>
        </div>
        
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="确 定"/>
            <input id="btnCancel" class="btn" type="button" value="取 消" onclick="history.go(-1)"/>
        </div>
    </form:form>
    
</body>
</html>