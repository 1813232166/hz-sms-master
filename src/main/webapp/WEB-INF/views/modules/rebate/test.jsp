<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>返佣测试页面</title>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<form id="form" name="form" action="http://localhost:8080/hzwd/rebate/recordInvest">
		投资人手机号:<input type="text" name="userMobile" />
		<br /> 姓名：<input type="text" name="userName" />
		<br />出借时间:<input type="text" name="investTime" />
		<br />出借金额:<input type="text" name="investAmount" />
		<br />出借期限:<input type="text" name="deadline" />
		<br />项目名称:<input type="text" name="projectName" />
		<br />标号（编号）:<input type="text" name="projectNo" />
		<br />订单类型（1：新手标，2：出借计划，3：散标）:<input type="text" name="orderType" />
		<br />来源：<input type="text" name="resource" />
		<br />订单号：<input type="text" name="orderId" />
		<br /><button type="button" onclick="forSubmit()">提交</button>	
		<input type="hidden" name="data" id="data" />
	</form>
	<a href="http://localhost:8080/hzwd/rebate/rebateComputerJob">计算返佣</a>
	
	<br><br>
	<a href="http://localhost:8080/hzwd/rebate/extendUserUpdateBeforeJob">同步用户，并更新用户标志位0</a>
	<br><br>
	<a href="http://localhost:8080/hzwd/rebate/extendUserUpdateComputerJob">统计用户信息</a>
	<br><br>
	<a href="http://localhost:8080/hzwd/rebate/monthRebateJob">每月统计返佣</a>
	<script type="text/javascript">
	(function($) {
		$.fn.serializeJson = function() {
			var serializeObj = {};
			var array = this.serializeArray();
			var str = this.serialize();
			$(array).each(
					function() {
						if (serializeObj[this.name]) {
							if ($.isArray(serializeObj[this.name])) {
								serializeObj[this.name].push(this.value);
							} else {
								serializeObj[this.name] = [
										serializeObj[this.name], this.value ];
							}
						} else {
							serializeObj[this.name] = this.value;
						}
					});
			return serializeObj;
		};
	})(jQuery);
		function forSubmit(){
			var json = $("#form").serializeJson(); 
			alert(json);
			$("#data").val(JSON.stringify(json));
			console.info($("#data").val())
			$("#form").submit();
		}
	</script>
</body>
</html>