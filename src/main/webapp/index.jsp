<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 7.오전 10:44:42</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<!-- JSP
	directive 지시자(선언) -> 해당 코드에서는 가장 윗줄
	
	, scriptlet, expression -->
	
	<%-- 주석 구문 
	directive
	<%@ page %>
	<%@ include %> 공통부분에 대한 처리
	<%@ taglib %> tag library 
	
	
	scriptlet
	<% %>
	<%! %>
	
	expression 표현식
	<%= %>
		
	
	--%>
	<%
	/* System.out.println(num); 선언하지 않았기 때문에 사용 불가*/ 
	%>
	<%
		System.out.println("콘솔에 출력");
	
		int num = 10; /* 지역변수 */
		System.out.println(num);
		List<Integer> integers = new ArrayList<>();
	%>
	<%
		System.out.println(num);
	%>
	
	<%!
		/* 전역 내 */
		int num = 30;
	
	%>
</body>
</html>