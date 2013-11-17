<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<%@ include file="main_import.jsp" %>
</head>
<body>
	<form action="login.htm?register" method="post" id="registerForm">
		<input type="hidden" id="passwordMd5" name="password">
		<table>
			<tr>
				<td>登录名: </td>
				<td>
					<input type="text" id="loginName" name="loginName" value="${user.loginName }">
					<spring:message code="required.loginName" />
				</td>
			</tr>
			<tr>
				<td>密码: </td>
				<td>
					<input type="password" id="password" >
					<spring:message code="required.password" />
				</td>
			</tr>
			<tr>
				<td>用户名: </td>
				<td>
					<input type="text" id="userName" name="userName" value="${user.userName }">
					<spring:message code="required.userName" />
				</td>
			</tr>
			<tr>
				<td>年龄: </td>
				<td>
					<input type="text" id="age" name="age" value="${user.age }">
					<spring:message code="restrict.age" />
				</td>
			</tr>
			<tr>
				<td>性别: </td>
				<td>
					<select id="sex" name="sex" >
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</td>
			</tr>
		</table>
	</form>
	<input type="button" onclick="register()" value="注册">
	<a href="login.htm">返回登录</a>
	
	<div id="registerError" style="color: red;"></div>
</body>

	<script type="text/javascript">
		var errormsg = "${errormsg}";
		if(errormsg != ""){
			$("#registerError").html(errormsg);
		}
	
		function register(){
			var password = $.trim($("#password").val());
			if(password == ""){
				$("#passwordMd5").val("");
			}else{
				$("#passwordMd5").val(CryptoJS.MD5(password));
			}

			$("#registerForm").submit();
		}
	</script>
</html>