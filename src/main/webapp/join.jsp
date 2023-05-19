<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="joinProcess" method="post">
		<input type="text" name="id" placeholder="id 입력"><br>
		<input type="password" name="pw" placeholder="pw 입력"><br>
		<input type="password" name="pw_chk" placeholder="pw 확인"><br>
		<input type="text" name="name" placeholder="이름 확인"><br>
		<hr>s
		성별
		<label><input type="radio" name="gender" value="male">남</label>
		<label><input type="radio" name="gender" value="female">여</label>
		<hr>
		취미
		<label><input type="checkbox" name="hobby" value="read">독서</label>
		<label><input type="checkbox" name="hobby" value="movie">영화감상</label>
		<label><input type="checkbox" name="hobby" value="fishing">낚시</label>
		<label><input type="checkbox" name="hobby" value="climing">등산</label>
		<hr>
		<button>회원가입</button>
	</form>

</body>
</html>