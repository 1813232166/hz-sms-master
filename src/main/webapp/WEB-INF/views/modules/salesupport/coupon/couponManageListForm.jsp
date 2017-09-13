<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>优惠券管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
    
    	
    
        $(document).ready(function() {
        	
        	
        		
        	$("#radio1").click(function(){
        		$("#beginTime").attr("disabled",true);
        		$("#endTime").attr("disabled",true);
        		$("#effectiveDays").attr("disabled",false);
        		$("#beginTime").val("");
        		$("#endTime").val("");
        		
        	})
        	$("#effectiveDays").click(function(){
        		$("#beginTime").attr("disabled",true);
        		$("#endTime").attr("disabled",true);
        		$("#effectiveDays").attr("disabled",false);
        		$("#beginTime").val("");
        		$("#endTime").val("");
        		$("#radio1").attr("checked","checked");
        	})
        	$("#radio2").click(function(){
        		$("#effectiveDays").attr("disabled",true);
        		$("#beginTime").attr("disabled",false);
        		$("#endTime").attr("disabled",false);
        		$("#effectiveDays").val("");
        	})
        		$("#endTime").click(function(){
        		$("#effectiveDays").attr("disabled",true);
        		$("#beginTime").attr("disabled",false);
        		$("#endTime").attr("disabled",false);
        		$("#effectiveDays").val("");
        		$("#radio2").attr("checked","checked");
        	})
        	$("#beginTime").click(function(){
        		$("#effectiveDays").attr("disabled",true);
        		$("#beginTime").attr("disabled",false);
        		$("#endTime").attr("disabled",false);
        		$("#effectiveDays").val("");
        		$("#radio2").attr("checked","checked");
        	})
        	
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                	var reg = /^[\+]?\d+(\.d+)?$/;
                	 var pattern =/^[0-9]+([.]\d{1,2})?$/;//小数点后两位
                	 var pat=/^[0-9]*[1-9][0-9]*$/ ;//正整数
                	var cptype= $("#couponTypeIdS").val();
					var faceValue= $("#faceValue").val();
					if(faceValue==null ||faceValue =="")
						{
						   alert("优惠券面额不能为空");
						  return  false;
					 }else{
						 if(isNaN(faceValue) || !pattern.test(faceValue)){
								
							  alert("优惠券面额必须为数字保留小数点后两位");
							  return  false;
						  }else{
							  if(cptype=="cashBack"){
								  if(faceValue<0.01 || faceValue>20000){
									  alert("优惠券面额返现券：0.01-20000元");
									  return false;
								  }
							  }else if(cptype=="interest"){
								  if(faceValue<0.01 || faceValue>5){
									  alert("优惠券面额利息券：0.01-5.00%");
									  return false;
								  }
								  
							  }
						  }
					 }
					   if($('#radio1').is(':checked')){
				     		var effectiveDays= $("#effectiveDays").val();
							if(effectiveDays==null ||effectiveDays =="")
								{
								   alert("有效期不能为空");
								  return  false;
							 }
							if(isNaN(effectiveDays) || !pat.test(effectiveDays)){
								
								  alert("有效期必须为大于0的数据");
								  return  false;
							  }
					   }
					   if($('#radio2').is(':checked')){
				       		var beginTime= $("#beginTime").val();
							if(beginTime==null ||beginTime =="")
								{
								   alert("使用期限 开始时间不能为空");
								  return  false;
							  }
					   		var endTime= $("#endTime").val();
							if(endTime==null ||endTime =="")
								{
								   alert("使用期限结束时间不能为空");
								  return  false;
							 }
							
							   if(beginTime!=null&&beginTime!="" && endTime!=null&&endTime!="") {
								   
								   if(beginTime>endTime){
									    alert("使用期限开始时间不能大于使用期限结束时间");
									   return false;
								    }
							     }
					   }
				 	   var loanAmountMin= $("#loanAmountMin").val();
		             	if(loanAmountMin==""||loanAmountMin==null){
		            		alert('出借金额限制不能为空');
		            		return false;
		            	}else{
		            		if(isNaN(loanAmountMin) || loanAmountMin<0 || !pattern.test(loanAmountMin)){
		            			alert('出借金额限制必须为数字保留小数点后两位');
			            		return false;
		            		}
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
          
            //提交验证
            $("#btnSubmit").click(function(){
            	
            	/* var loanChannelListH= $("#loanChannelListH").val();
            	var loanTermListS=$("#loanTermListS").val();
            	if(loanChannelListH==""||loanChannelListH==null){
            		
            		layer.msg('出借端口限制不能为空');
            		return false;
            	}
             if(loanTermListS==""||loanTermListS==null){
            		
            		layer.msg('出借期限限制不能为空');
            		return false;
            	} */
            	/* var couponTypeIdS="";
            	var loanTermListS="";
            	var loanChannelListS="";
            	$("#couponTypeIdS :selected").each(function(){
            		couponTypeIdS +=$(this).val();
            	});
            	$("#loanTermListS :selected").each(function(){
            		loanTermListS += $(this).val();
                });
            	$("#loanChannelListS :selected").each(function(){
            		loanChannelListS += $(this).val();
                });
            	$("#couponTypeIdH").val(couponTypeIdS);
            	$("#loanTermListH").val(loanTermListS);
            	$("#loanChannelListH").val(loanChannelListS); */
            	// 创建优惠券
            	$("#inputForm").submit();
            });
            $("#couponTypeIdS").click(function(){
            	var cptype= $("#couponTypeIdS").val();
            	$("#fvText").text("");
                if ('cashBack'==cptype) {
                    $("#fvText").append("元");
                }
                if ('interest'==cptype) {
                    $("#fvText").append("%");
                }
            	
            })
            
           /* $('#loanTermListS').change(function(){
            	var cptype= $("#chujieqixian .select2-choices").text();
            	alert(cptype);
            	
            	if("全部"==cptype){
            		$("#chujieqixian").empty();
            	}
            	
            }) */
            
        });
   $(function(){
        	 var cptype= $("#couponTypeIdS").val();//
        	 $("#fvText").text("");
             if ('cashBack'==cptype) {
                 $("#fvText").append("元");
             }
             if ('interest'==cptype) {
                 $("#fvText").append("%");
             }
    }) 
    var idFlag="";
    function ddl_change(idv){
    	idFlag=idv;
	   var idNW="#s2id_"+idv;
	    var select = document.getElementById(idv);
	    var str = [];
	    for(i=0;i<select.length;i++){
	        if(select.options[i].selected){
	            str.push(select[i].value);
	        }
	    }
	    if (str.indexOf("全部")>=0) {
	       // select.length=0;
	       for(i=0;i<select.length;i++){
	            select.options[i].selected=false;
	       }
	      $(idNW+" .select2-search-choice").remove();
	      select.options[str.indexOf("全部")].selected=true;
	      var htm="<li class='select2-search-choice' onclick='delAll(this)' ><div>全部</div><a href='#' onclick='return false;' class='select2-search-choice-close' tabindex='-1'></a></li>"
	    	  $(idNW+" .select2-choices").prepend(htm);
		}
	   // alert(str);
    }
    function delAll(obj){
    	obj.remove();
    	var select = document.getElementById(idFlag);
    	for(i=0;i<select.length;i++){
            select.options[i].selected=false;
       }
    }
    </script>
</head>
<body onload="initFaceValue()">
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/coupon/couponManage/couponManageList">优惠券列表</a></li>
        <li class="active"><a href="javascript:;">新建优惠券</a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="coupon" action="${ctx}/coupon/couponManage/saveUpdateCoupon" method="post" onsubmit="return docheck()" class="form-horizontal">
        <sys:message content="${message}"/>     
            <input type="hidden" name="id" value="${coupon.id}"/>
            
		<div class="control-group">
		      <label class="control-label">*优惠券类型</label>
			<div class="controls">
				<form:select path="couponTypeId" class="input-xlarge "  id="couponTypeIdS" >
<%-- 					<form:option value="" label=""/> --%>
					<form:options items="${fns:getDictList('couponType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		

        <div class="control-group"><!-- 返现券：0.01-20000元利息券：0.01-5.00%-->
            <label class="control-label">*优惠券面额：</label>
            <div class="controls">
                <form:input path="faceValue" value="${coupon.faceValue}"   htmlEscape="false" class="input-xlarge "/>
					<em  id="fvText">  
					</em>
            </div>
        </div>
        <div class="control-group" >
            <label class="control-label"><input type="radio"  <c:if test="${coupon.statusshow=='1'}">checked</c:if> name="statusshow"  id="radio1" value="1" checked/>有效期：</label>
            <div class="controls">
                <form:input  path="effectiveDays" name="effectiveDays" id="effectiveDays" value="${coupon.effectiveDays}"   htmlEscape="false" maxlength="3" class="input-xlarge " />天
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><input <c:if test="${coupon.statusshow=='2' }">checked</c:if> type="radio" name="statusshow" id="radio2" value="2"/>使用期限：</label>
            <div class="controls">
                <input   name="beginTime"  id="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                    value="<fmt:formatDate value="${coupon.beginTime}" pattern="yyyy-MM-dd"/>"
                    onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\');}',minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',isShowClear:true});"/>至
                <input   name="endTime" id="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                    value="<fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd HH:mm"/>"
                    onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\');}',dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*出借金额限制：</label>
            <div class="controls">
                <form:input path="loanAmountMin" value="${coupon.loanAmountMin}"  htmlEscape="false" class="input-xlarge "/>元
            </div>
        </div>
        <div class="control-group" >
            <label class="control-label">*出借期限限制</label>
            <div class="controls" id="chujieqixian">
            <input type="hidden" name="loanTermList" value="" id="loanTermListH" />
                <form:select path="termList"  multiple="multiple"  class="input-medium" id="loanTermListS" onchange="ddl_change('loanTermListS')">
                    <form:option value="全部" label="全部"/>
                   <%--  <c:if test="${!empty coupon.termList}">
                        <c:forEach items="${coupon.termList}" var="term">
                            <form:option value="${term}" label="${term}"/>
                        </c:forEach>
                    </c:if> --%>
                    <form:options items="${fns:getDictList('MothConstant')}" itemLabel="label" itemValue="value" htmlEscape="true"/>
                </form:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*出借端限制：</label>
            <div class="controls" >
            <input type="hidden" name="loanChannelList" value="" id="loanChannelListH"/>
	            <form:select path="channelList" multiple="multiple" class="input-medium"  id="loanChannelListS" onchange="ddl_change('loanChannelListS')">
	                    <form:option value="全部" label="全部"/>
	                    <%-- <c:if test="${!empty coupon.channelList}">
                        <c:forEach items="${coupon.channelList}" var="channel">
                            <form:option value="${channel}" label="${channel}"/>
                        </c:forEach>
                    </c:if> --%>
	                   <form:options items="${fns:getDictList('PortConstant')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
	                </form:select>
            </div>
        </div>
        <%-- <div class="control-group">
            <label class="control-label">*最多发送张数：</label>
            <div class="controls">
                <form:input path="couponCount" type="number" value="${coupon.couponCount}"  htmlEscape="false" maxlength="11" class="input-xlarge "/>
            </div>
        </div> --%>
        
        <div class="form-actions">
            <input id="btnSubmit" class="btn btn-primary" type="button" value="保 存"/>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
</body>
</html>
