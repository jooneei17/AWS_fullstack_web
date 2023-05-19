<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 8.오후 12:49:04</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
	<h2>${true}</h2>	
	<h2>${10 / 3}</h2>	
	<h2>${1 / 0}</h2>	
	<h2>${"double"}</h2>	
	<h2>${'single'}</h2>	
	<!-- '1' eq 1 값은 true로 나온다. (빨간줄 떠서 없앤거) -->
	<h2>${10 gt 4 && 1 eq 1}</h2> 	
	<h2>${12 mod 5}</h2>
	<h2>${empty 0}</h2>
	<h2>${empty ""}</h2>
	<h2>${empty " "}</h2>
	<h2>${empty null}</h2>
	<h2>${empty {10, 20, 30}}</h2>
	<h2>${{10, 20, 30}}</h2>
	
</body>
</html>