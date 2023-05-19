<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오전 10:10:38</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<%
		if(exception instanceof ArithmeticException) {
			out.println("<h1>제수를 0으로 지정할 수 없습니다");
		} else if(exception instanceof NumberFormatException){
			out.println("<h1>지정 인수는 숫자를 사용하세요");	
		} else {
			out.println("<h1>알 수 업는 오류 발생 관리자에게 물어보세요");
		}
	%>
</body>
</html>