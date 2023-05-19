<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 7.오후 3:46:21</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<%
// 	response.sendError(404, "tmp move");
	
	//특정 위치로 보내기 (절대경로, 상대경로 모두 가능)
	response.sendRedirect("layer.jsp");
	
	%>
	
</body>
</html>