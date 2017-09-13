<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			if($("#id").val()!=""){
				$("#name").attr("disabled",true); 
			}
			
			
			$("#inputForm").validate({
				
				  rules:{
					  collectAmount:{
						  moreThan0Doubles:true
	                    },            
	                    minTenderSum:{
	                    	doubles:true
	                    },            
	                    incrementalAmount:{
	                    	doubles:true
	                    },            
	                    maxTenderSum:{
	                    	doubles:true,
	                    	compareTenderSum:true
	                    },            
	                    poundage:{
	                    	doubles:true
	                    },            
	                    poundageMore:{
	                    	doubles:true,
	                    	comparePoundage:true
	                    },           
	                    endTimeOfCollection:{
	                    	compareDate:true
	                    }           
	                },
	                
	                messages:{
	                	
	                },
				
				submitHandler: function(form){
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
			
		    //正则表达式验证的写法
            $.validator.addMethod("doubles",function(value,element,params){  
                var doubles= /^\d+(\.\d{0,2})?$/;  
                return this.optional(element)||(doubles.test(value));  
            },"请按规则输入,文本框不能输入汉字和拼音或其他特殊符号");  
			
            //正则表达式验证的写法
            $.validator.addMethod("moreThan0Doubles",function(value,element,params){  
                var doubles=  /^[1-9]\d*(\.\d+)?$/;  
                return this.optional(element)||(doubles.test(value));  
            },"请按规则输入,最小不能为0，且不能输入汉字和拼音");  
			
			
            //时间
            $.validator.addMethod("compareDate",function(value,element){  
                var dateStart = $("#startTimeOfCollection").val();
                var dateEnd = $("#endTimeOfCollection").val();
                return dateStart <= dateEnd;
            }, "开始日期必须早于结束日期");
  
            //出借金额
            $.validator.addMethod("compareTenderSum",function(value,element){  
                var minTenderSum = $("#minTenderSum").val();
                var maxTenderSum = $("#maxTenderSum").val();
                return Number(minTenderSum)  < Number(maxTenderSum);
            }, "最高出借金额必须大于最低出借金额");
            
            //手续费
            $.validator.addMethod("comparePoundage",function(value,element){  
                var poundage = $("#poundage").val();
                var poundageMore = $("#poundageMore").val();
                return Number(poundage)  < Number(poundageMore);
            }, "未过半提前赎回手续费必须大于过半提前赎回手续费");
            
            
		});
		
		/* 计划名称修改 */
		function financeNameChange(idName){
			$.ajax( {  
	    	    type : "POST",  
	    	    url : ctx+'/finance/finance/queryProductDetail',
	    	    data : {
	    	    	idName:idName
	    	     }, 
	    	    dataType: "json",  
	    	    success : function(data) {
	    	    	if(data.rtnFlag){
	    	    		$("#nper").val(data.map.financeProduct.nper);
		    	    	$("#lendingMethod").val(data.map.financeProduct.lendingMethod);
		    	    	$("#rate").val(data.map.financeProduct.rate);
		    	    	$("#rate_show").val(data.map.financeProduct.rate*1+data.map.financeProduct.rewardRate*1);
		    	    	
		    	    	$("#rewardRate").val(data.map.financeProduct.rewardRate);
		    	    	$("#isNoviceLabel").val(data.map.financeProduct.isNoviceLabel);   	    	
		    	    	$("#lendingAgreement").val(data.map.financeProduct.lendingAgreement);
		    	    	$("#lendingAgreementCode").val(data.map.financeProduct.lendingAgreementCode);
		    	    	$("#financeCode").val(data.msg);
	    	    		$("#tishi").html("*");

					}else{
						$("#nper").val("");
		    	    	$("#lendingMethod").val("");
		    	    	$("#rate").val("");
		    	    	$("#rate_show").val("");		    	    	
		    	    	$("#rewardRate").val("");	
		    	    	$("#isNoviceLabel").val("");
		    	    	$("#lendingAgreement").val("");
		    	    	$("#financeCode").val("");
		    	    	$("#lendingAgreementCode").val("");
						$("#tishi").html("出借计划中尚存在该产品没有结束的状态，请检查重新选择...");
					}
	    	    }, 
	    	    
	    	    error :function(){  
	    	        alert("网络连接出错！");  
	    	    }  
		});
			
		
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/finance/finance/">出借计划列表</a></li>
		<li class="active"><a href="${ctx}/finance/finance/form?id=${finance.id}">出借计划${not empty finance.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="finance" action="${ctx}/finance/finance/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		
		<form:input path="lendingAgreementCode" htmlEscape="false" class="input-xlarge" type="hidden" />
		
		<div class="control-group">
			<label class="control-label">计划名称</label>
			
			<div class="controls">
			
				<form:select id="name" path="name" class="input-medium required"  onchange="financeNameChange(this.value)">
					<form:option value="" label=""/>
					 <c:forEach items="${financeProductList}" var="financeProduct">    
                         <option <c:if test="${financeProduct.name eq finance.name}">selected</c:if>  value="${financeProduct.id}-${financeProduct.name}">${financeProduct.name}</option>  
                     </c:forEach>  
				</form:select>
				
				<span class="help-inline"><font id="tishi" color="red">*</font> </span>
			</div>
		</div>
	
		
		<div class="control-group">
			<label class="control-label">计划编号：</label>
			<div class="controls">
				<form:input path="financeCode" htmlEscape="false" class="input-xlarge required" readonly = "true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">募集总额：</label>
			<div class="controls">
				<form:input path="collectAmount" htmlEscape="false" class="input-xlarge required "/>
				
				
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">出借期限：</label>
			<div class="controls">
				<form:input path="nper" htmlEscape="false" readonly = "true"  class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		
		
		<c:if test="${not empty finance.id}">
		<div class="control-group">
			<label class="control-label">出借方式：</label>
			<div class="controls">
				<form:select path="lendingMethod" class="input-xlarge required" disabled="true">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('LENDING_METHOD')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		
		<c:if test="${empty finance.id}">
		<div class="control-group">
			<label class="control-label">出借方式：</label>
			<div class="controls">
				<form:input path="lendingMethod" htmlEscape="false" class="input-xlarge required" readonly = "true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		
		
		
		
		
		<div class="control-group">
			<label class="control-label">年均出借回报率约：</label>
			<div class="controls">
				<input type="text" readonly = "true" value="${finance.rate*1+finance.rewardRate*1}" id="rate_show">
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">起息日：</label>
			<div class="controls">
			
				<form:select path="interestDateType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('INTEREST_DATE_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">最低出借金额：</label>
			<div class="controls">
				<form:input path="minTenderSum" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">递增金额：</label>
			<div class="controls">
				<form:input path="incrementalAmount" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">最高出借金额：</label>
			<div class="controls">
				<form:input path="maxTenderSum" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">募集开始时间：</label>
			<div class="controls">
				<input id="startTimeOfCollection" name="startTimeOfCollection" type="text" readonly="readonly" class="input-medium Wdate required"
					value="<fmt:formatDate value="${finance.startTimeOfCollection}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">募集结束时间：</label>
			<div class="controls">
				<input id="endTimeOfCollection" name="endTimeOfCollection" type="text" readonly="readonly"  class="input-medium Wdate required"
					value="<fmt:formatDate value="${finance.endTimeOfCollection}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">期限过半提前赎回手续费%：</label>
			<div class="controls">
				<form:input path="poundage" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">期限未过半提前赎回手续费%：</label>
			<div class="controls">
				<form:input path="poundageMore" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">出借协议：</label>
			<div class="controls">
			<form:input path="lendingAgreement" htmlEscape="false" class="input-xlarge required" readonly = "true" />
			<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">退出方式：</label>
			<div class="controls">
				<form:select path="exitMode" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('EXIT_MODE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保障方式：</label>
			<div class="controls">
				<form:select path="guaranteeMode" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('GUARANTEE_MODE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">介绍：</label>
			<div class="controls">
				<form:input path="detail" style="width: 280px;height: 180px;"  htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<input type="hidden" id="rate" name="rate" value="${finance.rate}"/>
		<input type="hidden" id="rewardRate" name="rewardRate" value="${finance.rewardRate}"/>
		<input type="hidden" id="isNoviceLabel" name="isNoviceLabel" value="${finance.isNoviceLabel}"/>
	
	
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>