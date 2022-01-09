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
<title>회원정보 수정 페이지 </title>
</head>
<body>

	<form method="post" action="/bbs/memcheck">
		<input readonly id="id" name="id" value=${ sessionScope.user.id }><br>
		<input type="text" name="password" value=${ sessionScope.user.password } required><br>
		<input type="text" name="name" value=${ sessionScope.user.name } required><br>
		<button type="submit" name="mem_btn" value="mem_update">정보수정</button>
		<input type="reset" value="reset">
		<button type="submit" name="mem_btn" value="sign-out">회원탈퇴</button>
	</form>
	
	<form method="get" action="/bbs/board">
		<button type="submit" name="detail_btn" value="main">메인페이지</button>
	</form>

</body>
</html>