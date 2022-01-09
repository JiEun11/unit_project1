<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
form {
  display: inline-block;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<form method="post" action="/bbs/memcheck">
		<input type="text" name="id" placeholder="id" required><br> 
		<input type="password" name="password" placeholder="password" required><br>
		<input type="submit" name="mem_btn" value="login">
		<input type="reset" value="reset">
	</form>
	
	<form method="get" action="/bbs/jsp/SignUp.jsp">
		<input type="submit" value="SignUp">
	</form>

</body>
</html>