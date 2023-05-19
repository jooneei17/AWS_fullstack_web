<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오전 11:51:11</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<h1>forward</h1>
	<%
		System.out.println("forward: " + request.getParameter("value"));
	
		//pageContext는 private 인스턴스 변수처럼 외부에서 사용 불가하다.
		//출력 결과 역시 나오지 않음.
		pageContext.setAttribute("pc", "pcValue");
		
		request.setAttribute("req", "reqValue");
		request.getRequestDispatcher("page2.jsp").forward(request, response);
	%>
<!-- 	위 코드
		request.getRequestDispatcher("page2.jsp").forward(request, response);
		랑 같은 동작을 함 
-->
<%-- 	<jsp:forward page="page2.jsp" /> --%>
</body>
</html>