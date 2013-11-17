<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<%@ include file="main_import.jsp" %>
</head>
<body>
	<form action="login.htm?login" method="post" id="loginForm">
		登录名： <input type="text" id="loginName" name="loginName" value="${user.loginName }"><br />
		密码： <input type="password" id="password"><br />
		<input type="hidden" name="password" id="passwordMd5">
		
		<input type="checkbox" id="autoLogin" name="autoLogin" value="autoLoginFlag">
		<label for="autoLogin">自动登录</label>
	</form>
	
	
	<input type="button" onclick="loginBut()" value="登录">
	<a href="login.htm?showRegister">注册</a>
	
	<div id="loginError" style="color: red;"></div>
	<div id="registerSuccess" style="color: red;"></div>
</body>

	<script type="text/javascript">
		var errormsg = "${errormsg}";
		if(errormsg != ""){
			$("#loginError").html(errormsg);
		}
		var successmsg = "${successmsg}";
		if(successmsg != ""){
			$("#registerSuccess").html(successmsg);
		}
		
		
		function loginBut(){
			var password = $.trim($("#password").val());
			if(password == ""){
				$("#passwordMd5").val("");
			}else{
				$("#passwordMd5").val(CryptoJS.MD5(password));
			}

			$("#loginForm").submit();
		}
	</script>

</html>