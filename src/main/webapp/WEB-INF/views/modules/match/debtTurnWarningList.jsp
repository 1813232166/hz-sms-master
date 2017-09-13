<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>债权转让预警管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		//定义一个变量用于存储选中复选框的值
		var sel_a=[];
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		$(function() {
			
			//选中时插入，取消时去除
			$(".click_checkbox").click(function(){
				if($("#checkIds").val() !== ''){
					sel_a=$("#checkIds").val().split(",");
				}
				
				  var v=$(this).val();
			         if ($.inArray(v,sel_a)==-1){
			                sel_a.push(v);
			            }else{
			                for(var i=0;i<=sel_a.length-1;i++){
			                    if(sel_a[i]==v){
			                        sel_a.splice(i,1);
			                    }
			                }
			            }
			     
			         $("#checkIds").val(sel_a);
			         
			});
			
			
			$("#myModal").hide();
			$("#confirmreturn").hide();
			$("#batchResetWeight").hide();
			
			$(".batchResetWeight").click(function(){ 
				
				var checkIds=$("#checkIds").val();
				if(checkIds==''){
					alert("亲！请先选择数据!");
					return false;
				}
				
				
			$("#confirmreturn").hide();
			$("#batchResetWeight").show();
			$("#myModal").modal('show');
			});
			
			
			$("#batchResetWeight").click(function(){
				var checkIds=$("#checkIds").val();
				var customWeight=$("#customWeight").val();
				var isStickTop =$('input:radio:checked').val();
				
				
				var reg = /^[1-9]\d*$/;
				if(!reg.test(customWeight)){
					alert("请输入大于0的整数");
					return false;
				}
				
				$.ajax( {  
		    	    type : "POST",  
		    	    url : ctx+'/match/debtTurnWarning/batchResetWeight',
		    	    data : {
		    	    	checkIds:checkIds,
		    	    	customWeight:customWeight,
		    	    	stickTop:isStickTop
		    	     }, 
		    	    dataType: "json",  
		    	    success : function(data) {
		    	    	if(data.rtnFlag){
							$("#batchResetWeight").hide();
							$("#dialog2").hide();
							$("#confirmreturn").show();
							$("#dialog1").text("重新计算权重成功");
						}else{
							$("#batchResetWeight").hide();
							$("#dialog2").hide();
							$("#confirmreturn").show();
							$("#dialog1").text("重新计算权重失败"+data.msg);
						}
		    	    	
		    	    }, 
		    	    
		    	    error :function(){  
		    	        alert("网络连接出错！");  
		    	    }  
			});
				
				
			});
			
			$("#close1").click(function(){
				$("#myModal").hide();
				$("#searchForm").submit();
			});
			
			$("#confirmreturn").click(function(){
				$("#myModal").hide();
				$("#searchForm").submit();
			});
			
			$("#cancel").click(function(){
				$("#myModal").hide();
				$("#searchForm").submit();
			});
			
			
		//复选框遍历回显
          $(".click_checkbox").each(function(){
        		 if($("#checkIds").val() !== ''){
					sel_a=$("#checkIds").val().split(",");
				 }
        		 
                 var v=$(this).val();
                 if ($.inArray(v,sel_a)!=-1){
                   $(this).prop("checked",true);
                   }
            })
                
            
            /*提交校验*/
			$("#btnSubmit").click(function(){
				   var  warningDayLow=  $("#warningDayLow").val();
				   var warningDayHigh = $("#warningDayHigh").val();
				   var  weightLow=  $("#weightLow").val();
				   var  weightHigh = $("#weightHigh").val();

				   if(warningDayLow!=null&&warningDayLow!="" && warningDayHigh!=null&&warningDayHigh!="") {
					   
					   if(warningDayLow>warningDayHigh){
						    alert("预警天数开始不能大于结束");
 						    $("#warningDayLow").val("");
 						    $("#warningDayHigh").val("");
						   return false;
					    }
				     }
				     
				   if(weightLow!=null&&weightLow!="" && weightHigh!=null&&weightHigh!="") {
					   var num=/^[0-9]*$/;
					   if(!num.test(weightLow)){
						   alert("权重请输入数字");
						   return false;
					   }
					   if(!num.test(weightHigh)){
						   alert("权重请输入数字");
						   return false;
					   }
					   if(weightLow>weightHigh){
						    alert("权重开始值要小于结束值");
						    $("#weightLow").val("");
 						    $("#weightHigh").val("");
						   return false;
					    }
				     }
				     
			});/*提交校验*/
			$("#btnSubmit").click(function(){
				   var  beginInvestTime=  $("#beginInvestTime").val();
				   var endInvestTime = $("#endInvestTime").val();
				   var  weightLow=  $("#weightLow").val();
				   var  weightHigh = $("#weightHigh").val();

				   if(beginInvestTime!=null&&beginInvestTime!="" && endInvestTime!=null&&endInvestTime!="") {
					   
					   if(beginInvestTime>endInvestTime){
						    alert("开始时间不能大于结束时间");
						    $("#beginInvestTime").val("");
						    $("#endInvestTime").val("");
						   return false;
					    }
				     }
				     
				   if(weightLow!=null&&weightLow!="" && weightHigh!=null&&weightHigh!="") {
					   var num=/^[0-9]*$/;
					   if(!num.test(weightLow)){
						   alert("权重请输入数字");
						   return false;
					   }
					   if(!num.test(weightHigh)){
						   alert("权重请输入数字");
						   return false;
					   }
					   if(weightLow>weightHigh){
						    alert("权重开始值要小于结束值");
						    $("#weightLow").val("");
						    $("#weightHigh").val("");
						   return false;
					    }
				     }
				     
			});
            
		});
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/match/debtTurnWarning/">债权转让预警列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="debtTurnWarning" action="${ctx}/match/debtTurnWarning/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="checkIds" name="checkIds" type="hidden" value="${checkIds}"/>
		
		<ul class="ul-form">
			<li>
			<label>借款编号：</label>	
				<form:input id="assetCode" path="assetCode" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
			</li>
			<li><label>借款人姓名：</label>
				<form:input id="realName" path="realName" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"/>
			</li>
			<li><label>身份证号:</label>
				<form:input id="idCard" path="idCard" htmlEscape="false" maxlength="18" class="input-medium" onkeyup="value=value.replace(/^[a-zA-Z]+\D*|^\d{0,16}[a-zA-Z]+|[^0-9Xx]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
			</li>
			
			<li>
			<label>产品类型:</label>
				<form:select path="productId" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('PRODUCT_ID')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		
			<li><label>撮合状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('CAPITAL_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>资产来源：</label>
				<form:select path="assetSource" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('CAPITAL_SOURCE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>资产类别：</label>
				<form:select path="assetCategory" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('ASSET_CATEGORY')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li>
			<label>是否置顶：</label>
				<form:select path="stickTop" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('IS_STICK_TOP')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li>
				<label>权重：</label>
				<input name="weightLow" id="weightLow" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${debtTurnWarning.weightLow}"/> 至 
				<input name="weightHigh" id="weightHigh" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${debtTurnWarning.weightHigh}"/>
			</li>
			
			<li>
				<label>预警天数：</label>
				<input name="warningDayLow" id="warningDayLow" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${debtTurnWarning.warningDayLow}"/> 至 
				<input name="warningDayHigh" id="warningDayHigh" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${debtTurnWarning.warningDayHigh}"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
			
				
			<li style="width: 950px;"  float="left">
				预警资产：<c:if test="${warningAssetAmount.amountCount ==null}">0.00</c:if>${warningAssetAmount.amountCount}元
			</li>
			
			<li style="width: 100px;" float="right">
					<a href="javascript:void(0)" class="batchResetWeight">批量设置权重</a>
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th id="xuhao">序号</th>
				<th>借款编号</th>
				<th>资产类别</th>
				<th>借款人姓名</th>
				<th>身份证号</th>
				<th>借款金额（元）</th>
				<th>剩余金额（元）</th>
				<th>借款期限（月）</th>
				<th>借款年化利率（%）</th>
				<th>预警天数（天）</th>
				<th>产品类型</th>
				<th>债权类型</th>
				<th>撮合状态</th>
				<th>权重</th>
				<th>是否置顶</th>
				<th>资产来源</th>
				<th>撮合笔数</th>
			<tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="debtTurnWarning">
			<tr>
			<td>
			<input class="click_checkbox"  type="checkbox" value="${debtTurnWarning.id}" name="chk_list" />
			</td>
			<td>${debtTurnWarning.assetCode}</td>
			<td>
			${fns:getDictLabel(debtTurnWarning.assetCategory, 'ASSET_CATEGORY', '')}
			</td>
			<td>${fn:substring(debtTurnWarning.realName, 0, 1)}**</td>
			<td>${fn:substring(debtTurnWarning.idCard, 0, 3)}***************</td>
			<td>${debtTurnWarning.amount}</td>
			<td>${debtTurnWarning.residueAmount}</td>
			<td>${debtTurnWarning.nper}</td>
			<td>${debtTurnWarning.rate}%</td>
			<td>${debtTurnWarning.warningDay}</td>
			<td>
			${fns:getDictLabel(debtTurnWarning.productId, 'PRODUCT_ID', '')}
			</td>
			<td>
			${fns:getDictLabel(debtTurnWarning.assetType, 'ASSET_TYPE', '')}
			</td>
			<td>
			${fns:getDictLabel(debtTurnWarning.status, 'ASSET_STATUS', '')}
			</td>
			<td>${debtTurnWarning.weight}</td>
			<td>
			${fns:getDictLabel(debtTurnWarning.stickTop, 'IS_STICK_TOP', '')}
			</td>
			<td>
			${fns:getDictLabel(debtTurnWarning.assetSource, 'CAPITAL_SOURCE', '')}
			</td>
			<td>
			${debtTurnWarning.matchingPenNumber}
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
					<h4 class="modal-title">批量设置权重</h4>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="line-height: 30px;text-align: center;font-size: 18px;">
							<div id="dialog2">
							<label>加权重：</label>
							<input id="customWeight" htmlEscape="false" value="1" maxlength="10" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" /><br>
							<label>是否置顶：</label><input name="isStickTop" id="isStickTop1" type="radio" value="1" checked/>是 <input name="isStickTop" id="isStickTop0"  type="radio" value="0"/>否
							</div>
							<p id="dialog1"></p>
					</div>
				</div>
				<div class="modal-footer">
					<p class="text-center">
						<button class="btn btn-primary" id="cancel" style="margin-right: 50px;">取消</button>
						<button class="btn btn-primary" id="confirmreturn" style="margin-left: 50px;">确认</button>
						<button class="btn btn-primary" id="batchResetWeight" style="margin-left: 50px;">确认</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>