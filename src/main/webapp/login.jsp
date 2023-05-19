<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오후 12:01:26</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<h1><%=request.getMethod() %></h1>
	<%
		if(request.getMethod().equals("GET")){
	%>
	<form method="post">
		<input name="id">
		<input type="password" name="pw">
		<button>로그인</button>
	</form>
	<%
		} else {
	
	%>
	<h2><%=request.getParameter("id") %></h2>
	<h2><%=request.getParameter("pw") %></h2>
	<%
		}
	%>
	
</body>
</html>