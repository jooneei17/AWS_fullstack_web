<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오전 10:31:44</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<%
		//영역 객체
		//page request session application
		pageContext.setAttribute("pageName", 1);
		application.setAttribute("appName", "myapp");
		session.setAttribute("session", "my info");
		
	%>
	<h1><%=page==this %></h1>
	<%
		System.out.println(pageContext.getAttribute("pageName"));
	%>
</body>
</html>