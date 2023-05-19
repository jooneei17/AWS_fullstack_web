<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오전 10:46:44</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<h2>outer : <%=request.getParameter("value") %></h2>
	<%@include file="inner.jsp" %>
</body>
</html>