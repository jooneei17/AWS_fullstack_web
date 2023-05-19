<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="except.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오전 9:49:10</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>

<%-- 	<h1><% request.getParameter("val1"); %></h1> --%>
	<%
	
		String val1 = request.getParameter("val1");
		String val2 = request.getParameter("val2");
// 		System.out.println(val1);
// 		System.out.println(val2);
// 		out.println(val1);
// 		out.println(val2);
		
		int num1 = Integer.parseInt(val1);
		int num2 = Integer.parseInt(val2);
		
		int result = num1 / num2;
		out.println(result);
		
		out.println("<p> 결과 : " + result);
		
	%>


</body>
</html>