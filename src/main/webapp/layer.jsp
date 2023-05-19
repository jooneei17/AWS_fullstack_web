<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2023. 3. 7.오후 1:13:33</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
	<style>
		.container {height: 800px; background: orange;}
		.popup {width: 420px; position: absolute; top: 300px; right: 200px; display: none;}
		.popup label {display: block; background: navy; color: white; margin-top: -4px; padding: 8px;}
	
	</style>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js" integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
	$(function() {
		$(".popup :checkbox").change(function() {
// 			console.log("changed");
			$(".popup").hide();
// 			alert($.cookie("myCookie"))
// 			alert($.cookie("myCookie2"))
// 			$.cookie("popup", "daily", {expires:1});
			localStorage.setItem("sto_popup", "daily")
		});
		
		if(!localStorage.getItem("sto_popup")) {
// 			처음 상태 : 팝업 보이기
			$(".popup").show();
		}
		// session cookie
		// session의 lifecyle 브라우저가 닫히면 쿠키가 삭제
// 		$.cookie("myCookie", "yami", {expires:1});

		
	})	
	</script>

</head>
<body>
	<div class="container">
		<h1>본문</h1>
		
	</div>
	<div class="popup">
		<img alt="" src="https://image.yes24.com/dms/202108/IMG_222111(1).jpg">
		<label><input type="checkbox">하루동안 이 창 보지 않기</label>
	</div>
	
	<%
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			out.print("<p>" + cookie.getName() + ": : " + cookie.getValue() + " :: " + cookie.getMaxAge());
		}
		Cookie cookie = new Cookie("jsp", "cookie");
		cookie.setMaxAge(60 * 60); //1시간짜리 쿠키 
		response.addCookie(cookie);
	
	%>
</body>
</html>