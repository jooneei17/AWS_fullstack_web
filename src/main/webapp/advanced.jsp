<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오후 12:57:45</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<h2>scope</h2>
	<%
		//pageContext.setAttribute("value", 10);
		request.setAttribute("value", 20);
		
		// collection 
		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(5);
		list.add(8);
		list.add(20);
		
		//그냥 값을 추가하는 것만으로는 el을 사용할수 없다.
		request.setAttribute("list", list);
		request.setAttribute("arr", new int[] {30, 40, 50});
		
		
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 10);
		map.put("b", 20);
		map.put("c", 30);
		
		request.setAttribute("map", map);
	%>
	<h2>${value}</h2>
	<h2>${pageScope.value}</h2>
	<h2>${list}</h2>
	<h2>${list[2]}</h2>
	<h2>${arr[0]}</h2>
	<h2>${map}</h2>
	<h2>${map.b}</h2>
	<h2>${map['c']}</h2>
</body>
</html>