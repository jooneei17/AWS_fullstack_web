<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오전 11:11:47</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<h1>page 1</h1>
	<script>
// 		location.href="page2.jsp";
	</script>
	<% 
		response.sendRedirect("page2.jsp");
	%>
</body>
</html>