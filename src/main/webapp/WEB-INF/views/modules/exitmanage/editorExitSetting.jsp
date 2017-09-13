<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设置退出金额</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		tr{
			height: 35px;
			line-height: 35px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//确定
			$("#confirmBtn").click(function(){
				var id='${page.list[0].id}';
				var quitAmount=$("#quitAmount").val();
				if(quitAmount==''){
					alert("请输入退出金额");
					return;
				}
				if(!/^[0-9]*.?[0-9]*$/.test(quitAmount)){
					alert("请输入数字");
					return;
				}
	 			$.post(ctx+'/exitmanage/tExitSettings/updateCanQuitAmount',{id:id,quitAmount:quitAmount},function(f){
	 				if(f){
	 					alert("修改成功");
	 					location.reload();
	 				}else{
	 					alert("修改失败");
	 				}
	 			},"json")
			});
		});

	</script>
</head>
<body>
	<table style="margin-left: 80px;width: 850px;font-size: 14px;">
		<tr>
			<td style="width: 150px; "align="right">*明日到期金额:</td>
			<td style="width: 200px;">
			<input type="text" name="expireAmount" id="expireAmount"  value="${page.list[0].expireAmount}" readonly="readonly"  />元
			</td>
		</tr>
		<tr>
			<td  align="right">*到期未退出金额:</td>
			<td><input type="text" name="notExitedAmount" id="notExitedAmount"  value="${page.list[0].notExitedAmount}"  readonly="readonly" />元</td>
		</tr>
		<tr>
			<td align="right">*退出金额:</td>
			<td><input type="text" name="quitAmount" value="${page.list[0].canQuitAmount}" id="quitAmount" />元</td>
		</tr>
		<tr>
			<td align="right">*当前时间:</td>
			<td colspan="1">
			<jsp:useBean id="now" class="java.util.Date" scope="page"/>
            <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm" />
			</td>
		</tr>
	
	</table>
		<div class="NewListConfig4Button">
			<a href="javascript:void(0);" class="NewListConfig4Button1" id="confirmBtn" >确定</a> 
			<a href="javascript:void(0);" class="NewListConfig4Button2" style="background: #ccc" id="updateFirstMsg" onclick="history.go(-1)">返回</a> 
		</div>
	</div>
</body>
</html>