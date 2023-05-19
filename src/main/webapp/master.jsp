<%@page import="java.util.Date"%>
<%@page import="vo.Board"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오후 1:13:53</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<%
		List<Board> boards = new ArrayList<>();
		boards.add(new Board(1, "1글의 제목", "1글의 내용", new Date()));
		boards.add(new Board(1, "2글의 제목", "2글의 내용", new Date()));
		boards.add(new Board(1, "3글의 제목", "3글의 내용", new Date()));
		
		request.setAttribute("boards", boards);
	
	%>
	
	<h2>${boards[2].content}</h2> 
<!-- 	내부에서 getContent를 호출한 것과 같음 -->
	<h1>${boards[2].test }</h1>
<!-- 	test역시 getTest를 호출한 것이다 -->
	<h2>${pageContext.session.maxInactiveInterval }</h2>
	<form>
		<input name="value1">
		<input name="value2">
		<input type="checkbox" name="chk" value="1">
		<input type="checkbox" name="chk" value="2">
		<input type="checkbox" name="chk" value="3">
		<button>전송</button>
	</form>
	<h2>${param.value1 }</h2>
	<h2>${param.value2 }</h2>
	<h2>${paramValues.chk}</h2>
	<h2>${paramValues.chk[0]}</h2>
	<h2>${paramValues.chk[1]}</h2>
	<h2>${paramValues.chk[2]}</h2>
	<h2>${header["user-agent"]}</h2>
	

</body>
</html>