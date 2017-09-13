<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/jquery/jquery.media.js"></script>  
	<script type="text/javascript"> 
	$(document).ready(function(){  
        $('a.media').media({width:800, height:600});  
    });  
</script>  
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/contractSeal/">合同详情</a></li>
	</ul>
	<div style="width:100%;height: 800px">
	<div>
		<div style="float: left;">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="返回" 
			onclick="window.location.href='${ctx}/borrow/contractSeal/'"/>
		</div>
	</div>
	<embed width="100%" height="100%" name="plugin" 
	src="http://10.20.100.6:8100/hzdjr-web/seal/download/
	${contractSeal.id}.pdf?referenceId=${contractSeal.referenceId}&contractType=${contractSeal.contractType}" 
	type="application/pdf" internalinstanceid="102">
	</div>
</body>
</html>