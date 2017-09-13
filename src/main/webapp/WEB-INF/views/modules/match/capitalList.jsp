<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金列表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		
		$(function() {	
			$("#myModal").hide();
			$("#confirmreturn").hide();
			$("#confirmAddWeight").hide();
			$("#batchResetWeight").hide();
			
			$(".weight").click(function(){
				 var capitalCode=$(this).next().val();
				 var id=$(this).next().next().val();
				 var stickTop=$(this).next().next().next().val();
				 var customWeight=$(this).next().next().next().next().val();
				 if(stickTop=='0'){
					$("#isStickTop0").attr("checked","checked");
				 }else{
					 $("#isStickTop1").attr("checked","checked");
				 }
				$("#ida").val(id);
				$("#customWeight").val(customWeight);
				$("#dialog1").text("出借编号:"+capitalCode);
				$("#confirmreturn").hide();
				$("#confirmAddWeight").show();
				$("#batchResetWeight").hide();
				$("#myModal").modal('show');
			});
			
			$(".batchResetWeight").click(function(){
				$("#dialog3").text("确实重新计算权重？");
				$("#confirmreturn").hide();
				$("#dialog1").hide();
				$("#dialog2").hide();
				$("#batchResetWeight").show();
				$("#myModal").modal('show');
			});
			
			
			$("#batchResetWeight").click(function(){
				
				$.ajax( {  
		    	    type : "POST",  
		    	    url : ctx+'/match/capital/batchResetWeight',
		    	    data : {
		    	     }, 
		    	    dataType: "json",  
		    	    success : function(data) {
		    	    	if(data.rtnFlag){
							$("#batchResetWeight").hide();
							$("#dialog1").hide();
							$("#dialog2").hide();
							$("#confirmreturn").show();
							$("#dialog3").text("重新计算权重成功");
						}else{
							$("#batchResetWeight").hide();
							$("#dialog1").hide();
							$("#dialog2").hide();
							$("#confirmreturn").show();
							$("#dialog3").text("重新计算权重失败"+data.msg);
						}
		    	    	
		    	    }, 
		    	    
		    	    error :function(){  
		    	        alert("网络连接出错！");  
		    	    }  
			});
				
				
			});
				
				
			$("#confirmAddWeight").click(function(){
				var id=$("#ida").val();
				var customWeight=$("#customWeight").val();
				
				var reg = /^[1-9]\d*$/;
				if(!reg.test(customWeight)){
					alert("请输入大于0的整数");
					return false;
				}
				
				var isStickTop =$('input:radio:checked').val();
				$.post(ctx+'/match/capital/singleResetWeight',{id:id,customWeight:customWeight,stickTop:isStickTop},function(f){
					if(f){
						$("#confirmAddWeight").hide();
						$("#dialog1").hide();
						$("#dialog2").hide();
						$("#confirmreturn").show();
						$("#dialog3").text("修改成功");
					}else{
						$("#confirmAddWeight").hide();
						$("#dialog1").hide();
						$("#dialog2").hide();
						$("#confirmreturn").show();
						$("#dialog3").text("修改失败");
					}
				},"json")
			});
			
			$("#cancel").click(function(){
				$("#myModal").hide();
				$("#searchForm").submit();
			});
			
			$("#close1").click(function(){
				$("#myModal").hide();
				$("#searchForm").submit();
			});
			
			$("#confirmreturn").click(function(){
				$("#myModal").hide();
				$("#searchForm").submit();
			});
			
			/*提交校验*/
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
		<li class="active"><a href="${ctx}/match/capital/">资金列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="capital" action="${ctx}/match/capital/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%--隐藏字段 --%>
		<input id="ida"  type="hidden" value=""/>
		<ul class="ul-form">
		
			<li><label>出借编号：</label>
				<form:input id="capitalCode" path="capitalCode" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
			</li>
			<li><label>出借人姓名：</label>
				<form:input id="realName" path="realName" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"/>
			</li>
			<li><label>身份证号：</label>
				<form:input id="idCard" path="idCard" htmlEscape="false" maxlength="18" class="input-medium" onkeyup="value=value.replace(/^[a-zA-Z]+\D*|^\d{0,16}[a-zA-Z]+|[^0-9Xx]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
			</li>
		
		
			<li><label>资金状态：</label>
				<form:select path="capitalType" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('CAPITAL_TYPE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li>
				<label>权重：</label>
				<input name="weightLow" id="weightLow" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${capital.weightLow}"/> 至 
				<input name="weightHigh" id="weightHigh" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" value="${capital.weightHigh}"/>
			</li>
			
			<li>
				<label>加入队列时间</label>
				<input name="beginInvestTime" readonly="readonly" id="beginInvestTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${capital.beginInvestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				<input name="endInvestTime" readonly="readonly" id="endInvestTime" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${capital.endInvestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>资金来源：</label>
				<form:select path="capitalSource" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('CAPITAL_SOURCE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>资金类别：</label>
				<form:select path="capitalCategory" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('CAPITAL_CATEGORY')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			
			<li><label>撮合状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('CAPITAL_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>是否置顶：</label>
				<form:select path="stickTop" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('IS_STICK_TOP')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			

			
		
			<%-- 
			锁定删除
			<li><label>是否锁定：</label>
				<form:select path="sourceCapitalId" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('SOURCE_CAPITAL_ID')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			--%>
			
			
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
			<li class="clearfix"></li>
		</ul>
	  <table style="width: 80%" >
        <tr>
	        <td  style="width: 80px;"  align="right" >原始资金:</td><td style="width: 70px;" ><c:if test="${originalCount.amountCount ==null}">0.00</c:if>${originalCount.amountCount}元 </td>
	        <td  align="right" valign="top">原始部分匹配资金:</td><td style="width: 70px;" ><c:if test="${originalCount.matchedAmountCount ==null}">0.00</c:if>${originalCount.matchedAmountCount}元</td>
	        <td  align="right" valign="top">原始剩余待匹配资金:</td><td style="width: 70px;" ><c:if test="${originalCount.surplusAmountCount ==null}">0.00</c:if>${originalCount.surplusAmountCount}元</td>
        </tr>
        <tr>
	        <td  align="right">回款资金:</td><td style="width: 70px;" ><c:if test="${returnCount.amountCount ==null}">0.00</c:if>${returnCount.amountCount}元</td>
	        <td  align="right">回款部分匹配资金:</td><td style="width: 70px;" ><c:if test="${returnCount.matchedAmountCount ==null}">0.00</c:if>${returnCount.matchedAmountCount}元</td>
	        <td  align="right">回款剩余待匹配资金:</td><td style="width: 70px;" ><c:if test="${returnCount.surplusAmountCount ==null}">0.00</c:if>${returnCount.surplusAmountCount}元</td>
	        <td align="right">	<a href="javascript:void(0)" class="batchResetWeight">重新计算权重</a><td>
        </tr>
        </table>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
				<th>出借编号</th>
				<th>出借产品</th>
				<th>资金类别</th>
				<th>出借人姓名</th>
				<th>身份证号</th>
				<th>出借金额( 元）</th>
				<th>剩余金额（元）</th>
				<th>出借年化利率（%）</th>
				<th>出借期限（月）</th>
				<th>预计收益（元）</th>
				<th>撮合状态</th>
				<th>资金状态</th>
				<th>撮合笔数</th>
				<th>加入资金队列时间</th>
				<th>权重</th>
				<th>是否置顶</th>
				<th>资金来源</th>
				<th>操作</th>
			<tr>
			
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="capital">
			<tr>
			
			<td>${capital.capitalCode}</td>
			<td>${capital.capitalProduct}</td>
			<td>
			${fns:getDictLabel(capital.capitalCategory, 'CAPITAL_CATEGORY', '')}
			</td>
			<td>
			${fn:substring(capital.realName, 0, 1)}**
			</td>
			<td>
			${fn:substring(capital.idCard, 0, 3)}***************
			</td>
			<td>${capital.amount}</td>
			<td>${capital.surplusAmount}</td>
			<td>${capital.rate}%</td>
			<td>${capital.nper}</td>
			<td>${capital.projectedEarnings}</td>
			<td>
			${fns:getDictLabel(capital.status, 'CAPITAL_STATUS', '')}
			</td>
			
			<td>
			${fns:getDictLabel(capital.capitalType, 'CAPITAL_TYPE', '')}
			</td>
			
			<td>${capital.matchingPenNumber}</td>
			<td style="text-align: center;">
					<fmt:formatDate value="${capital.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>${capital.weight}</td>
			<td>
			${fns:getDictLabel(capital.stickTop, 'IS_STICK_TOP', '')}
			
			</td>	
			<td>
			${fns:getDictLabel(capital.capitalSource, 'CAPITAL_SOURCE', '')}
			</td>
			<td>
   				<a href="javascript:void(0)" class="weight">权重</a>
				<input type="hidden" value="${capital.capitalCode}">
				<input type="hidden" value="${capital.id}">
				<input type="hidden" value="${capital.stickTop}">
				<input type="hidden" value="${capital.customWeight}">
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
					<h4 class="modal-title">修改权重</h4>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="line-height: 30px;text-align: center;font-size: 18px;">
							<label id="dialog1"></label>
							<div id="dialog2">
							<label>加权重：</label>
							<input id="customWeight" htmlEscape="false" value="1" maxlength="10" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" /><br>
							<label>是否置顶：</label><input name="isStickTop" id="isStickTop1" type="radio" value="1"/>是 <input name="isStickTop" id="isStickTop0"  type="radio" value="0"/>否
							</div>
							<p id="dialog3"></p>
					</div>
				</div>
				<div class="modal-footer">
					<p class="text-center">
						<button class="btn btn-primary" id="cancel" style="margin-right: 50px;">取消</button>
						<button class="btn btn-primary" id="confirmreturn" style="margin-left: 50px;">确认</button>
						<button class="btn btn-primary" id="confirmAddWeight" style="margin-left: 50px;">确认</button>
						<button class="btn btn-primary" id="batchResetWeight" style="margin-left: 50px;">确认</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>