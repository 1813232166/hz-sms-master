<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理管理</title>
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
					$("#img1").append("<img src='${baseurl_img}"+filename+"' style='width:120px;height:80px;'> ");
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
		<li><a href="${ctx}/content/article/">文章管理列表</a></li>
		<li class="active"><a href="${ctx}/content/article/form?id=${article.id}">文章管理<shiro:hasPermission name="content:article:edit">${not empty article.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="content:article:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form enctype="multipart/form-data" id="inputForm" modelAttribute="article" action="${ctx}/content/article/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">文章标题：</label>
			<div class="controls">
				<form:input path="articletitle" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
    <div class="control-group">
      <label class="control-label">文章副标题：</label>
      <div class="controls">
        <form:input path="articleSubtitle" htmlEscape="false" maxlength="128" class="input-xlarge" />
      </div>
    </div>
    <div class="control-group">
			<label class="control-label">封面图片：</label>
			<div class="controls">
<%-- 				<c:if test="${article.artclepic!=NULL }"> --%>
<%-- 					<form:input path="artclepic"  htmlEscape="false" maxlength="255" class="input-xlarge "/> --%>
<%-- 				</c:if> --%>
<%-- 				<c:if test="${article.artclepic==NULL }"> --%>
<!-- 					<input type="file" name="picturePath" /> -->
<%-- 				</c:if> --%>
				<input type="hidden" id="image" name="artclepic" value="${article.artclepic}" />
       <%--  <sys:ckfinder input="image" type="thumb" uploadPath="/cms/article" selectMultiple="false"/> --%>
			
			 <input type="file" name="file" id="file1"/><input type="button" value="上传" onclick="uploadImage()" class="btn btn-primary"/>
        		
        		<p id="img1">
        			<c:if test="${article.artclepic!=null}">
        				<img  src="${baseurl_img}${article.artclepic}" style="width: 120px;height: 80px;">
        				<input type="button" value="清除" onclick="clear1()" class="btn btn-primary"/>
        			</c:if>
        		</p>
			
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">作者：</label>
			<div class="controls">
				<form:input path="author" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<c:if test="${empty article.creator}">
			<form:hidden path="creator" value="${fns:getUser().loginName}"/>
		</c:if>
		<div class="control-group">
			<label class="control-label">来源：</label>
			<div class="controls">
				<form:input path="source" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">栏目：</label>
			<div class="controls">
				<form:select path="articlecolumn" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('article_column')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keyword" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<span class="help-inline">多个关键字，用空格分隔。</span>
			</div>
		</div>
    <div class="control-group">
      <label class="control-label">发布时间：</label>
      <div class="controls">
        <input name="releasetime" type="text" maxlength="20" class="input-mini Wdate"
        value="<fmt:formatDate value="${article.releasetime}" pattern="yyyy-MM-dd"/>"
        onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">文章摘要：</label>
      <div class="controls">
        <form:textarea path="articleDigest" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
      </div>
    </div>
		<div class="control-group">
			<label class="control-label">文章内容：</label>
			<div class="controls" style="height: 400px;">
				<%-- <form:textarea id="content" path="articlecnt" htmlEscape="false" rows="4" class="input-xxlarge "/> --%>
     
     <input type="hidden" id="content" name="articlecnt" value="${article.articlecnt}" />
     
       <!-- 加载编辑器的容器 -->
	    <script id="container" name="content" type="text/plain">
       		

   		</script>
	    <!-- 配置文件 -->
	    <script type="text/javascript" src="${ctxStatic }/ueditor/ueditor.config.js"></script>
	    <!-- 编辑器源码文件 -->
		<script type="text/javascript" src="${ctxStatic }/ueditor/ueditor.all.js"></script>
	    <!-- 实例化编辑器 -->
	    <script type="text/javascript">
	    
	    	$(function(){
	    		
	    	
	        var ue = UE.getEditor('container',{
	            toolbars: [
							['source', 'undo', 'redo','bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist',
							 'selectall','link', 'cleardoc','simpleupload', 'imagenone', 'imageleft', 'imageright','imagecenter','fontsize','justifyleft','justifyright','justifycenter']
	                   ],//自定义文本编辑器的工具栏
	                   initialFrameHeight:350,//自定义文本编辑器的高度
	                   autoHeightEnabled: false,
	                   autoFloatEnabled: true,//文本编辑器是否浮动
	                   enableAutoSave:false
	               });
	       //获取数据库中的文章内容
	        var cont=$("#content").val();
		 	   
	      //对编辑器的操作最好在编辑器ready之后再做
	      ue.ready(function() {
	          //设置编辑器的内容
	          
	        ue.setContent(cont);
	          //获取html内容，返回: <p>hello</p>
	         // var html = ue.getContent();
	          //获取纯文本内容，返回: hello
	         
	      });
	      //ueditor中的图片上传的设置
	      UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	    	UE.Editor.prototype.getActionUrl = function(action) {
	    		//判断路径   这里是config.json 中设置执行上传的action名称
	    		//覆盖默认路径
	    	    if (action == 'uploadimage') {
	    	        return '${ctx}/banner/appBanner/upload_ueditor';
	    	    } else if (action == 'uploadvideo') {
	    	        return '';
	    	    } else {
	    	        return this._bkGetActionUrl.call(this, action);
	    	    }
	    	}

	      	//在提交表单之前获取编辑器的内容，放在input隐藏域中
	      	$("#btnSubmit").click(function(){
	      		var txt = ue.getContent();
	      		$("#content").val(txt);
	      		
	      	});
	      
	      
	    	})
	        
	    </script>
    
    
        <%-- <sys:ckeditor replace="content" uploadPath="/cms/article" /> --%>
        
        
        <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="content:article:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>