<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>
	<style type="text/css">
      html,body,table{background-color:#f5f5f5;width:100%;text-align:center;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
       .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;width:100%;margin:0 auto;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
      .lL{float:left;}
      .container{width: 1200px;margin: 0 auto}
      .clear{clear:both;}
      .rR{float:right;}
      .headers{line-height: 26px;color: #a1a1a1;}
      .hzTit{width: 100%;background: #f2f2f2;}
      .hzTit .left{height: 26px;font-size: 12px;}
      .hzTit .right ul li{float: left;padding: 0px 3px;margin:0px 5px;color: #ababab;}
      .hzTit .right .hzAdmin{color: #111111;}
      .hzLogo{height: 100px;}
      .hzLogo .left{width: 225px;height: 100px;line-height: 100px;background: url("${ctxStatic}/images/logo.png") no-repeat 5px 25px;}
      .hzLogo .left{display: inline-block;text-indent: -10000px;}
      .hzLogo .slogan{display: inline-block;width: 195px;height: 100px;background: url("${ctxStatic}/images/hzdjr-pic.png") no-repeat center;}
      .center {background: #ebebeb; padding: 80px 0 70px;}
      .center .login {width: 1000px;  height: 385px;box-shadow: -5px 5px 5px #dadada;margin: 0 auto;background: url(${ctxStatic}/images/lo.png) no-repeat center center; position: relative;}
      .center .login form { position: absolute;  right: 43px;top:25px;  width: 300px;height: 250px;padding: 40px 0 0 20px; border:none;}
      .footer{height:40px;line-height:40px;background:#ffffff;}
</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="headers">
    <div class="hzLogo container clearfix">
            <a href="#"><div class="left lL">汇中网</div></a>
            <span class="slogan lL"></span>
        </div>

    </div>
	<div class="center">
	<div class="login">
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
		<label class="input-label" for="username">登录名</label>
		<input type="text" id="username" name="username" class="input-block-level required" value="${username}">
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required">
		<c:if test="${isValidateCodeLogin}"><div class="validateCode">
			<label class="input-label mid" for="validateCode">验证码</label>
			<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
		</div></c:if>
		<input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;
		<h3><div id="messageBox"  style="line-height:20px; -webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;" class=" alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div></h3>
	</form>
	</div>
	</div>
	<div class="footer">
		<!-- Copyright -->汇中网 &copy; 2016-2017
	</div>
	<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
</body>
</html>