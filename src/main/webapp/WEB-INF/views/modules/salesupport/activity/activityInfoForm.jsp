<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动管理</title>
	<meta name="decorator" content="default"/>
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

			
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					
					var activityname= $("#activityname").val();
					if(activityname==null ||activityname =="")
						{
						   alert("活动的名称不能为空");
						  return  false;
					 }
					
					var introduction= $("#introduction").val();
					if(introduction==null ||introduction =="")
						{
						   alert("活动的简介不能为空");
						  return  false;
					 }
					
					var activitytype= $("#activitytype").val();
					  if(activitytype==null ||activitytype =="")
					   {
					   alert("活动的类型不能为空");
					    return  false;
					 }
	
					
					var begintime= $("#begintime").val();
					if(begintime==null ||begintime =="")
						{
						   alert("活动开始时间不能为空");
						  return  false;
					 }
		
					
					var endtime= $("#endtime").val();
					if(endtime==null ||endtime =="")
						{
						   alert("活动结束时间不能为空");
						  return  false;
					 }
					
					 if(begintime!=null&&begintime!="" && endtime!=null&&endtime!="") {
						   
						   if(endtime<=begintime){
							    alert("活动结束时间必须大于活动开始时间");
							    $("#endtime").val("");
							   return false;
						    }
					     }
					
					if(activitytype=="4" )
						{
						var imageurl= $("#imageurl").val();
						if(imageurl==null ||imageurl =="")
							{
							   alert("活动图片不能为空");
							  return  false;
						 }
						
						
						var activityhref= $("#activityhref").val();
						if(activityhref==null ||activityhref =="")
							{
							   alert("活动链接不能为空");
							  return  false;
						 }
					   }
					
					
					var id_array=new Array();  
					$('input[name="selected"]:checked').each(function(){  
					    id_array.push($(this).val());//向数组中添加元素  
					});  
					var idstr=id_array.join(',');//将数组元素连接起来以构建一个字符串  
					$("#ids").val(idstr);
					
					if(activitytype=="1"||activitytype=="2"||activitytype=="3"  )   //注册成功，开户成功，首次投资优惠券必须要选择
					{
			
						var ids= $("#ids").val();
						if(ids==null ||ids =="")
						{
						   alert("请选择优惠券信息");
						  return  false;
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
		});
		
		function clear1(){
			$("#img1").empty();
			$("#image").val("");
			$("#file1").val("");
		}
		
		
		//活动类型改变事件
		function typeChannge()
		{
			var activitytype= $("#activitytype").val();
			if(activitytype=="4")
				{
					$("#imgUrl").show();
					$("#hrefUrl").show();
				}
			else{
				$("#imgUrl").hide();
				$("#hrefUrl").hide();
			 }
			
		}
		
		
		
		function uploadImage(){
			var file=$("#file1").val();
			//判断上传的文件是否为空
			if(file==""){
				 alert("请选择上传的文件！");
			     return false ;
			}
			//判断上传文件的后缀名  png、jpg、jpeg、gif
		    var isfix = true,fixArr = [ "jpg", "gif", "png", "jpeg"];
		    strExtension = file.substr(file.lastIndexOf('.') + 1);
		    for ( var fix in fixArr ) {
		        if ( fixArr[fix].toLowerCase() == strExtension.toLowerCase() ) {
		            isfix = false;
		            break;
		        }
		    }
		    if (isfix ) {
		        alert("上传的文件类型不正确！");
		        return false ;
		    }
			
			//得到formdata对象
			var formdata = new FormData($("#inputForm")[0]);
			//ajax异步上传图片
			//返回的filename类似 front/a.jpg
			$.ajax({
				url : '${ctx}/banner/appBanner/uploadImg',
				type : 'POST',
				data : formdata,
				cache : false,
				contentType : false, //不可缺
				processData : false, //不可缺
				success:function(filename){
					$("#img1").empty();
					$("#img1").append("<img src='${baseurl_img}"+filename+"' style='width:120px;height:80px;'> ");
					$("#img1").append('<input type="button" value="清除" onclick="clear1()" class="btn btn-primary"/>');
					$("#imageurl").val("${baseurl_img}"+filename);
				},
				error:function(){
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/activityInfo/list">活动列表</a></li>
	<li class="active"><a href="javascript:;">活动修改</a></li>
	
	</ul><br/>
	<form:form id="inputForm" modelAttribute="activityInfo" action="${ctx}/activity/activityInfo/updateActivityInfo" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">活动名称：</label>
			<div class="controls">
					<form:input path="activityname" htmlEscape="false" maxlength="64" class="input-xlarge"/>
	
			</div>
		</div>
		
			<div class="control-group">
			<label class="control-label">活动简介：</label>
			<div class="controls">
				<form:input path="introduction" htmlEscape="false" maxlength="300" class="input-xlarge " />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">活动类型</label>
			<div class="controls">
				<form:select path="activitytype" class="input-xlarge " onchange="typeChannge();">
<%-- 					<form:option value="" label=""/> --%>
					<form:options items="${fns:getDictList('activityType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group"  style="display: none;">
			<label class="control-label">活动状态:</label>
			<div class="controls">
				<form:input path="activitystatus" htmlEscape="false" maxlength="300" class="input-xlarge " />
			</div>
		</div>
	
		<div class="control-group">
			<label class="control-label">活动开始时间</label>
			<div class="controls">
		
		<input id="begintime" name="begintime" type="text" 
			readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${activityInfo.begintime}" pattern="yyyy-MM-dd"/>"
			 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动结束时间</label>
			<div class="controls">

		<input id="endtime" name="endtime" type="text" 
			readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${activityInfo.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
			 onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});">
		</div>
		
		
				<c:if test="${activityInfo.activitytype ==4}">
				
				    <div class="control-group"  id="imgUrl">
					<label class="control-label">活动图片：</label>
					<div class="controls">
					
					<input type="text"  id="imageurl" name="imageurl" readonly="readonly" value="${activityInfo.imageurl}" />
					 <input type="file" name="file" id="file1"/><input type="button" value="上传" onclick="uploadImage()" class="btn btn-primary"/>
		        		
		        		<p id="img1">
		        			<c:if test="${activityInfo.imageurl!=null}">
		        				<img  src="${baseurl_img}${activityInfo.imageurl}" style="width: 120px;height: 80px;">
		        				<input type="button" value="清除" onclick="clear1()" class="btn btn-primary"/>
		        			</c:if>
		        		</p>
					</div>
				</div>
				
				<div class="control-group" id="hrefUrl">
					<label class="control-label">活动链接：</label>
					<div class="controls">
						<form:input path="activityhref" htmlEscape="false" maxlength="300" class="input-xlarge "/>
					</div>
				</div>
				
				</c:if>

		
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
				<td>${coupon.faceValue} </td>
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
					 </c:if></td>
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
						0 
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
		


		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>