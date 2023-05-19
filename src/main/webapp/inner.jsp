<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 7.오후 12:10:10</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
<%
	/* 특정 아이피를 ban하기 */
	if(request.getRemoteAddr().equals("192.168.0.240")){
		out.println("<h1>당신의 ip는 ban되었습니다</h1>");
		return;
	}
%>
	<h1>request</h1>
	<%
		String cp = request.getContextPath();
		/* out.print(cp);  */
		Cookie[] cookies = request.getCookies();
		Enumeration<String> headerNames = request.getHeaderNames();
		request.getMethod(); //METHOD
		request.getRemoteAddr();
		request.getRemoteHost();
		HttpSession session2 = request.getSession();
		request.setAttribute("abcd", "1234");
		request.getAttribute("abcd");
// 		request.removeAttribute("abcd");
		
	%>
	<p>request.getContextPath() : <%=cp %></p>
	<p>request.getMethod() : <%=request.getMethod()%>
	<p>request.getRemoteAddr() : <%=request.getRemoteAddr()%>
	<p>request.getRemoteHost() : <%=request.getRemoteHost()%>
	<p>request.getAttribute("abcd") : <%=request.getAttribute("abcd")%>
	<%
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemoteHost());
		
		while(headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			out.print("<h2" + name + " :: " + request.getHeader(name));
		}
	%>
	
	<h1>response</h1>
	<%
		response.getWriter(); //out 객체
		
	
	%>
	HTTP의 특징 : 무상태, 비연결 
	TCP : 연결 
	UDP : 비연결
	
	<h1><%=session.getId()%></h1>
	<%=session.getMaxInactiveInterval()%>
	<%
		session.setMaxInactiveInterval(60 * 10);
	%>
	
</body>
</html>