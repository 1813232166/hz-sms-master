<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>

	<title>Banner管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
		});
		
		function clear1(){
			
			$("#img1").empty();
			$("#image").val("");
			$("#file1").val("");
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
					$("#img1").append("<img src='${baseurl_img }"+filename+"' style='width:120px;height:80px;'> ");
					$("#img1").append('<input type="button" value="清除" onclick="clear1()" class="btn btn-primary"/>');
					$("#image").val(filename);
				},
				error:function(){
					
				}
				
				
				
			});
		}
	</script>
</head>
<body>
	
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/banner/appBanner/">Banner列表</a></li>
		<li class="active"><a href="${ctx}/banner/appBanner/form?id=${appBanner.id}">Banner<shiro:hasPermission name="banner:appBanner:edit">${not empty appBanner.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="banner:appBanner:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="appBanner" action="${ctx}/banner/appBanner/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<c:if test="${empty appBanner.creator}">
			<form:hidden path="creator" value="${fns:getUser().loginName}"/>
		</c:if>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">banner名称：</label>
			<div class="controls">
				<form:input path="bannerName" htmlEscape="false" maxlength="2000" class="input-medium required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-medium required">
							<form:option  value="0" label="APP"/>
							<form:option value="1" label="PC"/>
							<form:option value="2" label="微信"/>
							<form:option value="3" label="APP1.0"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				 <input type="hidden" id="image" name="picurl" value="${appBanner.picurl}" />
        		<%-- <sys:ckfinder input="image" type="thumb" uploadPath="/cms/banner" selectMultiple="false"/> --%>
        		<!-- <form action="" > -->
        		    <input type="file" name="file" id="file1"/><input type="button" value="上传" onclick="uploadImage()" class="btn btn-primary"/>
        		<!-- </form> -->
        		<p id="img1">
        			<c:if test="${appBanner.picurl!=null}">
        				<img  src="${baseurl_img }${appBanner.picurl}" style="width: 120px;height: 80px;">
        				<input type="button" value="清除" onclick="clear1()" class="btn btn-primary"/>
        			</c:if>
        		</p>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用状态：</label>
			<div class="controls">
				<form:radiobutton  path="status" value="1" />启用
				<c:choose>
					<c:when test="${not empty appBanner.status }">
						<form:radiobutton path="status" value="0"/>禁用
					</c:when>
					<c:otherwise>
						<form:radiobutton path="status" value="0" checked="checked"/>禁用
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<c:if test="${not empty appBanner.updateDate}">
			<div class="control-group">
				<label class="control-label">更新时间：</label>
				<div class="controls">
					<input type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${appBanner.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				</div>
			</div>
		</c:if>
		
		
		<div class="control-group">
			<label class="control-label">链接：</label>
			<div class="controls">
				<form:input path="link" htmlEscape="false" maxlength="2000" class="input-xlarge" />
				<span class="help-inline">添加时请以<font color="red">http://</font>开头</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="orders" htmlEscape="false" maxlength="2000" class="input-medium required" />
				<span class="help-inline"><font color="red">*</font> </span>
				<span class="help-inline">输入1开始的自然数，1为最前</span>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="banner:appBanner:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>