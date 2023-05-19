<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오전 11:11:58</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<h1>page 2</h1>
	<%
	System.out.println("page2: " + request.getParameter("value"));
	//url에 queryString을 사용하여 value 직접 기입
	%>
	<h1>${param.value}</h1>
<%--<h1><%=pageContext.getAttribute("pc")%></h1> --%>
	<h1>${pc}</h1>
	
<%--<h1><%=request.getAttribute("req")%></h1> --%>
	<h1>${req}</h1>
</body>
</html>