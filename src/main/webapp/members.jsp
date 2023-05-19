<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 7.오전 11:07:50</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>가입일시</th>
		</tr>
	<%
	Connection conn = null;
	Class.forName("org.mariadb.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mariadb://np.jmymble.co.kr:3306/sample_db", "SAMPLE", "1234");

	//처리할 sql 구문
	String sql = "select * from tbl_member";
	
	// 문장 생성
	Statement stmt = conn.createStatement();
	
	//결과집합 반환
	ResultSet rs = stmt.executeQuery(sql);
	
	//결과 집합 >> 자바 객체
	while(rs.next()) { //반환이 true인 조건문에는 while 반복문이 잘 어울림. true일때 반복, false일 경우 
	%>
	
		<tr>
			<td><%=rs.getString("id")%></td>
			<td><%out.print(rs.getString("pw")); %></td>
			<td><%out.print(rs.getString("name")); %></td>
			<td><%out.print(rs.getString("regdate")); %></td>
		</tr>
		
	<%			
	}

	%>
	</table>
</body>
</html>