<%@page import="java.util.List"%>
<%@page import="kr.co.jmymble.jsp.domain.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 9.오전 10:16:20</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
<!-- xss 공격에 대한 방지를 할 수 있다 -->

<%
	List<Board> boards = new ArrayList<>();
	boards.add(new Board("aaa", "bbb", "ccc"));
	boards.add(new Board("aaa", "bbb", "ccc"));
	boards.add(new Board("aaa", "bbb", "ccc"));
	
	request.setAttribute("boards", boards);

%>

	<c:set var="num" value="15" />
	<c:set var="title" value="<script>alert()</script>"/>
	<h1>${num}</h1>
	<h2><c:out value="안녕하세요"/></h2>
	<h2><c:out value="${100}"/></h2>
	<h2><c:out value="${title}" /></h2>
	
	
	<c:if test="${num == 20}">
		<h2>20입니다.</h2>
	</c:if>
	<c:if test="${num != 20}">
		<h2>20이 아닙니다</h2>
	</c:if>
	
	<c:choose>
		<c:when test="${num == 20}">
			<h3>20입니다</h3>
		</c:when>
		<c:when test="${num == 30}">
			<h3>30입니다</h3>
		</c:when>
		<c:otherwise>
			<h3>20도 30도 아닙니다</h3>
		</c:otherwise>
	</c:choose>
	
	<h2>forEach를 활용한 구구단 출력하기</h2>
	<c:forEach var="i" begin="2" end="9">
		<c:forEach var="j" begin="1" end="9">
			<!--<h4>${i} * ${j} = ${i*j}</h4> -->
		</c:forEach>
	</c:forEach>
	
	<c:forEach var="board" items="${boards}" varStatus="stat"  step="2">
		<h4>index : ${stat.index}</h4>
		<h4>count : ${stat.count}</h4>
		<h3>${board.title}</h3>
	</c:forEach>
</body>
</html>