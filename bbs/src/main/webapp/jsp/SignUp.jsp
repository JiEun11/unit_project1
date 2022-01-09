<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	<form method="post" action="/bbs/memcheck">
		<input type="text" name="id" placeholder="id를 입력하세요" required><br> 
		<input type="password" name="password" placeholder="비밀번호를 입력하세요" required><br>
		<input type="text" name="name" placeholder="이름을 입력하세요" required><br><br> 
		<input type="submit" name="mem_btn" value="sign-up">
		<input type="reset" value="reset">
	</form>

</body>
</html>