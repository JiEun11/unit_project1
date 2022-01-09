<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/boardStyle.css" />
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<meta charset="UTF-8">
<title>Login.jsp</title>
</head>
<body>
<div class="loginpage">
	<h1>"로그인 페이지"</h1>
	<form method="post" action="/bbs/board">
		<input type="text" name="id" placeholder="id" required><br> 
		<input type="password" name="password" placeholder="password" required><br>
		<p>
		<button type="submit" name="mem_btn" value="login">로그인</button>
		<button type="reset" value="reset">재작성</button>
	</form>
	<form method="get" action="/bbs/jsp/SignUp.jsp">
		<button type="submit" value="SignUp">회원가입</button>
	</form>
	<form method="get" action="/bbs/board">
		<button type="submit" name="detail_btn" value="main">메인 페이지</button>
	</form>
</div>

<jsp:include page="./bottom.jsp" flush='false' />

</body>
</html>