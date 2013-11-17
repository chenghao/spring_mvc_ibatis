<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱好列表</title>
<%@ include file="main_import.jsp" %>
</head>
<body>
	<h3>${userSession.userName}的爱好</h3>	
	
	<table border="1">
		<tr>
			<th>ID</th>
			<th>爱好</th>
			<th>操作</th>
		</tr>	
		<c:forEach var="hobby" items="${hobbys}">
			<tr id="hobby_${hobby.pid}">
				<td>${hobby.pid }</td>
				<td>${hobby.name }</td>
				<td><a href="hobby.htm?modify">修改</a>&nbsp;<a href="javascript:deleteHobby('${hobby.pid }')">删除</a></td>
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
	<br />
	<a href="user.htm">返回用户列表</a>
	
</body>
	<script type="text/javascript">
		function deleteHobby(pid){
			if (confirm("确认删除?")){ 
				$.ajax({
					url: "hobby.htm?deleteHobby",
					type: "POST",
					dataType: "json",
					data: {
						pid: pid
					},
					success: function(data){
						if(data.result == "1"){
							window.location.reload();
						}else{
							alert("删除失败。");
						}
					}
				});
			}
		}
		
		function more(pageTotal, pageNo){
			window.location.href = "hobby.htm?moreHobby&pageTotal="+pageTotal+"&pageNo="+pageNo;
		}
	</script>
</html>