<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<%@ include file="main_import.jsp" %>
</head>
<body>
	<h3>
		当前用户名： ${userSession.userName }
		<a href="login.htm?logout" style="font-size: 10px; font-weight: normal; color: red;">退出</a>
	</h3>
	
	<c:if test="${userSession != null }">
		<a href="hobby.htm">查看兴趣爱好</a>
		
		<br /><br />
	</c:if>
	
	按用户名搜索： <input type="text" id="searchUserName" value="${userName}">
	<input type="button" value="搜索" onclick="search()"><br />
	
	<table border="1">
		<tr>
			<th>ID</th>
			<th>登录名</th>
			<th>用户名</th>
			<th>年龄</th>
			<th>性别</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.pid }</td>
				<td>${user.loginName }</td>
				<td>${user.userName }</td>
				<td>${user.age }</td>
				<td>${user.sexStr }</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${pageNo != 1 }">
		<a href="javascript:more('${pageTotal }', 1)">首页</a>
		<a href="javascript:more('${pageTotal }', '${pageNo - 1}')">上一页</a>
	</c:if>
	<c:if test="${pageNo != pageTotal && pageTotal != 0}">
		<a href="javascript:more('${pageTotal }', '${pageNo + 1}')">下一页</a>
		<a href="javascript:more('${pageTotal }', '${pageTotal}')">尾页</a>
	</c:if>
	<span>第${pageNo }页 / 共${pageTotal }页</span>
</body>

	<script type="text/javascript">
		function search(){
			more("${pageTotal}", 1);
		}
		function more(pageTotal, pageNo){
			var userName = $.trim($("#searchUserName").val());
			window.location.href = "user.htm?searchUser&userName="+userName+"&pageTotal="+pageTotal+"&pageNo="+pageNo;
		}
	</script>

</html>